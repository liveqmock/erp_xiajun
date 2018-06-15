package com.wangqin.globalshop.channel.service.jingdong;

import com.alibaba.fastjson.JSONObject;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.Feature;
import com.jd.open.api.sdk.domain.Image;
import com.jd.open.api.sdk.domain.Sku;
import com.jd.open.api.sdk.domain.Ware;
import com.jd.open.api.sdk.internal.JSON.JSON;
import com.jd.open.api.sdk.request.category.CategoryAttributeSearchRequest;
import com.jd.open.api.sdk.request.ware.WareGetRequest;
import com.jd.open.api.sdk.request.ware.WareInfoByInfoRequest;
import com.jd.open.api.sdk.request.ware.WareWriteAddRequest;
import com.jd.open.api.sdk.response.category.CategoryAttributeSearchResponse;
import com.jd.open.api.sdk.response.ware.WareGetResponse;
import com.jd.open.api.sdk.response.ware.WareInfoByInfoSearchResponse;
import com.jd.open.api.sdk.response.ware.WareWriteAddResponse;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * Create by 777 on 2018/6/15
 */
public class JdShopServiceImplTest {


	public static final String url = "https://api.jd.com/routerjson";

	public static final String token = "32c4da5a-2a99-4d0e-a304-3c58da5cf59d";


	public static final String appkey = "96C38E0AAAA47520B6211D32A5A14EDE";


	public static final String appsecret = "758ae3185aec4822aa5593fda0aa9b98";


	protected Logger logger = LogManager.getLogger(getClass());

	JdClient client = new DefaultJdClient(url,token,appkey,appsecret);


	@Test public void createItem() {



		WareWriteAddRequest request = new WareWriteAddRequest();


		Ware ware = new Ware();
		ware.setTitle("title_名称");
		ware.setCategoryId(16797L);//必填
		ware.setOuterId("1528882109");
		ware.setWrap("件");




		//属性[产品产地:152198],
		// 国产/进口:152199],
		// 分类:152196],
		// 功效:152197],
		// 净含量（mL/g）:10152247],
		// 分类:10152248],
		// 保质期（年）:10152245],
		// 产品产地:10152246],
		// 功效:10152249],必填

		ware.setColType(0);
		ware.setWareLocation(1);

		//属性ID，
		//private Set<Prop> props;


//		Set<Prop> props = new HashSet<>();
//		Prop Prop_01 = new Prop();
//		Prop_01.setAttrId(152198+"");
//		Prop_01.setAttrValues();
//
//
//		Prop Prop_02 = new Prop(152199+"","国产","国产/进口");
//		Prop Prop_03 = new Prop(152196+"","分类，不懂填什么","分类");
//		Prop Prop_04 = new Prop(152197+"","很好用饿","功效");
//		Prop Prop_05 = new Prop(10152247+"","10","净含量（mL/g）");
//
//		Prop Prop_06 = new Prop(10152248+"","又来一个分类","分类");
//		Prop Prop_07 = new Prop(10152245+"","1","保质期（年）");
//		Prop Prop_08 = new Prop(10152246+"","又来中国","产品产地");
//		Prop Prop_09 = new Prop(10152249+"","又来功效","功效");
//
//		features.add(feature_01);
//		features.add(feature_02);
//		features.add(feature_03);
//		features.add(feature_04);
//		features.add(feature_05);
//		features.add(feature_06);
//		features.add(feature_07);
//		features.add(feature_08);
//		features.add(feature_09);
//		ware.setFeatures(features);

		//特殊属性
		//private Set<Feature> features;

		Set<Feature> features = new HashSet<>();
		//产品产地:152198
		Feature feature_01 = new Feature(152198+"","中国","产品产地");
		Feature feature_02 = new Feature(152199+"","国产","国产/进口");
		Feature feature_03 = new Feature(152196+"","分类，不懂填什么","分类");
		Feature feature_04 = new Feature(152197+"","很好用饿","功效");
		Feature feature_05 = new Feature(10152247+"","10","净含量（mL/g）");

		Feature feature_06 = new Feature(10152248+"","又来一个分类","分类");
		Feature feature_07 = new Feature(10152245+"","1","保质期（年）");
		Feature feature_08 = new Feature(10152246+"","又来中国","产品产地");
		Feature feature_09 = new Feature(10152249+"","又来功效","功效");

		features.add(feature_01);
		features.add(feature_02);
		features.add(feature_03);
		features.add(feature_04);
		features.add(feature_05);
		features.add(feature_06);
		features.add(feature_07);
		features.add(feature_08);
		features.add(feature_09);
		ware.setFeatures(features);







		List<Image> images = new ArrayList<>();
		//		Image image = new Image();
		//		image.setColorId(00000000000+"");
		//		image.setImgUrl("jfs/t2116/102/1731643157/81969/c3df941a/5670f868Nc441d4c3.jpg");
		//		image.setImgId(1L);
		//		image.setImgIndex(1);

		Image image = new Image("jfs/t2116/102/1731643157/81969/c3df941a/5670f868Nc441d4c3.jpg",00000000000+"",1);
		images.add(image);
		ware.setImages(images);


//		AdWords adWords = new AdWords();
//		adWords.setUrl("adWords url");
//		adWords.setUrlWords("广告词的关键字");
//		adWords.setWords("广告词的关键字和广告词");
//		ware.setAdWords(adWords);

		//介绍
		ware.setIntroduction("商品介绍");
		ware.setMobileDesc("商品介绍-移动端");


		//市场价，成本价，京东价
		ware.setJdPrice(BigDecimal.valueOf(10));
		ware.setCostPrice(BigDecimal.valueOf(10));
		ware.setMarketPrice(BigDecimal.valueOf(10));

		List<Sku> skus = new ArrayList<>();

			Sku sku = new Sku();
			sku.setBarCode("upc_barcode");
			sku.setOuterId("skucode");
			sku.setJdPrice(BigDecimal.valueOf(10));
			//sku.setStockNum(skuVo.getTotalAvailableInv());
			sku.setStockNum(100L);//必填大于0
		    sku.setFeatures(features);
			skus.add(sku);

		ware.setSkus(skus);

		request.setSkus(skus);

		request.setWare(ware);


		WareWriteAddResponse response = null;
		try {
			response=client.execute(request);
		} catch (JdException e) {
			logger.error("createItem_error",e);
			throw new ErpCommonException("createItem,商品发布时，京东内部出错");
		}

		if(!response.getCode().equals("0")){
			String errorMsg = "";
			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc()+response.getEnDesc();
			throw new ErpCommonException("商品发布时，京东内部出错:"+errorMsg);
		}
		System.out.println("成功：");
		System.out.println(JSONObject.toJSON(response.getWare()));
	}




