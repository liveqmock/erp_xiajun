package com.wangqin.globalshop.channel.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.JdItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.enums.ItemStatus;
import com.wangqin.globalshop.biz1.app.enums.PlatformType;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.dal.youzan.YzProperties;
import com.wangqin.globalshop.channel.service.intramirror.IMProduct;
import com.wangqin.globalshop.channel.service.intramirror.IMProductResponse;
import com.wangqin.globalshop.channel.service.intramirror.IMSku;
import com.wangqin.globalshop.channel.service.item.IItemSkuService;
import com.wangqin.globalshop.channel.service.jingdong.SendStatus;
import com.wangqin.globalshop.channelapi.dal.*;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemGetResult;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemsOnsaleGetResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

/**
 * Create by 777 on 2018/8/27
 */

@Service
public class IntraMirrorService {

	private static Logger logger = LoggerFactory.getLogger("IntraMirrorService");

	@Value("#{sys.intra_mirror_url}")
	public String INTRA_MIRROR_URL;




	public static final String oauth_token_path = "/auth/token"; //get
//	buyer_id String 唯一标识 Y
//	secret_key String password Y
//	version String 调用Api版本，现在只有1.0 Y


	public static final String get_product_path = "/product/getProduct"; //get


	public static final String create_order_path = "/order/offline/create"; //post



	public static final String logistics_path = "";


	@Autowired
	private JdItemDOMapperExt jdItemDOMapperExt;

	@Autowired
	private IItemSkuService itemSkuService;


	public void getAllItems(JdShopOauthDO shopOauth) {
		Boolean hasNext = true;
		Long pageNo = 1L;
		Long pageSize = 100L;

		Map<String,String> param = new HashMap<>();
		param.put("bannerPosId","101");   //bannerPosId Integer 渠道编码，由IntraMirror商务提供。例如：101
		param.put("limit",pageSize+"");   //limit Integer 分页的记录数
		param.put("toCountryId","3");     //toCountryId Integer 订单到货目的地国家ID。发往香港使用3。
		param.put("currency","CNY");      //currency String 货币代码。请使用"CNY"获得人民币值
		param.put("access_token",shopOauth.getAccessToken());      //currency String 货币代码。请使用"CNY"获得人民币值


		while(hasNext){
			param.put("page",pageNo+"");      //page Integer 分页码

			String resultString = HttpClientUtil.get(INTRA_MIRROR_URL+get_product_path, param);

			logger.info("improduct get product: "+ resultString);

			//IMProductResponse productResponse = BaseDto.fromJson(resultString,IMProductResponse.class);

			IMProductResponse productResponse = JSON.parseObject(resultString, IMProductResponse.class);

			List<IMProduct> productList = productResponse.getProductList();

			if(productList == null || productList.size() < pageSize){
				hasNext = false;
			}else{
				hasNext = true;
				pageNo++;
			}

			// 处理商品转换问题
			if(!EasyUtil.isListEmpty(productList)){
				for (IMProduct product : productList) {
					saveItemJson(shopOauth,product);
				}

			}

		}
	}

	private void saveItemJson(JdShopOauthDO shopOauth, IMProduct product){
		JdItemDO jdItemSo = new JdItemDO();
		jdItemSo.setChannelItemCode(product.getProduct_id());
		jdItemSo.setShopCode(shopOauth.getShopCode());
		JdItemDO oldJdItem = jdItemDOMapperExt.searchJdItem(jdItemSo);
		if(oldJdItem == null){
			JdItemDO newJdItemDO = new JdItemDO();
			newJdItemDO.setCreator("-1");
			newJdItemDO.setModifier("-1");
			newJdItemDO.setChannelNo(shopOauth.getChannelNo());
			newJdItemDO.setCompanyNo(shopOauth.getCompanyNo());
			newJdItemDO.setShopCode(shopOauth.getShopCode());
			newJdItemDO.setSendStatus(SendStatus.REQUEST);
			newJdItemDO.setChannelItemCode(product.getProduct_id());
			newJdItemDO.setItemJson(BaseDto.toString(product));
			newJdItemDO.setItemModifyTime(new Date());
			newJdItemDO.setIsDel(false);
			jdItemDOMapperExt.insert(newJdItemDO);
		}else {
			oldJdItem.setItemModifyTime(new Date());
			oldJdItem.setItemJson(BaseDto.toString(product));
			oldJdItem.setSendStatus(SendStatus.REQUEST);
			jdItemDOMapperExt.updateByPrimaryKeySelective(oldJdItem);
		}
	}



