package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.AuthResourceMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResource;
import com.wangqin.globalshop.biz1.app.service.IAuthResourceService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * AuthResource 表数据服务层接口实现类
 *
 */
@Service
public class AuthResourceServiceImpl extends SuperServiceImpl<AuthResourceMapper, AuthResource> implements IAuthResourceService {


}