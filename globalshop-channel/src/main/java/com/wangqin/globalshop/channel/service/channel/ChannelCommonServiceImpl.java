package com.wangqin.globalshop.channel.service.channel;

import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ChannelAccountDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountSo;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.channelapi.service.ChannelCommonService;
import com.wangqin.globalshop.common.utils.EasyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by 777 on 2018/6/7
 */
@Service
public class ChannelCommonServiceImpl implements ChannelCommonService {


	@Autowired
	private IChannelAccountService channelAccountService;

	protected Logger logger = LogManager.getLogger(getClass());



	/**
	 * 上新接口：暂时只支持有赞
	 * @param itemId
	 */
	@Override
    public void createItem(String companyNo, Long itemId){
		if(EasyUtil.isStringEmpty(companyNo) || itemId == null || itemId < 0){
			throw  new ErpCommonException("companyNo或itemId为空，"+companyNo+";"+itemId);
		}
		List<ChannelAccountDO> channelAccountList =channelAccountService.searchCAListByComNoChType(companyNo,ChannelType.YouZan);
		if(EasyUtil.isListEmpty(channelAccountList)){
			throw  new ErpCommonException("channel_account,companyNo: "+companyNo+" 无账号存在");
		}
		for (ChannelAccountDO channelAccount : channelAccountList) {
			try {
				ChannelFactory.getChannel(channelAccount).createItem(itemId);
			}catch (Exception e){
				logger.error("",e);
			}
		}
	}





	/**
	 * 发货：同步运单号至各个平台
	 * @param mallSubOrderList
	 * @param shippingOrder
	 */
	@Override
    public void syncLogistics2Channel(List<MallSubOrderDO> mallSubOrderList, ShippingOrderDO shippingOrder){
		if(EasyUtil.isListEmpty(mallSubOrderList)){
             throw new ErpCommonException("mallSubOrderList为空");
		}
		//按照订单分配
		Map<String,List<MallSubOrderDO>> shopCodeSubOrderListMap = new HashMap<>();
		for (MallSubOrderDO mallSubOrderDO : mallSubOrderList) {
			if(EasyUtil.isStringEmpty(mallSubOrderDO.getShopCode())){
				throw new ErpCommonException("子订单号，sub_Order_no: "+mallSubOrderDO.getSubOrderNo()+" shopCode不存在");
			}
			if(shopCodeSubOrderListMap.get(mallSubOrderDO.getShopCode()) == null){
				List<MallSubOrderDO> mallSubOrderDOS = new ArrayList<>();
				mallSubOrderDOS.add(mallSubOrderDO);
				shopCodeSubOrderListMap.put(mallSubOrderDO.getShopCode(),mallSubOrderDOS);
			}else {
				shopCodeSubOrderListMap.get(mallSubOrderDO.getShopCode()).add(mallSubOrderDO);
			}
		}
		ChannelAccountSo so = new ChannelAccountSo();
		for(String shopCode : shopCodeSubOrderListMap.keySet()){
			so.setShopCode(shopCode);
			ChannelAccountDO channelAccount =channelAccountService.queryPo(so);
			if(channelAccount == null){
				throw  new ErpCommonException("channel_account,shopCode: "+shopCode+" 不存在");
			}
			try {
				ChannelFactory.getChannel(channelAccount).syncLogisticsOnlineConfirm(shopCodeSubOrderListMap.get(shopCode),shippingOrder);
			}catch (Exception e){
				logger.error("",e);
			}
		}

	}
}
