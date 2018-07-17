package com.wangqin.globalshop.order.app.service.shipping.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Sets;
import com.wangqin.globalshop.biz1.app.constants.enums.ChannelType;
import com.wangqin.globalshop.biz1.app.constants.enums.OrderStatus;
import com.wangqin.globalshop.biz1.app.constants.enums.TransferStatus;
import com.wangqin.globalshop.biz1.app.dal.dataObject.*;
import com.wangqin.globalshop.biz1.app.dal.dataSo.ChannelAccountSo;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.IShippingOrderMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.LogisticCompanyDOMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.MallSubOrderMapperExt;
import com.wangqin.globalshop.biz1.app.dal.mapperExt.SequenceUtilMapperExt;
import com.wangqin.globalshop.biz1.app.dto.MultiDeliveryFormDTO;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;
import com.wangqin.globalshop.channel.service.channel.ChannelFactory;
import com.wangqin.globalshop.channel.service.channelAccount.IChannelAccountService;
import com.wangqin.globalshop.common.enums.StockUpStatus;
import com.wangqin.globalshop.common.exception.ErpCommonException;
import com.wangqin.globalshop.common.exception.InventoryException;
import com.wangqin.globalshop.common.utils.*;
import com.wangqin.globalshop.common.utils.czh.Util;
import com.wangqin.globalshop.inventory.app.service.InventoryService;
import com.wangqin.globalshop.order.app.service.mall.IMallOrderService;
import com.wangqin.globalshop.order.app.service.mall.IMallSubOrderService;
import com.wangqin.globalshop.order.app.service.shipping.IShippingOrderService;
import com.wangqin.globalshop.order.app.service.util_service.OrderISequenceUtilService;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.wangqin.globalshop.order.app.comm.Constant.*;

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
    private InventoryService inventoryService;
    @Autowired
    private SequenceUtilMapperExt sequenceUtilMapperExt;
    @Autowired
    private LogisticCompanyDOMapperExt logisticCompanyMapper;
    @Autowired
    private IChannelAccountService iChannelAccountService;
    @Autowired
    private OrderISequenceUtilService sequenceUtilService;

    @Override
    public List<ShippingOrderDO> queryShippingOrders(ShippingOrderVO shippingOrderVO) {
        shippingOrderVO.init();
        List<ShippingOrderDO> shippingOrders = shippingOrderMapper.queryShippingOrders(shippingOrderVO);
        return shippingOrders;
    }

    @Override
    public MultiDeliveryFormDTO queryByOrderId(String orderId) {
        MultiDeliveryFormDTO multiDeliveryFormDTO = new MultiDeliveryFormDTO();

        String s = orderId.replace("&quot;", "\"");
        List<Long> erpOrderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
        });
        List<MallSubOrderDO> mallSubOrderList = mallSubOrderMapper.queryByOrderId(erpOrderIdList);
        String receiver = null;
        String telephone = null;
        String addressDetail = null;
        Set<Long> erpOrderIdSet = Sets.newHashSet();

        Double skuWeight = 0D;
        Double totalSalePrice = 0D;
        for (MallSubOrderDO mallSubOrder : mallSubOrderList) {
//            if (mallSubOrder.getStockStatus() == null || (mallSubOrder.getStockStatus() != StockUpStatus.STOCKUP.getCode() && mallSubOrder.getStockStatus() != StockUpStatus.PREPARE.getCode())) {
//                throw new ErpCommonException("商品备货状态不对，子订单号：" + mallSubOrder.getId());
//            }
        	mallSubOrder.setSkuPic(ImgUtil.initImg2Json(mallSubOrder.getSkuPic()));
            if (StringUtils.isBlank(mallSubOrder.getReceiver()) || StringUtils.isBlank(mallSubOrder.getTelephone()) || StringUtils.isBlank(mallSubOrder.getReceiverState()) || StringUtils.isBlank(mallSubOrder.getReceiverCity()) || StringUtils.isBlank(mallSubOrder.getReceiverDistrict())) {
                throw new ErpCommonException("收货人地址不能为空：" + mallSubOrder.getId());
            }
            if (mallSubOrder.getWeight() != null) {
                skuWeight += mallSubOrder.getWeight() * mallSubOrder.getQuantity();
            }
            totalSalePrice += mallSubOrder.getSalePrice() * mallSubOrder.getQuantity();
            if (StringUtils.isBlank(receiver)) {
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
            } else if (!receiver.equals(mallSubOrder.getReceiver()) || !telephone.equals(mallSubOrder.getTelephone()) || !addressDetail.equals(mallSubOrder.getReceiverAddress())) {
                multiDeliveryFormDTO.setInfo("子订单收货信息不一致，请注意核对！");
            }
            if (erpOrderIdSet.contains(mallSubOrder.getId())) {
                continue;
            }

//            //搜索同一收货人的子订单
//            MallSubOrderDO tjErpOrder = new MallSubOrderDO();
//            tjErpOrder.setReceiver(mallSubOrder.getReceiver());
//            tjErpOrder.setTelephone(mallSubOrder.getTelephone());
//            tjErpOrder.setReceiverState(mallSubOrder.getReceiverState());
//            tjErpOrder.setReceiverCity(mallSubOrder.getReceiverCity());
//            tjErpOrder.setReceiverDistrict(mallSubOrder.getReceiverDistrict());
//            tjErpOrder.setStatus(0); // 订单状态：新建
//            // tjErpOrder.setStockStatus(erpOrder.getStockStatus()); //备货状态：已备货
//            // tjErpOrder.setWarehouseId(erpOrder.getWarehouseId()); //相同仓库
//            List<MallSubOrderDO> selErpOrderList = mallSubOrderService.selectByOrderNo(mallSubOrder.getOrderNo());
//            for (int i = 0; i < selErpOrderList.size(); i++) {
//                MallSubOrderDO selErpOrder = selErpOrderList.get(i);
//                //不在同一仓库的情况，不用考虑
//                if (selErpOrder.getWarehouseNo() != null && selErpOrder.getWarehouseNo() != mallSubOrder.getWarehouseNo()) {
//                    continue;
//                }
//                //在同一仓库的或者未备货的
//                if (!erpOrderIdSet.contains(selErpOrder.getId())) {
//                    String positionNoStr = mallSubOrderMapper.selectPositionNoByOrderId(selErpOrder.getOrderNo());
//                    // TODO: 18.5.28 Biscuits
//                    /* selErpOrder.setPositionNo(positionNoStr);*/
//                    totalMallSubOrderList.add(selErpOrder);
//                    erpOrderIdSet.add(selErpOrder.getId());
//                }
//            }
        }
