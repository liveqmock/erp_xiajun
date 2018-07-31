package com.wangqin.globalshop.biz1.app.bean.dataVo;

import com.wangqin.globalshop.biz1.app.dal.dataObject.BaseModel;
import com.wangqin.globalshop.common.utils.AppUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 出入库记录查询表单对应的 VO
 * <p>
 * 前端查询参数示例：
 * <pre>
 *  warehouseId: 143 // inventory_inout 表无此字段，建议改为 warehouseNo
 *  skuCode: S0101005Q1000390002
 *  upc: 2121 // inventory_inout 表无此字段
 *  itemName: 456456 // inventory_inout 表无此字段
 *  operatorType: 0
 *  positionNo: 1111ff // inventory_inout 表无此字段，建议改为 shelfNo
 *  pageIndex: 1
 * <pre/>
 *
 * @author angus
 * @date 2018/7/30
 */
@Getter
@Setter
@ToString
public class InventoryInoutQueryVO {
    /**
     * 仓库ID
     */
    private String warehouseNo;

    /**
     * sku码
     */
    private String skuCode;

    /**
     * upc
     */
    private String upc;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 库存操作类型
     */
    private Integer operatorType;

    /**
     * 货架编号
     */
    private String shelfNo;

    /**
     * 公司编号
     */
    private String companyNo;

    public InventoryInoutQueryVO() {
        this.companyNo = AppUtil.getLoginUserCompanyNo();
    }
}
