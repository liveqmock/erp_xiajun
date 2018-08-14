package com.wangqin.globalshop.common.utils;

import com.songshun.sdk.entity.OperatorReq;
import com.wangqin.globalshop.common.idcard.ConfigContant;
import com.wangqin.globalshop.common.idcard.IDCardHttpClientUtil;
import org.junit.Test;

import java.util.HashMap;

/**
 * /**
 * 三大运营商实名认证
 * @date:2017/05/17
 */
public class YYSRZTest {

    /**
     * 运营商三要素实名认证
     * @throws Exception
     */
    @Test
    public void testT1002() throws Exception {
        OperatorReq req = new OperatorReq();

		String name = "张宇飞";
		String mobile = "15988860942";
		String idNum = "360722198909151519";

        req.setServiceCode("1002");
        req.setMobile(mobile);
        req.setName(name);
        req.setIdNumber(idNum);
        HashMap<String, Object> map = ConfigContant.sendParams(req);
        IDCardHttpClientUtil.invoke(map);
    }


    /**
     * 运营商三要素在网时长查询
     * @throws Exception
     */
    @Test
    public void testT1023() throws Exception {
        OperatorReq req = new OperatorReq();
        req.setServiceCode("1023");
        req.setMobile("");
        req.setName("");
        req.setIdNumber("");
        HashMap<String, Object> map = ConfigContant.sendParams(req);
        IDCardHttpClientUtil.invoke(map);
    }

    /**
     * 运营商三要素在网状态查询
     * @throws Exception
     */
    @Test
    public void testT1024() throws Exception {
        OperatorReq req = new OperatorReq();
        req.setServiceCode("1024");
        req.setMobile("");
        req.setName("");
        req.setIdNumber("");
        HashMap<String, Object> map = ConfigContant.sendParams(req);
        IDCardHttpClientUtil.invoke(map);
    }

}
