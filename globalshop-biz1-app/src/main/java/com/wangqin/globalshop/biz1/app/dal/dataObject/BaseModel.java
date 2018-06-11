package com.wangqin.globalshop.biz1.app.dal.dataObject;

import com.wangqin.globalshop.common.utils.AppUtil;

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
    private String companyNo;

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

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
        this.setModifier("biscuits");
        this.setCreator("biscuits");
        this.setIsDel(false);
        Date date = new Date();
        this.setGmtCreate(date);
        this.setGmtModify(date);
        this.setCompanyNo("3");
    }
    public void initCompany() {
        this.setCompanyNo("3");
    }

    public void update() {
        this.setModifier(AppUtil.getLoginUserId());
        Date date = new Date();
        this.setGmtModify(date);
    }
}
