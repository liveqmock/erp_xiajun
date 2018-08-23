package com.wangqin.globalshop.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.wangqin.globalshop.common.base.BaseDto;
import net.sf.json.JSONObject;


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
	
	/**
	 * 拼装商品的主图JSON
	 */
	public static String assempleMainPic(List<String> urlList) {
		PicModel mPic = new PicModel();
		if(EasyUtil.isListEmpty(urlList)){
			return BaseDto.toString(mPic);
		}
		mPic.setMainPicNum("1");
		List<PicModel.PicList> picList = new ArrayList<PicModel.PicList>();
		int index = 0;
		for (String url:urlList) {
			PicModel.PicList pList = new PicModel.PicList();
			pList.setType("image/jpeg");
			pList.setUrl(url);
			pList.setUid("i_" + index++);
			picList.add(pList);
		}
		mPic.setPicList(picList);
		return BaseDto.toString(mPic);
	}
	
	
}
