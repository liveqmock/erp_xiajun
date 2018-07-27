package com.wangqin.globalshop.biz1.app.enums;

public enum ShippingOrderType {
	INTAX(1, "BSX"),	//包税线
	CARD(2, "身份证线"),
	BC(3, "BC线"),
	USAP(4, "USA-P"),	//运通
	USAC(5, "USA-C"),	//运通
	YOUKEFOOD(6, "邮客食品线"),		//邮客食品线
	YOUKEMILK(7, "邮客奶粉线"),		//邮客奶粉线
    FPXA(8, "4PX经济A线"),		    //4PX经济A线
    FPXB(9, "4PX经济B线");		    //4PX经济B线
	
	private int code;
    private String description;
    
    ShippingOrderType(int code,String description){
    	this.code = code;
    	this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    public static ShippingOrderType of(int code) {
        for (ShippingOrderType status : ShippingOrderType.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("invalid status code " + code);
    }
}
