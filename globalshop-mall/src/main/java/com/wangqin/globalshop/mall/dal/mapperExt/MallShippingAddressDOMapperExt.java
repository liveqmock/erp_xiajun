package com.wangqin.globalshop.mall.dal.mapperExt;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallShippingAddressDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallShippingAddressDOMapper;

public interface MallShippingAddressDOMapperExt extends MallShippingAddressDOMapper {

    Integer countMallShippingAddressDOByOpenId(String openId);

    Integer updateMallShippingAddressDOIsDefault(String openId);

    List<MallShippingAddressDO> selectByOpenId(String openId);

    MallShippingAddressDO selectByDefaultAndOpenId(int isDefault, String openId);

    Integer updateMallShippingAddressDOIsDefaultByGmtCreate(String openId);

}
