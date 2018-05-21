package com.wangqin.shopkeeper.biz1.app.dal.dataObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InventoryDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InventoryDOExample() {
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

        public Criteria andVirtualInvIsNull() {
            addCriterion("virtual_inv is null");
            return (Criteria) this;
        }

        public Criteria andVirtualInvIsNotNull() {
            addCriterion("virtual_inv is not null");
            return (Criteria) this;
        }

        public Criteria andVirtualInvEqualTo(Long value) {
            addCriterion("virtual_inv =", value, "virtualInv");
            return (Criteria) this;
        }

        public Criteria andVirtualInvNotEqualTo(Long value) {
            addCriterion("virtual_inv <>", value, "virtualInv");
            return (Criteria) this;
        }

        public Criteria andVirtualInvGreaterThan(Long value) {
            addCriterion("virtual_inv >", value, "virtualInv");
            return (Criteria) this;
        }

        public Criteria andVirtualInvGreaterThanOrEqualTo(Long value) {
            addCriterion("virtual_inv >=", value, "virtualInv");
            return (Criteria) this;
        }

        public Criteria andVirtualInvLessThan(Long value) {
            addCriterion("virtual_inv <", value, "virtualInv");
            return (Criteria) this;
        }

        public Criteria andVirtualInvLessThanOrEqualTo(Long value) {
            addCriterion("virtual_inv <=", value, "virtualInv");
            return (Criteria) this;
        }

        public Criteria andVirtualInvIn(List<Long> values) {
            addCriterion("virtual_inv in", values, "virtualInv");
            return (Criteria) this;
        }

        public Criteria andVirtualInvNotIn(List<Long> values) {
            addCriterion("virtual_inv not in", values, "virtualInv");
            return (Criteria) this;
        }

        public Criteria andVirtualInvBetween(Long value1, Long value2) {
            addCriterion("virtual_inv between", value1, value2, "virtualInv");
            return (Criteria) this;
        }

        public Criteria andVirtualInvNotBetween(Long value1, Long value2) {
            addCriterion("virtual_inv not between", value1, value2, "virtualInv");
            return (Criteria) this;
        }

        public Criteria andLockedVirtualInvIsNull() {
            addCriterion("locked_virtual_inv is null");
            return (Criteria) this;
        }

        public Criteria andLockedVirtualInvIsNotNull() {
            addCriterion("locked_virtual_inv is not null");
            return (Criteria) this;
        }

        public Criteria andLockedVirtualInvEqualTo(Long value) {
            addCriterion("locked_virtual_inv =", value, "lockedVirtualInv");
            return (Criteria) this;
        }

        public Criteria andLockedVirtualInvNotEqualTo(Long value) {
            addCriterion("locked_virtual_inv <>", value, "lockedVirtualInv");
            return (Criteria) this;
        }

        public Criteria andLockedVirtualInvGreaterThan(Long value) {
            addCriterion("locked_virtual_inv >", value, "lockedVirtualInv");
            return (Criteria) this;
        }

        public Criteria andLockedVirtualInvGreaterThanOrEqualTo(Long value) {
            addCriterion("locked_virtual_inv >=", value, "lockedVirtualInv");
            return (Criteria) this;
        }

        public Criteria andLockedVirtualInvLessThan(Long value) {
            addCriterion("locked_virtual_inv <", value, "lockedVirtualInv");
            return (Criteria) this;
        }

        public Criteria andLockedVirtualInvLessThanOrEqualTo(Long value) {
            addCriterion("locked_virtual_inv <=", value, "lockedVirtualInv");
            return (Criteria) this;
        }

        public Criteria andLockedVirtualInvIn(List<Long> values) {
            addCriterion("locked_virtual_inv in", values, "lockedVirtualInv");
            return (Criteria) this;
        }

        public Criteria andLockedVirtualInvNotIn(List<Long> values) {
            addCriterion("locked_virtual_inv not in", values, "lockedVirtualInv");
            return (Criteria) this;
        }

        public Criteria andLockedVirtualInvBetween(Long value1, Long value2) {
            addCriterion("locked_virtual_inv between", value1, value2, "lockedVirtualInv");
            return (Criteria) this;
        }

        public Criteria andLockedVirtualInvNotBetween(Long value1, Long value2) {
            addCriterion("locked_virtual_inv not between", value1, value2, "lockedVirtualInv");
            return (Criteria) this;
        }

        public Criteria andInvIsNull() {
            addCriterion("inv is null");
            return (Criteria) this;
        }

        public Criteria andInvIsNotNull() {
            addCriterion("inv is not null");
            return (Criteria) this;
        }

        public Criteria andInvEqualTo(Long value) {
            addCriterion("inv =", value, "inv");
            return (Criteria) this;
        }

        public Criteria andInvNotEqualTo(Long value) {
            addCriterion("inv <>", value, "inv");
            return (Criteria) this;
        }

        public Criteria andInvGreaterThan(Long value) {
            addCriterion("inv >", value, "inv");
            return (Criteria) this;
        }

        public Criteria andInvGreaterThanOrEqualTo(Long value) {
            addCriterion("inv >=", value, "inv");
            return (Criteria) this;
        }

        public Criteria andInvLessThan(Long value) {
            addCriterion("inv <", value, "inv");
            return (Criteria) this;
        }

        public Criteria andInvLessThanOrEqualTo(Long value) {
            addCriterion("inv <=", value, "inv");
            return (Criteria) this;
        }

        public Criteria andInvIn(List<Long> values) {
            addCriterion("inv in", values, "inv");
            return (Criteria) this;
        }

        public Criteria andInvNotIn(List<Long> values) {
            addCriterion("inv not in", values, "inv");
            return (Criteria) this;
        }

        public Criteria andInvBetween(Long value1, Long value2) {
            addCriterion("inv between", value1, value2, "inv");
            return (Criteria) this;
        }

        public Criteria andInvNotBetween(Long value1, Long value2) {
            addCriterion("inv not between", value1, value2, "inv");
            return (Criteria) this;
        }

        public Criteria andLockedInvIsNull() {
            addCriterion("locked_inv is null");
            return (Criteria) this;
        }

        public Criteria andLockedInvIsNotNull() {
            addCriterion("locked_inv is not null");
            return (Criteria) this;
        }

        public Criteria andLockedInvEqualTo(Long value) {
            addCriterion("locked_inv =", value, "lockedInv");
            return (Criteria) this;
        }

        public Criteria andLockedInvNotEqualTo(Long value) {
            addCriterion("locked_inv <>", value, "lockedInv");
            return (Criteria) this;
        }

        public Criteria andLockedInvGreaterThan(Long value) {
            addCriterion("locked_inv >", value, "lockedInv");
            return (Criteria) this;
        }

        public Criteria andLockedInvGreaterThanOrEqualTo(Long value) {
            addCriterion("locked_inv >=", value, "lockedInv");
            return (Criteria) this;
        }

        public Criteria andLockedInvLessThan(Long value) {
            addCriterion("locked_inv <", value, "lockedInv");
            return (Criteria) this;
        }

        public Criteria andLockedInvLessThanOrEqualTo(Long value) {
            addCriterion("locked_inv <=", value, "lockedInv");
            return (Criteria) this;
        }

        public Criteria andLockedInvIn(List<Long> values) {
            addCriterion("locked_inv in", values, "lockedInv");
            return (Criteria) this;
        }

        public Criteria andLockedInvNotIn(List<Long> values) {
            addCriterion("locked_inv not in", values, "lockedInv");
            return (Criteria) this;
        }

        public Criteria andLockedInvBetween(Long value1, Long value2) {
            addCriterion("locked_inv between", value1, value2, "lockedInv");
            return (Criteria) this;
        }

        public Criteria andLockedInvNotBetween(Long value1, Long value2) {
            addCriterion("locked_inv not between", value1, value2, "lockedInv");
            return (Criteria) this;
        }

        public Criteria andTransInvIsNull() {
            addCriterion("trans_inv is null");
            return (Criteria) this;
        }

        public Criteria andTransInvIsNotNull() {
            addCriterion("trans_inv is not null");
            return (Criteria) this;
        }

        public Criteria andTransInvEqualTo(Long value) {
            addCriterion("trans_inv =", value, "transInv");
            return (Criteria) this;
        }

        public Criteria andTransInvNotEqualTo(Long value) {
            addCriterion("trans_inv <>", value, "transInv");
            return (Criteria) this;
        }

        public Criteria andTransInvGreaterThan(Long value) {
            addCriterion("trans_inv >", value, "transInv");
            return (Criteria) this;
        }

        public Criteria andTransInvGreaterThanOrEqualTo(Long value) {
            addCriterion("trans_inv >=", value, "transInv");
            return (Criteria) this;
        }

        public Criteria andTransInvLessThan(Long value) {
            addCriterion("trans_inv <", value, "transInv");
            return (Criteria) this;
        }

        public Criteria andTransInvLessThanOrEqualTo(Long value) {
            addCriterion("trans_inv <=", value, "transInv");
            return (Criteria) this;
        }

        public Criteria andTransInvIn(List<Long> values) {
            addCriterion("trans_inv in", values, "transInv");
            return (Criteria) this;
        }

        public Criteria andTransInvNotIn(List<Long> values) {
            addCriterion("trans_inv not in", values, "transInv");
            return (Criteria) this;
        }

        public Criteria andTransInvBetween(Long value1, Long value2) {
            addCriterion("trans_inv between", value1, value2, "transInv");
            return (Criteria) this;
        }

        public Criteria andTransInvNotBetween(Long value1, Long value2) {
            addCriterion("trans_inv not between", value1, value2, "transInv");
            return (Criteria) this;
        }

        public Criteria andLockedTransInvIsNull() {
            addCriterion("locked_trans_inv is null");
            return (Criteria) this;
        }

        public Criteria andLockedTransInvIsNotNull() {
            addCriterion("locked_trans_inv is not null");
            return (Criteria) this;
        }

        public Criteria andLockedTransInvEqualTo(Long value) {
            addCriterion("locked_trans_inv =", value, "lockedTransInv");
            return (Criteria) this;
        }

        public Criteria andLockedTransInvNotEqualTo(Long value) {
            addCriterion("locked_trans_inv <>", value, "lockedTransInv");
            return (Criteria) this;
        }

        public Criteria andLockedTransInvGreaterThan(Long value) {
            addCriterion("locked_trans_inv >", value, "lockedTransInv");
            return (Criteria) this;
        }

        public Criteria andLockedTransInvGreaterThanOrEqualTo(Long value) {
            addCriterion("locked_trans_inv >=", value, "lockedTransInv");
            return (Criteria) this;
        }

        public Criteria andLockedTransInvLessThan(Long value) {
            addCriterion("locked_trans_inv <", value, "lockedTransInv");
            return (Criteria) this;
        }

        public Criteria andLockedTransInvLessThanOrEqualTo(Long value) {
            addCriterion("locked_trans_inv <=", value, "lockedTransInv");
            return (Criteria) this;
        }

        public Criteria andLockedTransInvIn(List<Long> values) {
            addCriterion("locked_trans_inv in", values, "lockedTransInv");
            return (Criteria) this;
        }

        public Criteria andLockedTransInvNotIn(List<Long> values) {
            addCriterion("locked_trans_inv not in", values, "lockedTransInv");
            return (Criteria) this;
        }

        public Criteria andLockedTransInvBetween(Long value1, Long value2) {
            addCriterion("locked_trans_inv between", value1, value2, "lockedTransInv");
            return (Criteria) this;
        }

        public Criteria andLockedTransInvNotBetween(Long value1, Long value2) {
            addCriterion("locked_trans_inv not between", value1, value2, "lockedTransInv");
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