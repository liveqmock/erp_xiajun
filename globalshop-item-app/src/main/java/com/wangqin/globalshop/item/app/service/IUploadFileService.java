package com.wangqin.globalshop.item.app.service;

import java.io.InputStream;

import com.aliyun.oss.model.OSSObject;

/**
 * 文件上传
 * @author zhulu
 */
public interface IUploadFileService {

	/**
	 * 上传单个图片
	 * @param inputStream
	 */
	String uploadImg(InputStream inputStream,String picKey);
	
	/**
	 * 根据key得到文件对象
	 * @param picKey
	 * @return
	 */
	OSSObject getOSSObject(String picKey);
}
