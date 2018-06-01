package com.wangqin.globalshop.channel.dal.youzan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.youzan.open.sdk.model.APIResult;

import java.util.Date;

public class YouzanTradesSoldGetResult implements APIResult {
	//搜索到的交易总数
	@JsonProperty(value = "total_results")
	private Long totalResults;
	
	
    //是否存在下一页
    @JsonProperty(value = "has_next")
    private Boolean hasNext;
    
    //搜索到的交易列表
    @JsonProperty(value = "trades")
    private TradeDetailV2[] trades;

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

    public Long getTotalResults() {
        return this.totalResults;
    }
    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Boolean getHasNext() {
        return this.hasNext;
    }
    public void setTrades(TradeDetailV2[] trades) {
        this.trades = trades;
    }

    public TradeDetailV2[] getTrades() {
        return this.trades;
    }

    public static class HotelInfo {
    	//入住时间
    	@JsonProperty(value = "check_in_time")
        private String checkInTime;
    	
    	//退房时间
    	@JsonProperty(value = "check_out_time")
        private String checkOutTime;
    	
    	//入住人信息
    	@JsonProperty(value = "accommodates")
        private String[] accommodates;
    
    	public void setCheckInTime(String checkInTime) {
            this.checkInTime = checkInTime;
        }

        public String getCheckInTime() {
            return this.checkInTime;
        }

        public void setCheckOutTime(String checkOutTime) {
            this.checkOutTime = checkOutTime;
        }

        public String getCheckOutTime() {
            return this.checkOutTime;
        }

        public void setAccommodates(String[] accommodates) {
            this.accommodates = accommodates;
        }

