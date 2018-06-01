package com.wangqin.globalshop.purchase.model;

import java.util.List;

import com.wangqin.globalshop.common.utils.PicModel;

public class MainPicModel {

    private List<PicModel> picList;
    private Integer        mainPicNum;

    public List<PicModel> getPicList() {
        return picList;
    }

    public void setPicList(List<PicModel> picList) {
        this.picList = picList;
    }

    public Integer getMainPicNum() {
        return mainPicNum;
    }

    public void setMainPicNum(Integer mainPicNum) {
        this.mainPicNum = mainPicNum;
    }

}

