package com.wangqin.globalshop.item.app.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuAddVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
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
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.DimensionCodeUtil;
import com.wangqin.globalshop.common.utils.EasyuiJsonResult;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.ImageUtil;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.common.utils.ItemEnCodeUtil;
import com.wangqin.globalshop.common.utils.PriceUtil;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.common.utils.StringUtil;
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
 * 商品
 *
 * @author xiajun
 */

@Authenticated
@RestController
@RequestMapping("/item")
public class ItemController {

	  //旧的service
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

    
    //新的service
//    @Autowired
//    private ItemBrandFeignService brandService;
//    @Autowired
//    private ItemCategoryFeignService categoryService;
//    @Autowired
//    private ItemFeignService itemService;
//    @Autowired
//    private ItemSkuFeignService itemSkuService;
//    @Autowired
//    private ItemSkuScaleFeignService scaleService;    
//    @Autowired
//    private AppletConfigFeignService appletConfigService;
//    @Autowired
//    private ItemCompanyFeignService companyService;
    



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
    	 JsonResult<ItemDO> result = new JsonResult<>();
         if (!loginCheck()) {
             return result.buildIsSuccess(false).buildMsg("请先登录");
         }
         String companyNo = AppUtil.getLoginUserCompanyNo();
         String userNo = AppUtil.getLoginUserId();
         ItemDO newItem = new ItemDO();

         //类目处理
         String categoryCode = item.getCategoryCode();
         newItem.setCategoryName(categoryService.queryByCategoryCode(categoryCode).getName());
         //商品必须有主图
         if (!ItemUtil.picCheck(item.getMainPic())) {
             return result.buildIsSuccess(false).buildMsg("商品必须有主图");
         }
         //系统自动生成item_code
         String itemCode = ItemEnCodeUtil.generateItemCode(categoryCode);
         // 解析skuList 数组对象
         String skuList = item.getSkuList();
         List<Double> itemSkuPriceList = new ArrayList<Double>();
         List<ItemSkuAddVO> skus = new ArrayList<ItemSkuAddVO>();
         try {
             String s = skuList.replace("&quot;", "\"");
             skus = HaiJsonUtils.toBean(s, new TypeReference<List<ItemSkuAddVO>>() { });
         } catch (Exception e) {
             return result.buildMsg("解析SKU错误").buildIsSuccess(false);
         }
         int i = 0;
         for (ItemSkuAddVO itemSku : skus) {
         	itemSkuPriceList.add(itemSku.getSalePrice());
         	itemSku.setSkuCode(ItemEnCodeUtil.generateSkuCode(itemCode, i++));
         	//sku没有图片就用商品的图片
         	if (!ItemUtil.picCheck(itemSku.getSkuPic())) {//没图
         		itemSku.setSkuPic(item.getMainPic());
         	}
         	//检测upc是否和数据库里面已有的upc重复,按公司划分
             List<String> codeList = itemSkuService.querySkuCodeListByUpc(companyNo, itemSku.getUpc());
             if (IsEmptyUtil.isCollectionNotEmpty(codeList)) {
                 return result.buildIsSuccess(false).buildMsg("新增失败，添加的upc和已有的upc重复");
             }   
         }
         //上架处理
         try {
             ItemUtil.handleShelf(item, newItem);
         } catch (Exception e) {
             return result.buildIsSuccess(false).buildMsg("上架时间填写错误");
         }
        
