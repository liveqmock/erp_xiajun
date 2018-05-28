package com.wangqin.globalshop.mall.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallShippingAddressDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallShippingAddressDOMapper;
import com.wangqin.globalshop.common.utils.JsonResult;

@Controller
@RequestMapping("/wx/address")
public class WxAddressController {

    @Autowired
    MallShippingAddressDOMapper mallShippingAddressDOMapper;
    
    
}
