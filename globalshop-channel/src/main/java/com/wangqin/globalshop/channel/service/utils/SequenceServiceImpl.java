package com.wangqin.globalshop.channel.service.utils;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 *
 * Sequence 表数据服务层接口实现类
 *
 */
@Service
public class SequenceServiceImpl  implements ISequenceService {



	@Resource SequenceDOMapperExt sequenceDOMapperExt;

	public Long gainORDSequence(){
           return sequenceDOMapperExt.gainORDSequence();
	}


}