	//该接口无权限调用
	@Test public void categolyGet(){

//		CategorySearchRequest request=new CategorySearchRequest();
//
//		request.setFields( "jingdong" );
//
//		CategorySearchResponse response = null;
//		try {
//			response=client.execute(request);
//		} catch (JdException e) {
//			logger.error("createItem_error",e);
//			throw new ErpCommonException("createItem,商品发布时，京东内部出错");
//		}
//
//		if(!response.getCode().equals("0")){
//			String errorMsg = "";
//			errorMsg += response == null ? "" : response.getCode()+" "+response.getZhDesc()+response.getEnDesc();
//			throw new ErpCommonException("商品发布时，京东内部出错:"+errorMsg);
//		}
//		System.out.println("成功：");
//		System.out.println(JSONObject.toJSON(response.getCategory()));



		CategoryAttributeSearchRequest  attributeSearchRequest=new CategoryAttributeSearchRequest();

		//attributeSearchRequest.setFields( "jingdong" );

		CategoryAttributeSearchResponse attributeSearchResponse = null;
		try {
			attributeSearchResponse= client.execute(attributeSearchRequest);
		} catch (JdException e) {
			logger.error("createItem_error",e);
			throw new ErpCommonException("createItem,商品发布时，京东内部出错");
		}

		if(!attributeSearchResponse.getCode().equals("0")){
			String errorMsg = "";
			errorMsg += attributeSearchResponse == null ? "" : attributeSearchResponse.getCode()+" "+attributeSearchResponse.getZhDesc()+attributeSearchResponse.getEnDesc();
			throw new ErpCommonException("商品发布时，京东内部出错:"+errorMsg);
		}
		System.out.println("成功：");
		System.out.println(JSONObject.toJSON(attributeSearchResponse));



	}

	@Test
	public void getItems(){

		Date endTime  = new Date();
		Date startTime  = DateUtil.getDateByCalculate(endTime, Calendar.DAY_OF_MONTH, -1);
		WareInfoByInfoRequest request = new WareInfoByInfoRequest();
		request.setEndModified(DateUtil.convertDate2Str(endTime,DateUtil.formateStr19));
		request.setStartModified(DateUtil.convertDate2Str(startTime,DateUtil.formateStr19));


		request.setWareStatus(2+"");

		request.setPage(1+"");
		request.setPageSize(50+"");

		WareInfoByInfoSearchResponse response = null;
		try {
			response = client.execute(request);
		} catch (JdException e) {
			e.printStackTrace();
		}

		System.out.println(JSON.toString(response));


		List<com.jd.open.api.sdk.domain.ware.Ware> wares = response.getWareInfos();
		List<Long> wareIdlist = new ArrayList<>();
		for(com.jd.open.api.sdk.domain.ware.Ware ware : wares){
			wareIdlist.add(ware.getWareId());
		}



		//查找完wareID，再去找sku
		WareGetRequest skuRequest=new WareGetRequest();
		for(Long wareId : wareIdlist){
			skuRequest.setWareId(wareId+"");
		}
		WareGetResponse skuResponse = null;
		try {
			skuResponse = client.execute(skuRequest);
		} catch (JdException e) {
			e.printStackTrace();
		}
		System.out.println(JSON.toString(skuRequest));

	}



}
