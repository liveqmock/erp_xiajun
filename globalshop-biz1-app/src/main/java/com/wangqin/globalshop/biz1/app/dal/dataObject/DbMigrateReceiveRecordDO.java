package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class DbMigrateReceiveRecordDO extends BaseModel {
    private Long id;

    private String token;

    private String dbScript;

    private String status;

    private String modifier;

    private String creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getDbScript() {
        return dbScript;
    }

    public void setDbScript(String dbScript) {
        this.dbScript = dbScript == null ? null : dbScript.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
}