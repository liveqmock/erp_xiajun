package com.wangqin.globalshop.purchase.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wangqin.globalshop.common.utils.TimeUtil;
import com.wangqin.globalshop.purchase.service.TaskService;
import com.wangqin.globalshop.biz1.app.constants.enums.TaskStatusEnum;
import com.wangqin.globalshop.common.GlobalContants;
import com.wangqin.globalshop.common.result.RetCode;
import com.wangqin.globalshop.common.utils.ErrorResult;
import com.wangqin.globalshop.common.utils.JSONUtil;
import com.wangqin.globalshop.common.utils.Md5Util;
import com.wangqin.globalshop.common.utils.SingleResult;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.common.utils.SuccessResult;


@Controller
@ResponseBody
@RequestMapping("/api/task")
public class ApiTaskController {

    @Autowired
    TaskService taskService;

    /**
     * 采购任务列表
     * 
     * @param key
     * @param status
     * @param pageNum
     * @param taskId
     * @return
     */
    @RequestMapping("/list")
    public String list(String key, Integer status, Integer pageNum, Integer taskId) {
        if(status==0) {
            status = null;
        }
        if (pageNum == null || pageNum < 0) {
            pageNum = 0;
        }
        Map<String, Object> result = new HashMap<>();
        Boolean searchGoodsFlag = true;
        List<TaskDailyExt> taskDailyExtList = taskService.selectAllByCondition(key, null, null, status, taskId, pageNum,
                                                                               GlobalContants.pageSize,
                                                                               searchGoodsFlag);
        String resultType = "task";
        taskDailyExtList = dealTaskExtList(taskDailyExtList, key, status, taskId, resultType);
        result.put("taskDailyList", taskDailyExtList);
        result.put("pageNum", pageNum);
        result.put("pageSize", GlobalContants.pageSize);
        result.put("token", Md5Util.getMD5(JSONUtil.object2JSON(result)));
        return new SingleResult<>(result).toJSONString();
    }

    @RequestMapping("/detail")
    public String detail(Long id) {
        TaskDailyDetail taskDailyDetail = taskDailyDetailMapper.selectByPrimaryKey(id);
        TaskReceipt taskReceipt = taskReceiptMapper.countAllQuantity(id);
        Item item = itemMapper.selectByPrimaryKey(taskDailyDetail.getItemId());
        Map<String, String> picMap = JSONUtil.getMapStr(item.getMainPic());
        List<PicModel> picList = JSONUtil.getListObj(picMap.get("picList"), PicModel.class);
        item.setMainPic(picList.get(0).getUrl());
        ItemSku itemSku = itemSkuMapper.selectByPrimaryKey(taskDailyDetail.getSkuId());
        Map<String, Object> result = new HashMap<>();
        result.put("taskReceipt", taskReceipt);
        result.put("taskDailyDetail", taskDailyDetail);
        result.put("item", item);
        result.put("itemSku", itemSku);
        return new SingleResult<>(result).toJSONString();
    }


