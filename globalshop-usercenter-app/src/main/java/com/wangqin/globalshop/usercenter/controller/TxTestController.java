package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.usercenter.wechat_sdk.WXBizMsgCrypt;
import org.springframework.web.bind.annotation.*;

/**
 * @author biscuit
 * @data 2018/07/11
 */
@RestController
public class TxTestController {
    @RequestMapping("/4883393465")
    public String getTxt() {
        return "136bbec27d35b86aafb3cefde812a05e";
    }

    private String componentAppid = "wxe25c15397f0ec710";

    /**
     * 微信审核回调
     * @param timestamp
     * @param nonce
     * @param msgSignature
     * @param appid
     * @param postData
     * @return
     */
    @RequestMapping("/{appid}/callback")
    public String callback(@RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce,
                           @RequestParam("msg_signature") String msgSignature, @PathVariable String appid, @RequestBody String postData) {
        /**在日志中打印所有参数方便确认*/
        System.out.println("微信审核回调");
        System.out.println("callBack----timestamp========="+timestamp);
        System.out.println("callBack----nonce========="+nonce);
        System.out.println("callBack----msg_signature========="+msgSignature);
        System.out.println("callBack----appid========="+appid);
        System.out.println("callBack----postData========="+postData);

        WXBizMsgCrypt pc;
        try {
            pc = new WXBizMsgCrypt("FBDctXZiRWQYjrXMm5txJPAFWsEdkYnc", "FBDctXZiRWQYjrXMm5txJPAFWsEdkYnc7wud647ryu1", componentAppid);
            /**对xml进行解密*/
            String resultData = pc.DecryptMsg(msgSignature, timestamp, nonce, postData);

            System.out.println("callBack----resultData========="+resultData);
        } catch (Exception e) {
            //todo
        }
        return "success";
    }

//    callBack----timestamp=========1531313697
//    callBack----nonce=========1253080875
//    callBack----msg_signature=========2f7970efe4b931fda3a7302b2dba3dee4f066cda
//    callBack----appid=========wx43020a9d04042b56
//    callBack----postData=========<xml>
//    <ToUserName><![CDATA[gh_526a327ebd0d]]></ToUserName>
//    <Encrypt><![CDATA[Ve9qceR0/K6QX0cSxLmVZzCfBoJx2ivzEMF0CtgCzW9+/XX5AhE8nSC2mvLZYWzbsK6GLoInLAy3G6dD7+Tf3PT0Q15G7vAbIUpVAWp6NPohWok4vzHdWh4YYbF5Mm9m5YTT53SGXfzfCNqV1ewU+HkkpXgH7yxE6AHGtA0Ddx2EusGrBL5S/x0u1lDJLvwiIki86taprMy4PLM65SdMlsKi+WygjIHE7RLAYflm2lUgh/CxVrh290fMZ/F/0R2YWojY0EGICJNBf3xQ5Q2NsyZWq6Xo9GqJrZPiuYVhLRPstUBDeJNWMSvyo4iQW5eb/1OhvzE/fUHnmAGsxS6SyVTfcmfYj22c9hr6NXbE3dP+kmlBDkNmffPthGUTR+uz08dfzGWM+G36G8/IXVgIANAq9HYvQ3k+Wuvqf9eaGtr245I82IB8VpC2ABYXxGjE2DhLrfoMH9nQnAzDspOrrw==]]></Encrypt>
//</xml>
//
//    callBack----resultData=========<xml><ToUserName><![CDATA[gh_526a327ebd0d]]></ToUserName>
//<FromUserName><![CDATA[oePJ65dA7l3M1edSeF-Ep0Vng3nQ]]></FromUserName>
//<CreateTime>1531313697</CreateTime>
//<MsgType><![CDATA[event]]></MsgType>
//<Event><![CDATA[weapp_audit_success]]></Event>
//<SuccTime>1531313697</SuccTime>
//</xml>

}
