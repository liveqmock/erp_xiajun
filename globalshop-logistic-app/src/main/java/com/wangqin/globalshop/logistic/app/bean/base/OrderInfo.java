package com.wangqin.globalshop.logistic.app.bean.base;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
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
