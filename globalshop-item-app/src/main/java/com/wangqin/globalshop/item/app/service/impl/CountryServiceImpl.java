package com.wangqin.globalshop.item.app.service.impl;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.CountryMapperExt;
import com.wangqin.globalshop.common.utils.JsonPageResult;
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
	
	/**
	 * 根据名字查找国家
	 */
	/*
	@Override
	public Country selectCountryByName(String name) {
		return countryMapper.selectCountryByName(name);
	}
	*/
	/**
	 * 添加国家
	 *//*
	@Override
	public void addCountry(Country country) {
		String userCreate = ShiroUtil.getShiroUser().getLoginName();
		country.setUserCreate(userCreate);
		country.setGmtCreate(new Date());
		countryMapper.insert(country);
	}
	*/
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
	
	@Override
	public CountryDO queryCountrySelective(CountryDO countryDO) {
		return countryMapper.queryCountrySelective(countryDO);
	}

	@Override
	public String queryCodeById(Integer id) {
		return countryMapper.queryCodeById(id);
	}

}
