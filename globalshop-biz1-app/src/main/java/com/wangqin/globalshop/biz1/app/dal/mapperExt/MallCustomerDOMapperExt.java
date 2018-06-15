package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallCustomerDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallCustomerDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallCustomerDOMapperExt extends MallCustomerDOMapper {

    MallCustomerDO selectByOpenId(@Param("openId") String openId);

    List<MallCustomerDO> selectByRole(@Param("role")Integer role);
}
