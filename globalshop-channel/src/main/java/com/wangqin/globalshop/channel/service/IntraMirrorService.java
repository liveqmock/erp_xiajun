package com.wangqin.globalshop.channel.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelListingItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelListingItemSkuDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.JdItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.JdOrderDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.enums.ItemStatus;
import com.wangqin.globalshop.biz1.app.enums.PlatformType;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.dal.youzan.YzProperties;
import com.wangqin.globalshop.channel.service.intramirror.*;
import com.wangqin.globalshop.channel.service.item.IItemSkuService;
import com.wangqin.globalshop.channel.service.jingdong.SendStatus;
import com.wangqin.globalshop.channelapi.dal.*;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.wangqin.globalshop.common.utils.HttpPostUtil;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemGetResult;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemsOnsaleGetResult;
import net.sf.json.JSONObject;
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

	public static final String get_order_detail = "/order/order/detail";//get



	public static final String logistics_path = "";


	@Autowired
	private JdItemDOMapperExt jdItemDOMapperExt;


	@Autowired
	private ChannelListingItemDOMapperExt outItemMapper;

	@Autowired
	private ChannelListingItemSkuDOMapperExt outItemSkuMapper;

	@Autowired
	private JdOrderDOMapperExt jdOrderDOMapperExt;



	@Autowired
	private IItemSkuService itemSkuService;


	static Map<String, String> intraMirrorCatagoryMap = new HashMap<String, String>();

	// 暂时硬编码
	// (itraMirrorCode, erpCode)
	static {
		intraMirrorCatagoryMap.put("1518","0602006");
		intraMirrorCatagoryMap.put("1521","0602006");
		intraMirrorCatagoryMap.put("1522","0602005");
		intraMirrorCatagoryMap.put("1523","0602004");
		intraMirrorCatagoryMap.put("1524","0602002");
		intraMirrorCatagoryMap.put("1525","0602003");
		intraMirrorCatagoryMap.put("1526","0602005");
		intraMirrorCatagoryMap.put("1527","5351050691");
		intraMirrorCatagoryMap.put("1528","5351097688");
		intraMirrorCatagoryMap.put("1530","0602005");
		intraMirrorCatagoryMap.put("1531","0602006");
		intraMirrorCatagoryMap.put("1532","0604007");
		intraMirrorCatagoryMap.put("1543","0604002");
		intraMirrorCatagoryMap.put("1545","5351360099");
		intraMirrorCatagoryMap.put("1546","0702003");
		intraMirrorCatagoryMap.put("1547","0701004");
		intraMirrorCatagoryMap.put("1549","5351472638");
		intraMirrorCatagoryMap.put("1551","0702002");
		intraMirrorCatagoryMap.put("1554","0702004");
		intraMirrorCatagoryMap.put("1555","5177413271");
		intraMirrorCatagoryMap.put("1557","0902001");
		intraMirrorCatagoryMap.put("1558","5351725799");
		intraMirrorCatagoryMap.put("1559","5351765094");
		intraMirrorCatagoryMap.put("1560","0703006");
		intraMirrorCatagoryMap.put("1563","5177435366");
		intraMirrorCatagoryMap.put("1565","0602006");
		intraMirrorCatagoryMap.put("1571","0601006");
		intraMirrorCatagoryMap.put("1574","0601005");
		intraMirrorCatagoryMap.put("1575","0601001");
		intraMirrorCatagoryMap.put("1576","0601006");
		intraMirrorCatagoryMap.put("1578","1531125990");
		intraMirrorCatagoryMap.put("1579","0601006");
		intraMirrorCatagoryMap.put("1580","0601005");
		intraMirrorCatagoryMap.put("1582","0601006");
		intraMirrorCatagoryMap.put("1583","0601005");
		intraMirrorCatagoryMap.put("1586","0604007");
		intraMirrorCatagoryMap.put("1594","0604006");
		intraMirrorCatagoryMap.put("1595","0604002");
		intraMirrorCatagoryMap.put("1597","0801005");
		intraMirrorCatagoryMap.put("1599","0701003");
		intraMirrorCatagoryMap.put("1600","0701004");
		intraMirrorCatagoryMap.put("1601","0703006");
		intraMirrorCatagoryMap.put("1602","0703001");
		intraMirrorCatagoryMap.put("1604","0701002");
		intraMirrorCatagoryMap.put("1605","0701001");
		intraMirrorCatagoryMap.put("1609","5177413271");
		intraMirrorCatagoryMap.put("1610","0902001");
		intraMirrorCatagoryMap.put("1611","5351725799");
		intraMirrorCatagoryMap.put("1613","5351765094");
		intraMirrorCatagoryMap.put("1617","0703006");
		intraMirrorCatagoryMap.put("1618","5177435366");
		intraMirrorCatagoryMap.put("1620","0701006");
		intraMirrorCatagoryMap.put("1625","0903007");
		intraMirrorCatagoryMap.put("1629","0703006");
		intraMirrorCatagoryMap.put("1632","0101001");
		intraMirrorCatagoryMap.put("1634","0903007");
		intraMirrorCatagoryMap.put("1637","0601006");
		intraMirrorCatagoryMap.put("1640","0101001");
		intraMirrorCatagoryMap.put("1645","0601006");
		intraMirrorCatagoryMap.put("1646","0604001");
		intraMirrorCatagoryMap.put("1647","0604004");
		intraMirrorCatagoryMap.put("1648","0604002");
		intraMirrorCatagoryMap.put("1649","5351472638");
		intraMirrorCatagoryMap.put("1650","0903007");
		intraMirrorCatagoryMap.put("1651","0701006");
		intraMirrorCatagoryMap.put("1652","0901001");
		intraMirrorCatagoryMap.put("1653","0604004");
		intraMirrorCatagoryMap.put("1654","0604004");
		intraMirrorCatagoryMap.put("1655","0703001");
		intraMirrorCatagoryMap.put("1656","0903007");
		intraMirrorCatagoryMap.put("1657","0901001");
		intraMirrorCatagoryMap.put("1769","0403005");
		intraMirrorCatagoryMap.put("1770","");
		intraMirrorCatagoryMap.put("1771","0403005");
		intraMirrorCatagoryMap.put("1772","0403005");
		intraMirrorCatagoryMap.put("1773","0403005");
		intraMirrorCatagoryMap.put("1774","0403005");
		intraMirrorCatagoryMap.put("1775","");
		intraMirrorCatagoryMap.put("1776","5355307318");
		intraMirrorCatagoryMap.put("1777","5355323226");
		intraMirrorCatagoryMap.put("1778","0403005");
		intraMirrorCatagoryMap.put("1779","0403005");
		intraMirrorCatagoryMap.put("1780","0403004");
		intraMirrorCatagoryMap.put("1781","0403004");
		intraMirrorCatagoryMap.put("1782","0403004");
		intraMirrorCatagoryMap.put("1783","0403004");
		intraMirrorCatagoryMap.put("1784","0403004");
		intraMirrorCatagoryMap.put("1785","0403005");
		intraMirrorCatagoryMap.put("1786","0403005");
		intraMirrorCatagoryMap.put("1787","0403005");
		intraMirrorCatagoryMap.put("1788","0403005");
		intraMirrorCatagoryMap.put("1789","0403005");
		intraMirrorCatagoryMap.put("1790","0403005");
		intraMirrorCatagoryMap.put("1791","0403005");
		intraMirrorCatagoryMap.put("1792","0403005");
		intraMirrorCatagoryMap.put("1793","1531125990");
		intraMirrorCatagoryMap.put("1794","0601006");
		intraMirrorCatagoryMap.put("1795","0601006");
		intraMirrorCatagoryMap.put("1796","0601006");
		intraMirrorCatagoryMap.put("1797","0601001");
		intraMirrorCatagoryMap.put("1798","0601005");
		intraMirrorCatagoryMap.put("1799","0601005");
		intraMirrorCatagoryMap.put("1800","0601005");
		intraMirrorCatagoryMap.put("1801","0601006");
		intraMirrorCatagoryMap.put("1802","0601006");
		intraMirrorCatagoryMap.put("1803","0604003");
		intraMirrorCatagoryMap.put("1804","0604007");
		intraMirrorCatagoryMap.put("1805","0604002");
		intraMirrorCatagoryMap.put("1806","5351360099");
		intraMirrorCatagoryMap.put("1807","0604007");
		intraMirrorCatagoryMap.put("1808","0701006");
		intraMirrorCatagoryMap.put("1809","5351725799");
		intraMirrorCatagoryMap.put("1810","5351765094");
		intraMirrorCatagoryMap.put("1811","5177435366");
		intraMirrorCatagoryMap.put("1812","0603003");
		intraMirrorCatagoryMap.put("1813","0903007");
		intraMirrorCatagoryMap.put("1814","5177413271");
		intraMirrorCatagoryMap.put("1815","0903007");
		intraMirrorCatagoryMap.put("1816","0902001");
		intraMirrorCatagoryMap.put("1817","0903007");
		intraMirrorCatagoryMap.put("1818","0602002");
		intraMirrorCatagoryMap.put("1819","0602003");
		intraMirrorCatagoryMap.put("1820","5351097688");
		intraMirrorCatagoryMap.put("1821","0602006");
		intraMirrorCatagoryMap.put("1822","0602004");
		intraMirrorCatagoryMap.put("1823","0602006");
		intraMirrorCatagoryMap.put("1824","0602005");
		intraMirrorCatagoryMap.put("1825","0602005");
		intraMirrorCatagoryMap.put("1826","0602005");
		intraMirrorCatagoryMap.put("1827","0602006");
		intraMirrorCatagoryMap.put("1828","0602006");
		intraMirrorCatagoryMap.put("1829","0604003");
		intraMirrorCatagoryMap.put("1830","0602006");
		intraMirrorCatagoryMap.put("1831","0604002");
		intraMirrorCatagoryMap.put("1832","5351360099");
		intraMirrorCatagoryMap.put("1833","0604007");
		intraMirrorCatagoryMap.put("1834","0702006");
		intraMirrorCatagoryMap.put("1835","5351725799");
		intraMirrorCatagoryMap.put("1836","5351765094");
		intraMirrorCatagoryMap.put("1837","5177435366");
		intraMirrorCatagoryMap.put("1838","0603003");
		intraMirrorCatagoryMap.put("1839","0903007");
		intraMirrorCatagoryMap.put("1840","5177413271");
		intraMirrorCatagoryMap.put("1841","0902001");
		intraMirrorCatagoryMap.put("1842","0903007");
		intraMirrorCatagoryMap.put("1843","0603003");
		intraMirrorCatagoryMap.put("1844","0603003");
	}


	public void getAllItems(JdShopOauthDO shopOauth) {
		Boolean hasNext = true;
		Long pageNo = 0L;//页码从0开始
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

		IMCatogery imCatogery = new IMCatogery();
		imCatogery.setCategory_l1(product.getCategory_l1());
		imCatogery.setCategory_l1_id(product.getCategory_l1_id());
		imCatogery.setCategory_l2(product.getCategory_l2());
		imCatogery.setCategory_l2_id(product.getCategory_l2_id());
		imCatogery.setCategory_l3(product.getCategory_l3());
		imCatogery.setCategory_l3_id(product.getCategory_l3_id());
		outerItemVo.setCategoryJson(BaseDto.toString(imCatogery));

		//第二步：item处理
		ItemVo itemVo = new ItemVo();
		itemVo.setItemCode(product.getProduct_id());
		itemVo.setItemName(product.getProduct_name());
		itemVo.setCompanyNo(shopOauth.getCompanyNo());

		itemVo.setMainPic(getMainPic(product.getCover_img()));//本身就是list
		itemVo.setDetail(product.getProduct_description());

		itemVo.setStatus(ItemStatus.LISTING.getCode());

		itemVo.setCategoryCode(intraMirrorCatagoryMap.get(product.getCategory_l3_id()));

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
				outerItemSku.setShopProductSkuId(Integer.valueOf(sku.getShop_product_sku_id()));

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


	/**
	 * 主动去intramirror下单,只支持订单明细同一个收件人的订单下单操作
	 * @param shopOauth
	 */
	public void createOrder(JdShopOauthDO shopOauth, MallOrderDO mallOrder, List<MallSubOrderDO> subOrderDOList){

		Long contact_phone = 13971282863L;
		String contact_name = "EQunDaShu";
		Long user_id = 91L;

		Double subtotalPrice = 0d;

		List<OrderLine> order_line = new ArrayList<>();
  		for(MallSubOrderDO subOrder : subOrderDOList){

  			//第一步：检查是否存在这个外部商品
			ChannelListingItemSkuDO outSkuSo = new ChannelListingItemSkuDO();
			outSkuSo.setCompanyNo(shopOauth.getCompanyNo());
			outSkuSo.setItemCode(subOrder.getItemCode());
			outSkuSo.setSkuCode(subOrder.getSkuCode());

			ChannelListingItemSkuDO relateSku = outItemSkuMapper.queryPo(outSkuSo);

			if(relateSku == null){
				//非intraMirror商品，不下单
				return;
				//throw new ErpCommonException("sku emptity","在channel_listing_item_sku无对应商品，item_code："+subOrder.getItemCode() + " sku_code:" + subOrder.getSkuCode());
			}

			//第二步：校验库存是否足够
			dealInv(shopOauth, relateSku.getChannelItemCode(),relateSku.getChannelItemSkuCode(),subOrder.getQuantity());

			Integer quantity = subOrder.getQuantity();
			while (quantity > 0){
				OrderLine orderLine = new OrderLine();
				orderLine.setShop_product_sku_id(Long.valueOf(relateSku.getShopProductSkuId()));
				orderLine.setProduct_id(Long.valueOf(relateSku.getChannelItemCode()));
				// todo 类目未对应
				orderLine.setCategory_id(Long.valueOf(getCategoryId(shopOauth,relateSku)));//
				orderLine.setQuantity(1);
				orderLine.setSale_price(BigDecimal.valueOf(subOrder.getSalePrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
				order_line.add(orderLine);
				quantity--;
			}
			subtotalPrice += subOrder.getQuantity()*subOrder.getSalePrice();
		}

		MallSubOrderDO mallSubOrderDO = subOrderDOList.get(0);

		CustomerInfo customerInfo = new CustomerInfo();

		//收件信息
  		customerInfo.setGeography_id(2L);//Long 国际区域id（固定：2 - 港澳地区；） Y
		customerInfo.setAddress_country_id(3L);// Long 国家id（固定：3 - 中国香港；） Y
		customerInfo.setConsignee_country("中国香港");
		customerInfo.setConsignee_name(mallSubOrderDO.getReceiver());
		customerInfo.setConsignee_mobile(mallSubOrderDO.getTelephone());
		customerInfo.setConsignee_province(mallSubOrderDO.getReceiverState());
		customerInfo.setConsignee_area(mallSubOrderDO.getReceiverDistrict());
		customerInfo.setConsignee_address(mallSubOrderDO.getReceiverAddress());
		customerInfo.setConsignee_city(mallSubOrderDO.getReceiverCity());
		customerInfo.setConsignee_mobile(mallSubOrderDO.getTelephone());

		//寄件信息,待确定
		customerInfo.setConsigner_mobile(contact_phone+"");
		customerInfo.setConsigner_name(contact_name);


		CreateOrderRequest request = new CreateOrderRequest();
		request.setSubtotalPrice(BigDecimal.valueOf(subtotalPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
		request.setPayWay(3);
		request.setUser_id(user_id);
		request.setContact_name(contact_name);
		request.setContact_phone(contact_phone);
		request.setCustomer(customerInfo);
		request.setOrder_line(order_line);
		request.setC_user_id(100L);

		String requestJsonStr = JSON.toJSONString(request);
		String url = INTRA_MIRROR_URL + create_order_path + "?access_token=" + shopOauth.getAccessToken();

		logger.info("request url: "+url);
		logger.info("request json Str: "+requestJsonStr);

		String resultStr = null;
		try {
			resultStr = HttpClientUtil
					.post(url, requestJsonStr);
		} catch (Exception e) {
			logger.error("", e);
			throw new ErpCommonException("remote url error", "远程下单失败，网络错误");
		}
		logger.info("intra mirror create order response: " + resultStr);
		if (EasyUtil.isStringEmpty(resultStr)) {
			throw new ErpCommonException("remote url error", "远程下单失败，网络错误");
		}
		CreateOrderResponse createOrderResponse = JSON.parseObject(resultStr, CreateOrderResponse.class);
		if (createOrderResponse.getStatus() != null && createOrderResponse.getStatus().equals(1)) {
			//成功
			mallOrder.setChannelNo(createOrderResponse.getOrder_id()+"");

			//创建任务，获取订单详情
			saveOrder4Get(shopOauth,createOrderResponse.getOrder_id()+"");

		} else {
			if(!EasyUtil.isStringEmpty(createOrderResponse.getLacking_products())){
				throw new ErpCommonException("remote url error", "远程下单失败,商品缺货"+createOrderResponse.getLacking_products());
			}else {
				throw new ErpCommonException("remote url error", "远程下单失败:" + resultStr);
			}
		}



	}


	private void saveOrder4Get(JdShopOauthDO shopOauth, String orderId){

		JdOrderDO jdOrderSo = new JdOrderDO();
		jdOrderSo.setShopCode(shopOauth.getShopCode());
		jdOrderSo.setChannelOrderNo(orderId);
		jdOrderSo.setChannelNo(shopOauth.getChannelNo());
		JdOrderDO order = jdOrderDOMapperExt.searchJdOrder(jdOrderSo);
		if(order == null){
			order = new JdOrderDO();
			order.setSendStatus(SendStatus.GET);
			order.setShopCode(shopOauth.getShopCode());
			order.setChannelOrderNo(orderId);
			order.setChannelNo(shopOauth.getChannelNo());
			order.setOrderModifyTime(new Date());
            order.setCompanyNo(shopOauth.getCompanyNo());
			order.init4NoLogin();
			jdOrderDOMapperExt.insertSelective(order);
		}else {
			order.setChannelOrderNo(orderId);
			order.setOrderModifyTime(new Date());
			order.setSendStatus(SendStatus.GET);
			jdOrderDOMapperExt.updateByPrimaryKey(order);
		}
	}


	/**
	 * 默认返回三级ID
	 * @param relateSku
	 * @return
	 */
	private String getCategoryId(JdShopOauthDO shopOauth, ChannelListingItemSkuDO relateSku){
		String categoryId = "0";
		ChannelListingItemDO itemSo = new ChannelListingItemDO();
		itemSo.setChannelItemCode(relateSku.getChannelItemCode());
		itemSo.setShopCode(shopOauth.getShopCode());
		ChannelListingItemDO channelListingItemDO = outItemMapper.queryPo(itemSo);
		if(channelListingItemDO == null || channelListingItemDO.getCategoryJson() == null){
            return categoryId;
		}
		IMCatogery imCatogery = BaseDto.fromJson(channelListingItemDO.getCategoryJson(),IMCatogery.class);
		categoryId = imCatogery.getCategory_l3_id()+"";
		return categoryId;
	}


	public void dealInv(JdShopOauthDO shopOauth, String productId, String skuid, Integer needStock) {
		Long pageNo = 0L; //从页码0开始
		Long pageSize = 1L;
		Map<String,String> param = new HashMap<>();
		param.put("bannerPosId","101");   //bannerPosId Integer 渠道编码，由IntraMirror商务提供。例如：101
		param.put("page",pageNo+"");      //page Integer 分页码
		param.put("limit",pageSize+"");   //limit Integer 分页的记录数
		param.put("toCountryId","3");     //toCountryId Integer 订单到货目的地国家ID。发往香港使用3。
		param.put("currency","CNY");      //currency String 货币代码。请使用"CNY"获得人民币值
		param.put("access_token",shopOauth.getAccessToken());      //currency String 货币代码。请使用"CNY"获得人民币值
		param.put("product_id",productId);
		String resultString = HttpClientUtil.get(INTRA_MIRROR_URL+get_product_path, param);
		logger.info("improduct get product: "+ resultString);
		IMProductResponse productResponse = JSON.parseObject(resultString, IMProductResponse.class);
		List<IMProduct> productList = productResponse.getProductList();
		if(productList== null || productList.size() < 1 || productList.get(0).getSku() == null || productList.get(0).getSku().size() < 1){
			throw new ErpCommonException("remote skuid emptity","下单时，远程未找到当前商品：product_id-skuid: " + productId + "-" +skuid);
		}

		List<IMSku> skus = productList.get(0).getSku();
		Map<String, Integer> skuIdStockMap = new HashMap<>();
		for(IMSku sku : skus){
			skuIdStockMap.put(sku.getSkuid(), sku.getStock() == null ? 0 : sku.getStock());
		}
		if(skuIdStockMap.get(skuid) == null || skuIdStockMap.get(skuid) < needStock){
			throw new ErpCommonException("intramirror sku stock inv not enough","intra mirror 库存不足");
		}
	}

	/**
	 * 获取订单详情
	 * @param shopOauth
	 * @param getJdOrder
	 */
	public void getOrder(JdShopOauthDO shopOauth, JdOrderDO getJdOrder) {
		Long user_id = 91L;
		Map<String,String> param = new HashMap<>();
		param.put("userId",user_id+"");
		param.put("orderId ",getJdOrder.getChannelOrderNo());
		param.put("access_token",shopOauth.getAccessToken());      //currency String 货币代码。请使用"CNY"获得人民币值
		String resultString = HttpClientUtil.get(INTRA_MIRROR_URL+get_order_detail, param);

		if(!EasyUtil.isStringEmpty(resultString)){
			getJdOrder.setOrderJson(resultString);
		}

		jdOrderDOMapperExt.updateByPrimaryKey(getJdOrder);

	}
}
