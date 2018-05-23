package com.wangqin.globalshop.usercenter.service.impl;

import java.io.Serializable;

import com.wangqin.globalshop.usercenter.mapper.UserMapper;
import com.wangqin.globalshop.usercenter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private UserMapper userMapper;
	
    @Cacheable(value = "hour", key = "#id")
	public User selectById(Serializable id) {
		return userMapper.selectById(id);
	}
}
