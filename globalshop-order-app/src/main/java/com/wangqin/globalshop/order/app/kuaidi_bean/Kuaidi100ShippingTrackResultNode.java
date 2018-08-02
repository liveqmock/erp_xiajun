package com.wangqin.globalshop.order.app.kuaidi_bean;

import lombok.Data;

/**
 * 快递100物流轨迹节点信息，形如：
 * <pre>
 * {
 *  "time":"2018-05-15 15:39:04",
 *  "ftime":"2018-05-15 15:39:04",
 *  "context":"[江西主城区公司赣州市政府服务部]快件已被 本人 签收"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/7/25
 */
@Data
public class Kuaidi100ShippingTrackResultNode {

    private String time;

    private String ftime;

    /**
     * 物流信息
     */
    private String context;
}
