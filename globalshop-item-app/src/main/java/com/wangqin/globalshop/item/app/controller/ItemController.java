package com.wangqin.globalshop.item.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.biz1.app.bean.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.AppletConfigDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CompanyDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemQrcodeShareDOMapperExt;
import com.wangqin.globalshop.biz1.app.enums.ItemShelfMethod;
import com.wangqin.globalshop.biz1.app.enums.ItemStatus;
import com.wangqin.globalshop.biz1.app.exception.BizCommonException;
import com.wangqin.globalshop.common.enums.AppletType;
import com.wangqin.globalshop.common.enums.ItemIsSale;
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
import com.wangqin.globalshop.item.app.service.IItemSubOrderService;
import com.wangqin.globalshop.item.app.util.ItemUtil;

import net.sf.json.JSONObject;

/**
 * 商品处理器
 *
 * @author xiajun
 */
@Controller
@RequestMapping("/item")
@Authenticated
public class ItemController {

    @Autowired
    private IItemBrandService brandService;
    @Autowired
    private IItemCategoryService categoryService;
    @Autowired
    private IItemService iItemService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private IItemSkuService itemSkuService;
    @Autowired
    private IItemSkuScaleService scaleService;
    @Autowired
    private IAppletConfigService appletConfigService;
    @Autowired
    private IItemSubOrderService orderService;
    @Autowired
    private IItemCompanyService companyService;
    @Autowired
    private ItemQrcodeShareDOMapperExt mapperExt;

    public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    //public static final String getaccess_tokenparam = "grant_type=client_credential&appid=wxdef3e972a4a93e91&secret=fef11f402f8e8f3c1442163155aeb65a";
    public static final String ACCESS_TOKEN_PART = "grant_type=client_credential&appid=";
    public static final String ACCESS_TOKEN_MI = "&secret=";
//    public static final String getaccess_tokenparam = "grant_type=client_credential&appid=wx56e36d38aff90280&secret=9269561bae6e1b59c8107c35a669016c";

    /**
     * 添加商品(fin)
     *
     * @param
     */
    @RequestMapping("/add")
    @ResponseBody
    @Transactional(rollbackFor = BizCommonException.class)
    public Object add(ItemQueryVO item) {
        //log.info("---->start to add item---->");
        return iItemService.addItem(item);
    }

