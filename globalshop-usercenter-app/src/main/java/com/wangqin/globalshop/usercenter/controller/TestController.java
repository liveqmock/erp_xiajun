package com.wangqin.globalshop.usercenter.controller;

import com.wangqin.globalshop.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description：测试Controller
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    /**
     * 图标测试
     * @return
     */
    @GetMapping("/dataGrid")
    public String dataGrid() {
        return "admin/test";
    }

}
