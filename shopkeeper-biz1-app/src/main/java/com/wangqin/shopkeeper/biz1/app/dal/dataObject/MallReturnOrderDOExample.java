package com.wangqin.shopkeeper.biz1.app.dal.dataObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MallReturnOrderDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MallReturnOrderDOExample() {
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

        public Criteria andOuterOrderNoIsNull() {
            addCriterion("outer_order_no is null");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoIsNotNull() {
            addCriterion("outer_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoEqualTo(Long value) {
            addCriterion("outer_order_no =", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoNotEqualTo(Long value) {
            addCriterion("outer_order_no <>", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoGreaterThan(Long value) {
            addCriterion("outer_order_no >", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoGreaterThanOrEqualTo(Long value) {
            addCriterion("outer_order_no >=", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoLessThan(Long value) {
            addCriterion("outer_order_no <", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoLessThanOrEqualTo(Long value) {
            addCriterion("outer_order_no <=", value, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoIn(List<Long> values) {
            addCriterion("outer_order_no in", values, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoNotIn(List<Long> values) {
            addCriterion("outer_order_no not in", values, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoBetween(Long value1, Long value2) {
            addCriterion("outer_order_no between", value1, value2, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andOuterOrderNoNotBetween(Long value1, Long value2) {
            addCriterion("outer_order_no not between", value1, value2, "outerOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoIsNull() {
            addCriterion("sub_order_no is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoIsNotNull() {
            addCriterion("sub_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoEqualTo(String value) {
            addCriterion("sub_order_no =", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoNotEqualTo(String value) {
            addCriterion("sub_order_no <>", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoGreaterThan(String value) {
            addCriterion("sub_order_no >", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("sub_order_no >=", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoLessThan(String value) {
            addCriterion("sub_order_no <", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoLessThanOrEqualTo(String value) {
            addCriterion("sub_order_no <=", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoLike(String value) {
            addCriterion("sub_order_no like", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoNotLike(String value) {
            addCriterion("sub_order_no not like", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoIn(List<String> values) {
            addCriterion("sub_order_no in", values, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoNotIn(List<String> values) {
            addCriterion("sub_order_no not in", values, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoBetween(String value1, String value2) {
            addCriterion("sub_order_no between", value1, value2, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoNotBetween(String value1, String value2) {
            addCriterion("sub_order_no not between", value1, value2, "subOrderNo");
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

        public Criteria andReturnReasonIsNull() {
            addCriterion("return_reason is null");
            return (Criteria) this;
        }

        public Criteria andReturnReasonIsNotNull() {
            addCriterion("return_reason is not null");
            return (Criteria) this;
        }

        public Criteria andReturnReasonEqualTo(String value) {
            addCriterion("return_reason =", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonNotEqualTo(String value) {
            addCriterion("return_reason <>", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonGreaterThan(String value) {
            addCriterion("return_reason >", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonGreaterThanOrEqualTo(String value) {
            addCriterion("return_reason >=", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonLessThan(String value) {
            addCriterion("return_reason <", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonLessThanOrEqualTo(String value) {
            addCriterion("return_reason <=", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonLike(String value) {
            addCriterion("return_reason like", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonNotLike(String value) {
            addCriterion("return_reason not like", value, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonIn(List<String> values) {
            addCriterion("return_reason in", values, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonNotIn(List<String> values) {
            addCriterion("return_reason not in", values, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonBetween(String value1, String value2) {
            addCriterion("return_reason between", value1, value2, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonNotBetween(String value1, String value2) {
            addCriterion("return_reason not between", value1, value2, "returnReason");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailIsNull() {
            addCriterion("return_reason_detail is null");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailIsNotNull() {
            addCriterion("return_reason_detail is not null");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailEqualTo(String value) {
            addCriterion("return_reason_detail =", value, "returnReasonDetail");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailNotEqualTo(String value) {
            addCriterion("return_reason_detail <>", value, "returnReasonDetail");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailGreaterThan(String value) {
            addCriterion("return_reason_detail >", value, "returnReasonDetail");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailGreaterThanOrEqualTo(String value) {
            addCriterion("return_reason_detail >=", value, "returnReasonDetail");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailLessThan(String value) {
            addCriterion("return_reason_detail <", value, "returnReasonDetail");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailLessThanOrEqualTo(String value) {
            addCriterion("return_reason_detail <=", value, "returnReasonDetail");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailLike(String value) {
            addCriterion("return_reason_detail like", value, "returnReasonDetail");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailNotLike(String value) {
            addCriterion("return_reason_detail not like", value, "returnReasonDetail");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailIn(List<String> values) {
            addCriterion("return_reason_detail in", values, "returnReasonDetail");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailNotIn(List<String> values) {
            addCriterion("return_reason_detail not in", values, "returnReasonDetail");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailBetween(String value1, String value2) {
            addCriterion("return_reason_detail between", value1, value2, "returnReasonDetail");
            return (Criteria) this;
        }

        public Criteria andReturnReasonDetailNotBetween(String value1, String value2) {
            addCriterion("return_reason_detail not between", value1, value2, "returnReasonDetail");
            return (Criteria) this;
        }

        public Criteria andReturnQuantityIsNull() {
            addCriterion("return_quantity is null");
            return (Criteria) this;
        }

        public Criteria andReturnQuantityIsNotNull() {
            addCriterion("return_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andReturnQuantityEqualTo(Integer value) {
            addCriterion("return_quantity =", value, "returnQuantity");
            return (Criteria) this;
        }

        public Criteria andReturnQuantityNotEqualTo(Integer value) {
            addCriterion("return_quantity <>", value, "returnQuantity");
            return (Criteria) this;
        }

        public Criteria andReturnQuantityGreaterThan(Integer value) {
            addCriterion("return_quantity >", value, "returnQuantity");
            return (Criteria) this;
        }

        public Criteria andReturnQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("return_quantity >=", value, "returnQuantity");
            return (Criteria) this;
        }

        public Criteria andReturnQuantityLessThan(Integer value) {
            addCriterion("return_quantity <", value, "returnQuantity");
            return (Criteria) this;
        }

        public Criteria andReturnQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("return_quantity <=", value, "returnQuantity");
            return (Criteria) this;
        }

        public Criteria andReturnQuantityIn(List<Integer> values) {
            addCriterion("return_quantity in", values, "returnQuantity");
            return (Criteria) this;
        }

        public Criteria andReturnQuantityNotIn(List<Integer> values) {
            addCriterion("return_quantity not in", values, "returnQuantity");
            return (Criteria) this;
        }

        public Criteria andReturnQuantityBetween(Integer value1, Integer value2) {
            addCriterion("return_quantity between", value1, value2, "returnQuantity");
            return (Criteria) this;
        }

        public Criteria andReturnQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("return_quantity not between", value1, value2, "returnQuantity");
            return (Criteria) this;
        }

        public Criteria andReturnPriceIsNull() {
            addCriterion("return_price is null");
            return (Criteria) this;
        }

        public Criteria andReturnPriceIsNotNull() {
            addCriterion("return_price is not null");
            return (Criteria) this;
        }

        public Criteria andReturnPriceEqualTo(Double value) {
            addCriterion("return_price =", value, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceNotEqualTo(Double value) {
            addCriterion("return_price <>", value, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceGreaterThan(Double value) {
            addCriterion("return_price >", value, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("return_price >=", value, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceLessThan(Double value) {
            addCriterion("return_price <", value, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceLessThanOrEqualTo(Double value) {
            addCriterion("return_price <=", value, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceIn(List<Double> values) {
            addCriterion("return_price in", values, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceNotIn(List<Double> values) {
            addCriterion("return_price not in", values, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceBetween(Double value1, Double value2) {
            addCriterion("return_price between", value1, value2, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andReturnPriceNotBetween(Double value1, Double value2) {
            addCriterion("return_price not between", value1, value2, "returnPrice");
            return (Criteria) this;
        }

        public Criteria andIsCivilIsNull() {
            addCriterion("is_civil is null");
            return (Criteria) this;
        }

        public Criteria andIsCivilIsNotNull() {
            addCriterion("is_civil is not null");
            return (Criteria) this;
        }

        public Criteria andIsCivilEqualTo(Byte value) {
            addCriterion("is_civil =", value, "isCivil");
            return (Criteria) this;
        }

        public Criteria andIsCivilNotEqualTo(Byte value) {
            addCriterion("is_civil <>", value, "isCivil");
            return (Criteria) this;
        }

        public Criteria andIsCivilGreaterThan(Byte value) {
            addCriterion("is_civil >", value, "isCivil");
            return (Criteria) this;
        }

        public Criteria andIsCivilGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_civil >=", value, "isCivil");
            return (Criteria) this;
        }

        public Criteria andIsCivilLessThan(Byte value) {
            addCriterion("is_civil <", value, "isCivil");
            return (Criteria) this;
        }

        public Criteria andIsCivilLessThanOrEqualTo(Byte value) {
            addCriterion("is_civil <=", value, "isCivil");
            return (Criteria) this;
        }

        public Criteria andIsCivilIn(List<Byte> values) {
            addCriterion("is_civil in", values, "isCivil");
            return (Criteria) this;
        }

        public Criteria andIsCivilNotIn(List<Byte> values) {
            addCriterion("is_civil not in", values, "isCivil");
            return (Criteria) this;
        }

        public Criteria andIsCivilBetween(Byte value1, Byte value2) {
            addCriterion("is_civil between", value1, value2, "isCivil");
            return (Criteria) this;
        }

        public Criteria andIsCivilNotBetween(Byte value1, Byte value2) {
            addCriterion("is_civil not between", value1, value2, "isCivil");
            return (Criteria) this;
        }

        public Criteria andIsCheckinIsNull() {
            addCriterion("is_checkin is null");
            return (Criteria) this;
        }

        public Criteria andIsCheckinIsNotNull() {
            addCriterion("is_checkin is not null");
            return (Criteria) this;
        }

        public Criteria andIsCheckinEqualTo(Byte value) {
            addCriterion("is_checkin =", value, "isCheckin");
            return (Criteria) this;
        }

        public Criteria andIsCheckinNotEqualTo(Byte value) {
            addCriterion("is_checkin <>", value, "isCheckin");
            return (Criteria) this;
        }

        public Criteria andIsCheckinGreaterThan(Byte value) {
            addCriterion("is_checkin >", value, "isCheckin");
            return (Criteria) this;
        }

        public Criteria andIsCheckinGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_checkin >=", value, "isCheckin");
            return (Criteria) this;
        }

        public Criteria andIsCheckinLessThan(Byte value) {
            addCriterion("is_checkin <", value, "isCheckin");
            return (Criteria) this;
        }

        public Criteria andIsCheckinLessThanOrEqualTo(Byte value) {
            addCriterion("is_checkin <=", value, "isCheckin");
            return (Criteria) this;
        }

        public Criteria andIsCheckinIn(List<Byte> values) {
            addCriterion("is_checkin in", values, "isCheckin");
            return (Criteria) this;
        }

        public Criteria andIsCheckinNotIn(List<Byte> values) {
            addCriterion("is_checkin not in", values, "isCheckin");
            return (Criteria) this;
        }

        public Criteria andIsCheckinBetween(Byte value1, Byte value2) {
            addCriterion("is_checkin between", value1, value2, "isCheckin");
            return (Criteria) this;
        }

        public Criteria andIsCheckinNotBetween(Byte value1, Byte value2) {
            addCriterion("is_checkin not between", value1, value2, "isCheckin");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdIsNull() {
            addCriterion("customer_open_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdIsNotNull() {
            addCriterion("customer_open_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdEqualTo(String value) {
            addCriterion("customer_open_id =", value, "customerOpenId");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdNotEqualTo(String value) {
            addCriterion("customer_open_id <>", value, "customerOpenId");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdGreaterThan(String value) {
            addCriterion("customer_open_id >", value, "customerOpenId");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("customer_open_id >=", value, "customerOpenId");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdLessThan(String value) {
            addCriterion("customer_open_id <", value, "customerOpenId");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdLessThanOrEqualTo(String value) {
            addCriterion("customer_open_id <=", value, "customerOpenId");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdLike(String value) {
            addCriterion("customer_open_id like", value, "customerOpenId");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdNotLike(String value) {
            addCriterion("customer_open_id not like", value, "customerOpenId");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdIn(List<String> values) {
            addCriterion("customer_open_id in", values, "customerOpenId");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdNotIn(List<String> values) {
            addCriterion("customer_open_id not in", values, "customerOpenId");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdBetween(String value1, String value2) {
            addCriterion("customer_open_id between", value1, value2, "customerOpenId");
            return (Criteria) this;
        }

        public Criteria andCustomerOpenIdNotBetween(String value1, String value2) {
            addCriterion("customer_open_id not between", value1, value2, "customerOpenId");
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

        public Criteria andReceiveTimeIsNull() {
            addCriterion("receive_time is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNotNull() {
            addCriterion("receive_time is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeEqualTo(Date value) {
            addCriterion("receive_time =", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotEqualTo(Date value) {
            addCriterion("receive_time <>", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThan(Date value) {
            addCriterion("receive_time >", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("receive_time >=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThan(Date value) {
            addCriterion("receive_time <", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThanOrEqualTo(Date value) {
            addCriterion("receive_time <=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIn(List<Date> values) {
            addCriterion("receive_time in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotIn(List<Date> values) {
            addCriterion("receive_time not in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeBetween(Date value1, Date value2) {
            addCriterion("receive_time between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotBetween(Date value1, Date value2) {
            addCriterion("receive_time not between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReturnPayTimeIsNull() {
            addCriterion("return_pay_time is null");
            return (Criteria) this;
        }

        public Criteria andReturnPayTimeIsNotNull() {
            addCriterion("return_pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andReturnPayTimeEqualTo(Date value) {
            addCriterion("return_pay_time =", value, "returnPayTime");
            return (Criteria) this;
        }

        public Criteria andReturnPayTimeNotEqualTo(Date value) {
            addCriterion("return_pay_time <>", value, "returnPayTime");
            return (Criteria) this;
        }

        public Criteria andReturnPayTimeGreaterThan(Date value) {
            addCriterion("return_pay_time >", value, "returnPayTime");
            return (Criteria) this;
        }

        public Criteria andReturnPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("return_pay_time >=", value, "returnPayTime");
            return (Criteria) this;
        }

        public Criteria andReturnPayTimeLessThan(Date value) {
            addCriterion("return_pay_time <", value, "returnPayTime");
            return (Criteria) this;
        }

        public Criteria andReturnPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("return_pay_time <=", value, "returnPayTime");
            return (Criteria) this;
        }

        public Criteria andReturnPayTimeIn(List<Date> values) {
            addCriterion("return_pay_time in", values, "returnPayTime");
            return (Criteria) this;
        }

        public Criteria andReturnPayTimeNotIn(List<Date> values) {
            addCriterion("return_pay_time not in", values, "returnPayTime");
            return (Criteria) this;
        }

        public Criteria andReturnPayTimeBetween(Date value1, Date value2) {
            addCriterion("return_pay_time between", value1, value2, "returnPayTime");
            return (Criteria) this;
        }

        public Criteria andReturnPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("return_pay_time not between", value1, value2, "returnPayTime");
            return (Criteria) this;
        }

        public Criteria andDescIsNull() {
            addCriterion("desc is null");
            return (Criteria) this;
        }

        public Criteria andDescIsNotNull() {
            addCriterion("desc is not null");
            return (Criteria) this;
        }

        public Criteria andDescEqualTo(String value) {
            addCriterion("desc =", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotEqualTo(String value) {
            addCriterion("desc <>", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThan(String value) {
            addCriterion("desc >", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescGreaterThanOrEqualTo(String value) {
            addCriterion("desc >=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThan(String value) {
            addCriterion("desc <", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLessThanOrEqualTo(String value) {
            addCriterion("desc <=", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescLike(String value) {
            addCriterion("desc like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotLike(String value) {
            addCriterion("desc not like", value, "desc");
            return (Criteria) this;
        }

        public Criteria andDescIn(List<String> values) {
            addCriterion("desc in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotIn(List<String> values) {
            addCriterion("desc not in", values, "desc");
            return (Criteria) this;
        }

        public Criteria andDescBetween(String value1, String value2) {
            addCriterion("desc between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andDescNotBetween(String value1, String value2) {
            addCriterion("desc not between", value1, value2, "desc");
            return (Criteria) this;
        }

        public Criteria andReturnReferIsNull() {
            addCriterion("return_refer is null");
            return (Criteria) this;
        }

        public Criteria andReturnReferIsNotNull() {
            addCriterion("return_refer is not null");
            return (Criteria) this;
        }

        public Criteria andReturnReferEqualTo(Integer value) {
            addCriterion("return_refer =", value, "returnRefer");
            return (Criteria) this;
        }

        public Criteria andReturnReferNotEqualTo(Integer value) {
            addCriterion("return_refer <>", value, "returnRefer");
            return (Criteria) this;
        }

        public Criteria andReturnReferGreaterThan(Integer value) {
            addCriterion("return_refer >", value, "returnRefer");
            return (Criteria) this;
        }

        public Criteria andReturnReferGreaterThanOrEqualTo(Integer value) {
            addCriterion("return_refer >=", value, "returnRefer");
            return (Criteria) this;
        }

        public Criteria andReturnReferLessThan(Integer value) {
            addCriterion("return_refer <", value, "returnRefer");
            return (Criteria) this;
        }

        public Criteria andReturnReferLessThanOrEqualTo(Integer value) {
            addCriterion("return_refer <=", value, "returnRefer");
            return (Criteria) this;
        }

        public Criteria andReturnReferIn(List<Integer> values) {
            addCriterion("return_refer in", values, "returnRefer");
            return (Criteria) this;
        }

        public Criteria andReturnReferNotIn(List<Integer> values) {
            addCriterion("return_refer not in", values, "returnRefer");
            return (Criteria) this;
        }

        public Criteria andReturnReferBetween(Integer value1, Integer value2) {
            addCriterion("return_refer between", value1, value2, "returnRefer");
            return (Criteria) this;
        }

        public Criteria andReturnReferNotBetween(Integer value1, Integer value2) {
            addCriterion("return_refer not between", value1, value2, "returnRefer");
            return (Criteria) this;
        }

        public Criteria andProofImgIsNull() {
            addCriterion("proof_img is null");
            return (Criteria) this;
        }

        public Criteria andProofImgIsNotNull() {
            addCriterion("proof_img is not null");
            return (Criteria) this;
        }

        public Criteria andProofImgEqualTo(String value) {
            addCriterion("proof_img =", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgNotEqualTo(String value) {
            addCriterion("proof_img <>", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgGreaterThan(String value) {
            addCriterion("proof_img >", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgGreaterThanOrEqualTo(String value) {
            addCriterion("proof_img >=", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgLessThan(String value) {
            addCriterion("proof_img <", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgLessThanOrEqualTo(String value) {
            addCriterion("proof_img <=", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgLike(String value) {
            addCriterion("proof_img like", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgNotLike(String value) {
            addCriterion("proof_img not like", value, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgIn(List<String> values) {
            addCriterion("proof_img in", values, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgNotIn(List<String> values) {
            addCriterion("proof_img not in", values, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgBetween(String value1, String value2) {
            addCriterion("proof_img between", value1, value2, "proofImg");
            return (Criteria) this;
        }

        public Criteria andProofImgNotBetween(String value1, String value2) {
            addCriterion("proof_img not between", value1, value2, "proofImg");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIsNull() {
            addCriterion("return_type is null");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIsNotNull() {
            addCriterion("return_type is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTypeEqualTo(Integer value) {
            addCriterion("return_type =", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotEqualTo(Integer value) {
            addCriterion("return_type <>", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeGreaterThan(Integer value) {
            addCriterion("return_type >", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("return_type >=", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeLessThan(Integer value) {
            addCriterion("return_type <", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeLessThanOrEqualTo(Integer value) {
            addCriterion("return_type <=", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIn(List<Integer> values) {
            addCriterion("return_type in", values, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotIn(List<Integer> values) {
            addCriterion("return_type not in", values, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeBetween(Integer value1, Integer value2) {
            addCriterion("return_type between", value1, value2, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("return_type not between", value1, value2, "returnType");
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