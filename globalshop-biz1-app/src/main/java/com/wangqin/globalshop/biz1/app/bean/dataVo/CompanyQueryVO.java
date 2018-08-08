package com.wangqin.globalshop.biz1.app.bean.dataVo;

import lombok.Data;

/**
 * 商家查询表单对应的 VO
 *
 * @author angus
 * @date 2018/8/7
 */
@Data
public class CompanyQueryVO {
    /**
     * 商家名称
     * <p>
     * 对应 company 表的 company_name 字段
     */
    String companyName;

    /**
     * 商家状态 0:正常，1:关闭
     * <p>
     * 对应 company 表的 status 字段
     */
    Integer status;
}
