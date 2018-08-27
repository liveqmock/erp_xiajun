package com.wangqin.globalshop.logistic.app.bean.ceb;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * 入库明细单业务节点：
 * 放置入库明细单单业务的相关信息
 *
 * @author angus
 * @date 2018/8/23
 */
@Data
@Builder
@XStreamAlias("Delivery")
public class Delivery {

    @XStreamAlias("DeliveryHead")
    private DeliveryHead deliveryHead;

    @XStreamAlias("DeliveryList")
    private DeliveryList deliveryList;
}
