package com.wangqin.globalshop.channel.service.jingdong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.Image;
import com.jd.open.api.sdk.domain.Prop;
import com.jd.open.api.sdk.domain.Sku;
import com.jd.open.api.sdk.domain.Ware;
import com.jd.open.api.sdk.domain.category.Category;
import com.jd.open.api.sdk.domain.list.CategoryAttrReadService.CategoryAttrJos;
import com.jd.open.api.sdk.request.category.CategoryAttributeSearchRequest;
import com.jd.open.api.sdk.request.category.CategorySearchRequest;
import com.jd.open.api.sdk.request.list.CategoryReadFindAttrsByCategoryIdJosRequest;
import com.jd.open.api.sdk.request.list.CategoryReadFindValuesByAttrIdRequest;
import com.jd.open.api.sdk.request.ware.WareGetRequest;
import com.jd.open.api.sdk.request.ware.WareInfoByInfoRequest;
import com.jd.open.api.sdk.request.ware.WareWriteAddRequest;
import com.jd.open.api.sdk.response.category.CategoryAttributeSearchResponse;
import com.jd.open.api.sdk.response.category.CategorySearchResponse;
import com.jd.open.api.sdk.response.list.CategoryReadFindAttrsByCategoryIdJosResponse;
import com.jd.open.api.sdk.response.list.CategoryReadFindValuesByAttrIdResponse;
import com.jd.open.api.sdk.response.ware.WareGetResponse;
import com.jd.open.api.sdk.response.ware.WareInfoByInfoSearchResponse;
import com.jd.open.api.sdk.response.ware.WareWriteAddResponse;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.DateUtil;
import org.apache.commons.lang.math.RandomUtils;
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

	public static final String token = "b67bd481-2f8a-45d1-bce8-01f64e0d84de";


	public static final String appkey = "96C38E0AAAA47520B6211D32A5A14EDE";


	public static final String appsecret = "758ae3185aec4822aa5593fda0aa9b98";


	protected Logger logger = LogManager.getLogger(getClass());

	JdClient client = new DefaultJdClient(url,token,appkey,appsecret);



	@Test
	public void dateTest(){
		String userNo = DateUtil.formatDate(new Date(), "yyMMddHHmmss") + String.format("%1$06d", RandomUtils.nextInt(1000000));
		System.out.println("userNo: "+userNo);
	}


	@Test public void createItem() {



		WareWriteAddRequest request = new WareWriteAddRequest();


		Ware ware = new Ware();
		ware.setTitle("title_名称");
		ware.setCategoryId(16797L);//必填16753
		ware.setOuterId("1528882109");
		ware.setWrap("件");




		//属性,

		// 分类:152196],







		//找出来的：
		//产品产地152198，进口国产152199，分类152196,功效152197，功效10152249，金含量10152247，分类10152248，产地10152246
		//1001042309颜色，尺码1001042310，
		//，保质期10152245
		//

		ware.setColType(0);
		ware.setWareLocation(1);

		//属性ID，
		//private Set<Prop> props;




		// 152196:781934^
		// 152197:781954^
		// 152198:781960^
		// 152199:781962^
		//152249:10782209^




		// 1001042309:1875762344,1875764708^

		// 10152245:0,3年
		// 10152246:0,日本^
		// 10152247:0,101g_mL-200g_mL^
		// 10152248:10782198^

		Set<Prop> props = new HashSet<>();

		Prop prop01 = new Prop();

		prop01.setAttrId(152198+"");//[产品产地:152198]
		String[] value = new String[1];
		value[0]="781960";
		//value[0]="ww";
		prop01.setAttrValues(value);

		Prop prop02 = new Prop();
		prop02.setAttrId(152199+"");// 国产/进口:152199],已验证
		value[0]="781962";//781961
		//value[0]="ww";
		prop02.setAttrValues(value);

		Prop prop03 = new Prop();
		prop03.setAttrId(152197+"");// 功效:152197],已验证
		value[0]="781954";
		//value[0]="ww";
		prop03.setAttrValues(value);

		Prop prop09 = new Prop();
		prop09.setAttrId(152196+"");// 分类:10152248],
		value[0]="781934";
		prop09.setAttrValues(value);






		Prop prop04 = new Prop();
		prop04.setAttrId(10152247+"");// 净含量（mL/g）:10152247],
		value[0]="随便写能通过吗";//呢能过
		prop04.setAttrValues(value);

		Prop prop05 = new Prop();
		prop05.setAttrId(10152248+"");// 分类:10152248],
		value[0]="10782198";
		prop05.setAttrValues(value);

		Prop prop06 = new Prop();
		prop06.setAttrId(10152245+"");// 保质期（年）:10152245],
		value[0]="3年";
		prop06.setAttrValues(value);

		Prop prop07 = new Prop();
		prop07.setAttrId(10152246+"");// 产品产地:10152246],
		value[0]="781960";
		//value[0]="没有值";
		prop07.setAttrValues(value);



//		Prop prop08 = new Prop();
//		prop08.setAttrId(10152249+"");// 功效:10152249],必填
//		value[0]="10782203";
//		//value[0]="ww";
//		prop08.setAttrValues(value);



		Set<Prop> skuProps = new HashSet<>();
		skuProps.add(prop01);
		skuProps.add(prop02);
		skuProps.add(prop03);
		skuProps.add(prop09);


		props.add(prop04);
		//props.add(prop05);
		props.add(prop06);
		props.add(prop07);
		//props.add(prop08);
		props.add(prop01);
		props.add(prop02);
		props.add(prop03);
		props.add(prop09);



		ware.setProps(props);











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
		    //sku.setFeatures(features);
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

		System.out.println(JSON.toJSONString(response));


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
		System.out.println(JSON.toJSONString(skuRequest));

	}




	@Test
	public void getSkus(){

		String wareId = "1967499532";

		WareGetRequest skuRequest=new WareGetRequest();

		skuRequest.setWareId(wareId+"");

		WareGetResponse skuResponse = null;
		try {
			skuResponse = client.execute(skuRequest);
		} catch (JdException e) {
			e.printStackTrace();
		}
		System.out.println(JSON.toJSONString(skuRequest));
	}

	//第一步：获取商家已有类目
	@Test
	public void getCategory(){

		//第一步：获取商家已有类目

		CategorySearchRequest request=new CategorySearchRequest();
		CategorySearchResponse cateRes = null;
		try {
			 cateRes=client.execute(request);
			System.out.println(JSON.toJSONString(cateRes));
		} catch (JdException e) {
			e.printStackTrace();
		}







		//第二步：根据已有类目ID，查看该类目下有哪些属性
        List<Integer> categoryIdList = new ArrayList<>();
		for(Category category : cateRes.getCategory()){
                 categoryIdList.add(category.getId());

			CategoryReadFindAttrsByCategoryIdJosRequest attIdListRequest=new CategoryReadFindAttrsByCategoryIdJosRequest();


			attIdListRequest.setCid(Long.valueOf(category.getId()));
			//request.setAttributeType( 123 );
			try {
				CategoryReadFindAttrsByCategoryIdJosResponse attresponse=client.execute(attIdListRequest);
				System.out.println("category.getId(): "+category.getId()+" "+category.getName()+"  "+JSON.toJSONString(attresponse));


				//第三步：针对每个属性值，再去查看所有可能的值

                for(CategoryAttrJos attrJos : attresponse.getCategoryAttrs()){

					CategoryReadFindValuesByAttrIdRequest valueRequest=new CategoryReadFindValuesByAttrIdRequest();


					valueRequest.setCategoryAttrId(Long.valueOf(attrJos.getCategoryAttrId()));
					//valueRequest.setField( "jingdong,yanfa,pop" );
					try {
						CategoryReadFindValuesByAttrIdResponse valuesresponse=client.execute(valueRequest);
						System.out.println(category.getId()+""+ attrJos.getAttName()+" "+attrJos.getCategoryAttrId()+"  " +JSON.toJSONString(valuesresponse));
					} catch (JdException e) {
						e.printStackTrace();
					}
				}











			} catch (JdException e) {
				e.printStackTrace();

			}
		}





	}







}
