package com.wangqin.globalshop.channel.service.order;

import com.google.common.collect.Maps;
import com.wangqin.globalshop.biz1.app.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.enums.StockUpStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.InventoryBookingRecordDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.InventoryBookingRecordDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.channel.Exception.InventoryException;
import com.wangqin.globalshop.channel.service.inventory.ErpOrderUtil;
import com.wangqin.globalshop.channel.service.inventory.IInventoryBookingRecordChannelService;
import com.wangqin.globalshop.common.enums.InventoryRecord;
import com.wangqin.globalshop.common.utils.BeanUtils;
import com.wangqin.globalshop.common.utils.CodeGenUtil;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * MallSubOrder 表数据服务层接口实现类
 *
 */
@Service("mallSubOrderService")
public class MallSubOrderServiceImplChannel implements ChannelIMallSubOrderService {


	@Autowired
	private MallSubOrderMapperExt mallSubOrderDOMapperExt;

	@Autowired
	private InventoryBookingRecordDOMapperExt inventoryBookingRecordDOMapperExt;

	@Autowired
	private IInventoryBookingRecordChannelService iInventoryBookingRecordChannelService;

	@Autowired
	private InventoryService iInventoryService;


	@Override
    public int deleteByPrimaryKey(Long id){
		return mallSubOrderDOMapperExt.deleteByPrimaryKey(id);
	}

	@Override
    public int insert(MallSubOrderDO record){
		return mallSubOrderDOMapperExt.insert(record);
	}

	@Override
    public int insertSelective(MallSubOrderDO record){
		return mallSubOrderDOMapperExt.insertSelective(record);
	}

	@Override
    public MallSubOrderDO selectByPrimaryKey(Long id){
		return mallSubOrderDOMapperExt.selectByPrimaryKey(id);
	}

	@Override
    public int updateByPrimaryKeySelective(MallSubOrderDO record){
		return mallSubOrderDOMapperExt.updateByPrimaryKeySelective(record);
	}

	@Override
    public int updateByPrimaryKey(MallSubOrderDO record){
		return mallSubOrderDOMapperExt.updateByPrimaryKeySelective(record);
	}
//	/**
//	 * 海狐拆单，只是针对一个商品拆单，不会出现多个商品混合拆单
//	 * @param outerOrderDetailList
//	 * @param tartgetNO
//	 * @param channelAccount
//	 */
//	public void splithaihuErpOrder(String outerOrderDetailList, String tartgetNO, ChannelAccountDO channelAccount) throws InventoryException{
//		String s = outerOrderDetailList.replace("&quot;", "\"");
//		List<OuterOrderDetail> outerOrderDetails = HaiJsonUtils.toBean(s, new TypeReference<List<OuterOrderDetail>>(){
//		});
//		if (CollectionUtils.isNotEmpty(outerOrderDetails)) {
//			for (OuterOrderDetail outerOrderDetail : outerOrderDetails) {
//
//				MallSubOrderDO mallSubOrderSo = new MallSubOrderDO();
//				mallSubOrderSo.setShopCode(channelAccount.getShopCode());
//				mallSubOrderSo.setSkuCode(outerOrderDetail.getSkuCode());
//				mallSubOrderSo.setQuantity(outerOrderDetail.getQuantity());
//
//				MallSubOrderDO erpOrder = mallSubOrderDOMapperExt.queryHaihuErpOrders(mallSubOrderSo);
//				if (erpOrder == null) {
//					throw new BizCommonException("拆单数量不能超过订单数量,或订单状态不对");
//				}
//				this.splitErpOrder(erpOrder, outerOrderDetail.getQuantity());
//				erpOrder.setReceiver("海狐海淘");
//				erpOrder.setTelephone("18868810546");
//				erpOrder.setReceiverState("浙江省");
//				erpOrder.setReceiverCity("杭州市");
//				erpOrder.setReceiverDistrict("西湖区");
//				erpOrder.setReceiverAddress("西斗门路9号福地创业园二期四栋二层");
//				erpOrder.setCreator("海狐拆单");
//				erpOrder.setChannelOrderNo(tartgetNO);
//				this.mallSubOrderDOMapperExt.updateByPrimaryKeySelective(erpOrder);
//
//			}
//
//		} else {
//			throw new BizCommonException("拆单明细不能为空");
//		}
//	}


