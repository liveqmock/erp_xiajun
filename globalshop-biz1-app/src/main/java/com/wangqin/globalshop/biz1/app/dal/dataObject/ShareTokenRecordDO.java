package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class ShareTokenRecordDO extends BaseModel {
    private Long id;

    private String companyNo;

    private String itemCode;

    private String token;

    private Long seq;

    private String userWx;

    private String userNo;

    private String parentUserNo;

    private String parentUserWx;

    private String shareToken;

    private String creator;

    private String modifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getUserWx() {
        return userWx;
    }

    public void setUserWx(String userWx) {
        this.userWx = userWx == null ? null : userWx.trim();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getParentUserNo() {
        return parentUserNo;
    }

    public void setParentUserNo(String parentUserNo) {
        this.parentUserNo = parentUserNo == null ? null : parentUserNo.trim();
    }

    public String getParentUserWx() {
        return parentUserWx;
    }

    public void setParentUserWx(String parentUserWx) {
        this.parentUserWx = parentUserWx == null ? null : parentUserWx.trim();
    }

    public String getShareToken() {
        return shareToken;
    }

    public void setShareToken(String shareToken) {
        this.shareToken = shareToken == null ? null : shareToken.trim();
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