        public String[] getAccommodates() {
            return this.accommodates;
        }

	}

//    public static class TradeDetailV2 {
//    	//交易编号
//    	@JsonProperty(value = "tid")
//        private String tid;
//    	
//    	//商品购买数量。当一个trade对应多个order的时候，值为所有商品购买数量之和
//    	@JsonProperty(value = "num")
//        private Long num;
//    	
//    	//商品数字编号。当一个trade对应多个order的时候，值为第一个交易明细中的商品的编号
//    	@JsonProperty(value = "item_id")
//        private Long itemId;
//    	
//    	//商品价格。精确到2位小数；单位：元。当一个trade对应多个order的时候，值为第一个交易明细中的商品的价格
//    	@JsonProperty(value = "price")
//        private Float price;
//    	
//    	//商品主图片地址。当一个trade对应多个order的时候，值为第一个交易明细中的商品的图片地址
//    	@JsonProperty(value = "pic_path")
//        private String picPath;
//    	
//    	//商品主图片缩略图地址
//    	@JsonProperty(value = "pic_thumb_path")
//        private String picThumbPath;
//    	
//    	//交易标题，以首个商品标题作为此标题的值
//    	@JsonProperty(value = "title")
//        private String title;
//    	
//    	//身份证
//    	@JsonProperty(value = "id_card_number")
//        private String idCardNumber;
//    	
//    	/**
//         * 交易类型。取值范围：
// 		 *	FIXED （一口价）
// 		 *	GIFT （送礼）
// 		 *	BULK_PURCHASE（来自分销商的采购）
// 		 *	PRESENT （赠品领取）
// 		 *	GROUP （拼团订单）
// 		 *	PIFA （批发订单）
// 		 *	COD （货到付款）
// 		 *	PEER （代付）
// 		 *	QRCODE（扫码商家二维码直接支付的交易）
// 		 *	QRCODE_3RD（线下收银台二维码交易)
//         */
//        @JsonProperty(value = "type")
//        private String type;
//        
//        //买家购买附言
//        @JsonProperty(value = "buyer_message")
//        private String buyerMessage;
//        
//        //卖家备注星标，取值范围 1、2、3、4、5；如果为0，表示没有备注星标
//        @JsonProperty(value = "seller_flag")
//        private Long sellerFlag;
//        
//        //卖家对该交易的备注
//        @JsonProperty(value = "trade_memo")
//        private String tradeMemo;
//        
//        /**
//         * 收货人的所在城市。
// 		 * PS：如果订单类型是送礼订单，收货地址在sub_trades字段中；如果物流方式是到店自提，收货地址在fetch_detail字段中
//         */
//        @JsonProperty(value = "receiver_city")
//        private String receiverCity;
//        
//        //收货人的所在地区
//        @JsonProperty(value = "receiver_district")
//        private String receiverDistrict;
//        
//        //收货人的姓名
//        @JsonProperty(value = "receiver_name")
//        private String receiverName;
//        
//        //收货人的所在省份
//        @JsonProperty(value = "receiver_state")
//        private String receiverState;
//        
//        //收货人的详细地址
//        @JsonProperty(value = "receiver_address")
//        private String receiverAddress;
//        
//        //收货人的邮编
//        @JsonProperty(value = "receiver_zip")
//        private String receiverZip;
//        
//        //收货人的手机号码
//        @JsonProperty(value = "receiver_mobile")
//        private String receiverMobile;
//        
//        /**
//         * 交易维权状态。
// 		 * 0 无维权，1 顾客发起维权，2 顾客拒绝商家的处理结果，3 顾客接受商家的处理结果，9 商家正在处理,101 维权处理中,110 维权结束。
// 		 * 备注：1到10的状态码是微信维权状态码，100以上的状态码是有赞维权状态码
//         */
//        @JsonProperty(value = "feedback")
//        private Long feedback;
//        
//        /**
//         * 退款状态。取值范围：
//		 * NO_REFUND（无退款）
//		 * PARTIAL_REFUNDING（部分退款中）
//		 * PARTIAL_REFUNDED（已部分退款）
//		 * PARTIAL_REFUND_FAILED（部分退款失败）
//		 * FULL_REFUNDING（全额退款中）
//		 * FULL_REFUNDED（已全额退款）
//		 * FULL_REFUND_FAILED（全额退款失败）
//         */
//        @JsonProperty(value = "refund_state")
//        private String refundState;
//        
//        //外部交易编号。比如，如果支付方式是微信支付，就是财付通的交易单号
//        @JsonProperty(value = "outer_tid")
//        private String outerTid;
//        
//        //支付流水号
//        @JsonProperty(value = "transaction_tid")
//        private String transactionTid;
//        
//        /**
//         * 交易状态。取值范围：
//		 * TRADE_NO_CREATE_PAY (没有创建支付交易) 
//		 * WAIT_BUYER_PAY (等待买家付款) 
//		 * WAIT_PAY_RETURN (等待支付确认) 
//		 * WAIT_GROUP（等待成团，即：买家已付款，等待成团）
//		 * WAIT_SELLER_SEND_GOODS (等待卖家发货，即：买家已付款) 
//		 * WAIT_BUYER_CONFIRM_GOODS (等待买家确认收货，即：卖家已发货) 
//		 * TRADE_BUYER_SIGNED (买家已签收) 
//		 * TRADE_CLOSED (付款以后用户退款成功，交易自动关闭) 
//		 * TRADE_CLOSED_BY_USER (付款以前，卖家或买家主动关闭交易)
//         */
//        @JsonProperty(value = "status")
//        private String status;
//        
//        //创建交易时的物流方式。取值范围：express（快递），fetch（到店自提），local（同城配送）
//        @JsonProperty(value = "shipping_type")
//        private String shippingType;
//        
//        //运费。单位：元，精确到分
//        @JsonProperty(value = "post_fee")
//        private Float postFee;
//        
//        //商品总价（商品价格乘以数量的总金额）。单位：元，精确到分
//        @JsonProperty(value = "total_fee")
//        private Float totalFee;
//        
//        //交易完成后退款的金额。单位：元，精确到分
//        @JsonProperty(value = "refunded_fee")
//        private Float refundedFee;
//        
//        //交易优惠金额（不包含交易明细中的优惠金额）。单位：元，精确到分
//        @JsonProperty(value = "discount_fee")
//        private Float discountFee;
//        
//        //实付金额。单位：元，精确到分
//        @JsonProperty(value = "payment")
//        private Float payment;
//        
//        //交易创建时间
//        @JsonProperty(value = "created")
//        private Date created;
//        
//        //交易更新时间。当交易的：状态改变、备注更改、星标更改 等情况下都会刷新更新时间
//        @JsonProperty(value = "update_time")
//        private Date updateTime;
//        
//        //买家付款时间
//        @JsonProperty(value = "pay_time")
//        private Date payTime;
//        
//        /**
//         * 支付类型。取值范围：
//		 * WEIXIN (微信自有支付)
//		 * WEIXIN_DAIXIAO (微信代销支付)
//		 * ALIPAY (支付宝支付)
//		 * BANKCARDPAY (银行卡支付)
//		 * PEERPAY (代付)
//		 * CODPAY (货到付款)
//		 * BAIDUPAY (百度钱包支付)
//		 * PRESENTTAKE (直接领取赠品)
//		 * COUPONPAY（优惠券/码全额抵扣）
//		 * BULKPURCHASE（来自分销商的采购）
//		 * MERGEDPAY (合并付货款) 
//		 * ECARD（有赞E卡支付）
//         */
//        @JsonProperty(value = "pay_type")
//        private String payType;
//        
//        //卖家发货时间
//        @JsonProperty(value = "consign_time")
//        private Date consignTime;
//        
//        //买家签收时间
//        @JsonProperty(value = "sign_time")
//        private Date signTime;
//        
//        //买家下单的地区
//        @JsonProperty(value = "buyer_area")
//        private String buyerArea;
//        
//        //交易明细数据结构
//        @JsonProperty(value = "orders")
//        private TradeOrderV2[] orders;
//        
//        //到店自提详情
//        @JsonProperty(value = "fetch_detail")
//        private TradeFetch fetchDetail;
//        
//        //订单中使用到的卡券的数据结构
//        @JsonProperty(value = "coupon_details")
//        private UmpTradeCoupon[] couponDetails;
//        
//        //用户信息
//        @JsonProperty(value = "fans_info")
//        private FansInfo fansInfo;
//            
//        //酒店入住信息
//        @JsonProperty(value = "hotel_info")
//        private HotelInfo hotelInfo;
//            
//        //订单中使用到的优惠活动的数据结构
//        @JsonProperty(value = "promotion_details")
//        private TradePromotion[] promotionDetails;
//        
//        //改价信息
//        @JsonProperty(value = "adjust_fee")
//        private AdjustFee adjustFee;
//        
//        //交易中包含的子交易，目前：仅在送礼订单中会有子交易
//        @JsonProperty(value = "sub_trades")
//        private TradeDetailV2[] subTrades;
//        
//        //分销/采购单:source:采购单;fenxiao:分销单 空串则为非分销/采购单
//        @JsonProperty(value = "relation_type")
//        private String relationType;
//            
//        /**
//         * relation_type返回source时,为分销订单号列表
// 		 * 返回fenxiao时,为供应商订单号列表
// 		 * 返回空时,列表返回空
//         */
//        @JsonProperty(value = "relations")
//        private String[] relations;
//        
//        //代付订单外部交易号列表,非代付订单类型返回空
//        @JsonProperty(value = "out_trade_no")
//        private String[] outTradeNo;
//        
//        //利润（分销订单特有）。格式：5.20；单位：元；精确到：分
//        @JsonProperty(value = "profit")
//        private Float profit;
//        
//        //结算状态（分销订单特有）。1：已结算，0：未结算
//        @JsonProperty(value = "handled")
//        private Long handled;
//            
//        //三方APP用户id
//        @JsonProperty(value = "outer_user_id")
//        private String outerUserId;
//            
//        //多门店订单的门店id 非多门店订单则默认为0 
//        @JsonProperty(value = "shop_id")
//        private Long shopId;
//            
//        //表示线下网点id，包含自提点和门店
//        @JsonProperty(value = "offline_id")
//        private Long offlineId;
//            
//        //积分兑换订单，数值代表消耗的积分 非积分兑换订单默认为0 
//        @JsonProperty(value = "points_price")
//        private Long pointsPrice;
//        
//        //拼团订单对应的团编号
//        @JsonProperty(value = "tuan_no")
//        private String tuanNo;
//        
//        //是否为团长订单 1 团长订单 0 非拼团订单 或 非团长订单
//        @JsonProperty(value = "is_tuan_head")
//        private Long isTuanHead;
//            
//        //收银台订单的二维码id号
//        @JsonProperty(value = "qr_id")
//        private Long qrId;
//            
//        //同城送订单送达时间
//        @JsonProperty(value = "delivery_time_display")
//        private String deliveryTimeDisplay;
//    
//        public void setTid(String tid) {
//            this.tid = tid;
//        }
//
//        public String getTid() {
//            return this.tid;
//        }
//
//        public void setNum(Long num) {
//            this.num = num;
//        }
//
//        public Long getNum() {
//            return this.num;
//        }
//
//        public void setItemId(Long itemId) {
//            this.itemId = itemId;
//        }
//
//        public Long getItemId() {
//            return this.itemId;
//        }
//
//        public void setPrice(Float price) {
//            this.price = price;
//        }
//
//        public Float getPrice() {
//            return this.price;
//        }
//
//        public void setPicPath(String picPath) {
//            this.picPath = picPath;
//        }
//
//        public String getPicPath() {
//            return this.picPath;
//        }
//
//        public void setPicThumbPath(String picThumbPath) {
//            this.picThumbPath = picThumbPath;
//        }
//
//        public String getPicThumbPath() {
//            return this.picThumbPath;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getTitle() {
//            return this.title;
//        }
//
//        public String getIdCardNumber() {
//			return idCardNumber;
//		}
//
//		public void setIdCardNumber(String idCardNumber) {
//			this.idCardNumber = idCardNumber;
//		}
//
//		public void setType(String type) {
//            this.type = type;
//        }
//
//        public String getType() {
//            return this.type;
//        }
//
//        public void setBuyerMessage(String buyerMessage) {
//            this.buyerMessage = buyerMessage;
//        }
//
//        public String getBuyerMessage() {
//            return this.buyerMessage;
//        }
//
//        public void setSellerFlag(Long sellerFlag) {
//            this.sellerFlag = sellerFlag;
//        }
//
//        public Long getSellerFlag() {
//            return this.sellerFlag;
//        }
//
//        public void setTradeMemo(String tradeMemo) {
//            this.tradeMemo = tradeMemo;
//        }
//
//        public String getTradeMemo() {
//            return this.tradeMemo;
//        }
//
//        public void setReceiverCity(String receiverCity) {
//            this.receiverCity = receiverCity;
//        }
//
//        public String getReceiverCity() {
//            return this.receiverCity;
//        }
//
//        public void setReceiverDistrict(String receiverDistrict) {
//            this.receiverDistrict = receiverDistrict;
//        }
//
//        public String getReceiverDistrict() {
//            return this.receiverDistrict;
//        }
//
//        public void setReceiverName(String receiverName) {
//            this.receiverName = receiverName;
//        }
//
//        public String getReceiverName() {
//            return this.receiverName;
//        }
//
//        public void setReceiverState(String receiverState) {
//            this.receiverState = receiverState;
//        }
//
//        public String getReceiverState() {
//            return this.receiverState;
//        }
//
//        public void setReceiverAddress(String receiverAddress) {
//            this.receiverAddress = receiverAddress;
//        }
//
//        public String getReceiverAddress() {
//            return this.receiverAddress;
//        }
//
//        public void setReceiverZip(String receiverZip) {
//            this.receiverZip = receiverZip;
//        }
//
//        public String getReceiverZip() {
//            return this.receiverZip;
//        }
//
//        public void setReceiverMobile(String receiverMobile) {
//            this.receiverMobile = receiverMobile;
//        }
//
//        public String getReceiverMobile() {
//            return this.receiverMobile;
//        }
//
//        public void setFeedback(Long feedback) {
//            this.feedback = feedback;
//        }
//
//        public Long getFeedback() {
//            return this.feedback;
//        }
//
//        public void setRefundState(String refundState) {
//            this.refundState = refundState;
//        }
//
//        public String getRefundState() {
//            return this.refundState;
//        }
//
//        public void setOuterTid(String outerTid) {
//            this.outerTid = outerTid;
//        }
//
//        public String getOuterTid() {
//            return this.outerTid;
//        }
//
//        public void setTransactionTid(String transactionTid) {
//            this.transactionTid = transactionTid;
//        }
//
//        public String getTransactionTid() {
//            return this.transactionTid;
//        }
//
//        public void setStatus(String status) {
//            this.status = status;
//        }
//
//        public String getStatus() {
//            return this.status;
//        }
//
//        public void setShippingType(String shippingType) {
//            this.shippingType = shippingType;
//        }
//
//        public String getShippingType() {
//            return this.shippingType;
//        }
//	
//        public void setPostFee(Float postFee) {
//            this.postFee = postFee;
//        }
//
//        public Float getPostFee() {
//            return this.postFee;
//        }
//
//        public void setTotalFee(Float totalFee) {
//            this.totalFee = totalFee;
//        }
//
//        public Float getTotalFee() {
//            return this.totalFee;
//        }
//
//        public void setRefundedFee(Float refundedFee) {
//            this.refundedFee = refundedFee;
//        }
//
//        public Float getRefundedFee() {
//            return this.refundedFee;
//        }
//
//        public void setDiscountFee(Float discountFee) {
//            this.discountFee = discountFee;
//        }
//
//        public Float getDiscountFee() {
//            return this.discountFee;
//        }
//
//        public void setPayment(Float payment) {
//            this.payment = payment;
//        }
//
//        public Float getPayment() {
//            return this.payment;
//        }
//
//        public void setCreated(Date created) {
//            this.created = created;
//        }
//
//        public Date getCreated() {
//            return this.created;
//        }
//
//        public void setUpdateTime(Date updateTime) {
//            this.updateTime = updateTime;
//        }
//
//        public Date getUpdateTime() {
//            return this.updateTime;
//        }
//
//        public void setPayTime(Date payTime) {
//            this.payTime = payTime;
//        }
//
//        public Date getPayTime() {
//            return this.payTime;
//        }
//
//        public void setPayType(String payType) {
//            this.payType = payType;
//        }
//
//        public String getPayType() {
//            return this.payType;
//        }
//
//        public void setConsignTime(Date consignTime) {
//            this.consignTime = consignTime;
//        }
//
//        public Date getConsignTime() {
//            return this.consignTime;
//        }
//
//        public void setSignTime(Date signTime) {
//            this.signTime = signTime;
//        }
//
//        public Date getSignTime() {
//            return this.signTime;
//        }
//
//        public void setBuyerArea(String buyerArea) {
//            this.buyerArea = buyerArea;
//        }
//
//        public String getBuyerArea() {
//            return this.buyerArea;
//        }
//
//        public void setOrders(TradeOrderV2[] orders) {
//            this.orders = orders;
//        }
//
//        public TradeOrderV2[] getOrders() {
//            return this.orders;
//        }
//
//        public void setFetchDetail(TradeFetch fetchDetail) {
//            this.fetchDetail = fetchDetail;
//        }
//
//        public TradeFetch getFetchDetail() {
//            return this.fetchDetail;
//        }
//
//        public void setCouponDetails(UmpTradeCoupon[] couponDetails) {
//            this.couponDetails = couponDetails;
//        }
//
//        public UmpTradeCoupon[] getCouponDetails() {
//            return this.couponDetails;
//        }
//
//        public void setFansInfo(FansInfo fansInfo) {
//            this.fansInfo = fansInfo;
//        }
//
//        public FansInfo getFansInfo() {
//            return this.fansInfo;
//        }
//
//        public void setHotelInfo(HotelInfo hotelInfo) {
//            this.hotelInfo = hotelInfo;
//        }
//
//        public HotelInfo getHotelInfo() {
//            return this.hotelInfo;
//        }
//
//        public void setPromotionDetails(TradePromotion[] promotionDetails) {
//            this.promotionDetails = promotionDetails;
//        }
//
//        public TradePromotion[] getPromotionDetails() {
//            return this.promotionDetails;
//        }
//
//        public void setAdjustFee(AdjustFee adjustFee) {
//            this.adjustFee = adjustFee;
//        }
//
//        public AdjustFee getAdjustFee() {
//            return this.adjustFee;
//        }
//
//        public void setSubTrades(TradeDetailV2[] subTrades) {
//            this.subTrades = subTrades;
//        }
//
//        public TradeDetailV2[] getSubTrades() {
//            return this.subTrades;
//        }
//
//        public void setRelationType(String relationType) {
//            this.relationType = relationType;
//        }
//
//        public String getRelationType() {
//            return this.relationType;
//        }
//
//        public void setRelations(String[] relations) {
//            this.relations = relations;
//        }
//
//        public String[] getRelations() {
//            return this.relations;
//        }
//
//        public void setOutTradeNo(String[] outTradeNo) {
//            this.outTradeNo = outTradeNo;
//        }
//
//        public String[] getOutTradeNo() {
//            return this.outTradeNo;
//        }
//
//        public void setProfit(Float profit) {
//            this.profit = profit;
//        }
//
//        public Float getProfit() {
//            return this.profit;
//        }
//
//        public void setHandled(Long handled) {
//            this.handled = handled;
//        }
//
//        public Long getHandled() {
//            return this.handled;
//        }
//
//        public void setOuterUserId(String outerUserId) {
//            this.outerUserId = outerUserId;
//        }
//
//        public String getOuterUserId() {
//            return this.outerUserId;
//        }
//
//        public void setShopId(Long shopId) {
//            this.shopId = shopId;
//        }
//
//        public Long getShopId() {
//            return this.shopId;
//        }
//
//        public void setOfflineId(Long offlineId) {
//            this.offlineId = offlineId;
//        }
//
//        public Long getOfflineId() {
//            return this.offlineId;
//        }
//
//        public void setPointsPrice(Long pointsPrice) {
//            this.pointsPrice = pointsPrice;
//        }
//
//        public Long getPointsPrice() {
//            return this.pointsPrice;
//        }
//
//        public void setTuanNo(String tuanNo) {
//            this.tuanNo = tuanNo;
//        }
//
//        public String getTuanNo() {
//            return this.tuanNo;
//        }
//
//        public void setIsTuanHead(Long isTuanHead) {
//            this.isTuanHead = isTuanHead;
//        }
//
//        public Long getIsTuanHead() {
//            return this.isTuanHead;
//        }
//
//        public void setQrId(Long qrId) {
//            this.qrId = qrId;
//        }
//
//        public Long getQrId() {
//            return this.qrId;
//        }
//
//        public void setDeliveryTimeDisplay(String deliveryTimeDisplay) {
//            this.deliveryTimeDisplay = deliveryTimeDisplay;
//        }
//
//        public String getDeliveryTimeDisplay() {
//            return this.deliveryTimeDisplay;
//        }
//
//	}

