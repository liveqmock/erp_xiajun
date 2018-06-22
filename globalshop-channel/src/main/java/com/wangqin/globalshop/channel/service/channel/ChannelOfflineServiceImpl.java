package com.wangqin.globalshop.channel.service.channel;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelDOMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by 777 on 2018/6/8
 */

@Service
public class ChannelOfflineServiceImpl implements IChannelOfflineService {

	@Autowired
	private ChannelDOMapperExt channelDOMapperExt;

	@Override
    public int deleteByPrimaryKey(Long id){
		return channelDOMapperExt.deleteByPrimaryKey(id);
	}

	@Override
    public int insert(ChannelDO record){
		return channelDOMapperExt.insert(record);
	}

	@Override
    public int insertSelective(ChannelDO record){
		return channelDOMapperExt.insertSelective(record);
	}

	@Override
    public ChannelDO selectByPrimaryKey(Long id){
		return channelDOMapperExt.selectByPrimaryKey(id);
	}

	@Override
    public int updateByPrimaryKeySelective(ChannelDO record){
		return channelDOMapperExt.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKey(ChannelDO record){
		return channelDOMapperExt.updateByPrimaryKey(record);
	}

	@Override
    public List<ChannelDO> queryChannelList(){
		return channelDOMapperExt.queryChannelList();
	}

}
