package com.wangqin.globalshop.order.app.kuaidi_bean;

/**
 * 通用物流轨迹状态枚举（参考自“快递100”）
 *
 * @author angus
 * @date 2018/8/2
 */
public enum CommonShippingTrackState {
    /**
     * 在途
     */
    ON_PASSAGE(0, "在途，即货物处于运输过程中"),

    /**
     * 揽件
     */
    COLLECT_PARCEL(1, "揽件，货物已由快递公司揽收并且产生了第一条跟踪信息"),

    /**
     * 疑难
     */
    APPEAR_PROBLEM(2, "疑难，货物寄送过程出了问题"),

    /**
     * 签收
     */
    HAVE_RECEIVE(3, "签收，收件人已签收"),

    /**
     * 退签
     */
    BACK_RECEIVE(4, "退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收"),

    /**
     * 派件
     */
    IN_DELIVERY(5, "派件，即快递正在进行同城派件"),

    /**
     * 退回
     */
    SEND_BACK(6, "退回，货物正处于退回发件人的途中");

    /**
     * 状态
     */
    private int state;

    /**
     * 状态说明
     */
    private String stateInfo;

    CommonShippingTrackState(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static CommonShippingTrackState of(int state) {
        for (CommonShippingTrackState commonShippingTrackState : CommonShippingTrackState.values()) {
            if (commonShippingTrackState.state == state) {
                return commonShippingTrackState;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
