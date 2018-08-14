package com.wangqin.globalshop.usercenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AppletConfigDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.PublishStatus;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.WxPay.PayUtil;
import com.wangqin.globalshop.usercenter.service.IAppletConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author biscuits
 * 小程序配置类
 */
@Service
public class AppletConfigServiceImplement implements IAppletConfigService {
    @Autowired
    private AppletConfigDOMapperExt mapper;

    @Override
    public void insert(AppletConfigDO applet) {
        mapper.insertSelective(applet);

    }

    @Override
    public AppletConfigDO selectByCompanyNoAndType(String companyNo, String type) {
        return mapper.selectByCompanyNoAndType(companyNo, type);
    }

    @Override
    public List<AppletConfigDO> list() {
        return mapper.list();
    }

    @Override
    public void update(AppletConfigDO applet) {
        mapper.updateByPrimaryKeySelective(applet);

    }

    @Override
    public AppletConfigDO selectByAppid(String appid) {
        return mapper.selectByAppid(appid);
    }

    @Override
    public List<AppletConfigDO> selectByPublishStatus(int publishStatus) {
        return mapper.selectByPublishStatus(publishStatus);
    }

    @Override
    public List<AppletConfigDO> selectByType(String type) {
        return mapper.selectByType(type);
    }

    @Override
    public List<String> publish(String appids, Integer templateId, String userDesc, String userVersion) {
        List<String> appidList = JSON.parseArray(appids, String.class);
        List<String> erroMsg = new ArrayList<>();
        for (String appid : appidList) {
            try {
                publishApplet(templateId, appid, userDesc, userVersion);
            } catch (IOException | ErpCommonException e) {
                erroMsg.add("小程序appid:"+appid+"发布失败");
            }

        }
        return erroMsg;
    }


    private void publishApplet(Integer templateId, String appid, String userDesc, String userVersion) throws IOException {
        AppletConfigDO applet = mapper.selectByAppid(appid);
        if (applet == null) {
            throw new ErpCommonException("找不到对应的小程序配置");
        }

        String authorizerAccessToken = applet.getAuthorizerAccessToken();
        String url = "https://api.weixin.qq.com/wxa/commit?access_token=" + authorizerAccessToken;
        String imgUrl = "https://api.weixin.qq.com/wxa/get_qrcode?access_token=${token}";
        String str = System.currentTimeMillis() + "";
        str = str.substring(3, 13);
        //language=JSON
        String param = "{\"template_id\":" + templateId + ",\n" + "  \"user_version\": "+userVersion+",\n" + "  \"user_desc\": "+userDesc+",\n" + "  \"ext_json\": \"{\\\"extEnable\\\":true,\\\"extAppid\\\":\\\"" + appid + "\\\",\\\"ext\\\":{\\\"userAppId\\\":\\\"" + appid + "\\\"}}\"\n" + "}";
        //language=JSON
        /*发布所有的满足条件的小程序的体验版  并返回二维码图片  保存到数据库中*/
        String post = PayUtil.httpRequest(url, "POST", param);
        if (!post.contains("ok")){
            throw new ErpCommonException("小程序发布失败");
        }
        applet.setTempletId(templateId);
        applet.setPublishStatus(PublishStatus.SUBMITTED.getCode());
        mapper.updateByPrimaryKeySelective(applet);
    }

}