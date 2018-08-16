package com.wangqin.globalshop.biz1.app.bean.dataVo;

import java.util.HashMap;
import java.util.List;

/**
 * 首页数据看板
 * @author xiajun
 *
 */
public class HomePageVO {
	private Integer todayOrderNum;//今日订单数
	private Double todayGmv;//今日gmv
	private Integer weekOrderNum;//一周订单数
	private Double weekGmv;//一周gmv
	
	private Integer waitSendOrderNum;//待发货订单数
	private Integer todaySendNum;//今日发货包裹数
	private Integer weekSendNum;//一周发货包裹数
	
	private Integer returningOrderNum;//未完成售后订单数
	
	//一周的日期和销量，这样写是为了前端处理起来方便
	private Integer firstDay;
	private Double firstSales;
	private Integer secondDay;
	private Double secondSales;
	private Integer thirdDay;
	private Double thirdSales;
	private Integer fourthDay;
	private Double fourthSales;
	private Integer fifthDay;
	private Double fifthSales;
	private Integer sixthDay;
	private Double sixthSales;
	private Integer seventhDay;
	private Double seventhSales;
			
	public Integer getTodayOrderNum() {
		return todayOrderNum;
	}

	public void setTodayOrderNum(Integer todayOrderNum) {
		this.todayOrderNum = todayOrderNum;
	}

	public Double getTodayGmv() {
		return todayGmv;
	}

	public void setTodayGmv(Double todayGmv) {
		this.todayGmv = todayGmv;
	}

	public Integer getWeekOrderNum() {
		return weekOrderNum;
	}

	public void setWeekOrderNum(Integer weekOrderNum) {
		this.weekOrderNum = weekOrderNum;
	}

	public Double getWeekGmv() {
		return weekGmv;
	}

	public void setWeekGmv(Double weekGmv) {
		this.weekGmv = weekGmv;
	}

	public Integer getWaitSendOrderNum() {
		return waitSendOrderNum;
	}

	public void setWaitSendOrderNum(Integer waitSendOrderNum) {
		this.waitSendOrderNum = waitSendOrderNum;
	}

	public Integer getTodaySendNum() {
		return todaySendNum;
	}

	public void setTodaySendNum(Integer todaySendNum) {
		this.todaySendNum = todaySendNum;
	}

	public Integer getWeekSendNum() {
		return weekSendNum;
	}

	public void setWeekSendNum(Integer weekSendNum) {
		this.weekSendNum = weekSendNum;
	}

	public Integer getReturningOrderNum() {
		return returningOrderNum;
	}

	public void setReturningOrderNum(Integer returningOrderNum) {
		this.returningOrderNum = returningOrderNum;
	}

	public Integer getFirstDay() {
		return firstDay;
	}

	public void setFirstDay(Integer firstDay) {
		this.firstDay = firstDay;
	}

	public Double getFirstSales() {
		return firstSales;
	}

	public void setFirstSales(Double firstSales) {
		this.firstSales = firstSales;
	}

	public Integer getSecondDay() {
		return secondDay;
	}

	public void setSecondDay(Integer secondDay) {
		this.secondDay = secondDay;
	}

	public Double getSecondSales() {
		return secondSales;
	}

	public void setSecondSales(Double secondSales) {
		this.secondSales = secondSales;
	}

	public Integer getThirdDay() {
		return thirdDay;
	}

	public void setThirdDay(Integer thirdDay) {
		this.thirdDay = thirdDay;
	}

	public Double getThirdSales() {
		return thirdSales;
	}

	public void setThirdSales(Double thirdSales) {
		this.thirdSales = thirdSales;
	}

	public Integer getFourthDay() {
		return fourthDay;
	}

	public void setFourthDay(Integer fourthDay) {
		this.fourthDay = fourthDay;
	}

	public Double getFourthSales() {
		return fourthSales;
	}

	public void setFourthSales(Double fourthSales) {
		this.fourthSales = fourthSales;
	}

	public Integer getFifthDay() {
		return fifthDay;
	}

	public void setFifthDay(Integer fifthDay) {
		this.fifthDay = fifthDay;
	}

	public Double getFifthSales() {
		return fifthSales;
	}

	public void setFifthSales(Double fifthSales) {
		this.fifthSales = fifthSales;
	}

	public Integer getSixthDay() {
		return sixthDay;
	}

	public void setSixthDay(Integer sixthDay) {
		this.sixthDay = sixthDay;
	}

	public Double getSixthSales() {
		return sixthSales;
	}

	public void setSixthSales(Double sixthSales) {
		this.sixthSales = sixthSales;
	}

	public Integer getSeventhDay() {
		return seventhDay;
	}

	public void setSeventhDay(Integer seventhDay) {
		this.seventhDay = seventhDay;
	}

	public Double getSeventhSales() {
		return seventhSales;
	}

	public void setSeventhSales(Double seventhSales) {
		this.seventhSales = seventhSales;
	}
	
}
