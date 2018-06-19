package com.wangqin.globalshop.biz1.app.dal.mapper;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WxUserShareHistoryDO;

public interface WxUserShareHistoryDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WxUserShareHistoryDO record);

    int insertSelective(WxUserShareHistoryDO record);

    WxUserShareHistoryDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WxUserShareHistoryDO record);

    int updateByPrimaryKey(WxUserShareHistoryDO record);
}