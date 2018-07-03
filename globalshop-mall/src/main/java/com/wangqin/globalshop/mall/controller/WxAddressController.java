package com.wangqin.globalshop.mall.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.constants.enums.YesOrNoEnum;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallShippingAddressDO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.mall.dal.mapperExt.MallShippingAddressDOMapperExt;

@Controller
@RequestMapping("/wx/address")
public class WxAddressController {

    @Autowired
    MallShippingAddressDOMapperExt mallShippingAddressDOMapperExt;

    @RequestMapping("/addMallShippingAddressDO")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object addMallShippingAddressDO(MallShippingAddressDO MallShippingAddressDO) {
        JsonResult<MallShippingAddressDO> result = new JsonResult<>();
        Integer addressCountInteger = mallShippingAddressDOMapperExt.countMallShippingAddressDOByOpenId(MallShippingAddressDO.getOpenId());
        if (addressCountInteger == 0) {
            MallShippingAddressDO.setIsDefault((byte) YesOrNoEnum.YES.getCode());
            MallShippingAddressDO.setGmtCreate(new Date());
            MallShippingAddressDO.setGmtModify(new Date());
            mallShippingAddressDOMapperExt.insertSelective(MallShippingAddressDO);
            result.buildData(MallShippingAddressDO).buildIsSuccess(true);
        } else if (MallShippingAddressDO.getIsDefault() == YesOrNoEnum.YES.getCode()) {
            mallShippingAddressDOMapperExt.updateMallShippingAddressDOIsDefault(MallShippingAddressDO.getOpenId());
            MallShippingAddressDO.setGmtCreate(new Date());
            MallShippingAddressDO.setGmtModify(new Date());
            mallShippingAddressDOMapperExt.insertSelective(MallShippingAddressDO);
            result.buildIsSuccess(true).buildData(MallShippingAddressDO);
        } else {
            MallShippingAddressDO.setGmtCreate(new Date());
            MallShippingAddressDO.setGmtModify(new Date());
            mallShippingAddressDOMapperExt.insertSelective(MallShippingAddressDO);
            result.buildIsSuccess(true).buildData(MallShippingAddressDO);
        }
        return result;
    }

    @RequestMapping("/queryDeliveryAddressByOpenId")
    @ResponseBody
    public Object queryDeliveryAddressByOpenId(String openId) {
        JsonResult<List<MallShippingAddressDO>> result = new JsonResult<>();
        if (openId != null) {
            List<MallShippingAddressDO> MallShippingAddressDOesList = mallShippingAddressDOMapperExt.selectByOpenId(openId);
            result.buildData(MallShippingAddressDOesList).buildIsSuccess(true);
        } else {
            result.buildIsSuccess(false).buildMsg("传入的参数有误！");
        }
        return result;
    }

    @RequestMapping("/queryMallShippingAddressDOById")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object queryMallShippingAddressDOById(String openId, Integer id) {
        JsonResult<MallShippingAddressDO> result = new JsonResult<>();
        if (id == null) {
            MallShippingAddressDO selMallShippingAddressDO = mallShippingAddressDOMapperExt.selectByDefaultAndOpenId(YesOrNoEnum.YES.getCode(),
                                                                                                                     openId);
            result.buildData(selMallShippingAddressDO).buildIsSuccess(true);
        } else {
            MallShippingAddressDO MallShippingAddressDO = mallShippingAddressDOMapperExt.selectByPrimaryKey(id);
            result.buildData(MallShippingAddressDO).buildIsSuccess(true);
        }
        return result;
    }

    @RequestMapping("/updateMallShippingAddressDOById")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object updateMallShippingAddressDOById(MallShippingAddressDO MallShippingAddressDO) {
        JsonResult<String> result = new JsonResult<>();
        MallShippingAddressDO selMallShippingAddressDO = mallShippingAddressDOMapperExt.selectByPrimaryKey(MallShippingAddressDO.getId());

        if ((MallShippingAddressDO.getIsDefault()) == YesOrNoEnum.YES.getCode()) {
            mallShippingAddressDOMapperExt.updateMallShippingAddressDOIsDefault(MallShippingAddressDO.getOpenId());
            MallShippingAddressDO.setGmtModify(new Date());
            mallShippingAddressDOMapperExt.updateByPrimaryKeySelective(MallShippingAddressDO);
            result.buildIsSuccess(true).buildData("更新成功！");
        } else if (selMallShippingAddressDO.getIsDefault() == YesOrNoEnum.YES.getCode()) {
            MallShippingAddressDO.setGmtModify(new Date());
            mallShippingAddressDOMapperExt.updateByPrimaryKeySelective(MallShippingAddressDO);
            mallShippingAddressDOMapperExt.updateMallShippingAddressDOIsDefaultByGmtCreate(MallShippingAddressDO.getOpenId());
            result.buildIsSuccess(true).buildData("更新成功！");
        } else {
            MallShippingAddressDO.setGmtModify(new Date());
            mallShippingAddressDOMapperExt.updateByPrimaryKeySelective(MallShippingAddressDO);
            result.buildIsSuccess(true).buildData("更新成功！");
        }
        return result;
    }

    @RequestMapping("/deleteMallShippingAddressDOById")
    @ResponseBody
    @Transactional(rollbackFor = ErpCommonException.class)
    public Object deleteMallShippingAddressDOById(Integer id) {
        JsonResult<String> result = new JsonResult<>();
        if (id != null) {
            MallShippingAddressDO MallShippingAddressDO = mallShippingAddressDOMapperExt.selectByPrimaryKey(id);
            mallShippingAddressDOMapperExt.deleteByPrimaryKey(id);
            if (MallShippingAddressDO.getIsDefault() != null
                && MallShippingAddressDO.getIsDefault() == YesOrNoEnum.YES.getCode()) {
                mallShippingAddressDOMapperExt.updateMallShippingAddressDOIsDefaultByGmtCreate(MallShippingAddressDO.getOpenId());
            }
            result.buildIsSuccess(true).buildData("删除成功！");
        } else {
            result.buildIsSuccess(false).buildMsg("参数错误！");
        }
        return result;
    }
}
