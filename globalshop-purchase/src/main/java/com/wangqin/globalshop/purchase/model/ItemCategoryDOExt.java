package com.wangqin.globalshop.purchase.model;

import java.util.List;

import com.wangqin.globalshop.biz1.app.dal.dataObject.ItemCategoryDO;

public class ItemCategoryDOExt extends ItemCategoryDO {

    private List<ItemCategoryDO> categoryList;

    public List<ItemCategoryDO> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ItemCategoryDO> categoryList) {
        this.categoryList = categoryList;
    }

}
