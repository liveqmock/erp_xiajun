package com.wangqin.globalshop.logistic.app.bean.ceb;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * 签名节点：
 * 放置报文加业务签信息
 *
 * @author angus
 * @date 2018/8/23
 */
@Data
@Builder
@XStreamAlias("Signature")
public class Signature {
}
