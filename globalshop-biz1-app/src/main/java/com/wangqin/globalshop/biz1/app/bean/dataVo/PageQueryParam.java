package com.wangqin.globalshop.biz1.app.bean.dataVo;

import lombok.Data;

/**
 * 分页查询参数对应的实体类
 *
 * @author angus
 * @date 2018/7/30
 */
@Data
public class PageQueryParam {
    /**
     * 分页查询页码
     */
    private Integer pageIndex;

    /**
     * 分页查询页面大小
     */
    private Integer pageSize;

    /**
     * 分页查询页面起始行索引
     */
    private Integer rowIndex;

    /**
     * 分页查询结果总数
     */
    private Integer totalCount;

    /**
     * 根据页码和页面大小，计算出起始行索引，用于分页查询
     */
    public void calculateRowIndex() {
        if (this.pageIndex != null && this.pageSize != null) {
            this.rowIndex = this.pageIndex > 0 ? (this.pageIndex - 1) * this.pageSize : 0;
        }
    }
}
