package com.wangqin.globalshop.channel.dal.youzan;

import com.youzan.open.sdk.api.AbstractAPI;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanTradesSoldGetParams;

public class YouzanTradesSoldGet extends AbstractAPI {

    public YouzanTradesSoldGet() {
    }

    public YouzanTradesSoldGet(YouzanTradesSoldGetParams apiParams) {
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
        return "youzan.trades.sold.get";
    }

    @Override
    public Class getResultModelClass() {
        return YouzanTradesSoldGetResult.class;
    }

    @Override
    public Class getParamModelClass() {
        return YouzanTradesSoldGetParams.class;
    }

    @Override
    public boolean hasFiles() {
        return  false ;
    }

}