    /**
     * 更新商品(fin)
     *
     * @param
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    @Transactional(rollbackFor = BizCommonException.class)
    public Object update(ItemQueryVO item) {
        JsonResult<ItemDO> result = new JsonResult<>();
        if (!loginCheck()) {
            return result.buildIsSuccess(false).buildMsg("请先登录");
        }

        String companyNo = AppUtil.getLoginUserCompanyNo();

        //商品图片不能为空，TODO：在前端处理
        if (!ItemUtil.picCheck(item.getMainPic())) {
            return result.buildIsSuccess(false).buildMsg("商品必须有主图");
        }

        String skuList = item.getSkuList();
        List<ItemSkuScaleDO> scaleList = new ArrayList<>();
        List<Double> skuSalePriceList = new ArrayList<Double>();

        try {
            String s = skuList.replace("&quot;", "\"");
            List<ItemSkuQueryVO> skus = HaiJsonUtils.toBean(s, new TypeReference<List<ItemSkuQueryVO>>() {
            });
            //更新商品的价格区间,同时判断用户传来的upc是否相互之间有重复，同时判断upc和数据库里面已有的upc是否重复
            String itemCode = iItemService.queryItemCodeById(item.getId());
            List<String> upcList = new ArrayList<>();
            for (ItemSkuQueryVO sku : skus) {
                Integer duplcatedCountNumber = itemSkuService.queryRecordCountByUpcCompanyNotInSameItem(AppUtil.getLoginUserCompanyNo(), sku.getUpc(), itemCode);
                if (0 < duplcatedCountNumber) {
                    return result.buildIsSuccess(false).buildMsg("更新失败，添加的upc和已有的upc重复");
                }
                upcList.add(sku.getUpc());
                skuSalePriceList.add(sku.getSalePrice());
            }
            HashSet<String> upcSet = new HashSet<String>(upcList);
            if (upcList.size() > upcSet.size()) {
                result.buildIsSuccess(false);
                result.buildMsg("输入的upc有重复，请再次输入");
                return result;
            }
            //检查更新的这些upc是否和数据库里面(除了正在更新的这个商品的sku)的重复        	
            int startIndex = 0;
            //获取原来绑定该商品的所有sku的skuCode
            ItemSkuQueryVO itemSkuQueryVO = new ItemSkuQueryVO();
            itemSkuQueryVO.setCompanyNo(companyNo);
            itemSkuQueryVO.setItemCode(iItemService.queryItemCodeById(item.getId()));
            List<ItemSkuDO> itemSkuDOList = itemSkuService.queryItemSkuListSelective(itemSkuQueryVO);
            List<String> oldSkuCodeList = new ArrayList<String>();
            for (ItemSkuDO itemSkuDO : itemSkuDOList) {
                oldSkuCodeList.add(itemSkuDO.getSkuCode());
            }
            //获取现在该商品的所有skuCode
            List<String> newSkuCodeList = new ArrayList<String>();
            for (ItemSkuQueryVO skuQueryVO : skus) {
                if (null != skuQueryVO.getSkuCode()) {
                    newSkuCodeList.add(skuQueryVO.getSkuCode());
                }
            }
            //两个集合求差值，判断哪些sku被删除了
            List<String> diffList = new ArrayList<String>();
            diffList.addAll(oldSkuCodeList);
            diffList.removeAll(newSkuCodeList);
            //删除sku
            for (String skuCode : diffList) {
                itemSkuService.deleteItemSkuBySkuCode(skuCode);
                //删除虚拟库存TODO
                //暂时用更新虚拟库存为0代替
                try {
                    inventoryService.updateVirtualInv(skuCode, 0L, AppUtil.getLoginUserCompanyNo());
                } catch (Exception e) {
                    return result.buildIsSuccess(false).buildMsg("您试图删除不能删除的sku，这样的操作导致了库存异常");
                }
                //删除规格
                scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "颜色");
                scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "尺寸");
            }


            //更新需要更新的sku
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
            			ItemSkuScaleDO colorScale = new ItemSkuScaleDO();
            			String userNo = AppUtil.getLoginUserId();
            			colorScale.setCompanyNo(companyNo);
            			colorScale.setCreator(userNo);
            			colorScale.setModifier(userNo);
            			colorScale.setItemCode(itemCode);
            			colorScale.setScaleCode(CodeGenUtil.getScaleCode());
            			colorScale.setScaleValue(updateSku.getColor());
            			colorScale.setScaleName("颜色");
            			colorScale.setSkuCode(skuCode);
            			scaleService.insertSelective(colorScale);
            		}
            		scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "尺寸");
            		if (IsEmptyUtil.isStringNotEmpty(updateSku.getScale())) {            			
            			ItemSkuScaleDO scale = new ItemSkuScaleDO();
            			String userNo = AppUtil.getLoginUserId();
            			scale.setCompanyNo(companyNo);
            			scale.setCreator(userNo);
            			scale.setModifier(userNo);
            			scale.setItemCode(itemCode);
            			scale.setScaleCode(CodeGenUtil.getScaleCode());
            			scale.setScaleValue(updateSku.getScale());
            			scale.setScaleName("尺寸");
            			scale.setSkuCode(skuCode);
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
            //插入新增的sku
            for (ItemSkuQueryVO newSku : skus) {
                if (null == newSku.getSkuCode()) {//需要添加的sku
                    ItemSkuDO addSku = new ItemSkuDO();
                    System.out.println("update1.1...");
                    ItemDTO itemDTO = iItemService.queryItemById(item.getId());

                    addSku.setCompanyNo(AppUtil.getLoginUserCompanyNo());
                    addSku.setItemCode(itemDTO.getItemCode());
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
                    addSku.setCreator(AppUtil.getLoginUserId());
                    addSku.setModifier(AppUtil.getLoginUserId());
                    addSku.setItemName(itemDTO.getName());
                    addSku.setCategoryCode(itemDTO.getCategoryCode());
                    addSku.setCategoryName(itemDTO.getCategoryName());
                    addSku.setSkuRate(ItemUtil.divideOneHundred(newSku.getSkuRateString()));
                    //插入item_sku_scale表                                       
                    if (IsEmptyUtil.isStringNotEmpty(newSku.getColor())) {
                    	ItemSkuScaleDO colorObject = new ItemSkuScaleDO();
                    	setInfo(colorObject, addSku, newSku.getColor(), "颜色");
                    	scaleList.add(colorObject);
                    }
                    if (IsEmptyUtil.isStringNotEmpty(newSku.getScale())) {
                    	ItemSkuScaleDO scaleObject = new ItemSkuScaleDO();
                    	scaleList.add(scaleObject);
                    	setInfo(scaleObject, addSku, newSku.getScale(), "尺寸");
                    }                                                        
                   
                    if (IsEmptyUtil.isCollectionNotEmpty(scaleList)) {
                    	scaleService.insertBatch(scaleList);
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
        iItemService.updateByIdSelective(newItem);
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
     * 通过id查找商品（在修改商品的时候使用，fin）
     *
     * @param id
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    @Transactional(rollbackFor = BizCommonException.class)
    public Object query(Long id) {
        JsonResult<ItemDTO> result = new JsonResult<>();

        if (!loginCheck()) {
            return result.buildIsSuccess(false).buildMsg("请先登录");
        }
        
        ItemDTO item = iItemService.queryItemById(id);
        if (item == null) {
            result.buildIsSuccess(false).buildMsg("没有找到商品，商品可能已删除");
        }
        String itemName = item.getName();
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
    @ResponseBody
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
                List<ItemDO> itemList = iItemService.queryItems(itemIds);
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
    @ResponseBody
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
                List<ItemDO> itemList = iItemService.queryItems(itemIds);
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
     * 商品查询管理主列表
     *
     * @param itemQueryVO
     * @return
     */
    @RequestMapping("/queryItemList")
    @ResponseBody
    public Object queryItemList(ItemQueryVO itemQueryVO) {
        EasyuiJsonResult<List<ItemDTO>> jsonResult = new EasyuiJsonResult<>();
        if (!loginCheck()) {
            return jsonResult.buildIsSuccess(false).buildMsg("请先登录");
        }
        String companyNo = AppUtil.getLoginUserCompanyNo();
        itemQueryVO.setCompanyNo(companyNo);
        if (null == itemQueryVO.getStatus()) { //默认查询上架的商品
        	itemQueryVO.setStatus(ItemStatus.LISTING.getCode());
        }
        //对前端传来的字符串类型的时间参数进行转换
        if (IsEmptyUtil.isStringNotEmpty(itemQueryVO.getStartTime()) && IsEmptyUtil.isStringNotEmpty(itemQueryVO.getEndTime())) {
        	try {
        		itemQueryVO.setStartDate(DateUtil.transferStringToDay(itemQueryVO.getStartTime()));
        		itemQueryVO.setEndDate(DateUtil.transferStringToDay(itemQueryVO.getEndTime()));
        	} catch(Exception e) {
        		return jsonResult.buildIsSuccess(false).buildMsg("查询条件中的时间格式错误");
        	}      	
        }
        JsonPageResult<List<ItemDTO>> result = iItemService.queryItems(itemQueryVO);
        //计算销量
//        List<ItemDTO> itemList = result.getData();
//        if (IsEmptyUtil.isCollectionNotEmpty(itemList)) {
//        	for (ItemDTO item:itemList) {
//        		Integer salesVolume = orderService.calItemSalesVolume(item.getItemCode(), companyNo);
//        		item.setSalesVolume(salesVolume);
//        	}
//        }
        jsonResult.setTotal(result.getTotalCount());
        jsonResult.setRows(result.getData());
        return jsonResult.buildIsSuccess(true);
    }

