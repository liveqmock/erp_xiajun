package com.wangqin.globalshop.biz1.app.bean.dataVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 出入库记录展示列表项对应的 VO
 *
 * @author angus
 * @date 2018/7/30
 */
@Setter
@Getter
@ToString
public class InventoryInoutItemVO {
    /**
     * sku 码
     */
    private String skuCode;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * upc
     */
    private String upc;

    /**
     * sku 图片
     */
    private String skuPic;

    /**
     * 操作人
     */
    private String creator;

    /**
     * 操作时间
     */
    protected Date gmtCreate;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 货架号
     */
    private String shelfNo;

    /**
     * 数量
     */
    private Long quantity;

    /**
     * 操作类型
     */
    private Integer operatorType;

    /**
     * 操作类型备注
     */
    private String remark;
}
