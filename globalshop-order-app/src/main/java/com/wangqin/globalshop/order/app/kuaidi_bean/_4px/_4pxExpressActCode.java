package com.wangqin.globalshop.order.app.kuaidi_bean._4px;

import org.springframework.util.StringUtils;

public enum _4pxExpressActCode {
AO("AO","到达海外仓库","WO","系统产生",LogisticsStatus.OVERSEA_REPOSITORY_STOCK_IN),
	
	
	LO("LO","离开海外仓库","WO","系统产生",LogisticsStatus.SENDING_TO_CHINA),

	TDWF("TDWF", "转运延迟-等待航班", "IL", "人工补充",LogisticsStatus.SENDING_TO_AIRPORT),
	FT("FT","航班起飞","IL","系统产生",LogisticsStatus.AIRLINE_TO_CHINA),
	DA("DA","货物离开起运港","IL","系统产生",LogisticsStatus.AIRLINE_TO_CHINA),
	FL("FL","航班降落","IL","系统产生",LogisticsStatus.ARRIVE_CHINA_AIRPORT),
	AA("AA","货物到达港口","IL","系统产生",LogisticsStatus.ARRIVE_CHINA_AIRPORT),
	TRM("TRM", "正在送往清关口岸", "IL", "系统产生",LogisticsStatus.ARRIVE_CHINA_AIRPORT),
	PTW("PTW", "包裹从机场提取，转往海关监管仓", "IL", "系统产生",LogisticsStatus.ARRIVE_CHINA_AIRPORT), // 9274892700465505423714
	PTCW("PTCW", "包裹从机场提取，转往海关监管仓", "IL", "系统产生",LogisticsStatus.ARRIVE_CHINA_AIRPORT),
	PTATW("PTATW", "包裹从港口提取,转往海关监管仓库", "IL", "系统产生",LogisticsStatus.ARRIVE_CHINA_AIRPORT), // 625019702392
	
	CC("CC","等候海关查验","ID","系统产生",LogisticsStatus.CLEARANCE_DOING),
	CP("CP","货物正在清关，等候海关放行","ID","系统产生",LogisticsStatus.CLEARANCE_DOING),
	HC("HC","海关扣件","ID","系统产生",LogisticsStatus.CLEARANCE_DOING), // 443550161180
	CCE("CCE","已清关完成","ID","系统产生",LogisticsStatus.CLEARANCE_DONE), // RC
	TD("TD","转运延迟","IL","人工补充",LogisticsStatus.AIRLINE_TO_CHINA),
	IS("IS","已交接国内派送服务商","IL","系统产生",LogisticsStatus.CHINA_DISPATCHED),
	
	STT01("STT01","收件","ID","外部配送商提供,在此分类下会有多条明细",LogisticsStatus.CHINA_DISPATCHED),
	STT02("STT02","发件","ID","外部配送商提供,在此分类下会有多条明细",LogisticsStatus.CHINA_DISPATCHED),
	STT03("STT03","到件","ID","外部配送商提供,在此分类下会有多条明细",LogisticsStatus.CHINA_DISPATCHED),
	STT04("STT04","派件","ID","外部配送商提供,在此分类下会有多条明细",LogisticsStatus.CHINA_DISPATCHED),
	STT05("STT05","装袋","ID","外部配送商提供,在此分类下会有多条明细",LogisticsStatus.CHINA_DISPATCHED),
	STT06("STT06","拆袋","ID","外部配送商提供,在此分类下会有多条明细",LogisticsStatus.CHINA_DISPATCHED),
	STT07("STT07","疑难件","ID","外部配送商提供,在此分类下会有多条明细",LogisticsStatus.CHINA_DISPATCHED),
	STT08("STT08","货件留仓","ID","外部配送商提供,在此分类下会有多条明细",LogisticsStatus.CHINA_DISPATCHED),
	STT09("STT09","一票多件","ID","外部配送商提供,在此分类下会有多条明细",LogisticsStatus.CHINA_DISPATCHED),
	STT10("STT10","发包","ID","外部配送商提供,在此分类下会有多条明细",LogisticsStatus.CHINA_DISPATCHED),
	
	STT99("STT99","已签收","TD","系统产生",LogisticsStatus.SIGNED),
	;
	
	private String code;//业务状态代码
	private String name;//业务状态中文名称
	private String stepCode;//业务环节代码
	private String stepMode;//产生方式
	private LogisticsStatus logisticsStatus ;//对应我们的关键节点
	
	private  _4pxExpressActCode(String code,String name,String stepCode,String stepMode,LogisticsStatus logisticsStatus){
		this.code = code ;
		this.name = name ;
		this.stepCode = stepCode ;
		this.stepMode = stepMode ;
		this.logisticsStatus = logisticsStatus ;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStepCode() {
		return stepCode;
	}
	public void setStepCode(String stepCode) {
		this.stepCode = stepCode;
	}
	public String getStepMode() {
		return stepMode;
	}
	public void setStepMode(String stepMode) {
		this.stepMode = stepMode;
	}
	public LogisticsStatus getLogisticsStatus() {
		return logisticsStatus;
	}
	public void setLogisticsStatus(LogisticsStatus logisticsStatus) {
		this.logisticsStatus = logisticsStatus;
	}
	public static _4pxExpressActCode get4pxExpressActCodeByCode(String code){
		if (StringUtils.isEmpty(code)) {
			return IS;
		}
		for(_4pxExpressActCode v: _4pxExpressActCode.values()) {
			if(code.equals(v.getCode())) {
				return v;
			}
		}
		return null;
    }
	
	public static _4pxExpressActCode get4pxExpressActCodeByName(String name) {
		if (name == null) {
			return null;
		}
		for (_4pxExpressActCode v: _4pxExpressActCode.values()) {
			if (name.equals(v.name)) {
				return v;
			}
		}
		return null;
	}
}
