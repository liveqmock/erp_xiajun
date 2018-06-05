package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class MallWxCustomerTrackDO extends BaseModel {
    private Long id;

    private String companyNo;

    private String path;

    private String query;

    private String scene;

    private String shareOpenId;

    private String ownerOpenId;

    private Date gmtCreate;

    private Date gmtModify;

    private Boolean isDel;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query == null ? null : query.trim();
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene == null ? null : scene.trim();
    }

    public String getShareOpenId() {
        return shareOpenId;
    }

    public void setShareOpenId(String shareOpenId) {
        this.shareOpenId = shareOpenId == null ? null : shareOpenId.trim();
    }

    public String getOwnerOpenId() {
        return ownerOpenId;
    }

    public void setOwnerOpenId(String ownerOpenId) {
        this.ownerOpenId = ownerOpenId == null ? null : ownerOpenId.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
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