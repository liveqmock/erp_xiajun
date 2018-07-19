package com.wangqin.globalshop.biz1.app.dal.mapperExt;


import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ShippingOrderDOMapper;
import com.wangqin.globalshop.biz1.app.vo.ShippingOrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author biscuits
 * @date 2018/5/24
 */

public interface IShippingOrderMapperExt extends ShippingOrderDOMapper {
    List<ShippingOrderDO> queryShippingOrders(ShippingOrderVO shippingOrderVO);

    Integer queryShippingOrderCount(ShippingOrderVO shippingOrderVO);

    List<ShippingOrderDO> selectBatchIds(@Param("shippingOrderIdList") List<Long> shippingOrderIdList);

    List<ShippingOrderDO> queryByShippingOrderPackageTime(ShippingOrderVO shippingOrderQueryVO);

    ShippingOrderDO selectByShippingNo(String shippingNO);

    int selectCount(@Param("idCard") String idCard,@Param("logisticCompany") String logisticCompany);

    ShippingOrderDO selectByLogisticNo(ShippingOrderDO order);

    void updateStatusByShippingNo(String logisticNo);

    List<ShippingOrderDO> selectByLogisticNoIsNotNull();

    List<ShippingOrderDO> selectInOneMonth();

    List<ShippingOrderDO> listByLogisticCompany(String logistCompany);
}
