package com.wangqin.globalshop.purchase.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemFindDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemCategoryDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapper.ItemFindDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemCategoryDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemFindDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuDOMapperExt;
import com.wangqin.globalshop.common.aliyun.OSSObjectApi;
import com.wangqin.globalshop.common.result.RetCode;
import com.wangqin.globalshop.common.utils.ErrorResult;
import com.wangqin.globalshop.common.utils.JSONUtil;
import com.wangqin.globalshop.common.utils.Md5Util;
import com.wangqin.globalshop.common.utils.PicModel;
import com.wangqin.globalshop.common.utils.ResourceUtil;
import com.wangqin.globalshop.common.utils.SingleResult;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.common.utils.SuccessResult;
import com.wangqin.globalshop.common.utils.TimeUtil;
import com.wangqin.globalshop.common.utils.UploadUtil;
import com.wangqin.globalshop.purchase.model.MainPicModel;

/**
 * 发现功能模块 类ApiFindController.java的实现描述：
 * 
 * @author sivanblack 2018年4月24日 上午10:38:55
 */
@Controller
@ResponseBody
@RequestMapping("/api/find")
public class ApiFindController {

    @Autowired
    ItemDOMapperExt     itemDOMapper;
    @Autowired
    ItemFindDOMapperExt itemFindDOMapperExt;
    @Autowired
    ItemSkuDOMapperExt  itemSkuDOMapperExt;
    @Autowired
    ItemCategoryDOMapperExt itemCategoryDOMapperExt;

    /**
     * 删除发现商品
     * 
     * @param id
     * @return
     */
    @Transactional
    @RequestMapping("/delItem")
    public String delItem(Long id) {
        if (id == null) {
            return new ErrorResult(RetCode.ILLEGAL_ARGS, "参数错误").toJSONString();
        }
        ItemFindDO itemFindDO = itemFindDOMapperExt.selectByPrimaryKey(id);
        itemFindDO.setIsDel(true);
        int result = itemFindDOMapperExt.updateByPrimaryKeySelective(itemFindDO);
        if (result > 0) {
            return new SuccessResult().toJSONString();
        }
        return new ErrorResult(RetCode.SYS_ERROR, "删除失败").toJSONString();
    }

    /**
     * 发现商品详情
     * 
     * @param id
     * @return
     */
    @RequestMapping("/detail")
    public String detail(Long id) {
        if (null == id) {
            return new ErrorResult(RetCode.ILLEGAL_ARGS, "参数错误").toJSONString();
        }
        ItemFindDO findItem = itemFindDOMapperExt.selectByPrimaryKey(id);
        ItemDO item= itemDOMapper.selectByItemCode(findItem.getItemCode());
        if (findItem == null) {
            return new ErrorResult(RetCode.NO_DATA, "该商品已删除").toJSONString();
        }
        Map<String, Object> result = new HashMap<>();
        List<ItemSkuDO> findItemSkuList = itemSkuDOMapperExt.selectByItemCode(findItem.getItemCode());
        ItemCategoryDO category = itemCategoryDOMapperExt.selectByCode(item.getCategoryCode());
        FindItemExt findItemExt = new FindItemExt();
        BeanCopier copy = BeanCopier.create(FindItem.class, FindItemExt.class, false);
        copy.copy(findItem, findItemExt, null);
        findItemExt.setStartDateStr(TimeUtil.getDate(TimeUtil.DEFAULT_DAY_FORMAT, findItem.getStartDate()));
        findItemExt.setEndDateStr(TimeUtil.getDate(TimeUtil.DEFAULT_DAY_FORMAT, findItem.getEndDate()));
        if (StringUtil.isNotBlank(findItem.getMainPic())) {
            Map<String, String> picMap = JSONUtil.getMapStr(findItem.getMainPic());
            List<PicModel> picList = JSONUtil.getListObj(picMap.get("picList"), PicModel.class);
            String[] pictureArr = new String[picList.size()];
            for (int i = 0; i < picList.size(); i++) {
                pictureArr[i] = picList.get(i).getUrl();
            }
            findItemExt.setPictureArr(pictureArr);
            findItemExt.setPictureList(picList);
        }
        result.put("item", findItemExt);
        result.put("category", category);
        result.put("itemSkuList", findItemSkuList);
        return new SingleResult<>(result).toJSONString();
    }

