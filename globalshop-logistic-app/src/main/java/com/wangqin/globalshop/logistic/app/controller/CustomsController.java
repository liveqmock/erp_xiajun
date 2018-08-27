package com.wangqin.globalshop.logistic.app.controller;

import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.logistic.app.bean.xml.Mo;
import com.wangqin.globalshop.logistic.app.util.XStreamUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 海关相关 controller
 *
 * @author angus
 * @date 2018/8/22
 */
@RestController
@RequestMapping("/customs")
public class CustomsController {

    @PostMapping("/result")
    public Object result(HttpServletRequest request) {
        JsonResult<Mo> result = new JsonResult<>();

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(request.getInputStream()))) {

            reader.lines().forEach(builder::append);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String body = builder.toString();
        System.out.println(body);

        Mo mo = XStreamUtil.toBean(body, Mo.class);
        System.out.println(mo);

        result.buildData(mo).setSuccess(true);
        return result;
    }
}
