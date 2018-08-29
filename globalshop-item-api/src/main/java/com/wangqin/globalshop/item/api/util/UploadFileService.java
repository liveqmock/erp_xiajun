package com.wangqin.globalshop.item.api.util;

import java.io.InputStream;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aliyun.oss.model.OSSObject;

/**
 * 文件上传
 * @author zhulu
 */
public interface UploadFileService {

	/**
	 * 上传单个图片
	 * @param inputStream
	 */
	@RequestMapping(value = "/file/uploadImg", method = RequestMethod.POST)
	String uploadImg(@RequestBody InputStream inputStream, @RequestParam("picKey") String picKey);
	
	/**
	 * 根据key得到文件对象
	 * @param picKey
	 * @return
	 */
	@RequestMapping(value = "/file/getOSSObject", method = RequestMethod.POST)
	OSSObject getOSSObject(@RequestParam("picKey") String picKey);
}
