package com.wangqin.globalshop.channel.service.utils;

import javax.annotation.Resource;

import com.wangqin.globalshop.channel.dal.mapperExt.CASequenceDOMapperExt;
import org.springframework.stereotype.Service;

/**
 *
 * Sequence 表数据服务层接口实现类
 *
 */
@Service
public class SequenceServiceImpl  implements ISequenceService {



	@Resource CASequenceDOMapperExt sequenceDOMapperExt;

	public Long gainORDSequence(){
           return sequenceDOMapperExt.gainORDSequence();
	}


}
