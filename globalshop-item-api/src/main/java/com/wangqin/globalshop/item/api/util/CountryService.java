package com.wangqin.globalshop.item.api.util;






import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.common.utils.JsonPageResult;

/**
 * Country Service层接口
 * @author Xiajun
 *
 */

public interface CountryService {

	

	//插入单个国家
	@RequestMapping(value = "/country/insertCountrySelective", method = RequestMethod.POST)
	int insertCountrySelective(@RequestBody CountryDO country);
	
	/**
	 * 查找所有国家
	 */
	@RequestMapping(value = "/country/queryAllCountries", method = RequestMethod.POST)
	JsonPageResult<List<CountryDO>> queryAllCountries();
	
	//按条件查找国家
	@RequestMapping(value = "/country/queryCountrySelective", method = RequestMethod.POST)
	CountryDO queryCountrySelective(@RequestBody CountryDO countryDO);


	@RequestMapping(value = "/country/undeleteCountry", method = RequestMethod.POST)
	void undeleteCountry(@RequestBody CountryDO countryDO);
	@RequestMapping(value = "/country/queryCodeByName", method = RequestMethod.POST)
	Long queryCodeByName(@RequestParam("name")String name);
}
