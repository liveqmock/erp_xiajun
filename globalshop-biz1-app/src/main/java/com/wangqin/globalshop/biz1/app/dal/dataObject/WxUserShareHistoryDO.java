package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class WxUserShareHistoryDO extends BaseModel {
    private Long id;

    private String openId;

    private String unionId;

    private String sharerWxid;

    private String shareResult;

    private String itemCode;

    private String companyNo;

    private String orderStatus;

    private String creator;

    private String modifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getSharerWxid() {
        return sharerWxid;
    }

    public void setSharerWxid(String sharerWxid) {
        this.sharerWxid = sharerWxid == null ? null : sharerWxid.trim();
    }

    public String getShareResult() {
        return shareResult;
    }

    public void setShareResult(String shareResult) {
        this.shareResult = shareResult == null ? null : shareResult.trim();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}