    public static class TradeFetch {
    	//领取人（即：预约人）的姓名
    	@JsonProperty(value = "fetcher_name")
        private String fetcherName;
    	
    	//领取人的手机号
    	@JsonProperty(value = "fetcher_mobile")
        private String fetcherMobile;
    	
    	/**
         * 预约的领取时间。新版到店自提的数据格式:
		 * 1: 2016-04-18 17:00-17:15
		 * 2: 尽快到店提货
         */
    	@JsonProperty(value = "fetch_time")
        private String fetchTime;
    	
    	//自提点id
    	@JsonProperty(value = "shop_id")
        private Long shopId;
    	
    	//自提点名称
    	@JsonProperty(value = "shop_name")
        private String shopName;
            
    	//自提点联系方式
    	@JsonProperty(value = "shop_mobile")
        private String shopMobile;
            
    	//自提点所在省份
    	@JsonProperty(value = "shop_state")
        private String shopState;
            
    	//自提点所在城市
    	@JsonProperty(value = "shop_city")
        private String shopCity;
        
    	//自提点所在地区
    	@JsonProperty(value = "shop_district")
        private String shopDistrict;
            
    	//自提点详细地址
    	@JsonProperty(value = "shop_address")
        private String shopAddress;
    
    	public void setFetcherName(String fetcherName) {
            this.fetcherName = fetcherName;
        }

