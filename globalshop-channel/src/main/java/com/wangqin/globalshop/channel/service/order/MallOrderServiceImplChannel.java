package com.wangqin.globalshop.channel.service.order;

import com.google.common.collect.Sets;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallOrderDOMapper;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallOrderMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.channel.Exception.InventoryException;
import com.wangqin.globalshop.common.utils.EasyUtil;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * MallOrder 表数据服务层接口实现类
 */
@Service("mallOrderService")
public class MallOrderServiceImplChannel implements ChannelIMallOrderService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ChannelIMallSubOrderService mallSubOrderService;

    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private MallOrderMapperExt mallOrderDOMapperExt;

    @Autowired
    private MallSubOrderMapperExt mallSubOrderDOMapperExt;

    public MallOrderDOMapper getMapper() {
        return mallOrderDOMapperExt;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mallOrderDOMapperExt.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MallOrderDO record) {
        return mallOrderDOMapperExt.insert(record);
    }

    @Override
    public int insertSelective(MallOrderDO record) {
        return mallOrderDOMapperExt.insertSelective(record);
    }

    @Override
    public MallOrderDO selectByPrimaryKey(Long id) {
        return mallOrderDOMapperExt.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(MallOrderDO record) {
        return mallOrderDOMapperExt.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MallOrderDO record) {
        return mallOrderDOMapperExt.updateByPrimaryKeySelective(record);
    }

    @Override
    public Integer queryPoCount(MallOrderDO mallOrderDO) {
        return mallOrderDOMapperExt.queryPoCount(mallOrderDO);
    }

    @Override
    public MallOrderDO queryPo(MallOrderDO mallOrderDO) {
        return mallOrderDOMapperExt.queryPo(mallOrderDO);
    }

    @Override
    public List<MallOrderDO> queryPoList(MallOrderDO mallOrderDO) {
        return mallOrderDOMapperExt.queryPoList(mallOrderDO);
    }


//    public void reviewByIdList(List<String> orderNoList) {
//        if (EasyUtil.isListEmpty(orderNoList)) {
//            return;
//        }
//        for (String orderNo : orderNoList) {
//            review(orderNo);
//        }
//    }

//
//    /**
//     * 方法中两部分别开启两个新事务处理
//     */
//
//    public void review(String orderNo) {
//        // 1、审核导入
//        //this.reviewOuterOrder(orderId);
//        // 2、锁定库存
//        MallSubOrderDO so = new MallSubOrderDO();
//        so.setOrderNo(orderNo);
//        List<MallSubOrderDO> erpOrders = mallSubOrderDOMapperExt.selectSubOrderList(so);
//        if (CollectionUtils.isNotEmpty(erpOrders)) {
//            try {
//                //同一个仓库分配库存
//                List<WarehouseCollector> wcs = inventoryService.selectWarehousesByErpOrders(erpOrders);
//                Set<Long> erpOrderIds = Sets.newHashSet();
//                if (CollectionUtils.isNotEmpty(wcs)) {
//                    for (WarehouseCollector wc : wcs) {
//                        try {
//                            erpOrderIds.add(wc.getErpOrderId());
//                            inventoryService.lockedInventroy(wc);
//                        } catch (InventoryException e) {
//                            logger.info(e.getMessage() + e);
//                            e.printStackTrace();
//                        } catch (exception e) {
//                            logger.info(e.getMessage() + e);
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                //落单的商品，其他的仓库随机分配库存
//                for (MallSubOrderDO erpOrder : erpOrders) {
//                    if (!erpOrderIds.contains(erpOrder.getId())) {
//                        try {
//                            mallSubOrderService.lockErpOrder(erpOrder);
//                        } catch (InventoryException e) {
//                            logger.info(e.getMessage() + e);
//                            e.printStackTrace();
//                        } catch (exception e) {
//                            logger.info(e.getMessage() + e);
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            } catch (exception e) {
//                logger.info(e.getMessage() + e);
//            }
//        }
//    }

//	@Override
//	public void importOuterOrder(MallOrderVo order) {
//		List<MallSubOrderDO> subOrderDOS = order.getMallSubOrderDOS();
//		int i=0;
//		for (MallSubOrderDO outerOrderDetail : subOrderDOS) {
//			if(outerOrderDetail.getSkuCode()==null) continue;
//			mallSubOrderDOMapperExt(order, outerOrderDetail, ++i);
//		}
//	}


//	public void reviewOuterOrder(Long orderId) {
//		MallOrderDO mallOrder = mallOrderDOMapperExt.selectByPrimaryKey(orderId);
//
//
//		MallOrderVo resultVo = new MallOrderVo();
//		if(mallOrder != null){
//			MallSubOrderDO so = new MallSubOrderDO();
//			so.setOrderNo(mallOrder.getOrderNo());
//			List<MallSubOrderDO> subOrderDOS =  mallSubOrderDOMapperExt.queryPoList(so);
//
//			BeanUtils.copies(mallOrder,resultVo);
//			resultVo.setMallSubOrderDOS(subOrderDOS);
//		}
//
//		if(mallOrder!=null){
//			importOuterOrder(resultVo);
//		}
//	}


}