    /**
     * 清除虚拟库存
     *
     * @param itemCode
     * @return
     */
    @RequestMapping("/updateVirtualInvByItemId")
    @ResponseBody
    @Transactional(rollbackFor = BizCommonException.class)
    public Object updateVirtualInvByItemId(Long id) {
        //logger.info("updateVirtualInvByItemId start");
        JsonResult<ItemDO> result = new JsonResult<>();
        if (IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserCompanyNo()) || IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserId())) {
            return result.buildIsSuccess(false).buildMsg("请先登录");
        }
        String itemCode = iItemService.queryItemCodeById(id);
        if (IsEmptyUtil.isStringNotEmpty(itemCode)) {
            List<ItemSkuDO> list = itemSkuService.queryByItemCodeAndCompanyNo(itemCode, AppUtil.getLoginUserCompanyNo());
            for (ItemSkuDO itemSkuDO : list) {
                inventoryService.updateVirtualInv(itemSkuDO.getSkuCode(), 0L, itemSkuDO.getCompanyNo());
            }
        }
        return result.buildIsSuccess(true);
    }


    /**
     * 更新二维码
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/getDimensionCodeUtil")
    @ResponseBody
    @Transactional(rollbackFor = BizCommonException.class)
    public Object getDimensionCodeUtil(Long itemId) {
        //logger.info("getDimensionCodeUtil start");
        JsonResult<Object> result = new JsonResult<>();
        if (!loginCheck()) {
            return result.buildIsSuccess(false).buildMsg("请先登录");
        }
        String companyNo = AppUtil.getLoginUserCompanyNo();
        CompanyDO companyDO = companyService.selectByCompanyNo(companyNo);
        if (null !=  companyDO) {
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
            //System.out.println("part:"+ACCESS_TOKEN_PART+appId+ACCESS_TOKEN_MI+secret);
            JSONObject myJson = JSONObject.fromObject(reponse);
            token = (String) myJson.get("access_token");

        } else {
            token = accessToken;

        }

        String itemCode = iItemService.queryItemCodeById(itemId);
        if (IsEmptyUtil.isStringEmpty(itemCode)) {
            return result.buildIsSuccess(false).buildMsg("失败，商品已被删除");
        }
        String picUrl = iItemService.insertIntoItemDimension(itemCode, "pages/item/detail", token);
        if (StringUtil.isNotBlank(picUrl)) {
            if (itemId != null) {
                //System.out.println(picUrl);
                ItemDO item = new ItemDO();
                item.setId(itemId);
                item.setQrCodeUrl(picUrl);
                iItemService.updateByIdSelective(item);
            }
            //更新item_qrcode_url表
            mapperExt.updatePicUrlByShareNo("item"+itemCode, picUrl);
        }
        return result.buildIsSuccess(true);
    }


    /**
     * 新增商品同时生成二维码
     * 
     *
     * @param itemId
     */
    @Transactional(rollbackFor = BizCommonException.class)
    public void voidDimensionCodeUtil(Long itemId) {
//		/logger.info("voidDimensionCodeUtil start");
        if (IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserCompanyNo()) || IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserId())) {
            return;
        }
        
        String companyNo = AppUtil.getLoginUserCompanyNo();
        CompanyDO companyDO = companyService.selectByCompanyNo(companyNo);
        if (null !=  companyDO) {
    		if (IsEmptyUtil.isStringNotEmpty(companyDO.getCompanyGroup())) {
    			companyNo = companyDO.getCompanyGroup();
    		}   		
    	}
        AppletConfigDO appletConfig = appletConfigService.queryWxMallConfigInfoByCompanyNo(companyNo, AppletType.MALL_APPLET.getValue());
        if (null == appletConfig) {
            return;
        }
        String appId = appletConfig.getAppid();
        String secret = appletConfig.getSecret();
        String accessToken = appletConfig.getAuthorizerAccessToken();
        String token;
        if (StringUtil.isBlank(accessToken)){
            if (IsEmptyUtil.isStringEmpty(appId) || IsEmptyUtil.isStringEmpty(secret)) {
                return;
            }
            String reponse = DimensionCodeUtil.sendGet(TOKEN_URL, ACCESS_TOKEN_PART + appId + ACCESS_TOKEN_MI + secret);
            JSONObject myJson = JSONObject.fromObject(reponse);
            token = (String) myJson.get("access_token");
        } else {
            token = accessToken;
        }

        String picUrl = iItemService.insertIntoItemDimension(itemId.toString(), "pages/item/detail", token);
        if (StringUtil.isNotBlank(picUrl)) {
            if (itemId != null) {
                ItemDO item = new ItemDO();
                item.setId(itemId);
                item.setQrCodeUrl(picUrl);
                iItemService.updateByIdSelective(item);
            }
        }

    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("返回值：" + result);
        return result;
    }

    /**
     * 导入商品
     *
     * @param file
     * @return
     */
    @RequestMapping("/improtItem")
    @ResponseBody
    public Object improtItem(MultipartFile file) {
        JsonResult<Object> result = new JsonResult<>();
        try {
            if (!file.isEmpty()) {
                // 文件保存路径
                List<List<Object>> list = ReadExcel.readExcel(file.getInputStream(), file.getOriginalFilename(), 1, 0, 16);
                iItemService.importItem(list);
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

