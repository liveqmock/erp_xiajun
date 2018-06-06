package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class ChannelAccountConfigDO extends BaseModel {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String companyNo;

    private String shopcode;

    private String itemkey;

    private String itemvalue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public String getShopcode() {
        return shopcode;
    }

    public void setShopcode(String shopcode) {
        this.shopcode = shopcode == null ? null : shopcode.trim();
    }

    public String getItemkey() {
        return itemkey;
    }

    public void setItemkey(String itemkey) {
        this.itemkey = itemkey == null ? null : itemkey.trim();
    }

    public String getItemvalue() {
        return itemvalue;
    }

    public void setItemvalue(String itemvalue) {
        this.itemvalue = itemvalue == null ? null : itemvalue.trim();
    }
}