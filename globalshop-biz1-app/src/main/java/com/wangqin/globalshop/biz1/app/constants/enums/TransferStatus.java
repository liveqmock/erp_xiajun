package com.wangqin.globalshop.biz1.app.constants.enums;

public enum TransferStatus {
	UNPREDICT(0, "未预报"),
	PREDICT_FAILED(1, "预报失败"),
	PREDICTED(10, "预报成功"),
	CREATED_ORDER(20, "创建订单成功");
	
	private int value;
	private String desc;
	
	private TransferStatus(int value,String desc) {
		this.setValue(value);
		this.setDesc(desc);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
