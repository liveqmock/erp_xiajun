package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthResourceDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ResourceQueryVO;

import java.util.List;

public interface AuthResourceDOMapperExt extends AuthResourceDOMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(AuthResourceDO record);
//
//    int insertSelective(AuthResourceDO record);
//
//    AuthResourceDO selectByPrimaryKey(Long id);
//
//    int updateByPrimaryKeySelective(AuthResourceDO record);
//
//    int updateByPrimaryKey(AuthResourceDO record);

    //Here are
    List<AuthResourceDO> selectList();

    Integer queryResourcesCount(ResourceQueryVO resourceQueryVO);

    List<AuthResourceDO> queryResourceQueryList(ResourceQueryVO resourceQueryVO);
}