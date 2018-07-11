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
}