        public String getFetcherName() {
            return this.fetcherName;
        }

        public void setFetcherMobile(String fetcherMobile) {
            this.fetcherMobile = fetcherMobile;
        }

        public String getFetcherMobile() {
            return this.fetcherMobile;
        }

        public void setFetchTime(String fetchTime) {
            this.fetchTime = fetchTime;
        }

        public String getFetchTime() {
            return this.fetchTime;
        }

        public void setShopId(Long shopId) {
            this.shopId = shopId;
        }

        public Long getShopId() {
            return this.shopId;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopName() {
            return this.shopName;
        }

        public void setShopMobile(String shopMobile) {
            this.shopMobile = shopMobile;
        }

        public String getShopMobile() {
            return this.shopMobile;
        }

        public void setShopState(String shopState) {
            this.shopState = shopState;
        }

        public String getShopState() {
            return this.shopState;
        }

        public void setShopCity(String shopCity) {
            this.shopCity = shopCity;
        }

        public String getShopCity() {
            return this.shopCity;
        }

        public void setShopDistrict(String shopDistrict) {
            this.shopDistrict = shopDistrict;
        }

        public String getShopDistrict() {
            return this.shopDistrict;
        }

        public void setShopAddress(String shopAddress) {
            this.shopAddress = shopAddress;
        }

        public String getShopAddress() {
            return this.shopAddress;
        }

	}

