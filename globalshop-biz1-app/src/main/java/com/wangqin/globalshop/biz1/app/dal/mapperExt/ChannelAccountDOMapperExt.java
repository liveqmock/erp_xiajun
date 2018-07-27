package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ChannelAccountSo;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelAccountDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ChannelAccountVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ChannelAccountDOMapperExt extends ChannelAccountDOMapper {

	public Integer queryPoCount(ChannelAccountSo channelAccountSo);

	public ChannelAccountDO queryPo(ChannelAccountSo channelAccountSo);

	public List<ChannelAccountDO> queryPoList(ChannelAccountSo channelAccountSo);


    ChannelAccountDO queryByTypeAndCompanyNo(ChannelAccountDO tmEntity);

	List<ChannelAccountDO> queryChannelAccountList(ChannelAccountVO channelAccountVO);
	
	//查询某个公司的所有渠道
	List<ChannelAccountDO> queryChannelAccountListByCompanyNo(String companyNo);

	Integer queryChannelNoByChannelName(@Param("channelName")String channelName,
			@Param("companyNo")String companyNo);
	
	String queryChannelNoByChannelNameAndCompanyNo(@Param("channelName")String channelName,
			@Param("companyNo")String companyNo);
	
	String queryChannelNameByChannelNoAndCompanyNo(@Param("channelNo")String channelNo,
			@Param("companyNo")String companyNo);

}