    /**
     * 加入预入库
     * 
     * @param pageNum
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
    @Transactional
    @RequestMapping("/addStorage")
    public String addStorage(Integer[] receiptIds, Long buyerId) {
        if (buyerId == null || receiptIds==null || receiptIds.length<1) {
            return new ErrorResult(RetCode.ILLEGAL_ARGS).toJSONString();
        }
        WxPurchaseUser wxPurchaseUser = wxPurchaseUserMapper.selectByPrimaryKey(buyerId);
        if (wxPurchaseUser == null) {
            return new ErrorResult(RetCode.WRONG_DATA).toJSONString();
        }
        Warehouse warehouse = warehouseMapper.selectByCompanyId(wxPurchaseUser.getCompanyId());
        PurchaseStorage purchaseStorage = new PurchaseStorage();
        purchaseStorage.setWarehouseId(warehouse.getId());
        purchaseStorage.setWarehouseName(warehouse.getName());
        purchaseStorage.setBuyerId(buyerId);
        purchaseStorage.setBuyerName(wxPurchaseUser.getNickName());
        purchaseStorage.setGmtCreate(new Date());
        purchaseStorage.setGmtModify(new Date());
        Integer status = 1;
        Integer type = 0;
        Integer currency = 3;
        purchaseStorage.setStatus((byte) status.intValue());
        purchaseStorage.setStorageType((byte) type.intValue());
        String pos = "G"+TimeUtil.getDate(TimeUtil.DEFAULT_DATE_NO_SEPRATORS_FORMAT, new Date())+"U"+String.format("%0"+4+"d", buyerId)+purchaseStorageMapper.gainPOSequence();
        purchaseStorage.setStoOrderNo(pos);
        int result = purchaseStorageMapper.insertSelective(purchaseStorage);
        if(result>0) {
            List<TaskReceipt> taskReceiptList = taskReceiptMapper.selectByIds(receiptIds);
            Map<Integer,ItemSku> itemSkuMap = new HashMap<>();
            if(!taskReceiptList.isEmpty()) {
                itemSkuMap = itemSkuMapper.selectByItemMapByTaskReceipt(taskReceiptList);
            }
            for(TaskReceipt taskReceipt :taskReceiptList) {
                PurchaseStorageDetail purchaseStorageDetail = new PurchaseStorageDetail();
                purchaseStorageDetail.setStorageId(purchaseStorage.getId());
                if(itemSkuMap.get(taskReceipt.getSkuId())!=null) {
                    purchaseStorageDetail.setSkuId(itemSkuMap.get(taskReceipt.getSkuId()).getId());
                    purchaseStorageDetail.setUpc(itemSkuMap.get(taskReceipt.getSkuId()).getUpc());
                    purchaseStorageDetail.setItemId(itemSkuMap.get(taskReceipt.getSkuId()).getItemId());
                    purchaseStorageDetail.setSkuBuysite(itemSkuMap.get(taskReceipt.getSkuId()).getBuySite());
                    purchaseStorageDetail.setSkuCode(itemSkuMap.get(taskReceipt.getSkuId()).getSkuCode());
                }
                purchaseStorageDetail.setGmtCreate(new Date());
                purchaseStorageDetail.setGmtModify(new Date());
                purchaseStorageDetail.setStoOrderNo(pos);
                purchaseStorageDetail.setStorageId(purchaseStorage.getId());
                purchaseStorageDetail.setTaskDailyDetailId(taskReceipt.getTaskDailyDetailId());
                purchaseStorageDetail.setPrice(taskReceipt.getPrice());
                purchaseStorageDetail.setQuantity(taskReceipt.getQuantity());
                if(purchaseStorageDetail.getQuantity()==null) {
                    purchaseStorageDetail.setQuantity(0);
                }
                purchaseStorageDetail.setTransQuantity(taskReceipt.getTransQuantity());
                if(purchaseStorageDetail.getTransQuantity()==null) {
                    purchaseStorageDetail.setTransQuantity(0);
                }
                purchaseStorageDetail.setTotalPrice(taskReceipt.getPrice()*(purchaseStorageDetail.getQuantity()+purchaseStorageDetail.getTransQuantity()));
                //TODO 货位号临时处理
                purchaseStorageDetail.setShelfNo("t34");
                purchaseStorageDetail.setCurrency((byte) currency.intValue());
                result = purchaseStorageDetailMapper.insertSelective(purchaseStorageDetail);
                TaskReceipt newTaskReceipt = new TaskReceipt();
                newTaskReceipt.setPurchaseStorageId(purchaseStorageDetail.getId());
                newTaskReceipt.setId(taskReceipt.getId());
                taskReceiptMapper.updateByPrimaryKeySelective(newTaskReceipt);
                Receipt receipt = new Receipt();
                receipt.setId(taskReceipt.getReceiptId());
                receipt.setPurchaseStorageId(purchaseStorage.getId());
                receiptMapper.updateByPrimaryKeySelective(receipt);
            }
        }
        return new ErrorResult(RetCode.SYS_ERROR).toJSONString();
    }

    /**
     * 已结算列表
     * 
     * @param pageNum
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
    @RequestMapping("/taskReceiptList")
    public String taskReceiptList(Integer pageNum, String key,String type) {
        List<Receipt> receiptList = receiptMapper.selectByAllAndType(type,key);
        List<TaskDaily> taskDailyList = new ArrayList<>();
        Integer pageSize = GlobalContants.pageSize;
        Integer taskDoneNumber = taskDailyMapper.countByStatus(TaskStatusEnum.done.getCode());
        Integer taskingNumber = taskDailyMapper.countByStatus(TaskStatusEnum.doing.getCode());
        Integer storage = receiptMapper.countTaskDailyNum();
        if (!receiptList.isEmpty()) {
            taskDailyList = taskDailyMapper.selectByReceiptList(receiptList, pageNum * pageSize, pageSize);
        }
        List<TaskDailyExt> taskDailyExtList = taskService.getTaskDailyExtList(taskDailyList);
        BigDecimal amount = new BigDecimal("0.00");
        Integer quantity = 0;
        Integer transQuantity = 0;
        for (TaskDailyExt taskDailyExt : taskDailyExtList) {
            List<TaskDailyDetailExt> taskDailyDetailExtList = new ArrayList<>();
            List<Receipt> tempReceiptList = receiptMapper.selectByTaskDailyId(taskDailyExt.getId());
            Integer taskQuantity = 0;
            Integer taskTransQuantity = 0;
            for (Receipt receipt : tempReceiptList) {
                List<TaskReceipt> taskReceiptList = taskReceiptMapper.selectByReceiptId(receipt.getId(), type);
                Map<Integer, Item> itemMap = new HashMap<>();
                Map<Integer, ItemSku> itemSkuMap = new HashMap<>();
                if (!taskReceiptList.isEmpty()) {
                    itemMap = itemMapper.selectByItemMapByTaskReceipt(taskReceiptList);
                    itemSkuMap = itemSkuMapper.selectByItemMapByTaskReceipt(taskReceiptList);
                }
                for (TaskReceipt taskReceipt : taskReceiptList) {
                    TaskDailyDetailExt taskDailyDetailExt = new TaskDailyDetailExt();
                    taskDailyDetailExt.setPurchasePrice(taskReceipt.getPrice());
                    if (itemMap.get(taskReceipt.getItemId()) != null) {
                        if (StringUtil.isNotBlank(itemMap.get(taskReceipt.getItemId()).getMainPic())) {
                            Map<String, String> picMap = JSONUtil.getMapStr(itemMap.get(taskReceipt.getItemId()).getMainPic());
                            List<PicModel> picList = JSONUtil.getListObj(picMap.get("picList"), PicModel.class);
                            taskDailyDetailExt.setSkuPic(picList.get(0).getUrl());
                        }
                        taskDailyDetailExt.setName(itemMap.get(taskReceipt.getItemId()).getName());
                    }
                    if (itemSkuMap.get(taskReceipt.getSkuId()) != null) {
                        if (StringUtil.isNotBlank(itemSkuMap.get(taskReceipt.getSkuId()).getSkuPic())) {
                            Map<String, String> picMap = JSONUtil.getMapStr(itemSkuMap.get(taskReceipt.getSkuId()).getSkuPic());
                            List<PicModel> picList = JSONUtil.getListObj(picMap.get("picList"), PicModel.class);
                            taskDailyDetailExt.setSkuPic(picList.get(0).getUrl());
                        }
                        taskDailyDetailExt.setUpc(itemSkuMap.get(taskReceipt.getSkuId()).getUpc());
                        taskDailyDetailExt.setCostPrice(itemSkuMap.get(taskReceipt.getSkuId()).getCostPrice());
                        taskDailyDetailExt.setScale(itemSkuMap.get(taskReceipt.getSkuId()).getScale());
                        taskDailyDetailExt.setColor(itemSkuMap.get(taskReceipt.getSkuId()).getColor());
                        if (taskReceipt.getQuantity() != null) {
                            taskDailyDetailExt.setQuantity(taskReceipt.getQuantity());
                        } else {
                            taskDailyDetailExt.setQuantity(0);
                        }
                        if (taskReceipt.getTransQuantity() != null) {
                            taskDailyDetailExt.setTransQuantity(taskReceipt.getTransQuantity());
                        } else {
                            taskDailyDetailExt.setTransQuantity(0);
                        }
                        taskDailyDetailExt.setReceiptId(taskReceipt.getId());
                        quantity = quantity + taskDailyDetailExt.getQuantity();
                        transQuantity = transQuantity + taskDailyDetailExt.getTransQuantity();
                        Integer allQuantity = quantity + transQuantity;
                        amount = amount.add(new BigDecimal(allQuantity).multiply(new BigDecimal(taskReceipt.getPrice().toString())));
                        taskQuantity = taskQuantity + taskDailyDetailExt.getQuantity();
                        taskTransQuantity = taskTransQuantity + taskDailyDetailExt.getTransQuantity();
                    }
                    taskDailyDetailExtList.add(taskDailyDetailExt);
                }
                taskDailyExt.setTaskDailyDetailExtList(taskDailyDetailExtList);
            }
            taskDailyExt.setAllInCount(taskTransQuantity + taskQuantity);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("amount", amount);
        result.put("quantity", quantity);
        result.put("transQuantity", transQuantity);
        result.put("taskDailyList", taskDailyExtList);
        result.put("pageNum", pageNum);
        result.put("pageSize", GlobalContants.pageSize);
        result.put("taskDoneNumber", taskDoneNumber);
        result.put("taskingNumber", taskingNumber);
        result.put("purchaseStorage", storage - taskDoneNumber);
        result.put("token", Md5Util.getMD5(JSONUtil.object2JSON(result)));
        return new SingleResult<>(result).toJSONString();
    }

    @SuppressWarnings({ "unlikely-arg-type", "rawtypes" })
    public List<TaskDailyExt> dealTaskExtList(List<TaskDailyExt> taskDailyExtList, String key, Integer status,
                                              Integer taskId, String resultType) {
        // 搜索词可能为采购任务名或者分配人及买手时 商品不按关键字过滤
        List<TaskDaily> taskDailyList = taskDailyMapper.selectAllByCondition(key, null, null, status, taskId, null,
                                                                             null);
        if (!taskDailyList.isEmpty()) {
            key = "";
        }
        List<TaskDailyDetail> taskDailyDetailList = new ArrayList<>();
        Map<Integer, Item> itemMap = new HashMap<>();
        Map<Integer, ItemSku> itemSkuMap = new HashMap<>();
        Map<Integer, TaskReceipt> taskReceiptMap = new HashMap<>();
        if (!taskDailyExtList.isEmpty()) {
            taskDailyDetailList = taskDailyDetailMapper.selectListByTaskDailyExtListAndKey(taskDailyExtList, key);
            if (!taskDailyDetailList.isEmpty()) {
                itemMap = itemMapper.selectMapByTaskDailyDetailList(taskDailyDetailList);
                itemSkuMap = itemSkuMapper.selectByTaskDailyDetailList(taskDailyDetailList);
                taskReceiptMap = taskReceiptMapper.countQuantityByTaskDailyDetailList(taskDailyDetailList);
            }
        }
        Iterator iter = taskDailyDetailList.iterator();
        for (TaskDailyExt taskDailyExt : taskDailyExtList) {
            List<TaskDailyDetailExt> taskDailyDetailExtList = new ArrayList<>();
            Integer transQuantity = 0;
            Integer quantity = 0;
            for (iter = taskDailyDetailList.iterator(); iter.hasNext();) {
                TaskDailyDetail taskDailyDetail = (TaskDailyDetail) iter.next();
                if (taskDailyDetail.getTaskId().equals(taskDailyExt.getId())) {
                    TaskDailyDetailExt taskDailyDetailExt = new TaskDailyDetailExt();
                    BeanCopier copy = BeanCopier.create(TaskDailyDetail.class, TaskDailyDetailExt.class, false);
                    copy.copy(taskDailyDetail, taskDailyDetailExt, null);
                    if (itemMap.get(taskDailyDetail.getItemId()) != null) {
                        if (StringUtil.isNotBlank(itemMap.get(taskDailyDetail.getItemId()).getMainPic())) {
                            Map<String, String> picMap = JSONUtil.getMapStr(itemMap.get(taskDailyDetail.getItemId()).getMainPic());
                            List<PicModel> picList = JSONUtil.getListObj(picMap.get("picList"), PicModel.class);
                            taskDailyDetailExt.setSkuPic(picList.get(0).getUrl());
                        }
                        taskDailyDetailExt.setName(itemMap.get(taskDailyDetail.getItemId()).getName());
                    }
                    if (itemSkuMap.get(taskDailyDetail.getSkuId()) != null) {
                        if (StringUtil.isNotBlank(itemSkuMap.get(taskDailyDetail.getSkuId()).getSkuPic())) {
                            Map<String, String> picMap = JSONUtil.getMapStr(itemSkuMap.get(taskDailyDetail.getSkuId()).getSkuPic());
                            List<PicModel> picList = JSONUtil.getListObj(picMap.get("picList"), PicModel.class);
                            taskDailyDetailExt.setSkuPic(picList.get(0).getUrl());
                        }
                        taskDailyDetailExt.setCostPrice(itemSkuMap.get(taskDailyDetail.getSkuId()).getCostPrice());
                        taskDailyDetailExt.setSalePrice(itemSkuMap.get(taskDailyDetail.getSkuId()).getSalePrice());
                        taskDailyDetailExt.setScale(itemSkuMap.get(taskDailyDetail.getSkuId()).getScale());
                        taskDailyDetailExt.setColor(itemSkuMap.get(taskDailyDetail.getSkuId()).getColor());
                    }
                    // 设置采购商品在途及线下数量
                    if (taskReceiptMap.get(taskDailyDetail.getId()) != null) {
                        taskDailyDetailExt.setTransQuantity(taskReceiptMap.get(taskDailyDetail.getId()).getTransQuantity());
                        taskDailyDetailExt.setQuantity(taskReceiptMap.get(taskDailyDetail.getId()).getQuantity());
                        taskDailyDetailExt.setPurchasePrice(taskReceiptMap.get(taskDailyDetail.getId()).getPrice());
                    }
                    // 获取采购单总商品采购数量及线下数量
                    transQuantity = transQuantity + taskDailyDetailExt.getTransQuantity();
                    quantity = quantity + taskDailyDetailExt.getQuantity();
                    taskDailyDetailExt.setStatusStr(TaskStatusEnum.of(taskDailyDetail.getStatus()).getDescription());
                    taskDailyDetailExt.setInCount(taskDailyDetailExt.getTransQuantity()
                                                  + taskDailyDetailExt.getQuantity());
                    if (null == taskDailyDetailExt.getInCount()) {
                        taskDailyDetailExt.setInCount(0);
                    }
                    if ("task".equals(resultType)) {
                        taskDailyDetailExt.setTransQuantity(0);
                        taskDailyDetailExt.setQuantity(0);
                    }
                    taskDailyDetailExtList.add(taskDailyDetailExt);
                    iter.remove();
                }
            }
            taskDailyExt.setTaskDailyDetailExtList(taskDailyDetailExtList);
            taskDailyExt.setAllInCount(quantity + transQuantity);
        }
        return taskDailyExtList;
    }

    @RequestMapping("/calc")
    public String calc(Long[] taskDetailId, Integer[] transQuantity, Integer[] quantity, String[] skuBuysite,
                       Long buyerId, Double[] purchasePrice, long[] skuId) {
        if (taskDetailId == null || taskDetailId.length == 0) {
            return new ErrorResult(RetCode.SYS_ERROR).toJSONString();
        }
        int result = 0;
        for (int i = 0; i < taskDetailId.length; i++) {
            String skuBuysiteStr = "";
            if (null != skuBuysite && skuBuysite.length > i) {
                skuBuysiteStr = skuBuysite[i];
            }
            Boolean flag = dealCalc(taskDetailId[i], transQuantity[i], quantity[i], skuBuysiteStr, skuId[i],
                                    purchasePrice[i], buyerId);
            if (flag == null && taskDetailId.length == 1) {
                return new ErrorResult(RetCode.SYS_ERROR, "采购数量已上限").toJSONString();
            }
            if (flag) {
                result++;
            }
        }
        if (result == 0) {
            return new ErrorResult(RetCode.SYS_ERROR).toJSONString();
        }
        return new SuccessResult().toJSONString();
    }

    @Transactional
    public Boolean dealCalc(Long taskDetailId, Integer transQuantity, Integer quantity, String skuBuysite, Long skuId,
                            Double purchasePrice, Long buyerId) {
        TaskDailyDetail taskDailyDetail = taskDailyDetailMapper.selectByPrimaryKey(taskDetailId);
        TaskDaily taskDaily = new TaskDaily();
        taskDaily.setStatus(2);
        taskDaily.setId(taskDailyDetail.getTaskId());
        taskDailyMapper.updateByPrimaryKeySelective(taskDaily);
        TaskReceipt taskReceiptCount = taskReceiptMapper.countAllQuantity(taskDetailId);
        if (taskReceiptCount.getQuantity() + taskReceiptCount.getTransQuantity()
            + transQuantity > taskDailyDetail.getCount()) {
            return null;
        }
        Receipt receipt = new Receipt();
        receipt.setGmtCreate(new Date());
        receipt.setGmtModify(new Date());
        receipt.setBuyerId(buyerId);
        Long RECOSequence = receiptMapper.gainRECOSequence();
        receipt.setTaskDailyId(taskDailyDetail.getTaskId());
        receipt.setTotalPrice(transQuantity * purchasePrice);
        String receiptNo = "RE" + TimeUtil.getDate(TimeUtil.DEFAULT_DAY_NO_SEPRATOR_FORMAT, new Date()) + "U"
                           + String.format("%0" + 4 + "d", receipt.getBuyerId()) + RECOSequence;
        receipt.setReceiptNo(receiptNo);
        int result = receiptMapper.insertSelective(receipt);
        if (result < 1) {
            return false;
        }
        TaskReceipt taskReceipt = new TaskReceipt();
        taskReceipt.setReceiptId(receipt.getId());
        taskReceipt.setReceiptNo(receipt.getReceiptNo());
        taskReceipt.setTransQuantity(transQuantity);
        taskReceipt.setQuantity(quantity);
        taskReceipt.setSkuBuysite(skuBuysite);
        WxPurchaseUser wxPurchase = wxPurchaseUserMapper.selectByPrimaryKey(buyerId);
        if (wxPurchase != null) {
            taskReceipt.setUserCreate(wxPurchase.getNickName());
            taskReceipt.setUserModify(TimeUtil.getCurrentDateDefaultString());
        }
        taskReceipt.setSkuId(skuId);
        taskReceipt.setGmtCreate(new Date());
        taskReceipt.setPrice(purchasePrice);
        taskReceipt.setGmtModify(new Date());
        taskReceipt.setTaskDailyDetailId(taskDetailId);
        ItemSku itemSku = itemSkuMapper.selectByPrimaryKey(skuId);
        taskReceipt.setItemId(itemSku.getItemId());
        taskReceipt.setUpc(itemSku.getUpc());
        result = taskReceiptMapper.insertSelective(taskReceipt);
        if (result < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

}
