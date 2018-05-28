package com.wangqin.globalshop.biz1.app.dal.youzan;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TradeDetailV2 {

    @JsonProperty(value = "tid")
/**
* 交易编号
*/
private String tid;
    @JsonProperty(value = "num")
/**
* 商品购买数量。当一个trade对应多个order的时候，值为所有商品购买数量之和
*/
private Long num;
    @JsonProperty(value = "item_id")
/**
* 商品数字编号。当一个trade对应多个order的时候，值为第一个交易明细中的商品的编号
*/
private Long itemId;
    @JsonProperty(value = "price")
/**
* 商品价格。精确到2位小数；单位：元。当一个trade对应多个order的时候，值为第一个交易明细中的商品的价格
*/
private Float price;
    @JsonProperty(value = "pic_path")
/**
* 商品主图片地址。当一个trade对应多个order的时候，值为第一个交易明细中的商品的图片地址
*/
private String picPath;
    @JsonProperty(value = "pic_thumb_path")
/**
* 商品主图片缩略图地址
*/
private String picThumbPath;
    @JsonProperty(value = "title")
/**
* 交易标题，以首个商品标题作为此标题的值
*/
private String title;
    @JsonProperty(value = "type")
/**
* 交易类型。取值范围：<br>
FIXED （一口价）<br>
GIFT （送礼）<br>
BULK_PURCHASE（来自分销商的采购）<br>
PRESENT （赠品领取）<br>
GROUP （拼团订单）<br>
PIFA （批发订单）<br>
COD （货到付款）<br>
PEER （代付）<br>
QRCODE（扫码商家二维码直接支付的交易）<br>
QRCODE_3RD（线下收银台二维码交易)
*/
private String type;
    @JsonProperty(value = "buyer_message")
/**
* 买家购买附言
*/
private String buyerMessage;
    @JsonProperty(value = "seller_flag")
/**
* 卖家备注星标，取值范围 1、2、3、4、5；<br>如果为0，表示没有备注星标
*/
private Long sellerFlag;
    @JsonProperty(value = "trade_memo")
/**
* 卖家对该交易的备注
*/
private String tradeMemo;
    @JsonProperty(value = "receiver_city")
/**
* 收货人的所在城市。<br>
PS：如果订单类型是送礼订单，收货地址在sub_trades字段中；如果物流方式是到店自提，收货地址在fetch_detail字段中
*/
private String receiverCity;
    @JsonProperty(value = "receiver_district")
/**
* 收货人的所在地区
*/
private String receiverDistrict;
    @JsonProperty(value = "receiver_name")
/**
* 收货人的姓名
*/
private String receiverName;
    @JsonProperty(value = "receiver_state")
/**
* 收货人的所在省份
*/
private String receiverState;
    @JsonProperty(value = "receiver_address")
/**
* 收货人的详细地址
*/
private String receiverAddress;
    @JsonProperty(value = "receiver_zip")
/**
* 收货人的邮编
*/
private String receiverZip;
    @JsonProperty(value = "receiver_mobile")
/**
* 收货人的手机号码
*/
private String receiverMobile;
    @JsonProperty(value = "feedback")
/**
* 交易维权状态。<br>
		0 无维权，1 顾客发起维权，2 顾客拒绝商家的处理结果，3 顾客接受商家的处理结果，9 商家正在处理,101 维权处理中,110 维权结束。<br>
        备注：1到10的状态码是微信维权状态码，100以上的状态码是有赞维权状态码
*/
private Long feedback;
    @JsonProperty(value = "refund_state")
/**
* 退款状态。取值范围：<br>
NO_REFUND（无退款）<br>
PARTIAL_REFUNDING（部分退款中）<br>
PARTIAL_REFUNDED（已部分退款）<br>
PARTIAL_REFUND_FAILED（部分退款失败）<br>
FULL_REFUNDING（全额退款中）<br>
FULL_REFUNDED（已全额退款）<br>
FULL_REFUND_FAILED（全额退款失败）<br>
*/
private String refundState;
    @JsonProperty(value = "outer_tid")
/**
* 外部交易编号。比如，如果支付方式是微信支付，就是财付通的交易单号
*/
private String outerTid;
    @JsonProperty(value = "transaction_tid")
/**
* 支付流水号
*/
private String transactionTid;
    @JsonProperty(value = "status")
/**
* 交易状态。取值范围：<br>
TRADE_NO_CREATE_PAY (没有创建支付交易) <br>
WAIT_BUYER_PAY (等待买家付款) <br>
WAIT_PAY_RETURN (等待支付确认) <br>
WAIT_GROUP（等待成团，即：买家已付款，等待成团）<br>
WAIT_SELLER_SEND_GOODS (等待卖家发货，即：买家已付款) <br>
WAIT_BUYER_CONFIRM_GOODS (等待买家确认收货，即：卖家已发货) <br>
TRADE_BUYER_SIGNED (买家已签收) <br>
TRADE_CLOSED (付款以后用户退款成功，交易自动关
*/
private String status;
    @JsonProperty(value = "shipping_type")
/**
* 创建交易时的物流方式。取值范围：express（快递），fetch（到店自提），local（同城配送）
*/
private String shippingType;
    @JsonProperty(value = "post_fee")
/**
* 运费。单位：元，精确到分
*/
private Float postFee;
    @JsonProperty(value = "total_fee")
/**
* 商品总价（商品价格乘以数量的总金额）。单位：元，精确到分
*/
private Float totalFee;
    @JsonProperty(value = "refunded_fee")
/**
* 交易完成后退款的金额。单位：元，精确到分
*/
private Float refundedFee;
    @JsonProperty(value = "discount_fee")
/**
* 交易优惠金额（不包含交易明细中的优惠金额）。单位：元，精确到分
*/
private Float discountFee;
    @JsonProperty(value = "payment")
/**
* 实付金额。单位：元，精确到分
*/
private Float payment;
    @JsonProperty(value = "created")
/**
* 交易创建时间
*/
private Date created;
    @JsonProperty(value = "update_time")
/**
* 交易更新时间。当交易的：状态改变、备注更改、星标更改 等情况下都会刷新更新时间
*/
private Date updateTime;
    @JsonProperty(value = "pay_time")
/**
* 买家付款时间
*/
private Date payTime;
    @JsonProperty(value = "pay_type")
/**
* 支付类型。取值范围：<br>
WEIXIN (微信自有支付)<br>
WEIXIN_DAIXIAO (微信代销支付)<br>
ALIPAY (支付宝支付)<br>
BANKCARDPAY (银行卡支付)<br>
PEERPAY (代付)<br>
CODPAY (货到付款)<br>
BAIDUPAY (百度钱包支付)<br>
PRESENTTAKE (直接领取赠品)<br>
COUPONPAY（优惠券/码全额抵扣）<br>
BULKPURCHASE（来自分销商的采购）<br>
MERGEDPAY (合并付货款) <br>
ECARD（有赞E卡支付）
*/
private String payType;
    @JsonProperty(value = "consign_time")
/**
* 卖家发货时间
*/
private Date consignTime;
    @JsonProperty(value = "sign_time")
/**
* 买家签收时间
*/
private Date signTime;
    @JsonProperty(value = "buyer_area")
/**
* 买家下单的地区
*/
private String buyerArea;
    @JsonProperty(value = "orders")
/**
* 交易明细数据结构
*/
private TradeOrderV2[] orders;
    @JsonProperty(value = "fetch_detail")
/**
* 到店自提详情
*/
private TradeFetch fetchDetail;
    @JsonProperty(value = "coupon_details")
/**
* 订单中使用到的卡券的数据结构
*/
private UmpTradeCoupon[] couponDetails;
    @JsonProperty(value = "fans_info")
/**
* 用户信息
*/
private FansInfo fansInfo;
    @JsonProperty(value = "hotel_info")
/**
* 酒店入住信息
*/
private HotelInfo hotelInfo;
    @JsonProperty(value = "promotion_details")
/**
* 订单中使用到的优惠活动的数据结构
*/
private TradePromotion[] promotionDetails;
    @JsonProperty(value = "adjust_fee")
/**
* 改价信息
*/
private AdjustFee adjustFee;
    @JsonProperty(value = "sub_trades")
/**
* 交易数据结构
*/
private TradeDetailV2[] subTrades;
    @JsonProperty(value = "relation_type")
/**
* 分销/采购单:source:采购单;fenxiao:分销单 空串则为非分销/采购单
*/
private String relationType;
    @JsonProperty(value = "relations")
/**
* relation_type返回source时,为分销订单号列表<br>
返回fenxiao时,为供应商订单号列表<br>
返回空时,列表返回空
*/
private String[] relations;
    @JsonProperty(value = "out_trade_no")
/**
* 代付订单外部交易号列表,非代付订单类型返回空
*/
private String[] outTradeNo;
    @JsonProperty(value = "profit")
/**
* 利润（分销订单特有）。格式：5.20；单位：元；精确到：分
*/
private Float profit;
    @JsonProperty(value = "handled")
/**
* 结算状态（分销订单特有）。1：已结算，0：未结算
*/
private Long handled;
    @JsonProperty(value = "outer_user_id")
/**
* 三方APP用户id
*/
private String outerUserId;
    @JsonProperty(value = "shop_id")
/**
* 多门店订单的门店id 非多门店订单则默认为0
*/
private Long shopId;
    @JsonProperty(value = "offline_id")
/**
* 表示线下网点id，包含自提点和门店
*/
private Long offlineId;
    @JsonProperty(value = "points_price")
/**
* 积分兑换订单，数值代表消耗的积分 非积分兑换订单默认为0
*/
private Long pointsPrice;
    @JsonProperty(value = "tuan_no")
/**
* 拼团订单对应的团编号
*/
private String tuanNo;
    @JsonProperty(value = "is_tuan_head")
/**
* 是否为团长订单 1 团长订单 0 非拼团订单 或 非团长订单
*/
private Long isTuanHead;
    @JsonProperty(value = "qr_id")
/**
* 收银台订单的二维码id号
*/
private Long qrId;
    @JsonProperty(value = "delivery_time_display")
/**
* 同城送订单送达时间
*/
private String deliveryTimeDisplay;
    
private String idCardNumber;

    public void setTid(String tid) {
    this.tid = tid;
}

public String getTid() {
    return this.tid;
}

    public void setNum(Long num) {
    this.num = num;
}

public Long getNum() {
    return this.num;
}

    public void setItemId(Long itemId) {
    this.itemId = itemId;
}

public Long getItemId() {
    return this.itemId;
}

    public void setPrice(Float price) {
    this.price = price;
}

public Float getPrice() {
    return this.price;
}

    public void setPicPath(String picPath) {
    this.picPath = picPath;
}

public String getPicPath() {
    return this.picPath;
}

    public void setPicThumbPath(String picThumbPath) {
    this.picThumbPath = picThumbPath;
}

public String getPicThumbPath() {
    return this.picThumbPath;
}

    public void setTitle(String title) {
    this.title = title;
}

public String getTitle() {
    return this.title;
}

    public void setType(String type) {
    this.type = type;
}

public String getType() {
    return this.type;
}

    public void setBuyerMessage(String buyerMessage) {
    this.buyerMessage = buyerMessage;
}

public String getBuyerMessage() {
    return this.buyerMessage;
}

    public void setSellerFlag(Long sellerFlag) {
    this.sellerFlag = sellerFlag;
}

public Long getSellerFlag() {
    return this.sellerFlag;
}

    public void setTradeMemo(String tradeMemo) {
    this.tradeMemo = tradeMemo;
}

public String getTradeMemo() {
    return this.tradeMemo;
}

    public void setReceiverCity(String receiverCity) {
    this.receiverCity = receiverCity;
}

public String getReceiverCity() {
    return this.receiverCity;
}

    public void setReceiverDistrict(String receiverDistrict) {
    this.receiverDistrict = receiverDistrict;
}

public String getReceiverDistrict() {
    return this.receiverDistrict;
}

    public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
}

