package com.wangqin.globalshop.biz1.app.dal.dataVo;

public class PageQueryVO {


    /**
     * 分页中的，第几页
     */
    private Integer pageIndex = 1;

    private Integer pageSize = 20;

    /**
     * 第一条记录
     */
    // TODO:后续需要修改
    private Integer firstStart = 0;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getFirstStart() {
        return firstStart;
    }

    public void setFirstStart(Integer firstStart) {
        this.firstStart = firstStart;
    }


}
