package com.wangqin.globalshop.purchase.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDetailDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerTaskVO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.ItemTask;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerTaskDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerTaskDetailDOMapperExt;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.purchase.app.comm.Constant;
import com.wangqin.globalshop.purchase.app.service.IBuyerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author biscuit
 * @data 2018/06/12
 */
@Service
public class BuyerTaskServiceImpl implements IBuyerTaskService {
    @Autowired
    private BuyerTaskDOMapperExt mapper;
    @Autowired
    private BuyerTaskDetailDOMapperExt detailMapper;
    @Autowired
    private BuyerDOMapperExt buyerMapper;

    @Override
    public List<BuyerTaskDO> list(BuyerTaskDO buyerTask) {
        buyerTask.initCompany();
        return mapper.list(buyerTask);
    }

    /**
     * 新增采购任务
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void add(BuyerTaskVO vo) throws ErpCommonException {
        /**获取相关买手信息*/
        BuyerDO buyer = buyerMapper.selectByPrimaryKey(vo.getBuyerId());
        BuyerTaskDO task = new BuyerTaskDO();
        /**封装出一个buyerTaskDO对象*/
        getBuyerTaskDO(task, buyer, vo);
        mapper.insertSelective(task);
        List<ItemTask> list = JSON.parseArray(vo.getDetailList(), ItemTask.class);
        for (ItemTask itemTask : list) {
            /**获取相关买手信息*/
            BuyerDO by = buyerMapper.selectByPrimaryKey(itemTask.getBuyerId());
            BuyerTaskDetailDO detail = new BuyerTaskDetailDO();
            /**封装出一个buyerDetailTaskDO对象*/
            getBuyerTaskDetailDO(detail, itemTask, by);
            detailMapper.insertSelective(detail);
        }


    }

    private void getBuyerTaskDO(BuyerTaskDO task, BuyerDO buyer, BuyerTaskVO vo) {
        task.setTitle(vo.getTaskTitle());
        task.setRemark(vo.getTaskDesc());
        task.setEndTime(vo.getTaskEndTime());
        task.setStartTime(vo.getTaskStartTime());
        /**设置采购中*/
        task.setStatus(Constant.TO_BE_PURCHASED);
        task.init();
        task.setBuyerName(buyer.getNickName());
        task.setBuyerOpenId(buyer.getOpenId());
        task.setBuyerTaskNo("TaskNo" + System.currentTimeMillis());
    }

    private void getBuyerTaskDetailDO(BuyerTaskDetailDO detail, ItemTask itemTask, BuyerDO by) {
        detail.init();
        detail.setBuyerTaskNo("TaskNo" + System.currentTimeMillis());
        detail.setBuyerName(by.getNickName());
        detail.setBuyerOpenId(by.getOpenId());
        detail.setCount(itemTask.getCount());
        detail.setSkuCode(itemTask.getSkucode());
        detail.setMaxCount(itemTask.getTaskMaxCount());
        detail.setPrice(itemTask.getTaskPrice());
        detail.setMaxPrice(itemTask.getTaskMaxPrice());
        detail.setRemark(itemTask.getRemark());
        detail.setSkuPicUrl(itemTask.getImageUrl());
        detail.setMode(itemTask.getMode());
    }
}
