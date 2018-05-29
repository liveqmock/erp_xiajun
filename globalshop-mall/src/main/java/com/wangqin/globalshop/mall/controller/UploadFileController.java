package com.wangqin.globalshop.mall.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.Md5Util;
import com.wangqin.globalshop.mall.service.MallUploadFileService;

@Controller
@RequestMapping("/wx")
public class UploadFileController {

    @Autowired
    private MallUploadFileService uploadFileService;

    /**
     * 图片上传接口
     * 
     * @return
     */
    @PostMapping("/uploadFile/picUpload")
    @ResponseBody
    public Object picUpload(MultipartFile pic, String openId) {
        JsonResult<String> result = new JsonResult<>();
        String picUrl = "";
        if (!pic.isEmpty()) {
            String fileName = pic.getOriginalFilename();
            String end = fileName.substring(fileName.lastIndexOf(".") + 1);
            StringBuilder sb = new StringBuilder();
            sb.append(Md5Util.getMD5(openId)).append("_").append(System.currentTimeMillis()).append("_").append((int) (Math.random()
                                                                                                                       * 100)).append(".").append(end);

            String picKey = sb.toString();
            InputStream inputStream = null;
            try {
                inputStream = pic.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
                result.buildIsSuccess(false).buildMsg("文件错误");
            }
            if (inputStream != null) {
                picUrl = uploadFileService.uploadImg(inputStream, picKey);
                result.setData(picUrl);
            }
        } else {
            result.buildIsSuccess(false).buildMsg("没有文件");
        }
        return result.buildIsSuccess(true);
    }
}
