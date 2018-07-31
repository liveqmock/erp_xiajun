package com.wangqin.globalshop.mall.controller;

import java.util.Date;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallCustomerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxCustomerTrackDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallWxCustomerTrackDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallCustomerDOMapperExt;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.HttpClientUtil;

import net.sf.json.JSONObject;

@Controller("wxLoginController")
@RequestMapping("/wx")
public class LoginController {

    // private static final String WXAPPID = "wxeb4a1f63dd17d0ce";
    // private static final String WXAPPSECRET = "65afbfc5d50f34e5078c36dc59256836";
    private static final String WXAPPID     = "wxdef3e972a4a93e91";
    private static final String WXAPPSECRET = "fef11f402f8e8f3c1442163155aeb65a";

    @Autowired
    MallCustomerDOMapperExt mallCustomerDOMapperExt;
    @Autowired
    MallWxCustomerTrackDOMapper mallWxCustomerTrackDOMapper;

    @RequestMapping("/getXcxCookieId")
    @ResponseBody
    public Object getXcxCookieId(String code) {
        JsonResult<Object> result = new JsonResult<>();

        JSONObject o = HttpClientUtil.post("https://api.weixin.qq.com/sns/jscode2session?appid=" + WXAPPID + "&secret="
                                           + WXAPPSECRET + "&js_code=" + code + "&grant_type=authorization_code", null,
                                           null, "2");
        result.buildIsSuccess(true).setData(o);

        return result;
    }

    @RequestMapping("/setUserInfo")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object setUserInfo(MallCustomerDO mallCustomerDO, String xcxCookieId) {
        JsonResult<MallCustomerDO> result = new JsonResult<>();
        if (StringUtil.isBlank(xcxCookieId) || "undefined".equals(xcxCookieId) || "null".equals(xcxCookieId)) {
            return result;
        }
        MallCustomerDO selWxUser = mallCustomerDOMapperExt.selectByOpenId(xcxCookieId);
        Date nowTime = new Date();
        if (selWxUser != null) {
            mallCustomerDO.setId(selWxUser.getId());
            mallCustomerDO.setLastLoginTime(nowTime);
            mallCustomerDO.setGmtModify(nowTime);
            mallCustomerDOMapperExt.updateByPrimaryKeySelective(mallCustomerDO);
            result.buildData(selWxUser);
        } else {
            mallCustomerDO.setOpenId(xcxCookieId);
            mallCustomerDO.setFirstLoginTime(nowTime);
            mallCustomerDO.setLastLoginTime(nowTime);
            mallCustomerDO.setGmtCreate(nowTime);
            mallCustomerDO.setGmtModify(nowTime);
            mallCustomerDOMapperExt.insert(mallCustomerDO);
            result.buildData(mallCustomerDO);
        }
        result.buildIsSuccess(true);
        return result;
    }

    @RequestMapping("/setWxAppLaunch")
    @ResponseBody
    public Object setWxAppLaunch(MallWxCustomerTrackDO wxAppLaunch) {
        wxAppLaunch.setGmtCreate(new Date());
        wxAppLaunch.setGmtModify(new Date());
        JsonResult<Long> result = new JsonResult<>();
        mallWxCustomerTrackDOMapper.insertSelective(wxAppLaunch);
        result.buildIsSuccess(true).buildData(wxAppLaunch.getId());
        return result;
    }
}
