package com.wangqin.globalshop.purchase.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemTask;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.*;
import com.wangqin.globalshop.biz1.app.bean.dataVo.BuyerTaskDetailVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.BuyerTaskVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.UserQueryVO;
import com.wangqin.globalshop.common.utils.*;
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

    @Autowired
    private AuthUserDOMapperExt authUserMapper;
    @Autowired
    private ItemSkuScaleMapperExt itemSkuScaleMapper;

    @Override
    public List<BuyerTaskVO> list(BuyerTaskVO buyerTask) {
        buyerTask.initCompany();
		buyerTask.initFirstStart();
        return mapper.list(buyerTask);
    }

    @Override
    public BuyerTaskVO selectVoById(Long id) {

        //buyer_task_detail.item_code, buyer_task_detail.upc, buyer_task_detail.sku_pic_url
        BuyerTaskDO taskDO = mapper.selectByPrimaryKey(id);
        BuyerTaskVO resultVo = new BuyerTaskVO();
        BeanUtils.copies(taskDO, resultVo);

        List<BuyerTaskDetailDO> buyerTaskDetailDOList = detailMapper.taskDailyByTaskNo(resultVo.getBuyerTaskNo());
        if (!EasyUtil.isListEmpty(buyerTaskDetailDOList)) {

            List<BuyerTaskDetailVO> detailVOList = new ArrayList<>();
            for (BuyerTaskDetailDO detailDO : buyerTaskDetailDOList) {
                BuyerDO buyerSo = new BuyerDO();
                buyerSo.setIsDel(false);
                buyerSo.setCompanyNo(AppUtil.getLoginUserCompanyNo());
                buyerSo.setOpenId(detailDO.getBuyerOpenId());
                BuyerDO buyer = buyerMapper.searchBuyer(buyerSo);

                BuyerTaskDetailVO detailVO = new BuyerTaskDetailVO();
                BeanUtils.copies(detailDO, detailVO);
                if (buyer != null) {
                    detailVO.setBuyerId(buyer.getId());
                }
                detailVOList.add(detailVO);
            }
            resultVo.setTaskDetailList(detailVOList);

            BuyerTaskDetailDO detailDO = buyerTaskDetailDOList.get(0);

            resultVo.setItemCode(detailDO.getItemCode());
            resultVo.setUpc(detailDO.getUpc());
            resultVo.setSkuPicUrl(detailDO.getSkuPicUrl());
        } else {
            throw new BizCommonException("task_detail_error", "未找到对应商品");
        }
        return resultVo;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        BuyerTaskDO taskDO = mapper.selectByPrimaryKey(id);
        if (taskDO == null || EasyUtil.isStringEmpty(taskDO.getBuyerTaskNo())) {
            return;
        }

        detailMapper.deleteByTaskNo(taskDO.getBuyerTaskNo(), taskDO.getCompanyNo());

        mapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增采购任务
     */
    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public void add(BuyerTaskVO vo) {
        List<ItemTask> list = JSON.parseArray(vo.getDetailList(), ItemTask.class);

        for (ItemTask itemTask : list) {
            Long buyerId = vo.getBuyerId();
            /**获取相关买手信息*/
            if (vo.getBuyerId() == null || vo.getBuyerId() < 0) {
                buyerId = itemTask.getBuyerId();
            }
            BuyerDO buyer = buyerMapper.selectByPrimaryKey(buyerId);
            BuyerTaskDO task = new BuyerTaskDO();
            /**封装出一个buyerTaskDO对象*/

            Long nestTask = mapper.gainTASKSequence();

            String buyerTaskNo = CodeGenUtil.getBuyerTaskNo(buyerId, nestTask);
            task.setBuyerTaskNo(buyerTaskNo);

            task.setTitle(vo.getTitle());
            task.setRemark(vo.getRemark());
            task.setTaskDesc(vo.getTaskDesc());
            task.setImageUrl(vo.getImageUrl());
            getBuyerTaskDO(task, buyer, vo);
            mapper.insertSelective(task);

            /**获取相关买手信息*/
            BuyerDO by = buyerMapper.selectByPrimaryKey(itemTask.getBuyerId());
            BuyerTaskDetailDO detail = new BuyerTaskDetailDO();
            /**封装出一个buyerDetailTaskDO对象*/
            detail.setBuyerTaskNo(buyerTaskNo);
            detail.setBuyerTaskDetailNo(CodeGenUtil.getBuyerTaskDetailNo());

            detail.setStartTime(vo.getTaskStartTime());
            detail.setEndTime(vo.getTaskEndTime());

            getBuyerTaskDetailDO(detail, itemTask, by);
            detailMapper.insertSelective(detail);
        }
    }


    @Override
    public void update(BuyerTaskVO buyerTaskVO) {

        BuyerTaskDO buyerTaskDo = new BuyerTaskDO();

        BeanUtils.copies(buyerTaskVO, buyerTaskDo);

        BuyerDO buyer = buyerMapper.selectByPrimaryKey(buyerTaskVO.getBuyerId());
        if (buyer != null) {
            buyerTaskDo.setBuyerName(buyer.getNickName());
            buyerTaskDo.setBuyerOpenId(buyer.getOpenId());
        }

        mapper.updateByPrimaryKeySelective(buyerTaskDo);


        //先删除明细，再新增
        detailMapper.deleteByTaskNo(buyerTaskDo.getBuyerTaskNo(), AppUtil.getLoginUserCompanyNo());


        List<ItemTask> list = JSON.parseArray(buyerTaskVO.getDetailList(), ItemTask.class);
        for (ItemTask itemTask : list) {
            /**获取相关买手信息*/
            BuyerDO by = buyerMapper.selectByPrimaryKey(itemTask.getBuyerId());
            BuyerTaskDetailDO detail = new BuyerTaskDetailDO();
            /**封装出一个buyerDetailTaskDO对象*/
            detail.setBuyerTaskNo(buyerTaskDo.getBuyerTaskNo());
            detail.setBuyerTaskDetailNo(CodeGenUtil.getBuyerTaskDetailNo());

            //detail.setStartTime(vo.getTaskStartTime());
            //detail.setEndTime(vo.getTaskEndTime());

            getBuyerTaskDetailDO(detail, itemTask, by);
            detailMapper.insertSelective(detail);
        }

    }

    @Override
    @Transactional(rollbackFor = BizCommonException.class)
    public void importTask(List<List<Object>> list) throws Exception {
        try {
            List<String> errMsg = new ArrayList<>();
            List<BuyerTaskDO> taskList = new ArrayList<>();
            List<BuyerTaskDetailDO> detailList = new ArrayList<>();
            int i = 0;
            if (list.size() > 200) {
                throw new BizCommonException("最多只能导入两百条");
            }
            if (list.size() == 0) {
                throw new BizCommonException("当前导入为空");
            }
            Long nestTask = mapper.gainTASKSequence();
            for (List<Object> obj : list) {
                i++;
                BuyerTaskDO task = new BuyerTaskDO();
                BuyerTaskDetailDO detail = new BuyerTaskDetailDO();
                String buyerTaskDetailNo = CodeGenUtil.getBuyerTaskDetailNo();
                detail.setBuyerTaskDetailNo(buyerTaskDetailNo);
                /**采购任务名称*/
                String title = obj.get(0).toString();
                if (EasyUtil.isStringEmpty(title)) {
                    errMsg.add("采购任务名称为空!位于第" + i + "行 第2列");
                }
                task.setTitle(title);
                /**upc*/
                String upc = obj.get(1).toString();
                /**根据upc*/
                /**根据UPC来获取商品信息*/
                String status = setDetailInfo(detail, upc);
                if (status != null) {
                    errMsg.add(status + "位于第" + i + "行 第2列");
                }
                task.setImageUrl(detail.getSkuPicUrl());
                /**采购限价*/
                String maxPrice = obj.get(3).toString().trim();
                maxPrice = StringUtil.isBlank(maxPrice) ? "" : maxPrice;
                if (isParseToDouble(maxPrice)) {
                    BigDecimal decimal = new BigDecimal(maxPrice);
                    detail.setMaxPrice(decimal);
                    detail.setPrice(decimal);
                } else {
                    errMsg.add("存在未知格式的数据:第" + i + "行 第4列的  " + maxPrice);
                }
                /**采购数目*/
                String maxCount = obj.get(4).toString().trim();
                maxCount = StringUtil.isBlank(maxCount) ? "" : maxCount;
                if (isParseToInteger(maxCount)) {
                    Integer count = Integer.valueOf(maxCount);
                    detail.setMaxCount(count);
                    detail.setCount(count);
                } else {
                    errMsg.add("存在未知格式的数据:第" + i + "行 第5列的  " + maxCount);
                }
                /**任务的有效天数*/
                String limitTime = obj.get(5).toString().trim();
                limitTime = StringUtil.isBlank(limitTime) ? "" : limitTime;
                if (isParseToInteger(limitTime)) {
                    Date date = new Date();
                    task.setStartTime(date);
                    detail.setStartTime(date);
                    Calendar rightNow = Calendar.getInstance();
                    rightNow.setTime(date);
                    rightNow.add(Calendar.DAY_OF_YEAR, Integer.valueOf(limitTime));
                    task.setEndTime(rightNow.getTime());
                    detail.setEndTime(rightNow.getTime());
                } else {
                    errMsg.add("存在未知格式的数据:第" + i + "行 第6列的  " + limitTime);
                }
                task.setStatus(Constant.TO_BE_PURCHASED);

                String buyerTaskNo = CodeGenUtil.getBuyerTaskNo(0000L, nestTask + i);
                task.setBuyerTaskNo(buyerTaskNo);
                task.init();
                taskList.add(task);

                detail.init();
                detail.setBuyerTaskNo(buyerTaskNo);
                detail.setMode((byte) 1);
                detailList.add(detail);
            }

            int size = errMsg.size();
            if (size == 0) {
                detailMapper.inserBatch(detailList);
                mapper.insertBatch(taskList);
            } else if (size < 10) {
                throw new BizCommonException(errMsg.toString());
            } else {
                throw new BizCommonException("上传文件错误过多,请验证后再次上传");
            }
        } catch (BizCommonException e) {
            throw new BizCommonException(e.getErrorMsg());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
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
        if (StringUtils.isBlank(upc)) {
            return "UPC为空";
        }
        try {
            ItemSkuDO itemSku = itemSkuMapper.queryBySkuCodeOrUpcAndCompanyNo(upc, AppUtil.getLoginUserCompanyNo());

            if (itemSku == null) {
                return "不存在对应的商品:" + upc;
            }
            detail.setSkuPicUrl(ImgUtil.initImg2Json(itemSku.getSkuPic()));
            detail.setUpc(upc);
            detail.setItemCode(itemSku.getItemCode());
            detail.setSkuCode(itemSku.getSkuCode());
            detail.setRemark(itemSku.getRemark());
            detail.setSkuPicUrl(itemSku.getSkuPic());
            return null;
        } catch (Exception e) {
            return "异常的upc" + upc;
        }
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

    /**
     * 判断是否能够转换成Double类型
     *
     * @param price
     */
    private boolean isParseToDouble(String price) {
        try {
            Double.valueOf(price);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void getBuyerTaskDO(BuyerTaskDO task, BuyerDO buyer, BuyerTaskVO vo) {


        task.setEndTime(vo.getTaskEndTime());
        task.setStartTime(vo.getTaskStartTime());
        /**设置采购中*/
        task.setStatus(Constant.TO_BE_PURCHASED);
        task.init();
        if (buyer != null) {
            //默认分配的买手
            task.setBuyerName(buyer.getNickName());
            task.setBuyerOpenId(buyer.getOpenId());
        }

        //任务创建人，买手主管，具备新增采购任务的人
        task.setOwnerNo(AppUtil.getLoginUserId());
    }

    private void getBuyerTaskDetailDO(BuyerTaskDetailDO detail, ItemTask itemTask, BuyerDO by) {

        detail.setOwnerNo(AppUtil.getLoginUserId());
        UserQueryVO user = authUserMapper.selectUserVoByUserNo(AppUtil.getLoginUserId());
        if (user != null) {
            detail.setOwnerName(user.getName());
        }

        ItemSkuDO sku = itemSkuMapper.queryItemBySkuCode(itemTask.getSkucode());

        if (itemTask.getCount() == null || itemTask.getCount() <= 0) {
            throw new BizCommonException("采购数量要大于0");
        }
        if (itemTask.getPrice() == null || itemTask.getPrice().doubleValue() <= 0) {
            throw new BizCommonException("采购价要大于0");
        }

        detail.init();
        if (by != null) {
            detail.setBuyerName(by.getNickName());
            detail.setBuyerOpenId(by.getOpenId());
        } else {
            throw new BizCommonException("明细买手必填");
        }

        detail.setCount(itemTask.getCount());
        detail.setItemCode(sku.getItemCode());
        detail.setSkuCode(itemTask.getSkucode());
        detail.setMaxCount(itemTask.getMaxCount() == null ? itemTask.getCount() : itemTask.getMaxCount());
        detail.setPrice(itemTask.getPrice());
        detail.setMaxPrice(itemTask.getMaxPrice() == null ? itemTask.getPrice() : itemTask.getMaxPrice());
        detail.setRemark(itemTask.getRemark());
        detail.setSkuPicUrl(sku.getSkuPic());
        detail.setUpc(sku.getUpc());
        detail.setMode(itemTask.getMode());
    }


    //统一订单状态修改接口
    @Override
    @Transactional
    public void updateTaskStatus(Integer status, List<Long> taskDailyIdList) {
        for (Long id : taskDailyIdList) {
            BuyerTaskDO buyerTask = mapper.selectByPrimaryKey(id);
            if (buyerTask != null) {
                buyerTask.setStatus(status);
                this.mapper.updateByPrimaryKeySelective(buyerTask);
                detailMapper.updateTaskDetailDailyStatus(status, buyerTask.getBuyerTaskNo());
            }
        }
    }
}
