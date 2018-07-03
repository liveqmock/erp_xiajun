package com.wangqin.globalshop.item.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.JsonResult;
import com.wangqin.globalshop.item.app.service.ICountryService;

@Controller
@RequestMapping(value = "/country")
public class CountryController {

	@Autowired
	private ICountryService countryService;
	
	/**
	 * 添加国家
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object add(CountryDO country) {
		JsonResult<CountryDO> result = new JsonResult<>();		
		if(countryService.queryCountrySelective(country) != null) {
			return result.buildMsg("添加失败，该国已存在").buildIsSuccess(false);
		}
		countryService.insertCountrySelective(country);
		result.buildData(countryService.queryCountrySelective(country));
		return result.buildIsSuccess(true).buildMsg("添加成功");
	}
	
	/**
	 * 总查询所有国家
	 */
	@RequestMapping("/queryAllCountries")
	@ResponseBody
	public Object queryAllCountries(CountryDO country) {
		JsonResult<List<CountryDO>> result = countryService.queryAllCountries();
		return result.buildIsSuccess(true);
	}
	
}
