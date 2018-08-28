package com.wangqin.globalshop.biz1.app.dal.dataObject;

public class CustomsDeclareDetail extends BaseModel {
    private Long id;

    private String businessNo;

    private Integer goodsOrder;

    private String codeTs;

    private String goodsItemNo;

    private String itemRecordNo;

    private String itemName;

    private String goodsName;

    private String goodsModel;

    private String originCountry;

    private String tradeCurr;

    private Double tradeTotal;

    private Double declPrice;

    private Double declTotalPrice;

    private String useTo;

    private Double declareCount;

    private String goodsUnit;

    private Double goodsGrossWeight;

    private String firstUnit;

    private Double firstCount;

    private String secondUnit;

    private Double secondCount;

    private String productRecordNo;

    private String webSite;

    private String barCode;

    private String note;

    private String creator;

    private String modifier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo == null ? null : businessNo.trim();
    }

    public Integer getGoodsOrder() {
        return goodsOrder;
    }

    public void setGoodsOrder(Integer goodsOrder) {
        this.goodsOrder = goodsOrder;
    }

    public String getCodeTs() {
        return codeTs;
    }

    public void setCodeTs(String codeTs) {
        this.codeTs = codeTs == null ? null : codeTs.trim();
    }

    public String getGoodsItemNo() {
        return goodsItemNo;
    }

    public void setGoodsItemNo(String goodsItemNo) {
        this.goodsItemNo = goodsItemNo == null ? null : goodsItemNo.trim();
    }

    public String getItemRecordNo() {
        return itemRecordNo;
    }

    public void setItemRecordNo(String itemRecordNo) {
        this.itemRecordNo = itemRecordNo == null ? null : itemRecordNo.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel == null ? null : goodsModel.trim();
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry == null ? null : originCountry.trim();
    }

    public String getTradeCurr() {
        return tradeCurr;
    }

    public void setTradeCurr(String tradeCurr) {
        this.tradeCurr = tradeCurr == null ? null : tradeCurr.trim();
    }

    public Double getTradeTotal() {
        return tradeTotal;
    }

    public void setTradeTotal(Double tradeTotal) {
        this.tradeTotal = tradeTotal;
    }

    public Double getDeclPrice() {
        return declPrice;
    }

    public void setDeclPrice(Double declPrice) {
        this.declPrice = declPrice;
    }

    public Double getDeclTotalPrice() {
        return declTotalPrice;
    }

    public void setDeclTotalPrice(Double declTotalPrice) {
        this.declTotalPrice = declTotalPrice;
    }

    public String getUseTo() {
        return useTo;
    }

    public void setUseTo(String useTo) {
        this.useTo = useTo == null ? null : useTo.trim();
    }

    public Double getDeclareCount() {
        return declareCount;
    }

    public void setDeclareCount(Double declareCount) {
        this.declareCount = declareCount;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit == null ? null : goodsUnit.trim();
    }

    public Double getGoodsGrossWeight() {
        return goodsGrossWeight;
    }

    public void setGoodsGrossWeight(Double goodsGrossWeight) {
        this.goodsGrossWeight = goodsGrossWeight;
    }

    public String getFirstUnit() {
        return firstUnit;
    }

    public void setFirstUnit(String firstUnit) {
        this.firstUnit = firstUnit == null ? null : firstUnit.trim();
    }

    public Double getFirstCount() {
        return firstCount;
    }

    public void setFirstCount(Double firstCount) {
        this.firstCount = firstCount;
    }

    public String getSecondUnit() {
        return secondUnit;
    }

    public void setSecondUnit(String secondUnit) {
        this.secondUnit = secondUnit == null ? null : secondUnit.trim();
    }

    public Double getSecondCount() {
        return secondCount;
    }

    public void setSecondCount(Double secondCount) {
        this.secondCount = secondCount;
    }

    public String getProductRecordNo() {
        return productRecordNo;
    }

    public void setProductRecordNo(String productRecordNo) {
        this.productRecordNo = productRecordNo == null ? null : productRecordNo.trim();
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite == null ? null : webSite.trim();
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }
}