public String getReceiverName() {
    return this.receiverName;
}

    public void setReceiverState(String receiverState) {
    this.receiverState = receiverState;
}

public String getReceiverState() {
    return this.receiverState;
}

    public void setReceiverAddress(String receiverAddress) {
    this.receiverAddress = receiverAddress;
}

public String getReceiverAddress() {
    return this.receiverAddress;
}

    public void setReceiverZip(String receiverZip) {
    this.receiverZip = receiverZip;
}

public String getReceiverZip() {
    return this.receiverZip;
}

    public void setReceiverMobile(String receiverMobile) {
    this.receiverMobile = receiverMobile;
}

public String getReceiverMobile() {
    return this.receiverMobile;
}

    public void setFeedback(Long feedback) {
    this.feedback = feedback;
}

public Long getFeedback() {
    return this.feedback;
}

    public void setRefundState(String refundState) {
    this.refundState = refundState;
}

public String getRefundState() {
    return this.refundState;
}

    public void setOuterTid(String outerTid) {
    this.outerTid = outerTid;
}

public String getOuterTid() {
    return this.outerTid;
}

    public void setTransactionTid(String transactionTid) {
    this.transactionTid = transactionTid;
}

public String getTransactionTid() {
    return this.transactionTid;
}

    public void setStatus(String status) {
    this.status = status;
}

