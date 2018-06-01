package com.wangqin.globalshop.purchase.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxCustomerTrackDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallWxCustomerTrackDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerDOMapperExt;
import com.wangqin.globalshop.common.result.RetCode;
import com.wangqin.globalshop.common.utils.ErrorResult;
import com.wangqin.globalshop.common.utils.SingleResult;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.common.utils.WechatHelper;

@Controller
@ResponseBody
@RequestMapping("/api/wx/purchaseLogin")
public class ApiLoginController {

    @Autowired
    BuyerDOMapperExt buyerDOMapperExt;
    @Autowired
    MallWxCustomerTrackDOMapper mallWxCustomerTrackDOMapper;

    /**
     * 小程序登录
     * 
     * @param code
     * @return
     */
    @RequestMapping("/getXcxCookieId")
    @ResponseBody
    public Object getXcxCookieId(String code) {
        if (StringUtil.isBlank(code)) {
            return new ErrorResult(RetCode.ILLEGAL_ARGS, "参数错误").toJSONString();
        }
        Map<String, String> loginResult = WechatHelper.xcxLogin(code);
        if (loginResult == null) {
            return new ErrorResult(RetCode.SYS_ERROR, "登录微信服务器失败").toJSONString();
        }
        return new SingleResult<>(loginResult).toJSONString();
    }

    /**
     * 更新用户信息
     * 
     * @param wxPurchaseUser
     * @return
     */
    @RequestMapping("/setUserInfo")
    @ResponseBody
    public Object setUserInfo(BuyerDO wxPurchaseUser, String xcxCookieId) {
        if (StringUtil.isBlank(xcxCookieId) || "undefined".equals(xcxCookieId) || "null".equals(xcxCookieId)) {
            return new ErrorResult(RetCode.ILLEGAL_ARGS);
        }
        BuyerDO selWxPurchaseUser = buyerDOMapperExt.selectByOpenId(xcxCookieId);
        Date nowTime = new Date();
        int result = 0;
        if (selWxPurchaseUser != null) {
            wxPurchaseUser.setId(selWxPurchaseUser.getId());
            wxPurchaseUser.setLastLoginTime(nowTime);
            wxPurchaseUser.setGmtModify(nowTime);
            result = buyerDOMapperExt.updateByPrimaryKeySelective(wxPurchaseUser);
        } else {
            wxPurchaseUser.setOpenId(xcxCookieId);
            wxPurchaseUser.setFirstLoginTime(nowTime);
            wxPurchaseUser.setLastLoginTime(nowTime);
            wxPurchaseUser.setGmtCreate(nowTime);
            wxPurchaseUser.setGmtModify(nowTime);
            result = buyerDOMapperExt.insertSelective(wxPurchaseUser);
        }
        if (result > 0) {
            return new SingleResult<>(wxPurchaseUser).toJSONString();
        }
        return new ErrorResult(RetCode.SYS_ERROR).toJSONString();
    }

    /**
     * 记录用户访问页面
     * 
     * @param wxAppLaunch
     * @return
     */
    @RequestMapping("/setWxAppLaunch")
    @ResponseBody
    @Transactional
    public Object setWxAppLaunch(MallWxCustomerTrackDO wxAppLaunch) {
        wxAppLaunch.setGmtCreate(new Date());
        wxAppLaunch.setGmtModify(new Date());
        int result = mallWxCustomerTrackDOMapper.insert(wxAppLaunch);
        if (result > 0) {
            if (wxAppLaunch.getOwnerOpenId() != null) {
                BuyerDO selWxPurchaseUser = buyerDOMapperExt.selectByOpenId(wxAppLaunch.getOwnerOpenId());
                if (selWxPurchaseUser != null && selWxPurchaseUser.getReferer() == null) {
                    selWxPurchaseUser.setReferer(wxAppLaunch.getId());
                    result = buyerDOMapperExt.updateByPrimaryKeySelective(selWxPurchaseUser);
                    if (0 == result) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return new ErrorResult(RetCode.SYS_ERROR).toJSONString();
                    }
                }
            }
            return new SingleResult<>(wxAppLaunch.getId()).toJSONString();
        } else {
            return new ErrorResult(RetCode.SYS_ERROR).toJSONString();
        }
    }
}
