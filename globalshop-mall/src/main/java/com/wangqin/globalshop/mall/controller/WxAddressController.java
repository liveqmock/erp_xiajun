package com.wangqin.globalshop.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangqin.globalshop.biz1.app.dal.mapper.MallShippingAddressDOMapper;

@Controller
@RequestMapping("/wx/address")
public class WxAddressController {

    @Autowired
    MallShippingAddressDOMapper mallShippingAddressDOMapper;
    
    
}
