package com.wangqin.globalshop.order.app.kuaidi_bean;

/**
 * 物流轨迹信息对应的实体类
 */
public class ResultItem {

    private String time;
    private String ftime;
    /**
     * 物流信息
     */
    private String context;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    @Override
    public String toString() {
        return "ResultItem [time=" + time + ", ftime="
                + ftime + ", context=" + context + "]";
    }
}