public String getStatus() {
    return this.status;
}

    public void setShippingType(String shippingType) {
    this.shippingType = shippingType;
}

public String getShippingType() {
    return this.shippingType;
}

    public void setPostFee(Float postFee) {
    this.postFee = postFee;
}

public Float getPostFee() {
    return this.postFee;
}

    public void setTotalFee(Float totalFee) {
    this.totalFee = totalFee;
}

public Float getTotalFee() {
    return this.totalFee;
}

    public void setRefundedFee(Float refundedFee) {
    this.refundedFee = refundedFee;
}

public Float getRefundedFee() {
    return this.refundedFee;
}

    public void setDiscountFee(Float discountFee) {
    this.discountFee = discountFee;
}

public Float getDiscountFee() {
    return this.discountFee;
}

    public void setPayment(Float payment) {
    this.payment = payment;
}

public Float getPayment() {
    return this.payment;
}

    public void setCreated(Date created) {
    this.created = created;
}

public Date getCreated() {
    return this.created;
}

    public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
}

public Date getUpdateTime() {
    return this.updateTime;
}

    public void setPayTime(Date payTime) {
    this.payTime = payTime;
}

public Date getPayTime() {
    return this.payTime;
}

    public void setPayType(String payType) {
    this.payType = payType;
}

public String getPayType() {
    return this.payType;
}

    public void setConsignTime(Date consignTime) {
    this.consignTime = consignTime;
}

