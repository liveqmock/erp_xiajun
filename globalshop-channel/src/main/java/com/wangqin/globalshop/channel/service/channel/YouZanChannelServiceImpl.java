package com.wangqin.globalshop.channel.service.channel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.enums.*;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.dal.youzan.*;
import com.wangqin.globalshop.channel.dal.youzan.PicModel;
import com.wangqin.globalshop.channel.dal.youzan.YouzanTradeGet;
import com.wangqin.globalshop.channel.dal.youzan.YouzanTradesSoldGet;
import com.wangqin.globalshop.channel.service.jingdong.SendStatus;
import com.wangqin.globalshop.channelapi.dal.ItemSkuVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.*;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.gen.v3_0_0.api.*;
import com.youzan.open.sdk.gen.v3_0_0.model.*;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemCreateResult.ItemSkuOpenModel;
import com.youzan.open.sdk.model.ByteWrapper;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.ContentType;
import org.apache.xerces.dom.PSVIAttrNSImpl;
import org.eclipse.jetty.util.StringUtil;
import org.eclipse.jetty.util.security.Credential.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.ParseException;
import java.util.*;

@Channel(type = ChannelType.YouZan)
public class YouZanChannelServiceImpl extends AbstractChannelService implements IChannelService {

    private DefaultYZClient yzClient = null;
    private static final int mode = 1; // 服务商

	private static Logger logger = LoggerFactory.getLogger("YouZanChannelServiceImpl");


	private Map<String, Long> expressMap = null;

    static Map<String, String> localExpressMap = new HashMap<String, String>();


    // 暂时硬编码
    static {
        localExpressMap.put("海淘一号仓", "167");
        // localExpressMap.put("顺帮快递", "167");
        localExpressMap.put("运通快递", "102");
        localExpressMap.put("顺丰", "42");
        localExpressMap.put("韵达", "4");
        localExpressMap.put("转运中国", "224");
        localExpressMap.put("邮客", "208");
        // localExpressMap.put("海狐", "167");
        localExpressMap.put("联邦转运", "27");
        localExpressMap.put("4PX", "51");
        // localExpressMap.put("海狐联邦转运", "167");
        // localExpressMap.put("GLS", "167");
    }


    public YouZanChannelServiceImpl(JdShopOauthDO shopOauthDO) {
        super(shopOauthDO);
        yzClient = new DefaultYZClient(new Token(this.shopOauth.getAccessToken()));// 免签名方式
    }

    /**
     * 网擒内部新增商品：channel_listting_item,channel_listting_item_sku
     * 新增外部商品和SKU信息
     *
     * @param item
     * @param youzanItemCreateResult
     */
    private AdapterData addOuterItem(ItemVo item, YouzanItemCreateResult youzanItemCreateResult) {
        AdapterData adapterData = new AdapterData();
        String alias = youzanItemCreateResult.getItem().getAlias();// 有赞别名

        Long numIid = youzanItemCreateResult.getItem().getItemId();// 有赞ID
        // 1、保存外部商品
        ChannelListingItemDO outerItem = new ChannelListingItemDO();

        outerItem.setChannelNo(String.valueOf(ChannelType.YouZan.getValue()));
        outerItem.setCompanyNo(shopOauth.getCompanyNo());
        outerItem.setShopCode(shopOauth.getShopCode());
        outerItem.setChannelItemAlias(alias);
        outerItem.setChannelItemCode(String.valueOf(numIid));
        outerItem.setItemCode(item.getItemCode());
        outerItem.setStatus(ItemStatus.LISTING.getCode());

        //补充必填信息
        outerItem.setIsDel(false);
        outerItem.setGmtCreate(new Date());
        outerItem.setGmtModify(new Date());
        //outerItem.setCreator(AppUtil.getLoginAccount());
        //outerItem.setModifier(AppUtil.getLoginAccount());
        outerItem.setCreator("-1");
        outerItem.setModifier("-1");

        adapterData.item = item;
        adapterData.outerItem = outerItem;

        List<ItemSkuVo> itemSkuList = item.getItemSkus();
        Map<String, Long> itemSkuIdMap = new HashMap<String, Long>();
        for (ItemSkuDO itemSku : itemSkuList) {
            itemSkuIdMap.put(itemSku.getSkuCode(), itemSku.getId());
        }

        // 2、更新外部SKU信息
        ItemSkuOpenModel[] goodsSku = youzanItemCreateResult.getItem().getSkus();
        if (goodsSku != null && goodsSku.length > 0) {
            for (int i = 0; i < goodsSku.length; i++) {
                ItemSkuOpenModel sku = goodsSku[i];
                if (StringUtil.isBlank(sku.getItemNo()) || itemSkuIdMap.get(sku.getItemNo()) == null) {
                    continue;
                }
                ChannelListingItemSkuDO outerItemSku = new ChannelListingItemSkuDO();

                outerItemSku.setPlatformType(PlatformType.YOUZAN.getCode());
                //外部信息
                outerItemSku.setChannelItemCode(outerItem.getChannelItemCode());
                outerItemSku.setChannelItemSkuCode(String.valueOf(sku.getSkuId()));
                //内部信息
                outerItemSku.setItemCode(outerItem.getItemCode());
                outerItemSku.setSkuCode(sku.getItemNo());

                //补充必填信息
                outerItemSku.setIsDel(false);
                outerItemSku.setGmtCreate(new Date());
                outerItemSku.setGmtModify(new Date());
                //outerItem.setCreator(AppUtil.getLoginAccount());
                //outerItem.setModifier(AppUtil.getLoginAccount());
                outerItemSku.setCreator("-1");
                outerItemSku.setModifier("-1");

                adapterData.outerItemSkus.add(outerItemSku);
            }
        }

        return adapterData;
    }


