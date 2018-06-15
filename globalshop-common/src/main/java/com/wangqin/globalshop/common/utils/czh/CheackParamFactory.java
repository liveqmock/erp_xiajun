package com.wangqin.globalshop.common.utils.czh;

import org.apache.poi.hssf.record.formula.functions.T;

/**
 * @author biscuit
 * @data 2018/06/14
 */
public class CheackParamFactory {
    private CheackParamFactory(){}
    public static CheackParam<T> newInstance( ){
      return new  CheackParam(T.class);
    }
}
