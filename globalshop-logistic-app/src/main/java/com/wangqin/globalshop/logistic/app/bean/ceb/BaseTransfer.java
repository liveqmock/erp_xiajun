package com.wangqin.globalshop.logistic.app.bean.ceb;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * 基础报文传输实体节点：
 * 放置报文传输企业信息
 *
 * @author angus
 * @date 2018/8/23
 */
@Data
@Builder
@XStreamAlias("BaseTransfer")
public class BaseTransfer {

    /**
     * 传输企业代码
     * 必填：Y <br>
     * 报文传输的企业代码（需要与接入客户端的企业身份一致）
     */
    private String copCode;

    /**
     * 传输企业名称
     * 必填：Y <br>
     * 报文传输的企业名称
     */
    private String copName;

    /**
     * 报文传输模式
     * 必填：Y <br>
     * 默认为DXP；指中国电子口岸数据交换平台
     */
    private String dxpMode;

    /**
     * 报文传输编号
     * 必填：Y <br>
     * 向中国电子口岸数据中心申请数据交换平台的用户编号
     */
    private String dxpId;

    /**
     * 备注
     * 必填：N
     */
    private String note;
}