    public static class TradePromotion {
    	//该优惠活动的ID
    	@JsonProperty(value = "promotion_id")
        private Long promotionId;
            
    	//该优惠活动的名称
    	@JsonProperty(value = "promotion_name")
        private String promotionName;
            
    	//优惠的类型。可选值：<br> FULLREDUCE（满减满送）<br> ORDERCASHBACK（订单返现）<br> GROUPBUYING（商品团购）<br> GROUPON（多人拼团）<br> SECKILL（秒杀）<br> AUCTION（降价拍）
    	@JsonProperty(value = "promotion_type")
        private String promotionType;
            
    	//优惠使用条件说明
    	@JsonProperty(value = "promotion_condition")
        private String promotionCondition;
            
    	//使用时间
    	@JsonProperty(value = "used_at")
        private Date usedAt;
            
    	//优惠的金额，单位：元，精确到小数点后两位
    	@JsonProperty(value = "discount_fee")
        private Float discountFee;
    
    	public void setPromotionId(Long promotionId) {
            this.promotionId = promotionId;
        }

        public Long getPromotionId() {
            return this.promotionId;
        }

        public void setPromotionName(String promotionName) {
            this.promotionName = promotionName;
        }

        public String getPromotionName() {
            return this.promotionName;
        }

        public void setPromotionType(String promotionType) {
            this.promotionType = promotionType;
        }

