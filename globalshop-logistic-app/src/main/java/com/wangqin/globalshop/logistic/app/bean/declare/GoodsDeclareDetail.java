package com.wangqin.globalshop.logistic.app.bean.declare;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * 清单表体信息
 *
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
@XStreamAlias("goodsDeclareDetail")
public class GoodsDeclareDetail {

    /**
     * 序号
     * 必填：Y <br>
     * 只能有数字，外网自动生成大于0小于50
     */
    private Integer goodsOrder;

    /**
     * 商品编码
     * 必填：Y <br>
     * 填写商品对应的HS编码
     */
    private String codeTs;

    /**
     * 企业商品货号
     * 必填：N
     */
    private String goodsItemNo;

    /**
     * 账册备案料号
     * 必填：N <br>
     * 保税必填，与仓储企业备案的电子账册中料号数据一致
     */
    private String itemRecordNo;

    /**
     * 企业商品品名
     * 必填：N
     */
    private String itemName;

    /**
     * 商品名称
     * 必填：Y
     */
    private String goodsName;

    /**
     * 商品规格型号
     * 必填：Y <br>
     * 有数字和字母或者中文
     */
    private String goodsModel;

    /**
     * 原产国（地区）
     * 必填：Y <br>
     * 参照国别代码表(COUNTRY)
     */
    private String originCountry;

    /**
     * 币制
     * 必填：Y <br>
     * 参照币制代码表(CURR)
     */
    private String tradeCurr;

    /**
     * 成交总价
     * 必填：N <br>
     * 与申报总价一致
     */
    private Double tradeTotal;

    /**
     * 单价
     * 必填：Y <br>
     * 只能是数字
     */
    private Double declPrice;

    /**
     * 总价
     * 必填：Y <br>
     * 申报数量乘以申报单价
     */
    private Double declTotalPrice;

    /**
     * 用途
     * 必填：N <br>
     * 08-赠品、10-试用装
     */
    private String useTo;

    /**
     * 数量
     * 必填：Y <br>
     * 只能是数字，大于0
     */
    private Double declareCount;

    /**
     * 计量单位
     * 必填：Y <br>
     * 参照计量单位代码表(UNIT)
     */
    private String goodsUnit;

    /**
     * 商品毛重
     * 必填：N <br>
     * 只能是数字
     */
    private Double goodsGrossWeight;

    /**
     * 法定计量单位
     * 必填：Y <br>
     * 填写商品HS编码对应的第一单位
     */
    private String firstUnit;

    /**
     * 法定数量
     * 必填：Y <br>
     * 根据第一单位填写对应数量
     */
    private Double firstCount;

    /**
     * 第二计量单位
     * 必填：N <br>
     * 填写商品HS编码对应的第二单位
     */
    private String secondUnit;

    /**
     * 第二数量
     * 必填：N <br>
     * 根据第二单位填写对应数量
     */
    private Double secondCount;

    /**
     * 产品国检备案编号
     * 必填：Y <br>
     * 通过向国检备案获取
     */
    private String productRecordNo;

    /**
     * 商品网址
     * 必填：N
     */
    private String webSite;

    /**
     * 条形码
     * 必填：N
     */
    private String barCode;

    /**
     * 备注
     * 必填：N
     */
    private String note;
}
