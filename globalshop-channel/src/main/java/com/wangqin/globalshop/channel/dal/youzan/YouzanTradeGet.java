package com.wangqin.globalshop.channel.dal.youzan;

import com.youzan.open.sdk.api.AbstractAPI;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradeGetParams;

public class YouzanTradeGet extends AbstractAPI {

    public YouzanTradeGet() {
    }

    public YouzanTradeGet(YouzanTradeGetParams apiParams) {
        this.params = apiParams;
    }

    public String getHttpMethod() {
        return "GET";
    }

    public String getVersion() {
        return "3.0.0";
    }

    public String getName() {
        return "youzan.trade.get";
    }

    public Class getResultModelClass() {
        return YouzanTradeGetResult.class;
    }

    public Class getParamModelClass() {
        return YouzanTradeGetParams.class;
    }

    public boolean hasFiles() {
        return  false ;
    }

}
