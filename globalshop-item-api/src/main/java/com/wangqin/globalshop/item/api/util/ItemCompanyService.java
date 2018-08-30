package com.wangqin.globalshop.item.api.util;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;

public interface ItemCompanyService {

	@RequestMapping(value = "/company/selectByCompanyNo", method = RequestMethod.POST)
	CompanyDO selectByCompanyNo(@RequestParam("companyNo") String companyNo);
}
