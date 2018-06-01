package com.wangqin.globalshop.channel.controller.youzan;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.constants.enums.ItemStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemDO;
import com.wangqin.globalshop.biz1.app.vo.JsonResult;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.dal.dataObjectVo.ItemVo;
import com.wangqin.globalshop.channel.dal.dataSo.ChannelAccountSo;
import com.wangqin.globalshop.channel.service.channel.ChannelFactory;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.channel.service.item.IItemService;
import com.wangqin.globalshop.common.base.BaseController;
import com.wangqin.globalshop.common.utils.BeanUtils;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.ShiroUtil;

/**
 * 
 * 接收有赞商品推送接口
 * @author zhulu
 *
 */
@Controller
@RequestMapping("/youzanSyn")
public class YouzanSynController extends BaseController {
	
	@Autowired
	private IItemService itemService;

	@Autowired IChannelAccountService channelAccountService;

	/**
	 * 批量同步商品信息到有赞，只能同步新建和上架状态的商品
	 * @param itemIds
	 * @return
	 */
	@RequestMapping("/batchSynItemYouzan")
	@ResponseBody
	public Object batchSynItemYouzan(String itemIds) {
		JsonResult<String> result = new JsonResult<>();
		StringBuilder sb = new StringBuilder();
		if(StringUtils.isNotEmpty(itemIds)){
			String s = itemIds.replace("&quot;", "\"");
			List<Long> idList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>(){});

			ChannelAccountSo so = new ChannelAccountSo();
			so.setType(ChannelType.YouZan.getValue());
			so.setCompanyNo(ShiroUtil.getShiroUser().getCompanyNo());
			so.setStatus(0);
			List<ChannelAccountDO> channelAccountList = channelAccountService.queryPoList(so);
			if(channelAccountList == null || channelAccountList.size() < 1){
				return result.buildIsSuccess(false).buildMsg("未找到第三方渠道，不存在或已停用");
			}
			for(ChannelAccountDO channelAccount : channelAccountList){
				for(Long id:idList){
					try{
						if(id!=null&&id!=0){
							ChannelFactory.getChannel(channelAccount).syncItem(id);
						}
					}catch(ErpCommonException e){
						sb.append(e.getErrorMsg()+",");
					}catch (Exception e) {
						logger.error("同步异常，商品ID：" + id, e);
						sb.append(id +"未知异常"+",");
					}
				}
			}
		}else{
			return result.buildIsSuccess(false).buildMsg("没有商品");
		}
		String errorMsg = sb.toString();
		if(StringUtils.isEmpty(errorMsg)){
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
	public Object batchListingYouzan(String itemIds) {
		JsonResult<String> result = new JsonResult<>();
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotEmpty(itemIds)) {
			String s = itemIds.replace("&quot;", "\"");
			List<Long> idList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
			});
			if (CollectionUtils.isNotEmpty(idList)) {
				List<ItemDO> items = itemService.selectBatchIds(idList);

				ChannelAccountSo so = new ChannelAccountSo();
				so.setType(ChannelType.YouZan.getValue());
				so.setCompanyNo(ShiroUtil.getShiroUser().getCompanyNo());
				so.setStatus(0);
				List<ChannelAccountDO> channelAccountList = channelAccountService.queryPoList(so);

				if(channelAccountList == null || channelAccountList.size() < 1){
					return result.buildIsSuccess(false).buildMsg("未找到第三方渠道，不存在或已停用");
				}
				for (ItemDO item : items) {
						// 更新商品状态
					item.setStatus(ItemStatus.LISTING.getCode());
					item.setGmtModify(new Date());
					ItemVo itemVo = new ItemVo();
					BeanUtils.copies(item,itemVo);
					for (ChannelAccountDO channelAccount : channelAccountList){
						try {
							ChannelFactory.getChannel(channelAccount).syncListingItem(itemVo);
						} catch (Exception e) {
							e.printStackTrace();
							sb.append("名称:" + item.getItemName() + "商品代码:" + item.getItemCode()  + "店铺【" + channelAccount.getShopCode() + "】未成功上架：" +"未知错误;" + e.toString());
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
		if (StringUtils.isNotEmpty(itemIds)) {
			String s = itemIds.replace("&quot;", "\"");
			List<Long> idList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
			});
			if (CollectionUtils.isNotEmpty(idList)) {
				List<ItemDO> items = itemService.selectBatchIds(idList);

				ChannelAccountSo so = new ChannelAccountSo();
				so.setType(ChannelType.YouZan.getValue());
				so.setCompanyNo(ShiroUtil.getShiroUser().getCompanyNo());
				so.setStatus(0);
				List<ChannelAccountDO> channelAccountList = channelAccountService.queryPoList(so);

                if(channelAccountList == null || channelAccountList.size() < 1){
					sb.append("未找到第三方渠道，不存在或已停用");
				}
				for (ItemDO item : items) {
					//更新商品状态
					item.setStatus(ItemStatus.DELISTING.getCode());
					item.setGmtModify(new Date());
					ItemVo itemVo = new ItemVo();
					BeanUtils.copies(item,itemVo);
					for (ChannelAccountDO channelAccount : channelAccountList){
						try {
							// 有赞
							ChannelFactory.getChannel(channelAccount).syncDelistingItem(itemVo);
						} catch (Exception e) {
							sb.append("名称:" + item.getItemName() + "商品代码:" + item.getItemCode() + "在店铺名：" + channelAccount.getShopCode() + "未成功下架："  + ":" + e.toString());
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
