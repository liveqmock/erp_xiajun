package com.wangqin.globalshop.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallWxSpecialPageDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallWxBannerDOMapper;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.mall.dal.mapperExt.MallWxBannerDOMapperExt;
import com.wangqin.globalshop.mall.dal.mapperExt.MallWxSpecialPageDOMapperExt;

@Controller
@ResponseBody
@RequestMapping("/wx/main")
public class WxMainController {

    @Autowired
    MallWxBannerDOMapperExt      mallWxBannerDOMapperExt;
    @Autowired
    MallWxSpecialPageDOMapperExt mallWxSpecialPageDOMapperExt;

    @RequestMapping("/banner")
    public Object banner(String companyNo) {
        JsonResult<List<MallWxBannerDOMapper>> result = new JsonResult<>();
        if (null == companyNo) {
            result.buildIsSuccess(false).buildMsg("传入的参数有误！");
        } else {
            List<MallWxBannerDOMapper> bannerList = mallWxBannerDOMapperExt.selectByCompanyNo(companyNo);
            result.buildData(bannerList).buildIsSuccess(true);
        }
        return result;
    }
    
    @RequestMapping("/itemList")
    public Object itemList(String  companyNo) {
        JsonResult<List<ItemDO>> result = new JsonResult<>();
        if (null == companyNo) {
            result.buildIsSuccess(false).buildMsg("传入的参数有误！");
        }else {
            //
            result.buildIsSuccess(true);
        }
        return result;
    }

    @RequestMapping("/special")
    public Object special(Long specialPageId) {
        JsonResult<MallWxSpecialPageDO> result = new JsonResult<>();
        if (null == specialPageId) {
            result.buildIsSuccess(false).buildMsg("传入的参数有误！");
        } else {
            MallWxSpecialPageDO special = mallWxSpecialPageDOMapperExt.selectBySpecialPageId(specialPageId);
            result.buildData(special);
            result.buildIsSuccess(true);
        }
        return result;
    }
}
