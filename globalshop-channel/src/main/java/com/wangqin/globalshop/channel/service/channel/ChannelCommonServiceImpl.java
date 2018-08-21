package com.wangqin.globalshop.channel.service.channel;

import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ChannelAccountSo;
import com.wangqin.globalshop.channel.Exception.ErpCommonException;
import com.wangqin.globalshop.channel.service.HaihuService;
import com.wangqin.globalshop.channel.service.YouzanService;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.channel.service.channelItem.IChannelListingItemService;
import com.wangqin.globalshop.channel.service.item.IItemService;
import com.wangqin.globalshop.channel.service.jingdong.JdOrderService;
import com.wangqin.globalshop.channel.service.jingdong.JdShopOauthService;
import com.wangqin.globalshop.channel.service.order.ChannelIMallOrderService;
import com.wangqin.globalshop.channel.service.order.ChannelIMallSubOrderService;
import com.wangqin.globalshop.channelapi.dal.GlobalShopItemVo;
import com.wangqin.globalshop.channelapi.dal.GlobalshopOrderVo;
import com.wangqin.globalshop.channelapi.dal.ItemVo;
import com.wangqin.globalshop.channelapi.service.ChannelCommonService;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.EasyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;

/**
 * Create by 777 on 2018/6/7
 */
@Service
public class ChannelCommonServiceImpl implements ChannelCommonService {

	protected Logger logger = LogManager.getLogger("ChannelCommonServiceImpl");

	@Autowired
	private JdShopOauthService shopOauthService;

	@Autowired
	private  IItemService itemService;

	@Autowired
	private ChannelIMallOrderService mallOrderService;

	@Autowired
	private ChannelIMallSubOrderService subOrderService;

	@Autowired
	private IChannelListingItemService channelListingItemService;


	@Autowired
	private YouzanService youzanService;

	@Autowired
	private HaihuService haihuService;

	@Autowired
	private JdOrderService jdOrderService;

	@Autowired
	private TransactionTemplate transactionTemplate;

	/**
	 * 上新接口
	 * @param itemId
	 */
	@Override
    public void createItem(String shopCode, Long itemId){
		if(EasyUtil.isStringEmpty(shopCode) || itemId == null || itemId < 0){
			throw  new ErpCommonException("shopCode或itemId为空，"+shopCode+";"+itemId);
		}

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		//shopOauthSo.setChannelNo(ChannelType.YouZan.getValue()+"");
		List<JdShopOauthDO> shopOauthList = shopOauthService.searchShopOauthList(shopOauthSo);

		if(EasyUtil.isListEmpty(shopOauthList)){
			throw  new ErpCommonException("channel_shop,shopCode: "+shopCode+" 无有赞店铺存在");
		}
		for (JdShopOauthDO shopOauth : shopOauthList) {
			try {
				ChannelFactory.getChannel(shopOauth).createItem(itemId);
			}catch (Exception e){
				logger.error("",e);
			}
		}
	}





