package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.util.List;

public class ScaleTypeDO extends BaseModel {
    private Long id;

    private String type;
    List<ScaleDO> scaleList;

    public List<ScaleDO> getScaleList() {
        return scaleList;
    }

    public void setScaleList(List<ScaleDO> scaleList) {
        this.scaleList = scaleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}