    /**
     * 网擒内部更新商品：channel_list_item,channel_list_item_sku
     *
     * @param item
     * @param youzanItemUpdateResult
     * @return
     */
    private AdapterData updateOuterItem(ItemVo item, YouzanItemUpdateResult youzanItemUpdateResult) {
        AdapterData adapterData = new AdapterData();
        // String alias = youzanItemUpdateResult.getItem().getAlias();//有赞别名
        Long numIid = youzanItemUpdateResult.getItemId();// 有赞ID
        // 1、保存外部商品
        ChannelListingItemDO outerItem = new ChannelListingItemDO();
        outerItem.setItemCode(item.getItemCode());
        outerItem.setChannelNo(shopOauth.getChannelNo());
        ChannelListingItemDO outerItemDb = this.outerItemService.queryPo(outerItem);
        if (outerItemDb == null) {
            throw new ErpCommonException("更新outerItem 商品信息错误,channel_listing_item未找到，item_code: "+item.getItemCode());
        } else {

            this.outerItemService.updateByPrimaryKey(outerItemDb);
        }
        List<ItemSkuVo> itemSkuList = item.getItemSkus();
        Map<String, Long> itemSkuIdMap = new HashMap<String, Long>();
        for (ItemSkuDO itemSku : itemSkuList) {
            itemSkuIdMap.put(itemSku.getSkuCode(), itemSku.getId());
        }

        // 2、更新外部SKU信息
        YouzanItemGetParams youzanItemGetParams = new YouzanItemGetParams();
        youzanItemGetParams.setItemId(Long.valueOf(outerItemDb.getChannelItemCode()));
        youzanItemGetParams.setAlias(outerItemDb.getChannelItemAlias());
        YouzanItemGetResult itemGetResult = youzanItemGet(youzanItemGetParams);
        com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemGetResult.ItemSkuOpenModel[] goodsSku = itemGetResult.getItem()
                .getSkus();
        if (goodsSku != null && goodsSku.length > 0) {
            for (int i = 0; i < goodsSku.length; i++) {
                YouzanItemGetResult.ItemSkuOpenModel sku = goodsSku[i];
                if (StringUtil.isBlank(sku.getItemNo()) || itemSkuIdMap.get(sku.getItemNo()) == null) {
                    continue;
                }
                ChannelListingItemSkuDO outerItemSku = new ChannelListingItemSkuDO();


                outerItemSku.setSkuCode(sku.getItemNo());
                outerItemSku.setPlatformType(PlatformType.YOUZAN.getCode());
                ChannelListingItemSkuDO oldOuterItemSku = this.outerItemSkuService.queryPo(outerItemSku);


                //外部信息
                outerItemSku.setChannelItemCode(outerItemDb.getChannelItemCode());
                outerItemSku.setChannelItemSkuCode(String.valueOf(sku.getSkuId()));
                //内部信息
                outerItemSku.setItemCode(outerItemDb.getItemCode());


                outerItemSku.setCompanyNo(shopOauth.getCompanyNo());

				outerItemSku.init4NoLogin();
                if (oldOuterItemSku == null) {
                    this.outerItemSkuService.insert(outerItemSku);
                } else {
                    outerItemSku.setId(oldOuterItemSku.getId());
                    this.outerItemSkuService.updateByPrimaryKeySelective(outerItemSku);
                }
            }
        }
        return adapterData;
    }