public Date getConsignTime() {
    return this.consignTime;
}

    public void setSignTime(Date signTime) {
    this.signTime = signTime;
}

public Date getSignTime() {
    return this.signTime;
}

    public void setBuyerArea(String buyerArea) {
    this.buyerArea = buyerArea;
}

public String getBuyerArea() {
    return this.buyerArea;
}

    public void setOrders(TradeOrderV2[] orders) {
    this.orders = orders;
}

public TradeOrderV2[] getOrders() {
    return this.orders;
}

    public void setFetchDetail(TradeFetch fetchDetail) {
    this.fetchDetail = fetchDetail;
}

public TradeFetch getFetchDetail() {
    return this.fetchDetail;
}

    public void setCouponDetails(UmpTradeCoupon[] couponDetails) {
    this.couponDetails = couponDetails;
}

public UmpTradeCoupon[] getCouponDetails() {
    return this.couponDetails;
}

    public void setFansInfo(FansInfo fansInfo) {
    this.fansInfo = fansInfo;
}

public FansInfo getFansInfo() {
    return this.fansInfo;
}

    public void setHotelInfo(HotelInfo hotelInfo) {
    this.hotelInfo = hotelInfo;
}

public HotelInfo getHotelInfo() {
    return this.hotelInfo;
}

    public void setPromotionDetails(TradePromotion[] promotionDetails) {
    this.promotionDetails = promotionDetails;
}

public TradePromotion[] getPromotionDetails() {
    return this.promotionDetails;
}

    public void setAdjustFee(AdjustFee adjustFee) {
    this.adjustFee = adjustFee;
}

public AdjustFee getAdjustFee() {
    return this.adjustFee;
}

    public void setSubTrades(TradeDetailV2[] subTrades) {
    this.subTrades = subTrades;
}

public TradeDetailV2[] getSubTrades() {
    return this.subTrades;
}

    public void setRelationType(String relationType) {
    this.relationType = relationType;
}

public String getRelationType() {
    return this.relationType;
}

    public void setRelations(String[] relations) {
    this.relations = relations;
}

public String[] getRelations() {
    return this.relations;
}

    public void setOutTradeNo(String[] outTradeNo) {
    this.outTradeNo = outTradeNo;
}

public String[] getOutTradeNo() {
    return this.outTradeNo;
}

    public void setProfit(Float profit) {
    this.profit = profit;
}

public Float getProfit() {
    return this.profit;
}

    public void setHandled(Long handled) {
    this.handled = handled;
}

public Long getHandled() {
    return this.handled;
}

    public void setOuterUserId(String outerUserId) {
    this.outerUserId = outerUserId;
}

public String getOuterUserId() {
    return this.outerUserId;
}

    public void setShopId(Long shopId) {
    this.shopId = shopId;
}

public Long getShopId() {
    return this.shopId;
}

    public void setOfflineId(Long offlineId) {
    this.offlineId = offlineId;
}

public Long getOfflineId() {
    return this.offlineId;
}

    public void setPointsPrice(Long pointsPrice) {
    this.pointsPrice = pointsPrice;
}

public Long getPointsPrice() {
    return this.pointsPrice;
}

    public void setTuanNo(String tuanNo) {
    this.tuanNo = tuanNo;
}

public String getTuanNo() {
    return this.tuanNo;
}

    public void setIsTuanHead(Long isTuanHead) {
    this.isTuanHead = isTuanHead;
}

public Long getIsTuanHead() {
    return this.isTuanHead;
}

    public void setQrId(Long qrId) {
    this.qrId = qrId;
}

public Long getQrId() {
    return this.qrId;
}

    public void setDeliveryTimeDisplay(String deliveryTimeDisplay) {
    this.deliveryTimeDisplay = deliveryTimeDisplay;
}

public String getDeliveryTimeDisplay() {
    return this.deliveryTimeDisplay;
}

public String getIdCardNumber() {
	return idCardNumber;
}

public void setIdCardNumber(String idCardNumber) {
	this.idCardNumber = idCardNumber;
}


}
