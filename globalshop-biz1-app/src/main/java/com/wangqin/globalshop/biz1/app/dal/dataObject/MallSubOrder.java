package com.wangqin.globalshop.biz1.app.dal.dataObject;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * 商城子订单
 *
 */
@TableName("mall_sub_order")
public class MallSubOrder implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/** 订单编号 */
	@TableField(value = "order_no")
	private String orderNo;

	/** 退单ID */
	@TableField(value = "mall_return_order_no")
	private String mallReturnOrderNo;

	/** 买手Id */
	@TableField(value = "customer_no")
	private String customerNo;

	/** 微信open_id */
	@TableField(value = "open_id")
	private String openId;

	/**  */
	@TableField(value = "company_no")
	private String companyNo;

	/** 内部订单编号 */
	@TableField(value = "shop_code")
	private String shopCode;

	/** 订单ID */
	@TableField(value = "channel_order_no")
	private String channelOrderNo;

	/** 销售时间 */
	@TableField(value = "order_time")
	private Date orderTime;

	/**  */
	@TableField(value = "item_code")
	private String itemCode;

	/** 商品名称 */
	@TableField(value = "item_name")
	private String itemName;

	/** sku编码 */
	@TableField(value = "sku_code")
	private String skuCode;

	/** 第三方sku */
	@TableField(value = "channel_sku_code")
	private String channelSkuCode;

	/** 商品条形码 */
	private String upc;

	/** 尺寸 */
	private String scale;

	/**  */
	@TableField(value = "sku_pic")
	private String skuPic;

	/**  */
	@TableField(value = "logistic_type")
	private Integer logisticType;

	/** 运费(预估物流费用) */
	private Double freight;

	/** 真实物流费用 */
	@TableField(value = "freight_real")
	private Double freightReal;

	/** sku的重量,单位(磅) */
	private Double weight;

	/** 销售价 */
	@TableField(value = "sale_price")
	private Double salePrice;

	/** 代理销售价 */
	@TableField(value = "sale_price_agent")
	private Double salePriceAgent;

	/** 销售数量 */
	private Integer quantity;

	/** 订单状态 */
	private Integer status;

	/** 订单关闭原因 */
	@TableField(value = "close_reason")
	private String closeReason;

	/** 配货仓库ID */
	@TableField(value = "warehouse_no")
	private String warehouseNo;

	/** 备货状态 */
	@TableField(value = "stock_status")
	private Integer stockStatus;

	/** 发货单id */
	@TableField(value = "shipping_order_no")
	private String shippingOrderNo;

	/** 包裹号 */
	@TableField(value = "shipping_no")
	private String shippingNo;

	/** 收货人姓名 */
	private String receiver;

	/**  */
	@TableField(value = "receiver_country")
	private String receiverCountry;

	/** 省 */
	@TableField(value = "receiver_state")
	private String receiverState;

	/** 市 */
	@TableField(value = "receiver_city")
	private String receiverCity;

	/** 区 */
	@TableField(value = "receiver_district")
	private String receiverDistrict;

	/** 详细地址 */
	@TableField(value = "receiver_address")
	private String receiverAddress;

	/** 联系电话 */
	private String telephone;

	/** 邮编 */
	private String postcode;

	/** 身份证号码 */
	@TableField(value = "id_card")
	private String idCard;

	/** 身份证号正面 */
	@TableField(value = "idcard_pic_front")
	private String idcardPicFront;

	/** 身份证号反面 */
	@TableField(value = "idcard_pic_reverse")
	private String idcardPicReverse;

	/** 备注 */
	private String memo;

	/** 创建时间 */
	@TableField(value = "gmt_create")
	private Date gmtCreate;

	/** 操作时间 */
	@TableField(value = "gmt_modify")
	private Date gmtModify;

	/**  */
	private String creator;

	/**  */
	private String modifier;

	/**  */
	@TableField(value = "is_del")
	private Integer isDel;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMallReturnOrderNo() {
		return this.mallReturnOrderNo;
	}

	public void setMallReturnOrderNo(String mallReturnOrderNo) {
		this.mallReturnOrderNo = mallReturnOrderNo;
	}

	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getChannelOrderNo() {
		return this.channelOrderNo;
	}

	public void setChannelOrderNo(String channelOrderNo) {
		this.channelOrderNo = channelOrderNo;
	}

	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getChannelSkuCode() {
		return this.channelSkuCode;
	}

	public void setChannelSkuCode(String channelSkuCode) {
		this.channelSkuCode = channelSkuCode;
	}

	public String getUpc() {
		return this.upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getScale() {
		return this.scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getSkuPic() {
		return this.skuPic;
	}

	public void setSkuPic(String skuPic) {
		this.skuPic = skuPic;
	}

	public Integer getLogisticType() {
		return this.logisticType;
	}

	public void setLogisticType(Integer logisticType) {
		this.logisticType = logisticType;
	}

	public Double getFreight() {
		return this.freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getFreightReal() {
		return this.freightReal;
	}

	public void setFreightReal(Double freightReal) {
		this.freightReal = freightReal;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getSalePriceAgent() {
		return this.salePriceAgent;
	}

	public void setSalePriceAgent(Double salePriceAgent) {
		this.salePriceAgent = salePriceAgent;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCloseReason() {
		return this.closeReason;
	}

	public void setCloseReason(String closeReason) {
		this.closeReason = closeReason;
	}

	public String getWarehouseNo() {
		return this.warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public Integer getStockStatus() {
		return this.stockStatus;
	}

	public void setStockStatus(Integer stockStatus) {
		this.stockStatus = stockStatus;
	}

	public String getShippingOrderNo() {
		return this.shippingOrderNo;
	}

	public void setShippingOrderNo(String shippingOrderNo) {
		this.shippingOrderNo = shippingOrderNo;
	}

	public String getShippingNo() {
		return this.shippingNo;
	}

	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverCountry() {
		return this.receiverCountry;
	}

	public void setReceiverCountry(String receiverCountry) {
		this.receiverCountry = receiverCountry;
	}

	public String getReceiverState() {
		return this.receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	public String getReceiverCity() {
		return this.receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverDistrict() {
		return this.receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public String getReceiverAddress() {
		return this.receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdcardPicFront() {
		return this.idcardPicFront;
	}

	public void setIdcardPicFront(String idcardPicFront) {
		this.idcardPicFront = idcardPicFront;
	}

	public String getIdcardPicReverse() {
		return this.idcardPicReverse;
	}

	public void setIdcardPicReverse(String idcardPicReverse) {
		this.idcardPicReverse = idcardPicReverse;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return this.gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

}
