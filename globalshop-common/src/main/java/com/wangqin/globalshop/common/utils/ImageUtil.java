package com.wangqin.globalshop.common.utils;



public class ImageUtil {

	/*
	 * 获取存储的图片URL
	 */
	public static String getImageUrl(String url){
		if(StringUtils.isNotBlank(url)){
			String nJson = url.replace("&quot;", "\"");
			if(nJson.startsWith("{\"picList\":[]")){
				return null;
			}else{
				return nJson;
			}
		}else{
			return null;
		}
		
	}
}
