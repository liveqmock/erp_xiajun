package com.wangqin.globalshop.channel.dal.youzan;

import com.youzan.open.sdk.api.AbstractAPI;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradeGetParams;

public class YouzanTradeGet extends AbstractAPI {

    public YouzanTradeGet() {
    }

    public YouzanTradeGet(YouzanTradeGetParams apiParams) {
        this.params = apiParams;
    }

    @Override
    public String getHttpMethod() {
        return "GET";
    }

    @Override
    public String getVersion() {
        return "3.0.0";
    }

    @Override
    public String getName() {
        return "youzan.trade.get";
    }

    @Override
    public Class getResultModelClass() {
        return YouzanTradeGetResult.class;
    }

    @Override
    public Class getParamModelClass() {
        return YouzanTradeGetParams.class;
    }

    @Override
    public boolean hasFiles() {
        return  false ;
    }

}
