package com.wangqin.globalshop.biz1.app.bean.dataVo;

/**
 * 佣金结算管理
 */
public class AgentCommissionVO {
    private Long id;
    private String userNo;
    private String headProtraitUrl;
    private String level;
    private Integer orderNum;
    private Double totalSaleMoney;
    private Double commission;//佣金

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getHeadProtraitUrl() {
        return headProtraitUrl;
    }

    public void setHeadProtraitUrl(String headProtraitUrl) {
        this.headProtraitUrl = headProtraitUrl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Double getTotalSaleMoney() {
        return totalSaleMoney;
    }

    public void setTotalSaleMoney(Double totalSaleMoney) {
        this.totalSaleMoney = totalSaleMoney;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }
}
