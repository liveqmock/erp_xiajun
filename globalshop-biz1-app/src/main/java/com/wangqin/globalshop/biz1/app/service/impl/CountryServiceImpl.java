package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.CountryMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.Country;
import com.wangqin.globalshop.biz1.app.service.ICountryService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * Country 表数据服务层接口实现类
 *
 */
@Service
public class CountryServiceImpl extends SuperServiceImpl<CountryMapper, Country> implements ICountryService {


}