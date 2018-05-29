package com.wangqin.globalshop.order.app.service.shipping.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Sets;
import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.constants.enums.ShippingOrderStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.LogisticCompanyDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.IShippingOrderMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.LogisticCompanyDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.SequenceUtilMapperExt;
import com.wangqin.globalshop.biz1.app.dto.MultiDeliveryFormDTO;
import com.wangqin.globalshop.biz1.app.service.channel.ChannelFactory;
import com.wangqin.globalshop.biz1.app.vo.JsonPageResult;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;
import com.wangqin.globalshop.common.enums.OrderStatus;
import com.wangqin.globalshop.common.enums.StockUpStatus;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.common.utils.DateUtil;
import com.wangqin.globalshop.common.utils.HaiJsonUtils;
import com.wangqin.globalshop.common.utils.NumberUtil;
import com.wangqin.globalshop.common.utils.ShiroUtil;
import com.wangqin.globalshop.order.app.service.inventory.OrderIInventoryService;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingOrderService;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author biscuit
 * @data 2018/05/24
 */
@Service
public class ShippingOrderServiceImpl implements IShippingOrderService {
    @Autowired
    private IShippingOrderMapperExt shippingOrderMapper;
    @Autowired
    private MallSubOrderMapperExt mallSubOrderMapper;
    @Autowired
    private IMallSubOrderService mallSubOrderService;
    @Autowired
    private IMallOrderService mallOrderService;
    @Autowired
    private OrderIInventoryService orderInventoryService;
    @Autowired
    private SequenceUtilMapperExt sequenceUtilMapperExt;
    @Autowired
    private LogisticCompanyDOMapperExt logisticCompanyMapper;

    @Override
    public JsonPageResult<List<ShippingOrderDO>> queryShippingOrders(ShippingOrderVO shippingOrderVO) {
        JsonPageResult<List<ShippingOrderDO>> shippingOrderResult = new JsonPageResult<>();
        // 1、查询总的记录数量
        Integer totalCount = shippingOrderMapper.queryShippingOrderCount(shippingOrderVO);
        // 2、查询分页记录
        if (totalCount != null && totalCount != 0L) {
            shippingOrderResult.buildPage(totalCount, shippingOrderVO);
            List<ShippingOrderDO> shippingOrders = shippingOrderMapper.queryShippingOrders(shippingOrderVO);
            shippingOrderResult.setData(shippingOrders);
        } else {
            List<ShippingOrderDO> shippingOrders = new ArrayList<>();
            shippingOrderResult.setData(shippingOrders);
        }
        return shippingOrderResult;
    }

    @Override
    public MultiDeliveryFormDTO queryByOrderId(String orderId) {
        MultiDeliveryFormDTO multiDeliveryFormDTO = new MultiDeliveryFormDTO();

        String s = orderId.replace("&quot;", "\"");
        List<Long> erpOrderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>(){});
        List<MallSubOrderDO> mallSubOrderList = mallSubOrderMapper.queryByOrderId(erpOrderIdList);
        List<MallSubOrderDO> totalMallSubOrderList = new ArrayList<>();
        String receiver=null;
        String telephone=null;
        String addressDetail=null;
        Set<Long> erpOrderIdSet = Sets.newHashSet();