//        Collections.sort(totalMallSubOrderList, new Comparator<MallSubOrderDO>() {
//            @Override
//            public int compare(MallSubOrderDO o1, MallSubOrderDO o2) {
//                return o2.getStockStatus().compareTo(o1.getStockStatus());
//            }
//
//        });
        multiDeliveryFormDTO.setSkuWeight(skuWeight);
        multiDeliveryFormDTO.setTotalSalePrice(totalSalePrice);
        multiDeliveryFormDTO.setMallSubOrderList(mallSubOrderList);
        return multiDeliveryFormDTO;
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public Set<String> multiDelivery(ShippingOrderDO shippingOrder) throws ErpCommonException {
        String erpOrderIds = shippingOrder.getMallOrders();
        StringBuffer erpNos = new StringBuffer();
        String s = erpOrderIds.replace("&quot;", "\"");
        List<Long> erpOrderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
        });

        List<MallSubOrderDO> erpOrderList = mallSubOrderService.selectBatchIds(erpOrderIdList);
        double skuWeight = 0.0D;
        for (MallSubOrderDO erpOrder : erpOrderList) {
            if ((erpOrder.getStockStatus() == StockUpStatus.STOCKUP.getCode() || erpOrder.getStockStatus() == StockUpStatus.PREPARE.getCode()) && erpOrder.getShippingNo() == null) {
                //拼接erp_no
                if (erpNos.length() < 1) {
                    erpNos.append(erpOrder.getShopCode());
                } else {
                    erpNos.append("," + erpOrder.getShopCode());
                }
                //扣减库存
                inventoryService.ship(erpOrder);
                //修改子订单状态
                erpOrder.setStatus(OrderStatus.SENT.getCode());
            } else if (erpOrder.getStockStatus() != StockUpStatus.STOCKUP.getCode()) {
                throw new ErpCommonException("商品备货状态不对，子订单号：" + erpOrder.getShopCode());
            } else {
                throw new ErpCommonException("商品不能重复发货，子订单号：" + erpOrder.getShopCode());
            }
            if (erpOrder.getWeight() != null) {
                skuWeight += erpOrder.getWeight() * erpOrder.getQuantity();
            }
        }
        if (skuWeight > 0) {
            shippingOrder.setSkuWeight(skuWeight);    //包裹里面的sku的重量(磅)
            double totalWeight = 0D;
            if (shippingOrder.getSkuWeight() + 0.33 < 1) {    //不足1磅的按1磅计
                totalWeight = 1;
            } else {
                totalWeight = Math.ceil((shippingOrder.getSkuWeight() + 0.33) * 10);
                totalWeight = totalWeight / 10;
            }
            if ("邮客".equals(shippingOrder.getLogisticCompany())) {
                if (shippingOrder.getType() == 6) {    //邮客食品线
                    shippingOrder.setFreight(NumberUtil.formatDouble2(totalWeight * 2.2));
                } else if (shippingOrder.getType() == 7) {    //邮客奶粉线(包邮)
                    shippingOrder.setFreight(0D);
                } else {
                    shippingOrder.setFreight(NumberUtil.formatDouble2(totalWeight * 3.5));
                }
            } else if ("运通快递".equals(shippingOrder.getLogisticCompany()) && shippingOrder.getType() == 4) {
                shippingOrder.setFreight(NumberUtil.formatDouble2(totalWeight * 3.7));
            } else if ("运通快递".equals(shippingOrder.getLogisticCompany()) && shippingOrder.getType() == 5) {
                shippingOrder.setFreight(NumberUtil.formatDouble2(totalWeight * 4.2));
            }
        }
        if (shippingOrder.getStatus() == null) {
            shippingOrder.setStatus(SHIP_INIT);
        }
        shippingOrder.setShippingNo(erpNos.toString());
        shippingOrder.setShippingNo("PKG" + DateUtil.formatDate(new Date(), DateUtil.DATE_PARTEN_YYMMDDHHMMSS) + sequenceUtilService.gainPKGSequence());
        shippingOrder.setGmtCreate(new Date());
        shippingOrder.setGmtModify(new Date());
        if (shippingOrder.getTransferStatus() == null) {
            shippingOrder.setTransferStatus(TransferStatus.UNPREDICT.getValue());
        }
        LogisticCompanyDO comp = logisticCompanyMapper.selectByCode(shippingOrder.getLogisticCompany());
