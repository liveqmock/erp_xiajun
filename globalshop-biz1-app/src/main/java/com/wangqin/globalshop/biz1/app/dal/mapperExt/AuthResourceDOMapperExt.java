package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AuthResourceDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AuthResourceDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ResourceQueryVO;

import java.util.List;

public interface AuthResourceDOMapperExt extends AuthResourceDOMapper {

	int insertByNoId(ResourceQueryVO resouceVo);
    //Here are
    List<ResourceQueryVO> selectList();
    
    List<ResourceQueryVO> queryResource();
    
    Integer queryResourcesCount(ResourceQueryVO resourceVO);

    List<ResourceQueryVO> queryResourceQueryList(ResourceQueryVO resourceVO);
    
    AuthResourceDO queryTreeByResourceId(String resourceId);
    
    ResourceQueryVO queryTreeVoByResourceId(String resourceId);
    
    int updateByResourceVo(ResourceQueryVO resourceVO);
}