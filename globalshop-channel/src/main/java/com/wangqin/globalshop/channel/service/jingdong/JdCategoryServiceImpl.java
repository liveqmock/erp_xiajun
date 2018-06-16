package com.wangqin.globalshop.channel.service.jingdong;

import com.alibaba.fastjson.JSON;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.category.Category;
import com.jd.open.api.sdk.domain.list.CategoryAttrReadService.CategoryAttrJos;
import com.jd.open.api.sdk.domain.list.CategoryAttrValueReadService.CategoryAttrValue;
import com.jd.open.api.sdk.request.category.CategorySearchRequest;
import com.jd.open.api.sdk.request.list.CategoryReadFindAttrsByCategoryIdJosRequest;
import com.jd.open.api.sdk.request.list.CategoryReadFindValuesByAttrIdRequest;
import com.jd.open.api.sdk.response.category.CategorySearchResponse;
import com.jd.open.api.sdk.response.list.CategoryReadFindAttrsByCategoryIdJosResponse;
import com.jd.open.api.sdk.response.list.CategoryReadFindValuesByAttrIdResponse;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdCategoryAttarDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdCategoryAttarValueDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.JdCategoryAttarDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapper.JdCategoryAttarValueDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapper.JdCategoryDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by 777 on 2018/6/16
 */
@Service
public class JdCategoryServiceImpl implements JdCategoryService{

	@Autowired
	private JdCategoryDOMapper jdCategoryDOMapper;

	@Autowired
	private JdCategoryAttarDOMapper jdCategoryAttarDOMapper;

	@Autowired
	private JdCategoryAttarValueDOMapper jdCategoryAttarValueDOMapper;


	public void getCategory(JdShopOauthDO shopOauth){

		JdClient client = new DefaultJdClient(shopOauth.getServerUrl(),shopOauth.getAccessToken(),shopOauth.getAppKey(),shopOauth.getAppsecretKey());

		CategorySearchRequest request=new CategorySearchRequest();
		CategorySearchResponse cateRes = null;
		try{
			cateRes=client.execute(request);
			System.out.println(JSON.toJSONString(cateRes));
		} catch (JdException e) {
			e.printStackTrace();
		}

		for(Category category : cateRes.getCategory()){
			JdCategoryDO jdCategoryDO = new JdCategoryDO();
			jdCategoryDO.setMsg(JSON.toJSONString(cateRes));
			jdCategoryDO.setCid(category.getId()+"");
			jdCategoryDO.setAttributeId(category.getId()+"");
			jdCategoryDO.setLev(category.getLev()+"");
			jdCategoryDO.setName(category.getName());

			jdCategoryDO.setShopCode(shopOauth.getShopCode());
			jdCategoryDO.setChannelNo(shopOauth.getChannelNo());
			jdCategoryDO.setCompanyNo(shopOauth.getCompanyNo());
			jdCategoryDOMapper.insert(jdCategoryDO);
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


				for(CategoryAttrJos attrJos : attresponse.getCategoryAttrs()){

					JdCategoryAttarDO jdCategoryAttarDO = new JdCategoryAttarDO();
					jdCategoryAttarDO.setShopCode(shopOauth.getShopCode());
					jdCategoryAttarDO.setChannelNo(shopOauth.getChannelNo());
					jdCategoryAttarDO.setCompanyNo(shopOauth.getCompanyNo());

					jdCategoryAttarDO.setAttname(attrJos.getAttName());
					jdCategoryAttarDO.setAttributeType(attrJos.getAttributeType()+"");
					//jdCategoryAttarDO.setMsg(JSON.toJSONString(attresponse));
					jdCategoryAttarDO.setCategoryAttrId(attrJos.getCategoryAttrId()+"");

					jdCategoryAttarDOMapper.insert(jdCategoryAttarDO);
				}


				//第三步：针对每个属性值，再去查看所有可能的值

				for(CategoryAttrJos attrJos : attresponse.getCategoryAttrs()){

					CategoryReadFindValuesByAttrIdRequest valueRequest=new CategoryReadFindValuesByAttrIdRequest();


					valueRequest.setCategoryAttrId(Long.valueOf(attrJos.getCategoryAttrId()));
					//valueRequest.setField( "jingdong,yanfa,pop" );
					try {
						CategoryReadFindValuesByAttrIdResponse valuesresponse=client.execute(valueRequest);
						System.out.println(category.getId()+""+ attrJos.getAttName()+" "+attrJos.getCategoryAttrId()+"  " +JSON.toJSONString(valuesresponse));



						for(CategoryAttrValue categoryAttrValue : valuesresponse.getCategoryAttrValues()){


							JdCategoryAttarValueDO attarValueDO = new JdCategoryAttarValueDO();
							attarValueDO.setShopCode(shopOauth.getShopCode());
							attarValueDO.setChannelNo(shopOauth.getChannelNo());
							attarValueDO.setCompanyNo(shopOauth.getCompanyNo());


							attarValueDO.setCategoryAttrId(categoryAttrValue.getAttributeId()+"");
							attarValueDO.setCategoryId(categoryAttrValue.getCategoryId()+"");
							attarValueDO.setValueId(categoryAttrValue.getId()+"");
							attarValueDO.setValue(categoryAttrValue.getValue());

							jdCategoryAttarValueDOMapper.insert(attarValueDO);


						}

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
