package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.math.BigDecimal;
import java.util.Date;

public class MallSubOrderDO extends BaseModel {
    private Long id;

    private String orderNo;

    private String mallReturnOrderNo;

    private String customerNo;

    private String openId;

    private String companyNo;

    private String shopCode;

    private String channelOrderNo;

    private Date orderTime;

    private String itemCode;

    private String itemName;

    private String skuCode;

    private String channelSkuCode;

    private String upc;

    private String scale;

    private String color;

    private String skuPic;

    private Integer logisticType;

    private Double freight;

    private Double freightReal;

    private Double weight;

    private Double salePrice;

    private Double salePriceAgent;

    private Integer quantity;

    private Integer status;

    private String closeReason;

    private String warehouseNo;

    private Integer stockStatus;

    private String shippingOrderNo;

    private String shippingNo;

    private String receiver;

    private String receiverCountry;

    private String receiverState;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private String telephone;

    private String postcode;

    private String idCard;

    private String idcardPicFront;

    private String idcardPicReverse;

    private String memo;

    private String creator;

    private String modifier;

    private String subOrderNo;

    private String channelSubOrderNo;

    private String shareUserId;

    private String shareToken;

    private String shareTime;

    private String shareCloseFlag;

    private String shareCloseTime;

    private BigDecimal shareMoney;

    private String brand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMallReturnOrderNo() {
        return mallReturnOrderNo;
    }

    public void setMallReturnOrderNo(String mallReturnOrderNo) {
        this.mallReturnOrderNo = mallReturnOrderNo;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getChannelSkuCode() {
        return channelSkuCode;
    }

    public void setChannelSkuCode(String channelSkuCode) {
        this.channelSkuCode = channelSkuCode;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSkuPic() {
        return skuPic;
    }

    public void setSkuPic(String skuPic) {
        this.skuPic = skuPic;
    }

    public Integer getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(Integer logisticType) {
        this.logisticType = logisticType;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getFreightReal() {
        return freightReal;
    }

    public void setFreightReal(Double freightReal) {
        this.freightReal = freightReal;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getSalePriceAgent() {
        return salePriceAgent;
    }

    public void setSalePriceAgent(Double salePriceAgent) {
        this.salePriceAgent = salePriceAgent;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public Integer getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Integer stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getShippingOrderNo() {
        return shippingOrderNo;
    }

    public void setShippingOrderNo(String shippingOrderNo) {
        this.shippingOrderNo = shippingOrderNo;
    }

    public String getShippingNo() {
        return shippingNo;
    }

    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdcardPicFront() {
        return idcardPicFront;
    }

    public void setIdcardPicFront(String idcardPicFront) {
        this.idcardPicFront = idcardPicFront;
    }

    public String getIdcardPicReverse() {
        return idcardPicReverse;
    }

    public void setIdcardPicReverse(String idcardPicReverse) {
        this.idcardPicReverse = idcardPicReverse;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    @Override
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo;
    }

    public String getChannelSubOrderNo() {
        return channelSubOrderNo;
    }

    public void setChannelSubOrderNo(String channelSubOrderNo) {
        this.channelSubOrderNo = channelSubOrderNo;
    }

    public String getShareUserId() {
        return shareUserId;
    }

    public void setShareUserId(String shareUserId) {
        this.shareUserId = shareUserId;
    }

    public String getShareToken() {
        return shareToken;
    }

    public void setShareToken(String shareToken) {
        this.shareToken = shareToken;
    }

    public String getShareTime() {
        return shareTime;
    }

    public void setShareTime(String shareTime) {
        this.shareTime = shareTime;
    }

    public String getShareCloseFlag() {
        return shareCloseFlag;
    }

    public void setShareCloseFlag(String shareCloseFlag) {
        this.shareCloseFlag = shareCloseFlag;
    }

    public String getShareCloseTime() {
        return shareCloseTime;
    }

    public void setShareCloseTime(String shareCloseTime) {
        this.shareCloseTime = shareCloseTime;
    }

    public BigDecimal getShareMoney() {
        return shareMoney;
    }

    public void setShareMoney(BigDecimal shareMoney) {
        this.shareMoney = shareMoney;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}