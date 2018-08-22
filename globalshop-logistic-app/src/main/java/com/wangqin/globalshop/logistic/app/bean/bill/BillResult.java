package com.wangqin.globalshop.logistic.app.bean.bill;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * 处理结果
 *
 * @author angus
 * @date 2018/8/22
 */
@Data
@Builder
@XStreamAlias("billResult")
public class BillResult {
    /**
     * 分运单号集合
     * 必填：Y <br>
     * 分运单号之间逗号分割
     */
    private String wayBillNos;

    /**
     * 出区状态
     * 必填：Y <br>
     * 1:已出区 2:未出区
     */
    private String outState;

    /**
     * 出区时间
     * 必填：Y <br>
     * yyyy-MM-dd HH:mm:ss
     */
    private String outTime;
}
