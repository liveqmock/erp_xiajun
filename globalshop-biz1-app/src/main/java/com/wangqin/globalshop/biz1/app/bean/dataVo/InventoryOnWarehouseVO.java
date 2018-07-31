package com.wangqin.globalshop.biz1.app.bean.dataVo;



import com.wangqin.globalshop.biz1.app.dal.dataObject.BaseModel;

/**
 * 库存管理展示页所需VO
 * @author xiajun
 *
 */
public class InventoryOnWarehouseVO extends BaseModel{
	/**DO里面的字段***/
	 private Long id;

	    private String inventoryOnWarehouseNo;

	    private String companyNo;

	    private String itemCode;

	    private String itemName;

	    private String upc;

	    private String scale;

	    private String skuCode;

	    private String skuPic;

	    private Long inventory;

	    private Long lockedInv;

	    private Long transInv;

	    private Long lockedTransInv;

	    private String warehouseNo;

	    private String warehouseName;

	    private String shelfNo;

	    private String batchNo;

	    private String creator;

	    private String modifier;

	    /**别的表里面的字段***/
	    private String color;//颜色
	    
	    private String salePrice;//尺寸
	    
	    private Long totalAvailableInv;//可售库存
	    
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getInventoryOnWarehouseNo() {
	        return inventoryOnWarehouseNo;
	    }

	    public void setInventoryOnWarehouseNo(String inventoryOnWarehouseNo) {
	        this.inventoryOnWarehouseNo = inventoryOnWarehouseNo == null ? null : inventoryOnWarehouseNo.trim();
	    }

	    public String getCompanyNo() {
	        return companyNo;
	    }

	    @Override
	    public void setCompanyNo(String companyNo) {
	        this.companyNo = companyNo == null ? null : companyNo.trim();
	    }

	    public String getItemCode() {
	        return itemCode;
	    }

	    public void setItemCode(String itemCode) {
	        this.itemCode = itemCode == null ? null : itemCode.trim();
	    }

	    public String getItemName() {
	        return itemName;
	    }

	    public void setItemName(String itemName) {
	        this.itemName = itemName == null ? null : itemName.trim();
	    }

	    public String getUpc() {
	        return upc;
	    }

	    public void setUpc(String upc) {
	        this.upc = upc == null ? null : upc.trim();
	    }

	    public String getScale() {
	        return scale;
	    }

	    public void setScale(String scale) {
	        this.scale = scale == null ? null : scale.trim();
	    }

	    public String getSkuCode() {
	        return skuCode;
	    }

	    public void setSkuCode(String skuCode) {
	        this.skuCode = skuCode == null ? null : skuCode.trim();
	    }

	    public String getSkuPic() {
	        return skuPic;
	    }

	    public void setSkuPic(String skuPic) {
	        this.skuPic = skuPic == null ? null : skuPic.trim();
	    }

	    public Long getInventory() {
	        return inventory;
	    }

	    public void setInventory(Long inventory) {
	        this.inventory = inventory;
	    }

	    public Long getLockedInv() {
	        return lockedInv;
	    }

	    public void setLockedInv(Long lockedInv) {
	        this.lockedInv = lockedInv;
	    }

	    public Long getTransInv() {
	        return transInv;
	    }

	    public void setTransInv(Long transInv) {
	        this.transInv = transInv;
	    }

	    public Long getLockedTransInv() {
	        return lockedTransInv;
	    }

	    public void setLockedTransInv(Long lockedTransInv) {
	        this.lockedTransInv = lockedTransInv;
	    }

	    public String getWarehouseNo() {
	        return warehouseNo;
	    }

	    public void setWarehouseNo(String warehouseNo) {
	        this.warehouseNo = warehouseNo == null ? null : warehouseNo.trim();
	    }

	    public String getWarehouseName() {
	        return warehouseName;
	    }

	    public void setWarehouseName(String warehouseName) {
	        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
	    }

	    public String getShelfNo() {
	        return shelfNo;
	    }

	    public void setShelfNo(String shelfNo) {
	        this.shelfNo = shelfNo == null ? null : shelfNo.trim();
	    }

	    public String getBatchNo() {
	        return batchNo;
	    }

	    public void setBatchNo(String batchNo) {
	        this.batchNo = batchNo == null ? null : batchNo.trim();
	    }

	    public String getCreator() {
	        return creator;
	    }

	    @Override
	    public void setCreator(String creator) {
	        this.creator = creator == null ? null : creator.trim();
	    }

	    public String getModifier() {
	        return modifier;
	    }

	    @Override
	    public void setModifier(String modifier) {
	        this.modifier = modifier == null ? null : modifier.trim();
	    }

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String getSalePrice() {
			return salePrice;
		}

		public void setSalePrice(String salePrice) {
			this.salePrice = salePrice;
		}

		public Long getTotalAvailableInv() {
			return totalAvailableInv;
		}

		public void setTotalAvailableInv(Long totalAvailableInv) {
			this.totalAvailableInv = totalAvailableInv;
		}
	    
	    

}
