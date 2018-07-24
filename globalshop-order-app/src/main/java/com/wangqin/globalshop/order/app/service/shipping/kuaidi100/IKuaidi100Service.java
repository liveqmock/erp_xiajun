package com.wangqin.globalshop.order.app.service.shipping.kuaidi100;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ShippingOrderDO;
import com.wangqin.globalshop.order.app.kuaidi_bean.NoticeResponse;
import com.wangqin.globalshop.order.app.kuaidi_bean.Result;

public interface IKuaidi100Service {
    /**
     * 查物流轨迹
     *
     * @param shippingNo 发货单号
     * @param com        物流公司代码
     * @param num        物流单号
     * @return
     */
    Result query(String shippingNo, String com, String num);

    /**
     * 查物流轨迹
     *
     * @param com 物流公司代码
     * @param num 物流单号
     * @return
     */
    Result query(String com, String num);

    /**
     * 查物流轨迹
     *
     * @param shippingNo 发货单号
     */
    Result query(String shippingNo);

    /**
     * 订阅物流100
     *
     * @param shippingNo erp打包号
     */
    void subscribe(String shippingNo);

    /**
     * 订阅物流100
     *
     * @param shippingOrder 包裹订单
     */
    void subscribe(ShippingOrderDO shippingOrder);

    /**
     * 查询物流
     *
     * @param logisticsNo 物流号
     */
    void fetchTrack(String logisticsNo);

    /**
     * 查询物流
     *
     * @param shippingNo erp打包号
     */
    void fetchTrackByShippingNo(String shippingNo);

    /**
     * 查询物流
     *
     * @param shippingOrder
     */
    void fetchTrackByShippingOrder(ShippingOrderDO shippingOrder);

    /**
     * 接收物流100回调
     *
     * @param json 内容
     */
    NoticeResponse handleCallback(String json);

    /**
     * 处理物流查询结果信息并更新数据库
     *
     * @param result
     */
    void handleTrackList(Result result);
}
