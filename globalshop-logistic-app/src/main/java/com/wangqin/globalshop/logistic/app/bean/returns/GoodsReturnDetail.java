package com.wangqin.globalshop.logistic.app.bean.returns;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * 退单表体对象
 *
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
@XStreamAlias("goodReturnDetail")
public class GoodsReturnDetail {

    /**
     * 商品序号
     * 必填：Y
     */
    private Integer goodsOrder;

    /**
     * 行邮税号
     * 必填：Y <br>
     * 必须已备案，且与 参数说明文档中的行邮税号 中的税号一致
     */
    private String codeTs;

    /**
     * 商品货号
     * 必填：N <br>
     * 保税进口业务，货号需与电子账册一致（保税进口时必填）
     */
    private String goodsItemNo;

    /**
     * 物品名称
     * 必填：Y
     */
    private String goodsName;

    /**
     * 申报数量
     * 必填：Y
     */
    private Double declareCount;
}
