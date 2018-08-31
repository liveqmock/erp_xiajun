package com.wangqin.globalshop.item.app.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.util.UploadFileUtil;


/**
 * 文件上传到海狐的图片服务器
 * @author xiajun
 *
 */
@Controller
public class ItemUploadFileController {
	
	/**
	 * 图片上传接口
	 * @return
	 */
	@PostMapping("/uploadFile/picUpload")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public 	Object picUpload(MultipartFile pic) {
		JsonResult<String> result = new JsonResult<>();
		String picUrl = "";
		if (pic.isEmpty()) {
			result.buildIsSuccess(false).buildMsg("没有文件");
		}
		String fileName = pic.getOriginalFilename();
		String end = fileName.substring(fileName.lastIndexOf(".")+1);
		StringBuilder sb = new StringBuilder();
		sb.append((int)(Math.random()*100)).append("_").append(System.currentTimeMillis())
		.append(".").append(end);			
		
		String picKey = sb.toString();			
		InputStream inputStream=null;
		try {
			inputStream = pic.getInputStream();
		} catch (IOException e) {
			result.buildIsSuccess(false).buildMsg("文件错误");
		}
		if (inputStream != null) {
			picUrl = UploadFileUtil.uploadImg(inputStream, picKey);	
			result.setData(picUrl);
		}			
		 
		return result.buildIsSuccess(true);
	}
	
	
}
