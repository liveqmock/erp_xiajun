package com.wangqin.globalshop.usercenter.service;

import com.aliyun.oss.model.OSSObject;

import java.io.InputStream;

public interface UserUploadFileService {

    /**
     * 上传单个图片
     * 
     * @param inputStream
     */
    String uploadImg(InputStream inputStream, String picKey);

    /**
     * 根据key得到文件对象
     * 
     * @param picKey
     * @return
     */
    OSSObject getOSSObject(String picKey);

}
