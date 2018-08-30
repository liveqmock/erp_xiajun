package com.wangqin.globalshop.item.app.service.impl;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CountryMapperExt;
import com.wangqin.globalshop.common.utils.JsonPageResult;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.item.app.service.ICountryService;



/**
 * Country Service层实现
 * @author XiaJun
 *
 */

@Service
public class CountryServiceImpl   implements ICountryService {

	@Autowired
	private CountryMapperExt countryMapper;
	


	
	//插入单个国家
	@Override
	public int insertCountrySelective(CountryDO country) {
		country.setCreator("admin");
		country.setModifier("admin");
		country.setCountryCode(RandomUtils.getTimeRandom());
		return countryMapper.insertCountrySelective(country);
	}
	
	/**
	 * 查找所有国家
	 */
	@Override
	public JsonPageResult<List<CountryDO>> queryAllCountries() {
		JsonPageResult<List<CountryDO>> countryResult = new JsonPageResult<>();
		List<CountryDO> countryList = countryMapper.queryAllCountries();
		countryResult.setData(countryList);
		return countryResult;
	}
	
	//按条件查找国家
	@Override
	public CountryDO queryCountrySelective(CountryDO countryDO) {
		return countryMapper.queryCountrySelective(countryDO);
	}


	@Override
	public void undeleteCountry(CountryDO countryDO){
		countryMapper.updateByPrimaryKeySelective(countryDO);
	}

	@Override
	public Long queryCodeByName(String name) {
		return countryMapper.queryCodeByName(name);
	}
}
