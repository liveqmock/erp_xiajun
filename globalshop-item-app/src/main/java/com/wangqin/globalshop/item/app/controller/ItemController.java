package com.wangqin.globalshop.item.app.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;
import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.BuyerDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.CountryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dto.ItemDTO;

import com.wangqin.globalshop.biz1.app.vo.InventoryAddVO;
import com.wangqin.globalshop.biz1.app.vo.ItemQueryVO;
import com.wangqin.globalshop.biz1.app.vo.ItemSkuAddVO;

import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.DimensionCodeUtil;
import com.wangqin.globalshop.common.utils.EasyuiJsonResult;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.ImageUtil;
import com.wangqin.globalshop.common.utils.RandomUtils;
import com.wangqin.globalshop.common.utils.StringUtils;

import com.wangqin.globalshop.item.app.service.IBuyerService;
import com.wangqin.globalshop.item.app.service.ICountryService;
import com.wangqin.globalshop.item.app.service.IInventoryService;
import com.wangqin.globalshop.item.app.service.IItemBrandService;
import com.wangqin.globalshop.item.app.service.IItemCategoryService;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.item.app.service.IItemSkuService;
import com.wangqin.globalshop.item.app.service.ISequenceUtilService;





import net.sf.json.JSONObject;

/**
 * 商品处理器
 * 
 * @author zhulu
 *
 */
@Controller
@RequestMapping("/item")
public class ItemController  {

	@Autowired
	private IItemService iItemService;
	
	@Autowired
	private IItemSkuService itemSkuService;
	
	@Autowired
	private IItemCategoryService categoryService;
	
	@Autowired
	private ISequenceUtilService sequenceUtilService;
	
	
	@Autowired
	private IInventoryService inventoryService;
	
