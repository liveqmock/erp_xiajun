package com.wangqin.globalshop.biz1.app.dal.dataObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 出库单对应的 DO
 *
 * @author angus
 * @date 2018/7/13
 */
@Setter
@Getter
@ToString
public class InventoryOutManifestDO {
    /**
     * ID
     */
    private Long id;

    /**
     * 出库单号
     */
    private String inventoryOutNo;

    /**
     * 公司 ID
     */
    private String companyNo;

    /**
     * 仓库 ID
     */
    private String warehouseNo;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 出库单状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作时间
     */
    private Date gmtModify;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    private String modifier;

    private String creator;

    protected Boolean isDel;
}