package com.wangqin.globalshop.logistic.app.bean.declare;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wangqin.globalshop.logistic.app.bean.common.JkfSign;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author angus
 * @date 2018/8/21
 */
@Data
@Builder
@XStreamAlias("goodsDeclareModule")
public class GoodsDeclareModule {

    @XStreamAlias("jkfSign")
    private JkfSign jkfSign;

    @XStreamAlias("goodsDeclare")
    private GoodsDeclare goodsDeclare;

    @XStreamAlias("goodsDeclareDetails")
    List<GoodsDeclareDetail> goodsDeclareDetails;
}
