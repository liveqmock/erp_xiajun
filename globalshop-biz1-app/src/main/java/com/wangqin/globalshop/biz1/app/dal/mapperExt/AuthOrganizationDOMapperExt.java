package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthOrganizationDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthOrganizationDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dataVo.OrganizationQueryVO;

import java.util.List;

public interface AuthOrganizationDOMapperExt extends AuthOrganizationDOMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(AuthOrganizationDO record);
//
//    int insertSelective(AuthOrganizationDO record);
//
//    AuthOrganizationDO selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(AuthOrganizationDO record);
//
//    int updateByPrimaryKey(AuthOrganizationDO record);


    //Here are
    List<AuthOrganizationDO> selectByPIdNull();

    List<AuthOrganizationDO> selectAllByPId(Long pid);

    List<AuthOrganizationDO> selectList();

    Integer queryOrganizationsCount(OrganizationQueryVO organizationQueryVO);

    List<AuthOrganizationDO> queryOrganizationQueryList(String companyNo);
    
    int insertByOrganizationVo(OrganizationQueryVO organizationQueryVO);
    
    int updateByOrganizationVo(OrganizationQueryVO organizationQueryVO);
}