	/**
	 * 发货：同步运单号至各个平台
	 *
	 * 一次只发一个主订单，可以多个明细，
	 *
	 * @param mallSubOrderList
	 * @param shippingOrder
	 */
	@Override
    public void syncLogistics2Channel(List<MallSubOrderDO> mallSubOrderList, ShippingOrderDO shippingOrder){
		if(EasyUtil.isListEmpty(mallSubOrderList)){
             throw new ErpCommonException("mallSubOrderList为空");
		}
		String shopCode = mallSubOrderList.get(0).getShopCode();
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setIsDel(false);
		shopOauthSo.setShopCode(shopCode);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);
		if (shopOauth == null) {
			throw new ErpCommonException("channel_account,shopCode: " + shopCode + " 不存在");
		}
		try {
			ChannelFactory.getChannel(shopOauth).syncLogisticsOnlineConfirm(mallSubOrderList, shippingOrder);
		} catch (Exception e) {
			logger.error("", e);
			throw new ErpCommonException("", e.getMessage());
		}

	}





	/**
	 * 商品上新接口
	 * @param shopCode 店铺唯一性编码
	 * @param itemCode 商品唯一性编码
	 */
	public void addItem(String shopCode, String itemCode){
		if(EasyUtil.isStringEmpty(shopCode) || EasyUtil.isStringEmpty(itemCode)){
			throw  new ErpCommonException("companyNo或itemId为空，"+shopCode+";"+itemCode);
		}

		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		shopOauthSo.setIsDel(false);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+shopCode);
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+shopCode);
		}

		ItemDO itemDO = itemService.getByItemCode(itemCode);

		if(itemDO == null){
			throw new ErpCommonException("shop_error","商品未找到 itemCode:"+itemCode);
		}

		try {
			ChannelFactory.getChannel(shopOauth).createItem(itemDO.getId());
		}catch (Exception e){
			logger.error("",e);

		}
	}


	// 上架
	public void syncListingItem(String shopCode, Long itemId){
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		shopOauthSo.setIsDel(false);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+shopCode);
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+shopCode);
		}

		try {
			ChannelFactory.getChannel(shopOauth).syncListingItem(itemId);
		}catch (Exception e){
			logger.error("",e);

		}

	}

	// 下架
	public void syncDelistingItem(String shopCode, Long itemId){
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		shopOauthSo.setIsDel(false);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+shopCode);
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+shopCode);
		}

		try {
			ChannelFactory.getChannel(shopOauth).syncDelistingItem(itemId);
		}catch (Exception e){
			logger.error("",e);

		}
	}


	@Override
	public void getOrders(String shopCode, Date startTime, Date endTime){
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		shopOauthSo.setIsDel(false);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+shopCode);
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+shopCode);
		}
		try {
			List<JdOrderDO> jdOrderDOList = new ArrayList<>();
			if(Integer.valueOf(ChannelType.YouZan.getValue()).equals(Integer.valueOf(shopOauth.getChannelNo()))){
				jdOrderDOList = youzanService.getOrders(shopOauth,startTime, endTime);
			}
			jdOrderService.saveOrders4Task(jdOrderDOList);
		}catch (Exception e){
			logger.error("",e);
            throw new ErpCommonException("",e.getMessage());
		}
	}
	/**
	 * 这个方法不应该被事务控制，转换的时候既会跑错又会保存未找到的商品信息
	 * 订单插入已经用事务控制了，安全的
	 * @param shopCode
	 * @param requestJdOrder
	 */
	@Override
	public void sendOrder(String shopCode, JdOrderDO requestJdOrder){
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		shopOauthSo.setIsDel(false);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+shopCode);
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+shopCode);
		}

		try {
			GlobalshopOrderVo  globalshopOrderVo = null;
			if(Integer.valueOf(ChannelType.YouZan.getValue()).equals(Integer.valueOf(shopOauth.getChannelNo()))){
				globalshopOrderVo = youzanService.convertYZOrder(shopOauth,requestJdOrder);
			}else{
				throw new ErpCommonException("","暂不支持该方法");
			}
			GlobalshopOrderVo finalGlobalshopOrderVo = globalshopOrderVo;
			transactionTemplate.execute(new TransactionCallback<Boolean>() {
				@Override
				public Boolean doInTransaction(TransactionStatus transactionStatus) {
					doSendOrder(finalGlobalshopOrderVo);
					return Boolean.TRUE;
				}
			});
		}catch (ErpCommonException e){
			logger.error("",e);
			throw e;
		}catch (Exception e){
			logger.error("",e);
			throw new ErpCommonException("",e.getMessage());
		}
	}


	/**
	 * 下发订单的逻辑
	 * @param
	 */
	private void doSendOrder(GlobalshopOrderVo globalshopOrderVo){
		if(globalshopOrderVo == null){
			return;
		}
		MallOrderDO outerOrder = globalshopOrderVo.getMallOrderDO();
		List<MallSubOrderDO> outerOrderDetails = globalshopOrderVo.getMallSubOrderDOS();
		//todo 如果有赞订单已存在，略过,暂时这样处理
		MallOrderDO p = new MallOrderDO();
		p.setChannelOrderNo(outerOrder.getChannelOrderNo());
		p.setCompanyNo(outerOrder.getCompanyNo());
		if (mallOrderService.queryPoCount(p) > 0) {
			throw new ErpCommonException("order has exist,orderNo: " + outerOrder.getChannelOrderNo(),"订单已下发过");
		}

		// 如果有赞订单已存在，略过
        List<String> outOrderIdList = new ArrayList<>();
		//List<String> outOrderIdList = new ArrayList<>();

		mallOrderService.insertSelective(outerOrder); // 添加主订单

		outOrderIdList.add(outerOrder.getChannelOrderNo()); // 收集主订单ID

		subOrderService.insertBatch(outerOrderDetails); // 添加子订单

		if (outOrderIdList.size() > 0) {
			// 把商品详情更新到主订单明细里面
			subOrderService.updateOuterOrderDetailByItemSku(outOrderIdList);
		}
	}





	@Override
	public void getItemsByTime(String shopCode, Date startTime, Date endTime){
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		shopOauthSo.setIsDel(false);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+shopCode);
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+shopCode);
		}

		try {

			if(Integer.valueOf(ChannelType.YouZan.getValue()).equals(Integer.valueOf(shopOauth.getChannelNo()))){
				youzanService.getItemsByTime(shopOauth,startTime, endTime);
			}else{
				throw new ErpCommonException("","暂不支持该方法");
			}

			//ChannelFactory.getChannel(shopOauth).getItems(startTime, endTime);
		}catch (Exception e){
			logger.error("",e);
			throw new ErpCommonException("",e.getMessage());
		}
	}

	@Override
	public void getAllItems(String shopCode){
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		shopOauthSo.setIsDel(false);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+shopCode);
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+shopCode);
		}

		try {

			if(Integer.valueOf(ChannelType.YouZan.getValue()).equals(Integer.valueOf(shopOauth.getChannelNo()))){
				youzanService.getAllItems(shopOauth);
			}else{
				throw new ErpCommonException("","暂不支持该方法");
			}

			//ChannelFactory.getChannel(shopOauth).getItems(startTime, endTime);
		}catch (Exception e){
			logger.error("",e);
			throw new ErpCommonException("",e.getMessage());
		}
	}

	@Override
	public void sendItem(String shopCode, JdItemDO jdItem){
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(shopCode);
		shopOauthSo.setIsDel(false);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+shopCode);
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+shopCode);
		}

		try {
			GlobalShopItemVo globalShopItemVo = null;
			if(Integer.valueOf(ChannelType.YouZan.getValue()).equals(Integer.valueOf(shopOauth.getChannelNo()))){
				 globalShopItemVo = youzanService.convertYZItem(shopOauth,jdItem);
			}else{
				throw new ErpCommonException("","暂不支持该方法");
			}
			GlobalShopItemVo finalGlobalShopItemVo = globalShopItemVo;
			transactionTemplate.execute(new TransactionCallback<Boolean>() {
				@Override
				public Boolean doInTransaction(TransactionStatus transactionStatus) {
					doSendItem(finalGlobalShopItemVo);
					return Boolean.TRUE;
				}
			});
		}catch (ErpCommonException e){
			throw e;
		}catch (Exception e){
			throw new ErpCommonException("",e.getMessage());
		}
	}


	/**
	 * 下发商品的逻辑
	 * @param globalShopItemVo
	 */
	private void doSendItem(GlobalShopItemVo globalShopItemVo){
		if(globalShopItemVo == null){
			return;
		}
		channelListingItemService.dealChannelListtingItem(globalShopItemVo.getChannelListingItemVo());
		itemService.addChannelItem(globalShopItemVo.getItemVo(),true);
	}


	@Override
	public void feedbackOrder(JdLogisticsDO requestLogistic){
		JdShopOauthDO shopOauthSo = new JdShopOauthDO();
		shopOauthSo.setShopCode(requestLogistic.getShopCode());
		shopOauthSo.setIsDel(false);
		shopOauthSo.setOpen(true);
		JdShopOauthDO shopOauth = shopOauthService.searchShopOauth(shopOauthSo);

		if(shopOauth == null){
			throw new ErpCommonException("shop_error","未找到对应店铺信息shopCode:"+requestLogistic.getShopCode());
		}
		//0正常，1关闭
		if (!shopOauth.getOpen()) {
			throw new ErpCommonException("shop_error","当前店铺已停用，请重新启用shopCode:"+requestLogistic.getShopCode());
		}

		try {
			if(Integer.valueOf(ChannelType.YouZan.getValue()).equals(Integer.valueOf(shopOauth.getChannelNo()))){
				youzanService.feedback(shopOauth,requestLogistic);
			}else if(Integer.valueOf(ChannelType.HaiHu.getValue()).equals(Integer.valueOf(shopOauth.getChannelNo()))){
				haihuService.feedback(shopOauth,requestLogistic);
			}else{
				throw new ErpCommonException("","暂不支持该方法");
			}
		}catch (ErpCommonException e){
			throw e;
		}catch (Exception e){
			throw new ErpCommonException("",e.getMessage());
		}

	}



}
