package com.wangqin.shopkeeper.biz1.app.dal.dataObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuyerTaskDetailDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BuyerTaskDetailDOExample() {
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

        public Criteria andBuyerTaskNoIsNull() {
            addCriterion("buyer_task_no is null");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoIsNotNull() {
            addCriterion("buyer_task_no is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoEqualTo(String value) {
            addCriterion("buyer_task_no =", value, "buyerTaskNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoNotEqualTo(String value) {
            addCriterion("buyer_task_no <>", value, "buyerTaskNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoGreaterThan(String value) {
            addCriterion("buyer_task_no >", value, "buyerTaskNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_task_no >=", value, "buyerTaskNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoLessThan(String value) {
            addCriterion("buyer_task_no <", value, "buyerTaskNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoLessThanOrEqualTo(String value) {
            addCriterion("buyer_task_no <=", value, "buyerTaskNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoLike(String value) {
            addCriterion("buyer_task_no like", value, "buyerTaskNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoNotLike(String value) {
            addCriterion("buyer_task_no not like", value, "buyerTaskNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoIn(List<String> values) {
            addCriterion("buyer_task_no in", values, "buyerTaskNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoNotIn(List<String> values) {
            addCriterion("buyer_task_no not in", values, "buyerTaskNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoBetween(String value1, String value2) {
            addCriterion("buyer_task_no between", value1, value2, "buyerTaskNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskNoNotBetween(String value1, String value2) {
            addCriterion("buyer_task_no not between", value1, value2, "buyerTaskNo");
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

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Float value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Float value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Float value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Float value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Float value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Float> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Float> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Float value1, Float value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Float value1, Float value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andMaxPriceIsNull() {
            addCriterion("max_price is null");
            return (Criteria) this;
        }

        public Criteria andMaxPriceIsNotNull() {
            addCriterion("max_price is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPriceEqualTo(Float value) {
            addCriterion("max_price =", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceNotEqualTo(Float value) {
            addCriterion("max_price <>", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceGreaterThan(Float value) {
            addCriterion("max_price >", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceGreaterThanOrEqualTo(Float value) {
            addCriterion("max_price >=", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceLessThan(Float value) {
            addCriterion("max_price <", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceLessThanOrEqualTo(Float value) {
            addCriterion("max_price <=", value, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceIn(List<Float> values) {
            addCriterion("max_price in", values, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceNotIn(List<Float> values) {
            addCriterion("max_price not in", values, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceBetween(Float value1, Float value2) {
            addCriterion("max_price between", value1, value2, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andMaxPriceNotBetween(Float value1, Float value2) {
            addCriterion("max_price not between", value1, value2, "maxPrice");
            return (Criteria) this;
        }

        public Criteria andCountIsNull() {
            addCriterion("count is null");
            return (Criteria) this;
        }

        public Criteria andCountIsNotNull() {
            addCriterion("count is not null");
            return (Criteria) this;
        }

        public Criteria andCountEqualTo(Integer value) {
            addCriterion("count =", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotEqualTo(Integer value) {
            addCriterion("count <>", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThan(Integer value) {
            addCriterion("count >", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("count >=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThan(Integer value) {
            addCriterion("count <", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountLessThanOrEqualTo(Integer value) {
            addCriterion("count <=", value, "count");
            return (Criteria) this;
        }

        public Criteria andCountIn(List<Integer> values) {
            addCriterion("count in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotIn(List<Integer> values) {
            addCriterion("count not in", values, "count");
            return (Criteria) this;
        }

        public Criteria andCountBetween(Integer value1, Integer value2) {
            addCriterion("count between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andCountNotBetween(Integer value1, Integer value2) {
            addCriterion("count not between", value1, value2, "count");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIsNull() {
            addCriterion("owner_name is null");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIsNotNull() {
            addCriterion("owner_name is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerNameEqualTo(String value) {
            addCriterion("owner_name =", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotEqualTo(String value) {
            addCriterion("owner_name <>", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameGreaterThan(String value) {
            addCriterion("owner_name >", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameGreaterThanOrEqualTo(String value) {
            addCriterion("owner_name >=", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLessThan(String value) {
            addCriterion("owner_name <", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLessThanOrEqualTo(String value) {
            addCriterion("owner_name <=", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameLike(String value) {
            addCriterion("owner_name like", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotLike(String value) {
            addCriterion("owner_name not like", value, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameIn(List<String> values) {
            addCriterion("owner_name in", values, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotIn(List<String> values) {
            addCriterion("owner_name not in", values, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameBetween(String value1, String value2) {
            addCriterion("owner_name between", value1, value2, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNameNotBetween(String value1, String value2) {
            addCriterion("owner_name not between", value1, value2, "ownerName");
            return (Criteria) this;
        }

        public Criteria andOwnerNoIsNull() {
            addCriterion("owner_no is null");
            return (Criteria) this;
        }

        public Criteria andOwnerNoIsNotNull() {
            addCriterion("owner_no is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerNoEqualTo(String value) {
            addCriterion("owner_no =", value, "ownerNo");
            return (Criteria) this;
        }

        public Criteria andOwnerNoNotEqualTo(String value) {
            addCriterion("owner_no <>", value, "ownerNo");
            return (Criteria) this;
        }

        public Criteria andOwnerNoGreaterThan(String value) {
            addCriterion("owner_no >", value, "ownerNo");
            return (Criteria) this;
        }

        public Criteria andOwnerNoGreaterThanOrEqualTo(String value) {
            addCriterion("owner_no >=", value, "ownerNo");
            return (Criteria) this;
        }

        public Criteria andOwnerNoLessThan(String value) {
            addCriterion("owner_no <", value, "ownerNo");
            return (Criteria) this;
        }

        public Criteria andOwnerNoLessThanOrEqualTo(String value) {
            addCriterion("owner_no <=", value, "ownerNo");
            return (Criteria) this;
        }

        public Criteria andOwnerNoLike(String value) {
            addCriterion("owner_no like", value, "ownerNo");
            return (Criteria) this;
        }

        public Criteria andOwnerNoNotLike(String value) {
            addCriterion("owner_no not like", value, "ownerNo");
            return (Criteria) this;
        }

        public Criteria andOwnerNoIn(List<String> values) {
            addCriterion("owner_no in", values, "ownerNo");
            return (Criteria) this;
        }

        public Criteria andOwnerNoNotIn(List<String> values) {
            addCriterion("owner_no not in", values, "ownerNo");
            return (Criteria) this;
        }

        public Criteria andOwnerNoBetween(String value1, String value2) {
            addCriterion("owner_no between", value1, value2, "ownerNo");
            return (Criteria) this;
        }

        public Criteria andOwnerNoNotBetween(String value1, String value2) {
            addCriterion("owner_no not between", value1, value2, "ownerNo");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(Byte value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(Byte value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(Byte value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(Byte value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(Byte value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(Byte value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<Byte> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<Byte> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(Byte value1, Byte value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(Byte value1, Byte value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andBuyerNameIsNull() {
            addCriterion("buyer_name is null");
            return (Criteria) this;
        }

        public Criteria andBuyerNameIsNotNull() {
            addCriterion("buyer_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerNameEqualTo(String value) {
            addCriterion("buyer_name =", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameNotEqualTo(String value) {
            addCriterion("buyer_name <>", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameGreaterThan(String value) {
            addCriterion("buyer_name >", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_name >=", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameLessThan(String value) {
            addCriterion("buyer_name <", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameLessThanOrEqualTo(String value) {
            addCriterion("buyer_name <=", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameLike(String value) {
            addCriterion("buyer_name like", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameNotLike(String value) {
            addCriterion("buyer_name not like", value, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameIn(List<String> values) {
            addCriterion("buyer_name in", values, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameNotIn(List<String> values) {
            addCriterion("buyer_name not in", values, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameBetween(String value1, String value2) {
            addCriterion("buyer_name between", value1, value2, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerNameNotBetween(String value1, String value2) {
            addCriterion("buyer_name not between", value1, value2, "buyerName");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdIsNull() {
            addCriterion("buyer_open_id is null");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdIsNotNull() {
            addCriterion("buyer_open_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdEqualTo(Long value) {
            addCriterion("buyer_open_id =", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdNotEqualTo(Long value) {
            addCriterion("buyer_open_id <>", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdGreaterThan(Long value) {
            addCriterion("buyer_open_id >", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdGreaterThanOrEqualTo(Long value) {
            addCriterion("buyer_open_id >=", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdLessThan(Long value) {
            addCriterion("buyer_open_id <", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdLessThanOrEqualTo(Long value) {
            addCriterion("buyer_open_id <=", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdIn(List<Long> values) {
            addCriterion("buyer_open_id in", values, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdNotIn(List<Long> values) {
            addCriterion("buyer_open_id not in", values, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdBetween(Long value1, Long value2) {
            addCriterion("buyer_open_id between", value1, value2, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdNotBetween(Long value1, Long value2) {
            addCriterion("buyer_open_id not between", value1, value2, "buyerOpenId");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andModeIsNull() {
            addCriterion("mode is null");
            return (Criteria) this;
        }

        public Criteria andModeIsNotNull() {
            addCriterion("mode is not null");
            return (Criteria) this;
        }

        public Criteria andModeEqualTo(Byte value) {
            addCriterion("mode =", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotEqualTo(Byte value) {
            addCriterion("mode <>", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThan(Byte value) {
            addCriterion("mode >", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeGreaterThanOrEqualTo(Byte value) {
            addCriterion("mode >=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThan(Byte value) {
            addCriterion("mode <", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeLessThanOrEqualTo(Byte value) {
            addCriterion("mode <=", value, "mode");
            return (Criteria) this;
        }

        public Criteria andModeIn(List<Byte> values) {
            addCriterion("mode in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotIn(List<Byte> values) {
            addCriterion("mode not in", values, "mode");
            return (Criteria) this;
        }

        public Criteria andModeBetween(Byte value1, Byte value2) {
            addCriterion("mode between", value1, value2, "mode");
            return (Criteria) this;
        }

        public Criteria andModeNotBetween(Byte value1, Byte value2) {
            addCriterion("mode not between", value1, value2, "mode");
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

        public Criteria andMaxCountIsNull() {
            addCriterion("max_count is null");
            return (Criteria) this;
        }

        public Criteria andMaxCountIsNotNull() {
            addCriterion("max_count is not null");
            return (Criteria) this;
        }

        public Criteria andMaxCountEqualTo(Integer value) {
            addCriterion("max_count =", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotEqualTo(Integer value) {
            addCriterion("max_count <>", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountGreaterThan(Integer value) {
            addCriterion("max_count >", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_count >=", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountLessThan(Integer value) {
            addCriterion("max_count <", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountLessThanOrEqualTo(Integer value) {
            addCriterion("max_count <=", value, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountIn(List<Integer> values) {
            addCriterion("max_count in", values, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotIn(List<Integer> values) {
            addCriterion("max_count not in", values, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountBetween(Integer value1, Integer value2) {
            addCriterion("max_count between", value1, value2, "maxCount");
            return (Criteria) this;
        }

        public Criteria andMaxCountNotBetween(Integer value1, Integer value2) {
            addCriterion("max_count not between", value1, value2, "maxCount");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlIsNull() {
            addCriterion("sku_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlIsNotNull() {
            addCriterion("sku_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlEqualTo(String value) {
            addCriterion("sku_pic_url =", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlNotEqualTo(String value) {
            addCriterion("sku_pic_url <>", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlGreaterThan(String value) {
            addCriterion("sku_pic_url >", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("sku_pic_url >=", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlLessThan(String value) {
            addCriterion("sku_pic_url <", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlLessThanOrEqualTo(String value) {
            addCriterion("sku_pic_url <=", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlLike(String value) {
            addCriterion("sku_pic_url like", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlNotLike(String value) {
            addCriterion("sku_pic_url not like", value, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlIn(List<String> values) {
            addCriterion("sku_pic_url in", values, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlNotIn(List<String> values) {
            addCriterion("sku_pic_url not in", values, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlBetween(String value1, String value2) {
            addCriterion("sku_pic_url between", value1, value2, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andSkuPicUrlNotBetween(String value1, String value2) {
            addCriterion("sku_pic_url not between", value1, value2, "skuPicUrl");
            return (Criteria) this;
        }

        public Criteria andEntryCountIsNull() {
            addCriterion("entry_count is null");
            return (Criteria) this;
        }

        public Criteria andEntryCountIsNotNull() {
            addCriterion("entry_count is not null");
            return (Criteria) this;
        }

        public Criteria andEntryCountEqualTo(Integer value) {
            addCriterion("entry_count =", value, "entryCount");
            return (Criteria) this;
        }

        public Criteria andEntryCountNotEqualTo(Integer value) {
            addCriterion("entry_count <>", value, "entryCount");
            return (Criteria) this;
        }

        public Criteria andEntryCountGreaterThan(Integer value) {
            addCriterion("entry_count >", value, "entryCount");
            return (Criteria) this;
        }

        public Criteria andEntryCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("entry_count >=", value, "entryCount");
            return (Criteria) this;
        }

        public Criteria andEntryCountLessThan(Integer value) {
            addCriterion("entry_count <", value, "entryCount");
            return (Criteria) this;
        }

        public Criteria andEntryCountLessThanOrEqualTo(Integer value) {
            addCriterion("entry_count <=", value, "entryCount");
            return (Criteria) this;
        }

        public Criteria andEntryCountIn(List<Integer> values) {
            addCriterion("entry_count in", values, "entryCount");
            return (Criteria) this;
        }

        public Criteria andEntryCountNotIn(List<Integer> values) {
            addCriterion("entry_count not in", values, "entryCount");
            return (Criteria) this;
        }

        public Criteria andEntryCountBetween(Integer value1, Integer value2) {
            addCriterion("entry_count between", value1, value2, "entryCount");
            return (Criteria) this;
        }

        public Criteria andEntryCountNotBetween(Integer value1, Integer value2) {
            addCriterion("entry_count not between", value1, value2, "entryCount");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonIsNull() {
            addCriterion("upc_pass_reason is null");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonIsNotNull() {
            addCriterion("upc_pass_reason is not null");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonEqualTo(String value) {
            addCriterion("upc_pass_reason =", value, "upcPassReason");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonNotEqualTo(String value) {
            addCriterion("upc_pass_reason <>", value, "upcPassReason");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonGreaterThan(String value) {
            addCriterion("upc_pass_reason >", value, "upcPassReason");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonGreaterThanOrEqualTo(String value) {
            addCriterion("upc_pass_reason >=", value, "upcPassReason");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonLessThan(String value) {
            addCriterion("upc_pass_reason <", value, "upcPassReason");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonLessThanOrEqualTo(String value) {
            addCriterion("upc_pass_reason <=", value, "upcPassReason");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonLike(String value) {
            addCriterion("upc_pass_reason like", value, "upcPassReason");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonNotLike(String value) {
            addCriterion("upc_pass_reason not like", value, "upcPassReason");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonIn(List<String> values) {
            addCriterion("upc_pass_reason in", values, "upcPassReason");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonNotIn(List<String> values) {
            addCriterion("upc_pass_reason not in", values, "upcPassReason");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonBetween(String value1, String value2) {
            addCriterion("upc_pass_reason between", value1, value2, "upcPassReason");
            return (Criteria) this;
        }

        public Criteria andUpcPassReasonNotBetween(String value1, String value2) {
            addCriterion("upc_pass_reason not between", value1, value2, "upcPassReason");
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