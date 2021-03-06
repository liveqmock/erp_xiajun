package com.wangqin.globalshop.logistic.app.bean.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * 海关报关报文头
 *
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
public class Head {
    @XStreamAlias("businessType")
    private String businessType;
}
