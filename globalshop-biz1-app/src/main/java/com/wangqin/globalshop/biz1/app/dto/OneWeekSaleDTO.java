package com.wangqin.globalshop.biz1.app.dto;

/**
 * 一周销售数据
 * @author XiaJun
 *
 */
public class OneWeekSaleDTO {

	//今天的销售数量
	private Integer oneDayNumber;

	//昨天的销售数量
	private Integer twoDayNumber;
	
	private Integer threeDayNumber;
	
	private Integer	fourDayNumber;
	
	private Integer fiveDayNumber;
	
	private Integer sixDayNumber;
	
	private Integer sevenDayNumber;
	
	private Integer totalNumber;
	
	//7天总销售额
	private Float totalSalesMoney;

	public Integer getOneDayNumber() {
		return oneDayNumber;
	}

	public void setOneDayNumber(Integer oneDayNumber) {
		this.oneDayNumber = oneDayNumber;
	}

	public Integer getTwoDayNumber() {
		return twoDayNumber;
	}

	public void setTwoDayNumber(Integer twoDayNumber) {
		this.twoDayNumber = twoDayNumber;
	}

	public Integer getThreeDayNumber() {
		return threeDayNumber;
	}

	public void setThreeDayNumber(Integer threeDayNumber) {
		this.threeDayNumber = threeDayNumber;
	}

	public Integer getFourDayNumber() {
		return fourDayNumber;
	}

	public void setFourDayNumber(Integer fourDayNumber) {
		this.fourDayNumber = fourDayNumber;
	}

	public Integer getFiveDayNumber() {
		return fiveDayNumber;
	}

	public void setFiveDayNumber(Integer fiveDayNumber) {
		this.fiveDayNumber = fiveDayNumber;
	}

	public Integer getSixDayNumber() {
		return sixDayNumber;
	}

	public void setSixDayNumber(Integer sixDayNumber) {
		this.sixDayNumber = sixDayNumber;
	}

	public Integer getSevenDayNumber() {
		return sevenDayNumber;
	}

	public void setSevenDayNumber(Integer sevenDayNumber) {
		this.sevenDayNumber = sevenDayNumber;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Float getTotalSalesMoney() {
		return totalSalesMoney;
	}

	public void setTotalSalesMoney(Float totalSalesMoney) {
		this.totalSalesMoney = totalSalesMoney;
	}

	public OneWeekSaleDTO(Integer oneDayNumber, Integer twoDayNumber, Integer threeDayNumber, Integer fourDayNumber,
			Integer fiveDayNumber, Integer sixDayNumber, Integer sevenDayNumber, Integer totalNumber,
			Float totalSalesMoney) {
		super();
		this.oneDayNumber = oneDayNumber;
		this.twoDayNumber = twoDayNumber;
		this.threeDayNumber = threeDayNumber;
		this.fourDayNumber = fourDayNumber;
		this.fiveDayNumber = fiveDayNumber;
		this.sixDayNumber = sixDayNumber;
		this.sevenDayNumber = sevenDayNumber;
		this.totalNumber = totalNumber;
		this.totalSalesMoney = totalSalesMoney;
	}
	
	
	
}
