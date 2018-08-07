package com.wangqin.globalshop.item.app.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.wangqin.globalshop.biz1.app.bean.dto.SkuChannelPriceDTO;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuScaleMapperExt;
import com.wangqin.globalshop.biz1.app.bean.dto.ISkuDTO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.InventoryAddVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.common.utils.PicModel;
import com.wangqin.globalshop.common.utils.PriceUtil;
import com.wangqin.globalshop.common.utils.excel.ExcelHelper;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.item.app.service.IItemSkuScaleService;
import com.wangqin.globalshop.item.app.service.IItemSkuService;

/**
 * 商品SKU处理器
 * @author zhulu
 *
 */
@Controller
@RequestMapping("/itemSku")
@Authenticated
public class ItemSkuController  {

	@Autowired
	private IItemSkuService iItemSkuService;
	
	@Autowired
	private IItemService itemService;

	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private IItemSkuScaleService scaleService;

	@Autowired
	private ItemSkuScaleMapperExt itemSkuScaleMapperExt;
	
	/**
	 * 更新sku
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object update(ItemSkuQueryVO itemSku) {
		JsonResult<String> result = new JsonResult<>();
		if(!loginCheck()) {
			return result.buildIsSuccess(false).buildMsg("请先登录");
		}
		//检测upc是否重复,一个公司旗下的upc不能重复
		String companyNo = AppUtil.getLoginUserCompanyNo();
		List<String> skuCodeList = iItemSkuService.querySkuCodeListByUpc(companyNo, itemSku.getUpc());
		if(!EasyUtil.isListEmpty(skuCodeList)) {//查到数据库里面有这个upc
			String currentSkuCode = iItemSkuService.querySkuCodeById(itemSku.getId());
			for(String skuCode:skuCodeList) {
				if(!skuCode.equals(currentSkuCode)) {//而且这个upc不是当前正在更新的sku的upc
					return result.buildIsSuccess(false).buildMsg("更新失败，更新的upc不能和已有的upc重复");
				}
			}			
		} 
		itemSku.setModifier(AppUtil.getLoginUserId());
		//更新虚拟库存
		String skuCode = iItemSkuService.querySkuCodeById(itemSku.getId());
		try {
			inventoryService.updateVirtualInv(skuCode, itemSku.getVirtualInv(), AppUtil.getLoginUserCompanyNo());	
		} catch (Exception e) {
			return result.buildIsSuccess(false).buildMsg("您填入的虚拟库存数据错误");
		}
		//更新尺寸
		if (IsEmptyUtil.isStringNotEmpty(itemSku.getColor())) {
			itemSkuScaleMapperExt.updateSkuScaleBySkuCodeAndScaleName(skuCode, "颜色", itemSku.getColor());
		}
		if (IsEmptyUtil.isStringNotEmpty(itemSku.getScale())) {
			itemSkuScaleMapperExt.updateSkuScaleBySkuCodeAndScaleName(skuCode, "尺寸", itemSku.getScale());
		}		
		//更新sku
		iItemSkuService.updateById(itemSku);
		
		//更新一下item(商品表)的price_range(价格区间)字段
		List<Double> skuPriceList = iItemSkuService.querySalePriceListBySkuCode(skuCode);
		ISkuDTO sku = iItemSkuService.queryItemSkuBySkuCode(skuCode);		
		ItemDO itemDO = itemService.queryItemDOByItemCode(sku.getItemCode());
		//更新价格区间
		String newPriceRange = PriceUtil.calNewPriceRange(skuPriceList);
		ItemDO updateItem = new ItemDO();
		updateItem.setId(itemDO.getId());
		updateItem.setModifier(AppUtil.getLoginUserId());
		updateItem.setPriceRange(newPriceRange);
		itemService.updateByIdSelective(updateItem);
		
		return result.buildIsSuccess(true).buildMsg("更新成功");
	}

	/**
	 * 根据sku_code获取sku
	 * @param id
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object query(String skuCode) {
		JsonResult<ISkuDTO> result = new JsonResult<>();
		//if haven't item id ,add item
		if(null != skuCode) {
			ISkuDTO itemSku = iItemSkuService.queryItemSkuBySkuCode(skuCode);
			List<ItemSkuScaleDO> skuScaleList = itemSkuScaleMapperExt.selectScaleNameValueBySkuCode(itemSku.getSkuCode());
        	if(!EasyUtil.isListEmpty(skuScaleList)) {
        		for(ItemSkuScaleDO scale:skuScaleList) {
        			if("颜色".equals(scale.getScaleName())) {
        				itemSku.setColor(scale.getScaleValue());
        			}
        			if("尺寸".equals(scale.getScaleName())) {
        				itemSku.setScale(scale.getScaleValue());
        			}
        		}
        	}
			result.setData(itemSku);
		}else{
			result.buildIsSuccess(false).buildMsg("没有skuCode");
		}
		return result.buildIsSuccess(true);
	}

	@PostMapping("/queryBySkuCodeOrUpc")
	@ResponseBody
	public Object queryBySkuCodeOrUpc(String code) {
		JsonResult<List<ItemSkuDO>> result = new JsonResult<>();
		ItemSkuDO item = iItemSkuService.queryBySkuCodeOrUpcAndCompanyNo(code,AppUtil.getLoginUserCompanyNo());

		List<ItemSkuDO> skuDOS = new ArrayList<>();
		skuDOS.add(item);

		return result.buildData(skuDOS).buildIsSuccess(true);

	}

	/**
	 * sku列表展示
	 * @param itemSkuQueryVO
	 * @return
	 */
	@RequestMapping("/queryItemSkuList")
	@ResponseBody
	public Object queryItemSkus(ItemSkuQueryVO itemSkuQueryVO) {
		JsonPageResult<List<ISkuDTO>> result = new JsonPageResult<>();
		if(!loginCheck()) {
			return result.buildIsSuccess(false).buildMsg("请登录");
		}
		itemSkuQueryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
		result = iItemSkuService.queryItemSkus(itemSkuQueryVO);
		result.buildIsSuccess(true);
		return result;
	}


