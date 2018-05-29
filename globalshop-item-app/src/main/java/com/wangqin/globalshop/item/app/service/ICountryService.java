package com.wangqin.globalshop.item.app.service;




import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.common.utils.JsonPageResult;

/**
 * Country Service层接口
 * @author Xiajun
 *
 */

public interface ICountryService {

	/**
	 * 根据名字查找国家
	 * 
	 */
	//Country selectCountryByName(String name);
	
	/**
	 * 添加国家
	 * @param country
	 */
	//void addCountry(Country country);
	
	/**
	 * 查找所有国家
	 */
	JsonPageResult<List<CountryDO>> queryAllCountries();
	
	CountryDO queryCountrySelective(CountryDO countryDO);
}
