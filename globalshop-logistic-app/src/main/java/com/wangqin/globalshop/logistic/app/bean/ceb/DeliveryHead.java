package com.wangqin.globalshop.logistic.app.bean.ceb;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * 入库明细单表头
 *
 * @author angus
 * @date 2018/8/23
 */
@Data
@Builder
@XStreamAlias("DeliveryHead")
public class DeliveryHead {
    /**
     * 系统唯一序号
     * 必填：Y <br>
     * 企业系统生成36位唯一序号（英文字母大写）
     */
    private String guid;

    /**
     * 报送类型
     * 必填：Y <br>
     * 企业报送类型：1-新增 2-变更 3-删除；默认为1。
     */
    private String appType;

    /**
     * 报送时间
     * 必填：Y <br>
     * 企业报送时间。格式:YYYYMMDDhhmmss。
     */
    private String appTime;

    /**
     * 业务状态
     * 必填：Y <br>
     * 业务状态：1-暂存,2-申报；默认为1。
     */
    private String appStatus;

    /**
     * 申报海关代码
     * 必填：Y <br>
     * 接受申报的海关关区代码，参照JGS/T 18《海关关区代码》。
     */
    private String customsCode;

    /**
     * 企业内部编号
     * 必填：Y <br>
     * 企业内部标识单证的编号
     */
    private String copNo;

    /**
     * 预录入编号
     * 必填：N <br>
     * 电子口岸标识单证的编号
     */
    private String preNo;

    /**
     * 入库单编号
     * 必填：N <br>
     * 海关接受申报生成的入库单编号
     */
    private String rkdNo;

    /**
     * 监管场所经营人代码
     * 必填：Y <br>
     * 监管场所经营人在海关注册登记的编号
     */
    private String operatorCode;

    /**
     * 监管场所经营人名称
     * 必填：Y <br>
     * 监管场所经营人在海关注册登记的名称
     */
    private String operatorName;

    /**
     * 进出口标记
     * 必填：Y <br>
     * I进口/E出口
     */
    private String ieFlag;

    /**
     * 运输方式
     * 必填：Y <br>
     * 填写海关标准的参数代码，参照《JGS-20 海关业务代码集》- 运输方式代码。直购进口指跨境段物流运输方式。
     */
    private String trafMode;

    /**
     * 运输工具编号
     * 必填：Y <br>
     * 货物进出境的运输工具的名称或运输工具编号。填报内容应与运输部门向海关申报的载货清单所列相应内容一致；同报关单填制规范。
     */
    private String trafNo;

    /**
     * 航班航次号
     * 必填：Y <br>
     * 货物进出境的运输工具的航次编号。
     */
    private String voyageNo;

    /**
     * 提运单号
     * 必填：Y <br>
     * 货物提单或运单的海运提单、空运总单或汽车载货清单
     */
    private String billNo;

    /**
     * 物流企业代码
     * 必填：Y <br>
     * 物流企业的海关注册登记编号
     */
    private String logisticsCode;

    /**
     * 物流企业名称
     * 必填：Y <br>
     * 物流企业的海关注册登记名称
     */
    private String logisticsName;

    /**
     * 卸货库位
     * 必填：N <br>
     * 货物卸货的仓储存放位置
     */
    private String unloadLocation;

    /**
     * 备注
     * 必填：N
     */
    private String note;
}
