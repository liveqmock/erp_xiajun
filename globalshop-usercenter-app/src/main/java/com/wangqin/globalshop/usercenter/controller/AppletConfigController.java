package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.biz1.app.bean.dataVo.AppletConfigVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.common.enums.AppletType;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.common.utils.czh.ParseObj2Obj;
import com.wangqin.globalshop.usercenter.service.IAppletConfigService;
import com.wangqin.globalshop.usercenter.service.IUserCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/08/13
 */
@RestController
@RequestMapping("/wechatApplet")
public class AppletConfigController {
    @Autowired
    private IAppletConfigService appletConfigService;
    @Autowired
    private IUserCompanyService companyService;

    @RequestMapping("/list")
    public Object list() {
        JsonResult<List<AppletConfigVO>> result = new JsonResult<>();
        List<AppletConfigVO> list = new ArrayList();
        try {


            List<AppletConfigDO> applets = appletConfigService.selectByType(AppletType.MALL_APPLET.getValue());
            for (AppletConfigDO applet : applets) {
                AppletConfigVO appletConfigVO = new AppletConfigVO();
                ParseObj2Obj.parseObj2Obj(applet, appletConfigVO);
                CompanyDO companyDO = companyService.selectByCompanyNo(applet.getCompanyNo());
                appletConfigVO.setCompanyName(companyDO.getCompanyName());
                list.add(appletConfigVO);
            }
        } catch (Exception e) {
            return result.buildMsg("访问失败").buildIsSuccess(false);
        }
        return result.buildData(list).buildMsg("访问成功").buildIsSuccess(true);
    }

    @PostMapping("/publish")
    public Object publish(String appids, Integer templateId, String userDesc, String userVersion) {
        JsonResult<List<String>> result = new JsonResult<>();
        List<String> list = appletConfigService.publish(appids, templateId, userDesc, userVersion);
        return result.buildData(list).buildMsg("访问成功").buildIsSuccess(true);
    }

    @PostMapping("/update")
    public Object update(AppletConfigVO appletConfigVO) {
        JsonResult<List<String>> result = new JsonResult<>();
        AppletConfigDO applet = new AppletConfigDO();
        applet.setTempletId(appletConfigVO.getTempletId());
        applet.setId(appletConfigVO.getId());
        appletConfigService.update(applet);
        return result.buildMsg("访问成功").buildIsSuccess(true);
    }

    /**
     * 获取体验版二维码
     *
     * @param appid
     * @return
     */
    @RequestMapping("/getUrl")
    public Object getUrl(String appid) {
        JsonResult<String> result = new JsonResult<>();
        AppletConfigDO applet = appletConfigService.selectByAppid(appid);
        String token = applet.getAuthorizerAccessToken();
        String url = "https://api.weixin.qq.com/wxa/get_qrcode?access_token=" + token;
        return result.buildData(url).buildIsSuccess(true).buildMsg("访问成功");

    }


}
