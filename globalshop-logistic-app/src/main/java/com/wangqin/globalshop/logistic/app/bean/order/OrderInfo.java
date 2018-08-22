package com.wangqin.globalshop.logistic.app.bean.order;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wangqin.globalshop.logistic.app.bean.common.JkfSign;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 订单对象
 *
 * @author angus
 * @date 2018/8/21
 */

@Data
@Builder
@XStreamAlias("orderInfo")
public class OrderInfo {
    @XStreamAlias("jkfSign")
    private JkfSign jkfSign;

    @XStreamAlias("jkfOrderImportHead")
    private JkfOrderImportHead jkfOrderImportHead;

    @XStreamAlias("jkfOrderDetailList")
    private List<JkfOrderDetail> jkfOrderDetailList;

    @XStreamAlias("jkfGoodsPurchaser")
    private JkfGoodsPurchaser jkfGoodsPurchaser;
}
