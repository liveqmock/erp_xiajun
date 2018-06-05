package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountSo;
import com.wangqin.globalshop.biz1.app.dal.mapper.ChannelAccountDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ChannelAccountVO;

import java.util.List;

public interface ChannelAccountDOMapperExt extends ChannelAccountDOMapper {

	public Integer queryPoCount(ChannelAccountSo channelAccountSo);

	public ChannelAccountDO queryPo(ChannelAccountSo channelAccountSo);

	public List<ChannelAccountDO> queryPoList(ChannelAccountSo channelAccountSo);


    ChannelAccountDO queryByTypeAndCompanyNo(ChannelAccountDO tmEntity);

	List<ChannelAccountDO> queryChannelAccountList(ChannelAccountVO channelAccountVO);


}