         //可以直接从PageBean存在数据库的字段
         ItemUtil.transItemVoToDO(item, newItem, companyNo, userNo, categoryCode, itemCode);
         //对不能直接转换的字段做处理
         ItemUtil.detailDecoder(newItem);
         newItem.setPriceRange(PriceUtil.calNewPriceRange(itemSkuPriceList));     
         newItem.setBrandNo(brandService.selectBrandNoByName(item.getBrand().split("->")[0]));        
         itemService.generateQrCode(newItem, companyNo);  
         //渠道处理
         ItemUtil.handleThirdSale(newItem, item);        
         //插入itemsku和库存
         for (ItemSkuAddVO itemSku : skus) {                    
             //插入规格信息
             insertItemScaleInfo(itemSku, companyNo, userNo, itemCode);
             //item_sku所需的其他信息
             ItemUtil.genItemSku(itemSku, newItem, companyNo, userNo, itemCode);            
         }
         itemSkuService.insertBatch(skus);
         List<InventoryDO> inventoryList = itemSkuService.initInventory(skus);
         inventoryService.outbound(inventoryList);
         itemService.insertItemSelective(newItem);
         return result.buildIsSuccess(true).buildMsg("添加商品成功");
    }
    
    /**
     * 商品发布->插入规格信息
     */
    @Transactional
    private void insertItemScaleInfo(ItemSkuAddVO itemSku, String companyNo, String userNo, String itemCode) {    	
        if (IsEmptyUtil.isStringNotEmpty(itemSku.getColor())) {
            ItemSkuScaleDO color = ItemUtil.genScaleDO(companyNo,userNo,"颜色",itemSku.getColor(),itemSku.getSkuCode(),itemCode);
            scaleService.insertSelective(color);
        }
        if (IsEmptyUtil.isStringNotEmpty(itemSku.getScale())) {
        	ItemSkuScaleDO scale = ItemUtil.genScaleDO(companyNo,userNo,"尺寸",itemSku.getScale(),itemSku.getSkuCode(),itemCode);
            scaleService.insertSelective(scale);
        }
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
        String categoryCode = item.getCategoryCode();
        String categoryName = categoryService.queryByCategoryCode(categoryCode).getName();
        List<Double> skuSalePriceList = new ArrayList<Double>();

        //商品图片不能为空，TODO：在前端处理
        if (!ItemUtil.picCheck(item.getMainPic())) {
            return result.buildIsSuccess(false).buildMsg("商品必须有主图");
        }
        
        //解析sku
        List<ItemSkuQueryVO> skus = new ArrayList<ItemSkuQueryVO>();
        try {
            String s = item.getSkuList().replace("&quot;", "\"");
            skus = HaiJsonUtils.toBean(s, new TypeReference<List<ItemSkuQueryVO>>() {});
        } catch (Exception e) {
            return result.buildMsg("解析SKU错误").buildIsSuccess(false);
        }
        //判断upc和数据库里面已有的upc是否重复,获取现在该商品的所有skuCode  
        List<String> newSkuCodeList = new ArrayList<String>();
        for (ItemSkuQueryVO sku : skus) {
        	Integer dupNum = itemSkuService.queryRecordCountByUpcCompanyNotInSameItem(companyNo, sku.getUpc(), itemCode);
        	if (0 < dupNum) {
        		return result.buildIsSuccess(false).buildMsg("更新失败，添加的upc和已有的upc重复");
        	}
        	skuSalePriceList.add(sku.getSalePrice());
        	if (IsEmptyUtil.isStringNotEmpty(sku.getSkuCode())) {
        		newSkuCodeList.add(sku.getSkuCode());
        	}
        }  		             
        newSkuCodeList.add("-1");//这个是为了防止下面的查询出错
        //判断哪些sku被删除了
        List<String> deleteCodeList = itemSkuService.queryToDeleteSkuCodeList(newSkuCodeList, itemCode);
        //step1:删除需要删除的sku
        for (String skuCode : deleteCodeList) {
        	try {
        		deleteSku(skuCode, companyNo);
        	} catch (BizCommonException e) { //捕获库存添加时的异常并告知用户
				return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
			}
        }                   
        for (ItemSkuQueryVO skuVO : skus) {
        	if (IsEmptyUtil.isStringNotEmpty(skuVO.getSkuCode())) { //step2:更新需要更新的sku
        		try {
        			updateSku(skuVO, companyNo, userNo, itemCode, item, categoryName);
        		} catch (BizCommonException e) { //捕获库存更细时的异常并告知用户
					return result.buildIsSuccess(false).buildMsg(e.getErrorMsg());
				}
        	} else { //step3:插入新增的sku
        		int startIndex = 0;
        		addSku(skuVO, companyNo, userNo, itemCode, item, categoryName, startIndex++);
        	}
        }
        
        ItemDO newItem = new ItemDO();
        String imgJson = ImageUtil.getImageUrl(item.getMainPic());
        newItem.setMainPic(imgJson);       
        //上架处理
        try {
            ItemUtil.handleShelf(item, newItem);
        } catch (Exception e) {
            return result.buildIsSuccess(false).buildMsg("上架时间填写错误");
        }
        ItemUtil.handleThirdSale(newItem, item);
        ItemUtil.transItemVoToDOUpdate(item, newItem, userNo, categoryCode, categoryName);
        ItemUtil.detailDecoder(newItem);
        newItem.setBrandNo(brandService.selectBrandNoByName(item.getBrand().split("->")[0]));
        newItem.setPriceRange(PriceUtil.calNewPriceRange(skuSalePriceList));       
        itemService.updateByIdSelective(newItem);
        return result.buildIsSuccess(true);
    }
    
    
    /**
     * 商品编辑->删除sku
     */
    @Transactional(rollbackFor = BizCommonException.class)
    private void deleteSku(String skuCode,  String companyNo) throws BizCommonException {
    	itemSkuService.deleteItemSkuBySkuCode(skuCode);
    	//删除虚拟库存TODO,暂时用更新虚拟库存为0代替
    	inventoryService.updateVirtualInv(skuCode, 0L, companyNo);
    	//删除规格
    	scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "颜色");
    	scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "尺寸");
    }
    
    /**
     * 商品编辑->更新sku
     */
    @Transactional(rollbackFor = BizCommonException.class)
    private void updateSku(ItemSkuQueryVO updateSku, String companyNo, String userNo,
    		String itemCode, ItemQueryVO item, String categoryName) throws BizCommonException {
    	String skuCode = updateSku.getSkuCode();
		//先更新虚拟库存                    
		inventoryService.updateVirtualInv(skuCode, updateSku.getVirtualInv(), companyNo);
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
		ItemUtil.setSkuInfo(updateSku, item, categoryName);
		itemSkuService.updateById(updateSku);
    }
    
    /**
     * 商品编辑->插入新的skus
     */
    @Transactional(rollbackFor = BizCommonException.class)
    private void addSku(ItemSkuQueryVO newSku, String companyNo, String userNo,
    		String itemCode, ItemQueryVO item, String categoryName,int startIndex) throws BizCommonException {
    	ItemSkuDO addSku = new ItemSkuDO();                   
		addSku.setItemCode(itemCode);
		String skuCode = "S" + item.getCategoryCode() + "T" + RandomUtils.getTimeRandomMillSeconds() + "Q" + String.format("%0" + 2 + "d", (startIndex++));
		addSku.setSkuCode(skuCode); 
		ItemUtil.transItemSkuVoToDO(addSku, newSku, companyNo, userNo, item, categoryName);
		
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
		InventoryDO inventory = ItemUtil.genInvDO(itemCode, item.getName(), skuCode, newSku);
		inventoryList.add(inventory);
		inventoryService.outbound(inventoryList);
		//插入sku
		itemSkuService.insertItemSkuSelective(addSku);
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
        //第三方销售渠道的处理
        ItemUtil.queryThirdSale(item);
        result.setData(item);
        return result.buildIsSuccess(true);
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
        JsonResult<ItemDO> result = new JsonResult<>();
        if (!loginCheck()) {
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