    /**
     * 新增商品图片地址
     *
     * @param item
     * @return
     */
    private YouzanItemCreateParams createYouzanAddItem(ItemVo item) {
        YouzanItemCreateParams youzanItemCreateParams = new YouzanItemCreateParams();
        youzanItemCreateParams.setTitle(item.getItemName());
        if (StringUtils.isNotEmpty(item.getDetail())) {
            try {
                // String decodeStr = URLDecoder.decode(item.getDetail(),"UTF-8");
                youzanItemCreateParams.setDesc(item.getDetail());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            youzanItemCreateParams.setDesc(item.getItemName());
        }
        if (item.getFreight() != null && item.getFreight() > 0) {
            youzanItemCreateParams.setPostFee((long) (item.getFreight() * 100));
        } else {
            youzanItemCreateParams.setPostFee(0L);
        }
        // 图片
        String itemPic = item.getMainPic();
        ItemImages itemImages = initImages(itemPic);
        if (itemImages != null) {
            ByteWrapper[] byteWrapperArr = itemImages.getByteWrappers();
            String imageIds = null;
            for (int i = 0; i < byteWrapperArr.length; i++) {
                ByteWrapper[] pByteWrapperArr = new ByteWrapper[1];
                pByteWrapperArr[0] = byteWrapperArr[i];
                YouzanMaterialsStoragePlatformImgUploadParams youzanMaterialsStoragePlatformImgUploadParams = new YouzanMaterialsStoragePlatformImgUploadParams();
                youzanMaterialsStoragePlatformImgUploadParams.setImage(pByteWrapperArr);
                YouzanMaterialsStoragePlatformImgUploadResult result = youzanMaterialsStoragePlatformImgUpload(
                        youzanMaterialsStoragePlatformImgUploadParams);
                if (imageIds == null) {
                    imageIds = result.getImageId().toString();
                } else {
                    imageIds += "," + result.getImageId().toString();
                }
            }
            youzanItemCreateParams.setImageIds(imageIds);
        }

        youzanItemCreateParams.setItemNo(item.getItemCode());
        // SKU
        List<ItemSkuVo> itemSkus = item.getItemSkus();
        // List<YouZanSku> youZanSkuList = initSkuJson(itemSkus);
        List<Map<String, Object>> youZanSkuList = initSkuJsonOne(itemSkus);
        if (CollectionUtils.isNotEmpty(youZanSkuList)) {
            String skusWithJson = HaiJsonUtils.toJson(youZanSkuList);
            youzanItemCreateParams.setSkuStocks(skusWithJson);
        }


//        Double minSalePrice = itemSkus.get(0).getSalePrice();
//        Long totalQuantity = 0L;
//        for (ItemSkuVo itemSku : itemSkus) {
//            if (itemSku.getSalePrice() < minSalePrice) {
//                minSalePrice = itemSku.getSalePrice();
//            }
//			totalQuantity += itemSku.getTotalAvailableInv();
//        }


		Double minSalePrice = itemSkus.get(0).getChannelSalePrice(shopOauth.getChannelNo());
		Long totalQuantity = 0L;
		for (ItemSkuVo itemSku : itemSkus) {
			if (itemSku.getChannelSalePrice(shopOauth.getChannelNo()) < minSalePrice) {
				minSalePrice = itemSku.getChannelSalePrice(shopOauth.getChannelNo());
			}
			totalQuantity += itemSku.getTotalAvailableInv();
		}

        youzanItemCreateParams.setPrice((long) (minSalePrice * 100));
		youzanItemCreateParams.setQuantity(totalQuantity);
        return youzanItemCreateParams;
    }

    /**
     * 更新图片地址
     *
     * @param item
     * @return
     */
    private YouzanItemUpdateParams createYouzanUpdateItem(ItemVo item) {
        YouzanItemUpdateParams youzanItemUpdateParams = new YouzanItemUpdateParams();
        youzanItemUpdateParams.setTitle(item.getItemName());
        if (StringUtils.isNotEmpty(item.getDetail())) {
            try {
                // String decodeStr = URLDecoder.decode(item.getDetail(),"UTF-8");
                youzanItemUpdateParams.setDesc(item.getDetail());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (item.getFreight() != null && item.getFreight() > 0) {
            youzanItemUpdateParams.setPostFee((long) (item.getFreight() * 100));
        } else {
            youzanItemUpdateParams.setPostFee(0L);
        }
        // 图片
        String itemPic = item.getMainPic();
        ItemImages itemImages = initImages(itemPic);
        if (itemImages != null) {
            ByteWrapper[] byteWrapperArr = itemImages.getByteWrappers();
            String imageIds = null;
            for (int i = 0; i < byteWrapperArr.length; i++) {
                ByteWrapper[] pByteWrapperArr = new ByteWrapper[1];
                pByteWrapperArr[0] = byteWrapperArr[i];
                YouzanMaterialsStoragePlatformImgUploadParams youzanMaterialsStoragePlatformImgUploadParams = new YouzanMaterialsStoragePlatformImgUploadParams();
                youzanMaterialsStoragePlatformImgUploadParams.setImage(pByteWrapperArr);
                YouzanMaterialsStoragePlatformImgUploadResult result = youzanMaterialsStoragePlatformImgUpload(
                        youzanMaterialsStoragePlatformImgUploadParams);
                if (imageIds == null) {
                    imageIds = result.getImageId().toString();
                } else {
                    imageIds += "," + result.getImageId().toString();
                }
            }
            youzanItemUpdateParams.setImageIds(imageIds);
        }
        // SKU
        List<ItemSkuVo> itemSkus = item.getItemSkus();
        List<Map<String, Object>> youZanSkuList = initSkuJsonOne(itemSkus);
        if (CollectionUtils.isNotEmpty(youZanSkuList)) {
            String skusWithJson = HaiJsonUtils.toJson(youZanSkuList);
            youzanItemUpdateParams.setSkuStocks(skusWithJson);
        }

//        Double minSalePrice = itemSkus.get(0).getSalePrice();
//        Long totalQuantity= 0L;
//        for (ItemSkuVo itemSku : itemSkus) {
//            if (itemSku.getSalePrice() < minSalePrice) {
//                minSalePrice = itemSku.getSalePrice();
//            }
//			totalQuantity += itemSku.getTotalAvailableInv();
//        }

		Double minSalePrice = itemSkus.get(0).getChannelSalePrice(shopOauth.getChannelNo());
		Long totalQuantity= 0L;
		for (ItemSkuVo itemSku : itemSkus) {
			if (itemSku.getChannelSalePrice(shopOauth.getChannelNo()) < minSalePrice) {
				minSalePrice = itemSku.getChannelSalePrice(shopOauth.getChannelNo());
			}
			totalQuantity += itemSku.getTotalAvailableInv();
		}

        youzanItemUpdateParams.setPrice((long) (minSalePrice * 100));

		youzanItemUpdateParams.setQuantity(totalQuantity);

        return youzanItemUpdateParams;
    }

    private List<Map<String, Object>> initSkuJsonOne(List<ItemSkuVo> itemSkus) {
        List<Map<String, Object>> youZanSkuList = Lists.newArrayList();
        Map<String, ItemSkuVo> itemSkuMap = Maps.newHashMap();
        Set<String> colorSet = Sets.newHashSet();
        Set<String> scaleSet = Sets.newHashSet();
        if (CollectionUtils.isNotEmpty(itemSkus)) {
            for (ItemSkuVo itemSku : itemSkus) {
                String colorScaleKey = "";
                if (StringUtil.isNotBlank(itemSku.getColor())) {
                    colorSet.add(itemSku.getColor());
                    colorScaleKey += itemSku.getColor();
                }
                if (StringUtil.isNotBlank(itemSku.getSize())) {
                    scaleSet.add(itemSku.getSize());
                    colorScaleKey += itemSku.getSize();
                }
                itemSkuMap.put(colorScaleKey, itemSku);
            }
            if (colorSet.size() > 0 && scaleSet.size() == 0) {
                for (String color : colorSet) {
                    Map<String, Object> skuStocksMap = new HashMap<String, Object>();
                    ItemSkuVo itemSku = itemSkuMap.get(color);
//                    skuStocksMap.put("price", itemSku.getSalePrice() * 100);
					skuStocksMap.put("price", itemSku.getChannelSalePrice(shopOauth.getChannelNo()) * 100);
                    Long skuQuantity = 0L;
                    Long lockedVirtualInv = itemSku.getInventoryDO().getLockedVirtualInv();
                    if (itemSku.getInventoryDO().getVirtualInv() > 0) {
                        skuQuantity = itemSku.getInventoryDO().getVirtualInv() - lockedVirtualInv;
                    } else {
                        skuQuantity = itemSku.getTotalAvailableInv() - lockedVirtualInv;
                    }
                    if (skuQuantity < 0) {
                        skuQuantity = 0L;
                    }
                    skuStocksMap.put("quantity", skuQuantity);
                    skuStocksMap.put("item_no", itemSku.getSkuCode()); // 重点：这里的sku_item_no对应的有赞 outer_id
                    List<Map<String, Object>> skusList = new ArrayList<>();
                    Map<String, Object> skusColorMap = new HashMap<String, Object>();
                    skusColorMap.put("v", itemSku.getColor());
                    skusColorMap.put("k", "颜色");
                    skusList.add(skusColorMap);

                    skuStocksMap.put("skus", skusList);
                    youZanSkuList.add(skuStocksMap);
                }
            } else if (scaleSet.size() > 0 && colorSet.size() == 0) {
                for (String scale : scaleSet) {
                    Map<String, Object> skuStocksMap = new HashMap<String, Object>();
                    ItemSkuVo itemSku = itemSkuMap.get(scale);
                    //skuStocksMap.put("price", itemSku.getSalePrice() * 100);
					skuStocksMap.put("price", itemSku.getChannelSalePrice(shopOauth.getChannelNo()) * 100);
                    Long skuQuantity = 0L;
                    Long lockedVirtualInv = itemSku.getInventoryDO().getLockedVirtualInv();
                    if (itemSku.getInventoryDO().getVirtualInv() > 0) {
                        skuQuantity = itemSku.getInventoryDO().getVirtualInv() - lockedVirtualInv;
                    } else {
                        skuQuantity = itemSku.getTotalAvailableInv() - lockedVirtualInv;
                    }
                    if (skuQuantity < 0) {
                        skuQuantity = 0L;
                    }
                    skuStocksMap.put("quantity", skuQuantity);
                    skuStocksMap.put("item_no", itemSku.getSkuCode()); // 重点：这里的sku_item_no对应的有赞 outer_id
                    List<Map<String, Object>> skusList = new ArrayList<>();
                    Map<String, Object> skusScaleMap = new HashMap<String, Object>();
                    skusScaleMap.put("v", itemSku.getScale());
                    skusScaleMap.put("k", "尺寸");
                    skusList.add(skusScaleMap);

                    skuStocksMap.put("skus", skusList);
                    youZanSkuList.add(skuStocksMap);
                }
            } else {
                for (String color : colorSet) {
                    for (String scale : scaleSet) {
                        Map<String, Object> skuStocksMap = new HashMap<String, Object>();
                        ItemSkuVo itemSku = itemSkuMap.get(color + scale);
                        if (itemSku == null) {//这里表示没有这个颜色和尺寸组合，但是有赞要求你传，没办法，价格设高，数量为0
                            skuStocksMap.put("price", 100000 * 100);
                            skuStocksMap.put("quantity", 0);
                            skuStocksMap.put("item_no", ""); // 重点：这里的sku_item_no对应的有赞 outer_id
                            List<Map<String, Object>> skusList = new ArrayList<>();
                            Map<String, Object> skusColorMap = new HashMap<String, Object>();
                            Map<String, Object> skusScaleMap = new HashMap<String, Object>();
                            skusColorMap.put("v", color);
                            skusColorMap.put("k", "颜色");
                            skusList.add(skusColorMap);
                            skusScaleMap.put("v", scale);
                            skusScaleMap.put("k", "尺寸");
                            skusList.add(skusScaleMap);

                            skuStocksMap.put("skus", skusList);
                            youZanSkuList.add(skuStocksMap);
                        } else {
                            //skuStocksMap.put("price", itemSku.getSalePrice() * 100);
							skuStocksMap.put("price", itemSku.getChannelSalePrice(shopOauth.getChannelNo()) * 100);
                            Long skuQuantity = 0L;
                            Long lockedVirtualInv = itemSku.getInventoryDO().getLockedVirtualInv();
                            if (itemSku.getInventoryDO().getVirtualInv() > 0) {
                                skuQuantity = itemSku.getInventoryDO().getVirtualInv() - lockedVirtualInv;
                            } else {
                                skuQuantity = itemSku.getTotalAvailableInv() - lockedVirtualInv;
                            }
                            if (skuQuantity < 0) {
                                skuQuantity = 0L;
                            }
                            skuStocksMap.put("quantity", skuQuantity);
                            skuStocksMap.put("item_no", itemSku.getSkuCode()); // 重点：这里的sku_item_no对应的有赞 outer_id
                            List<Map<String, Object>> skusList = new ArrayList<>();
                            Map<String, Object> skusColorMap = new HashMap<String, Object>();
                            Map<String, Object> skusScaleMap = new HashMap<String, Object>();
                            skusColorMap.put("v", itemSku.getColor());
                            skusColorMap.put("k", "颜色");
                            skusList.add(skusColorMap);
                            skusScaleMap.put("v", itemSku.getScale());
                            skusScaleMap.put("k", "尺寸");
                            skusList.add(skusScaleMap);

                            skuStocksMap.put("skus", skusList);
                            youZanSkuList.add(skuStocksMap);
                        }
                    }
                }
            }
        } else {
            throw new ErpCommonException("没有SKU");
        }
        return youZanSkuList;
    }


	/**
	 * 以前的逻辑不对，重新写一个
	 * @param itemSkus
	 * @return
	 */
	private List<Map<String, Object>> initSkuJsonNew(List<ItemSkuVo> itemSkus) {
		List<Map<String, Object>> youZanSkuList = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(itemSkus)) {
			for (ItemSkuVo itemSku : itemSkus) {
				//开始
				Map<String, Object> skuStocksMap = new HashMap<String, Object>();
				skuStocksMap.put("price", itemSku.getSalePrice() * 100);
				skuStocksMap.put("quantity", itemSku.getTotalAvailableInv());
				skuStocksMap.put("item_no", itemSku.getSkuCode());
				List<Map<String, Object>> skusList = new ArrayList<>();
				Map<String, Object> skusColorMap = new HashMap<String, Object>();
				Map<String, Object> skusScaleMap = new HashMap<String, Object>();
				skusColorMap.put("v", itemSku.getColor());
				skusColorMap.put("k", "颜色");
				skusList.add(skusColorMap);
				skusScaleMap.put("v", itemSku.getSize());
				skusScaleMap.put("k", "尺寸");
				skusList.add(skusScaleMap);
				skuStocksMap.put("skus", skusList);
				youZanSkuList.add(skuStocksMap);
			}
		} else {
			throw new ErpCommonException("没有SKU");
		}
		return youZanSkuList;
	}

    private ItemImages initImages(String itemPic) {
        if (StringUtils.isNotEmpty(itemPic)) {
            ItemImages itemImages = new ItemImages();
            PicModel pm = HaiJsonUtils.toBean(itemPic, PicModel.class);
            ByteWrapper[] byteWrappers = new ByteWrapper[pm.getPicList().size()];
            int i = 0;
            StringBuilder sbImgs = new StringBuilder();
            // 主图排第一位
            int mainIndex = 0;
            if (pm.getMainPicNum() != null) {
                mainIndex = Integer.valueOf(pm.getMainPicNum()) - 1;
            }
            if (mainIndex != 0) {
                Collections.swap(pm.getPicList(), 0, mainIndex);
            }

            for (PicModel.PicList pic : pm.getPicList()) {
                String url = pic.getUrl();
                Integer index = url.indexOf("?");
                String picKey = null;
                if (index > 0) {
                    String urlWithoutQuery = url.substring(0, index);
                    picKey = urlWithoutQuery.substring(urlWithoutQuery.lastIndexOf("/") + 1);
                } else {
                    picKey = url.substring(url.lastIndexOf("/") + 1);
                }


                try {
                    URL urlImg = new URL(url);
                    URLConnection con = urlImg.openConnection();
                    con.setConnectTimeout(10 * 1000);
                    byteWrappers[i] = new ByteWrapper(picKey, con.getInputStream(), ContentType.create("image/jpeg"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                sbImgs.append(picKey.substring(0, picKey.lastIndexOf(".")));
                if (i < (pm.getPicList().size() - 1)) {
                    sbImgs.append(",");
                }
                i++;
            }
            itemImages.setByteWrappers(byteWrappers);
            itemImages.setImageIds(sbImgs.toString());
            return itemImages;
        }
        return null;
    }

    // 添加商品
    public YouzanItemCreateResult youzanItemCreate(YouzanItemCreateParams youzanItemCreateParams) {
        YouzanItemCreate youzanItemCreate = new YouzanItemCreate();
        youzanItemCreate.setAPIParams(youzanItemCreateParams);
        YouzanItemCreateResult result = yzClient.invoke(youzanItemCreate);
        //todo 没有对业务处理逻辑进行判断，直接就返回数据
        return result;
    }

    // 更新商品
    public YouzanItemUpdateResult youzanItemUpdate(YouzanItemUpdateParams youzanItemUpdateParams) {
        YouzanItemUpdate youzanItemUpdate = new YouzanItemUpdate();
        youzanItemUpdate.setAPIParams(youzanItemUpdateParams);
        YouzanItemUpdateResult result = yzClient.invoke(youzanItemUpdate);
        return result;
    }

    // 下架
    public YouzanItemUpdateDelistingResult youzanItemUpdateDelisting(
            YouzanItemUpdateDelistingParams youzanItemUpdateDelistingParams) {
        YouzanItemUpdateDelisting youzanItemUpdateDelisting = new YouzanItemUpdateDelisting();
        youzanItemUpdateDelisting.setAPIParams(youzanItemUpdateDelistingParams);
        YouzanItemUpdateDelistingResult result = yzClient.invoke(youzanItemUpdateDelisting);
        return result;
    }

    // 上架
    public YouzanItemUpdateListingResult youzanItemUpdateListing(
            YouzanItemUpdateListingParams youzanItemUpdateListingParams) {
        YouzanItemUpdateListing youzanItemUpdateListing = new YouzanItemUpdateListing();
        youzanItemUpdateListing.setAPIParams(youzanItemUpdateListingParams);
        YouzanItemUpdateListingResult result = yzClient.invoke(youzanItemUpdateListing);
        return result;
    }

    // 更新sku信息(例如：库存)
    public YouzanItemSkuUpdateResult youzanItemSkuUpdate(YouzanItemSkuUpdateParams youzanItemSkuUpdateParams) {
        YouzanItemSkuUpdate youzanItemSkuUpdate = new YouzanItemSkuUpdate();
        youzanItemSkuUpdate.setAPIParams(youzanItemSkuUpdateParams);
        YouzanItemSkuUpdateResult result = yzClient.invoke(youzanItemSkuUpdate);
        return result;
    }

    // 获取单个商品信息
    public YouzanItemGetResult youzanItemGet(YouzanItemGetParams youzanItemGetParams) {
        YouzanItemGet youzanItemGet = new YouzanItemGet();
        youzanItemGet.setAPIParams(youzanItemGetParams);
        YouzanItemGetResult result = yzClient.invoke(youzanItemGet);
        return result;
    }

    // @Override
    public YouzanMaterialsStoragePlatformImgUploadResult youzanMaterialsStoragePlatformImgUpload(
            YouzanMaterialsStoragePlatformImgUploadParams youzanMaterialsStoragePlatformImgUploadParams) {
        YouzanMaterialsStoragePlatformImgUpload youzanMaterialsStoragePlatformImgUpload = new YouzanMaterialsStoragePlatformImgUpload();
        youzanMaterialsStoragePlatformImgUpload.setAPIParams(youzanMaterialsStoragePlatformImgUploadParams);
        YouzanMaterialsStoragePlatformImgUploadResult result = yzClient.invoke(youzanMaterialsStoragePlatformImgUpload);
        return result;
    }

    /**
     * 发货反馈,可以根据子订单进行发货
	 * 支持多个主订单+多个子订单发货
     *
     * @param orderList
     * @param shippingOrder
     */
    @Override
    public void syncLogisticsOnlineConfirm(List<MallSubOrderDO> orderList, ShippingOrderDO shippingOrder) {

        logger.info("有赞发货");
        boolean hasFailed = false;
        for (MallSubOrderDO order : orderList) {
            try {
                // 获取第三方订单
                String tid = order.getChannelOrderNo();
                // 获取物流信息
                String logisticNo = shippingOrder.getLogisticNo();
                String logisticCompany = shippingOrder.getLogisticCompany();

                // 调用"商家已发货"
                String expressType = null;
                if (logisticCompany != null) {
                    expressType = localExpressMap.get(logisticCompany);
                }
                if (expressType == null) {
                    expressType = "1";
                }
                YouzanLogisticsOnlineConfirmParams youzanLogisticsOnlineConfirmParams = new YouzanLogisticsOnlineConfirmParams();

                youzanLogisticsOnlineConfirmParams.setTid(tid);
                youzanLogisticsOnlineConfirmParams.setOutStype(expressType);
                youzanLogisticsOnlineConfirmParams.setOutSid(logisticNo);
                youzanLogisticsOnlineConfirmParams.setOids(order.getChannelSubOrderNo());

                YouzanLogisticsOnlineConfirm youzanLogisticsOnlineConfirm = new YouzanLogisticsOnlineConfirm();
                youzanLogisticsOnlineConfirm.setAPIParams(youzanLogisticsOnlineConfirmParams);
                YouzanLogisticsOnlineConfirmResult result = yzClient.invoke(youzanLogisticsOnlineConfirm);
                if (!hasFailed && !result.getIsSuccess()) {
                    hasFailed = true;
                    this.logger.error("同步发货给 有赞 返回结果异常：" + result.toString());
					throw new ErpCommonException("youzan syncLogisticsOnlineConfirm error",result.toString());
                }
            } catch (Exception e) {
                hasFailed = true;
                logger.error("有赞发货异常: ", e);
                throw new ErpCommonException("youzan syncLogisticsOnlineConfirm error",e.getMessage());
            }
        }

        if (!hasFailed) {
            try {
                shippingOrder.setSyncSendStatus(1);
                shippingOrderService.updateByPrimaryKey(shippingOrder);
            } catch (Exception e) {
                this.logger.error("同步发货给 有赞,更新发货单状态异常");
				throw new ErpCommonException("youzan syncLogisticsOnlineConfirm error",e.getMessage());
            }
        }
    }

    public void getLogisticType() {
        YouzanLogisticsExpressGetParams youzanLogisticsExpressGetParams = new YouzanLogisticsExpressGetParams();

        YouzanLogisticsExpressGet youzanLogisticsExpressGet = new YouzanLogisticsExpressGet();
        youzanLogisticsExpressGet.setAPIParams(youzanLogisticsExpressGetParams);
        YouzanLogisticsExpressGetResult result = yzClient.invoke(youzanLogisticsExpressGet);
        YouzanLogisticsExpressGetResult.LogisticsExpressOpenApiModel[] allExpress = result.getAllExpress();
        if (allExpress == null || allExpress.length == 0) {
            return;
        }
        expressMap = new HashMap<String, Long>();
        for (YouzanLogisticsExpressGetResult.LogisticsExpressOpenApiModel model : allExpress) {
            Long id = model.getId();
            String name = model.getName();
            if (StringUtils.isNotBlank(name)) {
                expressMap.put(name, id);
            }
        }
    }

    @Override
    public AdapterData adapterAuth() {
        //不需要授权，可以优化，如果过期，则不主动刷新，除非refreshtoken也没用
        return null;
    }

	/*
     * 实现IChannelAdapter接口
	 * 
	 * @see
	 * com.wangqin.service.channels.IChannelAdapter#adapterCreateItem(com.wangqin.
	 * model.Item)
	 */

    @Override
    public AdapterData adapterCreateItem(ItemVo item) {
        // 构造上传的商品对象
        YouzanItemCreateParams youzanItemCreateParams = createYouzanAddItem(item);
        YouzanItemCreateResult youzanItemCreateResult = youzanItemCreate(youzanItemCreateParams);

        //item.setImageIds(youzanItemCreateParams.getImageIds());
        return addOuterItem(item, youzanItemCreateResult);
    }

    @Override
    public AdapterData adapterUpdateItem(ItemVo item, ChannelListingItemDO outerItem) {
        YouzanItemUpdateParams youzanItemUpdateParams = createYouzanUpdateItem(item);
        youzanItemUpdateParams.setItemId(Long.valueOf(outerItem.getChannelItemCode()));
        //youzanItemUpdateParams.setRemoveImageIds(outerItem.getImageIds());
        YouzanItemUpdateResult youzanItemUpdateResult = youzanItemUpdate(youzanItemUpdateParams);

        //item.setImageIds(youzanItemUpdateParams.getImageIds());
        updateOuterItem(item, youzanItemUpdateResult);
        return null;
    }

    @Override
    public AdapterData adapterListingItem(ItemVo item, ChannelListingItemDO outerItem) {
        YouzanItemUpdateListingParams youzanItemUpdateListingParams = new YouzanItemUpdateListingParams();
        youzanItemUpdateListingParams.setItemId(Long.valueOf(outerItem.getChannelItemCode()));
        youzanItemUpdateListing(youzanItemUpdateListingParams);
        return null;
    }

    @Override
    public AdapterData adapterDelistingItem(ItemVo item, ChannelListingItemDO outerItem) {
        YouzanItemUpdateDelistingParams youzanItemUpdateDelistingParams = new YouzanItemUpdateDelistingParams();
        youzanItemUpdateDelistingParams.setItemId(Long.valueOf(outerItem.getChannelItemCode()));
        youzanItemUpdateDelisting(youzanItemUpdateDelistingParams);
        return null;
    }

    @Override
    public AdapterData adapterUpdateSkuInventory(ChannelListingItemSkuDO sku, InventoryDO inventory) {
        YouzanItemSkuUpdateParams youzanItemSkuUpdateParams = new YouzanItemSkuUpdateParams();
        youzanItemSkuUpdateParams.setItemId(Long.valueOf(sku.getChannelItemCode()));
        youzanItemSkuUpdateParams.setSkuId(Long.valueOf(sku.getChannelItemSkuCode()));
        //youzanItemSkuUpdateParams.setQuantity(inventory.getSysInventory() + "");
        youzanItemSkuUpdate(youzanItemSkuUpdateParams);
        return null;
    }

    @Override
    public void syncItem(HttpServletRequest request, HttpServletResponse respose) throws Exception {
        throw new Exception("有赞 不支持此方法");
    }

    @Override
    public void syncOrder(HttpServletRequest request, HttpServletResponse respose) throws Exception {
    }


	/**
	 * 只能查询在售商品,目前没看到有更新时间这个参数，暂时查全部在售商品,那么30分钟抓一次好了
	 * @param startTime
	 * @param endTime
	 */
	@Override
	public void getItems(Date startTime, Date endTime){



		Boolean hasNext = true;
		Long pageNo = 1L;
		Long pageSize = 10L;


		YouzanItemsOnsaleGetParams youzanItemsOnsaleGetParams = new YouzanItemsOnsaleGetParams();
		youzanItemsOnsaleGetParams.setPageSize(pageSize);
		//youzanItemsOnsaleGetParams.setUpdateTimeStart(startTime.getTime());
		//youzanItemsOnsaleGetParams.setUpdateTimeEnd(endTime.getTime());
		YouzanItemsOnsaleGet youzanItemsOnsaleGet = new YouzanItemsOnsaleGet();


		while(hasNext){
			youzanItemsOnsaleGetParams.setPageNo(pageNo);
			youzanItemsOnsaleGet.setAPIParams(youzanItemsOnsaleGetParams);
			YouzanItemsOnsaleGetResult result = yzClient.invoke(youzanItemsOnsaleGet);

			if(result.getCount() == null || result.getCount() <= pageNo*pageSize){
				hasNext = false;
			}else{
				hasNext = true;
				pageNo++;
			}
            logger.info("抓到的商品信息: "+BaseDto.toString(result));
            // 处理商品转换问题
			YouzanItemsOnsaleGetResult.ItemListOpenModel[] items = result.getItems();
			for (int i = 0; i < items.length; i++) {
				//this.createOrupdateItem(items[i]);




			}
			// 处理插入item， channel_item 问题
		}

	}


	/**
	 * 有赞这个神经病，一个item，只有一个sku
	 * @param youzanItem
	 */
	private void createOrupdateItem(YouzanItemsOnsaleGetResult.ItemListOpenModel youzanItem){

		YouzanItemGetResult.ItemDetailOpenModel youzanItemSku = getItemSkus(youzanItem.getItemId());
		//分别操作4张表，channel_list_item

		ChannelListingItemDO channelItem = new ChannelListingItemDO();
		channelItem.setChannelNo(shopOauth.getChannelNo());
		channelItem.setCompanyNo(shopOauth.getCompanyNo());
		channelItem.setShopCode(shopOauth.getShopCode());

		channelItem.setChannelItemCode(youzanItem.getItemId()+"");
		channelItem.setItemCode(youzanItem.getItemNo());
		channelItem.setChannelItemAlias(youzanItem.getTitle());
		channelItem.setStatus(ListingStatus.LISTING_STATUS.getStatus());

		channelItem.init4NoLogin();

	}
	/**
	 * 根据tbspuid获取单个sku的信息
	 * @param channnelItemCode
	 * @return
	 */
	private YouzanItemGetResult.ItemDetailOpenModel getItemSkus(Long channnelItemCode){
		YouzanItemGetParams youzanItemGetParams = new YouzanItemGetParams();

		youzanItemGetParams.setItemId(channnelItemCode);

		YouzanItemGet youzanItemGet = new YouzanItemGet();
		youzanItemGet.setAPIParams(youzanItemGetParams);
		YouzanItemGetResult result = yzClient.invoke(youzanItemGet);
		logger.info("商品详情："+channnelItemCode+" "+BaseDto.toString(result));
		return result.getItem();
	}


	public static void main(String[] args) {
		String token = "18b72d595a023badbb2867d7e4490a73";

		Long start = 1534048291389L;

		Long end = 1533863772000L;

		DefaultYZClient yzClient = new DefaultYZClient(new Token(token));

		Boolean hasNext = true;
		Long pageNo = 1L;
		Long pageSize = 10L;


		YouzanItemsOnsaleGetParams youzanItemsOnsaleGetParams = new YouzanItemsOnsaleGetParams();

		youzanItemsOnsaleGetParams.setPageSize(pageSize);


		//youzanItemsOnsaleGetParams.setUpdateTimeStart(start);
		//youzanItemsOnsaleGetParams.setUpdateTimeEnd(end);


		YouzanItemsOnsaleGet youzanItemsOnsaleGet = new YouzanItemsOnsaleGet();

		youzanItemsOnsaleGetParams.setPageNo(pageNo);
		youzanItemsOnsaleGet.setAPIParams(youzanItemsOnsaleGetParams);
		YouzanItemsOnsaleGetResult result = yzClient.invoke(youzanItemsOnsaleGet);
		System.out.println("success");

	}

    @Override
	public void getOrders(Date startTime, Date endTime){

		// 方法
		YouzanTradesSoldGet youzanTradesSoldGet = new YouzanTradesSoldGet();
		// 参数
		YouzanTradesSoldGetParams youzanTradesSoldGetParams = new YouzanTradesSoldGetParams();
		youzanTradesSoldGetParams.setType("ALL");
		// 买家已付款，等待发货
		youzanTradesSoldGetParams.setStatus("WAIT_SELLER_SEND_GOODS");
		youzanTradesSoldGetParams.setStartUpdate(startTime);
		youzanTradesSoldGetParams.setEndUpdate(endTime);
		youzanTradesSoldGetParams.setPageSize(100L);
		youzanTradesSoldGetParams.setUseHasNext(true);

		boolean hasNext = true;
		long pageNo = 1L;
		while (hasNext) {
			youzanTradesSoldGetParams.setPageNo(pageNo);
			youzanTradesSoldGet.setAPIParams(youzanTradesSoldGetParams);
			com.wangqin.globalshop.channel.dal.youzan.YouzanTradesSoldGetResult result = yzClient.invoke(youzanTradesSoldGet);

			// 设置循环
			pageNo++;
			hasNext = result.getHasNext();

			com.wangqin.globalshop.channel.dal.youzan.TradeDetailV2[] tradeList = result.getTrades();
			for (int i = tradeList.length - 1; i >= 0; i--) {
				this.syncOrder(tradeList[i]);
			}
		}
	}
    /**
     * job主动抓取的订单处理
     */
    @Override
    public void syncOrder() {
        // 交易状态更新的结束时间,值为当前时间
        Date endUpdate = new Date();
        // 交易状态更新的开始时间,值为当前时间的1个小时前，因为定时任务设置为半个小时,这样每个订单会有2次抓取机会
        Date startUpdate = DateUtil.getDateByCalculate(endUpdate, Calendar.HOUR_OF_DAY, -1);

        // 方法
        YouzanTradesSoldGet youzanTradesSoldGet = new YouzanTradesSoldGet();
        // 参数
        YouzanTradesSoldGetParams youzanTradesSoldGetParams = new YouzanTradesSoldGetParams();
        youzanTradesSoldGetParams.setType("ALL");
        // 买家已付款，等待发货
        youzanTradesSoldGetParams.setStatus("WAIT_SELLER_SEND_GOODS");
        youzanTradesSoldGetParams.setStartUpdate(startUpdate);
        youzanTradesSoldGetParams.setEndUpdate(endUpdate);
        youzanTradesSoldGetParams.setPageSize(100L);
        youzanTradesSoldGetParams.setUseHasNext(true);

        boolean hasNext = true;
        long pageNo = 1L;
        while (hasNext) {
            youzanTradesSoldGetParams.setPageNo(pageNo);
            youzanTradesSoldGet.setAPIParams(youzanTradesSoldGetParams);
            com.wangqin.globalshop.channel.dal.youzan.YouzanTradesSoldGetResult result = yzClient.invoke(youzanTradesSoldGet);

            // 设置循环
            pageNo++;
            hasNext = result.getHasNext();

            com.wangqin.globalshop.channel.dal.youzan.TradeDetailV2[] tradeList = result.getTrades();
            for (int i = tradeList.length - 1; i >= 0; i--) {
                this.syncOrder(tradeList[i]);
            }
        }


    }

    /**
     * 有赞推过来的订单
     *
     * @param data
     * @return
     */
    @Override
    public Object syncOrder(Object data) {
        YouzanMsgPushEntity entity = (YouzanMsgPushEntity) data;
        logger.error("推送消息：" + entity.toString());
        JSONObject res = new JSONObject();
        res.put("code", 0);
        res.put("msg", "success");
        /**
         * 判断是否为心跳检查消息 1.是则直接返回
         */
        if (entity.isTest()) {
            logger.error("进来了1");
            return res;
        }

        /**
         * 解析消息推送的模式 这步判断可以省略 0-商家自由消息推送 1-服务商消息推送 以服务商 举例 判断是否为服务商类型的消息 否则直接返回
         */
        if (entity.getMode() != mode) {
            logger.error("进来了2");
            return res;
        }
        /**
         * 判断消息是否合法 解析sign MD5 工具类开发者可以自行引入
         */
        String sign = MD5
                .digest(this.shopOauth.getAppKey() + entity.getMsg() + this.shopOauth.getAppsecretKey());
        sign = sign.replace("MD5:", "");
        logger.error("进来了：*" + sign);
        logger.error("进来了：+" + entity.getSign());
        if (!sign.equals(entity.getSign())) {
            logger.error("进来了3");
            return res;
        }

        /**
         * 对于msg 先进行URI解码
         */
        String msg = "";
        try {
            msg = URLDecoder.decode(entity.getMsg(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.error("smg：" + msg);
        /**
         * .......... 接下来是一些业务处理 判断当前消息的类型 比如交易
         *
         */
        if ("TRADE_ORDER_STATE".equals(entity.getType())) {
            YouzanMsgDetailEntity msgDetail = HaiJsonUtils.toBean(msg, new TypeReference<YouzanMsgDetailEntity>() {
            });
            if ("WAIT_SELLER_SEND_GOODS".equals(msgDetail.getStatus())) {
                // 方法
                YouzanTradeGet youzanTradeGet = new YouzanTradeGet();
                // 参数
                YouzanTradeGetParams youzanTradeGetParams = new YouzanTradeGetParams();
                // 买家已付款，等待发货
                youzanTradeGetParams.setTid(msgDetail.getTid());

                youzanTradeGet.setAPIParams(youzanTradeGetParams);

                com.wangqin.globalshop.channel.dal.youzan.YouzanTradeGetResult result = yzClient.invoke(youzanTradeGet);
                com.wangqin.globalshop.channel.dal.youzan.TradeDetailV2 TradeDetail = result.getTrade();

                this.syncOrder(TradeDetail);
            }
        }

        /**
         * 返回结果
         */
        return res;
    }

    /**
     * 内部订单和外部订单合并成mall_order
     *
     * @param TradeDetail
     */
    void syncOrder(TradeDetailV2 TradeDetail) {
        List<String> outOrderIdList = new ArrayList<>();

        // 如果有赞订单已存在，略过
        MallOrderDO p = new MallOrderDO();
        p.setChannelOrderNo(TradeDetail.getTid());
        if (outerOrderMapper.queryPoCount(p) > 0) {
            logger.error("有赞订单已经存在 tid" + TradeDetail.getTid());
            //生产时，直接return，测试时，进行插入
			//todo 测试代码
            return;
        }

        // 如果有赞订单还不存在，继续
        MallOrderDO outerOrder = new MallOrderDO();
        outerOrder.setCompanyNo(shopOauth.getCompanyNo());
        outerOrder.setChannelNo(shopOauth.getChannelNo());
        outerOrder.setChannelName(ChannelType.getChannelName(Integer.valueOf(shopOauth.getChannelNo())));
        outerOrder.setChannelType(shopOauth.getChannelNo());
        outerOrder.setShopCode(shopOauth.getShopCode());

        // 有赞平台为01,销售为0000
        outerOrder.setOrderNo(CodeGenUtil.getOrderNo()); // 系统自动生成
        outerOrder.setOrderTime(TradeDetail.getPayTime()); // 付款时间
        outerOrder.setStatus(OrderStatus.PAID.getCode()); // 状态：代发货

        outerOrder.setChannelOrderNo(TradeDetail.getTid());

        outerOrder.setIdCard(TradeDetail.getIdCardNumber()); // 身份证

        if (StringUtil.isNotBlank(TradeDetail.getPayType())) {
            outerOrder.setPayType(PayType.valueOf(TradeDetail.getPayType()).getCode()); // 支付方式
        }

        //邮费
        outerOrder.setFreight(Double.valueOf(TradeDetail.getPostFee()));

        BigDecimal totalPrice = BigDecimal.valueOf(TradeDetail.getTotalFee().doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP);

        outerOrder.setTotalAmount(TradeDetail.getTotalFee() == null ? 0d : totalPrice.doubleValue());
        outerOrder.setActualAmount(TradeDetail.getPayment() == null ? 0d : totalPrice.doubleValue());
        outerOrder.setMemo(TradeDetail.getBuyerMessage() + TradeDetail.getTradeMemo());

        outerOrder.setCreator("有赞推送订单");
        outerOrder.setGmtCreate(TradeDetail.getCreated()); // 创建时间
        outerOrder.setGmtModify(TradeDetail.getUpdateTime()); // 修改时间


        //补充必填信息
        outerOrder.setChannelCustomerNo("自定义类型，无买家昵称");
        outerOrder.setIsDel(false);
        outerOrder.setModifier("-1");


        outerOrderMapper.insertSelective(outerOrder); // 添加主订单

        outOrderIdList.add(outerOrder.getChannelOrderNo()); // 收集主订单ID

        com.wangqin.globalshop.channel.dal.youzan.YouzanTradeGetResult.TradeOrderV2[] tradeOrderArr = TradeDetail.getOrders();
        List<MallSubOrderDO> outerOrderDetails = new ArrayList<MallSubOrderDO>();

        for (int j = 0; j < tradeOrderArr.length; j++) {
            com.wangqin.globalshop.channel.dal.youzan.YouzanTradeGetResult.TradeOrderV2 tradeOrder = tradeOrderArr[j];
            MallSubOrderDO outerOrderDetail = new MallSubOrderDO();

            //关键是查找到对应的商品信息

			String skuCode = null;
			if(Long.valueOf(0).equals(tradeOrder.getSkuId()) || EasyUtil.isStringEmpty(tradeOrder.getOuterSkuId())){
				//代表这个商品是个单品，只有itemId,itemCode
				String itemCode = tradeOrder.getOuterItemId();//这个就是上传的单品Item
				ItemSkuDO skuSo = new ItemSkuDO();
				skuSo.setCompanyNo(shopOauth.getCompanyNo());
				skuSo.setItemCode(itemCode);
				List<ItemSkuDO> skuDOS = itemSkuService.queryPoList(skuSo);
				if(EasyUtil.isListEmpty(skuDOS)){
					throw new ErpCommonException("item_code error","商品未能找到，itemCode: "+itemCode);
				}
				skuCode = skuDOS.get(0).getSkuCode();
			}else {
				skuCode = tradeOrder.getOuterSkuId();
			}

			outerOrderDetail.setSkuCode(skuCode); // sku编码

            outerOrderDetail.setCompanyNo(outerOrder.getCompanyNo());
            outerOrderDetail.setOrderNo(outerOrder.getOrderNo()); // 主订单ID
            outerOrderDetail.setShopCode(outerOrder.getShopCode());

            outerOrderDetail.setSalePrice(Double.parseDouble(String.valueOf(tradeOrder.getPrice()))); // 商品单价
            outerOrderDetail.setQuantity(Integer.parseInt(String.valueOf(tradeOrder.getNum()))); // 购买数量
            outerOrderDetail.setGmtCreate(TradeDetail.getCreated()); // 创建时间
            outerOrderDetail.setGmtModify(TradeDetail.getUpdateTime()); // 修改时间

			outerOrderDetail.setOrderTime(outerOrder.getOrderTime());

            outerOrderDetail.setReceiver(TradeDetail.getReceiverName()); // 收货人
            outerOrderDetail.setReceiverState(TradeDetail.getReceiverState()); // 省
            outerOrderDetail.setReceiverCity(TradeDetail.getReceiverCity()); // 市
            outerOrderDetail.setReceiverDistrict(TradeDetail.getReceiverDistrict()); // 区
            outerOrderDetail.setReceiverAddress(TradeDetail.getReceiverAddress()); // 详细地址
            outerOrderDetail.setTelephone(TradeDetail.getReceiverMobile()); // 联系电话
            outerOrderDetail.setPostcode(TradeDetail.getReceiverZip()); // 邮编

            outerOrderDetail.setShopCode(shopOauth.getShopCode());
            outerOrderDetail.setOrderNo(outerOrder.getOrderNo());
            outerOrderDetail.setSubOrderNo(CodeGenUtil.getSubOrderNo());

            outerOrderDetail.setCustomerNo("无");
            outerOrderDetail.setIsDel(false);
            outerOrderDetail.setCreator("系统");
            outerOrderDetail.setModifier("系统");
            outerOrderDetail.setChannelOrderNo(outerOrder.getChannelOrderNo());

			outerOrderDetail.setStatus(OrderStatus.PAID.getCode());

			//有赞有子订单号
            outerOrderDetail.setChannelSubOrderNo(String.valueOf(tradeOrder.getOid()));

            outerOrderDetails.add(outerOrderDetail);


            //inventoryService.order(outerOrderDetails);

            mallSubOrderService.insertBatch(outerOrderDetails); // 添加子订单

            if (outOrderIdList.size() > 0) {
                // 把商品详情更新到主订单明细里面
                outerOrderDetailMapper.updateOuterOrderDetailByItemSku(outOrderIdList);
            }
        }
    }
}

