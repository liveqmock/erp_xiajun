package com.wangqin.globalshop.biz1.app.dal.mapperExt;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShengpayPayDO;
import com.wangqin.globalshop.biz1.app.dal.mapper.ShengpayPayDOMapper;

/**
 * 盛付通支付单表（shengpay_pay）对应的 mapper
 *
 * @author angus
 * @date 2018/8/20
 */
public interface ShengpayPayDOMapperExt extends ShengpayPayDOMapper {
    /**
     * 根据商户订单号从 shengpay_pay 表中获取对应的盛付通订单号
     *
     * @param merchantOrderNo 商户订单号
     * @return 盛付通订单号
     */
    String getSftOrderNo(String merchantOrderNo);

    /**
     * 根据商户订单号更新支付订单信息
     *
     * @param shengpayPayDO
     * @return
     */
    int updateByMerchantOrderNo(ShengpayPayDO shengpayPayDO);
}
