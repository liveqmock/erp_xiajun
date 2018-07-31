package com.wangqin.globalshop.schedule.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AgencyModel implements Serializable{

	private String levelOneUserId;
	private String levelOneRate = "0";
	private String levelTwoUserId;
	private String levelTwoRate = "0";
	private String skuRate = "0";

}
