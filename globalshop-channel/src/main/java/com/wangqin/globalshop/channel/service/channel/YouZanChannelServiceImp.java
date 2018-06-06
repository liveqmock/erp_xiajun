package com.wangqin.globalshop.channel.service.channel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.wangqin.globalshop.biz1.app.constants.enums.*;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.dal.dataObjectVo.ItemSkuVo;
import com.wangqin.globalshop.channel.dal.dataObjectVo.ItemVo;
import com.wangqin.globalshop.channel.dal.youzan.*;
import com.wangqin.globalshop.channel.dal.youzan.YouzanTradeGet;
import com.wangqin.globalshop.channel.dal.youzan.YouzanTradesSoldGet;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.DimensionCodeUtil;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.gen.v3_0_0.api.*;
import com.youzan.open.sdk.gen.v3_0_0.model.*;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemCreateResult.ItemSkuOpenModel;
import com.youzan.open.sdk.model.ByteWrapper;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.ContentType;
import org.eclipse.jetty.util.StringUtil;
import org.eclipse.jetty.util.security.Credential.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.*;

@Channel(type = ChannelType.YouZan)
public class YouZanChannelServiceImp extends AbstractChannelService implements IChannelService {

	private DefaultYZClient yzClient = null;
	private static final int mode = 1; // 服务商

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

