package com.wangqin.globalshop.channel.service.utils;


import com.wangqin.globalshop.biz1.app.dal.mapperExt.SequenceUtilMapperExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Sequence 表数据服务层接口实现类
 *
 */
@Service
public class SequenceServiceImpl  implements ISequenceService {



	@Autowired
	private SequenceUtilMapperExt sequenceUtilMapperExt;

	public Long gainORDSequence(){
           return sequenceUtilMapperExt.gainORDSequence();
	}


}
