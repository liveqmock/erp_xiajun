package com.wangqin.globalshop.logistic.app.bean.base;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * 订单表体信息
 *
 * @author angus
 * @date 2018/8/20
 */
@Data
@Builder
@XStreamAlias("jkfOrderDetail")
public class JkfOrderDetail {
    /**
     * 商品序号
     * 必填：Y <br>
     * 商品序号,序号不大于50
     */
    private Integer goodsOrder;

    /**
     * 物品名称
     * 必填：Y <br>
     */
    private String goodsName;

    /**
     * 商品HS编码
     * 必填：Y <br>
     * 填写商品对应的HS编码
     */
    private String codeTs;

    /**
     * 商品规格、型号
     * 必填：N
     */
    private String goodsModel;

    /**
     * 产销国
     * 必填：Y <br>
     * 见参数表
     */
    private String originCountry;

    /**
     * 成交单价
     * 必填：Y <br>
     * 各商品成交单价 * 成交数量总和应等于表头的货款、成交总价
     */
    private Double unitPrice;

    /**
     * 币制
     * 必填：Y <br>
     * 限定为人民币，填写“142”
     */
    private String currency;

    /**
     * 成交数量
     * 必填：Y
     */
    private Double goodsCount;

    /**
     * 成交计量单位
     * 必填：Y <br>
     * 见参数表
     */
    private String goodsUnit;

    /**
     * 商品毛重
     * 必填：N
     */
    private Double grossWeight;

    /**
     * 条形码
     * 必填：N <br>
     * 国际通用的商品条形码，一般由前缀部分、制造厂商代码、商品代码和校验码组成。
     */
    private String barCode;

    /**
     * 备注
     * 必填：N <br>
     * 促销活动，商品单价偏离市场价格的，可以在此说明
     */
    private String note;
}
