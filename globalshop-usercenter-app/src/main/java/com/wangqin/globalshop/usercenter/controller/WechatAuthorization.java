package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.usercenter.wechat_sdk.AesException;
import com.wangqin.globalshop.usercenter.wechat_sdk.WXBizMsgCrypt;
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

}
