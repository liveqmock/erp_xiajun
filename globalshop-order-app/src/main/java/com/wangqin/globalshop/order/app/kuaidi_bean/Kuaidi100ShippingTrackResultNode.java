package com.wangqin.globalshop.order.app.kuaidi_bean;

/**
 * 快递100物流轨迹节点信息，形如：
 * <pre>
 * {
 *  "time":"2018-05-15 15:39:04",
 *  "ftime":"2018-05-15 15:39:04",
 *  "context":"[江西主城区公司赣州市政府服务部]快件已被 本人 签收"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/7/25
 */
public class Kuaidi100ShippingTrackResultNode {
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
