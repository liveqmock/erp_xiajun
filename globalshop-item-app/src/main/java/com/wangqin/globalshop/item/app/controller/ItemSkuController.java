package com.wangqin.globalshop.item.app.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.biz1.app.bean.dto.ISkuDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.common.utils.PriceUtil;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.item.app.service.IItemSkuScaleService;
import com.wangqin.globalshop.item.app.service.IItemSkuService;

/**
 * itemsku
 * @author xiajun
 *
 */
@Controller
@RequestMapping("/itemSku")
@Authenticated
public class ItemSkuController  {

	//旧的service
	@Autowired
	private IItemSkuService iItemSkuService;
	@Autowired
	private IItemSkuScaleService scaleService;	
	@Autowired
	private IItemService itemService;

	@Autowired
	private InventoryService inventoryService;
	

	//新的service
//	@Autowired
//	private ItemSkuFeignService iItemSkuService;
//	
//	@Autowired
//	private ItemSkuScaleFeignService scaleService;
//	
//	@Autowired
//	private IItemService itemService;


	
	/**
	 * 商品管理->sku管理->修改->确认
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
		String user = AppUtil.getLoginUserCompanyNo();
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
		
		//更新尺寸（先删除后插入）
		if (IsEmptyUtil.isStringNotEmpty(itemSku.getColor())) {
			scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "颜色");
			ItemSkuScaleDO colorScale = new ItemSkuScaleDO();
			colorScale.setCompanyNo(companyNo);
			colorScale.setCreator(user);
			colorScale.setModifier(user);
			colorScale.setItemCode(sku.getItemCode());
			colorScale.setScaleCode(CodeGenUtil.getScaleCode());
			colorScale.setScaleValue(itemSku.getColor());
			colorScale.setScaleName("颜色");
			colorScale.setSkuCode(skuCode);
			scaleService.insertSelective(colorScale);
		}
		if (IsEmptyUtil.isStringNotEmpty(itemSku.getScale())) {
			scaleService.deleteItemSkuScaleBySkuCodeAndScaleName(skuCode, "尺寸");
			ItemSkuScaleDO scale = new ItemSkuScaleDO();
			scale.setCompanyNo(companyNo);
			scale.setCreator(user);
			scale.setModifier(user);
			scale.setItemCode(sku.getItemCode());
			scale.setScaleCode(CodeGenUtil.getScaleCode());
			scale.setScaleValue(itemSku.getScale());
			scale.setScaleName("尺寸");
			scale.setSkuCode(skuCode);
			scaleService.insertSelective(scale);
		}	
	
		return result.buildIsSuccess(true).buildMsg("更新成功");
	}
	

	/**
	 * 商品管理->sku管理->修改
	 * @param id
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	@Transactional(rollbackFor = ErpCommonException.class)
	public Object query(String skuCode) {
		JsonResult<ISkuDTO> result = new JsonResult<>();
		ISkuDTO itemSku = iItemSkuService.queryItemSkuBySkuCode(skuCode);
		List<ItemSkuScaleDO> skuScaleList = scaleService.selectScaleNameValueBySkuCode(itemSku.getSkuCode());
		if (!EasyUtil.isListEmpty(skuScaleList)) {
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
	 * 商品管理->sku管理
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
	 * 商品管理->sku管理->删除
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
