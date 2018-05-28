package com.wangqin.globalshop.common.utils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 用于前端页面上传图片
 * 
 * @author Administrator
 */
public class UploadUtil {

    /**
     * @param request
     * @param saveDir 保存的图片目录，不同的业务存放不同的目录里面
     * @return
     */
    public static Map<String, String> upload(HttpServletRequest request, String saveDir) {
        Map<String, String> map = new HashMap<String, String>();
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 临时图片名称
        String ls_fileName = "";
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 图片名称
                ls_fileName = iter.next();
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(ls_fileName);
                String filePath = upload(file, saveDir);
                map.put(ls_fileName, filePath);
            }
        }
        return map;
    }

    public static String upload(MultipartFile file, String saveDir) {
        if (file == null) {
            return null;
        }
        // 取得当前上传文件的文件名称
        String myFileName = file.getOriginalFilename();
        // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
        if ((myFileName.trim()).equals("")) {
            return null;
        }
        File uploadFile = null;
        // 获取图片格式
        String type = myFileName.substring(myFileName.lastIndexOf('.') + 1, myFileName.length());
        // 存至数据库的图片name
        long time = System.currentTimeMillis();
        String imgName = time + RandomStringUtils.randomNumeric(6) + "." + type;
        // 定义上传路径
        String dir = TimeUtil.getDateString(new Date(), TimeUtil.DEFAULT_DAY_FORMAT).replace("-", "");
        String savePath = saveDir + File.separator + dir;
        String filePath = savePath + File.separator + imgName;
        uploadFile = new File(ResourceUtil.getValue("imageUploadPath") + savePath);
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }
        uploadFile = new File(ResourceUtil.getValue("imageUploadPath") + filePath);
        if (null != uploadFile && uploadFile.exists()) {
            uploadFile.delete();
        }
        try {
            file.transferTo(uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return filePath;
    }
}