	/**
	 * sku多渠道价格展示
	 * @param itemSkuQueryVO
	 * @return
	 */
	@RequestMapping("/queryItemSkuPriceList")
	@ResponseBody
	public Object queryItemSkuPriceList(ItemSkuQueryVO itemSkuQueryVO) {
		JsonPageResult<List<SkuChannelPriceDTO>> result = new JsonPageResult<>();
		if(!loginCheck()) {
			return result.buildIsSuccess(false).buildMsg("请登录");
		}
		itemSkuQueryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
//		result = iItemSkuService.queryItemSkus(itemSkuQueryVO);
		result = iItemSkuService.queryItemSkuPrices(itemSkuQueryVO);
		result.buildIsSuccess(true);
		return result;
	}
	/**
	 * 订单管理中要修改订单时添加商品的接口
	 * 新添加
	 */
	@RequestMapping("/queryItemList")
	@ResponseBody
	public Object queryItemList(ItemSkuQueryVO itemSkuQueryVO) {
		JsonPageResult<List<ISkuDTO>> result = new JsonPageResult<>();
		if(!loginCheck()) {
			return result.buildIsSuccess(false).buildMsg("请登录");
		}
		itemSkuQueryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
		result = iItemSkuService.queryItemSkus(itemSkuQueryVO);
		result.buildIsSuccess(true);
		return result;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	//@Transactional(rollbackFor = BizCommonException.class)
	public Object delete(Long id) {
		JsonResult<String> result = new JsonResult<>();
		//如果这个sku对应的商品只有这一个sku，禁止删除该sku
		if(1 >= iItemSkuService.querySkuNumberBySkuId(id)) {
			return result.buildIsSuccess(false).buildMsg("商品只有这一个sku,暂时无法删除");
		}
		String skuCode = iItemSkuService.querySkuCodeById(id);
		if(IsEmptyUtil.isStringNotEmpty(skuCode)) {
			//删除虚拟库存，用更新虚拟库存为0代替
			try {
				inventoryService.updateVirtualInv(skuCode, 0L, AppUtil.getLoginUserCompanyNo());
			} catch (Exception e) {
				return result.buildIsSuccess(false).buildMsg("库存被占用,不可直接删除！");
			}		
			//删除item_sku_scale
			scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "颜色");
			scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "尺寸");
		}		
		iItemSkuService.deleteById(id);
		result.buildIsSuccess(true);
		return result;
	}
	


	/**
	 * SKU 锁定虚拟库存，用在提前修改可售库存，以便同步到第三方平台(有赞)
	 *
	 * @param
	 * @return
	 */
	@RequestMapping("/lockedVirtualInv")
	@ResponseBody
	public Object lockedVirtualInv(InventoryAddVO inventory) {
		JsonResult<ItemSkuDO> result = new JsonResult<>();
//		if(itemSku.getId()==null) {
//			return result.buildIsSuccess(false).buildMsg("SKU ID错误");
//		} else if(itemSku.getItemCode()==null) {
//			return result.buildIsSuccess(false).buildMsg("商品编码错误");
//		}
		//InventoryAddVO inv = inventoryService.queryInvBySkuCode(inventory.getSkuCode());
		//if(inventory == null) {
		//	return result.buildIsSuccess(false).buildMsg("未找到此sku的库存");
		//}

//		int lockedNum = inventory.getLockedVirtualInv() + itemSku.getLockedVirtualInv();
//		if(lockedNum<0 || (lockedNum>inventory.getVirtualInv() && lockedNum>inventory.getTotalAvailableInv())) {
//			return result.buildIsSuccess(false).buildMsg("锁定数量异常");
//		}
		//inv.setLockedVirtualInv(inventory.getLockedVirtualInv());
		//inventoryService.lockVirtualInv(inv);

		/**
		//同步到有赞
		Item item = iItemService.selectById(itemSku.getItemId());
		// 同步到有赞并上架
		if (item.getIsSale() != null && item.getIsSale() == 1) {
			if (item.getSaleOnYouzan() == 1) {
				try {
					ShiroUser user = ShiroUtil.getShiroUser();
					IChannelService channelService = ChannelFactory.getChannel(user.getCompanyId(), ChannelType.YouZan);
					if (item.getSaleOnYouzan() == 1 && item.getIsSale() != null && item.getIsSale() == 1) { // 同步到有赞并上架
						channelService.syncItem(item.getId());
					} else { // 下架
						channelService.syncDelistingItem(item.getId());
					}
				} catch (exception e) {
					logger.error("SKU锁定虚拟库存时同步到有赞：", e);
				}
			}
		}
**/
		return result.buildIsSuccess(true);
	}

	/**
	 * 导出excel
	 * @param id
	 */
	@RequestMapping("/queryItemSkusForExcel")
    public ResponseEntity<byte[]> queryItemSkusForExcel() throws Exception {
    	List<List<Object>> rowDatas = new ArrayList<>();
    	List<ItemSkuDO> itemSkuList = iItemSkuService.queryItemSkusForExcel();
    	if(itemSkuList != null) {
    		for (ItemSkuDO itemSku : itemSkuList) {
    			List<Object> list = new ArrayList<>();
    			//sku图片
    			String skuPic = itemSku.getSkuPic();
    			if(StringUtil.isNotBlank(skuPic)) {
    				PicModel pm = HaiJsonUtils.toBean(skuPic, PicModel.class);
        			String imgSrc = pm.getPicList().get(0).getUrl();
        			String imgType = imgSrc.substring(imgSrc.lastIndexOf(".") + 1).toUpperCase();
        			if(imgSrc.contains("aliyuncs.com")) {
        				imgSrc += "?x-oss-process=image/resize,m_lfit,h_100,w_100";
        			} else {
        				imgSrc = imgSrc.replaceAll("Resource", "Thumbnail");
        			}
        			//System.out.println(imgSrc);
        			URL url = new URL(imgSrc);
        			BufferedImage image = ImageIO.read(url);
        			ByteArrayOutputStream output = new ByteArrayOutputStream();
        	        ImageIO.write(image, imgType, output);
        	        output.flush();
        	        list.add(output.toByteArray());
        	        output.close();
    			} else {
    				list.add(null);
    			}
    	        list.add(itemSku.getItemName());	//商品名称
    	        list.add(itemSku.getBrandName());       //商品品牌

    	        list.add(itemSku.getScale());		//尺码
    	        list.add(itemSku.getSalePrice());	//销售价格
    			rowDatas.add(list);
    		}
    	}
    	ExcelHelper excelHelper = new ExcelHelper();
    	String[] columnTitles = new String[]{"商品图片", "商品名称", "商品品牌", "颜色", "尺码", "销售价格"};
    	Integer[] columnWidth = new Integer[]{10, 30, 10, 10, 20, 20};
    	excelHelper.setItemToSheet("Item", columnTitles, rowDatas, columnWidth);
    	//excelHelper.writeToFile("/Users/liuyang/Work/test.xls");

    	ResponseEntity<byte[]> filebyte = null;
    	ByteArrayOutputStream  out = excelHelper.writeToByteArrayOutputStream();
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    	String fileName = "商品.xlsx";
        headers.setContentDispositionFormData("attachment", new String(fileName.getBytes("utf-8"), "ISO8859-1"));

        filebyte = new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        out.close();
        excelHelper.close();
        return filebyte;
    }
    
    
    /**
	 * 根据upc获取商品信息(取该upc对应的第一个item_sku记录，返回item_sku+inventory+virtualInv)
	 * @param upc
	 * @return
	 * @author XiaJun
	 */
    @RequestMapping("/queryItemSkuByUpc")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object queryItemSkuByUpc(String upc) {
    	JsonResult<ItemSkuDO> result = new JsonResult<>();
    	List<ItemSkuDO> itemSkuList = iItemSkuService.queryItemSkusByUpc(upc);
    	if(itemSkuList.size() == 0) {
        	result.buildIsSuccess(false);
        	result.buildMsg("没有该upc对应的商品");
    	} else {
    		ItemSkuDO itemSku = iItemSkuService.queryItemSkusByUpc(upc).get(0);
        	result.setData(itemSku);
        	result.buildIsSuccess(true);
        	result.buildMsg("查找成功");
    	}
    	return result;
	}
    
    
    //查询规格列表，不用的请求
	@RequestMapping("/scaleTypeList")
	@ResponseBody
	public Object scaleTypeList() {
		JsonResult<List<Scale>> result = new JsonResult<List<Scale>>();
		ScaleType scaleType1 = new ScaleType();
		ScaleType scaleType2 = new ScaleType();
		scaleType1.setId(348L);
		scaleType1.setName("3");
		scaleType1.setSeq(0);
		scaleType1.setTypeId(348L);
		scaleType2.setId(349L);
		scaleType2.setName("3.5");
		scaleType2.setSeq(1);
		scaleType2.setTypeId(348L);
		List<ScaleType> scaleList = new ArrayList<ScaleType>();
		scaleList.add(scaleType1);
		scaleList.add(scaleType2);
		Scale scale = new Scale();
		scale.setId(348L);
		scale.setType("成人鞋类(美码)");
		scale.setScaleList(scaleList);
		List<Scale> scaleCollection = new ArrayList<Scale>();
		scaleCollection.add(scale);
		return result.buildData(scaleCollection).buildIsSuccess(true).buildMsg("不用的请求");
	}



	@RequestMapping("/saleable")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object getSaleAble() {
		JsonResult<List<ItemSkuDO>> result = new JsonResult<>();
		List<ItemSkuDO> skuList = iItemSkuService.querySaleableSkus();
		if(0 == skuList.size()) {
			result.buildIsSuccess(false).buildMsg("无可售商品");
		}
		result.buildData(skuList).buildIsSuccess(true);
		return result;
	}
	
	private class ScaleType {
		private Long id;
		private String name;
		private Long typeId;
		private Integer seq;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Long getTypeId() {
			return typeId;
		}
		public void setTypeId(Long typeId) {
			this.typeId = typeId;
		}
		public Integer getSeq() {
			return seq;
		}
		public void setSeq(Integer seq) {
			this.seq = seq;
		}
		
	}
	
	private class Scale {
		private Long id;
		private String type;
		private List<ScaleType> scaleList;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public List<ScaleType> getScaleList() {
			return scaleList;
		}
		public void setScaleList(List<ScaleType> scaleList) {
			this.scaleList = scaleList;
		}		
	}
	
	/**
     * 工具类
     * 用户登录判断
     * @param itemCode
     * @return
     */
    public Boolean loginCheck() {
    	if(IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserCompanyNo()) || IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserId())) {
         	return false;
        }
    	return true;
    }
}
