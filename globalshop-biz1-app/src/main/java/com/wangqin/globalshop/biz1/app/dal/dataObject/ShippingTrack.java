package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 
 *
 */
@TableName("shipping_track")
public class ShippingTrack implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/**  */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 包裹号 */
	@TableField(value = "order_no")
	private String orderNo;

	/** 主单号 */
	@TableField(value = "waybill_no")
	private String waybillNo;

	/** 第三方物流公司运单号 */
	@TableField(value = "logistic_no")
	private String logisticNo;

	/** 轨迹状态 */
	@TableField(value = "logistics_status")
	private Integer logisticsStatus;

	/** 转运包裹重量单位kg */
	private Double weight;

	/**  体积尺寸单位mm */
	private String volume;

	/** 费用 单位 人民币 */
	private Double totalfee;

	/** 接收时间 */
	@TableField(value = "receive_time")
	private Date receiveTime;

	/** 海外入库时间 */
	@TableField(value = "oversea_in_time")
	private Date overseaInTime;

	/** 海外出库(发往国内)时间 */
	@TableField(value = "oversea_out_time")
	private Date overseaOutTime;

	/** 上航班时间 */
	@TableField(value = "oversea_on_transfer_time")
	private Date overseaOnTransferTime;

	/** 抵达国内时间 */
	@TableField(value = "inland_in_time")
	private Date inlandInTime;

	/**  国内出库时间(转国内快递) */
	@TableField(value = "inland_out_time")
	private Date inlandOutTime;

	/** 国内快递公司编码 字符串 20位 */
	@TableField(value = "inland_express_company_code")
	private String inlandExpressCompanyCode;

	/** 国内快递编号 字符串 40位 */
	@TableField(value = "inland_express_num")
	private String inlandExpressNum;

	/** 用户签收时间 */
	@TableField(value = "buyer_sign_time")
	private Date buyerSignTime;

	/** 起飞地 */
	@TableField(value = "air_take_off")
	private String airTakeOff;

	/** 航空公司名称 */
	private String airlines;

	/** 航班号 */
	private String flight;

	/** 物流轨迹说明 */
	@TableField(value = "track_info")
	private String trackInfo;

	/**  */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/**  */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/**  */
	@TableField(value = "is_del")
	private Integer isDel;

	/**  */
	private String creator;

	/**  */
	private String modifier;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getWaybillNo() {
		return this.waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getLogisticNo() {
		return this.logisticNo;
	}

	public void setLogisticNo(String logisticNo) {
		this.logisticNo = logisticNo;
	}

	public Integer getLogisticsStatus() {
		return this.logisticsStatus;
	}

	public void setLogisticsStatus(Integer logisticsStatus) {
		this.logisticsStatus = logisticsStatus;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Double getTotalfee() {
		return this.totalfee;
	}

	public void setTotalfee(Double totalfee) {
		this.totalfee = totalfee;
	}

	public Date getReceiveTime() {
		return this.receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Date getOverseaInTime() {
		return this.overseaInTime;
	}

	public void setOverseaInTime(Date overseaInTime) {
		this.overseaInTime = overseaInTime;
	}

	public Date getOverseaOutTime() {
		return this.overseaOutTime;
	}

	public void setOverseaOutTime(Date overseaOutTime) {
		this.overseaOutTime = overseaOutTime;
	}

	public Date getOverseaOnTransferTime() {
		return this.overseaOnTransferTime;
	}

	public void setOverseaOnTransferTime(Date overseaOnTransferTime) {
		this.overseaOnTransferTime = overseaOnTransferTime;
	}

	public Date getInlandInTime() {
		return this.inlandInTime;
	}

	public void setInlandInTime(Date inlandInTime) {
		this.inlandInTime = inlandInTime;
	}

	public Date getInlandOutTime() {
		return this.inlandOutTime;
	}

	public void setInlandOutTime(Date inlandOutTime) {
		this.inlandOutTime = inlandOutTime;
	}

	public String getInlandExpressCompanyCode() {
		return this.inlandExpressCompanyCode;
	}

	public void setInlandExpressCompanyCode(String inlandExpressCompanyCode) {
		this.inlandExpressCompanyCode = inlandExpressCompanyCode;
	}

	public String getInlandExpressNum() {
		return this.inlandExpressNum;
	}

	public void setInlandExpressNum(String inlandExpressNum) {
		this.inlandExpressNum = inlandExpressNum;
	}

	public Date getBuyerSignTime() {
		return this.buyerSignTime;
	}

	public void setBuyerSignTime(Date buyerSignTime) {
		this.buyerSignTime = buyerSignTime;
	}

	public String getAirTakeOff() {
		return this.airTakeOff;
	}

	public void setAirTakeOff(String airTakeOff) {
		this.airTakeOff = airTakeOff;
	}

	public String getAirlines() {
		return this.airlines;
	}

	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}

	public String getFlight() {
		return this.flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public String getTrackInfo() {
		return this.trackInfo;
	}

	public void setTrackInfo(String trackInfo) {
		this.trackInfo = trackInfo;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}
