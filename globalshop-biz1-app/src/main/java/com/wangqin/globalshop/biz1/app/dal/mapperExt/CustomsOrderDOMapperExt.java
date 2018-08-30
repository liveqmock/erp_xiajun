package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.CustomsOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.CustomsOrderDOMapper;

/**
 * @author angus
 * @date 2018/8/29
 */
public interface CustomsOrderDOMapperExt extends CustomsOrderDOMapper {

    int updateByBusinessNo(CustomsOrderDO customsOrderDO);
}
