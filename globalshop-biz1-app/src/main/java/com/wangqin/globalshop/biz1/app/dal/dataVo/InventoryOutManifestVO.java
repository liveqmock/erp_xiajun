package com.wangqin.globalshop.biz1.app.dal.dataVo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InventoryOutManifestVO extends PageQueryVO {

    /**
     * 仓库号
     */
    private String warehouseNo;

    /**
     * 出库单号
     */
    private String inventoryOutNo;

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

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getInventoryOutNo() {
        return inventoryOutNo;
    }

    public void setInventoryOutNo(String inventoryOutNo) {
        this.inventoryOutNo = inventoryOutNo;
    }

    public Date getStartGmt() {
        return startGmt;
    }

    public void setStartGmt(Date startGmt) {
        this.startGmt = startGmt;
    }

    public Date getEndGmt() {
        return endGmt;
    }

    public void setEndGmt(Date endGmt) {
        this.endGmt = endGmt;
    }


}
