package com.wangqin.globalshop.channel.dal.youzan;

import com.youzan.open.sdk.model.ByteWrapper;

public class ItemImages {

	private ByteWrapper[] byteWrappers;
	
	private String imageIds;

	public ByteWrapper[] getByteWrappers() {
		return byteWrappers;
	}

	public void setByteWrappers(ByteWrapper[] byteWrappers) {
		this.byteWrappers = byteWrappers;
	}

	public String getImageIds() {
		return imageIds;
	}

	public void setImageIds(String imageIds) {
		this.imageIds = imageIds;
	}
	
}
