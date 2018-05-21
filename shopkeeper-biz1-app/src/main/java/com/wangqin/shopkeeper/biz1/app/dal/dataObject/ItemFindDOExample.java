package com.wangqin.shopkeeper.biz1.app.dal.dataObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemFindDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ItemFindDOExample() {
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

        public Criteria andItemFindNoIsNull() {
            addCriterion("item_find_no is null");
            return (Criteria) this;
        }

        public Criteria andItemFindNoIsNotNull() {
            addCriterion("item_find_no is not null");
            return (Criteria) this;
        }

        public Criteria andItemFindNoEqualTo(String value) {
            addCriterion("item_find_no =", value, "itemFindNo");
            return (Criteria) this;
        }

        public Criteria andItemFindNoNotEqualTo(String value) {
            addCriterion("item_find_no <>", value, "itemFindNo");
            return (Criteria) this;
        }

        public Criteria andItemFindNoGreaterThan(String value) {
            addCriterion("item_find_no >", value, "itemFindNo");
            return (Criteria) this;
        }

        public Criteria andItemFindNoGreaterThanOrEqualTo(String value) {
            addCriterion("item_find_no >=", value, "itemFindNo");
            return (Criteria) this;
        }

        public Criteria andItemFindNoLessThan(String value) {
            addCriterion("item_find_no <", value, "itemFindNo");
            return (Criteria) this;
        }

        public Criteria andItemFindNoLessThanOrEqualTo(String value) {
            addCriterion("item_find_no <=", value, "itemFindNo");
            return (Criteria) this;
        }

        public Criteria andItemFindNoLike(String value) {
            addCriterion("item_find_no like", value, "itemFindNo");
            return (Criteria) this;
        }

        public Criteria andItemFindNoNotLike(String value) {
            addCriterion("item_find_no not like", value, "itemFindNo");
            return (Criteria) this;
        }

        public Criteria andItemFindNoIn(List<String> values) {
            addCriterion("item_find_no in", values, "itemFindNo");
            return (Criteria) this;
        }

        public Criteria andItemFindNoNotIn(List<String> values) {
            addCriterion("item_find_no not in", values, "itemFindNo");
            return (Criteria) this;
        }

        public Criteria andItemFindNoBetween(String value1, String value2) {
            addCriterion("item_find_no between", value1, value2, "itemFindNo");
            return (Criteria) this;
        }

        public Criteria andItemFindNoNotBetween(String value1, String value2) {
            addCriterion("item_find_no not between", value1, value2, "itemFindNo");
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

        public Criteria andIsNewIsNull() {
            addCriterion("is_new is null");
            return (Criteria) this;
        }

        public Criteria andIsNewIsNotNull() {
            addCriterion("is_new is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewEqualTo(Byte value) {
            addCriterion("is_new =", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotEqualTo(Byte value) {
            addCriterion("is_new <>", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewGreaterThan(Byte value) {
            addCriterion("is_new >", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_new >=", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLessThan(Byte value) {
            addCriterion("is_new <", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLessThanOrEqualTo(Byte value) {
            addCriterion("is_new <=", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewIn(List<Byte> values) {
            addCriterion("is_new in", values, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotIn(List<Byte> values) {
            addCriterion("is_new not in", values, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewBetween(Byte value1, Byte value2) {
            addCriterion("is_new between", value1, value2, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotBetween(Byte value1, Byte value2) {
            addCriterion("is_new not between", value1, value2, "isNew");
            return (Criteria) this;
        }

        public Criteria andSaleTypeIsNull() {
            addCriterion("sale_type is null");
            return (Criteria) this;
        }

        public Criteria andSaleTypeIsNotNull() {
            addCriterion("sale_type is not null");
            return (Criteria) this;
        }

        public Criteria andSaleTypeEqualTo(Byte value) {
            addCriterion("sale_type =", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotEqualTo(Byte value) {
            addCriterion("sale_type <>", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeGreaterThan(Byte value) {
            addCriterion("sale_type >", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("sale_type >=", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeLessThan(Byte value) {
            addCriterion("sale_type <", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeLessThanOrEqualTo(Byte value) {
            addCriterion("sale_type <=", value, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeIn(List<Byte> values) {
            addCriterion("sale_type in", values, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotIn(List<Byte> values) {
            addCriterion("sale_type not in", values, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeBetween(Byte value1, Byte value2) {
            addCriterion("sale_type between", value1, value2, "saleType");
            return (Criteria) this;
        }

        public Criteria andSaleTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("sale_type not between", value1, value2, "saleType");
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

        public Criteria andBuySiteIsNull() {
            addCriterion("buy_site is null");
            return (Criteria) this;
        }

        public Criteria andBuySiteIsNotNull() {
            addCriterion("buy_site is not null");
            return (Criteria) this;
        }

        public Criteria andBuySiteEqualTo(String value) {
            addCriterion("buy_site =", value, "buySite");
            return (Criteria) this;
        }

        public Criteria andBuySiteNotEqualTo(String value) {
            addCriterion("buy_site <>", value, "buySite");
            return (Criteria) this;
        }

        public Criteria andBuySiteGreaterThan(String value) {
            addCriterion("buy_site >", value, "buySite");
            return (Criteria) this;
        }

        public Criteria andBuySiteGreaterThanOrEqualTo(String value) {
            addCriterion("buy_site >=", value, "buySite");
            return (Criteria) this;
        }

        public Criteria andBuySiteLessThan(String value) {
            addCriterion("buy_site <", value, "buySite");
            return (Criteria) this;
        }

        public Criteria andBuySiteLessThanOrEqualTo(String value) {
            addCriterion("buy_site <=", value, "buySite");
            return (Criteria) this;
        }

        public Criteria andBuySiteLike(String value) {
            addCriterion("buy_site like", value, "buySite");
            return (Criteria) this;
        }

        public Criteria andBuySiteNotLike(String value) {
            addCriterion("buy_site not like", value, "buySite");
            return (Criteria) this;
        }

        public Criteria andBuySiteIn(List<String> values) {
            addCriterion("buy_site in", values, "buySite");
            return (Criteria) this;
        }

        public Criteria andBuySiteNotIn(List<String> values) {
            addCriterion("buy_site not in", values, "buySite");
            return (Criteria) this;
        }

        public Criteria andBuySiteBetween(String value1, String value2) {
            addCriterion("buy_site between", value1, value2, "buySite");
            return (Criteria) this;
        }

        public Criteria andBuySiteNotBetween(String value1, String value2) {
            addCriterion("buy_site not between", value1, value2, "buySite");
            return (Criteria) this;
        }

        public Criteria andOriginIsNull() {
            addCriterion("origin is null");
            return (Criteria) this;
        }

        public Criteria andOriginIsNotNull() {
            addCriterion("origin is not null");
            return (Criteria) this;
        }

        public Criteria andOriginEqualTo(String value) {
            addCriterion("origin =", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotEqualTo(String value) {
            addCriterion("origin <>", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginGreaterThan(String value) {
            addCriterion("origin >", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginGreaterThanOrEqualTo(String value) {
            addCriterion("origin >=", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLessThan(String value) {
            addCriterion("origin <", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLessThanOrEqualTo(String value) {
            addCriterion("origin <=", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLike(String value) {
            addCriterion("origin like", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotLike(String value) {
            addCriterion("origin not like", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginIn(List<String> values) {
            addCriterion("origin in", values, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotIn(List<String> values) {
            addCriterion("origin not in", values, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginBetween(String value1, String value2) {
            addCriterion("origin between", value1, value2, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotBetween(String value1, String value2) {
            addCriterion("origin not between", value1, value2, "origin");
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

        public Criteria andContactPersonIsNull() {
            addCriterion("contact_person is null");
            return (Criteria) this;
        }

        public Criteria andContactPersonIsNotNull() {
            addCriterion("contact_person is not null");
            return (Criteria) this;
        }

        public Criteria andContactPersonEqualTo(String value) {
            addCriterion("contact_person =", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonNotEqualTo(String value) {
            addCriterion("contact_person <>", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonGreaterThan(String value) {
            addCriterion("contact_person >", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonGreaterThanOrEqualTo(String value) {
            addCriterion("contact_person >=", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonLessThan(String value) {
            addCriterion("contact_person <", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonLessThanOrEqualTo(String value) {
            addCriterion("contact_person <=", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonLike(String value) {
            addCriterion("contact_person like", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonNotLike(String value) {
            addCriterion("contact_person not like", value, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonIn(List<String> values) {
            addCriterion("contact_person in", values, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonNotIn(List<String> values) {
            addCriterion("contact_person not in", values, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonBetween(String value1, String value2) {
            addCriterion("contact_person between", value1, value2, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactPersonNotBetween(String value1, String value2) {
            addCriterion("contact_person not between", value1, value2, "contactPerson");
            return (Criteria) this;
        }

        public Criteria andContactTelIsNull() {
            addCriterion("contact_tel is null");
            return (Criteria) this;
        }

        public Criteria andContactTelIsNotNull() {
            addCriterion("contact_tel is not null");
            return (Criteria) this;
        }

        public Criteria andContactTelEqualTo(String value) {
            addCriterion("contact_tel =", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotEqualTo(String value) {
            addCriterion("contact_tel <>", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelGreaterThan(String value) {
            addCriterion("contact_tel >", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelGreaterThanOrEqualTo(String value) {
            addCriterion("contact_tel >=", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLessThan(String value) {
            addCriterion("contact_tel <", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLessThanOrEqualTo(String value) {
            addCriterion("contact_tel <=", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelLike(String value) {
            addCriterion("contact_tel like", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotLike(String value) {
            addCriterion("contact_tel not like", value, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelIn(List<String> values) {
            addCriterion("contact_tel in", values, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotIn(List<String> values) {
            addCriterion("contact_tel not in", values, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelBetween(String value1, String value2) {
            addCriterion("contact_tel between", value1, value2, "contactTel");
            return (Criteria) this;
        }

        public Criteria andContactTelNotBetween(String value1, String value2) {
            addCriterion("contact_tel not between", value1, value2, "contactTel");
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

        public Criteria andIdCardEqualTo(Byte value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(Byte value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(Byte value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(Byte value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(Byte value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(Byte value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<Byte> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<Byte> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(Byte value1, Byte value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(Byte value1, Byte value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(Date value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(Date value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(Date value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(Date value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(Date value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<Date> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<Date> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(Date value1, Date value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(Date value1, Date value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andBookingDateIsNull() {
            addCriterion("booking_date is null");
            return (Criteria) this;
        }

        public Criteria andBookingDateIsNotNull() {
            addCriterion("booking_date is not null");
            return (Criteria) this;
        }

        public Criteria andBookingDateEqualTo(Date value) {
            addCriterion("booking_date =", value, "bookingDate");
            return (Criteria) this;
        }

        public Criteria andBookingDateNotEqualTo(Date value) {
            addCriterion("booking_date <>", value, "bookingDate");
            return (Criteria) this;
        }

        public Criteria andBookingDateGreaterThan(Date value) {
            addCriterion("booking_date >", value, "bookingDate");
            return (Criteria) this;
        }

        public Criteria andBookingDateGreaterThanOrEqualTo(Date value) {
            addCriterion("booking_date >=", value, "bookingDate");
            return (Criteria) this;
        }

        public Criteria andBookingDateLessThan(Date value) {
            addCriterion("booking_date <", value, "bookingDate");
            return (Criteria) this;
        }

        public Criteria andBookingDateLessThanOrEqualTo(Date value) {
            addCriterion("booking_date <=", value, "bookingDate");
            return (Criteria) this;
        }

        public Criteria andBookingDateIn(List<Date> values) {
            addCriterion("booking_date in", values, "bookingDate");
            return (Criteria) this;
        }

        public Criteria andBookingDateNotIn(List<Date> values) {
            addCriterion("booking_date not in", values, "bookingDate");
            return (Criteria) this;
        }

        public Criteria andBookingDateBetween(Date value1, Date value2) {
            addCriterion("booking_date between", value1, value2, "bookingDate");
            return (Criteria) this;
        }

        public Criteria andBookingDateNotBetween(Date value1, Date value2) {
            addCriterion("booking_date not between", value1, value2, "bookingDate");
            return (Criteria) this;
        }

        public Criteria andIsSaleIsNull() {
            addCriterion("is_sale is null");
            return (Criteria) this;
        }

        public Criteria andIsSaleIsNotNull() {
            addCriterion("is_sale is not null");
            return (Criteria) this;
        }

        public Criteria andIsSaleEqualTo(Byte value) {
            addCriterion("is_sale =", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleNotEqualTo(Byte value) {
            addCriterion("is_sale <>", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleGreaterThan(Byte value) {
            addCriterion("is_sale >", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_sale >=", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleLessThan(Byte value) {
            addCriterion("is_sale <", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleLessThanOrEqualTo(Byte value) {
            addCriterion("is_sale <=", value, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleIn(List<Byte> values) {
            addCriterion("is_sale in", values, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleNotIn(List<Byte> values) {
            addCriterion("is_sale not in", values, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleBetween(Byte value1, Byte value2) {
            addCriterion("is_sale between", value1, value2, "isSale");
            return (Criteria) this;
        }

        public Criteria andIsSaleNotBetween(Byte value1, Byte value2) {
            addCriterion("is_sale not between", value1, value2, "isSale");
            return (Criteria) this;
        }

        public Criteria andWxisSaleIsNull() {
            addCriterion("wxis_sale is null");
            return (Criteria) this;
        }

        public Criteria andWxisSaleIsNotNull() {
            addCriterion("wxis_sale is not null");
            return (Criteria) this;
        }

        public Criteria andWxisSaleEqualTo(Byte value) {
            addCriterion("wxis_sale =", value, "wxisSale");
            return (Criteria) this;
        }

        public Criteria andWxisSaleNotEqualTo(Byte value) {
            addCriterion("wxis_sale <>", value, "wxisSale");
            return (Criteria) this;
        }

        public Criteria andWxisSaleGreaterThan(Byte value) {
            addCriterion("wxis_sale >", value, "wxisSale");
            return (Criteria) this;
        }

        public Criteria andWxisSaleGreaterThanOrEqualTo(Byte value) {
            addCriterion("wxis_sale >=", value, "wxisSale");
            return (Criteria) this;
        }

        public Criteria andWxisSaleLessThan(Byte value) {
            addCriterion("wxis_sale <", value, "wxisSale");
            return (Criteria) this;
        }

        public Criteria andWxisSaleLessThanOrEqualTo(Byte value) {
            addCriterion("wxis_sale <=", value, "wxisSale");
            return (Criteria) this;
        }

        public Criteria andWxisSaleIn(List<Byte> values) {
            addCriterion("wxis_sale in", values, "wxisSale");
            return (Criteria) this;
        }

        public Criteria andWxisSaleNotIn(List<Byte> values) {
            addCriterion("wxis_sale not in", values, "wxisSale");
            return (Criteria) this;
        }

        public Criteria andWxisSaleBetween(Byte value1, Byte value2) {
            addCriterion("wxis_sale between", value1, value2, "wxisSale");
            return (Criteria) this;
        }

        public Criteria andWxisSaleNotBetween(Byte value1, Byte value2) {
            addCriterion("wxis_sale not between", value1, value2, "wxisSale");
            return (Criteria) this;
        }

        public Criteria andIsFindIsNull() {
            addCriterion("is_find is null");
            return (Criteria) this;
        }

        public Criteria andIsFindIsNotNull() {
            addCriterion("is_find is not null");
            return (Criteria) this;
        }

        public Criteria andIsFindEqualTo(Byte value) {
            addCriterion("is_find =", value, "isFind");
            return (Criteria) this;
        }

        public Criteria andIsFindNotEqualTo(Byte value) {
            addCriterion("is_find <>", value, "isFind");
            return (Criteria) this;
        }

        public Criteria andIsFindGreaterThan(Byte value) {
            addCriterion("is_find >", value, "isFind");
            return (Criteria) this;
        }

        public Criteria andIsFindGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_find >=", value, "isFind");
            return (Criteria) this;
        }

        public Criteria andIsFindLessThan(Byte value) {
            addCriterion("is_find <", value, "isFind");
            return (Criteria) this;
        }

        public Criteria andIsFindLessThanOrEqualTo(Byte value) {
            addCriterion("is_find <=", value, "isFind");
            return (Criteria) this;
        }

        public Criteria andIsFindIn(List<Byte> values) {
            addCriterion("is_find in", values, "isFind");
            return (Criteria) this;
        }

        public Criteria andIsFindNotIn(List<Byte> values) {
            addCriterion("is_find not in", values, "isFind");
            return (Criteria) this;
        }

        public Criteria andIsFindBetween(Byte value1, Byte value2) {
            addCriterion("is_find between", value1, value2, "isFind");
            return (Criteria) this;
        }

        public Criteria andIsFindNotBetween(Byte value1, Byte value2) {
            addCriterion("is_find not between", value1, value2, "isFind");
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

        public Criteria andSpecIsNull() {
            addCriterion("spec is null");
            return (Criteria) this;
        }

        public Criteria andSpecIsNotNull() {
            addCriterion("spec is not null");
            return (Criteria) this;
        }

        public Criteria andSpecEqualTo(String value) {
            addCriterion("spec =", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotEqualTo(String value) {
            addCriterion("spec <>", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecGreaterThan(String value) {
            addCriterion("spec >", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecGreaterThanOrEqualTo(String value) {
            addCriterion("spec >=", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLessThan(String value) {
            addCriterion("spec <", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLessThanOrEqualTo(String value) {
            addCriterion("spec <=", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecLike(String value) {
            addCriterion("spec like", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotLike(String value) {
            addCriterion("spec not like", value, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecIn(List<String> values) {
            addCriterion("spec in", values, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotIn(List<String> values) {
            addCriterion("spec not in", values, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecBetween(String value1, String value2) {
            addCriterion("spec between", value1, value2, "spec");
            return (Criteria) this;
        }

        public Criteria andSpecNotBetween(String value1, String value2) {
            addCriterion("spec not between", value1, value2, "spec");
            return (Criteria) this;
        }

        public Criteria andModelIsNull() {
            addCriterion("model is null");
            return (Criteria) this;
        }

        public Criteria andModelIsNotNull() {
            addCriterion("model is not null");
            return (Criteria) this;
        }

        public Criteria andModelEqualTo(String value) {
            addCriterion("model =", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotEqualTo(String value) {
            addCriterion("model <>", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThan(String value) {
            addCriterion("model >", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelGreaterThanOrEqualTo(String value) {
            addCriterion("model >=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThan(String value) {
            addCriterion("model <", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLessThanOrEqualTo(String value) {
            addCriterion("model <=", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelLike(String value) {
            addCriterion("model like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotLike(String value) {
            addCriterion("model not like", value, "model");
            return (Criteria) this;
        }

        public Criteria andModelIn(List<String> values) {
            addCriterion("model in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotIn(List<String> values) {
            addCriterion("model not in", values, "model");
            return (Criteria) this;
        }

        public Criteria andModelBetween(String value1, String value2) {
            addCriterion("model between", value1, value2, "model");
            return (Criteria) this;
        }

        public Criteria andModelNotBetween(String value1, String value2) {
            addCriterion("model not between", value1, value2, "model");
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

        public Criteria andBuyerOpenIdEqualTo(String value) {
            addCriterion("buyer_open_id =", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdNotEqualTo(String value) {
            addCriterion("buyer_open_id <>", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdGreaterThan(String value) {
            addCriterion("buyer_open_id >", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_open_id >=", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdLessThan(String value) {
            addCriterion("buyer_open_id <", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdLessThanOrEqualTo(String value) {
            addCriterion("buyer_open_id <=", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdLike(String value) {
            addCriterion("buyer_open_id like", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdNotLike(String value) {
            addCriterion("buyer_open_id not like", value, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdIn(List<String> values) {
            addCriterion("buyer_open_id in", values, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdNotIn(List<String> values) {
            addCriterion("buyer_open_id not in", values, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdBetween(String value1, String value2) {
            addCriterion("buyer_open_id between", value1, value2, "buyerOpenId");
            return (Criteria) this;
        }

        public Criteria andBuyerOpenIdNotBetween(String value1, String value2) {
            addCriterion("buyer_open_id not between", value1, value2, "buyerOpenId");
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

        public Criteria andPurchaseStatusIsNull() {
            addCriterion("purchase_status is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusIsNotNull() {
            addCriterion("purchase_status is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusEqualTo(Byte value) {
            addCriterion("purchase_status =", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusNotEqualTo(Byte value) {
            addCriterion("purchase_status <>", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusGreaterThan(Byte value) {
            addCriterion("purchase_status >", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("purchase_status >=", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusLessThan(Byte value) {
            addCriterion("purchase_status <", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusLessThanOrEqualTo(Byte value) {
            addCriterion("purchase_status <=", value, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusIn(List<Byte> values) {
            addCriterion("purchase_status in", values, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusNotIn(List<Byte> values) {
            addCriterion("purchase_status not in", values, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusBetween(Byte value1, Byte value2) {
            addCriterion("purchase_status between", value1, value2, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andPurchaseStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("purchase_status not between", value1, value2, "purchaseStatus");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andFindAddressIsNull() {
            addCriterion("find_address is null");
            return (Criteria) this;
        }

        public Criteria andFindAddressIsNotNull() {
            addCriterion("find_address is not null");
            return (Criteria) this;
        }

        public Criteria andFindAddressEqualTo(String value) {
            addCriterion("find_address =", value, "findAddress");
            return (Criteria) this;
        }

        public Criteria andFindAddressNotEqualTo(String value) {
            addCriterion("find_address <>", value, "findAddress");
            return (Criteria) this;
        }

        public Criteria andFindAddressGreaterThan(String value) {
            addCriterion("find_address >", value, "findAddress");
            return (Criteria) this;
        }

        public Criteria andFindAddressGreaterThanOrEqualTo(String value) {
            addCriterion("find_address >=", value, "findAddress");
            return (Criteria) this;
        }

        public Criteria andFindAddressLessThan(String value) {
            addCriterion("find_address <", value, "findAddress");
            return (Criteria) this;
        }

        public Criteria andFindAddressLessThanOrEqualTo(String value) {
            addCriterion("find_address <=", value, "findAddress");
            return (Criteria) this;
        }

        public Criteria andFindAddressLike(String value) {
            addCriterion("find_address like", value, "findAddress");
            return (Criteria) this;
        }

        public Criteria andFindAddressNotLike(String value) {
            addCriterion("find_address not like", value, "findAddress");
            return (Criteria) this;
        }

        public Criteria andFindAddressIn(List<String> values) {
            addCriterion("find_address in", values, "findAddress");
            return (Criteria) this;
        }

        public Criteria andFindAddressNotIn(List<String> values) {
            addCriterion("find_address not in", values, "findAddress");
            return (Criteria) this;
        }

        public Criteria andFindAddressBetween(String value1, String value2) {
            addCriterion("find_address between", value1, value2, "findAddress");
            return (Criteria) this;
        }

        public Criteria andFindAddressNotBetween(String value1, String value2) {
            addCriterion("find_address not between", value1, value2, "findAddress");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonIsNull() {
            addCriterion("refuse_reason is null");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonIsNotNull() {
            addCriterion("refuse_reason is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonEqualTo(String value) {
            addCriterion("refuse_reason =", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonNotEqualTo(String value) {
            addCriterion("refuse_reason <>", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonGreaterThan(String value) {
            addCriterion("refuse_reason >", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonGreaterThanOrEqualTo(String value) {
            addCriterion("refuse_reason >=", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonLessThan(String value) {
            addCriterion("refuse_reason <", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonLessThanOrEqualTo(String value) {
            addCriterion("refuse_reason <=", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonLike(String value) {
            addCriterion("refuse_reason like", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonNotLike(String value) {
            addCriterion("refuse_reason not like", value, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonIn(List<String> values) {
            addCriterion("refuse_reason in", values, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonNotIn(List<String> values) {
            addCriterion("refuse_reason not in", values, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonBetween(String value1, String value2) {
            addCriterion("refuse_reason between", value1, value2, "refuseReason");
            return (Criteria) this;
        }

        public Criteria andRefuseReasonNotBetween(String value1, String value2) {
            addCriterion("refuse_reason not between", value1, value2, "refuseReason");
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