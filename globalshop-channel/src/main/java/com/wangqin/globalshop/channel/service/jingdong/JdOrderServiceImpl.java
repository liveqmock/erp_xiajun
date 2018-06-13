package com.wangqin.globalshop.channel.service.jingdong;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.JdOrderDOMapperExt;
import com.wangqin.globalshop.channelapi.dal.GlobalshopOrderVo;
import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.wangqin.globalshop.common.utils.JsonResult;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by 777 on 2018/6/12
 */
@Service
public class JdOrderServiceImpl implements JdOrderService{

	protected Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private JdOrderDOMapperExt jdOrderDOMapperExt;

	public int deleteByPrimaryKey(Long id){
		return jdOrderDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(JdOrderDO record){
		return jdOrderDOMapperExt.insert(record);
	}

	public int insertSelective(JdOrderDO record){
		return jdOrderDOMapperExt.insertSelective(record);
	}

	public JdOrderDO selectByPrimaryKey(Long id){
		return jdOrderDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(JdOrderDO record){
		return jdOrderDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(JdOrderDO record){
		return jdOrderDOMapperExt.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(JdOrderDO record){
		return jdOrderDOMapperExt.updateByPrimaryKey(record);
	}

	public JdOrderDO searchJdOrder(JdOrderDO jdOrderDO){
		return jdOrderDOMapperExt.searchJdOrder(jdOrderDO);
	}

	public List<JdOrderDO> searchJdOrderList(JdOrderDO jdOrderDO){
		return jdOrderDOMapperExt.searchJdOrderList(jdOrderDO);
	}

	public Long searchJdOrderCount(JdOrderDO jdOrderDO){
		return jdOrderDOMapperExt.searchJdOrderCount(jdOrderDO);
	}

	/**
	 *
	 * 状态修改成request，等待下发
	 * @param jdOrderDOS
	 */
	public void saveOrders4Task(List<JdOrderDO> jdOrderDOS){
		for(JdOrderDO order : jdOrderDOS){

			order.setSendStatus(SendStatus.REQUEST);
			order.setOrderModifyTime(new Date());

			JdOrderDO so = new JdOrderDO();
			so.setChannelNo(order.getChannelNo());
			so.setShopCode(order.getShopCode());
			so.setChannelOrderNo(order.getChannelOrderNo());
			JdOrderDO result = jdOrderDOMapperExt.searchJdOrder(so);

			if(result == null){
				jdOrderDOMapperExt.insertSelective(order);
			}else {
				result.setModifier("-1");
				result.setGmtModify(new Date());
				result.setOrderJson(order.getOrderJson());
				jdOrderDOMapperExt.updateByPrimaryKey(result);
			}
		}
	}


	
	public void sendJdOrder2globalshop4Task(JdOrderDO jdOrderDO, JdShopOauthDO shopOauth){
		GlobalshopOrderVo globalShopOrderVo = null;
		try {
			globalShopOrderVo = JdShopFactory.getChannel(shopOauth).convertJdOrder2Globalshop(jdOrderDO.getOrderJson());
		} catch (Exception e) {
			logger.error("sendJdOrder2globalshop4Task error: ",e);
		}

		if(globalShopOrderVo == null){
			logger.error("sendJdOrder2globalshop4Task error: ");
		}

		Map<String,String> pram = new HashMap<>();
		pram.put("channelNo",shopOauth.getChannelNo());
		pram.put("companyNo",shopOauth.getCompanyNo());
		pram.put("shopCode",shopOauth.getShopCode());
		pram.put("globalShopOrderVo",JSON.toJSONString(globalShopOrderVo));

		JSONObject jsonObject = null;
		try {
			jsonObject = HttpClientUtil.post(GlobalshopStatic.globalshop_dev_url+"/channel/",pram);
		} catch (Exception e) {
			logger.error("sendJdOrder2globalshop4Task error: ",e);
		}

		JsonResult<String> result = JSON.parseObject(jsonObject.toString(),JsonResult.class);

		if(result.isSuccess()){
			jdOrderDO.setSendStatus(SendStatus.SUCCESS);
		}else {
			jdOrderDO.setSendStatus(SendStatus.FAILURE);
		}
		jdOrderDOMapperExt.updateByPrimaryKey(jdOrderDO);
	}


}