//        // 国内物流，需要订阅快递100
//        if (comp.getType() == 0) {
//            shippingOrder.setSubscribeKuaidi100(Kuaidi100Status.Need_Subscribe.getValue());
//        }
        shippingOrderMapper.insert(shippingOrder);

        Set<String> mainIds = Sets.newHashSet();
        for (MallSubOrderDO erpOrder : erpOrderList) {
            erpOrder.setShippingNo(shippingOrder.getShippingNo());
            erpOrder.setShippingNo(shippingOrder.getShippingNo());
            mainIds.add(erpOrder.getOrderNo());
        }
        mallSubOrderService.updateBatchById(erpOrderList);

        // 有赞
        // 查出outer_order
        MallOrderDO outerOrder = mallOrderService.selectByOrderNo(erpOrderList.get(0).getOrderNo());
        // 通知渠道发货
        ChannelAccountSo so = new ChannelAccountSo();
        so.setShopCode(outerOrder.getShopCode());
        so.setCompanyNo(outerOrder.getCompanyNo());
        so.setChannelNo(outerOrder.getChannelNo());

        ChannelAccountDO accountDO = iChannelAccountService.queryPo(so);
        try {
            ChannelFactory
                    .getChannel(accountDO).syncLogisticsOnlineConfirm(erpOrderList, shippingOrder);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return mainIds;
    }


    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public List<MallSubOrderDO> queryShippingOrderDetail(String mallOrders) {
        if (StringUtil.isBlank(mallOrders)) {
            throw new ErpCommonException("子订单号为空");
        }
        String s = mallOrders.replace("&quot;", "\"");
        List<Long> erpOrderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
        });
        List<MallSubOrderDO> ErpOrderList = mallSubOrderMapper.queryByOrderId(erpOrderIdList);
        ErpOrderList.forEach(erpOrder -> {
            //在同一仓库的或者未备货的
            String positionNoStr = mallSubOrderMapper.selectPositionNoByOrderId(erpOrder.getOrderNo());
            // TODO: 18.5.28 Biscuits
//            erpOrder.setPositionNo(positionNoStr);
        });
        return ErpOrderList;
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void updateOuterstatus(Set<String> mainIds) {
        for (String mainId : mainIds) {
            if (mainId != null) {
                MallOrderDO outerOrder = mallOrderService.selectByOrderNo(mainId);
                if (outerOrder != null) {
                    //entityWrapper.where("outer_order_id={0} and status=0", mainId);
                    //int count = erpOrderService.selectCount(entityWrapper);
                    List<MallSubOrderDO> erpOrderList = mallSubOrderService.selectByOrderNo(outerOrder.getOrderNo());
                    int totalCount = erpOrderList.size();
                    int initCount = 0;
                    int sentCount = 0;
                    int closeCount = 0;
                    if (totalCount > 0) {
                        for (int i = 0; i < totalCount; i++) {
                            switch (erpOrderList.get(i).getStatus()) {
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
                    if (closeCount == totalCount) {    //主订单关闭
                        outerOrder.setStatus(OrderStatus.CLOSE.getCode());
                    } else if ((closeCount + sentCount) == totalCount) {    //全部发货
                        outerOrder.setStatus(OrderStatus.SENT.getCode());
                    } else if (sentCount > 0) {    //部分发货
                        outerOrder.setStatus(OrderStatus.PART_SENT.getCode());
                    } else {
                        outerOrder.setStatus(OrderStatus.INIT.getCode());
                    }
                    outerOrder.setGmtModify(new Date());
                    mallOrderService.updateById(outerOrder);
                }
            }
        }


    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public Map<String, Set<String>> batchDelivery(ShippingOrderDO shippingOrder) throws InventoryException {
        String erpOrderIds = shippingOrder.getMallOrders();
        String s = erpOrderIds.replace("&quot;", "\"");
        List<Long> erpOrderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
        });
        /*if(shippingOrder.getIsBatch()==0 && erpOrderIdList.size()>1) {
            throw new ErpCommonException("此操作仅为一个子订单发货，而您选中了多个子订单！");
		}*/

        if (shippingOrder.getStatus() == null) {
            shippingOrder.setStatus(SHIP_INIT);
        }
        Date nowDate = new Date();
        Map<String, Set<String>> resultMap = new HashMap<>();
        Set<String> mainIds = Sets.newHashSet();
        Set<String> shippingOrderIds = Sets.newHashSet();
        List<MallSubOrderDO> erpOrderList = mallSubOrderService.selectBatchIds(erpOrderIdList);
        for (MallSubOrderDO erpOrder : erpOrderList) {
//            if(erpOrder.getStockStatus()==StockUpStatus.STOCKUP.getCode() && erpOrder.getShippingNo()==null) {
//                //扣减库存
//                orderInventoryService.sendInventroyOrder(erpOrder);
//                //修改子订单状态
//                erpOrder.setStatus(OrderStatus.SENT.getCode());
//            } else if(erpOrder.getStockStatus()!=StockUpStatus.STOCKUP.getCode()) {
//                throw new ErpCommonException("商品备货状态不对，子订单号：" + erpOrder.getOrderNo());
//            } else {
//                throw new ErpCommonException("商品不能重复发货，子订单号：" + erpOrder.getOrderNo());
//            }
            inventoryService.ship(erpOrder);
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
            shippingOrder.setShippingNo("PKG" + DateUtil.formatDate(nowDate, DateUtil.DATE_PARTEN_YYMMDDHHMMSS) + sequenceUtilMapperExt.gainPKGSequence());
            if (erpOrder.getWeight() != null) {
                double skuWeight = erpOrder.getWeight() * erpOrder.getQuantity();
                shippingOrder.setSkuWeight(skuWeight);    //包裹里面的sku的重量(磅)
                double totalWeight = 0D;
                if (shippingOrder.getSkuWeight() + 0.33 < 1) {    //不足1磅的按1磅计
                    totalWeight = 1;
                } else {
                    totalWeight = Math.ceil((shippingOrder.getSkuWeight() + 0.33) * 10);
                    totalWeight = totalWeight / 10;
                }

                if ("邮客".equals(shippingOrder.getLogisticCompany())) {
                    if (shippingOrder.getType() == 6) {    //邮客食品线
                        shippingOrder.setFreight(NumberUtil.formatDouble2(totalWeight * 2.2));
                    } else if (shippingOrder.getType() == 7) {    //邮客奶粉线(包邮)
                        shippingOrder.setFreight(0D);
                    } else {
                        shippingOrder.setFreight(NumberUtil.formatDouble2(totalWeight * 3.5));
                    }
                } else if ("运通快递".equals(shippingOrder.getLogisticCompany()) && shippingOrder.getType() == 4) {
                    shippingOrder.setFreight(NumberUtil.formatDouble2(totalWeight * 3.7));
                } else if ("运通快递".equals(shippingOrder.getLogisticCompany()) && shippingOrder.getType() == 5) {
                    shippingOrder.setFreight(NumberUtil.formatDouble2(totalWeight * 4.2));
                }
            }
            shippingOrder.setGmtCreate(nowDate);
            shippingOrder.setGmtModify(nowDate);
            shippingOrderMapper.insert(shippingOrder);

            // 对接邮客、运通快递
            if (shippingOrder.getLogisticCompany() != null
                    && ("邮客".equals(shippingOrder.getLogisticCompany()) || "运通快递".equals(shippingOrder.getLogisticCompany())
                    || "4PX".equals(shippingOrder.getLogisticCompany())
                    || "联邦转运".equals(shippingOrder.getLogisticCompany()))) {
                shippingOrderIds.add(shippingOrder.getId().toString());
            }

            erpOrder.setOrderNo(shippingOrder.getShippingNo());
            erpOrder.setShippingNo(shippingOrder.getShippingNo());
            mainIds.add(erpOrder.getShippingNo());
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
    	shippingOrderMapper.updateByPrimaryKeySelective(shippingOrder);
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
        return shippingOrderMapper.selectCount(idCard, logisticCompany);
    }

    @Override
    public ShippingOrderDO selectById(Long shippingOrderId) {
        return shippingOrderMapper.selectByPrimaryKey(shippingOrderId);
    }

    @Override
    public ShippingOrderDO selectOne(ShippingOrderDO order) {
        return shippingOrderMapper.selectByLogisticNo(order);
    }

    @Override
    public void updateStatusByShippingNo(String logisticNo) {
        shippingOrderMapper.updateStatusByShippingNo(logisticNo);
    }

    @Override
    @Transactional(rollbackFor = ErpCommonException.class)
    public void ship(ShippingOrderDO shippingOrder) throws ErpCommonException {


    	String shippingNo = CodeGenUtil.getShippingNO(sequenceUtilService.gainPKGSequence());
        StringBuffer erpNos = new StringBuffer();
        String mallOrders = shippingOrder.getMallOrders();
        if (Util.isEmpty(mallOrders)){
            throw new ErpCommonException("无法获取子订单id");
        }
        String s = mallOrders.replace("&quot;", "\"");
        List<Long> erpOrderIdList = HaiJsonUtils.toBean(s, new TypeReference<List<Long>>() {
        });
        List<MallSubOrderDO> list = mallSubOrderService.selectBatchIds(erpOrderIdList);
//        if (list.size() > 1) {
//            throw new ErpCommonException("海狐的包裹只能包含一个商品且数量为1，请选择其他物流公司！");
//        }
//        if (list.get(0).getQuantity() > 1) {
//            throw new ErpCommonException("海狐的包裹只能包含一个商品且数量为1，请选择其他物流公司！");
//        }
//        if (org.apache.commons.lang.StringUtils.isEmpty(list.get(0).getIdCard())) {
//            throw new ErpCommonException("海狐物流发货单号缺少身份证信息");
//        }
//
//         //对接海狐
//         if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("海狐")) {
//         haihuService.createOrder(shippingOrder.getId());
//         }
//         //对接海狐联邦转运
//         if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("海狐联邦转运")) {
//         haihuService.returnPackageNo(shippingOrder);
//
//         }
//         //对接美国转运四方
//         if(shippingOrder.getLogisticCompany()!=null && shippingOrder.getLogisticCompany().equals("4PX")) {
//         siFangService.createOrder(shippingOrder.getId());
//         }
//
        for (MallSubOrderDO erpOrder : list) {
            /**如果没有订单号  则认为是没有发货*/
            if (Util.isEmpty(erpOrder.getShippingNo())) {
                /**判断实际库存是否满足发货条件*/
            	InventoryDO inventory = inventoryService.selectByItemCodeAndSkuCode(erpOrder.getItemCode(), erpOrder.getSkuCode());
            	if(inventory.getInv() <= 0) {
            		throw new ErpCommonException("实际库存不足");
            	}
            	
            	/**物流出库*/
            	
                erpOrder.setStatus(OrderStatus.SENT.getCode());
                erpOrder.setShippingNo(shippingNo);
                Map<InventoryOnWareHouseDO, Long> ship = inventoryService.ship(erpOrder);
                for (InventoryOnWareHouseDO inventoryOnWareHouseDO : ship.keySet()) {

                    erpOrder.setWarehouseNo(inventoryOnWareHouseDO.getWarehouseNo());
                }
                /**拼接erp*/
                mallSubOrderMapper.updateByPrimaryKey(erpOrder);
                erpNos.append(erpOrder.getShopCode()).append(",");
            } else {
                throw new ErpCommonException("不能重复发货");
            }
            /**更新主订单的状态*/
            MallOrderDO orderDO = mallOrderService.selectByOrderNo(erpOrder.getOrderNo());
            updateMallOrderStats(orderDO);
            mallOrderService.updateById(orderDO);

        }


        // TODO: 18.6.6 绑定物流
//        if(skuWeight > 0) {
//            shippingOrder.setSkuWeight(skuWeight);	//包裹里面的sku的重量(磅)
//            double totalWeight = 0D;
//            if(shippingOrder.getSkuWeight()+0.33 < 1) {	//不足1磅的按1磅计
//                totalWeight = 1;
//            } else {
//                totalWeight = Math.ceil((shippingOrder.getSkuWeight()+0.33)*10);
//                totalWeight = totalWeight/10;
//            }
//        }
        String erpStr = erpNos.toString();
        erpStr = erpStr.substring(0, erpStr.length() - 1);
        shippingOrder.init();
        shippingOrder.setStatus(SHIP_INIT);
        shippingOrder.setShippingNo(erpStr);
        shippingOrder.setShippingNo(shippingNo);
        shippingOrder.setTransferStatus(TransferStatus.UNPREDICT.getValue());
        shippingOrderMapper.insertSelective(shippingOrder);

//
//        // 有赞
//        // 查出outer_order
//        MallOrderDO outerOrder = mallOrderService.selectByOrderNo(erpOrderList.get(0).getOrderNo());
//        // 通知渠道发货
//        ChannelAccountSo so = new ChannelAccountSo();
//        so.setShopCode(outerOrder.getShopCode());
//        so.setCompanyNo(outerOrder.getCompanyNo());
//        so.setChannelNo(outerOrder.getChannelNo());
//
//        ChannelAccountDO accountDO = iChannelAccountService.queryPo(so);
//        try {
//            ChannelFactory
//                    .getChannel(accountDO).syncLogisticsOnlineConfirm(erpOrderList,shippingOrder);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }


    }

    @Override
    public List<ShippingOrderDO> selectByLogisticNoIsNotNull() {
        return shippingOrderMapper.selectByLogisticNoIsNotNull();
    }

    @Override
    public List<ShippingOrderDO> selectInOneMonth() {
        return shippingOrderMapper.selectInOneMonth();
    }

    private void updateMallOrderStats(MallOrderDO orderDO) {
        List<MallSubOrderDO> list = mallSubOrderMapper.selectByOrderNo(orderDO.getOrderNo());
        for (MallSubOrderDO aDo : list) {
            if (OrderStatus.SENT.getCode() != aDo.getStatus()){
                orderDO.setStatus(OrderStatus.PART_SENT.getCode());
                return;
            }
        }
        orderDO.setStatus(OrderStatus.SENT.getCode());

    }


}