	@Autowired
	private IItemBrandService brandService;
	

	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private IBuyerService buyerService;
	
	
	
	
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
		//logger.info("add item start");
		JsonResult<ItemDO> result = new JsonResult<>();
		if (item.getId() == null) {
			StringBuffer nameNew = new StringBuffer();
			//品牌
			String[] brandArr = item.getBrand().split("->");
			if(StringUtil.isNotBlank(brandArr[0])) {	//英文品牌
				nameNew.append(brandArr[0] + " ");
			}
			if(brandArr.length>1 && StringUtil.isNotBlank(brandArr[1])) {	//中文品牌
				nameNew.append(brandArr[1] + " ");
			}
			if(StringUtil.isNotBlank(item.getSexStyle())) {		//男女款
				nameNew.append(item.getSexStyle() + " ");
			}
			nameNew.append(item.getName());
			//重新设置商品名称
			item.setName(nameNew.toString());

			ItemCategoryDO category = categoryService.selectByPrimaryKey(item.getCategoryId());
			String categoryCode = category.getCategoryCode();
			if(category!=null && categoryCode!=null){
				item.setCategoryName(category.getName());
			}else{
				return result.buildMsg("没有找到类目").buildIsSuccess(false);
			}
			//设置item_code栏位的值
			//item.setItemCode("I" + categoryCode + "Q" + sequenceUtilService.gainItemSequence());
			item.setItemCode("I" + categoryCode + "Q" + RandomUtils.getTimeRandom());
			String imgJson = ImageUtil.getImageUrl(item.getMainPic());
			
			// 解析skuList 数组对象
			String skuList = item.getSkuList();
			Double minPrice = null;
			Double maxPrice = null;
			if (StringUtils.isNotBlank(skuList)) {
				try {
					String s = skuList.replace("&quot;", "\"");
					List<ItemSkuAddVO> skus = HaiJsonUtils.toBean(s, new TypeReference<List<ItemSkuAddVO>>(){});
					Map<String, Integer> colorScaleMap = new HashMap<String, Integer>();
					int i = 0;
					if (skus != null && !skus.isEmpty()) {
						for(ItemSkuAddVO itemSku : skus) {
							//颜色和尺寸不能都为空
							if(StringUtil.isBlank(itemSku.getColor()) && StringUtil.isBlank(itemSku.getScale())) {
								return result.buildMsg("颜色和尺寸不能都为空").buildIsSuccess(false);
							}
							if(minPrice == null) {
								minPrice = itemSku.getSalePrice();
								maxPrice = itemSku.getSalePrice();
							} else {
								if(minPrice > itemSku.getSalePrice())  minPrice = itemSku.getSalePrice();
								if(maxPrice < itemSku.getSalePrice())  maxPrice = itemSku.getSalePrice();
							}
							//判断颜色，尺寸是否冲突
							String colorScaleKey = "";
							if(itemSku.getColor() != null) {
								String color = itemSku.getColor().trim();
								colorScaleKey += color;
								itemSku.setColor(color);
							}
							if(itemSku.getScale() != null) {
								String scale = itemSku.getScale().trim();
								colorScaleKey += scale;
								itemSku.setScale(scale);
							}
							if(StringUtil.isBlank(colorScaleKey)) colorScaleKey = "none";
							if(colorScaleMap.get(colorScaleKey)!=null) {
								return result.buildMsg("SKU颜色尺码重复").buildIsSuccess(false);
							}
							colorScaleMap.put(colorScaleKey, 1);
							
							itemSku.setSkuCode("S" + item.getItemCode().substring(1) + String.format("%0"+4+"d", ++i));
							itemSku.setLogisticType(item.getLogisticType());
							itemSku.setBuySite(item.getBuySite());
					
							String skuPic = ImageUtil.getImageUrl(itemSku.getSkuPic());
							itemSku.setSkuPic(skuPic);
							if(StringUtils.isNotBlank(itemSku.getPackageLevelId()) ){
							  List<Long> a =  HaiJsonUtils.toBean(itemSku.getPackageLevelId(), new TypeReference<List<Long>>(){});
							  itemSku.setPackageId(a.get(a.size()-1));
							}
							// 如果商品没有图片，默认使用sku上的图片
							if (StringUtils.isBlank(imgJson) && StringUtils.isNotBlank(skuPic)) {
								imgJson = skuPic;
							}
						}
						item.setItemSkus(skus);
					}
				} catch (Exception e) {
					e.printStackTrace();
					return result.buildMsg("解析SKU错误").buildIsSuccess(false);
				}
			}
			//商品价格区间
			if(minPrice.equals(maxPrice)) {
				item.setPriceRange(maxPrice.toString());
			}  else {
				item.setPriceRange(minPrice.toString() + "-" + maxPrice.toString());
			}
			
			item.setMainPic(imgJson);
			//detailDecoder(item);
			//判断是否可售
//			if(item.getStartDate()==null || item.getEndDate()==null) {
//				item.setIsSale(0);
//			} else if(DateUtil.belongCalendar(new Date(), item.getStartDate(), DateUtil.getDateByCalculate(item.getEndDate(), Calendar.DATE, 1))) {
//				item.setIsSale(1);
//			} else {
//				item.setIsSale(0);
//			}
			//item.setCompanyId(ShiroUtil.getShiroUser().getCompanyId());			
			
			// 设置销售渠道
			if (CollectionUtils.isEmpty(item.getSaleOnChannels())) {
				item.setThirdSale(0);
				item.setSaleOnYouzan(0);
			} else {
				for (Integer type : item.getSaleOnChannels()) {
					ChannelType channel = ChannelType.getChannelType(type);
					if (channel == null) {
						continue;
					}
					switch (channel) {
					case YouZan:
						item.setSaleOnYouzan(1);
						break;
					case HaiHu:
						item.setThirdSale(1);
						break;
					case TaoBao:
						break;
					}
				}
			}	
			ItemDO newItem = new ItemDO();
			newItem.setCategoryCode(categoryService.selectByPrimaryKey(item.getCategoryId()).getCategoryCode());
			newItem.setCategoryName(categoryService.selectByPrimaryKey(item.getCategoryId()).getName());
			newItem.setBrandName(item.getBrand());
			newItem.setBrandNo(brandService.selectBrandNoByName(item.getBrand()));
			newItem.setEnName(item.getEnName());
			newItem.setItemName(item.getName());
			newItem.setCurrency(item.getCurrency().byteValue());
			newItem.setIdCard(item.getIdCard().byteValue());
			CountryDO countryDO = new CountryDO();
			countryDO.setId(item.getCountry());
			newItem.setCountry(countryService.queryCodeById(item.getCountry()));
			DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				newItem.setStartDate(format.parse(item.getStartDate()));
				newItem.setEndDate(format.parse(item.getEndDate()));
				newItem.setBookingDate(format.parse(item.getBookingDate()));
			} catch(Exception e) {
				//
			}
			newItem.setItemCode(RandomUtils.getTimeRandom());
			
