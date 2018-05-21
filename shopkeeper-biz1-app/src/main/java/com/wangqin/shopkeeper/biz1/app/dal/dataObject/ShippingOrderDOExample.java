package com.wangqin.shopkeeper.biz1.app.dal.dataObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShippingOrderDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShippingOrderDOExample() {
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

        public Criteria andLogisticNoIsNull() {
            addCriterion("logistic_no is null");
            return (Criteria) this;
        }

        public Criteria andLogisticNoIsNotNull() {
            addCriterion("logistic_no is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticNoEqualTo(String value) {
            addCriterion("logistic_no =", value, "logisticNo");
            return (Criteria) this;
        }

        public Criteria andLogisticNoNotEqualTo(String value) {
            addCriterion("logistic_no <>", value, "logisticNo");
            return (Criteria) this;
        }

        public Criteria andLogisticNoGreaterThan(String value) {
            addCriterion("logistic_no >", value, "logisticNo");
            return (Criteria) this;
        }

        public Criteria andLogisticNoGreaterThanOrEqualTo(String value) {
            addCriterion("logistic_no >=", value, "logisticNo");
            return (Criteria) this;
        }

        public Criteria andLogisticNoLessThan(String value) {
            addCriterion("logistic_no <", value, "logisticNo");
            return (Criteria) this;
        }

        public Criteria andLogisticNoLessThanOrEqualTo(String value) {
            addCriterion("logistic_no <=", value, "logisticNo");
            return (Criteria) this;
        }

        public Criteria andLogisticNoLike(String value) {
            addCriterion("logistic_no like", value, "logisticNo");
            return (Criteria) this;
        }

        public Criteria andLogisticNoNotLike(String value) {
            addCriterion("logistic_no not like", value, "logisticNo");
            return (Criteria) this;
        }

        public Criteria andLogisticNoIn(List<String> values) {
            addCriterion("logistic_no in", values, "logisticNo");
            return (Criteria) this;
        }

        public Criteria andLogisticNoNotIn(List<String> values) {
            addCriterion("logistic_no not in", values, "logisticNo");
            return (Criteria) this;
        }

        public Criteria andLogisticNoBetween(String value1, String value2) {
            addCriterion("logistic_no between", value1, value2, "logisticNo");
            return (Criteria) this;
        }

        public Criteria andLogisticNoNotBetween(String value1, String value2) {
            addCriterion("logistic_no not between", value1, value2, "logisticNo");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyIsNull() {
            addCriterion("logistic_company is null");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyIsNotNull() {
            addCriterion("logistic_company is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyEqualTo(String value) {
            addCriterion("logistic_company =", value, "logisticCompany");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyNotEqualTo(String value) {
            addCriterion("logistic_company <>", value, "logisticCompany");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyGreaterThan(String value) {
            addCriterion("logistic_company >", value, "logisticCompany");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("logistic_company >=", value, "logisticCompany");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyLessThan(String value) {
            addCriterion("logistic_company <", value, "logisticCompany");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyLessThanOrEqualTo(String value) {
            addCriterion("logistic_company <=", value, "logisticCompany");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyLike(String value) {
            addCriterion("logistic_company like", value, "logisticCompany");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyNotLike(String value) {
            addCriterion("logistic_company not like", value, "logisticCompany");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyIn(List<String> values) {
            addCriterion("logistic_company in", values, "logisticCompany");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyNotIn(List<String> values) {
            addCriterion("logistic_company not in", values, "logisticCompany");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyBetween(String value1, String value2) {
            addCriterion("logistic_company between", value1, value2, "logisticCompany");
            return (Criteria) this;
        }

        public Criteria andLogisticCompanyNotBetween(String value1, String value2) {
            addCriterion("logistic_company not between", value1, value2, "logisticCompany");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andTransferStatusIsNull() {
            addCriterion("transfer_status is null");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIsNotNull() {
            addCriterion("transfer_status is not null");
            return (Criteria) this;
        }

        public Criteria andTransferStatusEqualTo(Byte value) {
            addCriterion("transfer_status =", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotEqualTo(Byte value) {
            addCriterion("transfer_status <>", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusGreaterThan(Byte value) {
            addCriterion("transfer_status >", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("transfer_status >=", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLessThan(Byte value) {
            addCriterion("transfer_status <", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusLessThanOrEqualTo(Byte value) {
            addCriterion("transfer_status <=", value, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusIn(List<Byte> values) {
            addCriterion("transfer_status in", values, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotIn(List<Byte> values) {
            addCriterion("transfer_status not in", values, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusBetween(Byte value1, Byte value2) {
            addCriterion("transfer_status between", value1, value2, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andTransferStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("transfer_status not between", value1, value2, "transferStatus");
            return (Criteria) this;
        }

        public Criteria andSyncSendStatusIsNull() {
            addCriterion("sync_send_status is null");
            return (Criteria) this;
        }

        public Criteria andSyncSendStatusIsNotNull() {
            addCriterion("sync_send_status is not null");
            return (Criteria) this;
        }

        public Criteria andSyncSendStatusEqualTo(Byte value) {
            addCriterion("sync_send_status =", value, "syncSendStatus");
            return (Criteria) this;
        }

        public Criteria andSyncSendStatusNotEqualTo(Byte value) {
            addCriterion("sync_send_status <>", value, "syncSendStatus");
            return (Criteria) this;
        }

        public Criteria andSyncSendStatusGreaterThan(Byte value) {
            addCriterion("sync_send_status >", value, "syncSendStatus");
            return (Criteria) this;
        }

        public Criteria andSyncSendStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("sync_send_status >=", value, "syncSendStatus");
            return (Criteria) this;
        }

        public Criteria andSyncSendStatusLessThan(Byte value) {
            addCriterion("sync_send_status <", value, "syncSendStatus");
            return (Criteria) this;
        }

        public Criteria andSyncSendStatusLessThanOrEqualTo(Byte value) {
            addCriterion("sync_send_status <=", value, "syncSendStatus");
            return (Criteria) this;
        }

        public Criteria andSyncSendStatusIn(List<Byte> values) {
            addCriterion("sync_send_status in", values, "syncSendStatus");
            return (Criteria) this;
        }

        public Criteria andSyncSendStatusNotIn(List<Byte> values) {
            addCriterion("sync_send_status not in", values, "syncSendStatus");
            return (Criteria) this;
        }

        public Criteria andSyncSendStatusBetween(Byte value1, Byte value2) {
            addCriterion("sync_send_status between", value1, value2, "syncSendStatus");
            return (Criteria) this;
        }

        public Criteria andSyncSendStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("sync_send_status not between", value1, value2, "syncSendStatus");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusIsNull() {
            addCriterion("tpl_pkg_status is null");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusIsNotNull() {
            addCriterion("tpl_pkg_status is not null");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusEqualTo(String value) {
            addCriterion("tpl_pkg_status =", value, "tplPkgStatus");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusNotEqualTo(String value) {
            addCriterion("tpl_pkg_status <>", value, "tplPkgStatus");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusGreaterThan(String value) {
            addCriterion("tpl_pkg_status >", value, "tplPkgStatus");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusGreaterThanOrEqualTo(String value) {
            addCriterion("tpl_pkg_status >=", value, "tplPkgStatus");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusLessThan(String value) {
            addCriterion("tpl_pkg_status <", value, "tplPkgStatus");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusLessThanOrEqualTo(String value) {
            addCriterion("tpl_pkg_status <=", value, "tplPkgStatus");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusLike(String value) {
            addCriterion("tpl_pkg_status like", value, "tplPkgStatus");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusNotLike(String value) {
            addCriterion("tpl_pkg_status not like", value, "tplPkgStatus");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusIn(List<String> values) {
            addCriterion("tpl_pkg_status in", values, "tplPkgStatus");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusNotIn(List<String> values) {
            addCriterion("tpl_pkg_status not in", values, "tplPkgStatus");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusBetween(String value1, String value2) {
            addCriterion("tpl_pkg_status between", value1, value2, "tplPkgStatus");
            return (Criteria) this;
        }

        public Criteria andTplPkgStatusNotBetween(String value1, String value2) {
            addCriterion("tpl_pkg_status not between", value1, value2, "tplPkgStatus");
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

        public Criteria andSkuWeightIsNull() {
            addCriterion("sku_weight is null");
            return (Criteria) this;
        }

        public Criteria andSkuWeightIsNotNull() {
            addCriterion("sku_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSkuWeightEqualTo(Double value) {
            addCriterion("sku_weight =", value, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightNotEqualTo(Double value) {
            addCriterion("sku_weight <>", value, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightGreaterThan(Double value) {
            addCriterion("sku_weight >", value, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightGreaterThanOrEqualTo(Double value) {
            addCriterion("sku_weight >=", value, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightLessThan(Double value) {
            addCriterion("sku_weight <", value, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightLessThanOrEqualTo(Double value) {
            addCriterion("sku_weight <=", value, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightIn(List<Double> values) {
            addCriterion("sku_weight in", values, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightNotIn(List<Double> values) {
            addCriterion("sku_weight not in", values, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightBetween(Double value1, Double value2) {
            addCriterion("sku_weight between", value1, value2, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightNotBetween(Double value1, Double value2) {
            addCriterion("sku_weight not between", value1, value2, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andMallOrdersIsNull() {
            addCriterion("mall_orders is null");
            return (Criteria) this;
        }

        public Criteria andMallOrdersIsNotNull() {
            addCriterion("mall_orders is not null");
            return (Criteria) this;
        }

        public Criteria andMallOrdersEqualTo(String value) {
            addCriterion("mall_orders =", value, "mallOrders");
            return (Criteria) this;
        }

        public Criteria andMallOrdersNotEqualTo(String value) {
            addCriterion("mall_orders <>", value, "mallOrders");
            return (Criteria) this;
        }

        public Criteria andMallOrdersGreaterThan(String value) {
            addCriterion("mall_orders >", value, "mallOrders");
            return (Criteria) this;
        }

        public Criteria andMallOrdersGreaterThanOrEqualTo(String value) {
            addCriterion("mall_orders >=", value, "mallOrders");
            return (Criteria) this;
        }

        public Criteria andMallOrdersLessThan(String value) {
            addCriterion("mall_orders <", value, "mallOrders");
            return (Criteria) this;
        }

        public Criteria andMallOrdersLessThanOrEqualTo(String value) {
            addCriterion("mall_orders <=", value, "mallOrders");
            return (Criteria) this;
        }

        public Criteria andMallOrdersLike(String value) {
            addCriterion("mall_orders like", value, "mallOrders");
            return (Criteria) this;
        }

        public Criteria andMallOrdersNotLike(String value) {
            addCriterion("mall_orders not like", value, "mallOrders");
            return (Criteria) this;
        }

        public Criteria andMallOrdersIn(List<String> values) {
            addCriterion("mall_orders in", values, "mallOrders");
            return (Criteria) this;
        }

        public Criteria andMallOrdersNotIn(List<String> values) {
            addCriterion("mall_orders not in", values, "mallOrders");
            return (Criteria) this;
        }

        public Criteria andMallOrdersBetween(String value1, String value2) {
            addCriterion("mall_orders between", value1, value2, "mallOrders");
            return (Criteria) this;
        }

        public Criteria andMallOrdersNotBetween(String value1, String value2) {
            addCriterion("mall_orders not between", value1, value2, "mallOrders");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterIsNull() {
            addCriterion("shipping_printer is null");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterIsNotNull() {
            addCriterion("shipping_printer is not null");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterEqualTo(String value) {
            addCriterion("shipping_printer =", value, "shippingPrinter");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterNotEqualTo(String value) {
            addCriterion("shipping_printer <>", value, "shippingPrinter");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterGreaterThan(String value) {
            addCriterion("shipping_printer >", value, "shippingPrinter");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterGreaterThanOrEqualTo(String value) {
            addCriterion("shipping_printer >=", value, "shippingPrinter");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterLessThan(String value) {
            addCriterion("shipping_printer <", value, "shippingPrinter");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterLessThanOrEqualTo(String value) {
            addCriterion("shipping_printer <=", value, "shippingPrinter");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterLike(String value) {
            addCriterion("shipping_printer like", value, "shippingPrinter");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterNotLike(String value) {
            addCriterion("shipping_printer not like", value, "shippingPrinter");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterIn(List<String> values) {
            addCriterion("shipping_printer in", values, "shippingPrinter");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterNotIn(List<String> values) {
            addCriterion("shipping_printer not in", values, "shippingPrinter");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterBetween(String value1, String value2) {
            addCriterion("shipping_printer between", value1, value2, "shippingPrinter");
            return (Criteria) this;
        }

        public Criteria andShippingPrinterNotBetween(String value1, String value2) {
            addCriterion("shipping_printer not between", value1, value2, "shippingPrinter");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackIsNull() {
            addCriterion("gls_return_back is null");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackIsNotNull() {
            addCriterion("gls_return_back is not null");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackEqualTo(String value) {
            addCriterion("gls_return_back =", value, "glsReturnBack");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackNotEqualTo(String value) {
            addCriterion("gls_return_back <>", value, "glsReturnBack");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackGreaterThan(String value) {
            addCriterion("gls_return_back >", value, "glsReturnBack");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackGreaterThanOrEqualTo(String value) {
            addCriterion("gls_return_back >=", value, "glsReturnBack");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackLessThan(String value) {
            addCriterion("gls_return_back <", value, "glsReturnBack");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackLessThanOrEqualTo(String value) {
            addCriterion("gls_return_back <=", value, "glsReturnBack");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackLike(String value) {
            addCriterion("gls_return_back like", value, "glsReturnBack");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackNotLike(String value) {
            addCriterion("gls_return_back not like", value, "glsReturnBack");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackIn(List<String> values) {
            addCriterion("gls_return_back in", values, "glsReturnBack");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackNotIn(List<String> values) {
            addCriterion("gls_return_back not in", values, "glsReturnBack");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackBetween(String value1, String value2) {
            addCriterion("gls_return_back between", value1, value2, "glsReturnBack");
            return (Criteria) this;
        }

        public Criteria andGlsReturnBackNotBetween(String value1, String value2) {
            addCriterion("gls_return_back not between", value1, value2, "glsReturnBack");
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

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
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

        public Criteria andIdCardBackIsNull() {
            addCriterion("id_card_back is null");
            return (Criteria) this;
        }

        public Criteria andIdCardBackIsNotNull() {
            addCriterion("id_card_back is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardBackEqualTo(String value) {
            addCriterion("id_card_back =", value, "idCardBack");
            return (Criteria) this;
        }

        public Criteria andIdCardBackNotEqualTo(String value) {
            addCriterion("id_card_back <>", value, "idCardBack");
            return (Criteria) this;
        }

        public Criteria andIdCardBackGreaterThan(String value) {
            addCriterion("id_card_back >", value, "idCardBack");
            return (Criteria) this;
        }

        public Criteria andIdCardBackGreaterThanOrEqualTo(String value) {
            addCriterion("id_card_back >=", value, "idCardBack");
            return (Criteria) this;
        }

        public Criteria andIdCardBackLessThan(String value) {
            addCriterion("id_card_back <", value, "idCardBack");
            return (Criteria) this;
        }

        public Criteria andIdCardBackLessThanOrEqualTo(String value) {
            addCriterion("id_card_back <=", value, "idCardBack");
            return (Criteria) this;
        }

        public Criteria andIdCardBackLike(String value) {
            addCriterion("id_card_back like", value, "idCardBack");
            return (Criteria) this;
        }

        public Criteria andIdCardBackNotLike(String value) {
            addCriterion("id_card_back not like", value, "idCardBack");
            return (Criteria) this;
        }

        public Criteria andIdCardBackIn(List<String> values) {
            addCriterion("id_card_back in", values, "idCardBack");
            return (Criteria) this;
        }

        public Criteria andIdCardBackNotIn(List<String> values) {
            addCriterion("id_card_back not in", values, "idCardBack");
            return (Criteria) this;
        }

        public Criteria andIdCardBackBetween(String value1, String value2) {
            addCriterion("id_card_back between", value1, value2, "idCardBack");
            return (Criteria) this;
        }

        public Criteria andIdCardBackNotBetween(String value1, String value2) {
            addCriterion("id_card_back not between", value1, value2, "idCardBack");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontIsNull() {
            addCriterion("id_card_front is null");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontIsNotNull() {
            addCriterion("id_card_front is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontEqualTo(String value) {
            addCriterion("id_card_front =", value, "idCardFront");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontNotEqualTo(String value) {
            addCriterion("id_card_front <>", value, "idCardFront");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontGreaterThan(String value) {
            addCriterion("id_card_front >", value, "idCardFront");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontGreaterThanOrEqualTo(String value) {
            addCriterion("id_card_front >=", value, "idCardFront");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontLessThan(String value) {
            addCriterion("id_card_front <", value, "idCardFront");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontLessThanOrEqualTo(String value) {
            addCriterion("id_card_front <=", value, "idCardFront");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontLike(String value) {
            addCriterion("id_card_front like", value, "idCardFront");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontNotLike(String value) {
            addCriterion("id_card_front not like", value, "idCardFront");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontIn(List<String> values) {
            addCriterion("id_card_front in", values, "idCardFront");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontNotIn(List<String> values) {
            addCriterion("id_card_front not in", values, "idCardFront");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontBetween(String value1, String value2) {
            addCriterion("id_card_front between", value1, value2, "idCardFront");
            return (Criteria) this;
        }

        public Criteria andIdCardFrontNotBetween(String value1, String value2) {
            addCriterion("id_card_front not between", value1, value2, "idCardFront");
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