        public String getPromotionType() {
            return this.promotionType;
        }

        public void setPromotionCondition(String promotionCondition) {
            this.promotionCondition = promotionCondition;
        }

        public String getPromotionCondition() {
            return this.promotionCondition;
        }

        public void setUsedAt(Date usedAt) {
            this.usedAt = usedAt;
        }

        public Date getUsedAt() {
            return this.usedAt;
        }

        public void setDiscountFee(Float discountFee) {
            this.discountFee = discountFee;
        }

        public Float getDiscountFee() {
            return this.discountFee;
        }

	}

    public static class TradeBuyerMessage {
    	//留言的标题
    	@JsonProperty(value = "title")
        private String title;
            
    	//留言的内容
    	@JsonProperty(value = "content")
        private String content;
    
    	public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

	}
    

    public static class FansInfo {
            @JsonProperty(value = "fans_nickname")
        /**
        * 粉丝昵称 订单信息中存在三方(例如微信)粉丝昵称则取粉丝昵称;取不到粉丝昵称时则使用买家手机号;以上两点未满足时取买家收货人信息;无收货人信息时返回[匿名]
        */
        private String fansNickname;
            @JsonProperty(value = "fans_id")
        /**
        * 粉丝id
        */
        private Long fansId;
            @JsonProperty(value = "buyer_id")
        /**
        * 有赞买家ID
        */
        private Long buyerId;
            @JsonProperty(value = "fans_type")
        /**
        * 0:未知、1:微信自有粉丝
        */
        private Long fansType;
    
            public void setFansNickname(String fansNickname) {
            this.fansNickname = fansNickname;
        }

        public String getFansNickname() {
            return this.fansNickname;
        }

            public void setFansId(Long fansId) {
            this.fansId = fansId;
        }

        public Long getFansId() {
            return this.fansId;
        }

            public void setBuyerId(Long buyerId) {
            this.buyerId = buyerId;
        }

        public Long getBuyerId() {
            return this.buyerId;
        }

            public void setFansType(Long fansType) {
            this.fansType = fansType;
        }

        public Long getFansType() {
            return this.fansType;
        }

        }

    public static class UmpTradeCoupon {
            @JsonProperty(value = "coupon_id")
        /**
        * 该组卡券的ID
        */
        private Long couponId;
            @JsonProperty(value = "coupon_name")
        /**
        * 该组卡券的名称
        */
        private String couponName;
            @JsonProperty(value = "coupon_type")
        /**
        * 卡券的类型。可选值：PROMOCARD（优惠券）、PROMOCODE（优惠码）
        */
        private String couponType;
            @JsonProperty(value = "coupon_content")
        /**
        * 卡券内容。当卡券类型为优惠码时，值为优惠码字符串
        */
        private String couponContent;
            @JsonProperty(value = "coupon_description")
        /**
        * 卡券的说明
        */
        private String couponDescription;
            @JsonProperty(value = "coupon_condition")
        /**
        * 卡券使用条件说明
        */
        private String couponCondition;
            @JsonProperty(value = "used_at")
        /**
        * 使用时间
        */
        private Date usedAt;
            @JsonProperty(value = "discount_fee")
        /**
        * 优惠的金额，单位：元，精确到小数点后两位
        */
        private Float discountFee;
    
            public void setCouponId(Long couponId) {
            this.couponId = couponId;
        }

        public Long getCouponId() {
            return this.couponId;
        }

            public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getCouponName() {
            return this.couponName;
        }

            public void setCouponType(String couponType) {
            this.couponType = couponType;
        }

