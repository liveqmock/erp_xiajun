package com.wangqin.globalshop.order.app.kuaidi_bean._4px;

public class _4pxItem {

	
	private String ItemDeclareType ;//货物申报类型，申报类型如果是禁运物品将无法成功预报。详见转运四方货物品类表.xlsx
	private String ItemNameLocalLang ;//货物品名（进口国申报要求的语言）
	private Integer ItemNumber ;//数量
	private Double ItemUnitPrice ;//单价
	private Double ItemTotalAmount ;//总金额
	
	private String Brand ;//品牌
	
	private String Specifications; // 产品说明（原产品说明，4方要求改为Spec）
	private String Spec; // 货物规格
	private String SpecUnit; // 规格的单位
	private Integer SpecValue;// 规格值
	
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getItemDeclareType() {
		return ItemDeclareType;
	}
	public void setItemDeclareType(String itemDeclareType) {
		ItemDeclareType = itemDeclareType;
	}
	public String getItemNameLocalLang() {
		return ItemNameLocalLang;
	}
	public void setItemNameLocalLang(String itemNameLocalLang) {
		ItemNameLocalLang = itemNameLocalLang;
	}
	public Integer getItemNumber() {
		return ItemNumber;
	}
	public void setItemNumber(Integer itemNumber) {
		ItemNumber = itemNumber;
	}
	public Double getItemUnitPrice() {
		return ItemUnitPrice;
	}
	public void setItemUnitPrice(Double itemUnitPrice) {
		ItemUnitPrice = itemUnitPrice;
	}
	public Double getItemTotalAmount() {
		return ItemTotalAmount;
	}
	public void setItemTotalAmount(Double itemTotalAmount) {
		ItemTotalAmount = itemTotalAmount;
	}
	public String getSpecifications() {
		return Specifications;
	}
	public void setSpecifications(String specifications) {
		Specifications = specifications;
	}
	public String getSpecUnit() {
		return SpecUnit;
	}
	public void setSpecUnit(String specUnit) {
		SpecUnit = specUnit;
	}
	public String getSpec() {
		return Spec;
	}
	public void setSpec(String spec) {
		Spec = spec;
	}
	public Integer getSpecValue() {
		return SpecValue;
	}
	public void setSpecValue(Integer specValue) {
		SpecValue = specValue;
	}

}
