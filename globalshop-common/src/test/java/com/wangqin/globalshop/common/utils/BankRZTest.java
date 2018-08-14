package com.wangqin.globalshop.common.utils;

import com.songshun.sdk.entity.BankReq;
import com.wangqin.globalshop.common.idcard.ConfigContant;
import com.wangqin.globalshop.common.idcard.IDCardHttpClientUtil;
import org.junit.Test;

import java.util.HashMap;

/**
 * 银行卡的实名认证
 * @date:2017/05/17
 */
public class BankRZTest {

    /**
     * 银行卡二要素认证 （姓名+银行卡号）
     * @throws Exception
     */
   @Test
    public   void T301() throws Exception{
        BankReq req=new BankReq();

        String name = "张宇飞";
        String bankCard = "6214835713824611";

		req.setServiceCode("301");
        req.setName(name);
		req.setBankCard(bankCard);
        HashMap<String, Object> map = ConfigContant.initBankParams(req);
        IDCardHttpClientUtil.invoke(map);
    }

    /**
     * 银行卡三 要素认证 （姓名+银行卡号+身份证）
     * @throws Exception
     */
    @Test
    public   void T302() throws Exception{
        BankReq req=new BankReq();
        req.setServiceCode("302");
        req.setName("");
		req.setIdNumber("");
		req.setBankCard("");
        HashMap<String, Object> map = ConfigContant.initBankParams(req);
        IDCardHttpClientUtil.invoke(map);
    }

    /**
     * 银行卡四要素认证 （姓名+银行卡号+身份证+手机号码）
     * @throws Exception
     */
    @Test
    public   void T303() throws Exception{
        BankReq req=new BankReq();
        req.setServiceCode("303");
        req.setName("");
        req.setIdNumber("");
        req.setBankCard("");
		req.setMobile("");
        HashMap<String, Object> map = ConfigContant.initBankParams(req);
        IDCardHttpClientUtil.invoke(map);
    }

    /**
     * 银行卡五要素认证 （姓名+银行卡号+身份证+手机号码+账号类型）
     * @throws Exception
     */
    @Test
    public   void T304() throws Exception{
        BankReq req=new BankReq();
        req.setServiceCode("304");
        req.setName("");
        req.setIdNumber("");
        req.setBankCard("");
        req.setMobile("");
        req.setBankAccountType("");
        HashMap<String, Object> map = ConfigContant.initBankParams(req);
        IDCardHttpClientUtil.invoke(map);
    }


    /**
     * 银行卡三 要素认证返回精准错误 （姓名+银行卡号+身份证）
     * @throws Exception
     */
    @Test
    public   void T312() throws Exception{
        BankReq req=new BankReq();
        req.setServiceCode("312");
        req.setName("");
		req.setIdNumber("");
		req.setBankCard("");
        HashMap<String, Object> map = ConfigContant.initBankParams(req);
        IDCardHttpClientUtil.invoke(map);
    }

    /**
     * 银行卡四要素认证返回精准错误 （姓名+银行卡号+身份证+手机号码）
     * @throws Exception
     */
    @Test
    public   void T313() throws Exception{
        BankReq req=new BankReq();
        req.setServiceCode("313");
        req.setName("");
		req.setIdNumber("");
		req.setBankCard("");
		req.setMobile("");
        HashMap<String, Object> map = ConfigContant.initBankParams(req);
        IDCardHttpClientUtil.invoke(map);
    }
}