	public GlobalShopItemVo convertYZItem(JdShopOauthDO shopOauth, JdItemDO jdItem) {

		IMProduct product = JSON.parseObject(jdItem.getItemJson(), IMProduct.class);

		if(product == null ){
			throw new ErpCommonException(" IMItem empty","tbspuid: "+jdItem.getChannelItemCode());
		}


		//第一步：channelItem处理
		ChannelListingItemVo outerItemVo = new ChannelListingItemVo();
		outerItemVo.setChannelNo(String.valueOf(ChannelType.IntraMirror.getValue()));
		outerItemVo.setCompanyNo(shopOauth.getCompanyNo());
		outerItemVo.setShopCode(shopOauth.getShopCode());


		outerItemVo.setChannelItemCode(product.getProduct_id());
		outerItemVo.setItemCode(product.getProduct_id());//youzanItemDetail这个数据无item——no
		outerItemVo.setStatus(ItemStatus.LISTING.getCode());

		//补充必填信息
		outerItemVo.setIsDel(false);
		outerItemVo.setGmtCreate(new Date());
		outerItemVo.setGmtModify(new Date());
		outerItemVo.setCreator("-1");
		outerItemVo.setModifier("-1");

		//第二步：item处理
		ItemVo itemVo = new ItemVo();
		itemVo.setItemCode(product.getProduct_id());
		itemVo.setItemName(product.getProduct_name());
		itemVo.setCompanyNo(shopOauth.getCompanyNo());

		itemVo.setMainPic(getMainPic(product.getCover_img()));//本身就是list
		itemVo.setDetail(product.getProduct_description());
		itemVo.setSaleOnYouzan(1);
		itemVo.setStatus(ItemStatus.LISTING.getCode());

		List<ChannelListingItemSkuVo> outSkuList = new ArrayList<>();
		List<ItemSkuVo> itemSkuVoList = new ArrayList<>();

		List<IMSku> skus = product.getSku();

		if(skus == null || skus.size() < 1){
             throw new ErpCommonException("sku size 0","错误数据，无sku不下发");

		}else {
			//多规格：含一个及一个以上sku
			for(IMSku sku : skus){

				//第三步：外部SKU
				ChannelListingItemSkuVo outerItemSku = new ChannelListingItemSkuVo();
				outerItemSku.setPlatformType(PlatformType.IntraMirror.getCode());
				//外部信息
				outerItemSku.setChannelItemCode(outerItemVo.getChannelItemCode());
				outerItemSku.setChannelItemSkuCode(sku.getSkuid());
				//内部信息
				outerItemSku.setItemCode(outerItemVo.getItemCode());
				outerItemSku.setSkuCode(sku.getSkuid());

				//补充必填信息
				outerItemSku.setIsDel(false);
				outerItemSku.setGmtCreate(new Date());
				outerItemSku.setGmtModify(new Date());
				outerItemSku.setCreator("-1");
				outerItemSku.setModifier("-1");
				outSkuList.add(outerItemSku);

				//第四步：内部SKU
				ItemSkuVo itemSkuVo = new ItemSkuVo();
				itemSkuVo.setItemCode(product.getProduct_id());
				itemSkuVo.setSkuCode(sku.getSkuid());
				itemSkuVo.setSalePrice(sku.getIm_price() == null ? 0 : sku.getIm_price().doubleValue());
				itemSkuVo.setItemName(product.getProduct_name());

				itemSkuVo.setUpc(product.getProduct_id()+sku.getSkuid());//upc用spuid+skuid

				Map<String,ItemSkuScaleDO> scaleMap = new HashMap<>();

                //颜色
				ItemSkuScaleDO itemSkuColorScale = new ItemSkuScaleDO();
				itemSkuColorScale.setScaleCode(product.getColorcode());
				itemSkuColorScale.setScaleName("颜色");
				itemSkuColorScale.setScaleValue(product.getColor_description());
				scaleMap.put("颜色",itemSkuColorScale);

				//尺寸
				ItemSkuScaleDO itemSkuSizeScale = new ItemSkuScaleDO();
				itemSkuSizeScale.setScaleCode("size");
				itemSkuSizeScale.setScaleName("尺寸");
				itemSkuSizeScale.setScaleValue(sku.getSize());
				scaleMap.put("尺寸",itemSkuSizeScale);

				itemSkuVo.setScaleMap(scaleMap);

				itemSkuVo.setVirtualInv(sku.getStock().longValue());//库存

				itemSkuVo.setSkuPic(getSkuPic(product.getCover_img()));

				itemSkuVoList.add(itemSkuVo);
			}
		}
		outerItemVo.setChannelListingItemSkuVos(outSkuList);
		itemVo.setItemSkus(itemSkuVoList);

		GlobalShopItemVo globalShopItemVo = new GlobalShopItemVo();
		globalShopItemVo.setItemVo(itemVo);
		globalShopItemVo.setChannelListingItemVo(outerItemVo);

		return globalShopItemVo;
	}

	/**
	 * 只取第一个图片给子图
	 * @param
	 * @return
	 */
	private String getSkuPic(List<String> coverImgList){

		String coverImg = coverImgList.get(0);
		//coverImg = coverImg.substring(1,coverImg.length()-1);

		List<String> picList = JSON.parseObject(coverImg, ArrayList.class);
		List<String> skuList = new ArrayList<>();
		if(!EasyUtil.isListEmpty(picList)){
			skuList.add(picList.get(0));
		}
		return BaseDto.toString(skuList);

	}

	private String getMainPic(List<String> coverImgList){
		String coverImg = coverImgList.get(0);
		//coverImg = coverImg.substring(1,coverImg.length()-1);

		List<String> picList = JSON.parseObject(coverImg, ArrayList.class);
		List<String> skuList = new ArrayList<>();
		if(!EasyUtil.isListEmpty(picList)){
			for(String pic : picList){
				skuList.add(pic);
			}
		}
		return BaseDto.toString(skuList);
	}


}
