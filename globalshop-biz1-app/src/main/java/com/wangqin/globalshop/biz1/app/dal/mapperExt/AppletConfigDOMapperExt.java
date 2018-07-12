package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import org.apache.ibatis.annotations.Param;

import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.AppletConfigDOMapper;

import java.util.List;

public interface AppletConfigDOMapperExt extends AppletConfigDOMapper {

	AppletConfigDO queryWxMallConfigInfoByCompanyNo(@Param("companyNo")String companyNo,
			@Param("appletType")String appletType);

	AppletConfigDO selectByCompanyNoAndType(@Param("companyNo") String companyNo, @Param("type") String type);

	List<AppletConfigDO> list();
}
