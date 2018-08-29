package com.wangqin.globalshop.logistic.app.controller;

import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.logistic.app.constant.BusinessType;
import com.wangqin.globalshop.logistic.app.bean.xml.Mo;
import com.wangqin.globalshop.logistic.app.util.XStreamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(CustomsController.class);

    /**
     * 接收海关回执的接口
     *
     * @param request
     * @return
     */
    @PostMapping("/result")
    public Object result(HttpServletRequest request) {
        JsonResult<Mo> result = new JsonResult<>();

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(request.getInputStream()))) {
            // 获取请求体信息（回执报文）
            reader.lines().forEach(builder::append);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String reqBody = builder.toString();
        logger.info("body: {}", reqBody);

        Mo mo = XStreamUtil.toBean(reqBody, Mo.class);
        logger.info("mo: {}", mo);

        // 根据回执报文类型调用相应业务进行处理
        switch (mo.getHead().getBusinessType()) {
            case BusinessType.IMPORTORDER:
                break;
            case BusinessType.PERSONAL_GOODS_DECLAR:
                break;
            case BusinessType.RESULT:
                break;
            default:
        }

        result.buildData(mo).setSuccess(true);
        return result;
    }
}
