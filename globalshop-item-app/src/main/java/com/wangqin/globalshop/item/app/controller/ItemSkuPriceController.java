package com.wangqin.globalshop.item.app.controller;

import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dataVo.InventoryAddVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.biz1.app.bean.dto.ISkuDTO;
import com.wangqin.globalshop.biz1.app.bean.dto.SkuChannelPriceDTO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemSkuScaleDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.ItemSkuScaleMapperExt;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.common.utils.excel.ExcelHelper;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.item.app.service.IItemService;
import com.wangqin.globalshop.item.app.service.IItemSkuScaleService;
import com.wangqin.globalshop.item.app.service.IItemSkuService;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品SKU处理器
 * @author zhulu
 *
 */
@Controller
@Authenticated
public class ItemSkuPriceController {

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
	 * sku多渠道价格展示
	 * @param itemSkuQueryVO
	 * @return
	 */
	@RequestMapping("/itemSku/queryItemSkuPriceList")
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
	 * 设置所有SKU的渠道价格
	 * @param itemSkuQueryVO
	 * @return
	 */
	@PostMapping("/itemSku/saveItemSkuPriceList")
	@ResponseBody
	public Object saveAllItemSkuInOneChannelPrice(ItemSkuQueryVO itemSkuQueryVO) {
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
	 * 保存一个SKU的多渠道价格
	 * @param itemSkuQueryVO
	 * @return
	 */
	@PostMapping("/itemSku/saveOneItemSkuMultiPrice")
	@ResponseBody
	public Object saveOneItemSkuMultiPrice(ItemSkuQueryVO itemSkuQueryVO) {
		JsonPageResult<List<SkuChannelPriceDTO>> result = new JsonPageResult<>();
		if(!loginCheck()) {
			return result.buildIsSuccess(false).buildMsg("请登录");
		}
		itemSkuQueryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
		result = iItemSkuService.queryItemSkuPrices(itemSkuQueryVO);
		result.buildIsSuccess(true);
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
