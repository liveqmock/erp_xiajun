package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthResourceDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ResourceQueryVO;

import java.util.List;

public interface AuthResourceDOMapperExt extends AuthResourceDOMapper {


    //Here are
    List<AuthResourceDO> selectList();
    
    List<AuthResourceDO> queryResource();
    
    Integer queryResourcesCount(ResourceQueryVO resourceQueryVO);

    List<AuthResourceDO> queryResourceQueryList(ResourceQueryVO resourceQueryVO);
    
    AuthResourceDO queryTreeByResourceId(String resourceId);
}