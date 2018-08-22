package com.wangqin.globalshop.logistic.app.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 处理结果
 *
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
@XStreamAlias("jkfResult")
public class JkfResult {
    /**
     * 企业备案编码
     * 必填：Y
     */
    private String companyCode;

    /**
     * 业务编号
     * 必填：Y <br>
     * 与企业申报时的内容一致
     */
    private String businessNo;

    /**
     * 业务类型
     * 必填：Y <br>
     * 业务类型代码
     */
    private String businessType;

    /**
     * 申报类型
     * 必填：Y <br>
     * 申报类型代码
     */
    private Character declareType;

    /**
     * 处理结果
     * 必填：Y <br>
     * 非空	1:成功 2:处理失败　
     */
    private Character chkMark;

    /**
     * 通知日期
     * 必填：Y <br>
     * 格式：YYYY-MM-DD
     */
    private String noticeDate;

    /**
     * 通知时间
     * 必填：Y <br>
     * 格式：HH:MM
     */
    private String noticeTime;

    /**
     * 备注
     * 必填：N <br>
     * ZJPORTRESULT 表示浙江电子口岸反馈处理结果
     */
    private String note;

    @XStreamAlias("resultList")
    private List<JkfResultDetail> resultList;
}
