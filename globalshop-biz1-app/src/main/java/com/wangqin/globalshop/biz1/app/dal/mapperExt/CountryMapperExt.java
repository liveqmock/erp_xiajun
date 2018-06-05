package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.CountryDOMapper;

import java.util.List;

/**
 * country 数据控制层
 * 
 * @author XiaJun
 */
public interface CountryMapperExt extends CountryDOMapper{

	/**
	 * 根据名字查找国家
	 * @param name
	 * @return
	 */
	//Country selectCountryByName(String name);
	
	//插入单个国家
	int insertCountrySelective(CountryDO country);
	
	/**
	 * 查找所有国家
	 * @return
	 */
	List<CountryDO> queryAllCountries();
	
	//按条件查找国家
	CountryDO queryCountrySelective(CountryDO countryDO);
	
	String queryCodeById(Integer id);
}
