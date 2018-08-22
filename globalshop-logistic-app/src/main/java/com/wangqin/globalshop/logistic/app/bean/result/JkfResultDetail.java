package com.wangqin.globalshop.logistic.app.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * 处理结果文字信息记录明细
 *
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
@XStreamAlias("jkfResultDetail")
public class JkfResultDetail {
    /**
     * 5位数据校验状态码 + 冒号 + 处理结果文字信息
     * 必填：Y <br>
     * 格式类似：22001:企业编号未备；5位校验码定义见文档
     */
    private String resultInfo;
}
