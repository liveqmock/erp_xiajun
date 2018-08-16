package com.wangqin.globalshop.channel.controller.youzan;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.aop.annotation.Authenticated;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelShopDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.JdShopOauthDO;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.enums.ItemStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.JsonResult;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.channel.ChannelFactory;
import com.wangqin.globalshop.channel.service.channel.ChannelShopService;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.channel.service.item.IItemService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopService;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.channelapi.service.ChannelCommonService;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.common.utils.czh.Util;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 
 * 商品批量上新或更新到有赞
 * 新增商品，上新到有赞，主图，创建字图
 * 更新商品，更新到有赞，
 * @author zhulu
 *
 */
@Controller
@RequestMapping("/youzanSyn")
@Authenticated
public class YouzanSynController extends BaseController {
	
	@Autowired
	private IItemService itemService;


	@Autowired
	private ChannelShopService shopService;

	@Autowired
	private ChannelCommonService channelCommonService;



	/**
	 * 批量同步商品信息到有赞，只能同步新建和上架状态的商品
	 * @param itemIds
	 * @return
	 */
	@RequestMapping("/batchSynItemYouzan")
	@ResponseBody
	@Authenticated
	public Object batchSynItemYouzan(String itemIds) {
		JsonResult<String> result = new JsonResult<>();
		StringBuilder sb = new StringBuilder();
		if(!Util.isEmpty(itemIds)){
			String s = itemIds.replace("&quot;", "\"");
			List<Long> idList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>(){});
			ChannelShopDO shopSo = new ChannelShopDO();
			shopSo.setCompanyNo(AppUtil.getLoginUserCompanyNo());
			shopSo.setChannelNo(ChannelType.YouZan.getValue()+"");
			shopSo.setOpen(true);
			List<ChannelShopDO> channelShopList = shopService.searchShopList(shopSo);

			if(!EasyUtil.isListEmpty(channelShopList)) {
			for(ChannelShopDO shop : channelShopList){
				for(Long id:idList){
					try{
						if(id!=null&&id!=0){
							channelCommonService.createItem(shop.getShopCode(),id);
						}
					}catch(ErpCommonException e){
						sb.append(e.getErrorMsg()+",");
					}catch (Exception e) {
						logger.error("同步异常，商品ID：" + id, e);
						sb.append(id +"未知异常"+",");
					}
				}
			}
			}
		}else{
			return result.buildIsSuccess(false).buildMsg("没有商品");
		}
		String errorMsg = sb.toString();
		if(Util.isEmpty(errorMsg)){
			return result.buildIsSuccess(true);
		}else{
			return result.buildIsSuccess(false).buildMsg(errorMsg);
		}
	}
	/**
	 * 批量上架
	 * @param itemIds 商品IDs
	 * @return
	 */
	@RequestMapping("/batchListingYouzan")
	@ResponseBody
	public Object batchListingYouzan(@RequestParam("itemIds")String itemIds) {
		JsonResult<String> result = new JsonResult<>();
		StringBuilder sb = new StringBuilder();
		if (StringUtil.isNotEmpty(itemIds)) {
			String s = itemIds.replace("&quot;", "\"");
			List<Long> idList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
			});
			if (CollectionUtils.isNotEmpty(idList)) {
				List<ItemDO> items = itemService.selectBatchIds(idList);

				ChannelShopDO shopSo = new ChannelShopDO();
				shopSo.setCompanyNo(AppUtil.getLoginUserCompanyNo());
				shopSo.setChannelNo(ChannelType.YouZan.getValue()+"");
				List<ChannelShopDO> channelShopList = shopService.searchShopList(shopSo);
				
				for (ItemDO item : items) {
					//小程序可售则上架
					if(1 == item.getWxisSale()) {
						item.setStatus(1);						
					}
				}

				for (ItemDO item : items) {					
					// 更新商品状态
					item.setStatus(ItemStatus.LISTING.getCode());
					item.setGmtModify(new Date());
					ItemVo itemVo = new ItemVo();
					BeanUtils.copies(item,itemVo);
					if(!EasyUtil.isListEmpty(channelShopList)) {
						for (ChannelShopDO shop : channelShopList){
							try {
								channelCommonService.syncListingItem(shop.getShopCode(),item.getId());
							} catch (Exception e) {
								e.printStackTrace();
								sb.append("名称:" + item.getItemName() + "商品代码:" + item.getItemCode()  + "店铺【" + shop.getShopCode() + "】未成功上架：" +"未知错误;" + e.toString());
							}
						}
					}
					
				}
				itemService.updateBatchById(items);
			} else {
				return result.buildIsSuccess(false).buildMsg("没有商品");
			}
		} else {
			return result.buildIsSuccess(false).buildMsg("没有商品");
		}
		String errorMsg = sb.toString();
		if (StringUtils.isEmpty(errorMsg)) {
			return result.buildIsSuccess(true);
		} else {
			return result.buildIsSuccess(false).buildMsg(errorMsg);
		}
	}
	/**
	 * 批量下架
	 * @param itemIds 商品IDs
	 * @return
	 */
	@RequestMapping("/batchDelistingYouzan")
	@ResponseBody
	public Object batchDelistingYouzan(String itemIds) {
		JsonResult<String> result = new JsonResult<>();
		StringBuilder sb = new StringBuilder();
		if (StringUtil.isNotEmpty(itemIds)) {
			String s = itemIds.replace("&quot;", "\"");
			List<Long> idList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
			});

			ChannelShopDO shopSo = new ChannelShopDO();
			shopSo.setCompanyNo(AppUtil.getLoginUserCompanyNo());
			shopSo.setChannelNo(ChannelType.YouZan.getValue()+"");
			List<ChannelShopDO> channelShopList = shopService.searchShopList(shopSo);

			if (CollectionUtils.isNotEmpty(idList)) {
				List<ItemDO> items = itemService.selectBatchIds(idList);

				for (ItemDO item : items) {
					//小程序可售则下架
					if(1 == item.getWxisSale()) {
						item.setStatus(1);						
					}
				}

				for (ItemDO item : items) {
					//更新商品状态
					item.setStatus(ItemStatus.DELISTING.getCode());
					item.setGmtModify(new Date());
					ItemVo itemVo = new ItemVo();
					BeanUtils.copies(item,itemVo);
					if(!EasyUtil.isListEmpty(channelShopList)) {
						for (ChannelShopDO shop : channelShopList){
							try {
								// 有赞
								channelCommonService.syncDelistingItem(shop.getShopCode(),item.getId());
							} catch (Exception e) {
								sb.append("名称:" + item.getItemName() + "商品代码:" + item.getItemCode() + "在店铺名：" + shop.getShopCode() + "未成功下架："  + ":" + e.toString());
							}
						}
					}					
				}
				itemService.updateBatchById(items);
			} else {
				return result.buildIsSuccess(false).buildMsg("没有商品");
			}
		} else {
			return result.buildIsSuccess(false).buildMsg("没有商品");
		}
		String errorMsg = sb.toString();
		if (StringUtils.isEmpty(errorMsg)) {
			return result.buildIsSuccess(true);
		} else {
			return result.buildIsSuccess(false).buildMsg(errorMsg);
		}
	}


}