	public YouZanChannelServiceImp(ChannelAccountDO channelAccount) {
		super(channelAccount);

		auth();

		yzClient = new DefaultYZClient(new Token(this.channelAccount.getAccessToken()));// 免签名方式

		// 获取物流公司
		// this.getLogisticType();
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
        outerItem.setCompanyNo(channelAccount.getCompanyNo());
		outerItem.setShopCode(channelAccount.getShopCode());
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
				outerItemSku.setItemCode(String.valueOf(item.getId()));
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
		outerItem.setChannelNo(channelAccount.getChannelNo());
		ChannelListingItemDO outerItemDb = this.outerItemService.queryPo(outerItem);
		if (outerItemDb == null) {
			throw new ErpCommonException("更新outerItem 商品信息错误");
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
				ChannelListingItemSkuDO selOuterItemSku = this.outerItemSkuService.queryPo(outerItemSku);
				//外部信息
				outerItemSku.setChannelItemCode(outerItem.getChannelItemCode());
				outerItemSku.setChannelItemSkuCode(String.valueOf(sku.getSkuId()));
				//内部信息
				outerItemSku.setItemCode(String.valueOf(item.getId()));
				if (selOuterItemSku == null) {
					this.outerItemSkuService.insert(outerItemSku);
				} else {
					outerItemSku.setId(selOuterItemSku.getId());
					this.outerItemSkuService.updateByPrimaryKey(outerItemSku);
				}
			}
		}
		return adapterData;
	}
	/**
	 * 新增商品图片地址
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
		Double minSalePrice = itemSkus.get(0).getSalePrice();
		for (ItemSkuVo itemSku : itemSkus) {
			if (itemSku.getSalePrice() < minSalePrice) {
				minSalePrice = itemSku.getSalePrice();
			}
		}
		youzanItemCreateParams.setPrice((long) (minSalePrice * 100));
		return youzanItemCreateParams;
	}
	/**
	 * 更新图片地址
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
		Double minSalePrice = itemSkus.get(0).getSalePrice();
		for (ItemSkuDO itemSku : itemSkus) {
			if (itemSku.getSalePrice() < minSalePrice) {
				minSalePrice = itemSku.getSalePrice();
			}
		}
		youzanItemUpdateParams.setPrice((long) (minSalePrice * 100));
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
					skuStocksMap.put("price", itemSku.getSalePrice() * 100);
					Long skuQuantity = 0L;
					Long lockedVirtualInv =  itemSku.getInventoryDO().getLockedVirtualInv();
					if (itemSku.getInventoryDO().getVirtualInv() > 0) {
						skuQuantity = itemSku.getInventoryDO().getVirtualInv() - lockedVirtualInv;
					} else {
						skuQuantity = itemSku.getTotalAvailableInv() - lockedVirtualInv;
					}
					if (skuQuantity < 0){
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
					skuStocksMap.put("price", itemSku.getSalePrice() * 100);
					Long skuQuantity = 0L;
					Long lockedVirtualInv = itemSku.getInventoryDO().getLockedVirtualInv();
					if (itemSku.getInventoryDO().getVirtualInv() > 0) {
						skuQuantity = itemSku.getInventoryDO().getVirtualInv() - lockedVirtualInv;
					} else {
						skuQuantity = itemSku.getTotalAvailableInv() - lockedVirtualInv;
					}
					if (skuQuantity < 0)
						skuQuantity = 0L;
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
						if (itemSku == null) {
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
							skuStocksMap.put("price", itemSku.getSalePrice() * 100);
							Long skuQuantity = 0L;
							Long lockedVirtualInv = itemSku.getInventoryDO().getLockedVirtualInv();
							if (itemSku.getInventoryDO().getVirtualInv() > 0) {
								skuQuantity = itemSku.getInventoryDO().getVirtualInv() - lockedVirtualInv;
							} else {
								skuQuantity = itemSku.getTotalAvailableInv() - lockedVirtualInv;
							}
							if (skuQuantity < 0)
								skuQuantity = 0L;
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
	 * 发货反馈,
	 * @param orderList
	 * @param shippingOrder
	 */
	public void syncLogisticsOnlineConfirm(List<MallSubOrderDO> orderList, ShippingOrderDO shippingOrder) {
		logger.error("有赞发货");

		boolean hasFailed = false;
		for (MallSubOrderDO order : orderList) {
			try {
				// 获取第三方订单
				String tid = order.getChannelOrderNo();
				// 获取物流信息
				String logisticNo = shippingOrder.getShippingNo();
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
				// youzanLogisticsOnlineConfirmParams.setOids("1440273929715322794");

				YouzanLogisticsOnlineConfirm youzanLogisticsOnlineConfirm = new YouzanLogisticsOnlineConfirm();
				youzanLogisticsOnlineConfirm.setAPIParams(youzanLogisticsOnlineConfirmParams);
				YouzanLogisticsOnlineConfirmResult result = yzClient.invoke(youzanLogisticsOnlineConfirm);
				if (!hasFailed && !result.getIsSuccess()) {
					hasFailed = true;
					this.logger.error("同步发货给 有赞 返回结果异常：" + result.toString());
				}
			} catch (Exception e) {
				hasFailed = true;
				logger.error("有赞发货异常: ", e);
			}
		}

		if (!hasFailed) {
			try {
				shippingOrder.setSyncSendStatus(1);
				shippingOrderService.updateByPrimaryKey(shippingOrder);
			} catch (Exception e) {
				this.logger.error("同步发货给 有赞 返回结果异常");
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

		String urlresponse = DimensionCodeUtil.sendGet("https://open.youzan.com/oauth/token",
				"client_id=" + this.channelAccount.getAppKey() + "&client_secret=" + this.channelAccount.getAppSecret()
						+ "&grant_type=silent&kdt_id=" + this.channelAccount.getAppValue1());

		JSONObject jsonObject = JSONObject.fromObject(urlresponse);
		String youZanToken = jsonObject.getString("access_token");

		this.channelAccount.setAccessToken(youZanToken);

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
	 * job主动抓取的订单处理
	 */
	@Override
	public void syncOrder() {
		// 交易状态更新的结束时间,值为当前时间
		Date endUpdate = new Date();
		// 交易状态更新的开始时间,值为当前时间的1个小时前，因为定时任务设置为半个小时,这样每个订单会有2次抓取机会
		Date startUpdate = DateUtil.getDateByCalculate(endUpdate, Calendar.HOUR_OF_DAY, -1);


		//String startTime = "2018-05-02 10:33:00";
		//String endTime = "2018-05-03 19:33:00";

//		Date startUpdate = null;
//		Date endUpdate = null;
//		try {
//			 startUpdate = DateUtil.convertStr2Date(startTime,DateUtil.formateStr19);
//			 endUpdate = DateUtil.convertStr2Date(endTime,DateUtil.formateStr19);
//		}catch (ParseException e){
//			logger.info("");
//		}



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
				.digest(this.channelAccount.getAppKey() + entity.getMsg() + this.channelAccount.getAppSecret());
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
			return;
		}

		// 如果有赞订单还不存在，继续
		MallOrderDO outerOrder = new MallOrderDO();
		outerOrder.setCompanyNo(channelAccount.getCompanyNo());
		outerOrder.setChannelNo(channelAccount.getChannelNo());
		outerOrder.setChannelName(channelAccount.getChannelName());
		outerOrder.setChannelType(channelAccount.getType().toString());
		outerOrder.setShopCode(channelAccount.getShopCode());

		// 有赞平台为01,销售为0000
		outerOrder.setOrderNo("P" + String.format("%0" + 2 + "d", 1) + String.format("%0" + 4 + "d", 4) + "D"
				+ DateUtil.formatDate(TradeDetail.getPayTime(), DateUtil.DATE_PARTEN_YYMMDDHHMMSS)
				+ sequenceUtilService.gainORDSequence()); // 系统自动生成
		outerOrder.setOrderTime(TradeDetail.getPayTime()); // 付款时间
		outerOrder.setStatus(OrderStatus.INIT.getCode()); // 状态：新建

		outerOrder.setChannelOrderNo(TradeDetail.getTid());

		outerOrder.setIdCard(TradeDetail.getIdCardNumber()); // 身份证

		if (StringUtil.isNotBlank(TradeDetail.getPayType())) {
			outerOrder.setPayType(PayType.valueOf(TradeDetail.getPayType()).getCode()); // 支付方式
		}

		//邮费
		outerOrder.setFreight(Double.valueOf(TradeDetail.getPostFee()));
		outerOrder.setTotalAmount(Double.valueOf(TradeDetail.getTotalFee()));
		outerOrder.setActualAmount(Double.valueOf(TradeDetail.getPayment()));
		outerOrder.setMemo(TradeDetail.getBuyerMessage()+TradeDetail.getTradeMemo());

		outerOrder.setCreator("有赞推送订单");
		outerOrder.setGmtCreate(TradeDetail.getCreated()); // 创建时间
		outerOrder.setGmtModify(TradeDetail.getUpdateTime()); // 修改时间


		//补充必填信息
		outerOrder.setCustomerNo("无");
		outerOrder.setChannelCustomerNo("自定义类型，无买家昵称");
		outerOrder.setIsDel(false);
		outerOrder.setModifier("-1");


		outerOrderMapper.insertMallOrder(outerOrder); // 添加主订单

		outOrderIdList.add(outerOrder.getOrderNo()); // 收集主订单ID

		com.wangqin.globalshop.channel.dal.youzan.YouzanTradeGetResult.TradeOrderV2[] tradeOrderArr = TradeDetail.getOrders();
		List<MallSubOrderDO> outerOrderDetails = new ArrayList<MallSubOrderDO>();

		for (int j = 0; j < tradeOrderArr.length; j++) {
			com.wangqin.globalshop.channel.dal.youzan.YouzanTradeGetResult.TradeOrderV2 tradeOrder = tradeOrderArr[j];
			MallSubOrderDO outerOrderDetail = new MallSubOrderDO();
			outerOrderDetail.setCompanyNo(outerOrder.getCompanyNo());
			outerOrderDetail.setOrderNo(outerOrder.getOrderNo()); // 主订单ID
			outerOrderDetail.setShopCode(outerOrder.getShopCode());
			outerOrderDetail.setSkuCode(tradeOrder.getOuterSkuId()); // sku编码
			outerOrderDetail.setSalePrice(Double.parseDouble(String.valueOf(tradeOrder.getPrice()))); // 商品单价
			outerOrderDetail.setQuantity(Integer.parseInt(String.valueOf(tradeOrder.getNum()))); // 购买数量
			outerOrderDetail.setGmtCreate(TradeDetail.getCreated()); // 创建时间
			outerOrderDetail.setGmtModify(TradeDetail.getUpdateTime()); // 修改时间

			outerOrderDetail.setReceiver(TradeDetail.getReceiverName()); // 收货人
			outerOrderDetail.setReceiverState(TradeDetail.getReceiverState()); // 省
			outerOrderDetail.setReceiverCity(TradeDetail.getReceiverCity()); // 市
			outerOrderDetail.setReceiverDistrict(TradeDetail.getReceiverDistrict()); // 区
			outerOrderDetail.setReceiverAddress(TradeDetail.getReceiverAddress()); // 详细地址
			outerOrderDetail.setTelephone(TradeDetail.getReceiverMobile()); // 联系电话
			outerOrderDetail.setPostcode(TradeDetail.getReceiverZip()); // 邮编

			outerOrderDetail.setShopCode(channelAccount.getShopCode());
			outerOrderDetail.setOrderNo(outerOrder.getOrderNo());
			outerOrderDetail.setSubOrderNo("O" + outerOrder.getOrderNo().substring(1) + String.format("%0"+4+"d", outerOrderDetails.size()+1));

			outerOrderDetail.setCustomerNo("无");
			outerOrderDetail.setIsDel(false);
			outerOrderDetail.setCreator("系统");
			outerOrderDetail.setModifier("系统");
			outerOrderDetail.setChannelOrderNo(outerOrder.getChannelOrderNo());

			outerOrderDetails.add(outerOrderDetail);

			// 如果有虚拟库存就扣减虚拟库存
			ItemSkuDO tjItemSku = new ItemSkuDO();
			//订单关联才从商品outerId
			tjItemSku.setSkuCode(tradeOrder.getOuterSkuId());
			ItemSkuDO itemSku = itemSkuService.queryPo(tjItemSku);
			if (itemSku != null) {
				InventoryDO inventory = inventoryService.queryInventoryByCode(itemSku.getItemCode(), itemSku.getSkuCode());
				if (inventory.getVirtualInv() > 0) {
					Long virtualInv = inventory.getVirtualInv() - outerOrderDetail.getQuantity();
					virtualInv = virtualInv > 0 ? virtualInv : 0;
					// 如果虚拟库存小于等于可售库存，虚拟库存清零
					virtualInv = virtualInv > inventory.getInv() ? virtualInv : 0;

					// 如果虚拟占用库存大于零，有赞下单不应该减少虚拟预扣
					/*
					 * if(inventory.getLockedVirtualInv() > 0) { int lockedVirtualInv =
					 * inventory.getLockedVirtualInv() - outerOrderDetail.getQuantity();
					 * lockedVirtualInv = lockedVirtualInv>0 ? lockedVirtualInv : 0;
					 * inventory.setLockedVirtualInv(lockedVirtualInv); }
					 */
					inventory.setVirtualInv(virtualInv);
					inventory.setGmtModify(new Date());
					inventoryService.updateByPrimaryKey(inventory);
				}
			}
		}
		mallSubOrderService.insertBatch(outerOrderDetails); // 添加子订单

		if (outOrderIdList.size() > 0) {
			// 把商品详情更新到主订单明细里面
			outerOrderDetailMapper.updateOuterOrderDetailByItemSku(outOrderIdList);
			// 生成子订单并配货
			outerOrderService.reviewByIdList(outOrderIdList);



		}
	}
}
