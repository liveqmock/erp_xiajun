package com.wangqin.globalshop.item.app.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.api.util.UploadFileFeginService;



/**
 * 文件上传
 * @author zhulu
 */
@Controller
public class ItemUploadFileController {
    //旧的service
//	@Autowired
//	private IUploadFileService uploadFileService;
	
    //新的service
	@Autowired
	private UploadFileFeginService uploadFileService;
	
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
		if(!pic.isEmpty()){
			String fileName = pic.getOriginalFilename();
			String end = fileName.substring(fileName.lastIndexOf(".")+1);
			StringBuilder sb = new StringBuilder();
//			sb.append(getUserId()).append("_").append((int)(Math.random()*100)).append("_").append(System.currentTimeMillis())
//			.append("_").append(pic.getOriginalFilename());
			sb.append((int)(Math.random()*100)).append("_").append(System.currentTimeMillis())
			.append(".").append(end);
			
			
			String picKey = sb.toString();
			
			InputStream inputStream=null;
			try {
				inputStream = pic.getInputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result.buildIsSuccess(false).buildMsg("文件错误");
			}
			if(inputStream!=null){
				picUrl = uploadFileService.uploadImg(inputStream, picKey);	
				result.setData(picUrl);
			}
		}else{
			result.buildIsSuccess(false).buildMsg("没有文件");
		}
		return result.buildIsSuccess(true);
	}
	
	
}
