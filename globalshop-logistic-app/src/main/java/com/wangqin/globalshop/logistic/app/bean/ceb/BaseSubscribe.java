package com.wangqin.globalshop.logistic.app.bean.ceb;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

/**
 * 基础回执订阅实体节点：
 * 用于第三方提供数据的订阅下发
 *
 * @author angus
 * @date 2018/8/23
 */
@Data
@Builder
@XStreamAlias("BaseSubscribe")
public class BaseSubscribe {
}
