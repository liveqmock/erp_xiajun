package com.wangqin.globalshop.item.app.controller;


import java.io.IOException;

import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.wangqin.globalshop.biz1.app.bean.dataVo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemQrcodeShareDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemQrcodeShareDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.ItemStatus;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.common.enums.AppletType;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.DimensionCodeUtil;
import com.wangqin.globalshop.common.utils.EasyuiJsonResult;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.ImageUtil;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.common.utils.PriceUtil;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.common.utils.excel.ReadExcel;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.item.app.service.IAppletConfigService;
import com.wangqin.globalshop.item.app.service.IItemBrandService;
import com.wangqin.globalshop.item.app.service.IItemCategoryService;
import com.wangqin.globalshop.item.app.service.IItemCompanyService;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.item.app.service.IItemSkuScaleService;
import com.wangqin.globalshop.item.app.service.IItemSkuService;

import com.wangqin.globalshop.item.app.util.ItemUtil;

import net.sf.json.JSONObject;

/**
 * 商品处理器
 *
 * @author xiajun
 */

@Authenticated
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private IItemBrandService brandService;
    @Autowired
    private IItemCategoryService categoryService;
    @Autowired
    private IItemService itemService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private IItemSkuService itemSkuService;
    @Autowired
    private IItemSkuScaleService scaleService;
    @Autowired
    private IAppletConfigService appletConfigService;
    @Autowired
    private IItemCompanyService companyService;
    @Autowired
    private ItemQrcodeShareDOMapperExt shareMapperExt;

    public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String ACCESS_TOKEN_PART = "grant_type=client_credential&appid=";
    public static final String ACCESS_TOKEN_MI = "&secret=";


    /**
     * 商品管理->商品列表->商品发布
     *
     * @param
     */
    @RequestMapping("/add")
    @Transactional(rollbackFor = BizCommonException.class)
    public Object add(ItemQueryVO item) {
        //log.info("---->start to add item---->");
        return itemService.addItem(item);
    }

    /**
     * 商品管理->商品列表->操作->编辑->确定
     *
     * @param
     * @return
     */
    @RequestMapping("/update")
    @Transactional(rollbackFor = BizCommonException.class)
    public Object update(ItemQueryVO item) {
        JsonResult<ItemDO> result = new JsonResult<>();
        if (!loginCheck()) {
            return result.buildIsSuccess(false).buildMsg("请先登录");
        }

        String companyNo = AppUtil.getLoginUserCompanyNo();
        String userNo = AppUtil.getLoginUserId();
        String itemCode = itemService.queryItemCodeById(item.getId());

        //商品图片不能为空，TODO：在前端处理
        if (!ItemUtil.picCheck(item.getMainPic())) {
            return result.buildIsSuccess(false).buildMsg("商品必须有主图");
        }

        String skuList = item.getSkuList();
        List<Double> skuSalePriceList = new ArrayList<Double>();

        try {
            String s = skuList.replace("&quot;", "\"");
            List<ItemSkuQueryVO> skus = HaiJsonUtils.toBean(s, new TypeReference<List<ItemSkuQueryVO>>() {});
            //更新商品的价格区间,同时判断upc和数据库里面已有的upc是否重复         
            for (ItemSkuQueryVO sku : skus) {
                Integer dupNum = itemSkuService.queryRecordCountByUpcCompanyNotInSameItem(companyNo, sku.getUpc(), itemCode);
                if (0 < dupNum) {
                    return result.buildIsSuccess(false).buildMsg("更新失败，添加的upc和已有的upc重复");
                }
                skuSalePriceList.add(sku.getSalePrice());
            }  	
            int startIndex = 0;
            //获取现在该商品的所有skuCode
            List<String> newSkuCodeList = new ArrayList<String>();
            for (ItemSkuQueryVO skuQueryVO : skus) {
                if (null != skuQueryVO.getSkuCode()) {
                    newSkuCodeList.add(skuQueryVO.getSkuCode());
                }
            }
            //两个集合求差值，判断哪些sku被删除了
            List<String> deleteCodeList = itemSkuService.queryToDeleteSkuCodeList(newSkuCodeList, itemCode);
            //step1:删除需要删除的sku
            for (String skuCode : deleteCodeList) {
                itemSkuService.deleteItemSkuBySkuCode(skuCode);
                //删除虚拟库存TODO,暂时用更新虚拟库存为0代替
                try {
                    inventoryService.updateVirtualInv(skuCode, 0L, AppUtil.getLoginUserCompanyNo());
                } catch (Exception e) {
                    return result.buildIsSuccess(false).buildMsg("您试图删除不能删除的sku，这样的操作导致了库存异常");
                }
                //删除规格
                scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "颜色");
                scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "尺寸");
            }

            //step2:更新需要更新的sku
            for (ItemSkuQueryVO updateSku : skus) {
                if (null != updateSku.getSkuCode()) {//需要更新的sku
                    //先更新虚拟库存
                    String skuCode = updateSku.getSkuCode();
                    if (null != updateSku.getVirtualInv()) {
                        try {
                            inventoryService.updateVirtualInv(skuCode, updateSku.getVirtualInv(), AppUtil.getLoginUserCompanyNo());
                        } catch (BizCommonException e) {
                            return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
                        }
                    }
                    //再更新规格，先删除后插入
                    scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "颜色");
                    if (IsEmptyUtil.isStringNotEmpty(updateSku.getColor())) {
                    	ItemSkuScaleDO color = ItemUtil.genScaleDO(companyNo,userNo,"颜色",updateSku.getColor(),skuCode,itemCode); 
                        scaleService.insertSelective(color);
                    }
                    scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "尺寸");
                    if (IsEmptyUtil.isStringNotEmpty(updateSku.getScale())) {
                        ItemSkuScaleDO scale = ItemUtil.genScaleDO(companyNo,userNo,"尺寸",updateSku.getScale(),skuCode,itemCode);                      
                        scaleService.insertSelective(scale);
                    }

                    //最后更新其他的sku项目
                    updateSku.setBrand(item.getBrand());
                    updateSku.setItemName(item.getName());
                    updateSku.setCategoryCode(item.getCategoryCode());
                    updateSku.setCategoryName(categoryService.queryByCategoryCode(item.getCategoryCode()).getName());
                    updateSku.setSkuRate(ItemUtil.divideOneHundred(updateSku.getSkuRateString()));
                    itemSkuService.updateById(updateSku);
                }
            }
            //step3:插入新增的sku
            for (ItemSkuQueryVO newSku : skus) {
                if (null == newSku.getSkuCode()) {//需要添加的sku
                    ItemSkuDO addSku = new ItemSkuDO();
                    ItemDTO itemDTO = itemService.queryItemById(item.getId());
                    addSku.setCompanyNo(companyNo);
                    addSku.setItemCode(itemCode);
                    //todo skuCode
                    String skuCode = "S" + item.getCategoryCode() + "T" + RandomUtils.getTimeRandomMillSeconds() + "Q" + String.format("%0" + 2 + "d", (startIndex++));
                    addSku.setSkuCode(skuCode);
                    addSku.setSkuRate(newSku.getSkuRate());
                    addSku.setScale(newSku.getScale());
                    addSku.setSalePrice((double) newSku.getSalePrice());
                    addSku.setWeight(newSku.getWeight());
                    addSku.setUpc(newSku.getUpc());
                    addSku.setSkuPic(newSku.getSkuPic());
                    addSku.setPackageLevelId(newSku.getPackageLevelId());
                    addSku.setCreator(userNo);
                    addSku.setModifier(userNo);
                    addSku.setItemName(itemDTO.getName());
                    addSku.setCategoryCode(itemDTO.getCategoryCode());
                    addSku.setCategoryName(itemDTO.getCategoryName());
                    addSku.setSkuRate(ItemUtil.divideOneHundred(newSku.getSkuRateString()));
                    //插入item_sku_scale表                                       
                    if (IsEmptyUtil.isStringNotEmpty(newSku.getColor())) {
                        ItemSkuScaleDO color = ItemUtil.genScaleDO(companyNo,userNo,"颜色",newSku.getColor(),skuCode,itemCode);
                        scaleService.insertSelective(color);
                    }
                    if (IsEmptyUtil.isStringNotEmpty(newSku.getScale())) {
                    	ItemSkuScaleDO scale = ItemUtil.genScaleDO(companyNo,userNo,"尺寸",newSku.getScale(),skuCode,itemCode);
                        scaleService.insertSelective(scale);
                    }
                    //插入库存
                    List<InventoryDO> inventoryList = new ArrayList<InventoryDO>();
                    InventoryDO inventory = new InventoryDO();
                    inventory.setItemName(item.getName());
                    inventory.setItemCode(itemDTO.getItemCode());
                    inventory.setSkuCode(addSku.getSkuCode());
                    inventory.setUpc(newSku.getUpc());
                    inventory.setVirtualInv(Long.valueOf(newSku.getVirtualInv()));
                    inventoryList.add(inventory);
                    inventoryService.outbound(inventoryList);
                    //插入sku
                    itemSkuService.insertItemSkuSelective(addSku);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result.buildMsg("解析SKU错误").buildIsSuccess(false);
        }

        String imgJson = ImageUtil.getImageUrl(item.getMainPic());
        item.setMainPic(imgJson);

        ItemDO newItem = new ItemDO();
        //上架处理
        try {
            ItemUtil.handleShelf(item, newItem);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg("上架时间填写错误");
        }

        newItem.setIsAbroad(item.getIsAbroad());
        newItem.setDetail(item.getDetail());
        detailDecoder(newItem);
        newItem.setCategoryCode(item.getCategoryCode());
        newItem.setCategoryName(categoryService.queryByCategoryCode(item.getCategoryCode()).getName());
        newItem.setBrandName(item.getBrand());
        newItem.setBrandNo(brandService.selectBrandNoByName(item.getBrand().split("->")[0]));
        newItem.setItemName(item.getName());
        newItem.setIdCard(item.getIdCard().byteValue());
        newItem.setPriceRange(PriceUtil.calNewPriceRange(skuSalePriceList));
        newItem.setCountry(item.getCountry());
        newItem.setMainPic(item.getMainPic());
        newItem.setWxisSale(item.getWxisSale().byteValue());
        newItem.setModifier(AppUtil.getLoginUserId());
        newItem.setId(item.getId());//根据id更新
        itemService.updateByIdSelective(newItem);
        return result.buildIsSuccess(true);
    }

    
    /**
     * 封装ItemSkuScala对象信息
     *
     * @param obj     封装的对象
     * @param itemSku
     * @param value   scalaValue
     * @param name    scalaName
     * @author ChenZiHao
     */
    private void setInfo(ItemSkuScaleDO obj, ItemSkuDO itemSku, String value, String name) {
        obj.setSkuCode(itemSku.getSkuCode());
        obj.setItemCode(itemSku.getItemCode());
        obj.setScaleCode(CodeGenUtil.getScaleCode());
        obj.setScaleName(name);
        obj.setScaleValue(value);
        obj.init();

    }

    /*
     *商品详情处理
     */
    private void detailDecoder(ItemDO item) {
        if (StringUtils.isNotBlank(item.getDetail())) {
            String detail = item.getDetail();
            try {
                String deStr = URLDecoder.decode(detail, "UTF-8");
                item.setDetail(deStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 商品管理->商品列表->操作->编辑
     *
     * @param id
     * @return
     */
    @RequestMapping("/query")
    @Transactional(rollbackFor = BizCommonException.class)
    public Object query(Long id) {
        JsonResult<ItemDTO> result = new JsonResult<>();

        if (!loginCheck()) {
            return result.buildIsSuccess(false).buildMsg("请先登录");
        }

        ItemDTO item = itemService.queryItemById(id);
        if (item == null) {
            result.buildIsSuccess(false).buildMsg("没有找到商品，商品可能已删除");
        }
        //获取商品类目的Id
        Long categoryId = categoryService.queryCategoryByCategoryCode(item.getCategoryCode()).getId();
        item.setCategoryId(categoryId);

        result.setData(item);
        return result.buildIsSuccess(true);
    }

    /**
     * 商品批量上架
     *
     * @param itemIdStrs 要上架的商品ID集合 ，分隔符   123,222,132
     * @return
     */
    @RequestMapping("/itemsListing")
    public Object itemsListing(String itemIdStrs) {
        //logger.info("itemsListing start");
        JsonResult<String> result = new JsonResult<>();
        //查询出商品，查看商品状态，新档或者下架的商品可以上架
        if (itemIdStrs != null) {
            String[] items = itemIdStrs.split(",");
            List<Long> itemIds = new ArrayList<>();
            if (items != null && items.length > 0) {

                for (int i = 0; i < items.length; i++) {
                    Long one = Long.valueOf(items[i]);
                    itemIds.add(one);
                }
                List<ItemDO> itemList = itemService.queryItems(itemIds);
                //获取有赞的num_iid;

                //调取有赞批量上架接口


            } else {
                return result.buildIsSuccess(false);
            }
        } else {
            return result.buildIsSuccess(false);
        }
//		return result.buildIsSuccess(true);
        return result;
    }

    /**
     * 商品批量发布到有赞
     *
     * @return
     */
    @RequestMapping("/itemsPush")
    public Object itemsPush(String itemIdStrs) {
        //logger.info("itemsPush start");
        JsonResult<String> result = new JsonResult<>();
        //查询出商品，查看商品状态，新档或者下架的商品可以上架
        if (itemIdStrs != null) {
            String[] items = itemIdStrs.split(",");
            List<Long> itemIds = new ArrayList<>();
            if (items != null && items.length > 0) {

                for (int i = 0; i < items.length; i++) {
                    Long one = Long.valueOf(items[i]);
                    itemIds.add(one);
                }
                List<ItemDO> itemList = itemService.queryItems(itemIds);
                //获取有赞的num_iid;

                //调取有赞批量上架接口


            } else {
                return result.buildIsSuccess(false);
            }
        } else {
            return result.buildIsSuccess(false);
        }
//		return result.buildIsSuccess(true);
        return result;
    }

    /**
     * 商品管理->商品列表
     *
     * @param itemQueryVO
     * @return
     */
    @RequestMapping("/queryItemList")
    public Object queryItemList(ItemQueryVO itemQueryVO) {
    	//分页参数的计算
    	Integer startIndex = (itemQueryVO.getPageIndex() - 1)*itemQueryVO.getPageSize();
    	itemQueryVO.setFirstStart(startIndex);
    	
        EasyuiJsonResult<List<ItemDTO>> jsonResult = new EasyuiJsonResult<>();
        if (!loginCheck()) {
            return jsonResult.buildIsSuccess(false).buildMsg("请先登录");
        }
        String companyNo = AppUtil.getLoginUserCompanyNo();
        itemQueryVO.setCompanyNo(companyNo);
        if (null == itemQueryVO.getStatus()) { //默认查询上架的商品
            itemQueryVO.setStatus(ItemStatus.LISTING.getCode());
        }
        if (-1 == itemQueryVO.getStatus()) {
        	itemQueryVO.setStatus(null);
        }
        //对前端传来的字符串类型的时间参数进行转换
        if (IsEmptyUtil.isStringNotEmpty(itemQueryVO.getStartTime()) && IsEmptyUtil.isStringNotEmpty(itemQueryVO.getEndTime())) {
            try {
                itemQueryVO.setStartDate(DateUtil.transferStringToDay(itemQueryVO.getStartTime()));
                itemQueryVO.setEndDate(DateUtil.transferStringToDay(itemQueryVO.getEndTime()));
            } catch (Exception e) {
                return jsonResult.buildIsSuccess(false).buildMsg("查询条件中的时间格式错误");
            }
        }
        JsonPageResult<List<ItemDTO>> result = itemService.queryItems(itemQueryVO);
        jsonResult.setTotal(result.getTotalCount());
        jsonResult.setRows(result.getData());
        return jsonResult.buildIsSuccess(true);
    }

    /**
     * 商品管理->商品列表->操作->清除虚拟库存
     *
     * @param itemCode
     * @return
     */
    @RequestMapping("/updateVirtualInvByItemId")
    @Transactional(rollbackFor = BizCommonException.class)
    public Object updateVirtualInvByItemId(Long id) {
        //logger.info("updateVirtualInvByItemId start");
        JsonResult<ItemDO> result = new JsonResult<>();
        if (IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserCompanyNo()) || IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserId())) {
            return result.buildIsSuccess(false).buildMsg("请先登录");
        }
        String itemCode = itemService.queryItemCodeById(id);
        if (IsEmptyUtil.isStringNotEmpty(itemCode)) {
            List<ItemSkuDO> list = itemSkuService.queryByItemCodeAndCompanyNo(itemCode, AppUtil.getLoginUserCompanyNo());
            for (ItemSkuDO itemSkuDO : list) {
                inventoryService.updateVirtualInv(itemSkuDO.getSkuCode(), 0L, itemSkuDO.getCompanyNo());
            }
        }
        return result.buildIsSuccess(true);
    }


    /**
     * 商品管理->商品列表->操作->生成二维码
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/getDimensionCodeUtil")
    @Transactional(rollbackFor = BizCommonException.class)
    public Object getDimensionCodeUtil(Long itemId) {
        JsonResult<Object> result = new JsonResult<>();
        if (!loginCheck()) {
            return result.buildIsSuccess(false).buildMsg("请先登录");
        }
        String companyNo = AppUtil.getLoginUserCompanyNo();
        CompanyDO companyDO = companyService.selectByCompanyNo(companyNo);
        if (null != companyDO) {
            if (IsEmptyUtil.isStringNotEmpty(companyDO.getCompanyGroup())) {
                companyNo = companyDO.getCompanyGroup();
            }
        }
        AppletConfigDO appletConfig = appletConfigService.queryWxMallConfigInfoByCompanyNo(companyNo, AppletType.MALL_APPLET.getValue());
        if (null == appletConfig) {
            return result.buildIsSuccess(false).buildMsg("失败，没有本公司的商城小程序的appid记录");
        }
        String appId = appletConfig.getAppid();
        String accessToken = appletConfig.getAuthorizerAccessToken();
        String secret = appletConfig.getSecret();
        String token;
        if (StringUtil.isBlank(accessToken)) {
            if (IsEmptyUtil.isStringEmpty(appId) || IsEmptyUtil.isStringEmpty(secret)) {
                return result.buildIsSuccess(false).buildMsg("失败，没有本公司的商城小程序的appid记录");
            }
            String reponse = DimensionCodeUtil.sendGet(TOKEN_URL, ACCESS_TOKEN_PART + appId + ACCESS_TOKEN_MI + secret);
            JSONObject myJson = JSONObject.fromObject(reponse);
            token = (String) myJson.get("access_token");
        } else {
            token = accessToken;
        }

        String itemCode = itemService.queryItemCodeById(itemId);
        if (IsEmptyUtil.isStringEmpty(itemCode)) {
            return result.buildIsSuccess(false).buildMsg("失败，商品已被删除");
        }
        String picUrl = itemService.insertIntoItemDimension("item"+itemCode, "pages/item/detail", token);
        if (IsEmptyUtil.isStringNotEmpty(picUrl)) {
        	//更新item表
        	ItemDO item = new ItemDO();
        	item.setId(itemId);
        	item.setQrCodeUrl(picUrl);
        	itemService.updateByIdSelective(item);
        	//更新或者插入item_qrcode_share表
        	String uuid = "item" + itemCode;
        	ItemQrcodeShareDO qrcodeShareDO = shareMapperExt.queryRecordByShareNoAndCompanyNo(uuid, companyNo);
        	if (null == qrcodeShareDO) {
        		ItemQrcodeShareDO shareDO = new ItemQrcodeShareDO();
        		String currentUserNo = AppUtil.getLoginUserId();
        		shareDO.setCompanyNo(companyNo);
        		shareDO.setShareNo(uuid);
        		shareDO.setCreator(currentUserNo);
        		shareDO.setModifier(currentUserNo);
        		shareDO.setItemCode(itemCode);
        		shareDO.setPicUrl(picUrl);
        		shareMapperExt.insertSelective(shareDO);
        	} else {
        		shareMapperExt.updatePicUrlByShareNo(uuid, picUrl);
        	}  
        }
        return result.buildIsSuccess(true);
    }

    /**
     * 商品管理->商品列表->导入商品
     *
     * @param file
     * @return
     */
    @RequestMapping("/improtItem")
    public Object improtItem(MultipartFile file) {
        JsonResult<Object> result = new JsonResult<>();
        try {
            if (!file.isEmpty()) {
                // 文件保存路径
                List<List<Object>> list = ReadExcel.readExcel(file.getInputStream(), file.getOriginalFilename(), 1, 0, 16);
                itemService.importItem(list);
            }
        } catch (IOException e) {
            return result.buildIsSuccess(false).buildMsg("文件上传错误，请重试");
        } catch (BizCommonException e) {
            String str = e.getErrorMsg().replace(",", "</br>");
            return result.buildIsSuccess(false).buildMsg(str);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg(e.getMessage());
        }
        return result.buildIsSuccess(true).buildMsg("上传成功");
    }

    /**
     * 工具类
     * 用户登录判断
     *
     * @param itemCode
     * @return
     */
    public Boolean loginCheck() {
        if (IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserCompanyNo()) || IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserId())) {
            return false;
        }
        return true;
    }

}