	@Override
    public void insertBatch(List<MallSubOrderDO> outerOrderDetails){
		for(MallSubOrderDO mallSubOrderDO : outerOrderDetails){
			mallSubOrderDOMapperExt.insert(mallSubOrderDO);
		}
	}

	@Override
	public void updateOuterOrderDetailByItemSku(List<String> outOrderIdList){
		mallSubOrderDOMapperExt.updateOuterOrderDetailByItemSku(outOrderIdList);
	}


	/**
	 * 海狐拆单流程：查出一个未关闭旧子订单，原来数量-新订单数量=新的子订单
	 *             新订单数量，维护成旧订单的数量，如果未备货状态，成为第二个子订单
	 *             如果处于部分或全部备货状态
	 * @param erpOrder
	 * @param splitCount
	 */
	public void splitErpOrder(MallSubOrderDO erpOrder, int splitCount) throws InventoryException {

		MallSubOrderDO newErpOrder = new MallSubOrderDO();
		BeanUtils.copyProperties(erpOrder, newErpOrder);
		newErpOrder.setId(null);
		//		newErpOrder.setErpNo(erpNo);
		newErpOrder.setStatus(OrderStatus.INIT.getCode());
		newErpOrder.setStockStatus(StockUpStatus.INIT.getCode());
		newErpOrder.setGmtModify(new Date());
		newErpOrder.setQuantity(erpOrder.getQuantity()-splitCount);
		newErpOrder.setWarehouseNo(null);
		//获取子订单数量便于产生单号
		List<MallSubOrderDO> erpOrders = mallSubOrderDOMapperExt.selectByOrderNo(erpOrder.getChannelOrderNo());
		newErpOrder.setSubOrderNo(CodeGenUtil.getSubOrderNo());
		this.mallSubOrderDOMapperExt.insert(newErpOrder);

		erpOrder.setQuantity(splitCount);
		erpOrder.setGmtModify(new Date());
		//		this.baseMapper.updateById(erpOrder);
		//该子订单未备货状态
		if(erpOrder.getStockStatus()==StockUpStatus.INIT.getCode()||erpOrder.getStockStatus()==StockUpStatus.RELEASED.getCode()){
			this.mallSubOrderDOMapperExt.updateByPrimaryKeySelective(erpOrder);

		}else {
			//该子订单处于部分备货或者全部备货状态，去看看这个子订单备货到什么程度了。备货情况
			List<InventoryBookingRecordDO>  records = inventoryBookingRecordDOMapperExt.selectBySubOrderNo(erpOrder.getSubOrderNo());
			Long realBooked = 0L;//现货备货数量
			Long transBooked = 0L;//在途备货数量
			Map<Long,InventoryBookingRecordDO> realMaps = Maps.newHashMap();//现货备货记录
			Map<Long,InventoryBookingRecordDO> transMaps = Maps.newHashMap();//在途备货记录
			if(CollectionUtils.isNotEmpty(records)){
				for(InventoryBookingRecordDO record:records){
					if(InventoryRecord.InventoryType.INVENTORY.equalsIgnoreCase(record.getInventoryType())){
						//实际备货记录
						realBooked+=record.getBookedQuantity();//getQuantity是订单实际需要数量，book是备货数量
						realMaps.put(record.getId(), record);
					}else{
						//在途备货记录
						transBooked+=record.getBookedQuantity();
						transMaps.put(record.getId(), record);
					}
				}
			}else{
				throw new InventoryException("无备货记录，数据异常");
			}
			//要拆分的主订单比全部备货数量还大，原来的备货直接给予主订单，新增一条未备货的子订单即可
			if(splitCount>=(realBooked+transBooked)){
				//无剩余备货订单
				for(InventoryBookingRecordDO record:records){
					record.setQuantity(Long.valueOf(erpOrder.getQuantity()));
					record.setGmtModify(new Date());
				}
				iInventoryBookingRecordChannelService.updateBatchInvRecord(records);
				//根据备货数量计算订单状态
				ErpOrderUtil.calculateStockStatus(erpOrder,realBooked,transBooked);
				this.mallSubOrderDOMapperExt.updateByPrimaryKeySelective(erpOrder);
				//新订单改为未备货
				//				this.baseMapper.updateById(newErpOrder);

			}else if(splitCount>realBooked){
				//比现货备货大，现货全部给予主订单。
				Long mainReal = realBooked;  //主订单现货
				Long subReal = 0L;   //子订单现货

				Long mainTrans = splitCount-realBooked;  //主子订单在途
				Long subTrans = realBooked+transBooked-splitCount;  //子子订单在途


				//有剩余在途备货订单
				Long x = splitCount - realBooked;
				if(!transMaps.isEmpty()){
					for(Long id : transMaps.keySet()){
						InventoryBookingRecordDO tran = transMaps.get(id);
						if(x>0){
							if(x<tran.getBookedQuantity()){
								//拆
								Long tx = tran.getBookedQuantity()-x;
								//x的数量给主订单，tx的数量给子订单
								//新record初始化
								InventoryBookingRecordDO newRecord = new InventoryBookingRecordDO();
								BeanUtils.copyProperties(tran, newRecord);
								newRecord.setId(null);
								newRecord.setGmtCreate(new Date());
								newRecord.setGmtModify(new Date());
								newRecord.setBookedQuantity(tx);
								newRecord.setQuantity(Long.valueOf(newErpOrder.getQuantity()));
								newRecord.setSubOrderNo(newErpOrder.getSubOrderNo());
								newRecord.setOrderNo(newErpOrder.getOrderNo());
								inventoryBookingRecordDOMapperExt.insert(newRecord);

								//老record数据更新
								tran.setBookedQuantity(x);
								tran.setQuantity(Long.valueOf(erpOrder.getQuantity()));
								tran.setGmtModify(new Date());

								x=0L;
							}else{
								//不拆，全部给主子订单
								x-=tran.getBookedQuantity();
								tran.setQuantity(Long.valueOf(erpOrder.getQuantity()));
								tran.setGmtModify(new Date());
							}

						}else{
							//剩余在途全部给子子订单
							tran.setOrderNo(newErpOrder.getOrderNo());
							tran.setSubOrderNo(newErpOrder.getSubOrderNo());
							tran.setQuantity(Long.valueOf(newErpOrder.getQuantity()));
							tran.setGmtModify(new Date());
						}
					}
				}
				//现货全部给主订单
				if(!realMaps.isEmpty()){
					for(Long id : realMaps.keySet()){
						InventoryBookingRecordDO real = realMaps.get(id);
						real.setQuantity(Long.valueOf(erpOrder.getQuantity()));
						real.setGmtModify(new Date());
					}
				}
				//更新全部在途或者现货record
				iInventoryBookingRecordChannelService.updateBatchInvRecord(records);
				ErpOrderUtil.calculateStockStatus(erpOrder,mainReal,mainTrans);
				this.mallSubOrderDOMapperExt.updateByPrimaryKeySelective(erpOrder);

				newErpOrder.setWarehouseNo(erpOrder.getWarehouseNo());
				ErpOrderUtil.calculateStockStatus(newErpOrder,subReal,subTrans);
				this.mallSubOrderDOMapperExt.updateByPrimaryKeySelective(newErpOrder);
			}else{
				//从1 <= splitCount <= realBooked 个
				//				int mainReal = realBooked;
				//				int mainTrans = 0;
				Long subReal = realBooked-splitCount; //子订单实际备货数量
				Long subTrans = transBooked;//子订单在途备货数量

				//有剩余现货备货订单
				Long x = splitCount+0L;
				for(Long id : realMaps.keySet()){
					InventoryBookingRecordDO real = realMaps.get(id);
					if(x>0){
						if(x<real.getBookedQuantity()){
							//拆
							Long tx = real.getBookedQuantity()-x;
							//x的数量给主订单，tx的数量给子订单
							//新record初始化
							InventoryBookingRecordDO newRecord = new InventoryBookingRecordDO();
							BeanUtils.copyProperties(real, newRecord);
							newRecord.setId(null);
							newRecord.setGmtCreate(new Date());
							newRecord.setGmtModify(new Date());
							newRecord.setBookedQuantity(tx);
							newRecord.setQuantity(Long.valueOf(newErpOrder.getQuantity()));
							newRecord.setOrderNo(newErpOrder.getOrderNo());
							newRecord.setSubOrderNo(newErpOrder.getSubOrderNo());
							inventoryBookingRecordDOMapperExt.insert(newRecord);

							//老record数据更新
							real.setBookedQuantity(x);
							real.setQuantity(Long.valueOf(erpOrder.getQuantity()));
							real.setGmtModify(new Date());

							x=0L;
						}else{
							//不拆，全部给主订单
							x-=real.getBookedQuantity();
							real.setQuantity(Long.valueOf(erpOrder.getQuantity()));
							real.setGmtModify(new Date());
						}
					}else{//剩下现货都给子订单
						real.setOrderNo(newErpOrder.getOrderNo());
						real.setSubOrderNo(newErpOrder.getSubOrderNo());
						real.setQuantity(Long.valueOf(newErpOrder.getQuantity()));
						real.setGmtModify(new Date());
					}
				}
				//在途全部给子订单
				if(!transMaps.isEmpty()){
					for(Long id : transMaps.keySet()){
						InventoryBookingRecordDO tran = transMaps.get(id);
						tran.setOrderNo(newErpOrder.getOrderNo());
						tran.setSubOrderNo(newErpOrder.getSubOrderNo());
						tran.setQuantity(Long.valueOf(newErpOrder.getQuantity()));
						tran.setGmtModify(new Date());
					}
				}
				erpOrder.setStockStatus(StockUpStatus.STOCKUP.getCode());
				erpOrder.setGmtModify(new Date());
				this.mallSubOrderDOMapperExt.updateByPrimaryKeySelective(erpOrder);

				newErpOrder.setWarehouseNo(erpOrder.getWarehouseNo());
				ErpOrderUtil.calculateStockStatus(newErpOrder,subReal,subTrans);
				this.mallSubOrderDOMapperExt.updateByPrimaryKeySelective(newErpOrder);
				//更新全部在途或者现货record
				iInventoryBookingRecordChannelService.updateBatchInvRecord(records);
			}

		}
	}




//	@Override
//	public BizResult lockErpOrder(MallSubOrderDO erpOrder) throws InventoryException{
//		if(erpOrder.getStatus()!=OrderStatus.INIT.getCode()){
//			return BizResult.buildFailed("订单状态错误");
//		}
//		//未备货订单
//		if(erpOrder.getStockStatus()==StockUpStatus.INIT.getCode()||erpOrder.getStockStatus()==StockUpStatus.RELEASED.getCode()){
//			if(erpOrder.getWarehouseNo()==null){
//				List<MallSubOrderDO> erpOrders = Lists.newArrayList();
//				erpOrders.add(erpOrder);
//				List<InventoryServiceImpl.WarehouseCollector> wcs = iInventoryService.selectWarehousesByErpOrders(erpOrders);
//				if(CollectionUtils.isNotEmpty(wcs)){
//					iInventoryService.lockedInventroy(wcs.get(0));
//				}else{
//					return BizResult.buildFailed("没有库存备货失败");
//				}
//			}else{
//				return BizResult.buildFailed("没有仓库信息");
//			}
//		}else{
//			if(erpOrder.getStockStatus()!=StockUpStatus.STOCKUP.getCode()&&erpOrder.getStockStatus()!=StockUpStatus.MIX_STOCKUP.getCode()&&erpOrder.getWarehouseNo()!=null){
//				InventoryServiceImpl.WarehouseCollector wc = iInventoryService.selectWarehousesByErpOrder(erpOrder);
//				if(wc!=null){
//					iInventoryService.lockedInventroy(wc);
//				}else{
//					return BizResult.buildFailed("没有库存备货失败");
//				}
//			}else{
//				return BizResult.buildFailed("订单备货状态错误");
//			}
//
//		}
//		return BizResult.buildSuccess();
//	}




}
