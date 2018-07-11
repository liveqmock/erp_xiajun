package com.wangqin.globalshop.usercenter.controller;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryOnWareHouseDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryOnWarehouseMapperExt;
import com.wangqin.globalshop.usercenter.wechat_sdk.AesException;
import com.wangqin.globalshop.usercenter.wechat_sdk.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author biscuit
 * @data 2018/07/10
 * 第三方平台授权controller
 */
@RestController
@RequestMapping("/account")
public class WechatAuthorization {
    @Autowired
    private InventoryOnWarehouseMapperExt mapper;
    @RequestMapping("/authorization")
    public void getComponentVerifyTicket(@RequestParam("timestamp")String timestamp, @RequestParam("nonce")String nonce,
                                           @RequestParam("msg_signature")String msgSignature, @RequestBody String postData){
        System.out.println("timestamp=========="+timestamp);
        System.out.println("nonce=========="+nonce);
        System.out.println("msg_signature=========="+msgSignature);
        System.out.println("postData=========="+postData);
        WXBizMsgCrypt pc = null;
        try {
            //前两个是微信开放平台的账户  第三个是公众平台的appid
                pc = new WXBizMsgCrypt("FBDctXZiRWQYjrXMm5txJPAFWsEdkYnc", "FBDctXZiRWQYjrXMm5txJPAFWsEdkYnc7wud647ryu1", "wx43020a9d04042b56");
//            String msgSignature = nodelist2.item(0).getTextContent();

            String result2 = pc.DecryptMsg(msgSignature, timestamp, nonce, postData);
            System.out.println("解密后明文: " + result2);
        } catch (AesException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws AesException {

        String timestamp = "1531223218";
        String nonce = "69234950";
        String msgSignature = "0435fbbb36383831ceae0fddaa7a72b646bda4d2";
        String postData = "<xml>" +
                "<AppId><![CDATA[wxe25c15397f0ec710]]></AppId>" +
                "<Encrypt><![CDATA[t7k1CYPOinNbmDVXLA1pyFcqkutUsLFAoFYaN7Ms9S8d2GyOmc5+0olZ4Nxw0eNjcdWhDBQ7reJa/kH7a3GLNVmZFclMiOD/SNOPbO6Ko4gcVfR4UuTlkbPg5lUs/q9DgCEe88V7q7SvILsmYBnBoNjC5uZFLD6SrXunC0ckfKcADs6zcy6oqV4Om+If1XcxUDVm188uFtA0X9aBgvwvl/qfk34LQKexuLH0otj+oDpBb7ZjFLIYr+NL8Jj/1NkZgVXoVChB4zQABXLdqqkCEyKXp7P4fGWZlB6qxXiv3SEVnj2FWyZF9iKs3RY0v54kTEGAVYQyekGfNRFHxDrSr8PfVYO9irNKSJo+aaawDg9/oniIgFEbrMtNAC0bRo3Y1AhH/AQOqyEqOkvK95eARASUhdruayEq3K6TfCJfMnwfspVP5P7Tv8ENun1sLXtS4pZa3Av93VAqImqhW2Oy+A==]]></Encrypt>" +
                "</xml>";
        postData = postData.replace("AppId", "ToUserName");
        WXBizMsgCrypt pc = new WXBizMsgCrypt("FBDctXZiRWQYjrXMm5txJPAFWsEdkYnc", "FBDctXZiRWQYjrXMm5txJPAFWsEdkYnc7wud647ryu1", "wxe25c15397f0ec710");
        String result2 = pc.DecryptMsg(msgSignature, timestamp, nonce, postData);
        System.out.println("解密后明文: " + result2);

    }

}
