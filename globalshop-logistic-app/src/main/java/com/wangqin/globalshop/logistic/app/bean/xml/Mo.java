package com.wangqin.globalshop.logistic.app.bean.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Builder;
import lombok.Data;

/**
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
@XStreamAlias("mo")
public class Mo {
    @XStreamAsAttribute
    private String version;

    @XStreamAlias("head")
    private Head head;

    @XStreamAlias("body")
    private Body body;
}
