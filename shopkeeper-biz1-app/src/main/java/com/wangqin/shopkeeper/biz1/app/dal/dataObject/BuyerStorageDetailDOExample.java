package com.wangqin.shopkeeper.biz1.app.dal.dataObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuyerStorageDetailDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BuyerStorageDetailDOExample() {
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

        public Criteria andStorageNoIsNull() {
            addCriterion("storage_no is null");
            return (Criteria) this;
        }

        public Criteria andStorageNoIsNotNull() {
            addCriterion("storage_no is not null");
            return (Criteria) this;
        }

        public Criteria andStorageNoEqualTo(String value) {
            addCriterion("storage_no =", value, "storageNo");
            return (Criteria) this;
        }

        public Criteria andStorageNoNotEqualTo(String value) {
            addCriterion("storage_no <>", value, "storageNo");
            return (Criteria) this;
        }

        public Criteria andStorageNoGreaterThan(String value) {
            addCriterion("storage_no >", value, "storageNo");
            return (Criteria) this;
        }

        public Criteria andStorageNoGreaterThanOrEqualTo(String value) {
            addCriterion("storage_no >=", value, "storageNo");
            return (Criteria) this;
        }

        public Criteria andStorageNoLessThan(String value) {
            addCriterion("storage_no <", value, "storageNo");
            return (Criteria) this;
        }

        public Criteria andStorageNoLessThanOrEqualTo(String value) {
            addCriterion("storage_no <=", value, "storageNo");
            return (Criteria) this;
        }

        public Criteria andStorageNoLike(String value) {
            addCriterion("storage_no like", value, "storageNo");
            return (Criteria) this;
        }

        public Criteria andStorageNoNotLike(String value) {
            addCriterion("storage_no not like", value, "storageNo");
            return (Criteria) this;
        }

        public Criteria andStorageNoIn(List<String> values) {
            addCriterion("storage_no in", values, "storageNo");
            return (Criteria) this;
        }

        public Criteria andStorageNoNotIn(List<String> values) {
            addCriterion("storage_no not in", values, "storageNo");
            return (Criteria) this;
        }

        public Criteria andStorageNoBetween(String value1, String value2) {
            addCriterion("storage_no between", value1, value2, "storageNo");
            return (Criteria) this;
        }

        public Criteria andStorageNoNotBetween(String value1, String value2) {
            addCriterion("storage_no not between", value1, value2, "storageNo");
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

        public Criteria andWarehouseNameIsNull() {
            addCriterion("warehouse_name is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameIsNotNull() {
            addCriterion("warehouse_name is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameEqualTo(String value) {
            addCriterion("warehouse_name =", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameNotEqualTo(String value) {
            addCriterion("warehouse_name <>", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameGreaterThan(String value) {
            addCriterion("warehouse_name >", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameGreaterThanOrEqualTo(String value) {
            addCriterion("warehouse_name >=", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameLessThan(String value) {
            addCriterion("warehouse_name <", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameLessThanOrEqualTo(String value) {
            addCriterion("warehouse_name <=", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameLike(String value) {
            addCriterion("warehouse_name like", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameNotLike(String value) {
            addCriterion("warehouse_name not like", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameIn(List<String> values) {
            addCriterion("warehouse_name in", values, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameNotIn(List<String> values) {
            addCriterion("warehouse_name not in", values, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameBetween(String value1, String value2) {
            addCriterion("warehouse_name between", value1, value2, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameNotBetween(String value1, String value2) {
            addCriterion("warehouse_name not between", value1, value2, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andShelfNoIsNull() {
            addCriterion("shelf_no is null");
            return (Criteria) this;
        }

        public Criteria andShelfNoIsNotNull() {
            addCriterion("shelf_no is not null");
            return (Criteria) this;
        }

        public Criteria andShelfNoEqualTo(String value) {
            addCriterion("shelf_no =", value, "shelfNo");
            return (Criteria) this;
        }

        public Criteria andShelfNoNotEqualTo(String value) {
            addCriterion("shelf_no <>", value, "shelfNo");
            return (Criteria) this;
        }

        public Criteria andShelfNoGreaterThan(String value) {
            addCriterion("shelf_no >", value, "shelfNo");
            return (Criteria) this;
        }

        public Criteria andShelfNoGreaterThanOrEqualTo(String value) {
            addCriterion("shelf_no >=", value, "shelfNo");
            return (Criteria) this;
        }

        public Criteria andShelfNoLessThan(String value) {
            addCriterion("shelf_no <", value, "shelfNo");
            return (Criteria) this;
        }

        public Criteria andShelfNoLessThanOrEqualTo(String value) {
            addCriterion("shelf_no <=", value, "shelfNo");
            return (Criteria) this;
        }

        public Criteria andShelfNoLike(String value) {
            addCriterion("shelf_no like", value, "shelfNo");
            return (Criteria) this;
        }

        public Criteria andShelfNoNotLike(String value) {
            addCriterion("shelf_no not like", value, "shelfNo");
            return (Criteria) this;
        }

        public Criteria andShelfNoIn(List<String> values) {
            addCriterion("shelf_no in", values, "shelfNo");
            return (Criteria) this;
        }

        public Criteria andShelfNoNotIn(List<String> values) {
            addCriterion("shelf_no not in", values, "shelfNo");
            return (Criteria) this;
        }

        public Criteria andShelfNoBetween(String value1, String value2) {
            addCriterion("shelf_no between", value1, value2, "shelfNo");
            return (Criteria) this;
        }

        public Criteria andShelfNoNotBetween(String value1, String value2) {
            addCriterion("shelf_no not between", value1, value2, "shelfNo");
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

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Double value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Double value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Double value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Double value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Double value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Double> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Double> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Double value1, Double value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Double value1, Double value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNull() {
            addCriterion("total_price is null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNotNull() {
            addCriterion("total_price is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceEqualTo(Double value) {
            addCriterion("total_price =", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotEqualTo(Double value) {
            addCriterion("total_price <>", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThan(Double value) {
            addCriterion("total_price >", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("total_price >=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThan(Double value) {
            addCriterion("total_price <", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThanOrEqualTo(Double value) {
            addCriterion("total_price <=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIn(List<Double> values) {
            addCriterion("total_price in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotIn(List<Double> values) {
            addCriterion("total_price not in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBetween(Double value1, Double value2) {
            addCriterion("total_price between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotBetween(Double value1, Double value2) {
            addCriterion("total_price not between", value1, value2, "totalPrice");
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

        public Criteria andTransQuantityIsNull() {
            addCriterion("trans_quantity is null");
            return (Criteria) this;
        }

        public Criteria andTransQuantityIsNotNull() {
            addCriterion("trans_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andTransQuantityEqualTo(Integer value) {
            addCriterion("trans_quantity =", value, "transQuantity");
            return (Criteria) this;
        }

        public Criteria andTransQuantityNotEqualTo(Integer value) {
            addCriterion("trans_quantity <>", value, "transQuantity");
            return (Criteria) this;
        }

        public Criteria andTransQuantityGreaterThan(Integer value) {
            addCriterion("trans_quantity >", value, "transQuantity");
            return (Criteria) this;
        }

        public Criteria andTransQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("trans_quantity >=", value, "transQuantity");
            return (Criteria) this;
        }

        public Criteria andTransQuantityLessThan(Integer value) {
            addCriterion("trans_quantity <", value, "transQuantity");
            return (Criteria) this;
        }

        public Criteria andTransQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("trans_quantity <=", value, "transQuantity");
            return (Criteria) this;
        }

        public Criteria andTransQuantityIn(List<Integer> values) {
            addCriterion("trans_quantity in", values, "transQuantity");
            return (Criteria) this;
        }

        public Criteria andTransQuantityNotIn(List<Integer> values) {
            addCriterion("trans_quantity not in", values, "transQuantity");
            return (Criteria) this;
        }

        public Criteria andTransQuantityBetween(Integer value1, Integer value2) {
            addCriterion("trans_quantity between", value1, value2, "transQuantity");
            return (Criteria) this;
        }

        public Criteria andTransQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("trans_quantity not between", value1, value2, "transQuantity");
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

        public Criteria andPurchaseStorageNoIsNull() {
            addCriterion("purchase_storage_no is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoIsNotNull() {
            addCriterion("purchase_storage_no is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoEqualTo(String value) {
            addCriterion("purchase_storage_no =", value, "purchaseStorageNo");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoNotEqualTo(String value) {
            addCriterion("purchase_storage_no <>", value, "purchaseStorageNo");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoGreaterThan(String value) {
            addCriterion("purchase_storage_no >", value, "purchaseStorageNo");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoGreaterThanOrEqualTo(String value) {
            addCriterion("purchase_storage_no >=", value, "purchaseStorageNo");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoLessThan(String value) {
            addCriterion("purchase_storage_no <", value, "purchaseStorageNo");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoLessThanOrEqualTo(String value) {
            addCriterion("purchase_storage_no <=", value, "purchaseStorageNo");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoLike(String value) {
            addCriterion("purchase_storage_no like", value, "purchaseStorageNo");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoNotLike(String value) {
            addCriterion("purchase_storage_no not like", value, "purchaseStorageNo");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoIn(List<String> values) {
            addCriterion("purchase_storage_no in", values, "purchaseStorageNo");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoNotIn(List<String> values) {
            addCriterion("purchase_storage_no not in", values, "purchaseStorageNo");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoBetween(String value1, String value2) {
            addCriterion("purchase_storage_no between", value1, value2, "purchaseStorageNo");
            return (Criteria) this;
        }

        public Criteria andPurchaseStorageNoNotBetween(String value1, String value2) {
            addCriterion("purchase_storage_no not between", value1, value2, "purchaseStorageNo");
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

        public Criteria andBuyerTaskDetailNoIsNull() {
            addCriterion("buyer_task_detail_no is null");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoIsNotNull() {
            addCriterion("buyer_task_detail_no is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoEqualTo(String value) {
            addCriterion("buyer_task_detail_no =", value, "buyerTaskDetailNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoNotEqualTo(String value) {
            addCriterion("buyer_task_detail_no <>", value, "buyerTaskDetailNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoGreaterThan(String value) {
            addCriterion("buyer_task_detail_no >", value, "buyerTaskDetailNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_task_detail_no >=", value, "buyerTaskDetailNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoLessThan(String value) {
            addCriterion("buyer_task_detail_no <", value, "buyerTaskDetailNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoLessThanOrEqualTo(String value) {
            addCriterion("buyer_task_detail_no <=", value, "buyerTaskDetailNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoLike(String value) {
            addCriterion("buyer_task_detail_no like", value, "buyerTaskDetailNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoNotLike(String value) {
            addCriterion("buyer_task_detail_no not like", value, "buyerTaskDetailNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoIn(List<String> values) {
            addCriterion("buyer_task_detail_no in", values, "buyerTaskDetailNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoNotIn(List<String> values) {
            addCriterion("buyer_task_detail_no not in", values, "buyerTaskDetailNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoBetween(String value1, String value2) {
            addCriterion("buyer_task_detail_no between", value1, value2, "buyerTaskDetailNo");
            return (Criteria) this;
        }

        public Criteria andBuyerTaskDetailNoNotBetween(String value1, String value2) {
            addCriterion("buyer_task_detail_no not between", value1, value2, "buyerTaskDetailNo");
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

        public Criteria andItemCodeEqualTo(Long value) {
            addCriterion("item_code =", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotEqualTo(Long value) {
            addCriterion("item_code <>", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThan(Long value) {
            addCriterion("item_code >", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("item_code >=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThan(Long value) {
            addCriterion("item_code <", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeLessThanOrEqualTo(Long value) {
            addCriterion("item_code <=", value, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeIn(List<Long> values) {
            addCriterion("item_code in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotIn(List<Long> values) {
            addCriterion("item_code not in", values, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeBetween(Long value1, Long value2) {
            addCriterion("item_code between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andItemCodeNotBetween(Long value1, Long value2) {
            addCriterion("item_code not between", value1, value2, "itemCode");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteIsNull() {
            addCriterion("sku_buysite is null");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteIsNotNull() {
            addCriterion("sku_buysite is not null");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteEqualTo(String value) {
            addCriterion("sku_buysite =", value, "skuBuysite");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteNotEqualTo(String value) {
            addCriterion("sku_buysite <>", value, "skuBuysite");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteGreaterThan(String value) {
            addCriterion("sku_buysite >", value, "skuBuysite");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteGreaterThanOrEqualTo(String value) {
            addCriterion("sku_buysite >=", value, "skuBuysite");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteLessThan(String value) {
            addCriterion("sku_buysite <", value, "skuBuysite");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteLessThanOrEqualTo(String value) {
            addCriterion("sku_buysite <=", value, "skuBuysite");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteLike(String value) {
            addCriterion("sku_buysite like", value, "skuBuysite");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteNotLike(String value) {
            addCriterion("sku_buysite not like", value, "skuBuysite");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteIn(List<String> values) {
            addCriterion("sku_buysite in", values, "skuBuysite");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteNotIn(List<String> values) {
            addCriterion("sku_buysite not in", values, "skuBuysite");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteBetween(String value1, String value2) {
            addCriterion("sku_buysite between", value1, value2, "skuBuysite");
            return (Criteria) this;
        }

        public Criteria andSkuBuysiteNotBetween(String value1, String value2) {
            addCriterion("sku_buysite not between", value1, value2, "skuBuysite");
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