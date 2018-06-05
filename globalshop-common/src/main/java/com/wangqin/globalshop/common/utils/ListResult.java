package com.wangqin.globalshop.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 多条记录的数据结果
 * 
 * @author Robin 2014年7月14日 上午11:17:01
 */
public class ListResult<T> extends SuccessResult {

    private List<T> data = new ArrayList<T>();

    public ListResult(){
        super();
    }

    public ListResult(List<T> data){
        super();
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
