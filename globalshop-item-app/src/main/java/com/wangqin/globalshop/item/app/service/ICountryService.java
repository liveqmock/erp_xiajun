package com.wangqin.globalshop.item.app.service;




import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.common.utils.JsonPageResult;

import java.util.List;

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
	

	//插入单个国家
	int insertCountrySelective(CountryDO country);
	
	/**
	 * 查找所有国家
	 */
	JsonPageResult<List<CountryDO>> queryAllCountries();
	
	//按条件查找国家
	CountryDO queryCountrySelective(CountryDO countryDO);
	
	String queryCodeById(Integer id);

	void undeleteCountry(CountryDO countryDO);

    String queryCodeByName(String purchaseFrom);
}
