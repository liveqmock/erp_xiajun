package com.wangqin.globalshop.channel.service.order;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wangqin.globalshop.biz1.app.Exception.ErpCommonException;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.channel.dal.haihu.OuterOrderDetail;
import com.wangqin.globalshop.channel.dal.mapperExt.MallSubOrderDOMapperExt;
import com.wangqin.globalshop.common.utils.BizResult;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;

/**
 *
 * MallSubOrder 表数据服务层接口实现类
 *
 */
@Service("mallSubOrderService")
public class MallSubOrderServiceImpl implements IMallSubOrderService {


	@Resource MallSubOrderDOMapperExt mallSubOrderDOMapperExt;


	public int deleteByPrimaryKey(Long id){
		return mallSubOrderDOMapperExt.deleteByPrimaryKey(id);
	}

	public int insert(MallSubOrderDO record){
		return mallSubOrderDOMapperExt.insert(record);
	}

	public int insertSelective(MallSubOrderDO record){
		return mallSubOrderDOMapperExt.insertSelective(record);
	}

	public MallSubOrderDO selectByPrimaryKey(Long id){
		return mallSubOrderDOMapperExt.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(MallSubOrderDO record){
		return mallSubOrderDOMapperExt.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(MallSubOrderDO record){
		return mallSubOrderDOMapperExt.updateByPrimaryKey(record);
	}
	public void splithaihuErpOrder(String outerOrderDetailList, String tartgetNO) {
		String s = outerOrderDetailList.replace("&quot;", "\"");
		List<OuterOrderDetail> outerOrderDetails = HaiJsonUtils.toBean(s, new TypeReference<List<OuterOrderDetail>>(){
		});
		if (CollectionUtils.isNotEmpty(outerOrderDetails)) {
			for (OuterOrderDetail outerOrderDetail : outerOrderDetails) {

				MallSubOrderDO erpOrder = mallSubOrderDOMapperExt.queryHaihuErpOrders(outerOrderDetail);
				if (erpOrder == null) {
					throw new ErpCommonException("拆单数量不能超过订单数量");
				}
				this.splitErpOrder(erpOrder, outerOrderDetail.getQuantity());
				erpOrder.setReceiver("海狐海淘");
				erpOrder.setTelephone("18868810546");
				erpOrder.setReceiverState("浙江省");
				erpOrder.setReceiverCity("杭州市");
				erpOrder.setReceiverDistrict("西湖区");
				erpOrder.setReceiverAddress("西斗门路9号福地创业园二期四栋二层");
				erpOrder.setCreator("海狐拆单");
				erpOrder.setChannelOrderNo(tartgetNO);
				this.mallSubOrderDOMapperExt.updateByPrimaryKey(erpOrder);

			}

		} else {
			throw new ErpCommonException("拆单明细不能为空");
		}
	}





	public void splitErpOrder(MallSubOrderDO erpOrder, int splitCount)  {
//
//		ErpOrder newErpOrder = new ErpOrder();
//		BeanUtils.copyProperties(erpOrder, newErpOrder);
//		newErpOrder.setId(null);
//		//		newErpOrder.setErpNo(erpNo);
//		newErpOrder.setStatus(OrderStatus.INIT.getCode());
//		newErpOrder.setStockStatus(StockUpStatus.INIT.getCode());
//		newErpOrder.setGmtModify(new Date());
//		newErpOrder.setQuantity(erpOrder.getQuantity()-splitCount);
//		newErpOrder.setWarehouseId(null);
//		//获取子订单数量便于产生单号
//		Map<String, Object> clumnMap = new HashMap<>();
//		clumnMap.put("outer_order_id", erpOrder.getOuterOrderId());
//		List<ErpOrder> erpOrders = erpOrderMapper.selectByMap(clumnMap);
//		newErpOrder.setErpNo("O" + erpOrder.getOrderNo().substring(1) + String.format("%0"+4+"d", erpOrders.size()+1));
//		this.baseMapper.insert(newErpOrder);
//
//		erpOrder.setQuantity(splitCount);
//		erpOrder.setGmtModify(new Date());
//		//		this.baseMapper.updateById(erpOrder);
//		//未备货状态
//		if(erpOrder.getStockStatus()==StockUpStatus.INIT.getCode()||erpOrder.getStockStatus()==StockUpStatus.RELEASED.getCode()){
//			this.baseMapper.updateById(erpOrder);
//
//		}else {//部分备货或者全部备货状态
//			List<InventoryRecord>  records = inventoryRecordService.queryByErpOrderId(erpOrder.getId());
//			int realBooked = 0;//现货备货数量
//			int transBooked = 0;//在途备货数量
//			Map<Long,InventoryRecord> realMaps = Maps.newHashMap();//现货备货记录
//			Map<Long,InventoryRecord> transMaps = Maps.newHashMap();//在途备货记录
//			if(CollectionUtils.isNotEmpty(records)){
//				for(InventoryRecord record:records){
//					if(record.getInventoryType()==InventoryType.INVENTORY){
//						realBooked+=record.getBooked();
//						realMaps.put(record.getId(), record);
//					}else{
//						transBooked+=record.getBooked();
//						transMaps.put(record.getId(), record);
//					}
//				}
//			}else{
//				throw new InventoryException("无备货记录，数据异常");
//			}
//			//要拆分的主订单比全部备货数量还大，原来的备货直接给予主订单，新增一条未备货的子订单即可
//			if(splitCount>=(realBooked+transBooked)){
//				//无剩余备货订单
//				for(InventoryRecord record:records){
//					record.setQuantity(erpOrder.getQuantity());
//					record.setGmtModify(new Date());
//				}
//				inventoryRecordService.updateBatchById(records);
//				//根据备货数量计算订单状态
//				ErpOrderUtil.calculateStockStatus(erpOrder,realBooked,transBooked);
//				this.baseMapper.updateById(erpOrder);
//				//新订单改为未备货
//				//				this.baseMapper.updateById(newErpOrder);
//
//			}else if(splitCount>realBooked){//比现货备货大，现货全部给予主订单。
//				int mainReal = realBooked;  //主订单现货
//				int subReal = 0;   //子订单现货
//
//				int mainTrans = splitCount-realBooked;  //主订单在途
//				int subTrans = realBooked+transBooked-splitCount;  //子订单在途
//
//
//				//有剩余在途备货订单
//				int x = splitCount - realBooked;
//				if(!transMaps.isEmpty()){
//					for(Long id : transMaps.keySet()){
//						InventoryRecord tran = transMaps.get(id);
//						if(x>0){
//							if(x<tran.getBooked()){
//								//拆
//								int tx = tran.getBooked()-x;
//								//x的数量给主订单，tx的数量给子订单
//								//新record初始化
//								InventoryRecord newRecord = new InventoryRecord();
//								BeanUtils.copyProperties(tran, newRecord);
//								newRecord.setId(null);
//								newRecord.setGmtCreate(new Date());
//								newRecord.setGmtModify(new Date());
//								newRecord.setBooked(tx);
//								newRecord.setQuantity(newErpOrder.getQuantity());
//								newRecord.setErpOrderId(newErpOrder.getId());
//								inventoryRecordService.insert(newRecord);
//
//								//老record数据更新
//								tran.setBooked(x);
//								tran.setQuantity(erpOrder.getQuantity());
//								tran.setGmtModify(new Date());
//
//								x=0;
//							}else{
//								//不拆，全部给主订单
//								x-=tran.getBooked();
//								tran.setQuantity(erpOrder.getQuantity());
//								tran.setGmtModify(new Date());
//							}
//
//						}else{
//							//剩余在途全部给子订单
//							tran.setErpOrderId(newErpOrder.getId());
//							tran.setQuantity(newErpOrder.getQuantity());
//							tran.setGmtModify(new Date());
//						}
//					}
//				}
//				//现货全部给主订单
//				if(!realMaps.isEmpty()){
//					for(Long id : realMaps.keySet()){
//						InventoryRecord real = realMaps.get(id);
//						real.setQuantity(erpOrder.getQuantity());
//						real.setGmtModify(new Date());
//					}
//				}
//				//更新全部在途或者现货record
//				inventoryRecordService.updateBatchById(records);
//				ErpOrderUtil.calculateStockStatus(erpOrder,mainReal,mainTrans);
//				this.baseMapper.updateById(erpOrder);
//
//				newErpOrder.setWarehouseId(erpOrder.getWarehouseId());
//				ErpOrderUtil.calculateStockStatus(newErpOrder,subReal,subTrans);
//				this.baseMapper.updateById(newErpOrder);
//			}else{//从1 <= splitCount <= realBooked 个
//				//				int mainReal = realBooked;
//				//				int mainTrans = 0;
//				int subReal = realBooked-splitCount; //子订单实际备货数量
//				int subTrans = transBooked;//子订单在途备货数量
//
//				//有剩余现货备货订单
//				int x = splitCount;
//				for(Long id : realMaps.keySet()){
//					InventoryRecord real = realMaps.get(id);
//					if(x>0){
//						if(x<real.getBooked()){
//							//拆
//							int tx = real.getBooked()-x;
//							//x的数量给主订单，tx的数量给子订单
//							//新record初始化
//							InventoryRecord newRecord = new InventoryRecord();
//							BeanUtils.copyProperties(real, newRecord);
//							newRecord.setId(null);
//							newRecord.setGmtCreate(new Date());
//							newRecord.setGmtModify(new Date());
//							newRecord.setBooked(tx);
//							newRecord.setQuantity(newErpOrder.getQuantity());
//							newRecord.setErpOrderId(newErpOrder.getId());
//							inventoryRecordService.insert(newRecord);
//
//							//老record数据更新
//							real.setBooked(x);
//							real.setQuantity(erpOrder.getQuantity());
//							real.setGmtModify(new Date());
//
//							x=0;
//						}else{
//							//不拆，全部给主订单
//							x-=real.getBooked();
//							real.setQuantity(erpOrder.getQuantity());
//							real.setGmtModify(new Date());
//						}
//					}else{//剩下现货都给子订单
//						real.setErpOrderId(newErpOrder.getId());
//						real.setQuantity(newErpOrder.getQuantity());
//						real.setGmtModify(new Date());
//					}
//				}
//				//在途全部给子订单
//				if(!transMaps.isEmpty()){
//					for(Long id : transMaps.keySet()){
//						InventoryRecord tran = transMaps.get(id);
//						tran.setErpOrderId(newErpOrder.getId());
//						tran.setQuantity(newErpOrder.getQuantity());
//						tran.setGmtModify(new Date());
//					}
//				}
//				erpOrder.setStockStatus(StockUpStatus.STOCKUP.getCode());
//				erpOrder.setGmtModify(new Date());
//				this.baseMapper.updateById(erpOrder);
//
//				newErpOrder.setWarehouseId(erpOrder.getWarehouseId());
//				ErpOrderUtil.calculateStockStatus(newErpOrder,subReal,subTrans);
//				this.baseMapper.updateById(newErpOrder);
//				//更新全部在途或者现货record
//				inventoryRecordService.updateBatchById(records);
//			}
//
//		}
	}




	@Override
	public BizResult lockErpOrder(MallSubOrderDO erpOrder) {
//		if(erpOrder.getStatus()!=OrderStatus.INIT.getCode()){
//			return BizResult.buildFailed("订单状态错误");
//		}
//		//未备货订单
//		if(erpOrder.getStockStatus()==StockUpStatus.INIT.getCode()||erpOrder.getStockStatus()==StockUpStatus.RELEASED.getCode()){
//			if(erpOrder.getWarehouseId()==null){
//				List<ErpOrder> erpOrders = Lists.newArrayList();
//				erpOrders.add(erpOrder);
//				List<WarehouseCollector> wcs = inventoryService.selectWarehousesByErpOrders(erpOrders);
//				if(CollectionUtils.isNotEmpty(wcs)){
//					inventoryService.lockedInventroy(wcs.get(0));
//				}else{
//					return BizResult.buildFailed("没有库存备货失败");
//				}
//			}else{
//				return BizResult.buildFailed("没有仓库信息");
//			}
//		}else{
//			if(erpOrder.getStockStatus()!=StockUpStatus.STOCKUP.getCode()&&erpOrder.getStockStatus()!=StockUpStatus.MIX_STOCKUP.getCode()&&erpOrder.getWarehouseId()!=null){
//				WarehouseCollector wc = inventoryService.selectWarehousesByErpOrder(erpOrder);
//				if(wc!=null){
//					inventoryService.lockedInventroy(wc);
//				}else{
//					return BizResult.buildFailed("没有库存备货失败");
//				}
//			}else{
//				return BizResult.buildFailed("订单备货状态错误");
//			}
//
//		}
		return BizResult.buildSuccess();
	}




}
