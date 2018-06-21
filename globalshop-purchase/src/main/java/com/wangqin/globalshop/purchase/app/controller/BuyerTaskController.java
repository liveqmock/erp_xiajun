package com.wangqin.globalshop.purchase.app.controller;

import com.wangqin.globalshop.biz1.app.Exception.ErpCommonException;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.excel.ReadExcel;
import com.wangqin.globalshop.purchase.app.service.IBuyerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/19
 */
@Controller
@ResponseBody
@RequestMapping("/purchaseTask")
public class BuyerTaskController {
    @Autowired
    private IBuyerTaskService buyerTaskService;

    @RequestMapping("/improtTask")
    public Object importTask(HttpServletRequest request, MultipartFile file) {
        JsonResult<Object> result = new JsonResult<>();
        try {
            if (!file.isEmpty()) {
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
                        + file.getOriginalFilename();
                File baseFile = new File(filePath);
                file.transferTo(baseFile);
                List<List<Object>> list = ReadExcel.readExcel(baseFile, 1, 0, 12);
                buyerTaskService.importTask(list);
            }

        } catch (IOException e) {
            return result.buildIsSuccess(false).buildMsg("文件上传错误，请重试");
        } catch (ErpCommonException e) {
            return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
        }
        return result.buildIsSuccess(true).buildMsg("上传成功");

    }

}
