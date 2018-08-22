package com.wangqin.globalshop.logistic.app.bean.declare;

import lombok.Builder;
import lombok.Data;

/**
 * 清单审批结果信息
 *
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
public class JkfGoodsDeclar {
    /**
     * 清单编号
     * 必填：Y <br>
     * 关区号（4位）+年份（4位）+进出口标志（1位）+流水号（9位），
     * 服务平台生成后，反馈管理平台及电商平台
     */
    private String personalGoodsFormNo;

    /**
     * 清单状态
     * 必填：Y <br>
     * 见参数表
     */
    private String approveResult;

    /**
     * 四位审单状态码+冒号+海关审批意见
     * 必填：Y <br>
     * 格式类似：3201:支付类型不存在.
     * 四位审单状态码定义见参数文档。
     */
    private String approveComment;

    /**
     * 处理时间
     * 必填：Y <br>
     * 格式要求：20140623101024
     */
    private String processTime;
}
