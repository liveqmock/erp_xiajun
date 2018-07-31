package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.common.utils.AppUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 出库单管理查询表单对应的 VO
 * <p>
 * 前端查询参数示例：
 * <pre>
 *  warehouseNo: WARE1530238976240
 *  inventoryOutNo: INO1531815612046
 *  startGmt: 2018-07-12
 *  endGmt: 2018-08-22
 * <pre/>
 *
 * @author angus
 * @date 2018/7/13
 */
@Setter
@Getter
@ToString
public class InventoryOutManifestQueryVO {

    /**
     * 仓库号
     */
    private String warehouseNo;

    /**
     * 出库单号
     */
    private String inventoryOutNo;

    /**
     * 公司号
     */
    private String companyNo;

    /**
     * 创建开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startGmt;

    /**
     * 创建结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endGmt;

    public InventoryOutManifestQueryVO() {
        this.companyNo = AppUtil.getLoginUserCompanyNo();
    }
}