	        newItem.setDesc(item.getRemark());
	        newItem.setCompanyNo(RandomUtils.getTimeRandom());      
	        iItemService.insertItemSelective(newItem);
	        /**插入itemsku和库存**/
	        List<ItemSkuAddVO> itemSkuList = item.getItemSkus();
	        		if (itemSkuList != null && !itemSkuList.isEmpty()) {
	        			itemSkuList.forEach(itemSku -> {
	        				itemSku.setItemCode(item.getItemCode());
	        				itemSku.setItemName(item.getName());
	        				itemSku.setItemId(item.getId());
	        				itemSku.setCategoryId(item.getCategoryId());
	        				itemSku.setCategoryName(item.getCategoryName());
	        				itemSku.setBrand(item.getBrand());
	        				itemSku.setCompanyId(item.getCompanyId());
	        				itemSku.setCompanyNo("c12");
	        				itemSku.setCreator("admin");
	        				itemSku.setModifier("admin");
	        				itemSku.setUpc(RandomUtils.getTimeRandom());
	        				//skuFreight(itemSku);
	        			});
	        			itemSkuService.insertBatch(itemSkuList);
	        			List<InventoryAddVO> inventoryList = itemSkuService.initInventory(itemSkuList);
	        			inventoryService.insertBatchInventory(inventoryList);
	        		}
			//同步到有赞并上架
			
			if(item.getIsSale()!=null && item.getIsSale()==1) {
				if (item.getSaleOnYouzan() == 1) {
					try {
						//outerItemService.synItemYouzan(item.getId());
						//ShiroUser user = ShiroUtil.getShiroUser();
						String companyNo = "c23";
						//ChannelFactory.getChannel(companyNo, ChannelType.YouZan).createItem(item.getId());
					} catch(Exception e) {
						//logger.error("商品添加时同步到有赞：", e);
					}
				}				
			}
						
			//同步生成小程序的二维码
			if(item.getId()!= null) {
				voidDimensionCodeUtil(item.getId());
			}
			/**
			//新增商品授权买手
			String[] buyers = item.getOwners().split(",");
			BuyerDO itemBuyer = new BuyerDO();
			for (String string : buyers) {
				itemBuyer.setItemId(item.getId());
				itemBuyer.setBuyerId(Long.valueOf(string));
				itemBuyer.setGmtCreate(new Date());
				itemBuyer.setGmtModify(new Date());
				itemBuyerMapper.insert(itemBuyer);
			}**/
			return result.buildIsSuccess(true);
		} else {
			return result.buildMsg("新增不能有ID").buildIsSuccess(false);
		}

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
		//logger.info("update item start");
		JsonResult<ItemDO> result = new JsonResult<>();
//		ItemDO oldItem = iItemService.queryItem(item.getId());
//		if(StringUtil.isBlank(item.getDetail()) && StringUtil.isNotBlank(oldItem.getDetail())) {
//		    return result.buildMsg("商品详情不能为空").buildIsSuccess(false);
//		}
		// if haven't item id ,add item
		if (item.getId() == null) {
			return result.buildIsSuccess(false).buildMsg("没有Item id");
		} else {
			
			StringBuffer nameNew = new StringBuffer();
			//品牌
			String[] brandArr = item.getBrand().split("->");
			if(StringUtil.isNotBlank(brandArr[0])) {	//英文品牌
				nameNew.append(brandArr[0] + " ");
			}
			if(brandArr.length>1 && StringUtil.isNotBlank(brandArr[1])) {	//中文品牌
				nameNew.append(brandArr[1] + " ");
			}
			if(StringUtil.isNotBlank(item.getSexStyle())) {		//男女款
				nameNew.append(item.getSexStyle() + " ");
			}
			nameNew.append(item.getName());
			//重新设置商品名称
			item.setName(nameNew.toString());
			
			ItemCategoryDO category = categoryService.selectByPrimaryKey(item.getCategoryId());
			if(category!=null){
				item.setCategoryName(category.getName());
			}else{
				return result.buildMsg("没有找到类目").buildIsSuccess(false);
			}
		}
