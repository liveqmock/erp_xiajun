package com.wangqin.shopkeeper.biz1.app.dal.dataObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MallSubOrderDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MallSubOrderDOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoIsNull() {
            addCriterion("mall_return_order_no is null");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoIsNotNull() {
            addCriterion("mall_return_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoEqualTo(String value) {
            addCriterion("mall_return_order_no =", value, "mallReturnOrderNo");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoNotEqualTo(String value) {
            addCriterion("mall_return_order_no <>", value, "mallReturnOrderNo");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoGreaterThan(String value) {
            addCriterion("mall_return_order_no >", value, "mallReturnOrderNo");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("mall_return_order_no >=", value, "mallReturnOrderNo");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoLessThan(String value) {
            addCriterion("mall_return_order_no <", value, "mallReturnOrderNo");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoLessThanOrEqualTo(String value) {
            addCriterion("mall_return_order_no <=", value, "mallReturnOrderNo");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoLike(String value) {
            addCriterion("mall_return_order_no like", value, "mallReturnOrderNo");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoNotLike(String value) {
            addCriterion("mall_return_order_no not like", value, "mallReturnOrderNo");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoIn(List<String> values) {
            addCriterion("mall_return_order_no in", values, "mallReturnOrderNo");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoNotIn(List<String> values) {
            addCriterion("mall_return_order_no not in", values, "mallReturnOrderNo");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoBetween(String value1, String value2) {
            addCriterion("mall_return_order_no between", value1, value2, "mallReturnOrderNo");
            return (Criteria) this;
        }

        public Criteria andMallReturnOrderNoNotBetween(String value1, String value2) {
            addCriterion("mall_return_order_no not between", value1, value2, "mallReturnOrderNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoIsNull() {
            addCriterion("customer_no is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNoIsNotNull() {
            addCriterion("customer_no is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNoEqualTo(String value) {
            addCriterion("customer_no =", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoNotEqualTo(String value) {
            addCriterion("customer_no <>", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoGreaterThan(String value) {
            addCriterion("customer_no >", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoGreaterThanOrEqualTo(String value) {
            addCriterion("customer_no >=", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoLessThan(String value) {
            addCriterion("customer_no <", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoLessThanOrEqualTo(String value) {
            addCriterion("customer_no <=", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoLike(String value) {
            addCriterion("customer_no like", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoNotLike(String value) {
            addCriterion("customer_no not like", value, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoIn(List<String> values) {
            addCriterion("customer_no in", values, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoNotIn(List<String> values) {
            addCriterion("customer_no not in", values, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoBetween(String value1, String value2) {
            addCriterion("customer_no between", value1, value2, "customerNo");
            return (Criteria) this;
        }

        public Criteria andCustomerNoNotBetween(String value1, String value2) {
            addCriterion("customer_no not between", value1, value2, "customerNo");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("open_id is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("open_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("open_id =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("open_id <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("open_id >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("open_id >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("open_id <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("open_id <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("open_id like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("open_id not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("open_id in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("open_id not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("open_id between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("open_id not between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andCompanyNoIsNull() {
            addCriterion("company_no is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNoIsNotNull() {
            addCriterion("company_no is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNoEqualTo(String value) {
            addCriterion("company_no =", value, "companyNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNoNotEqualTo(String value) {
            addCriterion("company_no <>", value, "companyNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNoGreaterThan(String value) {
            addCriterion("company_no >", value, "companyNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNoGreaterThanOrEqualTo(String value) {
            addCriterion("company_no >=", value, "companyNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNoLessThan(String value) {
            addCriterion("company_no <", value, "companyNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNoLessThanOrEqualTo(String value) {
            addCriterion("company_no <=", value, "companyNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNoLike(String value) {
            addCriterion("company_no like", value, "companyNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNoNotLike(String value) {
            addCriterion("company_no not like", value, "companyNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNoIn(List<String> values) {
            addCriterion("company_no in", values, "companyNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNoNotIn(List<String> values) {
            addCriterion("company_no not in", values, "companyNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNoBetween(String value1, String value2) {
            addCriterion("company_no between", value1, value2, "companyNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNoNotBetween(String value1, String value2) {
            addCriterion("company_no not between", value1, value2, "companyNo");
            return (Criteria) this;
        }

        public Criteria andShopCodeIsNull() {
            addCriterion("shop_code is null");
            return (Criteria) this;
        }

        public Criteria andShopCodeIsNotNull() {
            addCriterion("shop_code is not null");
            return (Criteria) this;
        }

        public Criteria andShopCodeEqualTo(String value) {
            addCriterion("shop_code =", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeNotEqualTo(String value) {
            addCriterion("shop_code <>", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeGreaterThan(String value) {
            addCriterion("shop_code >", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeGreaterThanOrEqualTo(String value) {
            addCriterion("shop_code >=", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeLessThan(String value) {
            addCriterion("shop_code <", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeLessThanOrEqualTo(String value) {
            addCriterion("shop_code <=", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeLike(String value) {
            addCriterion("shop_code like", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeNotLike(String value) {
            addCriterion("shop_code not like", value, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeIn(List<String> values) {
            addCriterion("shop_code in", values, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeNotIn(List<String> values) {
            addCriterion("shop_code not in", values, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeBetween(String value1, String value2) {
            addCriterion("shop_code between", value1, value2, "shopCode");
            return (Criteria) this;
        }

        public Criteria andShopCodeNotBetween(String value1, String value2) {
            addCriterion("shop_code not between", value1, value2, "shopCode");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoIsNull() {
            addCriterion("channel_order_no is null");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoIsNotNull() {
            addCriterion("channel_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoEqualTo(String value) {
            addCriterion("channel_order_no =", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoNotEqualTo(String value) {
            addCriterion("channel_order_no <>", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoGreaterThan(String value) {
            addCriterion("channel_order_no >", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("channel_order_no >=", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoLessThan(String value) {
            addCriterion("channel_order_no <", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoLessThanOrEqualTo(String value) {
            addCriterion("channel_order_no <=", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoLike(String value) {
            addCriterion("channel_order_no like", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoNotLike(String value) {
            addCriterion("channel_order_no not like", value, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoIn(List<String> values) {
            addCriterion("channel_order_no in", values, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoNotIn(List<String> values) {
            addCriterion("channel_order_no not in", values, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoBetween(String value1, String value2) {
            addCriterion("channel_order_no between", value1, value2, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andChannelOrderNoNotBetween(String value1, String value2) {
            addCriterion("channel_order_no not between", value1, value2, "channelOrderNo");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNull() {
            addCriterion("order_time is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNotNull() {
            addCriterion("order_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeEqualTo(Date value) {
            addCriterion("order_time =", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotEqualTo(Date value) {
            addCriterion("order_time <>", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThan(Date value) {
            addCriterion("order_time >", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("order_time >=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThan(Date value) {
            addCriterion("order_time <", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThanOrEqualTo(Date value) {
            addCriterion("order_time <=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIn(List<Date> values) {
            addCriterion("order_time in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotIn(List<Date> values) {
            addCriterion("order_time not in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeBetween(Date value1, Date value2) {
            addCriterion("order_time between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotBetween(Date value1, Date value2) {
            addCriterion("order_time not between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andItemCodeIsNull() {
            addCriterion("item_code is null");
            return (Criteria) this;
        }

        public Criteria andItemCodeIsNotNull() {
            addCriterion("item_code is not null");
            return (Criteria) this;
        }

        public Criteria andItemCodeEqualTo(String value) {
            addCriterion("item_code =", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotEqualTo(String value) {
            addCriterion("item_code <>", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThan(String value) {
            addCriterion("item_code >", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThanOrEqualTo(String value) {
            addCriterion("item_code >=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThan(String value) {
            addCriterion("item_code <", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThanOrEqualTo(String value) {
            addCriterion("item_code <=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLike(String value) {
            addCriterion("item_code like", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotLike(String value) {
            addCriterion("item_code not like", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeIn(List<String> values) {
            addCriterion("item_code in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotIn(List<String> values) {
            addCriterion("item_code not in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeBetween(String value1, String value2) {
            addCriterion("item_code between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotBetween(String value1, String value2) {
            addCriterion("item_code not between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("item_name is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("item_name is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("item_name =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("item_name <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("item_name >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("item_name >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("item_name <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("item_name <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("item_name like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("item_name not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("item_name in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("item_name not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("item_name between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("item_name not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andSkuCodeIsNull() {
            addCriterion("sku_code is null");
            return (Criteria) this;
        }

        public Criteria andSkuCodeIsNotNull() {
            addCriterion("sku_code is not null");
            return (Criteria) this;
        }

        public Criteria andSkuCodeEqualTo(String value) {
            addCriterion("sku_code =", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeNotEqualTo(String value) {
            addCriterion("sku_code <>", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeGreaterThan(String value) {
            addCriterion("sku_code >", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sku_code >=", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeLessThan(String value) {
            addCriterion("sku_code <", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeLessThanOrEqualTo(String value) {
            addCriterion("sku_code <=", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeLike(String value) {
            addCriterion("sku_code like", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeNotLike(String value) {
            addCriterion("sku_code not like", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeIn(List<String> values) {
            addCriterion("sku_code in", values, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeNotIn(List<String> values) {
            addCriterion("sku_code not in", values, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeBetween(String value1, String value2) {
            addCriterion("sku_code between", value1, value2, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeNotBetween(String value1, String value2) {
            addCriterion("sku_code not between", value1, value2, "skuCode");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeIsNull() {
            addCriterion("channel_sku_code is null");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeIsNotNull() {
            addCriterion("channel_sku_code is not null");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeEqualTo(String value) {
            addCriterion("channel_sku_code =", value, "channelSkuCode");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeNotEqualTo(String value) {
            addCriterion("channel_sku_code <>", value, "channelSkuCode");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeGreaterThan(String value) {
            addCriterion("channel_sku_code >", value, "channelSkuCode");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeGreaterThanOrEqualTo(String value) {
            addCriterion("channel_sku_code >=", value, "channelSkuCode");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeLessThan(String value) {
            addCriterion("channel_sku_code <", value, "channelSkuCode");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeLessThanOrEqualTo(String value) {
            addCriterion("channel_sku_code <=", value, "channelSkuCode");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeLike(String value) {
            addCriterion("channel_sku_code like", value, "channelSkuCode");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeNotLike(String value) {
            addCriterion("channel_sku_code not like", value, "channelSkuCode");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeIn(List<String> values) {
            addCriterion("channel_sku_code in", values, "channelSkuCode");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeNotIn(List<String> values) {
            addCriterion("channel_sku_code not in", values, "channelSkuCode");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeBetween(String value1, String value2) {
            addCriterion("channel_sku_code between", value1, value2, "channelSkuCode");
            return (Criteria) this;
        }

        public Criteria andChannelSkuCodeNotBetween(String value1, String value2) {
            addCriterion("channel_sku_code not between", value1, value2, "channelSkuCode");
            return (Criteria) this;
        }

        public Criteria andUpcIsNull() {
            addCriterion("upc is null");
            return (Criteria) this;
        }

        public Criteria andUpcIsNotNull() {
            addCriterion("upc is not null");
            return (Criteria) this;
        }

        public Criteria andUpcEqualTo(String value) {
            addCriterion("upc =", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcNotEqualTo(String value) {
            addCriterion("upc <>", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcGreaterThan(String value) {
            addCriterion("upc >", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcGreaterThanOrEqualTo(String value) {
            addCriterion("upc >=", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcLessThan(String value) {
            addCriterion("upc <", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcLessThanOrEqualTo(String value) {
            addCriterion("upc <=", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcLike(String value) {
            addCriterion("upc like", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcNotLike(String value) {
            addCriterion("upc not like", value, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcIn(List<String> values) {
            addCriterion("upc in", values, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcNotIn(List<String> values) {
            addCriterion("upc not in", values, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcBetween(String value1, String value2) {
            addCriterion("upc between", value1, value2, "upc");
            return (Criteria) this;
        }

        public Criteria andUpcNotBetween(String value1, String value2) {
            addCriterion("upc not between", value1, value2, "upc");
            return (Criteria) this;
        }

        public Criteria andScaleIsNull() {
            addCriterion("scale is null");
            return (Criteria) this;
        }

        public Criteria andScaleIsNotNull() {
            addCriterion("scale is not null");
            return (Criteria) this;
        }

        public Criteria andScaleEqualTo(String value) {
            addCriterion("scale =", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleNotEqualTo(String value) {
            addCriterion("scale <>", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleGreaterThan(String value) {
            addCriterion("scale >", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleGreaterThanOrEqualTo(String value) {
            addCriterion("scale >=", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleLessThan(String value) {
            addCriterion("scale <", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleLessThanOrEqualTo(String value) {
            addCriterion("scale <=", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleLike(String value) {
            addCriterion("scale like", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleNotLike(String value) {
            addCriterion("scale not like", value, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleIn(List<String> values) {
            addCriterion("scale in", values, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleNotIn(List<String> values) {
            addCriterion("scale not in", values, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleBetween(String value1, String value2) {
            addCriterion("scale between", value1, value2, "scale");
            return (Criteria) this;
        }

        public Criteria andScaleNotBetween(String value1, String value2) {
            addCriterion("scale not between", value1, value2, "scale");
            return (Criteria) this;
        }

        public Criteria andSkuPicIsNull() {
            addCriterion("sku_pic is null");
            return (Criteria) this;
        }

        public Criteria andSkuPicIsNotNull() {
            addCriterion("sku_pic is not null");
            return (Criteria) this;
        }

        public Criteria andSkuPicEqualTo(String value) {
            addCriterion("sku_pic =", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicNotEqualTo(String value) {
            addCriterion("sku_pic <>", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicGreaterThan(String value) {
            addCriterion("sku_pic >", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicGreaterThanOrEqualTo(String value) {
            addCriterion("sku_pic >=", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicLessThan(String value) {
            addCriterion("sku_pic <", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicLessThanOrEqualTo(String value) {
            addCriterion("sku_pic <=", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicLike(String value) {
            addCriterion("sku_pic like", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicNotLike(String value) {
            addCriterion("sku_pic not like", value, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicIn(List<String> values) {
            addCriterion("sku_pic in", values, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicNotIn(List<String> values) {
            addCriterion("sku_pic not in", values, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicBetween(String value1, String value2) {
            addCriterion("sku_pic between", value1, value2, "skuPic");
            return (Criteria) this;
        }

        public Criteria andSkuPicNotBetween(String value1, String value2) {
            addCriterion("sku_pic not between", value1, value2, "skuPic");
            return (Criteria) this;
        }

        public Criteria andLogisticTypeIsNull() {
            addCriterion("logistic_type is null");
            return (Criteria) this;
        }

        public Criteria andLogisticTypeIsNotNull() {
            addCriterion("logistic_type is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticTypeEqualTo(Byte value) {
            addCriterion("logistic_type =", value, "logisticType");
            return (Criteria) this;
        }

        public Criteria andLogisticTypeNotEqualTo(Byte value) {
            addCriterion("logistic_type <>", value, "logisticType");
            return (Criteria) this;
        }

        public Criteria andLogisticTypeGreaterThan(Byte value) {
            addCriterion("logistic_type >", value, "logisticType");
            return (Criteria) this;
        }

        public Criteria andLogisticTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("logistic_type >=", value, "logisticType");
            return (Criteria) this;
        }

        public Criteria andLogisticTypeLessThan(Byte value) {
            addCriterion("logistic_type <", value, "logisticType");
            return (Criteria) this;
        }

        public Criteria andLogisticTypeLessThanOrEqualTo(Byte value) {
            addCriterion("logistic_type <=", value, "logisticType");
            return (Criteria) this;
        }

        public Criteria andLogisticTypeIn(List<Byte> values) {
            addCriterion("logistic_type in", values, "logisticType");
            return (Criteria) this;
        }

        public Criteria andLogisticTypeNotIn(List<Byte> values) {
            addCriterion("logistic_type not in", values, "logisticType");
            return (Criteria) this;
        }

        public Criteria andLogisticTypeBetween(Byte value1, Byte value2) {
            addCriterion("logistic_type between", value1, value2, "logisticType");
            return (Criteria) this;
        }

        public Criteria andLogisticTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("logistic_type not between", value1, value2, "logisticType");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(Double value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(Double value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(Double value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(Double value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(Double value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(Double value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<Double> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<Double> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(Double value1, Double value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(Double value1, Double value2) {
            addCriterion("freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightRealIsNull() {
            addCriterion("freight_real is null");
            return (Criteria) this;
        }

        public Criteria andFreightRealIsNotNull() {
            addCriterion("freight_real is not null");
            return (Criteria) this;
        }

        public Criteria andFreightRealEqualTo(Double value) {
            addCriterion("freight_real =", value, "freightReal");
            return (Criteria) this;
        }

        public Criteria andFreightRealNotEqualTo(Double value) {
            addCriterion("freight_real <>", value, "freightReal");
            return (Criteria) this;
        }

        public Criteria andFreightRealGreaterThan(Double value) {
            addCriterion("freight_real >", value, "freightReal");
            return (Criteria) this;
        }

        public Criteria andFreightRealGreaterThanOrEqualTo(Double value) {
            addCriterion("freight_real >=", value, "freightReal");
            return (Criteria) this;
        }

        public Criteria andFreightRealLessThan(Double value) {
            addCriterion("freight_real <", value, "freightReal");
            return (Criteria) this;
        }

        public Criteria andFreightRealLessThanOrEqualTo(Double value) {
            addCriterion("freight_real <=", value, "freightReal");
            return (Criteria) this;
        }

        public Criteria andFreightRealIn(List<Double> values) {
            addCriterion("freight_real in", values, "freightReal");
            return (Criteria) this;
        }

        public Criteria andFreightRealNotIn(List<Double> values) {
            addCriterion("freight_real not in", values, "freightReal");
            return (Criteria) this;
        }

        public Criteria andFreightRealBetween(Double value1, Double value2) {
            addCriterion("freight_real between", value1, value2, "freightReal");
            return (Criteria) this;
        }

        public Criteria andFreightRealNotBetween(Double value1, Double value2) {
            addCriterion("freight_real not between", value1, value2, "freightReal");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Double value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Double value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Double value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Double value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Double value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Double value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Double> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Double> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Double value1, Double value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Double value1, Double value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNull() {
            addCriterion("sale_price is null");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNotNull() {
            addCriterion("sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andSalePriceEqualTo(Double value) {
            addCriterion("sale_price =", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotEqualTo(Double value) {
            addCriterion("sale_price <>", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThan(Double value) {
            addCriterion("sale_price >", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThanOrEqualTo(Double value) {
            addCriterion("sale_price >=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThan(Double value) {
            addCriterion("sale_price <", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThanOrEqualTo(Double value) {
            addCriterion("sale_price <=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIn(List<Double> values) {
            addCriterion("sale_price in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotIn(List<Double> values) {
            addCriterion("sale_price not in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceBetween(Double value1, Double value2) {
            addCriterion("sale_price between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotBetween(Double value1, Double value2) {
            addCriterion("sale_price not between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceAgentIsNull() {
            addCriterion("sale_price_agent is null");
            return (Criteria) this;
        }

        public Criteria andSalePriceAgentIsNotNull() {
            addCriterion("sale_price_agent is not null");
            return (Criteria) this;
        }

        public Criteria andSalePriceAgentEqualTo(Double value) {
            addCriterion("sale_price_agent =", value, "salePriceAgent");
            return (Criteria) this;
        }

        public Criteria andSalePriceAgentNotEqualTo(Double value) {
            addCriterion("sale_price_agent <>", value, "salePriceAgent");
            return (Criteria) this;
        }

        public Criteria andSalePriceAgentGreaterThan(Double value) {
            addCriterion("sale_price_agent >", value, "salePriceAgent");
            return (Criteria) this;
        }

        public Criteria andSalePriceAgentGreaterThanOrEqualTo(Double value) {
            addCriterion("sale_price_agent >=", value, "salePriceAgent");
            return (Criteria) this;
        }

        public Criteria andSalePriceAgentLessThan(Double value) {
            addCriterion("sale_price_agent <", value, "salePriceAgent");
            return (Criteria) this;
        }

        public Criteria andSalePriceAgentLessThanOrEqualTo(Double value) {
            addCriterion("sale_price_agent <=", value, "salePriceAgent");
            return (Criteria) this;
        }

        public Criteria andSalePriceAgentIn(List<Double> values) {
            addCriterion("sale_price_agent in", values, "salePriceAgent");
            return (Criteria) this;
        }

        public Criteria andSalePriceAgentNotIn(List<Double> values) {
            addCriterion("sale_price_agent not in", values, "salePriceAgent");
            return (Criteria) this;
        }

        public Criteria andSalePriceAgentBetween(Double value1, Double value2) {
            addCriterion("sale_price_agent between", value1, value2, "salePriceAgent");
            return (Criteria) this;
        }

        public Criteria andSalePriceAgentNotBetween(Double value1, Double value2) {
            addCriterion("sale_price_agent not between", value1, value2, "salePriceAgent");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Integer value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Integer value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Integer value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Integer value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Integer> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Integer> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Integer value1, Integer value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCloseReasonIsNull() {
            addCriterion("close_reason is null");
            return (Criteria) this;
        }

        public Criteria andCloseReasonIsNotNull() {
            addCriterion("close_reason is not null");
            return (Criteria) this;
        }

        public Criteria andCloseReasonEqualTo(String value) {
            addCriterion("close_reason =", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotEqualTo(String value) {
            addCriterion("close_reason <>", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonGreaterThan(String value) {
            addCriterion("close_reason >", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonGreaterThanOrEqualTo(String value) {
            addCriterion("close_reason >=", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonLessThan(String value) {
            addCriterion("close_reason <", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonLessThanOrEqualTo(String value) {
            addCriterion("close_reason <=", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonLike(String value) {
            addCriterion("close_reason like", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotLike(String value) {
            addCriterion("close_reason not like", value, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonIn(List<String> values) {
            addCriterion("close_reason in", values, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotIn(List<String> values) {
            addCriterion("close_reason not in", values, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonBetween(String value1, String value2) {
            addCriterion("close_reason between", value1, value2, "closeReason");
            return (Criteria) this;
        }

        public Criteria andCloseReasonNotBetween(String value1, String value2) {
            addCriterion("close_reason not between", value1, value2, "closeReason");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoIsNull() {
            addCriterion("warehouse_no is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoIsNotNull() {
            addCriterion("warehouse_no is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoEqualTo(String value) {
            addCriterion("warehouse_no =", value, "warehouseNo");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoNotEqualTo(String value) {
            addCriterion("warehouse_no <>", value, "warehouseNo");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoGreaterThan(String value) {
            addCriterion("warehouse_no >", value, "warehouseNo");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoGreaterThanOrEqualTo(String value) {
            addCriterion("warehouse_no >=", value, "warehouseNo");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoLessThan(String value) {
            addCriterion("warehouse_no <", value, "warehouseNo");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoLessThanOrEqualTo(String value) {
            addCriterion("warehouse_no <=", value, "warehouseNo");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoLike(String value) {
            addCriterion("warehouse_no like", value, "warehouseNo");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoNotLike(String value) {
            addCriterion("warehouse_no not like", value, "warehouseNo");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoIn(List<String> values) {
            addCriterion("warehouse_no in", values, "warehouseNo");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoNotIn(List<String> values) {
            addCriterion("warehouse_no not in", values, "warehouseNo");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoBetween(String value1, String value2) {
            addCriterion("warehouse_no between", value1, value2, "warehouseNo");
            return (Criteria) this;
        }

        public Criteria andWarehouseNoNotBetween(String value1, String value2) {
            addCriterion("warehouse_no not between", value1, value2, "warehouseNo");
            return (Criteria) this;
        }

        public Criteria andStockStatusIsNull() {
            addCriterion("stock_status is null");
            return (Criteria) this;
        }

        public Criteria andStockStatusIsNotNull() {
            addCriterion("stock_status is not null");
            return (Criteria) this;
        }

        public Criteria andStockStatusEqualTo(Byte value) {
            addCriterion("stock_status =", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusNotEqualTo(Byte value) {
            addCriterion("stock_status <>", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusGreaterThan(Byte value) {
            addCriterion("stock_status >", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("stock_status >=", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusLessThan(Byte value) {
            addCriterion("stock_status <", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusLessThanOrEqualTo(Byte value) {
            addCriterion("stock_status <=", value, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusIn(List<Byte> values) {
            addCriterion("stock_status in", values, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusNotIn(List<Byte> values) {
            addCriterion("stock_status not in", values, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusBetween(Byte value1, Byte value2) {
            addCriterion("stock_status between", value1, value2, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andStockStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("stock_status not between", value1, value2, "stockStatus");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoIsNull() {
            addCriterion("shipping_order_no is null");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoIsNotNull() {
            addCriterion("shipping_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoEqualTo(String value) {
            addCriterion("shipping_order_no =", value, "shippingOrderNo");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoNotEqualTo(String value) {
            addCriterion("shipping_order_no <>", value, "shippingOrderNo");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoGreaterThan(String value) {
            addCriterion("shipping_order_no >", value, "shippingOrderNo");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("shipping_order_no >=", value, "shippingOrderNo");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoLessThan(String value) {
            addCriterion("shipping_order_no <", value, "shippingOrderNo");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoLessThanOrEqualTo(String value) {
            addCriterion("shipping_order_no <=", value, "shippingOrderNo");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoLike(String value) {
            addCriterion("shipping_order_no like", value, "shippingOrderNo");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoNotLike(String value) {
            addCriterion("shipping_order_no not like", value, "shippingOrderNo");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoIn(List<String> values) {
            addCriterion("shipping_order_no in", values, "shippingOrderNo");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoNotIn(List<String> values) {
            addCriterion("shipping_order_no not in", values, "shippingOrderNo");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoBetween(String value1, String value2) {
            addCriterion("shipping_order_no between", value1, value2, "shippingOrderNo");
            return (Criteria) this;
        }

        public Criteria andShippingOrderNoNotBetween(String value1, String value2) {
            addCriterion("shipping_order_no not between", value1, value2, "shippingOrderNo");
            return (Criteria) this;
        }

        public Criteria andShippingNoIsNull() {
            addCriterion("shipping_no is null");
            return (Criteria) this;
        }

        public Criteria andShippingNoIsNotNull() {
            addCriterion("shipping_no is not null");
            return (Criteria) this;
        }

        public Criteria andShippingNoEqualTo(String value) {
            addCriterion("shipping_no =", value, "shippingNo");
            return (Criteria) this;
        }

        public Criteria andShippingNoNotEqualTo(String value) {
            addCriterion("shipping_no <>", value, "shippingNo");
            return (Criteria) this;
        }

        public Criteria andShippingNoGreaterThan(String value) {
            addCriterion("shipping_no >", value, "shippingNo");
            return (Criteria) this;
        }

        public Criteria andShippingNoGreaterThanOrEqualTo(String value) {
            addCriterion("shipping_no >=", value, "shippingNo");
            return (Criteria) this;
        }

        public Criteria andShippingNoLessThan(String value) {
            addCriterion("shipping_no <", value, "shippingNo");
            return (Criteria) this;
        }

        public Criteria andShippingNoLessThanOrEqualTo(String value) {
            addCriterion("shipping_no <=", value, "shippingNo");
            return (Criteria) this;
        }

        public Criteria andShippingNoLike(String value) {
            addCriterion("shipping_no like", value, "shippingNo");
            return (Criteria) this;
        }

        public Criteria andShippingNoNotLike(String value) {
            addCriterion("shipping_no not like", value, "shippingNo");
            return (Criteria) this;
        }

        public Criteria andShippingNoIn(List<String> values) {
            addCriterion("shipping_no in", values, "shippingNo");
            return (Criteria) this;
        }

        public Criteria andShippingNoNotIn(List<String> values) {
            addCriterion("shipping_no not in", values, "shippingNo");
            return (Criteria) this;
        }

        public Criteria andShippingNoBetween(String value1, String value2) {
            addCriterion("shipping_no between", value1, value2, "shippingNo");
            return (Criteria) this;
        }

        public Criteria andShippingNoNotBetween(String value1, String value2) {
            addCriterion("shipping_no not between", value1, value2, "shippingNo");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("receiver like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("receiver not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryIsNull() {
            addCriterion("receiver_country is null");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryIsNotNull() {
            addCriterion("receiver_country is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryEqualTo(String value) {
            addCriterion("receiver_country =", value, "receiverCountry");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryNotEqualTo(String value) {
            addCriterion("receiver_country <>", value, "receiverCountry");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryGreaterThan(String value) {
            addCriterion("receiver_country >", value, "receiverCountry");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_country >=", value, "receiverCountry");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryLessThan(String value) {
            addCriterion("receiver_country <", value, "receiverCountry");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryLessThanOrEqualTo(String value) {
            addCriterion("receiver_country <=", value, "receiverCountry");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryLike(String value) {
            addCriterion("receiver_country like", value, "receiverCountry");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryNotLike(String value) {
            addCriterion("receiver_country not like", value, "receiverCountry");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryIn(List<String> values) {
            addCriterion("receiver_country in", values, "receiverCountry");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryNotIn(List<String> values) {
            addCriterion("receiver_country not in", values, "receiverCountry");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryBetween(String value1, String value2) {
            addCriterion("receiver_country between", value1, value2, "receiverCountry");
            return (Criteria) this;
        }

        public Criteria andReceiverCountryNotBetween(String value1, String value2) {
            addCriterion("receiver_country not between", value1, value2, "receiverCountry");
            return (Criteria) this;
        }

        public Criteria andReceiverStateIsNull() {
            addCriterion("receiver_state is null");
            return (Criteria) this;
        }

        public Criteria andReceiverStateIsNotNull() {
            addCriterion("receiver_state is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverStateEqualTo(String value) {
            addCriterion("receiver_state =", value, "receiverState");
            return (Criteria) this;
        }

        public Criteria andReceiverStateNotEqualTo(String value) {
            addCriterion("receiver_state <>", value, "receiverState");
            return (Criteria) this;
        }

        public Criteria andReceiverStateGreaterThan(String value) {
            addCriterion("receiver_state >", value, "receiverState");
            return (Criteria) this;
        }

        public Criteria andReceiverStateGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_state >=", value, "receiverState");
            return (Criteria) this;
        }

        public Criteria andReceiverStateLessThan(String value) {
            addCriterion("receiver_state <", value, "receiverState");
            return (Criteria) this;
        }

        public Criteria andReceiverStateLessThanOrEqualTo(String value) {
            addCriterion("receiver_state <=", value, "receiverState");
            return (Criteria) this;
        }

        public Criteria andReceiverStateLike(String value) {
            addCriterion("receiver_state like", value, "receiverState");
            return (Criteria) this;
        }

        public Criteria andReceiverStateNotLike(String value) {
            addCriterion("receiver_state not like", value, "receiverState");
            return (Criteria) this;
        }

        public Criteria andReceiverStateIn(List<String> values) {
            addCriterion("receiver_state in", values, "receiverState");
            return (Criteria) this;
        }

        public Criteria andReceiverStateNotIn(List<String> values) {
            addCriterion("receiver_state not in", values, "receiverState");
            return (Criteria) this;
        }

        public Criteria andReceiverStateBetween(String value1, String value2) {
            addCriterion("receiver_state between", value1, value2, "receiverState");
            return (Criteria) this;
        }

        public Criteria andReceiverStateNotBetween(String value1, String value2) {
            addCriterion("receiver_state not between", value1, value2, "receiverState");
            return (Criteria) this;
        }

        public Criteria andReceiverCityIsNull() {
            addCriterion("receiver_city is null");
            return (Criteria) this;
        }

        public Criteria andReceiverCityIsNotNull() {
            addCriterion("receiver_city is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverCityEqualTo(String value) {
            addCriterion("receiver_city =", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotEqualTo(String value) {
            addCriterion("receiver_city <>", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityGreaterThan(String value) {
            addCriterion("receiver_city >", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_city >=", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityLessThan(String value) {
            addCriterion("receiver_city <", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityLessThanOrEqualTo(String value) {
            addCriterion("receiver_city <=", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityLike(String value) {
            addCriterion("receiver_city like", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotLike(String value) {
            addCriterion("receiver_city not like", value, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityIn(List<String> values) {
            addCriterion("receiver_city in", values, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotIn(List<String> values) {
            addCriterion("receiver_city not in", values, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityBetween(String value1, String value2) {
            addCriterion("receiver_city between", value1, value2, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverCityNotBetween(String value1, String value2) {
            addCriterion("receiver_city not between", value1, value2, "receiverCity");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictIsNull() {
            addCriterion("receiver_district is null");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictIsNotNull() {
            addCriterion("receiver_district is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictEqualTo(String value) {
            addCriterion("receiver_district =", value, "receiverDistrict");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictNotEqualTo(String value) {
            addCriterion("receiver_district <>", value, "receiverDistrict");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictGreaterThan(String value) {
            addCriterion("receiver_district >", value, "receiverDistrict");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_district >=", value, "receiverDistrict");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictLessThan(String value) {
            addCriterion("receiver_district <", value, "receiverDistrict");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictLessThanOrEqualTo(String value) {
            addCriterion("receiver_district <=", value, "receiverDistrict");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictLike(String value) {
            addCriterion("receiver_district like", value, "receiverDistrict");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictNotLike(String value) {
            addCriterion("receiver_district not like", value, "receiverDistrict");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictIn(List<String> values) {
            addCriterion("receiver_district in", values, "receiverDistrict");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictNotIn(List<String> values) {
            addCriterion("receiver_district not in", values, "receiverDistrict");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictBetween(String value1, String value2) {
            addCriterion("receiver_district between", value1, value2, "receiverDistrict");
            return (Criteria) this;
        }

        public Criteria andReceiverDistrictNotBetween(String value1, String value2) {
            addCriterion("receiver_district not between", value1, value2, "receiverDistrict");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressIsNull() {
            addCriterion("receiver_address is null");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressIsNotNull() {
            addCriterion("receiver_address is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressEqualTo(String value) {
            addCriterion("receiver_address =", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressNotEqualTo(String value) {
            addCriterion("receiver_address <>", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressGreaterThan(String value) {
            addCriterion("receiver_address >", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressGreaterThanOrEqualTo(String value) {
            addCriterion("receiver_address >=", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressLessThan(String value) {
            addCriterion("receiver_address <", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressLessThanOrEqualTo(String value) {
            addCriterion("receiver_address <=", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressLike(String value) {
            addCriterion("receiver_address like", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressNotLike(String value) {
            addCriterion("receiver_address not like", value, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressIn(List<String> values) {
            addCriterion("receiver_address in", values, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressNotIn(List<String> values) {
            addCriterion("receiver_address not in", values, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressBetween(String value1, String value2) {
            addCriterion("receiver_address between", value1, value2, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andReceiverAddressNotBetween(String value1, String value2) {
            addCriterion("receiver_address not between", value1, value2, "receiverAddress");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNull() {
            addCriterion("postcode is null");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNotNull() {
            addCriterion("postcode is not null");
            return (Criteria) this;
        }

        public Criteria andPostcodeEqualTo(String value) {
            addCriterion("postcode =", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotEqualTo(String value) {
            addCriterion("postcode <>", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThan(String value) {
            addCriterion("postcode >", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("postcode >=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThan(String value) {
            addCriterion("postcode <", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThanOrEqualTo(String value) {
            addCriterion("postcode <=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLike(String value) {
            addCriterion("postcode like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotLike(String value) {
            addCriterion("postcode not like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeIn(List<String> values) {
            addCriterion("postcode in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotIn(List<String> values) {
            addCriterion("postcode not in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeBetween(String value1, String value2) {
            addCriterion("postcode between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotBetween(String value1, String value2) {
            addCriterion("postcode not between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontIsNull() {
            addCriterion("idcard_pic_front is null");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontIsNotNull() {
            addCriterion("idcard_pic_front is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontEqualTo(String value) {
            addCriterion("idcard_pic_front =", value, "idcardPicFront");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontNotEqualTo(String value) {
            addCriterion("idcard_pic_front <>", value, "idcardPicFront");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontGreaterThan(String value) {
            addCriterion("idcard_pic_front >", value, "idcardPicFront");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_pic_front >=", value, "idcardPicFront");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontLessThan(String value) {
            addCriterion("idcard_pic_front <", value, "idcardPicFront");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontLessThanOrEqualTo(String value) {
            addCriterion("idcard_pic_front <=", value, "idcardPicFront");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontLike(String value) {
            addCriterion("idcard_pic_front like", value, "idcardPicFront");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontNotLike(String value) {
            addCriterion("idcard_pic_front not like", value, "idcardPicFront");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontIn(List<String> values) {
            addCriterion("idcard_pic_front in", values, "idcardPicFront");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontNotIn(List<String> values) {
            addCriterion("idcard_pic_front not in", values, "idcardPicFront");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontBetween(String value1, String value2) {
            addCriterion("idcard_pic_front between", value1, value2, "idcardPicFront");
            return (Criteria) this;
        }

        public Criteria andIdcardPicFrontNotBetween(String value1, String value2) {
            addCriterion("idcard_pic_front not between", value1, value2, "idcardPicFront");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseIsNull() {
            addCriterion("idcard_pic_reverse is null");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseIsNotNull() {
            addCriterion("idcard_pic_reverse is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseEqualTo(String value) {
            addCriterion("idcard_pic_reverse =", value, "idcardPicReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseNotEqualTo(String value) {
            addCriterion("idcard_pic_reverse <>", value, "idcardPicReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseGreaterThan(String value) {
            addCriterion("idcard_pic_reverse >", value, "idcardPicReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_pic_reverse >=", value, "idcardPicReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseLessThan(String value) {
            addCriterion("idcard_pic_reverse <", value, "idcardPicReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseLessThanOrEqualTo(String value) {
            addCriterion("idcard_pic_reverse <=", value, "idcardPicReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseLike(String value) {
            addCriterion("idcard_pic_reverse like", value, "idcardPicReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseNotLike(String value) {
            addCriterion("idcard_pic_reverse not like", value, "idcardPicReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseIn(List<String> values) {
            addCriterion("idcard_pic_reverse in", values, "idcardPicReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseNotIn(List<String> values) {
            addCriterion("idcard_pic_reverse not in", values, "idcardPicReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseBetween(String value1, String value2) {
            addCriterion("idcard_pic_reverse between", value1, value2, "idcardPicReverse");
            return (Criteria) this;
        }

        public Criteria andIdcardPicReverseNotBetween(String value1, String value2) {
            addCriterion("idcard_pic_reverse not between", value1, value2, "idcardPicReverse");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNull() {
            addCriterion("gmt_modify is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNotNull() {
            addCriterion("gmt_modify is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyEqualTo(Date value) {
            addCriterion("gmt_modify =", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotEqualTo(Date value) {
            addCriterion("gmt_modify <>", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThan(Date value) {
            addCriterion("gmt_modify >", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modify >=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThan(Date value) {
            addCriterion("gmt_modify <", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modify <=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIn(List<Date> values) {
            addCriterion("gmt_modify in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotIn(List<Date> values) {
            addCriterion("gmt_modify not in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyBetween(Date value1, Date value2) {
            addCriterion("gmt_modify between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modify not between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Boolean value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Boolean value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Boolean value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Boolean value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Boolean value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Boolean> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Boolean> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Boolean value1, Boolean value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}