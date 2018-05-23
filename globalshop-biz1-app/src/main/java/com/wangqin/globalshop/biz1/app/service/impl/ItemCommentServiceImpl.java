package com.wangqin.globalshop.biz1.app.service.impl;

import org.springframework.stereotype.Service;

import com.wangqin.globalshop.biz1.app.dal.mapper.ItemCommentMapper;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemComment;
import com.wangqin.globalshop.biz1.app.service.IItemCommentService;
import com.baomidou.framework.service.impl.SuperServiceImpl;

/**
 *
 * ItemComment 表数据服务层接口实现类
 *
 */
@Service
public class ItemCommentServiceImpl extends SuperServiceImpl<ItemCommentMapper, ItemComment> implements IItemCommentService {


}