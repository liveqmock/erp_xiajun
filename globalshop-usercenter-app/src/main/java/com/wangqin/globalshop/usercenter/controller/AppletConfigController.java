package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.biz1.app.bean.dataVo.AppletConfigVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.common.enums.AppletType;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.common.utils.czh.ParseObj2Obj;
import com.wangqin.globalshop.usercenter.service.IAppletConfigService;
import com.wangqin.globalshop.usercenter.service.IUserCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger log = LoggerFactory.getLogger("AppletConfigController");

    @RequestMapping("/list")
    public Object list() {
        log.info("加载首页");
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
        log.info("批量发布");
        log.info("appids:" + appids);
        log.info("templateId" + templateId);
        log.info("userDesc" + userDesc);
        log.info("userVersion" + userVersion);
        JsonResult<List<String>> result = new JsonResult<>();
        List<String> list = appletConfigService.publish(appids, templateId, userDesc, userVersion);
        return result.buildData(list).buildMsg("访问成功").buildIsSuccess(true);
    }

    @PostMapping("/query")
    public Object query(String appid) {
        log.info("查询");
        log.info("appid:" + appid);
        JsonResult<AppletConfigVO> result = new JsonResult<>();
        AppletConfigDO applet = appletConfigService.selectByAppid(appid);
        AppletConfigVO appletVO = new AppletConfigVO();
        ParseObj2Obj.parseObj2Obj(applet, appletVO);
        return result.buildData(appletVO).buildMsg("访问成功").buildIsSuccess(true);
    }

    @PostMapping("/update")
    public Object update(AppletConfigVO appletConfigVO) {
        JsonResult<List<String>> result = new JsonResult<>();
        try {
            log.info("参数:" + appletConfigVO);

            AppletConfigDO applet = new AppletConfigDO();
            applet.setTempletId(appletConfigVO.getTempletId());
            applet.setId(appletConfigVO.getId());
            appletConfigService.update(applet);
        } catch (Exception e) {
            return result.buildMsg("未知异常").buildIsSuccess(false);
        }
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
        String url=null;
        JsonResult<String> result = new JsonResult<>();
        try {

            AppletConfigDO applet = appletConfigService.selectByAppid(appid);
            String token = applet.getAuthorizerAccessToken();
            url = "https://api.weixin.qq.com/wxa/get_qrcode?access_token=" + token;
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg("访问失败");
        }

        return result.buildData(url).buildIsSuccess(true).buildMsg("访问成功");

    }


}