        Double skuWeight = 0D;
        Double totalSalePrice = 0D;
        for(MallSubOrderDO mallSubOrder : mallSubOrderList) {
            if(mallSubOrder.getStockStatus()==null || (mallSubOrder.getStockStatus()!=StockUpStatus.STOCKUP.getCode() && mallSubOrder.getStockStatus()!=StockUpStatus.PREPARE.getCode())) {
                throw new ErpCommonException("商品备货状态不对，子订单号：" + mallSubOrder.getId());
            }
            if(StringUtils.isBlank(mallSubOrder.getReceiver()) || StringUtils.isBlank(mallSubOrder.getTelephone()) || StringUtils.isBlank(mallSubOrder.getReceiverState()) || StringUtils.isBlank(mallSubOrder.getReceiverCity()) || StringUtils.isBlank(mallSubOrder.getReceiverDistrict())) {
                throw new ErpCommonException("收货人地址不能为空：" + mallSubOrder.getId());
            }
            if(mallSubOrder.getWeight()!=null) {
                skuWeight += mallSubOrder.getWeight()*mallSubOrder.getQuantity();
            }
            totalSalePrice += mallSubOrder.getSalePrice()*mallSubOrder.getQuantity();
            if(StringUtils.isBlank(receiver)) {
                receiver = mallSubOrder.getReceiver();
                telephone = mallSubOrder.getTelephone();
                addressDetail = mallSubOrder.getReceiverAddress();

                multiDeliveryFormDTO.setReceiver(mallSubOrder.getReceiver());
                multiDeliveryFormDTO.setReceiverState(mallSubOrder.getReceiverState());
                multiDeliveryFormDTO.setReceiverCity(mallSubOrder.getReceiverCity());
                multiDeliveryFormDTO.setReceiverDistrict(mallSubOrder.getReceiverDistrict());
                multiDeliveryFormDTO.setIdCard(mallSubOrder.getIdCard());
                multiDeliveryFormDTO.setTelephone(mallSubOrder.getTelephone());
                multiDeliveryFormDTO.setPostcode(mallSubOrder.getPostcode());
                multiDeliveryFormDTO.setRemark(mallSubOrder.getMemo());
                multiDeliveryFormDTO.setAddressDetail(mallSubOrder.getReceiverAddress());
            } else if(!receiver.equals(mallSubOrder.getReceiver()) || !telephone.equals(mallSubOrder.getTelephone()) || !addressDetail.equals(mallSubOrder.getReceiverAddress())) {
                multiDeliveryFormDTO.setInfo("子订单收货信息不一致，请注意核对！");
            }
            if(erpOrderIdSet.contains(mallSubOrder.getId())) {
                continue;
            }

            //搜索同一收货人的子订单
            MallSubOrderDO tjErpOrder = new MallSubOrderDO();
            tjErpOrder.setReceiver(mallSubOrder.getReceiver());
            tjErpOrder.setTelephone(mallSubOrder.getTelephone());
            tjErpOrder.setReceiverState(mallSubOrder.getReceiverState());
            tjErpOrder.setReceiverCity(mallSubOrder.getReceiverCity());
            tjErpOrder.setReceiverDistrict(mallSubOrder.getReceiverDistrict());
            tjErpOrder.setStatus((byte) 0);								//订单状态：新建
            //tjErpOrder.setStockStatus(erpOrder.getStockStatus());	//备货状态：已备货
            //tjErpOrder.setWarehouseId(erpOrder.getWarehouseId());	//相同仓库
            List<MallSubOrderDO> selErpOrderList = mallSubOrderService.selectList(tjErpOrder);
            for(int i=0; i<selErpOrderList.size(); i++) {
                MallSubOrderDO selErpOrder= selErpOrderList.get(i);
                //不在同一仓库的情况，不用考虑
                if(selErpOrder.getWarehouseNo()!=null && selErpOrder.getWarehouseNo()!=mallSubOrder.getWarehouseNo()) {
                    continue;
                }
                //在同一仓库的或者未备货的
                if(!erpOrderIdSet.contains(selErpOrder.getId())) {
                    String positionNoStr = mallSubOrderMapper.selectPositionNoByOrderId(selErpOrder.getOrderNo());
                    // TODO: 18.5.28 Biscuits
                    /* selErpOrder.setPositionNo(positionNoStr);*/
                    totalMallSubOrderList.add(selErpOrder);
                    erpOrderIdSet.add(selErpOrder.getId());
                }
            }
        }
        Collections.sort(totalMallSubOrderList, new Comparator<MallSubOrderDO>() {
            @Override
            public int compare(MallSubOrderDO o1, MallSubOrderDO o2) {
                return o2.getStockStatus().compareTo(o1.getStockStatus());
            }

        });
        multiDeliveryFormDTO.setSkuWeight(skuWeight);
        multiDeliveryFormDTO.setTotalSalePrice(totalSalePrice);
        multiDeliveryFormDTO.setMallSubOrderList(totalMallSubOrderList);
        return multiDeliveryFormDTO;
    }

    @Override
    public Set<Long> multiDelivery(ShippingOrderDO shippingOrderDO) {
        return null;
    }


    @Override
    public List<MallSubOrderDO> queryShippingOrderDetail(String mallOrders) {
        if(StringUtil.isBlank(mallOrders)) {
            throw new ErpCommonException("子订单号为空");
        }
        String s = mallOrders.replace("&quot;", "\"");
        List<Long> erpOrderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>(){});
        List<MallSubOrderDO> ErpOrderList = mallSubOrderMapper.queryByOrderId(erpOrderIdList);
        ErpOrderList.forEach(erpOrder ->{
            //在同一仓库的或者未备货的
            String positionNoStr = mallSubOrderMapper.selectPositionNoByOrderId(erpOrder.getOrderNo());
            // TODO: 18.5.28 Biscuits
//            erpOrder.setPositionNo(positionNoStr);
        });
        return ErpOrderList;
    }

    @Override
    public void updateOuterstatus(Set<Long> mainIds) {
        for(Long mainId:mainIds){
            if (mainId != null) {
                MallOrderDO outerOrder = mallOrderService.selectById(mainId);
                if (outerOrder != null) {
                    //entityWrapper.where("outer_order_id={0} and status=0", mainId);
                    //int count = erpOrderService.selectCount(entityWrapper);
                    List<MallSubOrderDO> erpOrderList = mallSubOrderService.selectByOrderNo(outerOrder.getOrderNo());
                    int totalCount = erpOrderList.size();
                    int initCount = 0;
                    int sentCount = 0;
                    int closeCount = 0;
                    if(totalCount > 0) {
                        for(int i=0; i<totalCount; i++) {
                            switch(erpOrderList.get(i).getStatus()) {
                                case 0:
                                    initCount++;
                                    break;
                                case 2:
                                    sentCount++;
                                    break;
                                case -1:
                                    closeCount++;
                                    break;
                            }
                        }
                    }
                    if(closeCount == totalCount) {	//主订单关闭
                        outerOrder.setStatus((byte) OrderStatus.CLOSE.getCode());
                    } else if((closeCount+sentCount) == totalCount) {	//全部发货
                        outerOrder.setStatus((byte) OrderStatus.SENT.getCode());
                    } else if(sentCount > 0) {	//部分发货
                        outerOrder.setStatus((byte) OrderStatus.PART_SENT.getCode());
                    } else {
                        outerOrder.setStatus((byte) OrderStatus.INIT.getCode());
                    }
                    outerOrder.setGmtModify(new Date());
                    mallOrderService.updateById(outerOrder);
                }
            }
        }


    }

    @Override
    public Map<String, Set<Long>> batchDelivery(ShippingOrderDO shippingOrder) throws InventoryException {
        String erpOrderIds = shippingOrder.getMallOrders();
        String s = erpOrderIds.replace("&quot;", "\"");
        List<Long> erpOrderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>(){});
		/*if(shippingOrder.getIsBatch()==0 && erpOrderIdList.size()>1) {
			throw new ErpCommonException("此操作仅为一个子订单发货，而您选中了多个子订单！");
		}*/

        if(shippingOrder.getStatus() == null) {
            shippingOrder.setStatus((byte) ShippingOrderStatus.INIT.getCode());
        }
        Date nowDate = new Date();
        Map<String, Set<Long>> resultMap = new HashMap<>();
        Set<Long> mainIds = Sets.newHashSet();
        Set<Long> shippingOrderIds = Sets.newHashSet();
        List<MallSubOrderDO>  erpOrderList = mallSubOrderService.selectBatchIds(erpOrderIdList);
        for(MallSubOrderDO erpOrder : erpOrderList) {
            if(erpOrder.getStockStatus()==StockUpStatus.STOCKUP.getCode() && erpOrder.getShippingNo()==null) {
                //扣减库存
                orderInventoryService.sendInventroyOrder(erpOrder);
                //修改子订单状态
                erpOrder.setStatus((byte) OrderStatus.SENT.getCode());
            } else if(erpOrder.getStockStatus()!=StockUpStatus.STOCKUP.getCode()) {
                throw new ErpCommonException("商品备货状态不对，子订单号：" + erpOrder.getOrderNo());
            } else {
                throw new ErpCommonException("商品不能重复发货，子订单号：" + erpOrder.getOrderNo());
            }
            shippingOrder.setMallOrders("[" + erpOrder.getId() + "]");
            shippingOrder.setReceiver(erpOrder.getReceiver());
            shippingOrder.setReceiverState(erpOrder.getReceiverState());
            shippingOrder.setReceiverCity(erpOrder.getReceiverCity());
            shippingOrder.setReceiverDistrict(erpOrder.getReceiverDistrict());
            shippingOrder.setIdCard(erpOrder.getIdCard());
            shippingOrder.setTelephone(erpOrder.getTelephone());
            shippingOrder.setPostcode(erpOrder.getPostcode());
            shippingOrder.setMemo(erpOrder.getMemo());
            shippingOrder.setAddress(erpOrder.getReceiverAddress());
            shippingOrder.setShippingNo(erpOrder.getOrderNo());
            shippingOrder.setShippingNo("PKG"+ DateUtil.formatDate(nowDate, DateUtil.DATE_PARTEN_YYMMDDHHMMSS)+sequenceUtilMapperExt.gainPKGSequence());
            if(erpOrder.getWeight() != null) {
                double skuWeight = erpOrder.getWeight()*erpOrder.getQuantity();
                shippingOrder.setSkuWeight(skuWeight);	//包裹里面的sku的重量(磅)
                double totalWeight = 0D;
                if(shippingOrder.getSkuWeight()+0.33 < 1) {	//不足1磅的按1磅计
                    totalWeight = 1;
                } else {
                    totalWeight = Math.ceil((shippingOrder.getSkuWeight()+0.33)*10);
                    totalWeight = totalWeight/10;
                }

                if(shippingOrder.getLogisticCompany().equals("邮客")) {
                    if(shippingOrder.getType() == 6) {	//邮客食品线
                        shippingOrder.setFreight(NumberUtil.formatDouble2(totalWeight*2.2));
                    } else if(shippingOrder.getType() == 7) {	//邮客奶粉线(包邮)
                        shippingOrder.setFreight(0D);
                    } else {
                        shippingOrder.setFreight(NumberUtil.formatDouble2(totalWeight*3.5));
                    }
                } else if(shippingOrder.getLogisticCompany().equals("运通快递") && shippingOrder.getType()==4) {
                    shippingOrder.setFreight(NumberUtil.formatDouble2(totalWeight*3.7));
                } else if(shippingOrder.getLogisticCompany().equals("运通快递") && shippingOrder.getType()==5) {
                    shippingOrder.setFreight(NumberUtil.formatDouble2(totalWeight*4.2));
                }
            }
            shippingOrder.setGmtCreate(nowDate);
            shippingOrder.setGmtModify(nowDate);
            shippingOrderMapper.insert(shippingOrder);

            //对接邮客、运通快递
            if(shippingOrder.getLogisticCompany()!=null && (shippingOrder.getLogisticCompany().equals("邮客") || shippingOrder.getLogisticCompany().equals("运通快递") || shippingOrder.getLogisticCompany().equals("4PX") || shippingOrder.getLogisticCompany().equals("联邦转运"))) {
                shippingOrderIds.add(shippingOrder.getId());
            }

            erpOrder.setOrderNo(shippingOrder.getShippingNo());
            erpOrder.setShippingNo(shippingOrder.getShippingNo());
            mainIds.add(erpOrder.getId());
        }

        // 通知渠道发货
        try {
            MallOrderDO outerOrder = mallOrderService.selectByOrderNo(erpOrderList.get(0).getOrderNo());
            ChannelFactory
                    .getChannel(ShiroUtil.getShiroUser().getCompanyNo(),
                            ChannelType.getChannelType(outerOrder.getPayType()))
                    .syncLogisticsOnlineConfirm(erpOrderList, shippingOrder);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mallSubOrderService.updateBatchById(erpOrderList);

        resultMap.put("mainIds", mainIds);
        resultMap.put("shippingOrderIds", shippingOrderIds);
        return resultMap;
    }

    @Override
    public void update(ShippingOrderDO shippingOrder) {

    }

    @Override
    public List<LogisticCompanyDO> queryLogisticCompany() {
        List<LogisticCompanyDO> LogisticCompany = logisticCompanyMapper.selectList();
        return LogisticCompany;
    }

    @Override
    public List<ShippingOrderDO> selectBatchIds(List<Long> shippingOrderIdList) {
        return shippingOrderMapper.selectBatchIds(shippingOrderIdList);
    }

    @Override
    public List<ShippingOrderDO> queryByShippingOrderPackageTime(ShippingOrderVO shippingOrderQueryVO) {
        return shippingOrderMapper.queryByShippingOrderPackageTime(shippingOrderQueryVO);
    }

    @Override
    public ShippingOrderDO selectByShippingNO(String str) {
        return shippingOrderMapper.selectByShippingNo(str);
    }

    @Override
    public int selectCount(String idCard, String logisticCompany) {
        return shippingOrderMapper.selectCount(idCard,logisticCompany);
    }

    @Override
    public ShippingOrderDO selectById(Long shippingOrderId) {
        return shippingOrderMapper.selectByPrimaryKey(shippingOrderId);
    }

}