/*
			String skuList = item.getSkuList();
			Double minPrice = null;
			Double maxPrice = null;
			if (StringUtils.isNotBlank(skuList)) {
				Integer i = itemSkuService.queryMaxSkuCodeIndex(item.getId());
				if(i==null) i=0;
				
				try {
					String s = skuList.replace("&quot;", "\"");
					List<ItemSku> skus = HaiJsonUtils.toBean(s, new TypeReference<List<ItemSku>>(){});
					Map<String, Integer> colorScaleMap = new HashMap<String, Integer>();
					if (skus != null && !skus.isEmpty()) {
						for(ItemSku itemSku : skus) {
							//颜色和尺寸不能都为空
							if(StringUtil.isBlank(itemSku.getColor()) && StringUtil.isBlank(itemSku.getScale())) {
								return result.buildMsg("颜色和尺寸不能都为空").buildIsSuccess(false);
							}
							if(minPrice == null) {
								minPrice = itemSku.getSalePrice();
								maxPrice = itemSku.getSalePrice();
							} else {
								if(minPrice > itemSku.getSalePrice())  minPrice = itemSku.getSalePrice();
								if(maxPrice < itemSku.getSalePrice())  maxPrice = itemSku.getSalePrice();
							}
							//判断颜色，尺寸是否冲突
							String colorScaleKey = "";
							if(itemSku.getColor()!=null) {
								String color = itemSku.getColor().trim();
								colorScaleKey += color;
								itemSku.setColor(color);
							}
							if(itemSku.getScale()!=null) {
								String scale = itemSku.getScale().trim();
								colorScaleKey += scale;
								itemSku.setScale(scale);
							}
							if(StringUtil.isBlank(colorScaleKey)) colorScaleKey = "none";
							if(colorScaleMap.get(colorScaleKey)!=null) {
								return result.buildMsg("SKU颜色尺码重复").buildIsSuccess(false);
							}
							colorScaleMap.put(colorScaleKey, 1);
							
							if(itemSku.getId()==null){
								itemSku.setSkuCode("S" + item.getItemCode().substring(1) + String.format("%0"+4+"d", ++i));
								itemSku.setGmtCreate(new Date());
								itemSku.setGmtModify(new Date());
							}
							itemSku.setLogisticType(item.getLogisticType());
							itemSku.setBuySite(item.getBuySite());
							itemSku.setItemId(item.getId());
							itemSku.setItemCode(item.getItemCode());
							itemSku.setItemName(item.getName());
							itemSku.setCategoryId(item.getCategoryId());
							itemSku.setCategoryName(item.getCategoryName());
							itemSku.setBrand(item.getBrand());
							String skuPic = ImageUtil.getImageUrl(itemSku.getSkuPic());
							itemSku.setSkuPic(skuPic);
							
							if(StringUtils.isNotBlank(itemSku.getPackageLevelId()) ){
								  List<Long> a =  HaiJsonUtils.toBean(itemSku.getPackageLevelId(), new TypeReference<List<Long>>(){});
								  itemSku.setPackageId(a.get(a.size()-1));
								}
						}
						item.setItemSkus(skus);
					}
				} catch (Exception e) {
					e.printStackTrace();
					return result.buildMsg("解析SKU错误").buildIsSuccess(false);
				}
			}
			//商品价格区间
			if(minPrice.equals(maxPrice)) {
				item.setPriceRange(maxPrice.toString());
			}  else {
				item.setPriceRange(minPrice.toString() + "-" + maxPrice.toString());
			}
			String imgJson = ImageUtil.getImageUrl(item.getMainPic());
			item.setMainPic(imgJson);
			detailDecoder(item);
			//判断是否可售
			if(item.getStartDate()==null || item.getEndDate()==null) {
				item.setIsSale(0);
			} else if(DateUtil.belongCalendar(new Date(), item.getStartDate(), DateUtil.getDateByCalculate(item.getEndDate(), Calendar.DATE, 1))) {
				item.setIsSale(1);
			} else {
				item.setIsSale(0);
			}
			//编辑运费
			if(item.getFreight()==null) {
				item.setFreight(0.0d);
			}
			
			// 设置销售渠道
			item.setThirdSale(0);
			item.setSaleOnYouzan(0);
			if (CollectionUtils.isEmpty(item.getSaleOnChannels())) {
			} else {
				for (Integer type : item.getSaleOnChannels()) {
					ChannelType channel = ChannelType.getChannelType(type);
					if (channel == null) {
						continue;
					}
					switch (channel) {
					case YouZan:
						item.setSaleOnYouzan(1);
						break;
					case HaiHu:
						item.setThirdSale(1);
						break;
					case TaoBao:
						break;
					}
				}
			}
			*/
			iItemService.updateItemById(item);
			/*
			//修改商品授权买手
			Map<String, Object> delMap = Maps.newHashMap();
			delMap.put("item_id", item.getId());
			itemBuyerMapper.deleteByMap(delMap);
			String[] buyers = item.getOwners().split(",");
			ItemBuyer itemBuyer = new ItemBuyer();
			for (String string : buyers) {
				itemBuyer.setItemId(item.getId());
				itemBuyer.setBuyerId(Long.valueOf(string));
				itemBuyer.setGmtCreate(new Date());
				itemBuyer.setGmtModify(new Date());
				itemBuyerMapper.insert(itemBuyer);
			}
			
			// 有赞售卖有变化
			if (oldItem.getSaleOnYouzan() != item.getSaleOnYouzan()) {
				try {
					ShiroUser user = ShiroUtil.getShiroUser();
					IChannelService channelService = ChannelFactory.getChannel(user.getCompanyId(), ChannelType.YouZan);
					if (item.getSaleOnYouzan() == 1 && item.getIsSale() != null && item.getIsSale() == 1) { //同步到有赞并上架
						channelService.syncItem(item.getId());
					} else { // 下架
						channelService.syncDelistingItem(item.getId());
					}
				} catch (Exception e) {
					logger.error("商品添加时同步到有赞：", e);
				}
			}
			
			return result.buildIsSuccess(true);
		}*/
		return null;
	}

	private void detailDecoder(ItemQueryVO item) {
		if(StringUtils.isNotBlank(item.getDetail())){
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
	 * @param id
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Object query(Long id) {
		//logger.info("query item start");
		JsonResult<ItemDTO> result = new JsonResult<>();
		// if haven't item id ,add item
		if (id != null) {
			ItemQueryVO itemQueryVO = new ItemQueryVO();
			ItemDTO item = iItemService.queryItemListSelective(itemQueryVO).get(0);
			if (item == null) {
				result.buildIsSuccess(false).buildMsg("没有找到Item");
			}
			String itemName = item.getName();
		
			/*
            if(StringUtils.isNotBlank(buyers)){
            	List<WxPurchaseUser>  wxPurchaseUser = new ArrayList();
            	EntityWrapper<ItemBuyer>  entityWrapper = new EntityWrapper<ItemBuyer>();
            	entityWrapper.where("item_id={0}", id);
            	List<ItemBuyer> buyerresult = itemBuyerService.selectList(entityWrapper);
            	for (ItemBuyer itemBuyer : buyerresult) {
            		WxPurchaseUser one = wxPurchaseUserService.selectById(itemBuyer.getBuyerId());
            		wxPurchaseUser.add(one);
				}
            	item.setWxList(wxPurchaseUser);
            }*/
          
			if(itemName.contains("婴儿款")) {
				item.setSexStyle("婴儿款");
			} else if(itemName.contains("大童男款")) {
				item.setSexStyle("大童男款");
			} else if(itemName.contains("大童女款")) {
				item.setSexStyle("大童女款");
			} else if(itemName.contains("小童男款")) {
				item.setSexStyle("小童男款");
			} else if(itemName.contains("小童女款")) {
				item.setSexStyle("小童女款");
			} else if(itemName.contains("男款")) {
				item.setSexStyle("男款");
			} else if(itemName.contains("女款")) {
				item.setSexStyle("女款");
			} else if(itemName.contains("大童款")) {
				item.setSexStyle("大童款");
			} else if(itemName.contains("小童款")) {
				item.setSexStyle("小童款");
			}
			  
			StringBuffer nameRep = new StringBuffer();
			//品牌
			String[] brandArr = item.getBrand().split("->");
			
			if(StringUtil.isNotBlank(brandArr[0])) {	//英文品牌
				nameRep.append(brandArr[0] + " ");
			}
			if(brandArr.length>1 && StringUtil.isNotBlank(brandArr[1])) {	//中文品牌
				nameRep.append(brandArr[1] + " ");
			}
			if(StringUtil.isNotBlank(item.getSexStyle())) {		//男女款
				nameRep.append(item.getSexStyle() + " ");
			}
			System.out.println(nameRep.toString());
			//重新设置商品名称
			//item.setName(itemName.replace(nameRep.toString(), ""));
			item.setName(nameRep.toString());
			
			//item.setSaleOnChannels(item.generateSaleOnChannels());
			
			result.setData(item);
			return result.buildIsSuccess(true);
		} else {
			return result.buildIsSuccess(false).buildMsg("没有Item id");
		}
	}

	/**
	@RequestMapping("/queryItems")
	@ResponseBody
	public Object queryItems(ItemQueryVO itemQueryVO) {
		//logger.info("queryItems item start");
		JsonPageResult<List<ItemDO>> result = iItemService.queryItems(itemQueryVO);
		return result.buildIsSuccess(true);
	}
	**/
	/**
	 * 商品批量上架
	 * @param itemIdStrs 要上架的商品ID集合 ，分隔符   123,222,132 
	 * @return
	 */
	@RequestMapping("/itemsListing")
	@ResponseBody
	public Object itemsListing(String itemIdStrs) {
		//logger.info("itemsListing start");
		JsonResult<String> result = new JsonResult<>();
		//查询出商品，查看商品状态，新档或者下架的商品可以上架
		if(itemIdStrs!=null){
		String [] items = itemIdStrs.split(",");
		List<Long>  itemIds = new ArrayList<>();
			if(items!=null&&items.length>0){
			
				for (int i = 0;i<items.length;i++) {
					Long one = Long.valueOf(items[i]);
					itemIds.add(one);
				}
				List<ItemDO> itemList = iItemService.queryItems(itemIds);
				//获取有赞的num_iid;
				
				//调取有赞批量上架接口
				
				
			}else{
				return result.buildIsSuccess(false);
			}
		}else{
			return result.buildIsSuccess(false);
		}
//		return result.buildIsSuccess(true);
		return result;
	}
	
	
	/**
	 * 商品批量发布到有赞
	 * @return
	 */
	@RequestMapping("/itemsPush")
	@ResponseBody
	public Object itemsPush(String itemIdStrs) {
		//logger.info("itemsPush start");
		JsonResult<String> result = new JsonResult<>();
		//查询出商品，查看商品状态，新档或者下架的商品可以上架
		if(itemIdStrs!=null){
		String [] items = itemIdStrs.split(",");
		List<Long>  itemIds = new ArrayList<>();
			if(items!=null&&items.length>0){
			
				for (int i = 0;i<items.length;i++) {
					Long one = Long.valueOf(items[i]);
					itemIds.add(one);
				}
				List<ItemDO> itemList = iItemService.queryItems(itemIds);
				//获取有赞的num_iid;
				
				//调取有赞批量上架接口
				
				
			}else{
				return result.buildIsSuccess(false);
			}
		}else{
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
		//logger.info("itemsPush start");
		JsonPageResult<List<ItemDTO>> result = iItemService.queryItems(itemQueryVO);
		EasyuiJsonResult<List<ItemDTO>> jsonResult = new EasyuiJsonResult<>();
		jsonResult.setTotal(result.getTotalCount());
		jsonResult.setRows(result.getData());
//		/ShiroUser shiroUser = this.getShiroUser();
		//Set<String> roles = shiroUser.getRoles();
	//	if(roles.contains("cgpm")){
	//		jsonResult.setProductRoler(true);
	//	}
		return jsonResult.buildIsSuccess(true);
	}
	
	/**
	 * 清除虚拟库存
	 * @param itemCode
	 * @return
	 */
	@RequestMapping("/updateVirtualInvByItemId")
	@ResponseBody
	public Object updateVirtualInvByItemId(String itemCode){
		//logger.info("updateVirtualInvByItemId start");
		JsonResult<ItemDO> result = new JsonResult<>();
		if(itemCode!=null){
			inventoryService.updateVirtualInvByItemCode(itemCode);
		}		
		return result.buildIsSuccess(true);
	}
	
	
	/**
	 * 单品生成二维码
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
				ItemQueryVO itemQueryVO = new ItemQueryVO();
				itemQueryVO.setId(itemId);			
				itemQueryVO.setDimensionCodePic(picUrl);
				iItemService.updateItemById(itemQueryVO);
			}
		}
		return result.buildIsSuccess(true);
	}
	
	
	/**
	 * 新增商品同时生成二维码
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
				ItemQueryVO itemQueryVO = new ItemQueryVO();
				itemQueryVO.setId(itemId);			
				itemQueryVO.setDimensionCodePic(picUrl);
				iItemService.updateItemById(itemQueryVO);
			}
		}
		
	}
	
	/**
	 * 查询买手列表
	 * @return
	 */
	@RequestMapping("/queryAllItaliaBuyer")
	@ResponseBody
	public Object queryAllItaliaBuyer() {
		//logger.info("voidDimensionCodeUtil start");
		//EntityWrapper<BuyerDO> entityWrapper = new EntityWrapper<BuyerDO>();
		//entityWrapper.orderBy("gmt_create");
        //Integer powerCode = 0;
		//entityWrapper.where("power_code > {0}",powerCode);
		List<BuyerDO> buyers = buyerService.queryAllBuyers();
		return JsonResult.buildSuccess(buyers);
	}
	
	
	
}