        public String getCouponType() {
            return this.couponType;
        }

            public void setCouponContent(String couponContent) {
            this.couponContent = couponContent;
        }

        public String getCouponContent() {
            return this.couponContent;
        }

            public void setCouponDescription(String couponDescription) {
            this.couponDescription = couponDescription;
        }

        public String getCouponDescription() {
            return this.couponDescription;
        }

            public void setCouponCondition(String couponCondition) {
            this.couponCondition = couponCondition;
        }

        public String getCouponCondition() {
            return this.couponCondition;
        }

            public void setUsedAt(Date usedAt) {
            this.usedAt = usedAt;
        }

        public Date getUsedAt() {
            return this.usedAt;
        }

            public void setDiscountFee(Float discountFee) {
            this.discountFee = discountFee;
        }

        public Float getDiscountFee() {
            return this.discountFee;
        }

        }

    public static class TradeOrderV2 {
    	//交易明细编号。该编号并不唯一，只用于区分交易内的多条明细记录
    	@JsonProperty(value = "oid")
        private Long oid;
    	
    	//商品数字编号
    	@JsonProperty(value = "item_id")
        private Long itemId;
            
    	//Sku的ID，sku_id 在系统里并不是唯一的，结合商品ID一起使用才是唯一的。
    	@JsonProperty(value = "sku_id")
        private Long skuId;
    	
    	//Sku在系统中的唯一编号，可以在开发者的系统中用作 Sku 的唯一ID，但不能用于调用接口
        @JsonProperty(value = "sku_unique_code")
        private String skuUniqueCode;
        
        //商品购买数量
        @JsonProperty(value = "num")
        private Long num;
        
        //商家编码（商家为Sku设置的外部编号）
        @JsonProperty(value = "outer_sku_id")
        private String outerSkuId;
        
        //商品货号（商家为商品设置的外部编号）
        @JsonProperty(value = "outer_item_id")
        private String outerItemId;
        
        //商品标题
        @JsonProperty(value = "title")
        private String title;
            
        //卖家昵称
        @JsonProperty(value = "seller_nick")
        private String sellerNick;
            
        //商品在分销商那边的出售价格。精确到2位小数；单位：元。如果是采购单才有值，否则值为 0
        @JsonProperty(value = "fenxiao_price")
        private Float fenxiaoPrice;
            
        //商品在分销商那边的实付金额。精确到2位小数；单位：元。如果是采购单才有值，否则值为 0
        @JsonProperty(value = "fenxiao_payment")
        private Float fenxiaoPayment;
            
        //商品价格。精确到2位小数；单位：元
        @JsonProperty(value = "price")
        private Float price;
            
        //应付金额（商品价格乘以数量的总金额）
        @JsonProperty(value = "total_fee")
        private Float totalFee;
        
        //交易明细内的优惠金额。精确到2位小数，单位：元
        @JsonProperty(value = "discount_fee")
        private Float discountFee;
            
        //实付金额。精确到2位小数，单位：元
        @JsonProperty(value = "payment")
        private Float payment;
            
        //SKU的值，即：商品的规格。如：机身颜色:黑色;手机套餐:官方标配
        @JsonProperty(value = "sku_properties_name")
        private String skuPropertiesName;
            
        //商品主图片地址
        @JsonProperty(value = "pic_path")
        private String picPath;
        
        //商品主图片缩略图地址
        @JsonProperty(value = "pic_thumb_path")
        private String picThumbPath;
            
        //商品类型。0：普通商品；10：分销商品;
        @JsonProperty(value = "item_type")
        private Long itemType;
        
        //交易明细中买家留言的数据结构
        @JsonProperty(value = "buyer_messages")
        private TradeBuyerMessage[] buyerMessages;
            
        //交易明细中的优惠信息的数据结构
        @JsonProperty(value = "order_promotion_details")
        private TradeOrderPromotion[] orderPromotionDetails;
            
        //商品状态
        @JsonProperty(value = "state_str")
        private String stateStr;
        
        //商品退款状态
        @JsonProperty(value = "item_refund_state")
        private String itemRefundState;
           
        //1 虚拟商品 0 非虚拟商品
        @JsonProperty(value = "is_virtual")
        private Long isVirtual;
        
        //1 赠品商品 0 普通商品
        @JsonProperty(value = "is_present")
        private Long isPresent;
            
        //退款金额
        @JsonProperty(value = "refunded_fee")
        private Float refundedFee;
            
        //是否允许发货  1 可以发货 0 不能发货
        @JsonProperty(value = "allow_send")
        private Long allowSend;
    
        public void setOid(Long oid) {
            this.oid = oid;
        }

