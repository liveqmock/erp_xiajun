package com.wangqin.globalshop.item.app.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.Exception.ErpCommonException;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelListingItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemBrandDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingPackingPatternDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelListingItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ChannelListingItemSkuDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuScaleMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ShippingPackingPatternDOMapperExt;
import com.wangqin.globalshop.biz1.app.dto.ItemDTO;
import com.wangqin.globalshop.biz1.app.vo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuAddVO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.channelapi.dal.ChannelListingItemSkuVo;
import com.wangqin.globalshop.channelapi.dal.ChannelListingItemVo;
import com.wangqin.globalshop.channelapi.dal.GlobalShopItemVo;
import com.wangqin.globalshop.channelapi.dal.ItemSkuVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.channelapi.dal.JdCommonParam;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.redis.Cache;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.BeanUtils;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.ImageUtil;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.common.utils.StringUtil;
import com.wangqin.globalshop.common.utils.StringUtils;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.item.app.service.IItemBrandService;
import com.wangqin.globalshop.item.app.service.IItemCategoryService;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.item.app.service.IItemSkuScaleService;
import com.wangqin.globalshop.item.app.service.IItemSkuService;
import com.wangqin.globalshop.item.app.service.IUploadFileService;
import com.wangqin.globalshop.item.app.service.impl.entity.ShareTokenEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ItemServiceImplement implements IItemService {

    @Autowired
    private ItemDOMapperExt itemDOMapperExt;  
    @Autowired
    private IItemBrandService brandService;   
    @Autowired
    private IItemSkuScaleService scaleService;    
    @Autowired
    private InventoryService invService;
    @Autowired
    private ChannelListingItemDOMapperExt channelListingItemDOMapperExt;
    @Autowired
    private ChannelListingItemSkuDOMapperExt channelListingItemSkuDOMapperExt;
    @Autowired
    private InventoryMapperExt inventoryDOMapperExt;
    @Autowired
    private ItemSkuScaleMapperExt itemSkuScaleDOMapper;
    @Autowired
    private IItemSkuService itemSkuService;
    @Autowired
    private IItemBrandService iBrandService;
    @Autowired
    private IUploadFileService uploadFileService;
    @Autowired
    private ItemSkuMapperExt itemSkuMapperExt;
    @Autowired
    private IItemCategoryService categoryService;
    @Autowired
    private ShippingPackingPatternDOMapperExt shippingPackingPatternDOMapper;
    @Autowired
    private Cache shareCache;

    @Override
   	public ItemDO queryItemDOByItemCode(String itemCode) {
    	return itemDOMapperExt.queryItemDOByItemCode(itemCode);
    }
    
    @Override
	public String queryItemCodeById(Long id) {
		return itemDOMapperExt.queryItemCodeById(id);
	}
	
    //根据id更新商品(在用)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByIdSelective(ItemDO item) {
        itemDOMapperExt.updateByIdSelective(item);
    }

    //插入单个商品(在用)
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertItemSelective(ItemDO item) {
        return itemDOMapperExt.insertItemSelective(item);
    }

    @Transactional(rollbackFor = ErpCommonException.class)
    @Override
    public Object addItem(ItemQueryVO item) {
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
                        //itemSku.setPackageId(a.get(a.size() - 1));
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
        	itemSku.setBrand(newItem.getBrandName());
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
        
        insertItemSelective(newItem);
        return result.buildIsSuccess(true).buildMsg("添加商品成功");

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

    // 计算当前SKU的运费
    private void skuFreight(ItemSkuDO itemSku) {
        Double packageWeight = 0.0d;
        // 1,获取包装重量
        /**
         * if(itemSku.getPackageId()!=null){ PackageLevel pl= iFreightService.getPackageLevel(itemSku.getPackageId());
         * if(pl==null){ return; }else{ packageWeight = pl.getWeight(); itemSku.setPackageWeight(packageWeight);
         * itemSku.setPackageEn(pl.getPackageEn()); itemSku.setPackageName(pl.getName()); } } Double itemWeight = 0.0d;
         * if(itemSku.getWeight()!=null){ itemWeight = itemSku.getWeight(); }
         **/
        // if(itemSku.getPackageWeight()!=null){
        // packageWeight = itemSku.getPackageWeight();
        // }
        // Long f = iFreightService.calculateFreight(itemWeight, packageWeight);
        // itemSku.setFreight(f);
    }


    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public ItemDO queryIte(Long id) {
        if (id == null) {
            throw new RuntimeException("the item id is null");
        }
        ItemDO item = itemDOMapperExt.selectByPrimaryKey(id);
        if (item != null) {
            // Map<String,Object> clumnMap = new HashMap<>();
            // clumnMap.put("item_id", id);
            List<ItemSkuDO> itemSkus = itemSkuService.queryItemSkusByItemId(id);
            if (itemSkus != null) {
                // item.setItemSkus(itemSkus);
            }
        }
        return item;
    }

    //商品列表
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public JsonPageResult<List<ItemDTO>> queryItems(ItemQueryVO itemQueryVO) {
        JsonPageResult<List<ItemDTO>> itemResult = new JsonPageResult<>();
        // 1、查询总的记录数量
        Integer totalCount = itemDOMapperExt.queryItemsCount(itemQueryVO);

        // 2、查询分页记录
        if (totalCount != null && totalCount != 0) {
            itemResult.buildPage(totalCount, itemQueryVO);
            List<ItemDTO> items = itemDOMapperExt.queryItems(itemQueryVO);
            itemResult.setData(items);
        } else {
            List<ItemDTO> items = new ArrayList<>();
            itemResult.setData(items);
        }
        return itemResult;
    }

    /**
     * 根据id查询商品，商品编辑使用(fin)
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public ItemDTO queryItemById(Long id) {
        if (null == id) {
            throw new RuntimeException("商品的id不能为空");
        }
        ItemDTO item = itemDOMapperExt.queryItemById(id);
        if(null == item) {
        	throw new RuntimeException("商品不存在或者已删除");
        }
        List<ItemSkuQueryVO> itemSkus = itemSkuService.querySkuListByItemCodeContainsVirtualInvScale(item.getItemCode());
        if(EasyUtil.isListEmpty(itemSkus)) {
        	throw new RuntimeException("商品无sku，无法编辑");
        }
        for(ItemSkuQueryVO sku:itemSkus) {
        	List<ItemSkuScaleDO> skuScaleList = itemSkuScaleDOMapper.selectScaleNameValueBySkuCode(sku.getSkuCode());
        	if(!EasyUtil.isListEmpty(skuScaleList)) {
        		for(ItemSkuScaleDO scale:skuScaleList) {
        			if("颜色".equals(scale.getScaleName())) {
        				sku.setColor(scale.getScaleValue());
        			}
        			if("尺寸".equals(scale.getScaleName())) {
        				sku.setScale(scale.getScaleValue());
        			}
        		}
        	}
        }
        item.setItemSkus(itemSkus);
        return item;
    }

    @Override
    public List<ItemDO> queryItems(List<Long> ids) {
        // return itemDOMapperExt.selectBatchIds(ids);
        return null;
    }

    /**
     * 获取所有的 ItemCode and Id 2017-04-04,jc
     */
    @Override
    public Map<String, Long> queryAllItemCodeAndIdHashMap() {
        List<Map<String, Object>> codeAndIdMap = itemDOMapperExt.queryAllItemCodeAndIdHashMap();
        Map<String, Long> result = new HashMap<String, Long>();

        String itemCode = null;
        Long id = null;
        for (Map<String, Object> map : codeAndIdMap) {
            itemCode = null;
            id = null;

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if ("id".equals(entry.getKey())) {
                    id = ((Long) entry.getValue());
                } else if ("itemCode".equals(entry.getKey())) {
                    itemCode = entry.getKey();
                }
            }
            result.put(itemCode, id);
        }

        return result;
    }


    public ShareTokenEntity getTokenFromCache(String uuid){
        ShareTokenEntity entity = null;
        if (StringUtils.isBlank(uuid)){
            return entity;
        }
        String tokenStr = (String)shareCache.get(uuid);
        if (StringUtils.isNotBlank(tokenStr)){
            entity = BaseDto.fromJson(tokenStr, ShareTokenEntity.class);
        }
        return entity;
    }

    public String generateItemShareUrl(String userId,  String companyNo,String itemCode, String pages, String accessToken){

        String key = String.format("%s-uuid-%s-%s-%s", "token",userId, companyNo, itemCode);
        String picUrl = (String)shareCache.get(key);
        if (StringUtils.isBlank(picUrl)){
            UUID uid = UUID.randomUUID();
            String uuid = uid.toString().replaceAll("-", "");
            ShareTokenEntity tokenEntity = ShareTokenEntity.buildShareToken(userId, companyNo, itemCode, uuid);
            picUrl = insertIntoItemDimension(uuid, pages, accessToken);
            // uuid:picUrl
            shareCache.put(uuid, BaseDto.toString(tokenEntity));
            // key:uuid
            shareCache.put(key, picUrl);
        }
        return picUrl;
    }


    @Override
    public String insertIntoItemDimension(String sceneStr, String pages, String accessToken) {
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String picUrl = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
            Map<String, Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", pages);
            // param.put("path", pages);
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class);
            byte[] result = entity.getBody();
            inputStream = new ByteArrayInputStream(result);

            StringBuilder sb = new StringBuilder();
            sb.append((int) (Math.random()
                    * 100)).append("_").append(System.currentTimeMillis()).append(".").append("jpg");
            String picKey = sb.toString();
            picUrl = uploadFileService.uploadImg(inputStream, picKey);
            if (picUrl.isEmpty()) {
                throw new RuntimeException("二维码生成异常！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("调用生成微信URL接口传参:" + e);
            // LOG.error("调用小程序生成微信永久小程序码URL接口异常",e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return picUrl;

    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public JsonPageResult<List<ItemDO>> queryHaihuItems(ItemQueryVO itemQueryVO) {
        JsonPageResult<List<ItemDO>> itemResult = new JsonPageResult<>();
        // 1、查询总的记录数量
        Integer totalCount = itemDOMapperExt.queryItemsCountByhaihu(itemQueryVO);

        // 2、查询分页记录
        if (totalCount != null && totalCount != 0) {
            itemResult.buildPage(totalCount, itemQueryVO);
            List<ItemDO> items = itemDOMapperExt.queryHaihuItems(itemQueryVO);
            itemResult.setData(items);
        } else {
            List<ItemDO> items = new ArrayList<>();
            itemResult.setData(items);
        }
        return itemResult;
    }

    @Override
    public List<ItemDO> queryHaihuByUptime(ItemQueryVO itemQueryVO) {
        List<ItemDO> items = itemDOMapperExt.queryHaihuByUptime(itemQueryVO);
        return items;
    }

    @Override
    public Integer sumNewItemNumByDate(Integer days) {
        return itemDOMapperExt.sumNewItemNumByDate(days);
    }

    @Override
    public Integer sumNewItemNumByMonth(Integer months) {
        return itemDOMapperExt.sumNewItemNumByMonth(months);
    }

    @Override
    public List<ItemDTO> queryItemListSelective(ItemQueryVO itemQueryVO) {
        List<ItemDTO> list = itemDOMapperExt.queryItems(itemQueryVO);
        for (ItemDTO item : list) {
            item.setCategoryId(337L);
        }
        return itemDOMapperExt.queryItems(itemQueryVO);
    }


    @Override
    public Long queryIdByItemCode(String itemCode) {
        return itemDOMapperExt.queryIdByItemCode(itemCode);
    }


    /**
     * 专供渠道查询
     *
     * @param itemCode
     * @return
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public ItemVo queryAdd(String itemCode) {

        ItemVo itemVo = new ItemVo();
        ItemDO itemDo = itemDOMapperExt.queryItemByItemCode(itemCode);
        if (itemDo != null) {
            BeanUtils.copies(itemDo, itemVo);
        }
        List<ItemSkuVo> itemSkuVos = new ArrayList<>();
        ItemSkuDO skuSo = new ItemSkuDO();
        skuSo.setItemCode(itemDo.getItemCode());
        List<ItemSkuDO> skuList = itemSkuService.querySkuListByItemCode(itemCode);
        for (ItemSkuDO sku : skuList) {
            ItemSkuVo itemSkuVo = new ItemSkuVo();
            if (sku != null) {
                BeanUtils.copies(sku, itemSkuVo);
            }
            //库存
            InventoryDO inventoryDO = inventoryDOMapperExt.queryInventoryByCode(sku.getItemCode(), sku.getSkuCode());
            itemSkuVo.setInventoryDO(inventoryDO);

            //规格尺寸
            List<ItemSkuScaleDO> itemSkuScaleDOS = itemSkuScaleDOMapper.selectScaleNameValueBySkuCode(sku.getSkuCode());
            Map<String, ItemSkuScaleDO> scaleMap = new HashMap<>();
            for (ItemSkuScaleDO scale : itemSkuScaleDOS) {
                scaleMap.put(scale.getScaleCode(), scale);
            }
            itemSkuVo.setScaleMap(scaleMap);

            itemSkuVos.add(itemSkuVo);

        }
        itemVo.setItemSkus(itemSkuVos);
        return itemVo;

    }

    /**
     * 专供渠道查询
     *
     * @param itemCode
     * @return
     */
    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public GlobalShopItemVo queryUpdate(String itemCode, String shopCode) {

        GlobalShopItemVo resultVo = new GlobalShopItemVo();

        ItemVo itemVo = this.queryAdd(itemCode);

        resultVo.setItemVo(itemVo);

        ChannelListingItemVo channelListingItemVo = new ChannelListingItemVo();

        ChannelListingItemDO so = new ChannelListingItemVo();

        so.setItemCode(itemVo.getItemCode());
        so.setShopCode(shopCode);

        ChannelListingItemDO channelListingItemDO = channelListingItemDOMapperExt.queryPo(so);

        if (channelListingItemDO != null) {
            BeanUtils.copies(channelListingItemDO, channelListingItemVo);
        }


        ChannelListingItemSkuDO skuSo = new ChannelListingItemSkuDO();
        skuSo.setChannelItemCode(channelListingItemDO.getChannelItemCode());


        List<ChannelListingItemSkuDO> channelListingItemSkuDOS = channelListingItemSkuDOMapperExt.queryPoList(skuSo);

        List<ChannelListingItemSkuVo> channelListingItemSkuVos = new ArrayList<>();
        for (ChannelListingItemSkuDO sku : channelListingItemSkuDOS) {
            ChannelListingItemSkuVo skuVo = new ChannelListingItemSkuVo();
            if (sku != null) {
                BeanUtils.copies(sku, skuVo);
            }
            channelListingItemSkuVos.add(skuVo);
        }

        channelListingItemVo.setChannelListingItemSkuVos(channelListingItemSkuVos);


        resultVo.setChannelListingItemVo(channelListingItemVo);

        return resultVo;

    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void dealItemAndChannelItem4JdAdd(JdCommonParam jdCommonParam, GlobalShopItemVo globalShopItemVo) {

        ChannelListingItemVo channelListingItemVo = globalShopItemVo.getChannelListingItemVo();

        //第一步：channellistingitem
        ChannelListingItemDO channelListingItemDO = new ChannelListingItemDO();
        BeanUtils.copies(channelListingItemVo, channelListingItemDO);
        channelListingItemDOMapperExt.insert(channelListingItemDO);

        //第二步：channellistingitemSku
        List<ChannelListingItemSkuVo> channelListingItemSkuVoS = channelListingItemVo.getChannelListingItemSkuVos();
        for (ChannelListingItemSkuVo sku : channelListingItemSkuVoS) {
            ChannelListingItemSkuDO channelListingItemSkuDO = new ChannelListingItemSkuDO();
            BeanUtils.copies(sku, channelListingItemSkuDO);
            channelListingItemSkuDOMapperExt.insert(channelListingItemSkuDO);
        }

        ItemVo itemVo = globalShopItemVo.getItemVo();

        ItemDO itemDO = new ItemDO();
        BeanUtils.copies(itemVo, itemDO);
        itemDOMapperExt.insert(itemDO);

        List<ItemSkuVo> skuVos = itemVo.getItemSkus();
        for (ItemSkuVo skuVo : skuVos) {
            ItemSkuDO skuDO = new ItemSkuDO();
            BeanUtils.copies(skuVo, skuDO);
            itemSkuMapperExt.insert(skuDO);
        }
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void dealItemAndChannelItem4JdTask(JdCommonParam jdCommonParam, GlobalShopItemVo globalShopItemVo) {

        ChannelListingItemVo channelListingItemVo = globalShopItemVo.getChannelListingItemVo();

        //第一步：channellistingitem
        ChannelListingItemDO channelListingItemDO = new ChannelListingItemDO();
        BeanUtils.copies(channelListingItemVo, channelListingItemDO);
        channelListingItemDOMapperExt.insert(channelListingItemDO);

        //第二步：channellistingitemSku
        List<ChannelListingItemSkuVo> channelListingItemSkuVoS = channelListingItemVo.getChannelListingItemSkuVos();
        for (ChannelListingItemSkuVo sku : channelListingItemSkuVoS) {
            ChannelListingItemSkuDO channelListingItemSkuDO = new ChannelListingItemSkuDO();
            BeanUtils.copies(sku, channelListingItemSkuDO);
            channelListingItemSkuDOMapperExt.insert(channelListingItemSkuDO);
        }

        ItemVo itemVo = globalShopItemVo.getItemVo();

        ItemDO itemDO = new ItemDO();
        BeanUtils.copies(itemVo, itemDO);
        itemDOMapperExt.insert(itemDO);

        List<ItemSkuVo> skuVos = itemVo.getItemSkus();
        for (ItemSkuVo skuVo : skuVos) {
            ItemSkuDO skuDO = new ItemSkuDO();
            BeanUtils.copies(skuVo, skuDO);
            itemSkuMapperExt.insert(skuDO);
        }
    }



    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void importItem(List<List<Object>> list) throws ErpCommonException {
        List<String> errMsg = new ArrayList<>();
        List<ItemDO> itemList = new ArrayList<>();
        List<ItemSkuDO> skuList = new ArrayList<>();
        List<ItemSkuScaleDO> scaleList = new ArrayList<>();
        int i = 0;
        for (List<Object> obj : list) {
            i++;
            ItemDO item = new ItemDO();
            ItemSkuDO itemSku = new ItemSkuDO();
            /**SKU编号*/
            String skuCode = obj.get(0).toString().trim();
            itemSku.setSkuCode(skuCode);
            /**商品名称*/
            String itemName = obj.get(1).toString().trim();
            item.setItemName(itemName);
            itemSku.setItemName(itemName);
            /**UPC*/
            itemSku.setUpc(obj.get(2).toString().trim());
            /**品牌(英文)*/
            String brandEnName = obj.get(3).toString();
            /**品牌(中文)*/
            String brandCnName = obj.get(4).toString().trim();
            item.setBrandName(brandEnName + " " + brandCnName);
            itemSku.setBrandName(brandEnName + " " + brandCnName);
            List<ItemBrandDO> brand = iBrandService.queryByEnNameAndCnName(brandEnName, brandCnName);
            if (brand.size() == 0) {
                errMsg.add("第" + i + "行:找不到" + brandEnName + " " + brandCnName + "对应的品牌");
            } else if (brand.size() > 1) {
                errMsg.add("第" + i + "行:" + brandEnName + " " + brandCnName + "对应的品牌不唯一");
            } else {
                item.setBrandNo(brand.get(0).getBrandNo());
            }
            /**类目1*/
            String category1 = obj.get(5).toString().trim();
            /**类目2*/
            String category2 = obj.get(6).toString().trim();
            /**类目3*/

            String category3 = obj.get(7).toString().trim();
            List<ItemCategoryDO> categoryList = getByCategory(category1, category2, category3);
            String categoryCode = "";
            String itemCode = CodeGenUtil.getItemCode(categoryCode);
            if (categoryList == null || categoryList.size() == 0) {
                errMsg.add("第" + i + "行:找不到" + category1 + "/" + category2 + "/" + category3 + "对应的类目");
            } else if (categoryList.size() > 1) {
                errMsg.add("第" + i + "行:" + category1 + "/" + category2 + "/" + category3 + "对应的类目不唯一");
            } else {
                ItemCategoryDO category = categoryList.get(0);
                categoryCode = category.getCategoryCode();
                item.setCategoryCode(categoryCode);
                item.setCategoryName(category.getName());
                itemSku.setCategoryCode(categoryCode);
                itemSku.setCategoryCode(category.getName());
            }
            /**规格(颜色)*/
            String scala1 = obj.get(8).toString().trim();
            /**规格(尺寸)*/
            String scala2 = obj.get(9).toString().trim();
            /**采购地*/
            String purchaseFrom = obj.get(10).toString().trim();
            item.setCountry(purchaseFrom);
            /**币种*/
            String currency = obj.get(11).toString();
            currency = StringUtil.isBlank(currency) ? "0" : currency;
            if (isParseToInteger(currency)) {
                item.setCurrency(Byte.valueOf(currency));
            } else {
                errMsg.add("存在未知格式的数据:第" + i + "行 第13列的  " + currency);
            }
            /**销售价*/
            String salePrice = obj.get(12).toString();
            salePrice = StringUtil.isBlank(salePrice) ? "0" : salePrice;
            if (isParseToDouble(salePrice)) {
                itemSku.setSalePrice(Double.valueOf(salePrice));
            } else {
                errMsg.add("存在未知格式的数据:第" + i + "行 第13列的  " + salePrice);
            }
            /**原价*/
            String price = obj.get(13).toString();
            price = StringUtil.isBlank(price) ? "0" : price;
            if (isParseToDouble(price)) {
                itemSku.setCostPrice(Double.valueOf(price));
            } else {
                errMsg.add("存在未知格式的数据:第" + i + "行 第14列的  " + price);
            }
            /**重量*/
            String weight = obj.get(14).toString();
            weight = StringUtil.isBlank(weight) ? "0" : weight;
            if (isParseToDouble(weight)) {
                item.setWeight(Double.valueOf(weight));
                itemSku.setWeight(Double.valueOf(weight));
            } else {
                errMsg.add("存在未知格式的数据:第" + i + "行 第15列的  " + weight);
            }
            /**包装*/
            String packName = obj.get(15).toString();
            if (StringUtil.isNotBlank(packName)) {
                ShippingPackingPatternDO pack = shippingPackingPatternDOMapper.selectByName(packName);
                if (pack == null) {
                    errMsg.add("找不到对应的包装" + packName + ":第" + i + "行 第15列的  ");
                } else {
                    itemSku.setPackageCode(pack.getPatternNo());
                    itemSku.setPackageLevelId(String.valueOf(pack.getPackageLevel()));
                    itemSku.setPackageName(pack.getName());
                    itemSku.setPackageEn(pack.getNameEn());
                    itemSku.setPackageWeight(pack.getWeight());
                }
            }
            /**图片链接*/
            String imgUrl = obj.get(16).toString();
            item.setMainPic(imgUrl);
            itemSku.setSkuPic(imgUrl);

            item.setItemCode(itemCode);
            itemSku.setItemCode(itemCode);
            item.init();
            itemSku.init();

            itemList.add(item);
            skuList.add(itemSku);
            addInfo2ScaleList(scala1, scala2, scaleList,itemCode,skuCode);
        }
        int size = errMsg.size();
        if (size == 0) {
            itemDOMapperExt.insertBatch(itemList);
            itemSkuMapperExt.inserBatch(skuList);
            itemSkuScaleDOMapper.insertBatch(scaleList);
        } else if (size < 10) {
            throw new ErpCommonException(errMsg.toString());
        } else {
            throw new ErpCommonException("上传文件错误过多,请验证后再次上传");
        }


    }

    private List<ItemCategoryDO> getByCategory(String category1, String category2, String category3) {
        List<ItemCategoryDO> categoryList3 = categoryService.selectByName(category3);
        if (categoryList3.size()!=0){
            return categoryList3;
        }
        List<ItemCategoryDO> categoryList2 = categoryService.selectByName(category2);
        if (categoryList2.size()!=0){
            return categoryList2;
        }
        List<ItemCategoryDO> categoryList1 = categoryService.selectByName(category1);
        if (categoryList1.size()!=0){
            return categoryList1;
        }
        return null;
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

    /**
     * 根据颜色和尺寸完成itemSkuScale对象的封装并装配到链表中
     * @param scala1    颜色对应的值
     * @param scala2    尺寸对应的值
     * @param scaleList 装配itemSkuScale对象的链表
     * @param itemCode
     * @param skuCode
     */
    private void addInfo2ScaleList(String scala1, String scala2, List<ItemSkuScaleDO> scaleList, String itemCode, String skuCode) {
        ItemSkuScaleDO itemScale1 = new ItemSkuScaleDO();
        ItemSkuScaleDO itemScale2 = new ItemSkuScaleDO();
        itemScale1.init();
        itemScale1.setScaleCode(CodeGenUtil.getScaleCode());
        itemScale1.setScaleName("颜色");
        itemScale1.setScaleValue(scala1);
        itemScale1.setItemCode(itemCode);
        itemScale1.setSkuCode(skuCode);
        itemScale2.init();
        itemScale2.setScaleCode(CodeGenUtil.getScaleCode());
        itemScale2.setScaleName("尺寸");
        itemScale2.setScaleValue(scala2);
        itemScale2.setItemCode(itemCode);
        itemScale2.setSkuCode(skuCode);
        scaleList.add(itemScale1);
        scaleList.add(itemScale2);

    }

    /**
     * 判断是否能够转换成Integer类型
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

    //一键分享商品的首页
    @Override
    public List<ItemDO> queryItemByStatus(String companyNo, String status, int start, int pageSize) {
        // TODO Auto-generated method stub
        return itemDOMapperExt.queryItemByStatus(companyNo, status, start, pageSize);
    }

    //一键分享商品的搜索
    @Override
    public List<ItemDO> queryItemByKeyWord(List<String> keyWord, String companyNo, int start, int pageSize) {
        // TODO Auto-generated method stub
        return itemDOMapperExt.queryItemByKeyWord(keyWord, companyNo, start, pageSize);
    }

    //一键分享商品的详情
    @Override
    public ItemDO itemDetailByItemCode(String itemCode, String companyNo) {
        // TODO Auto-generated method stub
        return itemDOMapperExt.itemDetailByItemCode(itemCode, companyNo);
    }


    //一键分享，获取商品的图片
    @Override
    public String queryItemPicByItemCode(String itemCode) {
        return itemDOMapperExt.queryItemPicByItemCode(itemCode);
    }

    //一键分享，获取商品的图片
    @Override
    public String queryItemPicByItemCodeAndCompanyNo(String itemCode, String companyNo) {
        return itemDOMapperExt.queryItemPicByItemCodeAndCompanyNo(itemCode, companyNo);
    }
}
