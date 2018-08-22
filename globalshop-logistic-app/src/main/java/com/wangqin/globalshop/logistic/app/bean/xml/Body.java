package com.wangqin.globalshop.logistic.app.bean.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wangqin.globalshop.logistic.app.bean.bill.BillResult;
import com.wangqin.globalshop.logistic.app.bean.common.JkfSign;
import com.wangqin.globalshop.logistic.app.bean.declare.GoodsDeclareModule;
import com.wangqin.globalshop.logistic.app.bean.declare.JkfGoodsDeclar;
import com.wangqin.globalshop.logistic.app.bean.order.OrderInfo;
import com.wangqin.globalshop.logistic.app.bean.result.JkfResult;
import com.wangqin.globalshop.logistic.app.bean.returns.GoodsReturnModule;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
public class Body {
    /**
     * 通用表体
     */
    @XStreamAlias("jkfSign")
    private JkfSign jkfSign;

    /**
     * 订单报文表体
     */
    @XStreamAlias("orderInfoList")
    private List<OrderInfo> orderInfoList;

    /**
     * 清单报文表体
     */
    @XStreamAlias("goodsDeclareModuleList")
    private List<GoodsDeclareModule> goodsDeclareModuleList;

    /**
     * 清单审批回执报文表体
     */
    @XStreamAlias("jkfGoodsDeclar")
    private JkfGoodsDeclar jkfGoodsDeclar;

    /**
     * 回执报文表体
     */
    @XStreamAlias("list")
    private List<JkfResult> list;

    /**
     * 进口运单出区回执报文表体
     */
    @XStreamAlias("billResultList")
    private List<BillResult> billResultList;

    /**
     * 退单对象集
     */
    @XStreamAlias("goodsReturnModuleList")
    private List<GoodsReturnModule> goodsReturnModuleList;
}
