package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.AuthUserMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUser;
import com.wangqin.globalshop.biz1.app.service.IAuthUserService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * AuthUser 表数据服务层接口实现类
 *
 */
@Service
public class AuthUserServiceImpl extends SuperServiceImpl<AuthUserMapper, AuthUser> implements IAuthUserService {


}