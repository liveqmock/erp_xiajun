package com.wangqin.globalshop.company.service.impl;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthOrganizationDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.AuthOrganizationDOMapperExt;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.company.service.AuthOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author angus
 * @date 2018/8/6
 */
@Service
public class AuthOrganizationServiceImpl implements AuthOrganizationService {

    @Autowired
    private AuthOrganizationDOMapperExt authOrganizationDOMapper;

    @Override
    public void addAuthOrganization(AuthOrganizationDO authOrganizationDO) {
        try {
            authOrganizationDOMapper.insertSelective(authOrganizationDO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizCommonException("部门新增失败！");
        }
    }
}
