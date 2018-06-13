package com.wangqin.globalshop.biz1.app.dal.dataObject;

import com.wangqin.globalshop.common.utils.AppUtil;

import java.util.Date;

/**
 * @author biscuit
 * @data 2018/06/05
 */
public class BaseModel {
    protected Boolean isDel;
    protected Date gmtCreate;
    protected Date gmtModify;

    private String creator;

    private String modifier;
    private String companyNo;

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

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public void init() {
        this.setModifier(AppUtil.getLoginUserId());
        this.setCreator(AppUtil.getLoginUserId());
        this.setIsDel(false);
        this.setCompanyNo(AppUtil.getLoginUserCompanyNo());
    }
    public void initCompany() {
        this.setCompanyNo(AppUtil.getLoginUserCompanyNo());
    }

    public void update() {
        this.setModifier(AppUtil.getLoginUserId());
    }
}