package com.wangqin.globalshop.mall.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallShippingCartDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallShippingCartDOMapper;

public interface MallShippingCartDOMapperExt extends MallShippingCartDOMapper {

    List<MallShippingCartDO> selectListByOpenId(String openId);

}
