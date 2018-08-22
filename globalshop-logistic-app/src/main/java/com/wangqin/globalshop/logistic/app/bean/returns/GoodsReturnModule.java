package com.wangqin.globalshop.logistic.app.bean.returns;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wangqin.globalshop.logistic.app.bean.common.JkfSign;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 退单对象
 *
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
@XStreamAlias("goodsReturnModule")
public class GoodsReturnModule {

    /**
     * 退单签名对象
     */
    @XStreamAlias("jkfSign")
    private JkfSign jkfSign;

    /**
     * 退单表头对象
     */
    @XStreamAlias("goodsReturn")
    private GoodsReturn goodsReturn;

    /**
     * 退单表体对象集
     */
    @XStreamAlias("goodsReturnDetails")
    private List<GoodsReturnDetail> goodsReturnDetails;
}
