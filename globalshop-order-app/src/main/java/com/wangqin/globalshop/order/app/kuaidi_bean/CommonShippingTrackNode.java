package com.wangqin.globalshop.order.app.kuaidi_bean;

/**
 * 物流轨迹节点信息，形如：
 * <pre>
 * {
 *   "date":"2018-05-15 15:39:04",
 *   "info":"[江西主城区公司赣州市政府服务部]快件已被 本人 签收"
 * }
 * </pre>
 *
 * @author angus
 * @date 2018/7/25
 */
public class CommonShippingTrackNode {
    private String date;
    private String info;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
