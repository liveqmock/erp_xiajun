package com.wangqin.globalshop.logistic.app.bean.ceb;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Builder;
import lombok.Data;

/**
 * 入库明细单报文根节点：
 * CEB711Message 为入库明细单报文XML根节点名称，包括 guid 和 version 两个属性
 *
 * @author angus
 * @date 2018/8/23
 */
@Data
@Builder
@XStreamAlias("CEB711Message")
public class CEB711Message {
    /**
     * 报文编号
     * 必填：Y <br>
     * 报文的36位系统唯一序号（英文字母大写）。
     */
    @XStreamAsAttribute
    @XStreamAlias("guid")
    private String guid;

    /**
     * 版本号
     * 必填：Y <br>
     * 报文版本号，默认为1.0
     */
    @XStreamAsAttribute
    @XStreamAlias("version")
    private String version;

    @XStreamAlias("Delivery")
    private Delivery delivery;

    @XStreamAlias("BaseTransfer")
    private BaseTransfer baseTransfer;
}
