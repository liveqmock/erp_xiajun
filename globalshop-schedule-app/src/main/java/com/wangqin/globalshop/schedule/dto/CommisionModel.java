package com.wangqin.globalshop.schedule.dto;

import com.wangqin.globalshop.common.base.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommisionModel {

	private String strategy;
	private String model;


	public static void main(String[] args) {
		CommisionModel commisionModel = new CommisionModel();
		commisionModel.setStrategy("1");
		AgencyModel model = new AgencyModel();
		model.setLevelOneRate(0.2+"");
		model.setLevelOneUserId("u01");

		model.setLevelTwoRate(0.05+"");
		model.setLevelTwoUserId("US180801132644935803");


		commisionModel.setModel(BaseDto.toString(model));
		System.out.println(BaseDto.toString(commisionModel));
	}

}
