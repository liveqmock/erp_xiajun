package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.Date;

public class LogisticCompanyDO {
    private Long id;

    private String name;

    private String code;

    private String nameInJd;

    private String codeInJd;

    private String nameInYouzan;

    private String codeInYouzan;
    private String nameInKuaidi100;

    private String codeInKuaidi100;

    private String nameInPdd;

    private String codeInPdd;

    private String nameInTaobao;

    private String codeInTaobao;

    private Boolean isDel;

    private Date gmtCreate;

    private Date gmtModify;

    private String creator;

    private String modifier;

    public String getNameInKuaidi100() {
        return nameInKuaidi100;
    }

    public void setNameInKuaidi100(String nameInKuaidi100) {
        this.nameInKuaidi100 = nameInKuaidi100;
    }

    public String getCodeInKuaidi100() {
        return codeInKuaidi100;
    }

    public void setCodeInKuaidi100(String codeInKuaidi100) {
        this.codeInKuaidi100 = codeInKuaidi100;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getNameInJd() {
        return nameInJd;
    }

    public void setNameInJd(String nameInJd) {
        this.nameInJd = nameInJd == null ? null : nameInJd.trim();
    }

    public String getCodeInJd() {
        return codeInJd;
    }

    public void setCodeInJd(String codeInJd) {
        this.codeInJd = codeInJd == null ? null : codeInJd.trim();
    }

    public String getNameInYouzan() {
        return nameInYouzan;
    }

    public void setNameInYouzan(String nameInYouzan) {
        this.nameInYouzan = nameInYouzan == null ? null : nameInYouzan.trim();
    }

    public String getCodeInYouzan() {
        return codeInYouzan;
    }

    public void setCodeInYouzan(String codeInYouzan) {
        this.codeInYouzan = codeInYouzan == null ? null : codeInYouzan.trim();
    }

    public String getNameInPdd() {
        return nameInPdd;
    }

    public void setNameInPdd(String nameInPdd) {
        this.nameInPdd = nameInPdd == null ? null : nameInPdd.trim();
    }

    public String getCodeInPdd() {
        return codeInPdd;
    }

    public void setCodeInPdd(String codeInPdd) {
        this.codeInPdd = codeInPdd == null ? null : codeInPdd.trim();
    }

    public String getNameInTaobao() {
        return nameInTaobao;
    }

    public void setNameInTaobao(String nameInTaobao) {
        this.nameInTaobao = nameInTaobao == null ? null : nameInTaobao.trim();
    }

    public String getCodeInTaobao() {
        return codeInTaobao;
    }

    public void setCodeInTaobao(String codeInTaobao) {
        this.codeInTaobao = codeInTaobao == null ? null : codeInTaobao.trim();
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
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
        this.creator = creator == null ? null : creator.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}