package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.common.utils.AppUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 库存管理查询表单对应的 VO
 * <p>
 * 前端查询参数示例：
 * <pre>
 *  warehouseId: 143 // 建议改为 warehouseNo
 *  skuCode: S0101005Q1000390002
 *  upc: 1111ff
 *  itemName: 456456
 *  positionNo: 1111ff // 建议改为 shelfNo
 *  buySite: 23 // 建议去掉
 * <pre/>
 *
 * @author angus
 * @date 2018/7/31
 */
@Setter
@Getter
@ToString
public class InventoryOnWarehouseQueryVO {
    /**
     * 仓库ID
     * <p>
     * 对应 inventory_on_warehouse 表中的 warehouse_no 字段
     */
    private String warehouseNo;

    /**
     * SKU CODE
     * <p>
     * 对应 inventory_on_warehouse 表中的 sku_code 字段
     */
    private String skuCode;

    /**
     * UPC
     * <p>
     * 对应 inventory_on_warehouse 表中的 upc 字段
     */
    private String upc;

    /**
     * 商品名称
     * <p>
     * 对应 inventory_on_warehouse 表中的 item_name 字段
     */
    private String itemName;

    /**
     * 货架号
     * <p>
     * 对应 inventory_on_warehouse 表中的 shelf_no 字段
     */
    private String shelfNo;

    /**
     * 公司 ID
     * <p>
     * 对应 inventory_on_warehouse 表中的 company_no 字段
     */
    private String companyNo;

    public InventoryOnWarehouseQueryVO() {
        this.companyNo = AppUtil.getLoginUserCompanyNo();
    }
}
