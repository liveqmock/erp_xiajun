package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.common.utils.AppUtil;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 商品管理-商品列表查询表单对应的 VO
 *
 * @author angus
 * @date 2018/8/10
 */
@Data
public class ItemQuery2VO {
    /**
     * 商品代码
     * <br>
     * 对应 item 表 item_code 字段
     */
    private String itemCode;

    /**
     * 商品名称
     * <br>
     * 对应 item 表 item_name 字段
     */
    private String name;

    /**
     * 商品类目
     * <br>
     * 对应 item 表 category_code 字段
     */
    private String categoryCode;

    /**
     * 销售时间范围（起）
     * <br>
     * 对应 item 表 start_date 字段
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 销售时间范围（止）
     * <br>
     * 对应 item 表 start_date 字段
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 商品状态
     * <br>
     * 对应 item 表 status 字段
     */
    private Integer status;

    /**
     * 商家编号
     * <br>
     * 对应 item 表的 company_no
     */
    private String companyNo;

    public ItemQuery2VO() {
        this.companyNo = AppUtil.getLoginUserCompanyNo();
    }
}
