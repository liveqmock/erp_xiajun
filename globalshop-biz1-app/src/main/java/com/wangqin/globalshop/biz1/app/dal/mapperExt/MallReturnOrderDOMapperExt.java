package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.MallReturnOrderDO;
import com.wangqin.globalshop.biz1.app.bean.dataVo.MallReturnOrderVO;
import com.wangqin.globalshop.biz1.app.dal.mapper.MallReturnOrderDOMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallReturnOrderDOMapperExt extends MallReturnOrderDOMapper {

	//首页数据看板：未完成售后订单数
	Integer sumReturningOrderNum(@Param("companyNo")String companyNo,@Param("statusList")List<Integer> statusList);
	
    List<MallReturnOrderDO> selectByCondition(@Param("orderNo") String orderNo,
                                              @Param("startGmtCreateDate") String startGmtCreateDate,
                                              @Param("endGmtCreateDate") String endGmtCreateDate,
                                              @Param("companyNo") String loginUserCompanyNo);

    List<MallReturnOrderDO> listMallReturnOrder(@Param("orderNo") String orderNo,
                                                @Param("startGmtCreateDate") String startGmtCreateDate,
                                                @Param("endGmtCreateDate") String endGmtCreateDate,
                                                @Param("companyNo") String loginUserCompanyNo);

    /**
     * 根据 mallReturnOrderNo 获取 MallReturnOrder
     *
     * @param mallReturnOrderNo
     * @return
     */
    MallReturnOrderDO getByMallReturnOrderNo(String mallReturnOrderNo);

    /**
     * 根据 mallReturnOrderNo 更新 MallReturnOrder
     *
     * @param erpReturnOrder
     */
    void updateByMallReturnOrderNo(MallReturnOrderVO erpReturnOrder);
}