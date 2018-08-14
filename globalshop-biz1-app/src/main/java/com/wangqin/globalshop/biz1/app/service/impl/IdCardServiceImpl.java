package com.wangqin.globalshop.biz1.app.service.impl;

import com.songshun.sdk.entity.RespEntity;
import com.songshun.sdk.entity.SFRZReq;
import com.wangqin.globalshop.biz1.app.dal.dataObject.IdCardDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.IdCardDOMapperExt;
import com.wangqin.globalshop.biz1.app.service.IdCardService;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.idcard.ConfigContant;
import com.wangqin.globalshop.common.idcard.IDCardHttpClientUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Create by 777 on 2018/8/14
 */
@Service
public class IdCardServiceImpl implements IdCardService {

	public static final String sucess_code = "0000";


	@Autowired
	private IdCardDOMapperExt idCardDOMapper;

	/**
	 * 身份校验：已开通业务
	 * @param name
	 * @param idNum
	 * @return
	 */
	public Boolean idcardTwoItem(String name, String idNum){
		if(EasyUtil.isStringEmpty(name) || EasyUtil.isStringEmpty(idNum)){
            throw new ErpCommonException("param_error","缺少必要的参数");
		}
		Boolean rzSuccess = false;
        //第0步：先用正则表达式校验
		String reg =  "^\\d{15}$|^\\d{17}[0-9Xx]$";
		if(!idNum.matches(reg)){
			throw new ErpCommonException("id_card_error","身份证号码格式不对，请核对重新输入再试");
		}

		//第一步：先去数据库查
		IdCardDO idCardDO = idCardDOMapper.queryByNameAndIdNum(name,idNum);

		if(idCardDO == null){
			//第二步：如果数据库没有，再去松顺查
			SFRZReq req=new SFRZReq();
			req.setServiceCode("101");
			req.setName(name);
			req.setIdNumber(idNum);
			HashMap<String, Object> map = ConfigContant.initParams(req);

			RespEntity respEntity = null;
			try {
				respEntity = IDCardHttpClientUtil.invokeWithResp(map);
			} catch (Exception e) {
				e.printStackTrace();
				rzSuccess = false;
			}

			if(sucess_code.equals(respEntity.getKey())){
				rzSuccess = true;
				//第三步：如果通过，直接插入数据库
				idCardDO = new IdCardDO();
				idCardDO.setRealName(name);
				idCardDO.setIdNumber(idNum);
				idCardDO.init4NoLogin();
				idCardDOMapper.insert(idCardDO);
			}
		}else {
			rzSuccess = true;
		}
		return rzSuccess;
	}




	/**
	 * 银行卡校验：暂未开通业务
	 * @param name
	 * @param idNum
	 * @return
	 */
//	public Boolean bankRZTwoItem(String name, String idNum){
//		Boolean rzSuccess = false;
//
//		return rzSuccess;
//	}

	/**
	 * 移动运营商校验：暂未开通
	 * @param name
	 * @param idNum
	 * @param mobile
	 * @return
	 */
//	public Boolean yysRZThreeItem(String name, String idNum, String mobile){
//		Boolean rzSuccess = false;
//
//		return rzSuccess;
//	}
}
