package com.wangqin.globalshop.channel.service.jingdong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdItemOperateDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.JdItemOperateDOMapperExt;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channelapi.dal.GlobalShopItemVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.channelapi.dal.JdCommonParam;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.HttpPostUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by 777 on 2018/6/13
 */
@Service
public class JdItemOperateServiceImpl implements JdItemOperateService {

	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private JdItemOperateDOMapperExt jdItemOperateDOMapperExt;


	public int deleteByPrimaryKey(Long id){
		return jdItemOperateDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(JdItemOperateDO record){
		return jdItemOperateDOMapperExt.insert(record);
	}

	public int insertSelective(JdItemOperateDO record){
		return jdItemOperateDOMapperExt.insertSelective(record);
	}

	public JdItemOperateDO selectByPrimaryKey(Long id){
		return jdItemOperateDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(JdItemOperateDO record){
		return jdItemOperateDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(JdItemOperateDO record){
		return jdItemOperateDOMapperExt.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(JdItemOperateDO record){
		return jdItemOperateDOMapperExt.updateByPrimaryKey(record);
	}

	public JdItemOperateDO searchJdItemOperate(JdItemOperateDO jdItemOperateDO){
		return jdItemOperateDOMapperExt.searchJdItemOperate(jdItemOperateDO);
	}

	public List<JdItemOperateDO> searchJdItemOperateList(JdItemOperateDO jdItemOperateDO){
		return jdItemOperateDOMapperExt.searchJdItemOperateList(jdItemOperateDO);
	}

	public Long searchJdItemOperateCount(JdItemOperateDO jdItemOperateDO){
		return jdItemOperateDOMapperExt.searchJdItemOperateCount(jdItemOperateDO);
	}

	public void createOrUpdateItemOpreate(JdShopOauthDO shopOauth, String operateType, String itemCode, String syncStatus, String sendStatus){

		JdItemOperateDO so = new JdItemOperateDO();
		so.setChannelNo(shopOauth.getChannelNo());
		so.setCompanyNo(shopOauth.getCompanyNo());
		so.setShopCode(shopOauth.getShopCode());
		so.setItemCode(itemCode);
		so.setOperateType(operateType);
		JdItemOperateDO exit = jdItemOperateDOMapperExt.searchJdItemOperate(so);
		if(exit == null){
			JdItemOperateDO jdItemOperateDO = new JdItemOperateDO();
			jdItemOperateDO.setChannelNo(shopOauth.getChannelNo());
			jdItemOperateDO.setCompanyNo(shopOauth.getCompanyNo());
			jdItemOperateDO.setShopCode(shopOauth.getShopCode());
			jdItemOperateDO.setItemCode(itemCode);
			jdItemOperateDO.setOperateType(operateType);
			jdItemOperateDO.setIsDel(false);

			jdItemOperateDO.setSyncStatus(syncStatus);
			jdItemOperateDO.setSendStatus(sendStatus);
			jdItemOperateDOMapperExt.insert(jdItemOperateDO);
		}else {
			exit.setSyncStatus(syncStatus);
			exit.setSendStatus(sendStatus);
			jdItemOperateDOMapperExt.updateByPrimaryKey(exit);
		}

	}

	public void queryItemThenSync2Jd4Add(JdItemOperateDO jdItemOperateDO, JdShopOauthDO shopOauth){
		Map<String,String> param = new HashMap<>();
		param.put("itemCode",jdItemOperateDO.getItemCode());

		String jsonStr = HttpPostUtil.doHttpPost(GlobalshopStatic.globalshop_dev_url+"/jditem/queryadd",param);
		JsonResult<Object> jsonResult = JSON.parseObject(jsonStr,JsonResult.class);
		if(!jsonResult.isSuccess()){
						jdItemOperateDO.setSyncStatus(SyncStatus.FAILURE);
						jdItemOperateDO.setErrorMassge(EasyUtil.truncateLEFitSize(jsonResult.getMsg(),1020));
						jdItemOperateDOMapperExt.updateByPrimaryKey(jdItemOperateDO);
						return;
		}

		ItemVo itemVo  = JSONObject.parseObject(jsonResult.getData().toString(),ItemVo.class);

		if(itemVo == null){
			jdItemOperateDO.setSyncStatus(SyncStatus.SUCCESS);
			jdItemOperateDO.setErrorMassge("itemcode 查询到的数据为空");
			jdItemOperateDOMapperExt.updateByPrimaryKey(jdItemOperateDO);
			return;
		}

		Object ware = null;
		try {
			ware = JdShopFactory.getChannel(shopOauth).createItem(itemVo);
			if(ware == null){
				jdItemOperateDO.setErrorMassge("上传未返回值ware");
				jdItemOperateDO.setSyncStatus(SyncStatus.FAILURE);
				logger.error("queryItemThenSync2Jd4Add error: ");
			}else {
				jdItemOperateDO.setItemJson(JSON.toJSONString(ware));
				jdItemOperateDO.setSyncStatus(SyncStatus.SUCCESS);
			}
		} catch (ErpCommonException e){
			jdItemOperateDO.setErrorMassge(e.getErrorMsg());
			jdItemOperateDO.setSyncStatus(SyncStatus.FAILURE);
		}catch (Exception e) {
			jdItemOperateDO.setErrorMassge(e.getMessage());
			jdItemOperateDO.setSyncStatus(SyncStatus.FAILURE);
			logger.error("queryItemThenSync2Jd4Add error: ",e);
		}

		jdItemOperateDOMapperExt.updateByPrimaryKey(jdItemOperateDO);
	}

	public void queryItemThenSync2Jd4Update(JdItemOperateDO jdItemOperateDO, JdShopOauthDO shopOauth){

		Map<String,String> param = new HashMap<>();
		param.put("itemCode",jdItemOperateDO.getItemCode());
		param.put("shopCode",shopOauth.getShopCode());

		String jsonStr = null;
		try {
			jsonStr = HttpPostUtil.doHttpPost(GlobalshopStatic.globalshop_dev_url+"/jditem/queryupdate",param);
		} catch (Exception e) {
			logger.error("queryItemThenSync2Jd4Update error: ",e);
		}

		JsonResult<Object> jsonResult = JSON.parseObject(jsonStr,JsonResult.class);
		if(!jsonResult.isSuccess()){
			jdItemOperateDO.setSyncStatus(SyncStatus.FAILURE);
			jdItemOperateDO.setErrorMassge(EasyUtil.truncateLEFitSize(jsonResult.getMsg(),1020));
			jdItemOperateDOMapperExt.updateByPrimaryKey(jdItemOperateDO);
			return;
		}

		GlobalShopItemVo globalShopItemVo  = JSONObject.parseObject(jsonResult.getData().toString(),GlobalShopItemVo.class);

		if(globalShopItemVo == null){
			jdItemOperateDO.setSyncStatus(SyncStatus.SUCCESS);
			jdItemOperateDO.setErrorMassge("itemcode 查询到的数据为空");
			jdItemOperateDOMapperExt.updateByPrimaryKey(jdItemOperateDO);
			return;
		}


		try {
			JdShopFactory.getChannel(shopOauth).updateItem(globalShopItemVo);
			jdItemOperateDO.setSyncStatus(SyncStatus.SUCCESS);
		} catch (ErpCommonException e){
			jdItemOperateDO.setErrorMassge(e.getErrorMsg());
			jdItemOperateDO.setSyncStatus(SyncStatus.FAILURE);
		}catch (Exception e) {
			jdItemOperateDO.setErrorMassge(e.getMessage());
			jdItemOperateDO.setSyncStatus(SyncStatus.FAILURE);
			logger.error("queryItemThenSync2Jd4Update error: ",e);
		}

		jdItemOperateDOMapperExt.updateByPrimaryKey(jdItemOperateDO);

	}


	public void sendItem2Globalshop(JdItemOperateDO jdItemOperateDO, JdShopOauthDO shopOauth){
		GlobalShopItemVo globalShopItemVo = null;
		try {
			globalShopItemVo = JdShopFactory.getChannel(shopOauth).convertItemJd2Global(jdItemOperateDO.getItemJson());
		} catch (Exception e) {
			logger.error("sendItem2Globalshop error: ",e);
		}

		if(globalShopItemVo == null){
			logger.error("sendItem2Globalshop error: ");
		}

		Map<String,String> pram = new HashMap<>();
		JdCommonParam jdCommonParam = new JdCommonParam();
		jdCommonParam.setChannelNo(shopOauth.getChannelNo());
		jdCommonParam.setCompanyNo(shopOauth.getCompanyNo());
		jdCommonParam.setShopCode(shopOauth.getShopCode());
		pram.put("jdCommonParam",JSON.toJSONString(jdCommonParam));
		pram.put("globalShopItemVo",JSON.toJSONString(globalShopItemVo));

		String jsonObject = null;
		try {
			jsonObject = HttpPostUtil.doHttpPost(GlobalshopStatic.globalshop_dev_url+"/jditem/taskitem",pram);
		} catch (Exception e) {
			logger.error("sendItem2Globalshop error: ",e);
		}

		JsonResult<String> result = JSON.parseObject(jsonObject.toString(),JsonResult.class);

		if(result.isSuccess()){
			jdItemOperateDO.setSendStatus(SendStatus.SUCCESS);
		}else {
			//补充失败信息
			jdItemOperateDO.setSendStatus(SendStatus.FAILURE);
		}
		jdItemOperateDOMapperExt.updateByPrimaryKey(jdItemOperateDO);
	}


}
