package com.wangqin.globalshop.pay.controller;

import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import com.wangqin.globalshop.pay.constant.ReturnCodeEnum;
import com.wangqin.globalshop.pay.dto.Exts;
import com.wangqin.globalshop.pay.dto.PayNotifyParam;
import com.wangqin.globalshop.pay.dto.SharingReqItem;
import com.wangqin.globalshop.pay.service.PayService;
import com.wangqin.globalshop.pay.util.ShengpayUtil;
import net.sf.cglib.beans.BeanMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private IMallOrderService mallOrderService;

    /**
     * 创建支付订单
     *
     * @param merchantOrderNo 商户系统内的唯一订单号，商户订单号不能重复
     * @param userIp          用户IP（用户下单时的IP-公网IP,银联交易必填）
     * @param exts            扩展属性,JSON串（微信H5 、微信小程序、微信APP必传）
     * @return
     */
    @PostMapping("/orderPay")
    private Object orderPay(String merchantOrderNo, String userIp, Exts exts) {
        JsonResult result = new JsonResult();
        try {
            // 获取订单金额和商品信息
            MallOrderDO mallOrderDO = mallOrderService.selectByOrderNo(merchantOrderNo);
            Double totalAmount = mallOrderDO.getTotalAmount();
            String amount = String.valueOf(totalAmount);
            // 暂时用店名充当商品名
            String productName = mallOrderDO.getDealerName();
            // 创建支付订单
            payService.orderPay(merchantOrderNo, amount, productName, userIp, exts);
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
     * @param merchantOrderNo
     * @param exts
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
     * @param merchantOrderNo
     * @param exts
     * @return
     */
    @PostMapping("/refundPay")
    public Object refundPay(String merchantOrderNo, String exts) {
        JsonResult result = new JsonResult();
        try {
            payService.refundPay(merchantOrderNo, exts);
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
     * @param merchantOrderNo
     * @param exts
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
     * @param merchantOrderNo
     * @param exts
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
     * @param merchantOrderNo
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

    /**
     * 支付通知接口，盛付通主动调用
     *
     * @param request
     * @return 处理成功返回OK，否则返回FAIL
     */
    @PostMapping("/payNotify")
    public String payNotify(HttpServletRequest request) {
        try {
            payService.payNotify(request);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnCodeEnum.FAIL.getCode();
        }
        return ReturnCodeEnum.OK.getCode();
    }

    /**
     * 退款通知，盛付通主动调用
     *
     * @param request
     * @return 处理成功返回OK，否则返回FAIL
     */
    @PostMapping("/refundNotify")
    public String refundNotify(HttpServletRequest request) {
        try {
            payService.refundNotify(request);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnCodeEnum.FAIL.getCode();
        }
        return ReturnCodeEnum.OK.getCode();
    }

}
