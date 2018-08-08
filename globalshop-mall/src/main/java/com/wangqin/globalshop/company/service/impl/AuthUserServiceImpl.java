package com.wangqin.globalshop.company.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthUserDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthUserDOMapperExt;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.company.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author angus
 * @date 2018/8/6
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserDOMapperExt authUserDOMapper;

    @Override
    public void addAuthUser(AuthUserDO authUserDO) {
        try {
            authUserDOMapper.insertSelective(authUserDO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizCommonException("用户新增失败！");
        }
    }

    @Override
    public void updateAuthUser(AuthUserDO authUserDO) {
        if (authUserDO.getUserNo() == null) {
            throw new BizCommonException("数据不完整！");
        }

        int effectedNum = authUserDOMapper.updateByUserNo(authUserDO);

        if (effectedNum <= 0) {
            throw new BizCommonException("数据库中无此记录！");
        }
    }

    @Override
    public AuthUserDO getByLoginName(String loginName) {
        if (loginName == null) {
            throw new BizCommonException("数据不完整！");
        }
        return authUserDOMapper.getByLoginName(loginName);
    }
}
