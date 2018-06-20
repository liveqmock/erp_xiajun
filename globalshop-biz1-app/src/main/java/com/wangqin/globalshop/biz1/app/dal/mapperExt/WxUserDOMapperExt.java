package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.WxUserDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.WxUserDOMapper;

import java.util.List;

/**
 * Create by 777 on 2018/6/20
 */
public interface WxUserDOMapperExt extends WxUserDOMapper {

	public WxUserDO searchWxUser(WxUserDO wxUserDO);

	public Long searchWxUserCount(WxUserDO wxUserDO);


	public List<WxUserDO> searchWxUserList(WxUserDO wxUserDO);

	WxUserDO queryByUnionId(String unionId);
}
