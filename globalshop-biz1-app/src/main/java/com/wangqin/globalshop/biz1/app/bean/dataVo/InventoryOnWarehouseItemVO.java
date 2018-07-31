package com.wangqin.globalshop.biz1.app.bean.dataVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 库存管理展示列表项对应的 VO
 *
 * @author angus
 * @date 2018/7/31
 */
@Setter
@Getter
@ToString
public class InventoryOnWarehouseItemVO {
    /**
     * 库存 ID
     */
    private String inventoryOnWarehouseNo;

    /**
     * SKU 代码
     */
    private String skuCode;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品图片
     */
    private String skuPic;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * upc
     */
    private String upc;

    /**
     * 颜色（规格 1）
     */
    private String color;

    /**
     * 尺寸（规格 2）
     */
    private String scale;

    /**
     * 销售价
     */
    private Double salePrice;

    /**
     * 现货库存
     */
    private Long inventory;

    /**
     * 可售库存
     */
    private Long totalAvailableInv;

    /**
     * 货架号
     */
    private String shelfNo;
}
