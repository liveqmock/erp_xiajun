package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.baomidou.mybatisplus.mapper.AutoMapper;
import com.wangqin.globalshop.biz1.app.dal.mapper.SequenceDOMapper;


/**
 * catagory 数据控制层
 * @author zhulu
 *
 */
public interface SequenceUtilMapperExt extends SequenceDOMapper {
	Long gainSkuSequence();
	Long gainItemSequence();
	Long gainPOSequence();
	Long gainERPOSequence();
	Long gainRECOSequence();
	Long gainORDSequence();
	Long gainPKGSequence();
	Long gainTASKSequence();
	Long gainINVOUTSequence();
}
