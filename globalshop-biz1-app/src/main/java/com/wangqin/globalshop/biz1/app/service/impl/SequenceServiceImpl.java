package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.SequenceMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.Sequence;
import com.wangqin.globalshop.biz1.app.service.ISequenceService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * Sequence 表数据服务层接口实现类
 *
 */
@Service
public class SequenceServiceImpl extends SuperServiceImpl<SequenceMapper, Sequence> implements ISequenceService {


}