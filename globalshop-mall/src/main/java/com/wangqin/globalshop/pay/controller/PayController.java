package com.wangqin.globalshop.pay.controller;

import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.pay.dto.SharingReqItem;
import com.wangqin.globalshop.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人支付相关 controller
 *
 * @author angus
 * @date 2018/8/13
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService;

    /**
     * 创建支付订单
     *
     * @param merchantOrderNo
     * @param userIp
     * @param payChannel
     * @param exts
     * @return
     */
    @PostMapping("/orderPay")
    private Object orderPay(String merchantOrderNo, String userIp, String payChannel, String exts) {
        JsonResult result = new JsonResult();

        try {
            // TODO: 需从后台获取
            String amount = "";
            String productName = "";
            payService.orderPay(merchantOrderNo, amount, productName, userIp, payChannel, exts);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg())
                    .buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("未知异常")
                    .buildIsSuccess(false);
        }

        return result;
    }

    /**
     * 单笔查询
     *
     * @return
     */
    @PostMapping("/queryPay")
    public Object queryPay(String merchantOrderNo, String exts) {
        JsonResult result = new JsonResult();

        try {
            // TODO: 需从后台获取
            String sftOrderNo = "";
            payService.queryPay(merchantOrderNo, sftOrderNo, exts);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg())
                    .buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("未知异常")
                    .buildIsSuccess(false);
        }

        return result;
    }

    /**
     * 退款
     *
     * @return
     */
    @PostMapping("/refundPay")
    public Object refundPay(String merchantOrderNo, String exts) {
        JsonResult result = new JsonResult();

        try {
            // TODO: 需从后台获取
            String refundOrderNo = "";
            String refundAmount = "";
            payService.refundPay(merchantOrderNo, refundOrderNo, refundAmount, exts);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg())
                    .buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("未知异常")
                    .buildIsSuccess(false);
        }

        return result;
    }

    /**
     * 退款查询
     *
     * @return
     */
    @PostMapping("/queryRefund")
    public Object queryRefund(String merchantOrderNo, String exts) {
        JsonResult result = new JsonResult();

        try {
            // TODO: 需从后台获取
            String refundOrderNo = "";
            String refundTransNo = "";
            String sftOrderNo = "";
            payService.queryRefund(merchantOrderNo, refundOrderNo, refundTransNo, sftOrderNo, exts);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg())
                    .buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("未知异常")
                    .buildIsSuccess(false);
        }

        return result;
    }

    /**
     * 分账
     *
     * @return
     */
    @PostMapping("/sharingPay")
    public Object sharingPay(String merchantOrderNo, String exts) {
        JsonResult result = new JsonResult();

        try {
            // TODO: 需从后台获取
            String sharingOrderNo = "";
            List<SharingReqItem> sharingReqItemList = new ArrayList<>();
            payService.sharingPay(merchantOrderNo, sharingOrderNo, sharingReqItemList, exts);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg())
                    .buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("未知异常")
                    .buildIsSuccess(false);
        }

        return result;
    }

    /**
     * 分账查询
     *
     * @return
     */
    @PostMapping("/querySharing")
    public Object querySharing(String merchantOrderNo) {
        JsonResult result = new JsonResult();

        try {
            // TODO: 需从后台获取
            String sharingQueryOrderNo = "";
            String sharingType = "";
            payService.querySharing(merchantOrderNo, sharingQueryOrderNo, sharingType);
            result.buildIsSuccess(true);
        } catch (BizCommonException e) {
            result.buildMsg(e.getErrorMsg())
                    .buildIsSuccess(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            result.buildMsg("未知异常")
                    .buildIsSuccess(false);
        }

        return result;
    }
}
