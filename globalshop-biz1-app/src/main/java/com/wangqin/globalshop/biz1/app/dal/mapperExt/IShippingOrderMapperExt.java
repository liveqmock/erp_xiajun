package com.wangqin.globalshop.biz1.app.dal.mapperExt;


import com.wangqin.globalshop.biz1.app.bean.dataVo.PageQueryParam;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingOrderQueryVO;
import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ShippingOrderDOMapper;
import com.wangqin.globalshop.biz1.app.bean.dataVo.ShippingOrderVO;
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

    ShippingOrderDO selectByShippingNo(String shippingNo);

    int selectCount(@Param("idCard") String idCard, @Param("logisticCompany") String logisticCompany);

    ShippingOrderDO selectByLogisticNo(String logisticNo);

    void updateStatusByShippingNo(String logisticNo);

    List<ShippingOrderDO> selectByLogisticNoIsNotNull();

    List<ShippingOrderDO> selectInOneMonth();

    List<ShippingOrderDO> listByLogisticCompany(String logistCompany);

    /**
     * 根据指定条件分页查询发货单列表
     *
     * @param shippingOrderQueryVO
     * @param pageQueryParam
     * @return
     */
    List<ShippingOrderDO> listShippingOrders(@Param("shippingOrderQueryVO") ShippingOrderQueryVO shippingOrderQueryVO,
                                             @Param("pageQueryParam") PageQueryParam pageQueryParam);

    /**
     * 根据指定条件查询发货单数目
     *
     * @param shippingOrderQueryVO
     * @return
     */
    int countShippingOrders(@Param("shippingOrderQueryVO") ShippingOrderQueryVO shippingOrderQueryVO);
}
