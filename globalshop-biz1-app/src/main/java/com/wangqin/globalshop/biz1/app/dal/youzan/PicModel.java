package com.wangqin.globalshop.biz1.app.dal.youzan;

import java.util.List;

public class PicModel {

	
	private String mainPicNum;
	
	private List<PicList> picList;
	
	
	
	public String getMainPicNum() {
		return mainPicNum;
	}



	public void setMainPicNum(String mainPicNum) {
		this.mainPicNum = mainPicNum;
	}







	public List<PicList> getPicList() {
		return picList;
	}



	public void setPicList(List<PicList> picList) {
		this.picList = picList;
	}







	public static class PicList{
		private String type;
		private String uid;
		private String url;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
		
	}
}
