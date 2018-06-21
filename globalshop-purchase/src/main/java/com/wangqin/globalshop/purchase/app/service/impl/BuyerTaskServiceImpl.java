package com.wangqin.globalshop.purchase.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerTaskDetailDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.BuyerTaskVO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.ItemTask;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerTaskDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.BuyerTaskDetailDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.purchase.app.comm.Constant;
import com.wangqin.globalshop.purchase.app.service.IBuyerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    @Autowired
    private ItemSkuMapperExt itemSkuMapper;

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

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void importTask(List<List<Object>> list) {
        List<String> errMsg = new ArrayList<>();
        List<BuyerTaskDO> taskList = new ArrayList<>();
        List<BuyerTaskDetailDO> detailList = new ArrayList<>();
        int i = 0;
        for (List<Object> obj : list) {
            if (obj.size() < 6){
                break;
            }
            i++;
            BuyerTaskDO task = new BuyerTaskDO();
            BuyerTaskDetailDO detail = new BuyerTaskDetailDO();
            /**采购任务名称*/
            task.setTitle(obj.get(0).toString());
            /**upc*/
            String upc = obj.get(1).toString();
            /**根据upc*/
            /**根据UPC来获取商品信息*/
            String status = setDetailInfo(detail, upc);
            if (status != null) {
                errMsg.add(status + "位于第" + i + "行 第2列");
            }
            /**采购限价*/
            String maxPrice = obj.get(3).toString().trim();
            if (isParseToInteger(maxPrice)) {
                detail.setMaxPrice(BigDecimal.valueOf(Long.valueOf(maxPrice)));
            } else {
                errMsg.add("存在未知格式的数据:第" + i + "行 第4列的  " + maxPrice);
            }
            /**采购数目*/
            String maxCount = obj.get(4).toString().trim();
            if (isParseToInteger(maxCount)) {
                detail.setMaxCount(Integer.valueOf(maxCount));
            } else {
                errMsg.add("存在未知格式的数据:第" + i + "行 第5列的  " + maxCount);
            }
            /**任务的有效天数*/
            String limitTime = obj.get(5).toString().trim();
            if (isParseToInteger(limitTime)) {
                Date date = new Date();
                detail.setStartTime(date);
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(date);
                rightNow.add(Calendar.DAY_OF_YEAR, Integer.valueOf(limitTime));
                detail.setEndTime(rightNow.getTime());
            } else {
                errMsg.add("存在未知格式的数据:第" + i + "行 第5列的  " + limitTime);
            }
            task.setStatus(Constant.TO_BE_PURCHASED);
            task.setBuyerTaskNo("TaskNo" + System.currentTimeMillis());
            task.init();
            taskList.add(task);

            detail.init();
            detail.setBuyerTaskNo("TaskNo" + System.currentTimeMillis());
            detail.setMode((byte) 1);
            detailList.add(detail);
        }

        int size = errMsg.size();
        if (size == 0) {
            detailMapper.inserBatch(detailList);
            mapper.insertBatch(taskList);
        } else if (size < 10) {
            throw new ErpCommonException(errMsg.toString());
        } else {
            throw new ErpCommonException("上传文件错误过多,请验证后再次上传");
        }

    }

    /**
     * 根据upc来完善任务明细的内容
     *
     * @param detail
     * @param upc
     * @return 如果返回空  则没有问题
     * 如果不为空  返回的是错误信息
     */
    private String setDetailInfo(BuyerTaskDetailDO detail, String upc) {
        ItemSkuDO itemSku = itemSkuMapper.queryBySkuCodeOrUpcAndCompanyNo(upc, AppUtil.getLoginUserCompanyNo());
        if (itemSku == null) {
            return "不存在对应的商品:" + upc;
        }
        detail.setUpc(upc);
        detail.setItemCode(itemSku.getItemCode());
        detail.setSkuCode(itemSku.getSkuCode());
        detail.setRemark(itemSku.getRemark());
        detail.setSkuPicUrl(itemSku.getSkuPic());
        return null;
    }


    /**
     * 判断是否能够转换成Long类型
     *
     * @param maxPrice
     */
    private Boolean isParseToInteger(String maxPrice) {
        try {
            Integer.valueOf(maxPrice);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void getBuyerTaskDO(BuyerTaskDO task, BuyerDO buyer, BuyerTaskVO vo) {
        task.setTitle(vo.getTaskTitle());
        task.setRemark(vo.getTaskDesc());
        task.setEndTime(vo.getTaskEndTime());
        task.setStartTime(vo.getTaskStartTime());
        /**设置采购中*/
        task.setStatus(Constant.TO_BE_PURCHASED);
        task.setBuyerTaskNo("TaskNo" + System.currentTimeMillis());
        task.init();
        task.setBuyerName(buyer.getNickName());
        task.setBuyerOpenId(buyer.getOpenId());
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
