package com.wangqin.globalshop.item.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ItemSkuQueryVO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.bean.dataVo.SkuChannelPriceEditVO;
import com.wangqin.globalshop.biz1.app.bean.dto.SkuChannelPriceDTO;
import com.wangqin.globalshop.common.base.BaseDto;
import com.wangqin.globalshop.common.utils.AppUtil;
import com.wangqin.globalshop.common.utils.IsEmptyUtil;
import com.wangqin.globalshop.item.app.service.IItemSkuService;

/**
 * 渠道价格
 * @author 
 *
 */
@Controller
@Authenticated
public class ItemSkuPriceController {

	@Autowired
	private IItemSkuService iItemSkuService;
	



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
		//itemSkuQueryVO.setCompanyNo("KZQA3rqCwV");
		result = iItemSkuService.queryItemSkuPrices(itemSkuQueryVO);
		result.buildIsSuccess(true);
		return result;
	}


	/**
	 * 批量设置SKU的多渠道价格
	 * @param
	 * @return
	 */
	@PostMapping("/itemSku/saveItemSkuPriceList")
	@ResponseBody
	public Object saveAllItemSkuInOneChannelPrice(String skuChannelPriceEditVOList) {
		JsonPageResult<List<SkuChannelPriceDTO>> result = new JsonPageResult<>();
		List<SkuChannelPriceEditVO> jsonList = BaseDto.fromJson(skuChannelPriceEditVOList, new TypeReference<List<SkuChannelPriceEditVO>>(){});
		if(!loginCheck()) {
			return result.buildIsSuccess(false).buildMsg("请登录");
		}
		 iItemSkuService.saveItemSkuMultiPriceList(jsonList);
		result.buildIsSuccess(true).buildMsg("保存成功");
		return result;
	}

    /**
     * 保存一个SKU的多渠道价格
     * @param
     * @return
     */
    @PostMapping("/itemSku/saveOneItemSkuMultiPrice")
    @ResponseBody
    public Object saveOneItemSkuMultiPrice(String skuChannelPriceEditVO) {
        JsonPageResult<List<SkuChannelPriceDTO>> result = new JsonPageResult<>();
		SkuChannelPriceEditVO json = BaseDto.fromJson(skuChannelPriceEditVO, SkuChannelPriceEditVO.class);
        if(!loginCheck()) {
            return result.buildIsSuccess(false).buildMsg("请登录");
        }
        iItemSkuService.saveOneItemSkuMultiPrice(json);
        result.buildIsSuccess(true).buildMsg("保存成功");
        return result;
    }

    /**
     * 设置所有SKU的某渠道上价格
     * @param
     * @return
     */
    @PostMapping("/itemSku/saveAllItemSkuInOneChannelPrice")
    @ResponseBody
    public Object saveAllItemSkuInOneChannelPrice(String discountPercent, String channelNo) {
        JsonPageResult<List<SkuChannelPriceDTO>> result = new JsonPageResult<>();
        if(!loginCheck()) {
            return result.buildIsSuccess(false).buildMsg("请登录");
        }
        iItemSkuService.saveAllItemSkuInOneChannelPrice( discountPercent,  channelNo);
        result.buildIsSuccess(true).buildMsg("保存成功");
        return result;
    }

	/**
	 * 查询某个渠道上所有商品的原始售价
	 * @param
	 * @return
	 */
	@PostMapping("/itemSku/querySkuSalePrice")
	@ResponseBody
	public Object querySkuSalePrice(String channelNo, Integer pageIndex, Integer pageSize) {
		JsonPageResult<List<SkuChannelPriceDTO>> result = new JsonPageResult<>();

		if(!loginCheck()) {
			return result.buildIsSuccess(false).buildMsg("请登录");
		}
		ItemSkuQueryVO itemSkuQueryVO = new ItemSkuQueryVO();
		itemSkuQueryVO.setCompanyNo(AppUtil.getLoginUserCompanyNo());
		itemSkuQueryVO.setPageIndex(pageIndex);
		itemSkuQueryVO.setPageSize(pageSize);
		result = iItemSkuService.querySkuSalePrice(channelNo,itemSkuQueryVO);
		result.buildIsSuccess(true);
		return result;
	}


	/**
     * 工具类
     * 用户登录判断
     * @param
     * @return
     */
    public Boolean loginCheck() {
    	if(IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserCompanyNo()) || IsEmptyUtil.isStringEmpty(AppUtil.getLoginUserId())) {
         	return false;
        }
    	return true;
    }
}
