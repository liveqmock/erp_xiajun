package com.wangqin.globalshop.logistic.app.bean.declare;

import lombok.Builder;
import lombok.Data;

/**
 * 清单表头信息
 *
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
public class GoodsDeclare {
    /**
     * 账册编号
     * 必填：N <br>
     * 一线转入的对应账册编号，保税模式必填，直邮模式不填
     */
    private String accountBookNo;

    /**
     * 进出口标记
     * 必填：Y <br>
     * 必须为I
     */
    private Character ieFlag;

    /**
     * 预录入编号
     * 必填：Y <br>
     * 4位电商编号+14位企业流水，电商平台/物流企业生成后发送服务平台，
     * 与运单号一一对应，同个运单重新申报时，保持不变
     */
    private String preEntryNumber;

    /**
     * 监管方式
     * 必填：Y <br>
     * 填写0：对应9610直邮进口
     * 填写1：对应1210保税进口
     * 填写2：对应1239保税进口
     */
    private Character importType;

    /**
     * 进口日期
     * 必填：Y <br>
     * 格式：2014-02-18 20:33:33
     */
    private String inOutDateStr;

    /**
     * 进口口岸代码
     * 必填：Y <br>
     * 口岸代码表
     */
    private String iePort;

    /**
     * 指运港(抵运港)
     * 必填：Y <br>
     * 对应参数表
     */
    private String destinationPort;

    /**
     * 运输工具名称
     * 必填：N <br>
     * 包括字母和数字，可以填写中文，转关时填写@+16位转关单号
     */
    private String trafName;

    /**
     * 航班航次号
     * 必填：N <br>
     * 直邮必填,包括字母和数字，可以有中文
     */
    private String voyageNo;

    /**
     * 运输工具编号
     * 必填：N <br>
     * 直邮必填
     */
    private String trafNo;

    /**
     * 运输方式
     * 必填：Y <br>
     * 参照运输方式代码表(TRANSF)
     */
    private String trafMode;

    /**
     * 申报单位类别
     * 必填：Y <br>
     * 个人委托电商企业申报
     * 个人委托物流企业申报
     * 个人委托第三方申报
     */
    private String declareCompanyType;

    /**
     * 申报企业代码
     * 必填：Y <br>
     * 指委托申报单位代码，需在海关注册，具有报关资质
     */
    private String declareCompanyCode;

    /**
     * 申报企业名称
     * 必填：Y <br>
     * 指委托申报单位名称，需在海关注册，具有报关资质
     */
    private String declareCompanyName;

    /**
     * 电商平台名称
     * 必填：Y <br>
     * 电商平台在跨境电商通关服务平台的备案名称
     */
    private String companyName;

    /**
     * 电商平台代码
     * 必填：Y <br>
     * 电商平台在跨境电商通关服务的备案编号
     */
    private String companyCode;

    /**
     * 电商企业代码
     * 必填：Y <br>
     * 电商企业在跨境平台备案编码
     */
    private String eCommerceCode;

    /**
     * 电商企业名称
     * 必填：Y <br>
     * 电商企业在跨境平台备案的企业名称
     */
    private String eCommerceName;

    /**
     * 物流企业名称
     * 必填：Y <br>
     * 物流企业在跨境平台备案的企业名称
     */
    private String logisCompanyName;

    /**
     * 物流企业代码
     * 必填：Y <br>
     * 物流企业在跨境平台备案编码
     */
    private String logisCompanyCode;

    /**
     * 订单编号
     * 必填：Y
     */
    private String orderNo;

    /**
     * 物流运单编号
     * 必填：Y
     */
    private String wayBill;

    /**
     * 提运单号
     * 必填：N <br>
     * 直邮必填
     */
    private String billNo;

    /**
     * 启运国（地区）
     * 必填：Y <br>
     * 参照国别代码表(COUNTRY)
     */
    private String tradeCountry;

    /**
     * 件数
     * 必填：Y <br>
     * 表体独立包装的物品总数，不考虑物品计量单位，大于0
     */
    private Integer packNo;

    /**
     * 毛重（公斤）
     * 必填：Y <br>
     * 大于0
     */
    private Double grossWeight;

    /**
     * 净重（公斤）
     * 必填：Y <br>
     * 大于0
     */
    private Double netWeight;

    /**
     * 包装种类代码
     * 必填：N <br>
     * 参照包装种类代码表
     */
    private String warpType;

    /**
     * 备注
     * 必填：N <br>
     * 可以数字和字母或者中文
     */
    private String remark;

    /**
     * 申报地海关代码
     * 必填：Y <br>
     * 对应参数表
     */
    private String declPort;

    /**
     * 录入人
     * 必填：Y <br>
     * 默认9999
     */
    private String enteringPerson;

    /**
     * 录入单位名称
     * 必填：Y <br>
     * 默认9999
     */
    private String enteringCompanyName;

    /**
     * 报关员代码
     * 必填：N
     */
    private String declarantNo;

    /**
     * 监管场所代码
     * 必填：Y <br>
     * 对应参数表：
     * 292801	下城园区
     * 299102	下沙园区
     * 299201	萧山园区
     */
    private String customsField;

    /**
     * 发件人
     * 必填：Y <br>
     * 可以数字和字母，可以有中文和英文
     */
    private String senderName;

    /**
     * 收件人
     * 必填：Y <br>
     * 可以数字和字母，可以有中文和英文
     */
    private String consignee;

    /**
     * 发件人国别
     * 必填：Y <br>
     * 参数表
     */
    private String senderCountry;

    /**
     * 发件人城市
     * 必填：N <br>
     * 可以数字和字母，可以有中文和英文
     */
    private String senderCity;

    /**
     * 收件人证件类型
     * 必填：N <br>
     * 1-身份证（试点期间只能是身份证）
     * 2-护照
     * 3-其他
     */
    private Character paperType;

    /**
     * 收件人证件号
     * 必填：N <br>
     * 可以有数字和字母
     */
    private String paperNumber;

    /**
     * 收件人地址
     * 必填：Y <br>
     * 对应订单中的收件人地址
     */
    private String consigneeAddress;

    /**
     * 购买人电话
     * 必填：Y <br>
     * 海关监管对象的电话,对应订单中的购买人联系电话
     */
    private String purchaserTelNumber;

    /**
     * 订购人证件类型
     * 必填：Y <br>
     * 1-身份证；2-其它
     */
    private String buyerIdType;

    /**
     * 订购人证件号码
     * 必填：Y <br>
     * 海关监控对象的身份证号,对应订单购买人证件号码
     */
    private String buyerIdNumber;

    /**
     * 订购人姓名
     * 必填：Y <br>
     * 海关监控对象的姓名,对应订单购买人人姓名
     */
    private String buyerName;

    /**
     * 价值
     * 必填：Y <br>
     * 表体所有商品总价的和+运费+保费
     */
    private Double worth;

    /**
     * 运费
     * 必填：N <br>
     * 交易过程中商家向用户征收的运费，免邮模式填写0
     */
    private Double feeAmount;

    /**
     * 保费
     * 必填：Y <br>
     * 商家向用户征收的保价费用，无保费可填写0
     */
    private Double insureAmount;

    /**
     * 币制
     * 必填：Y <br>
     * 对应参数表
     */
    private String currCode;

    /**
     * 主要货物名称
     * 必填：Y <br>
     * 可以数字和字母或者中文
     */
    private String mainGName;

    /**
     * 区内企业代码
     * 必填：N <br>
     * 保税进口必填，填报仓储企业编码
     */
    private String internalAreaCompanyNo;

    /**
     * 区内企业名称
     * 必填：N <br>
     * 保税进口必填，填报仓储企业名称
     */
    private String internalAreaCompanyName;

    /**
     * 担保企业编号
     * 必填：Y
     */
    private String assureCode;

    /**
     * 申请单编号
     * 必填：N <br>
     * 保税进口必填，指仓储企业事先在辅助系统申请的分送集报申请单编号
     */
    private String applicationFormNo;

    /**
     * 是否授权
     * 必填：Y <br>
     * 代表是否个人买家授权电商申报数据，填写0或1，0代表否，1代表是
     */
    private Character isAuthorize;

    /**
     * 许可证号
     * 必填：N
     */
    private String licenseNo;
}
