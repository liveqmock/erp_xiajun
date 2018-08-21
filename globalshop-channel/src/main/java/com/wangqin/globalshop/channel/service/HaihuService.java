package com.wangqin.globalshop.channel.service;

import com.wangqin.globalshop.biz1.app.dal.dataObject.JdLogisticsDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.HttpClientUtil;
import com.wangqin.globalshop.common.utils.Md5Util;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create by 777 on 2018/8/21
 */
@Service
public class HaihuService {

	private static Logger logger = LoggerFactory.getLogger("HaihuService");

    public static final String haihu_feedback_url = "http://expressjob.haihu.com/erp/notify";

	/**
	 * 发货反馈
	 * @param shopOauth
	 * @param requestLogistic
	 */
	public void feedback(JdShopOauthDO shopOauth, JdLogisticsDO requestLogistic) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("packageNo", requestLogistic.getLogisticNo());
		param.put("logisticsCompany", requestLogistic.getLogisticName());
		param.put("enteCode", "irhua");//签名用
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStamp = dateFormat.format(new Date());
		String sign = Md5Util.getMD5("enteCode=irhua&timeStamp="+timeStamp);
		param.put("timeStamp", timeStamp);
		param.put("sign", sign);


		String targetNo = requestLogistic.getChannelOrderNo();
		String skuStr = requestLogistic.getChannelSubOrderNo();
		String[] skus = skuStr.split(",");

		List<Map<String, Object>> itemSkusList = new ArrayList<>();
		for(String skuCode : skus){
			Map<String, Object> itemSkusDetail = new HashMap<String, Object>();
			itemSkusDetail.put("skuCode", skuCode);
			itemSkusList.add(itemSkusDetail);
		}
		param.put("itemSkusList", itemSkusList);
		param.put("erpOrderNo", targetNo);

		JSONObject description = HttpClientUtil.post(haihu_feedback_url, null, param,"1");
		try {
			Integer respCode = (Integer) description.get("ResponseCode");
			if (respCode == 100) {
				return;
			}else {
				throw new ErpCommonException("","haihu feedback error"+description.toString());
			}
		} catch (Exception e) {
			throw new ErpCommonException("","haihu feedback error"+description.toString());
		}
	}




}
