package com.wangqin.globalshop.item.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;

import com.wangqin.globalshop.biz1.app.Exception.ErpCommonException;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.vo.*;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.common.utils.excel.ReadExcel;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.item.app.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    private ICountryService countryService;
    @Autowired
    private IItemService iItemService;
    @Autowired
    private InventoryService invService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private IItemSkuService itemSkuService;
    @Autowired
    private IItemSkuScaleService scaleService;
    public static final String getaccess_tokenurl = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String getaccess_tokenparam = "grant_type=client_credential&appid=wxdef3e972a4a93e91&secret=fef11f402f8e8f3c1442163155aeb65a";
//    public static final String getaccess_tokenparam = "grant_type=client_credential&appid=wx56e36d38aff90280&secret=9269561bae6e1b59c8107c35a669016c";

    /**
     * 添加商品(fin)
     *
     * @param
     */
    @RequestMapping("/add")
    @ResponseBody
    public Object add(ItemQueryVO item) {
        JsonResult<ItemDO> result = new JsonResult<>();       
        if (null == AppUtil.getLoginUserCompanyNo() || null == AppUtil.getLoginUserId()) {
            return result.buildIsSuccess(false).buildMsg("请先登录");
        }
        
        if (null != item.getId()) {
            return result.buildMsg("新增的商品不能有ID").buildIsSuccess(false);
        }
        
        List<ItemSkuScaleDO> scaleList = new ArrayList<>();
        String priceRange = "0";
        
        //商品名称处理
        StringBuffer nameNew = new StringBuffer();
        String[] brandArr = item.getBrand().split("->");
        if (StringUtil.isNotBlank(brandArr[0])) {    //英文品牌放在名字最前面
            nameNew.append(brandArr[0] + " ");
        }

        if (brandArr.length > 1 && StringUtil.isNotBlank(brandArr[1])) { //中文品牌紧随其后
            nameNew.append(brandArr[1] + " ");
        }

        if (StringUtil.isNotBlank(item.getSexStyle())) { //男女款排在第三
            nameNew.append(item.getSexStyle() + " ");
        }
        String itemNameShort = item.getName();
        nameNew.append(item.getName());//最后出场的才是商品的名字
        item.setName(nameNew.toString());//重新设置商品名称
        

        //类目处理
        String categoryCode = item.getCategoryCode();
        if (null != categoryCode) {
            item.setCategoryName(categoryService.queryByCategoryCode(categoryCode).getName());
        } else {
            return result.buildMsg("没有找到类目").buildIsSuccess(false);
        }

        String mainPic = item.getMainPic();
        //图片处理
        String imgJson = ImageUtil.getImageUrl(mainPic);
        //商品必须有主图(donot delete!)
        JSONObject jsonObject = JSONObject.fromObject(mainPic);
        JSONArray jsonArray = jsonObject.getJSONArray("picList");
        if(0 == jsonArray.size()) {
        	return result.buildIsSuccess(false).buildMsg("商品必须有主图");
        }

        //系统自动生成item_code
        String itemCode = "I"+categoryCode+"T"+RandomUtils.getTimeRandom();
        item.setItemCode(itemCode);
        
        // 解析skuList 数组对象
        String skuList = item.getSkuList();
        if(!StringUtils.isNotBlank(skuList)) {
        	return result.buildIsSuccess(false).buildMsg("最少需要提供一个sku");
        }
        try {
            String s = skuList.replace("&quot;", "\"");
            List<ItemSkuAddVO> skus = HaiJsonUtils.toBean(s, new TypeReference<List<ItemSkuAddVO>>() {
            });
            int i = 0;

            if (skus != null && !skus.isEmpty()) {
                Double minPrice = skus.get(0).getSalePrice();
                Double maxPrice = skus.get(0).getSalePrice();
                for (ItemSkuAddVO itemSku : skus) {
                    i++;
                    itemSku.setSkuCode("S" + itemCode.substring(1) + "Q" + String.format("%0" + 2 + "d", ++i));
                    //itemSku.setLogisticType(item.getLogisticType());TODO
                    
                    String skuMainPic = itemSku.getSkuPic();
                    String skuPic = ImageUtil.getImageUrl(skuMainPic);
                    
                    //sku没有图片就用商品的图片(别删，谁删谁负责)
                    JSONObject skuPicJsonObject = JSONObject.fromObject(skuMainPic);
                    JSONArray skuPicJsonArray = skuPicJsonObject.getJSONArray("picList");
                    if(0 == skuPicJsonArray.size()) {//没图
                    	itemSku.setSkuPic(mainPic);
                    } else {
                    	itemSku.setSkuPic(skuMainPic);
                    }
                    if (StringUtils.isNotBlank(itemSku.getPackageLevelId())) {
                        List<Long> a = HaiJsonUtils.toBean(itemSku.getPackageLevelId(), new TypeReference<List<Long>>() {
                        });
                        itemSku.setPackageId(a.get(a.size() - 1));
                    }
                    minPrice = minPrice > itemSku.getSalePrice() ? itemSku.getSalePrice() : minPrice;
                    maxPrice = maxPrice < itemSku.getSalePrice() ? itemSku.getSalePrice() : maxPrice;
                    // 商品价格区间
                    if (minPrice.equals(maxPrice)) {
                        priceRange = maxPrice.toString();
                    } else {
                        priceRange = minPrice.toString() + "-" + maxPrice.toString();
                    }
//                    // 如果商品没有图片，默认使用sku上的图片
//                    if (StringUtils.isBlank(imgJson) && StringUtils.isNotBlank(skuPic)) {
//                        imgJson = skuPic;
//                    }
                }
                item.setItemSkus(skus);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result.buildMsg("解析SKU错误").buildIsSuccess(false);
        }
        
        //对前端传来的时间进行处理
        ItemDO newItem = new ItemDO();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            newItem.setStartDate(format.parse(item.getStartDate()));
            newItem.setEndDate(format.parse(item.getEndDate()));
            if (null != item.getBookingDate()) {//非必填项，空指针检查
                newItem.setBookingDate(format.parse(item.getBookingDate()));
            }
        } catch (Exception e) {
            //TODO
        }

        //判断是否可售
//        if (item.getStartDate() == null || item.getEndDate() == null) {
//            item.setIsSale(0);
//        } else if (DateUtil.belongCalendar(new Date(), newItem.getStartDate(), DateUtil.getDateByCalculate(newItem.getEndDate(), Calendar.DATE, 1))) {
//            item.setIsSale(1);
//        } else {
//            item.setIsSale(0);
//        }
        //TEMP
        item.setIsSale(1);

        newItem.setDetail(item.getDetail());
        detailDecoder(newItem);
        newItem.setPriceRange(priceRange);
        newItem.setCategoryName(item.getCategoryName());
        newItem.setCategoryCode(categoryCode);
        newItem.setBrandName(item.getBrand());
        String itemBrandFullName = item.getBrand();
        if(itemBrandFullName.contains("->")) {
        	newItem.setBrandNo(brandService.selectBrandNoByName(item.getBrand().split("->")[0]));
        } else {
        	newItem.setBrandNo(brandService.selectBrandNoByName(item.getBrand()));
        }      
        newItem.setEnName(item.getEnName());
        newItem.setItemName(item.getName());
        newItem.setCurrency(item.getCurrency().byteValue());
        newItem.setIdCard(item.getIdCard().byteValue());
        //newItem.setLogisticType(item.getLogisticType().byteValue());
        newItem.setCountry(item.getCountry());
        newItem.setItemCode(item.getItemCode());
        newItem.setWxisSale(item.getWxisSale().byteValue());
        newItem.setRemark(item.getRemark());       
        newItem.setMainPic(item.getMainPic());

        newItem.setRemark(item.getRemark());
 
        newItem.setIsSale(item.getIsSale().byteValue());
        newItem.setItemShort(itemNameShort);

        newItem.setRemark(item.getRemark());     

        newItem.setCompanyNo(AppUtil.getLoginUserCompanyNo());
        newItem.setModifier(AppUtil.getLoginUserId());
        newItem.setCreator(AppUtil.getLoginUserId());
        
        /**插入itemsku和库存**/
        List<ItemSkuAddVO> itemSkuList = item.getItemSkus();
        List<String> upcList = new ArrayList<>();
        if(EasyUtil.isListEmpty(itemSkuList)) {
        	return result.buildIsSuccess(false).buildMsg("最少需要提供一个sku");
        }
        for(ItemSkuAddVO itemSku:itemSkuList) {
            //检测upc是否和数据库里面已有的upc重复,按公司划分
        	Integer duplcatedCountNumber = itemSkuService.queryRecordCountByUpcCompanyNotInSameItem(
    				AppUtil.getLoginUserCompanyNo(),itemSku.getUpc(), itemCode);
    		if(0 < duplcatedCountNumber) {
    			return result.buildIsSuccess(false).buildMsg("新增失败，添加的upc和已有的upc重复");
    		}
        	upcList.add(itemSku.getUpc());
        	itemSku.setItemCode(newItem.getItemCode());
        	/**插入ItemSkuScale*/
        	ItemSkuScaleDO colorObject = new ItemSkuScaleDO();
        	ItemSkuScaleDO scaleObject = new ItemSkuScaleDO();
        	setInfo(colorObject, itemSku, itemSku.getColor(), "颜色");
        	setInfo(scaleObject, itemSku, itemSku.getScale(), "尺寸");
        	scaleList.add(colorObject);
        	scaleList.add(scaleObject);
        	itemSku.setItemName(newItem.getItemName());
        	itemSku.setItemId(newItem.getId());
        	itemSku.setCategoryName(item.getCategoryName());
        	itemSku.setCategoryCode(item.getCategoryCode());
        	itemSku.setBrand(newItem.getBrandName());
        	itemSku.setModifier(AppUtil.getLoginUserId());
        	itemSku.setCreator(AppUtil.getLoginUserId());
        	itemSku.setCompanyNo(AppUtil.getLoginUserCompanyNo());
        	itemSku.setSalePrice(itemSku.getSalePrice());
        }
        //判断用户添加的几个upc之间是否重复
        HashSet<String> upcSet = new HashSet<String>(upcList);
        if(upcList.size() > upcSet.size()) {
        	result.buildIsSuccess(false);
        	result.buildMsg("输入的upc有重复，请再次输入");
        	return result;
        }
        
        itemSkuService.insertBatch(itemSkuList);       
        List<InventoryDO> inventoryList = itemSkuService.initInventory(itemSkuList);
        scaleService.insertBatch(scaleList);
        
        invService.outbound(inventoryList);

        //同步生成小程序的二维码
//        if (item.getId() != null) {
//            voidDimensionCodeUtil(item.getId());
//        }
        
    	//处理第三方销售平台，TEMP
    	List<Integer> channelList = item.getSaleOnChannels();
        if (CollectionUtils.isEmpty(channelList)) {
            item.setThirdSale(0);
            item.setSaleOnYouzan(0);
        } else {
            for(Integer channelId:channelList) {
            	if(1 == channelId) {//有赞
            		newItem.setSaleOnYouzan(1);
            	}
            	if(2 == channelId) {//海狐
            		newItem.setThirdSale(1);
            	}
            }
        }
        
        iItemService.insertItemSelective(newItem);
        return result.buildIsSuccess(true);

    }


    /**
     * 封装ItemSkuScala对象信息
     * @author ChenZiHao
     * @param obj     封装的对象
     * @param itemSku
     * @param value   scalaValue
     * @param name    scalaName
     */
    private void setInfo(ItemSkuScaleDO obj, ItemSkuAddVO itemSku, String value, String name) {
        obj.setSkuCode(itemSku.getSkuCode());
        obj.setItemCode(itemSku.getItemCode());
        obj.setScaleCode(CodeGenUtil.getScaleCode());
        obj.setScaleName(name);
        obj.setScaleValue(value);
        obj.init();

    }

    /**
     * 更新商品(fin)
     *
     * @param
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Object update(ItemQueryVO item) {
        JsonResult<ItemDO> result = new JsonResult<>();
        
        if (null == AppUtil.getLoginUserCompanyNo() || null == AppUtil.getLoginUserId()) {
            return result.buildIsSuccess(false).buildMsg("请先登录");
        }       
        if (null == item.getId()) {
            return result.buildMsg("更新商品需要提供商品ID").buildIsSuccess(false);
        }
        
        ItemDTO oldItem = iItemService.queryItemById(item.getId());
        if (StringUtil.isBlank(item.getDetail()) && StringUtil.isNotBlank(oldItem.getDetail())) {
            return result.buildMsg("商品详情不能为空").buildIsSuccess(false);
        }

        //商品名称处理
        StringBuffer nameNew = new StringBuffer();
        String[] brandArr = item.getBrand().split("->");
        if (StringUtil.isNotBlank(brandArr[0])) {    //英文品牌
        	nameNew.append(brandArr[0] + " ");
        }
        if (brandArr.length > 1 && StringUtil.isNotBlank(brandArr[1])) {    //中文品牌
        	nameNew.append(brandArr[1] + " ");
        }
        if (StringUtil.isNotBlank(item.getSexStyle())) {        //男女款
        	nameNew.append(item.getSexStyle() + " ");
        }
        nameNew.append(item.getName());   
        item.setName(nameNew.toString());//重新设置商品名称

        String skuList = item.getSkuList();
        if (!StringUtils.isNotBlank(skuList)) {
        	return result.buildMsg("需要至少提供一个sku").buildIsSuccess(false);
        }
        try {
        	String s = skuList.replace("&quot;", "\"");
        	List<ItemSkuQueryVO> skus = HaiJsonUtils.toBean(s, new TypeReference<List<ItemSkuQueryVO>>() {
        	});
        	if (skus == null || skus.isEmpty()) {
        		return result.buildMsg("需要至少提供一个sku").buildIsSuccess(false);
        	}
        	//更新商品的价格区间,同时判断用户传来的upc是否相互之间有重复，同时判断upc和数据库里面已有的upc是否重复
        	String itemCode = iItemService.queryItemCodeById(item.getId());
        	List<String> upcList = new ArrayList<>();
        	BigDecimal maxPrice = new BigDecimal(skus.get(0).getSalePrice());
        	BigDecimal minPrice = new BigDecimal(skus.get(0).getSalePrice());
        	for(ItemSkuQueryVO sku:skus) {
        		Integer duplcatedCountNumber = itemSkuService.queryRecordCountByUpcCompanyNotInSameItem(
        				AppUtil.getLoginUserCompanyNo(),sku.getUpc(), itemCode);
        		if(0 < duplcatedCountNumber) {
        			return result.buildIsSuccess(false).buildMsg("更新失败，添加的upc和已有的upc重复");
        		}
        		upcList.add(sku.getUpc());
        		BigDecimal temp = new BigDecimal(sku.getSalePrice());
        		maxPrice = maxPrice.compareTo(temp) < 0 ? temp : maxPrice;
        		minPrice = minPrice.compareTo(temp) > 0 ? temp : minPrice;
        	}
        	HashSet<String> upcSet = new HashSet<String>(upcList);
            if(upcList.size() > upcSet.size()) {
            	result.buildIsSuccess(false);
            	result.buildMsg("输入的upc有重复，请再次输入");
            	return result;
            }
            if(0 == minPrice.compareTo(maxPrice)) {
            	item.setPriceRange(minPrice.toString());
            } else {
            	item.setPriceRange(minPrice.toString()+"-"+maxPrice.toString());
            }
            
            //检查更新的这些upc是否和数据库里面(除了正在更新的这个商品的sku)的重复
        	
        	int startIndex = 0;
        	//获取原来绑定该商品的所有sku的skuCode
        	ItemSkuQueryVO itemSkuQueryVO = new ItemSkuQueryVO();
        	itemSkuQueryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
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
        	diffList.forEach(skuCode -> {
        		itemSkuService.deleteItemSkuBySkuCode(skuCode);
        	});
        	//更新需要更新的sku
        	for (ItemSkuQueryVO updateSku : skus) {
        		if (null != updateSku.getSkuCode()) {//需要更新的sku
        			itemSkuService.updateById(updateSku);
        		}
        	}
        	//插入新增的sku
        	for (ItemSkuQueryVO newSku : skus) {
        		if (null == newSku.getSkuCode()) {//需要添加的sku
        			ItemSkuDO addSku = new ItemSkuDO();
        			ItemDTO itemDTO = iItemService.queryItemById(item.getId());
        			addSku.setCompanyNo(AppUtil.getLoginUserCompanyNo());
        			addSku.setItemCode(itemDTO.getItemCode());		
        			addSku.setSkuCode("S" + item.getCategoryCode() + "T" + RandomUtils.getTimeRandom() + "Q"+String.format("%0" + 2 + "d", (startIndex++)));
        			addSku.setScale(newSku.getScale());
        			addSku.setSalePrice((double) newSku.getSalePrice());
        			addSku.setWeight(newSku.getWeight());
        			addSku.setUpc(newSku.getUpc());
        			addSku.setSkuPic(newSku.getSkuPic());
        			addSku.setPackageLevelId(newSku.getPackageLevelId());
        			addSku.setCreator(AppUtil.getLoginUserId());
        			addSku.setModifier(AppUtil.getLoginUserId());
        			addSku.setItemName(itemDTO.getName());
        			//插入规格TODO
        			itemSkuService.insertItemSkuSelective(addSku);
        		}
        	}        		      	     		
        } catch (Exception e) {
        	e.printStackTrace();
        	return result.buildMsg("解析SKU错误").buildIsSuccess(false);
        }           
        
        String imgJson = ImageUtil.getImageUrl(item.getMainPic());
        item.setMainPic(imgJson);

        //对前端传来的时间进行处理
        ItemDO newItem = new ItemDO();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            newItem.setStartDate(format.parse(item.getStartDate()));
            newItem.setEndDate(format.parse(item.getEndDate()));
            if(null != item.getBookingDate()) {
                newItem.setBookingDate(format.parse(item.getBookingDate()));
            }
        } catch (Exception e) {
            //
        }
        //判断是否可售
//        if (item.getStartDate() == null || item.getEndDate() == null) {
//            item.setIsSale(0);
//        } else if (DateUtil.belongCalendar(new Date(), newItem.getStartDate(), DateUtil.getDateByCalculate(newItem.getEndDate(), Calendar.DATE, 1))) {
//            item.setIsSale(1);
//        } else {
//            item.setIsSale(0);
//        }
        //TEMP
        item.setIsSale(1);
//        //编辑运费
//        if (item.getFreight() == null) {
//            item.setFreight(0.0d);
//        }

        newItem.setDetail(item.getDetail());
        detailDecoder(newItem);
        newItem.setCategoryCode(item.getCategoryCode());
        newItem.setCategoryName(categoryService.queryByCategoryCode(item.getCategoryCode()).getName());
        newItem.setBrandName(item.getBrand());
        String itemBrandFullName = item.getBrand();
        if(itemBrandFullName.contains("->")) {
        	newItem.setBrandNo(brandService.selectBrandNoByName(item.getBrand().split("->")[0]));
        } else {
        	
        	newItem.setBrandNo(brandService.selectBrandNoByName(item.getBrand()));
        } 
        newItem.setEnName(item.getEnName());
        newItem.setItemName(item.getName());
        newItem.setCurrency(item.getCurrency().byteValue());
        newItem.setIdCard(item.getIdCard().byteValue());
        newItem.setPriceRange(item.getPriceRange());
        newItem.setCountry(item.getCountry());
        newItem.setRemark(item.getRemark());
        newItem.setMainPic(item.getMainPic());
        newItem.setId(item.getId());
        newItem.setWxisSale(item.getWxisSale().byteValue());
        newItem.setMainPic(item.getMainPic());
        newItem.setModifier(AppUtil.getLoginUserId());
        newItem.setIsSale(item.getIsSale().byteValue());
        //newItem.setLogisticType(item.getLogisticType().byteValue());
    
        iItemService.updateByIdSelective(newItem);	
        return result.buildIsSuccess(true);
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
    public Object query(Long id) {
        JsonResult<ItemDTO> result = new JsonResult<>();

        if(null == id) {
        	return result.buildIsSuccess(false).buildMsg("id不能为空");
        }       	
        ItemDTO item = iItemService.queryItemById(id);
        if (item == null) {
        	result.buildIsSuccess(false).buildMsg("没有找到商品，商品可能已删除");
        }
        String itemName = item.getName();
        //获取商品类目的Id
        Long categoryId = categoryService.queryCategoryByCategoryCode(item.getCategoryCode()).getId();
        item.setCategoryId(categoryId);
        
        if (itemName.contains("婴儿款")) {
        	item.setSexStyle("婴儿款");
        } else if (itemName.contains("大童男款")) {
        	item.setSexStyle("大童男款");
        } else if (itemName.contains("大童女款")) {
        	item.setSexStyle("大童女款");
        } else if (itemName.contains("小童男款")) {
        	item.setSexStyle("小童男款");
        } else if (itemName.contains("小童女款")) {
        	item.setSexStyle("小童女款");
        } else if (itemName.contains("男款")) {
        	item.setSexStyle("男款");
        } else if (itemName.contains("女款")) {
        	item.setSexStyle("女款");
        } else if (itemName.contains("大童款")) {
        	item.setSexStyle("大童款");
        } else if (itemName.contains("小童款")) {
        	item.setSexStyle("小童款");
        }
        
        StringBuffer nameRep = new StringBuffer();
        //品牌
        String[] brandArr = item.getBrand().split("->");
        
        if (StringUtil.isNotBlank(brandArr[0])) {    //英文品牌
        	nameRep.append(brandArr[0] + " ");
        }
        if (brandArr.length > 1 && StringUtil.isNotBlank(brandArr[1])) {    //中文品牌
        	nameRep.append(brandArr[1] + " ");
        }
        if (StringUtil.isNotBlank(item.getSexStyle())) {        //男女款
        	nameRep.append(item.getSexStyle() + " ");
        }
        //重新设置商品名称
        item.setName(itemName.replace(nameRep.toString(), ""));     
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
     * @return
     */
    @RequestMapping("/queryItemList")
    @ResponseBody
    public Object queryItemList(ItemQueryVO itemQueryVO) {
    	EasyuiJsonResult<List<ItemDTO>> jsonResult = new EasyuiJsonResult<>();
    	if (null == AppUtil.getLoginUserCompanyNo() || null == AppUtil.getLoginUserId()) {
            return jsonResult.buildIsSuccess(false).buildMsg("请先登陆");
        }
        itemQueryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
        JsonPageResult<List<ItemDTO>> result = iItemService.queryItems(itemQueryVO);
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
    public Object updateVirtualInvByItemId(String itemCode) {
        //logger.info("updateVirtualInvByItemId start");
        JsonResult<ItemDO> result = new JsonResult<>();
        List<ItemSkuDO> list = itemSkuService.queryByItemCodeAndCompanyNo(itemCode, AppUtil.getLoginUserCompanyNo());
        for (ItemSkuDO itemSkuDO : list) {
            inventoryService.updateVirtualInv(itemSkuDO.getSkuCode(),0L,itemSkuDO.getCompanyNo());
        }
        return result.buildIsSuccess(true);
    }


    /**
     * 单品生成二维码
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/getDimensionCodeUtil")
    @ResponseBody
    public Object getDimensionCodeUtil(Long itemId) {
        //logger.info("getDimensionCodeUtil start");
        JsonResult<Object> result = new JsonResult<>();
        String reponse = DimensionCodeUtil.sendGet(getaccess_tokenurl, getaccess_tokenparam);
        JSONObject myJson = JSONObject.fromObject(reponse);
        String token = (String) myJson.get("access_token");
        String picUrl = iItemService.insertIntoItemDimension(itemId.toString(), "pages/item/detail", token);
        if (StringUtil.isNotBlank(picUrl)) {
            if (itemId != null) {
                System.out.println(picUrl);
                ItemDO item = new ItemDO();
                item.setId(itemId);
                item.setQrCodeUrl(picUrl);
                iItemService.updateByIdSelective(item);
            }
        }
        return result.buildIsSuccess(true);
    }


    /**
     * 新增商品同时生成二维码
     *
     * @param itemId
     */
    public void voidDimensionCodeUtil(Long itemId) {
//		/logger.info("voidDimensionCodeUtil start");
        String reponse = DimensionCodeUtil.sendGet(getaccess_tokenurl, getaccess_tokenparam);
        JSONObject myJson = JSONObject.fromObject(reponse);
        String token = (String) myJson.get("access_token");
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
//
//    /**
//     * 查询买手列表
//     *
//     * @return
//     */
//    @RequestMapping("/queryAllItaliaBuyer")
//    @ResponseBody
//    public Object queryAllItaliaBuyer() {
//        //logger.info("voidDimensionCodeUtil start");
//        //EntityWrapper<BuyerDO> entityWrapper = new EntityWrapper<BuyerDO>();
//        //entityWrapper.orderBy("gmt_create");
//        //Integer powerCode = 0;
//        //entityWrapper.where("power_code > {0}",powerCode);
//        List<BuyerDO> buyers = buyerService.queryAllBuyers();
//        return JsonResult.buildSuccess(buyers);
//    }


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
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
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
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
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
     * @param file
     * @return
     */
    @RequestMapping("/improtItem")
    @ResponseBody
    public Object importTask(MultipartFile file) {
        JsonResult<Object> result = new JsonResult<>();
        try {
            if (!file.isEmpty()) {
                // 文件保存路径
                List<List<Object>> list = ReadExcel.readExcel(file.getInputStream(),file.getOriginalFilename(),1,0,16);
                iItemService.importItem(list);
            }
        } catch (IOException e) {
            return result.buildIsSuccess(false).buildMsg("文件上传错误，请重试");
        } catch (ErpCommonException e) {
            String str = e.getErrorMsg().replace(",", "</br>");
            return result.buildIsSuccess(false).buildMsg(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.buildIsSuccess(true).buildMsg("上传成功");
    }

}

