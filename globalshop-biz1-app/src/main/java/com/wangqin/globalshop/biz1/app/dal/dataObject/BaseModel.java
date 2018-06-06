package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

/**
 * @author biscuit
 * @data 2018/06/05
 */
public class BaseModel {
    private Boolean isDel;
    private Date gmtCreate;

    private Date gmtModify;

    private String creator;

    private String modifier;

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean del) {
        isDel = del;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public void init() {
        this.setModifier("11111");
        this.setCreator("222222");
        this.setIsDel(false);
        Date date = new Date();
        this.setGmtCreate(date);
        this.setGmtModify(date);
    }

    public void update() {
        this.setModifier("11111");
        Date date = new Date();
        this.setGmtModify(date);
    }
}