        public Long getOid() {
            return this.oid;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public Long getItemId() {
            return this.itemId;
        }

        public void setSkuId(Long skuId) {
            this.skuId = skuId;
        }

        public Long getSkuId() {
            return this.skuId;
        }

        public void setSkuUniqueCode(String skuUniqueCode) {
            this.skuUniqueCode = skuUniqueCode;
        }

        public String getSkuUniqueCode() {
            return this.skuUniqueCode;
        }

        public void setNum(Long num) {
            this.num = num;
        }

        public Long getNum() {
            return this.num;
        }

        public void setOuterSkuId(String outerSkuId) {
            this.outerSkuId = outerSkuId;
        }

        public String getOuterSkuId() {
            return this.outerSkuId;
        }

        public void setOuterItemId(String outerItemId) {
            this.outerItemId = outerItemId;
        }

        public String getOuterItemId() {
            return this.outerItemId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setSellerNick(String sellerNick) {
            this.sellerNick = sellerNick;
        }

        public String getSellerNick() {
            return this.sellerNick;
        }

        public void setFenxiaoPrice(Float fenxiaoPrice) {
            this.fenxiaoPrice = fenxiaoPrice;
        }

        public Float getFenxiaoPrice() {
            return this.fenxiaoPrice;
        }
	
        public void setFenxiaoPayment(Float fenxiaoPayment) {
            this.fenxiaoPayment = fenxiaoPayment;
        }

        public Float getFenxiaoPayment() {
            return this.fenxiaoPayment;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        public Float getPrice() {
            return this.price;
        }

        public void setTotalFee(Float totalFee) {
            this.totalFee = totalFee;
        }

        public Float getTotalFee() {
            return this.totalFee;
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

        public void setSkuPropertiesName(String skuPropertiesName) {
            this.skuPropertiesName = skuPropertiesName;
        }

        public String getSkuPropertiesName() {
            return this.skuPropertiesName;
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

        public void setItemType(Long itemType) {
            this.itemType = itemType;
        }

        public Long getItemType() {
            return this.itemType;
        }

        public void setBuyerMessages(TradeBuyerMessage[] buyerMessages) {
            this.buyerMessages = buyerMessages;
        }

        public TradeBuyerMessage[] getBuyerMessages() {
            return this.buyerMessages;
        }

        public void setOrderPromotionDetails(TradeOrderPromotion[] orderPromotionDetails) {
            this.orderPromotionDetails = orderPromotionDetails;
        }

        public TradeOrderPromotion[] getOrderPromotionDetails() {
            return this.orderPromotionDetails;
        }

        public void setStateStr(String stateStr) {
            this.stateStr = stateStr;
        }

        public String getStateStr() {
            return this.stateStr;
        }

        public void setItemRefundState(String itemRefundState) {
            this.itemRefundState = itemRefundState;
        }

        public String getItemRefundState() {
            return this.itemRefundState;
        }
	
        public void setIsVirtual(Long isVirtual) {
            this.isVirtual = isVirtual;
        }

        public Long getIsVirtual() {
            return this.isVirtual;
        }

        public void setIsPresent(Long isPresent) {
            this.isPresent = isPresent;
        }

        public Long getIsPresent() {
            return this.isPresent;
        }

        public void setRefundedFee(Float refundedFee) {
            this.refundedFee = refundedFee;
        }

        public Float getRefundedFee() {
            return this.refundedFee;
        }

        public void setAllowSend(Long allowSend) {
            this.allowSend = allowSend;
        }

        public Long getAllowSend() {
            return this.allowSend;
        }

	}

    public static class AdjustFee {
    	//总改价金额
    	@JsonProperty(value = "change")
        private Float change;
            
    	//订单改价
    	@JsonProperty(value = "pay_change")
        private Float payChange;
            
    	//邮费改价
    	@JsonProperty(value = "post_change")
        private Float postChange;
    
    	public void setChange(Float change) {
            this.change = change;
        }

        public Float getChange() {
            return this.change;
        }

        public void setPayChange(Float payChange) {
            this.payChange = payChange;
        }

        public Float getPayChange() {
            return this.payChange;
        }

        public void setPostChange(Float postChange) {
            this.postChange = postChange;
        }

        public Float getPostChange() {
            return this.postChange;
        }

	}
    

    public static class TradeOrderPromotion {
    	//优惠的名称
    	@JsonProperty(value = "promotion_name")
        private String promotionName;
            
    	/**
         * 优惠的类型。可选值：
		 * MEMBER_CARD_DISCOUNT（会员卡折扣）
         * SCAN_DISCOUNT（扫码折扣）
		 * SCAN_DECREASE（扫码减额优惠）
		 * TIMELIMITED_DISCOUNT（限时折扣）
         */
    	@JsonProperty(value = "promotion_type")
        private String promotionType;
    	
    	//应用优惠的时间
    	@JsonProperty(value = "apply_at")
        private Date applyAt;
            
    	//优惠的金额，单位：元，精确到小数点后两位
    	@JsonProperty(value = "discount_fee")
        private Float discountFee;
    
    	public void setPromotionName(String promotionName) {
            this.promotionName = promotionName;
        }

        public String getPromotionName() {
            return this.promotionName;
        }

        public void setPromotionType(String promotionType) {
            this.promotionType = promotionType;
        }

        public String getPromotionType() {
            return this.promotionType;
        }

        public void setApplyAt(Date applyAt) {
            this.applyAt = applyAt;
        }

        public Date getApplyAt() {
            return this.applyAt;
        }

        public void setDiscountFee(Float discountFee) {
            this.discountFee = discountFee;
        }

        public Float getDiscountFee() {
            return this.discountFee;
        }

	}
}
