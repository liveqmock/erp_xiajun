package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallSubOrderDO;
import com.wangqin.globalshop.biz1.app.dal.dataVo.MallSubOrderVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallSubOrderDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;

import java.util.List;

public interface MallSubOrderDOMapperExt extends MallSubOrderDOMapper{
    int deleteByPrimaryKey(Long id);

    int insertSelective(MallSubOrderDO record);

    MallSubOrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallSubOrderDO record);

    int updateByPrimaryKey(MallSubOrderDO record);

    List<MallSubOrderDO> selectList(MallSubOrderDO order);

    List<MallSubOrderDO> selectBatchIds(List<Long> batchIds);

    List<MallSubOrderDO> queryByShippingOrder(ShippingOrderVO shippingOrderQueryVO);

    int selectCount(MallSubOrderDO erpOrderQuery);

    List<MallSubOrderDO> selectUnClosedByOrderNo(String orderNo);

    Integer queryErpOrdersCount(MallSubOrderVO erpOrderQueryVO);

    List<MallSubOrderDO> queryErpOrders(MallSubOrderVO erpOrderQueryVO);

    List<MallSubOrderDO> selectByOrderNo(String orderNo);

    List<MallSubOrderDO> queryErpOrderForExcel(MallSubOrderVO erpOrderQueryVO);
}