    /**
     * 审核
     * 
     * @param id
     * @param buyerId
     * @param status
     * @return
     */
    @Transactional
    @RequestMapping("/audit")
    public String audit(Long id, Long buyerId, Integer status, String reason, Integer[] skuAmount, float[] skuLimit,
                        String[] skuUpc, String[] skuCode) {
        if (id == null || buyerId == null || status == null) {
            return new ErrorResult(RetCode.ILLEGAL_ARGS, "参数错误").toJSONString();
        }
        WxPurchaseUser wxPurchaseUser = wxPurchaseUserMapper.selectByPrimaryKey(buyerId);
        if (wxPurchaseUser == null) {
            return new ErrorResult(RetCode.WRONG_DATA, "买手不存在").toJSONString();
        }
        Long companyId = wxPurchaseUser.getCompanyId();
        FindItem findItem = new FindItem();
        findItem.setBuyerId(buyerId);
        findItem.setId(id);
        findItem.setPurchaseStatus(status);
        findItem.setUserModify(wxPurchaseUser.getNickName());
        findItem.setRefuseReason(reason);
        if (status.equals(-1) && StringUtil.isBlank(reason)) {
            findItem.setRefuseReason("快速拒绝");
        }
        int result = findItemMapper.updateByPrimaryKeySelective(findItem);
        findItem = findItemMapper.selectByPrimaryKey(id);
        if (result > 0) {
            if (status.equals(1)) {
                Item item = new Item();
                Map<String, ItemSku> itemSkuTempMap = itemSkuMapper.selectBySkuUpcArr(skuUpc);
                for (Map.Entry<String, ItemSku> entry : itemSkuTempMap.entrySet()) {
                    item = itemMapper.selectByPrimaryKey(entry.getValue().getItemId());
                    break;
                }
                if (itemSkuTempMap.isEmpty()) {
                    BeanCopier copy = BeanCopier.create(FindItem.class, Item.class, false);
                    copy.copy(findItem, item, null);
                    item.setCompanyId(companyId);
                    item.setId(null);
                    item.setFindItemId(findItem.getId());
                    result = itemMapper.insertSelective(item);
                    if (result < 1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return new ErrorResult(RetCode.SYS_ERROR, "插入商品失败").toJSONString();
                    }
                    item = itemMapper.selectByPrimaryKey(item.getId());
                }
                List<FindItemSku> findItemSkuList = findItemSkuMapper.selectByItemId(id);
                List<ItemSku> itemSkuList = new ArrayList<>();
                Long itemId = item.getId();
                for (FindItemSku findItemSku : findItemSkuList) {
                    if (itemSkuTempMap.get(findItemSku.getUpc()) != null) {
                        continue;
                    }
                    ItemSku itemSku = new ItemSku();
                    BeanCopier beanCopy = BeanCopier.create(FindItemSku.class, ItemSku.class, false);
                    beanCopy.copy(findItemSku, itemSku, null);
                    itemSku.setItemId(itemId);
                    itemSku.setId(null);
                    if (itemSku.getVirtualInv() == null) {
                        itemSku.setVirtualInv(0L);
                    }
                    itemSku.setCompanyId(companyId);
                    itemSkuList.add(itemSku);
                }
                if (!itemSkuList.isEmpty()) {
                    result = itemSkuMapper.insertList(itemSkuList);
                    if (result < 1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return new ErrorResult(RetCode.SYS_ERROR, "插入商品规格失败").toJSONString();
                    }
                }
                if (status.equals(1)) {
                    TaskDaily taskDaily = new TaskDaily();
                    Long nestTask = taskDailyMapper.gainTASKSequence();
                    taskDaily.setBuyerId(buyerId);
                    taskDaily.setBuyerName(wxPurchaseUser.getNickName());
                    taskDaily.setImageUrl(findItem.getMainPic());
                    taskDaily.setGmtCreate(new Date());
                    taskDaily.setGmtModify(new Date());
                    taskDaily.setOwnerId(1L);
                    taskDaily.setOwnerName("admin");
                    taskDaily.setStatus(0);
                    taskDaily.setTaskTitle(findItem.getName() + "采购任务");
                    taskDaily.setTaskDesc(taskDaily.getTaskTitle());
                    taskDaily.setTaskStartTime(new Date());
                    taskDaily.setTaskEndTime(TimeUtil.calDate(new Date(), 7, "D"));
                    String taskOrderNo = "T" + TimeUtil.getDate(TimeUtil.DEFAULT_DATE_NO_SEPRATORS_FORMAT, new Date())
                                         + "U" + String.format("%0" + 4 + "d", taskDaily.getOwnerId()) + nestTask;
                    taskDaily.setTaskOrderNo(taskOrderNo);
                    result = taskDailyMapper.insertSelective(taskDaily);
                    if (result < 1) {
                        return new ErrorResult(RetCode.SYS_ERROR, "生成采购任务失败").toJSONString();
                    }
                    List<TaskDailyDetail> taskDailyDetailList = new ArrayList<>();
                    Map<String, ItemSku> itemSkuMap = itemSkuMapper.selectMapByItemId(item.getId());
                    for (int i = 0; i < skuUpc.length; i++) {
                        TaskDailyDetail taskDailyDetail = new TaskDailyDetail();
                        taskDailyDetail.setBuyerId(buyerId);
                        taskDailyDetail.setBuyerName(wxPurchaseUser.getNickName());
                        taskDailyDetail.setCount(skuAmount[i]);
                        taskDailyDetail.setGmtCreate(new Date());
                        taskDailyDetail.setGmtModify(new Date());
                        taskDailyDetail.setInCount(0);
                        taskDailyDetail.setCount(skuAmount[i]);
                        taskDailyDetail.setTaskId(taskDaily.getId());
                        taskDailyDetail.setItemId(item.getId());
                        taskDailyDetail.setOwnerId(1L);
                        taskDailyDetail.setTaskMaxPrice(skuLimit[i]);
                        taskDailyDetail.setOwnerName("admin");
                        taskDailyDetail.setTaskOrderNo(taskOrderNo);
                        taskDailyDetail.setUpc(skuUpc[i]);
                        taskDailyDetail.setSkuCode(skuCode[i]);
                        if (itemSkuMap.get(skuUpc[i]) != null) {
                            taskDailyDetail.setSkuId(itemSkuMap.get(skuUpc[i]).getId());
                            taskDailyDetailList.add(taskDailyDetail);
                        }
                    }
                    result = taskDailyDetailMapper.insertList(taskDailyDetailList);
                    if (result < 1) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return new ErrorResult(RetCode.SYS_ERROR, "分配采购任务失败").toJSONString();
                    }
                }
            }
            return new SuccessResult().toJSONString();
        }
        return new ErrorResult(RetCode.SYS_ERROR, "修改失败").toJSONString();
    }


    /**
     * 保存发现商品
     * 
     * @param request
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    @Transactional
    @RequestMapping("/save")
    public String save(HttpServletRequest request) throws IOException {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        body = stringBuilder.toString();
        if (StringUtil.isBlank(body)) {
            return new ErrorResult(RetCode.SYS_ERROR, "保存失败").toJSONString();
        }
        Map<String, Object> resMap = (HashMap<String, Object>) JSON.parseObject(body, HashMap.class);
        FindItem findItem = JSONUtil.getObj(body, FindItem.class);
        Category category = categoryMapper.selectByPrimaryKey(findItem.getCategoryId());
        List<PicModel> picList = new ArrayList<>();
        String mainPic = resMap.get("pictureList").toString();
        String[] picArray = mainPic.split(";");
        for (int i = 0; i < picArray.length; i++) {
            PicModel picModel = new PicModel();
            picModel.setType("image/jpeg");
            picModel.setUid("i_" + i);
            picModel.setUrl(picArray[i]);
            picList.add(picModel);
        }
        MainPicModel mainPicModel = new MainPicModel();
        mainPicModel.setMainPicNum(picArray.length);
        mainPicModel.setPicList(picList);
        findItem.setMainPic(JSONUtil.object2JSON(mainPicModel));
        findItem.setCategoryName(category.getName());
        findItem.setGmtCreate(new Date());
        findItem.setGmtModify(new Date());
        String categoryCode = category.getCategoryCode();
        String itemCode = findItemMapper.selectNextval();
        WxPurchaseUser wxPurchaseUser = wxPurchaseUserMapper.selectByPrimaryKey(findItem.getBuyerId());
        if (wxPurchaseUser != null) {
            findItem.setBuyerName(wxPurchaseUser.getNickName());
        }
        findItem.setItemCode("I" + categoryCode + "Q" + itemCode);
        int result = 0;
        List<FindItemSku> findItemSkuList = JSONUtil.getListObj(resMap.get("skuInfo").toString(), FindItemSku.class);
        // 设置价格区间
        if (findItemSkuList.size() == 1) {
            findItem.setPriceRange(findItemSkuList.get(0).getPurchasePrice().toString());
        } else {
            Double maxPurchasePrice = 0.00;
            Double minPurchasePrice = 0.00;
            for (FindItemSku findItemSku : findItemSkuList) {
                if (findItemSku.getPurchasePrice() != null) {
                    if (maxPurchasePrice.compareTo(findItemSku.getPurchasePrice()) < 0) {
                        maxPurchasePrice = findItemSku.getPurchasePrice();
                    }
                    if (minPurchasePrice.compareTo(findItemSku.getPurchasePrice()) > 0) {
                        minPurchasePrice = findItemSku.getPurchasePrice();
                    }
                }
            }
            if (minPurchasePrice.compareTo(0.00) > 0 && maxPurchasePrice.compareTo(0.00) > 0) {
                findItem.setPriceRange(minPurchasePrice + "~" + maxPurchasePrice);
            }
        }
        if (findItem.getId() == null) {
            result = findItemMapper.insertSelective(findItem);
        } else {
            result = findItemMapper.updateByPrimaryKeySelective(findItem);
        }
        if (result < 1) {
            return new ErrorResult(RetCode.SYS_ERROR).toJSONString();
        }
        result = findItemSkuMapper.deleteByItemId(findItem.getId());
        int i = 0;
        for (FindItemSku findItemSku : findItemSkuList) {
            findItemSku.setCategoryId(findItem.getCategoryId());
            findItemSku.setBrand(findItem.getBrand());
            findItemSku.setBuySite(findItem.getBuySite());
            findItemSku.setGmtCreate(new Date());
            findItemSku.setGmtModify(new Date());
            findItemSku.setItemCode(findItem.getItemCode());
            findItemSku.setItemId(findItem.getId());
            findItemSku.setItemName(findItem.getName());
            if (StringUtil.isBlank(findItemSku.getSkuPic())) {
                findItemSku.setSkuPic(findItem.getMainPic());
            }
            findItemSku.setSkuCode("S" + findItem.getItemCode().substring(1) + String.format("%0" + 4 + "d", ++i));
            findItemSku.setUserCreate(wxPurchaseUser.getNickName());
            i++;
        }
        result = findItemSkuMapper.insertList(findItemSkuList);
        if (result < 1) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ErrorResult(RetCode.SYS_ERROR, "保存失败").toJSONString();
        }
        return new SuccessResult().toJSONString();
    }

    @SuppressWarnings("unlikely-arg-type")
    @RequestMapping("/list")
    public String list(Integer pageNum, Integer status, String orderTimeType, String key) {
        if (pageNum == null || pageNum < 0) {
            pageNum = 0;
        }
        Map<String, Object> result = new HashMap<>();
        result.put("pageNum", pageNum);
        List<FindItem> findItemList = findItemMapper.selectByConditionOrderByTime(status, key, orderTimeType,
                                                                                  pageNum * GlobalContants.pageSize,
                                                                                  GlobalContants.pageSize);
        Map<Integer, FindItemSku> findItemSkuMap = new HashMap<>();
        Map<Integer, WxPurchaseUser> wxPurchaseUserMap = new HashMap<>();
        if (!findItemList.isEmpty()) {
            findItemSkuMap = findItemSkuMapper.selectByItemList(findItemList);
            wxPurchaseUserMap = wxPurchaseUserMapper.selectByFindItemList(findItemList);
        }
        Integer countAll = findItemMapper.countByPurchaseStatus(null, null);
        Integer audit = findItemMapper.countByPurchaseStatus(FindItemPurchanseStatusEnum.audit.getCode(), null);
        Integer draft = findItemMapper.countByPurchaseStatus(FindItemPurchanseStatusEnum.draft.getCode(), null);
        Integer pageCount = findItemMapper.countByPurchaseStatus(status, key);
        pageCount = PageUtil.countTotalPage(pageCount);
        result.put("pageCount", pageCount);
        result.put("countAll", countAll);
        result.put("audit", audit);
        result.put("draft", draft);
        List<FindItemExt> findItemExtList = new ArrayList<>();
        Date date = new Date();
        for (FindItem findItem : findItemList) {
            FindItemExt findItemExt = new FindItemExt();
            BeanCopier copy = BeanCopier.create(FindItem.class, FindItemExt.class, false);
            copy.copy(findItem, findItemExt, null);
            if (StringUtil.isNotBlank(findItem.getMainPic())) {
                Map<String, String> picMap = JSONUtil.getMapStr(findItem.getMainPic());
                List<PicModel> picList = JSONUtil.getListObj(picMap.get("picList"), PicModel.class);
                findItemExt.setSkuPic(picList.get(0).getUrl());
            }
            if (findItemSkuMap.get(findItem.getId()) != null
                && findItemSkuMap.get(findItem.getId()).getCostPrice().compareTo(0.00) > 0) {
                findItemExt.setCostPrice(findItemSkuMap.get(findItem.getId()).getCostPrice());
            }
            if (wxPurchaseUserMap.get(findItem.getBuyerId()) != null) {
                findItemExt.setBuyerAvatar(wxPurchaseUserMap.get(findItem.getBuyerId()).getAvatarUrl());
            }
            findItemExt.setDay(TimeUtil.getDatePoor(findItem.getEndDate(), date, "day"));
            findItemExt.setHours(TimeUtil.getDatePoor(findItem.getEndDate(), date, "hour"));
            findItemExt.setMinutes(TimeUtil.getDatePoor(findItem.getEndDate(), date, "minute"));
            findItemExtList.add(findItemExt);
        }
        result.put("findItemList", findItemExtList);
        result.put("token", Md5Util.getMD5(JSONUtil.object2JSON(result)));
        return new SingleResult<>(result).toJSONString();
    }

    /**
     * 上传图片
     * 
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload")
    public String uploadAvatar(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        map = UploadUtil.upload(request, "item");
        List<String> pictureList = new ArrayList<>();
        Map<String, Object> results = new HashMap<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String filePath = entry.getValue();
            File tempFile = new File(ResourceUtil.getImageUploadPath() + filePath);
            StringBuilder fileUrlStrBuf = new StringBuilder("");
            String imgName = UUID.randomUUID().toString();
            fileUrlStrBuf.append(imgName + ".jpg");
            FileInputStream fileStream = null;
            boolean result = false;
            try {
                fileStream = new FileInputStream(tempFile);
                result = OSSObjectApi.uploadFile(fileStream, tempFile.length(), null, fileUrlStrBuf.toString());
                FileUtils.forceDelete(tempFile);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fileStream != null) {
                    try {
                        fileStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (result) {
                pictureList.add(ResourceUtil.getImageDomain() + "/" + fileUrlStrBuf.toString());
            }
        }
        if (pictureList.isEmpty()) {
            return new ErrorResult(RetCode.SYS_ERROR, "上传失败").toJSONString();
        }
        results.put("pictureList", pictureList);
        return new SingleResult<>(results).toJSONString();
    }

    @RequestMapping("/skuList")
    public String skuList(String itemCode) {
        if (itemCode == null) {
            return new ErrorResult(RetCode.ILLEGAL_ARGS).toJSONString();
        }
        List<ItemSkuDO> findItemSkuList = itemSkuDOMapperExt.selectByItemCode(itemCode);
        Map<String, Object> result = new HashMap<>(1);
        result.put("skuList", findItemSkuList);
        return new SingleResult<>(result).toJSONString();
    }
    
    /**
     * 扫码返回
     * 
     * @param upc
     * @return
     */
    @RequestMapping("/scan")
    public String scan(String upc) {
        ItemSkuDO findItemSku = itemSkuDOMapperExt.selectByUpc(upc);
        if (findItemSku == null) {
            return new ErrorResult(RetCode.NO_DATA, "商品不存在数据").toJSONString();
        }
        return new SingleResult<>(findItemSku.getItemCode()).toJSONString();
    }

}
