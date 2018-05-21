package com.wangqin.shopkeeper.biz1.app.dal.dataObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShippingTrackDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShippingTrackDOExample() {
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

        public Criteria andWaybillNoIsNull() {
            addCriterion("waybill_no is null");
            return (Criteria) this;
        }

        public Criteria andWaybillNoIsNotNull() {
            addCriterion("waybill_no is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillNoEqualTo(String value) {
            addCriterion("waybill_no =", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotEqualTo(String value) {
            addCriterion("waybill_no <>", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoGreaterThan(String value) {
            addCriterion("waybill_no >", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoGreaterThanOrEqualTo(String value) {
            addCriterion("waybill_no >=", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLessThan(String value) {
            addCriterion("waybill_no <", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLessThanOrEqualTo(String value) {
            addCriterion("waybill_no <=", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLike(String value) {
            addCriterion("waybill_no like", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotLike(String value) {
            addCriterion("waybill_no not like", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoIn(List<String> values) {
            addCriterion("waybill_no in", values, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotIn(List<String> values) {
            addCriterion("waybill_no not in", values, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoBetween(String value1, String value2) {
            addCriterion("waybill_no between", value1, value2, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotBetween(String value1, String value2) {
            addCriterion("waybill_no not between", value1, value2, "waybillNo");
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

        public Criteria andLogisticsStatusIsNull() {
            addCriterion("logistics_status is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusIsNotNull() {
            addCriterion("logistics_status is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusEqualTo(Integer value) {
            addCriterion("logistics_status =", value, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusNotEqualTo(Integer value) {
            addCriterion("logistics_status <>", value, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusGreaterThan(Integer value) {
            addCriterion("logistics_status >", value, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("logistics_status >=", value, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusLessThan(Integer value) {
            addCriterion("logistics_status <", value, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusLessThanOrEqualTo(Integer value) {
            addCriterion("logistics_status <=", value, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusIn(List<Integer> values) {
            addCriterion("logistics_status in", values, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusNotIn(List<Integer> values) {
            addCriterion("logistics_status not in", values, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusBetween(Integer value1, Integer value2) {
            addCriterion("logistics_status between", value1, value2, "logisticsStatus");
            return (Criteria) this;
        }

        public Criteria andLogisticsStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("logistics_status not between", value1, value2, "logisticsStatus");
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

        public Criteria andVolumeIsNull() {
            addCriterion("volume is null");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNotNull() {
            addCriterion("volume is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeEqualTo(String value) {
            addCriterion("volume =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(String value) {
            addCriterion("volume <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(String value) {
            addCriterion("volume >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("volume >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(String value) {
            addCriterion("volume <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(String value) {
            addCriterion("volume <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLike(String value) {
            addCriterion("volume like", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotLike(String value) {
            addCriterion("volume not like", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<String> values) {
            addCriterion("volume in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<String> values) {
            addCriterion("volume not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(String value1, String value2) {
            addCriterion("volume between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(String value1, String value2) {
            addCriterion("volume not between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andTotalfeeIsNull() {
            addCriterion("totalfee is null");
            return (Criteria) this;
        }

        public Criteria andTotalfeeIsNotNull() {
            addCriterion("totalfee is not null");
            return (Criteria) this;
        }

        public Criteria andTotalfeeEqualTo(Double value) {
            addCriterion("totalfee =", value, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeNotEqualTo(Double value) {
            addCriterion("totalfee <>", value, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeGreaterThan(Double value) {
            addCriterion("totalfee >", value, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeGreaterThanOrEqualTo(Double value) {
            addCriterion("totalfee >=", value, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeLessThan(Double value) {
            addCriterion("totalfee <", value, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeLessThanOrEqualTo(Double value) {
            addCriterion("totalfee <=", value, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeIn(List<Double> values) {
            addCriterion("totalfee in", values, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeNotIn(List<Double> values) {
            addCriterion("totalfee not in", values, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeBetween(Double value1, Double value2) {
            addCriterion("totalfee between", value1, value2, "totalfee");
            return (Criteria) this;
        }

        public Criteria andTotalfeeNotBetween(Double value1, Double value2) {
            addCriterion("totalfee not between", value1, value2, "totalfee");
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

        public Criteria andOverseaInTimeIsNull() {
            addCriterion("oversea_in_time is null");
            return (Criteria) this;
        }

        public Criteria andOverseaInTimeIsNotNull() {
            addCriterion("oversea_in_time is not null");
            return (Criteria) this;
        }

        public Criteria andOverseaInTimeEqualTo(Date value) {
            addCriterion("oversea_in_time =", value, "overseaInTime");
            return (Criteria) this;
        }

        public Criteria andOverseaInTimeNotEqualTo(Date value) {
            addCriterion("oversea_in_time <>", value, "overseaInTime");
            return (Criteria) this;
        }

        public Criteria andOverseaInTimeGreaterThan(Date value) {
            addCriterion("oversea_in_time >", value, "overseaInTime");
            return (Criteria) this;
        }

        public Criteria andOverseaInTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("oversea_in_time >=", value, "overseaInTime");
            return (Criteria) this;
        }

        public Criteria andOverseaInTimeLessThan(Date value) {
            addCriterion("oversea_in_time <", value, "overseaInTime");
            return (Criteria) this;
        }

        public Criteria andOverseaInTimeLessThanOrEqualTo(Date value) {
            addCriterion("oversea_in_time <=", value, "overseaInTime");
            return (Criteria) this;
        }

        public Criteria andOverseaInTimeIn(List<Date> values) {
            addCriterion("oversea_in_time in", values, "overseaInTime");
            return (Criteria) this;
        }

        public Criteria andOverseaInTimeNotIn(List<Date> values) {
            addCriterion("oversea_in_time not in", values, "overseaInTime");
            return (Criteria) this;
        }

        public Criteria andOverseaInTimeBetween(Date value1, Date value2) {
            addCriterion("oversea_in_time between", value1, value2, "overseaInTime");
            return (Criteria) this;
        }

        public Criteria andOverseaInTimeNotBetween(Date value1, Date value2) {
            addCriterion("oversea_in_time not between", value1, value2, "overseaInTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOutTimeIsNull() {
            addCriterion("oversea_out_time is null");
            return (Criteria) this;
        }

        public Criteria andOverseaOutTimeIsNotNull() {
            addCriterion("oversea_out_time is not null");
            return (Criteria) this;
        }

        public Criteria andOverseaOutTimeEqualTo(Date value) {
            addCriterion("oversea_out_time =", value, "overseaOutTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOutTimeNotEqualTo(Date value) {
            addCriterion("oversea_out_time <>", value, "overseaOutTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOutTimeGreaterThan(Date value) {
            addCriterion("oversea_out_time >", value, "overseaOutTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("oversea_out_time >=", value, "overseaOutTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOutTimeLessThan(Date value) {
            addCriterion("oversea_out_time <", value, "overseaOutTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOutTimeLessThanOrEqualTo(Date value) {
            addCriterion("oversea_out_time <=", value, "overseaOutTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOutTimeIn(List<Date> values) {
            addCriterion("oversea_out_time in", values, "overseaOutTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOutTimeNotIn(List<Date> values) {
            addCriterion("oversea_out_time not in", values, "overseaOutTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOutTimeBetween(Date value1, Date value2) {
            addCriterion("oversea_out_time between", value1, value2, "overseaOutTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOutTimeNotBetween(Date value1, Date value2) {
            addCriterion("oversea_out_time not between", value1, value2, "overseaOutTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOnTransferTimeIsNull() {
            addCriterion("oversea_on_transfer_time is null");
            return (Criteria) this;
        }

        public Criteria andOverseaOnTransferTimeIsNotNull() {
            addCriterion("oversea_on_transfer_time is not null");
            return (Criteria) this;
        }

        public Criteria andOverseaOnTransferTimeEqualTo(Date value) {
            addCriterion("oversea_on_transfer_time =", value, "overseaOnTransferTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOnTransferTimeNotEqualTo(Date value) {
            addCriterion("oversea_on_transfer_time <>", value, "overseaOnTransferTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOnTransferTimeGreaterThan(Date value) {
            addCriterion("oversea_on_transfer_time >", value, "overseaOnTransferTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOnTransferTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("oversea_on_transfer_time >=", value, "overseaOnTransferTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOnTransferTimeLessThan(Date value) {
            addCriterion("oversea_on_transfer_time <", value, "overseaOnTransferTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOnTransferTimeLessThanOrEqualTo(Date value) {
            addCriterion("oversea_on_transfer_time <=", value, "overseaOnTransferTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOnTransferTimeIn(List<Date> values) {
            addCriterion("oversea_on_transfer_time in", values, "overseaOnTransferTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOnTransferTimeNotIn(List<Date> values) {
            addCriterion("oversea_on_transfer_time not in", values, "overseaOnTransferTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOnTransferTimeBetween(Date value1, Date value2) {
            addCriterion("oversea_on_transfer_time between", value1, value2, "overseaOnTransferTime");
            return (Criteria) this;
        }

        public Criteria andOverseaOnTransferTimeNotBetween(Date value1, Date value2) {
            addCriterion("oversea_on_transfer_time not between", value1, value2, "overseaOnTransferTime");
            return (Criteria) this;
        }

        public Criteria andInlandInTimeIsNull() {
            addCriterion("inland_in_time is null");
            return (Criteria) this;
        }

        public Criteria andInlandInTimeIsNotNull() {
            addCriterion("inland_in_time is not null");
            return (Criteria) this;
        }

        public Criteria andInlandInTimeEqualTo(Date value) {
            addCriterion("inland_in_time =", value, "inlandInTime");
            return (Criteria) this;
        }

        public Criteria andInlandInTimeNotEqualTo(Date value) {
            addCriterion("inland_in_time <>", value, "inlandInTime");
            return (Criteria) this;
        }

        public Criteria andInlandInTimeGreaterThan(Date value) {
            addCriterion("inland_in_time >", value, "inlandInTime");
            return (Criteria) this;
        }

        public Criteria andInlandInTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("inland_in_time >=", value, "inlandInTime");
            return (Criteria) this;
        }

        public Criteria andInlandInTimeLessThan(Date value) {
            addCriterion("inland_in_time <", value, "inlandInTime");
            return (Criteria) this;
        }

        public Criteria andInlandInTimeLessThanOrEqualTo(Date value) {
            addCriterion("inland_in_time <=", value, "inlandInTime");
            return (Criteria) this;
        }

        public Criteria andInlandInTimeIn(List<Date> values) {
            addCriterion("inland_in_time in", values, "inlandInTime");
            return (Criteria) this;
        }

        public Criteria andInlandInTimeNotIn(List<Date> values) {
            addCriterion("inland_in_time not in", values, "inlandInTime");
            return (Criteria) this;
        }

        public Criteria andInlandInTimeBetween(Date value1, Date value2) {
            addCriterion("inland_in_time between", value1, value2, "inlandInTime");
            return (Criteria) this;
        }

        public Criteria andInlandInTimeNotBetween(Date value1, Date value2) {
            addCriterion("inland_in_time not between", value1, value2, "inlandInTime");
            return (Criteria) this;
        }

        public Criteria andInlandOutTimeIsNull() {
            addCriterion("inland_out_time is null");
            return (Criteria) this;
        }

        public Criteria andInlandOutTimeIsNotNull() {
            addCriterion("inland_out_time is not null");
            return (Criteria) this;
        }

        public Criteria andInlandOutTimeEqualTo(Date value) {
            addCriterion("inland_out_time =", value, "inlandOutTime");
            return (Criteria) this;
        }

        public Criteria andInlandOutTimeNotEqualTo(Date value) {
            addCriterion("inland_out_time <>", value, "inlandOutTime");
            return (Criteria) this;
        }

        public Criteria andInlandOutTimeGreaterThan(Date value) {
            addCriterion("inland_out_time >", value, "inlandOutTime");
            return (Criteria) this;
        }

        public Criteria andInlandOutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("inland_out_time >=", value, "inlandOutTime");
            return (Criteria) this;
        }

        public Criteria andInlandOutTimeLessThan(Date value) {
            addCriterion("inland_out_time <", value, "inlandOutTime");
            return (Criteria) this;
        }

        public Criteria andInlandOutTimeLessThanOrEqualTo(Date value) {
            addCriterion("inland_out_time <=", value, "inlandOutTime");
            return (Criteria) this;
        }

        public Criteria andInlandOutTimeIn(List<Date> values) {
            addCriterion("inland_out_time in", values, "inlandOutTime");
            return (Criteria) this;
        }

        public Criteria andInlandOutTimeNotIn(List<Date> values) {
            addCriterion("inland_out_time not in", values, "inlandOutTime");
            return (Criteria) this;
        }

        public Criteria andInlandOutTimeBetween(Date value1, Date value2) {
            addCriterion("inland_out_time between", value1, value2, "inlandOutTime");
            return (Criteria) this;
        }

        public Criteria andInlandOutTimeNotBetween(Date value1, Date value2) {
            addCriterion("inland_out_time not between", value1, value2, "inlandOutTime");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeIsNull() {
            addCriterion("inland_express_company_code is null");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeIsNotNull() {
            addCriterion("inland_express_company_code is not null");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeEqualTo(String value) {
            addCriterion("inland_express_company_code =", value, "inlandExpressCompanyCode");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeNotEqualTo(String value) {
            addCriterion("inland_express_company_code <>", value, "inlandExpressCompanyCode");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeGreaterThan(String value) {
            addCriterion("inland_express_company_code >", value, "inlandExpressCompanyCode");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("inland_express_company_code >=", value, "inlandExpressCompanyCode");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeLessThan(String value) {
            addCriterion("inland_express_company_code <", value, "inlandExpressCompanyCode");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeLessThanOrEqualTo(String value) {
            addCriterion("inland_express_company_code <=", value, "inlandExpressCompanyCode");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeLike(String value) {
            addCriterion("inland_express_company_code like", value, "inlandExpressCompanyCode");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeNotLike(String value) {
            addCriterion("inland_express_company_code not like", value, "inlandExpressCompanyCode");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeIn(List<String> values) {
            addCriterion("inland_express_company_code in", values, "inlandExpressCompanyCode");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeNotIn(List<String> values) {
            addCriterion("inland_express_company_code not in", values, "inlandExpressCompanyCode");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeBetween(String value1, String value2) {
            addCriterion("inland_express_company_code between", value1, value2, "inlandExpressCompanyCode");
            return (Criteria) this;
        }

        public Criteria andInlandExpressCompanyCodeNotBetween(String value1, String value2) {
            addCriterion("inland_express_company_code not between", value1, value2, "inlandExpressCompanyCode");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumIsNull() {
            addCriterion("inland_express_num is null");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumIsNotNull() {
            addCriterion("inland_express_num is not null");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumEqualTo(String value) {
            addCriterion("inland_express_num =", value, "inlandExpressNum");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumNotEqualTo(String value) {
            addCriterion("inland_express_num <>", value, "inlandExpressNum");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumGreaterThan(String value) {
            addCriterion("inland_express_num >", value, "inlandExpressNum");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumGreaterThanOrEqualTo(String value) {
            addCriterion("inland_express_num >=", value, "inlandExpressNum");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumLessThan(String value) {
            addCriterion("inland_express_num <", value, "inlandExpressNum");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumLessThanOrEqualTo(String value) {
            addCriterion("inland_express_num <=", value, "inlandExpressNum");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumLike(String value) {
            addCriterion("inland_express_num like", value, "inlandExpressNum");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumNotLike(String value) {
            addCriterion("inland_express_num not like", value, "inlandExpressNum");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumIn(List<String> values) {
            addCriterion("inland_express_num in", values, "inlandExpressNum");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumNotIn(List<String> values) {
            addCriterion("inland_express_num not in", values, "inlandExpressNum");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumBetween(String value1, String value2) {
            addCriterion("inland_express_num between", value1, value2, "inlandExpressNum");
            return (Criteria) this;
        }

        public Criteria andInlandExpressNumNotBetween(String value1, String value2) {
            addCriterion("inland_express_num not between", value1, value2, "inlandExpressNum");
            return (Criteria) this;
        }

        public Criteria andBuyerSignTimeIsNull() {
            addCriterion("buyer_sign_time is null");
            return (Criteria) this;
        }

        public Criteria andBuyerSignTimeIsNotNull() {
            addCriterion("buyer_sign_time is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerSignTimeEqualTo(Date value) {
            addCriterion("buyer_sign_time =", value, "buyerSignTime");
            return (Criteria) this;
        }

        public Criteria andBuyerSignTimeNotEqualTo(Date value) {
            addCriterion("buyer_sign_time <>", value, "buyerSignTime");
            return (Criteria) this;
        }

        public Criteria andBuyerSignTimeGreaterThan(Date value) {
            addCriterion("buyer_sign_time >", value, "buyerSignTime");
            return (Criteria) this;
        }

        public Criteria andBuyerSignTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("buyer_sign_time >=", value, "buyerSignTime");
            return (Criteria) this;
        }

        public Criteria andBuyerSignTimeLessThan(Date value) {
            addCriterion("buyer_sign_time <", value, "buyerSignTime");
            return (Criteria) this;
        }

        public Criteria andBuyerSignTimeLessThanOrEqualTo(Date value) {
            addCriterion("buyer_sign_time <=", value, "buyerSignTime");
            return (Criteria) this;
        }

        public Criteria andBuyerSignTimeIn(List<Date> values) {
            addCriterion("buyer_sign_time in", values, "buyerSignTime");
            return (Criteria) this;
        }

        public Criteria andBuyerSignTimeNotIn(List<Date> values) {
            addCriterion("buyer_sign_time not in", values, "buyerSignTime");
            return (Criteria) this;
        }

        public Criteria andBuyerSignTimeBetween(Date value1, Date value2) {
            addCriterion("buyer_sign_time between", value1, value2, "buyerSignTime");
            return (Criteria) this;
        }

        public Criteria andBuyerSignTimeNotBetween(Date value1, Date value2) {
            addCriterion("buyer_sign_time not between", value1, value2, "buyerSignTime");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffIsNull() {
            addCriterion("air_take_off is null");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffIsNotNull() {
            addCriterion("air_take_off is not null");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffEqualTo(String value) {
            addCriterion("air_take_off =", value, "airTakeOff");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffNotEqualTo(String value) {
            addCriterion("air_take_off <>", value, "airTakeOff");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffGreaterThan(String value) {
            addCriterion("air_take_off >", value, "airTakeOff");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffGreaterThanOrEqualTo(String value) {
            addCriterion("air_take_off >=", value, "airTakeOff");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffLessThan(String value) {
            addCriterion("air_take_off <", value, "airTakeOff");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffLessThanOrEqualTo(String value) {
            addCriterion("air_take_off <=", value, "airTakeOff");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffLike(String value) {
            addCriterion("air_take_off like", value, "airTakeOff");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffNotLike(String value) {
            addCriterion("air_take_off not like", value, "airTakeOff");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffIn(List<String> values) {
            addCriterion("air_take_off in", values, "airTakeOff");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffNotIn(List<String> values) {
            addCriterion("air_take_off not in", values, "airTakeOff");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffBetween(String value1, String value2) {
            addCriterion("air_take_off between", value1, value2, "airTakeOff");
            return (Criteria) this;
        }

        public Criteria andAirTakeOffNotBetween(String value1, String value2) {
            addCriterion("air_take_off not between", value1, value2, "airTakeOff");
            return (Criteria) this;
        }

        public Criteria andAirlinesIsNull() {
            addCriterion("airlines is null");
            return (Criteria) this;
        }

        public Criteria andAirlinesIsNotNull() {
            addCriterion("airlines is not null");
            return (Criteria) this;
        }

        public Criteria andAirlinesEqualTo(String value) {
            addCriterion("airlines =", value, "airlines");
            return (Criteria) this;
        }

        public Criteria andAirlinesNotEqualTo(String value) {
            addCriterion("airlines <>", value, "airlines");
            return (Criteria) this;
        }

        public Criteria andAirlinesGreaterThan(String value) {
            addCriterion("airlines >", value, "airlines");
            return (Criteria) this;
        }

        public Criteria andAirlinesGreaterThanOrEqualTo(String value) {
            addCriterion("airlines >=", value, "airlines");
            return (Criteria) this;
        }

        public Criteria andAirlinesLessThan(String value) {
            addCriterion("airlines <", value, "airlines");
            return (Criteria) this;
        }

        public Criteria andAirlinesLessThanOrEqualTo(String value) {
            addCriterion("airlines <=", value, "airlines");
            return (Criteria) this;
        }

        public Criteria andAirlinesLike(String value) {
            addCriterion("airlines like", value, "airlines");
            return (Criteria) this;
        }

        public Criteria andAirlinesNotLike(String value) {
            addCriterion("airlines not like", value, "airlines");
            return (Criteria) this;
        }

        public Criteria andAirlinesIn(List<String> values) {
            addCriterion("airlines in", values, "airlines");
            return (Criteria) this;
        }

        public Criteria andAirlinesNotIn(List<String> values) {
            addCriterion("airlines not in", values, "airlines");
            return (Criteria) this;
        }

        public Criteria andAirlinesBetween(String value1, String value2) {
            addCriterion("airlines between", value1, value2, "airlines");
            return (Criteria) this;
        }

        public Criteria andAirlinesNotBetween(String value1, String value2) {
            addCriterion("airlines not between", value1, value2, "airlines");
            return (Criteria) this;
        }

        public Criteria andFlightIsNull() {
            addCriterion("flight is null");
            return (Criteria) this;
        }

        public Criteria andFlightIsNotNull() {
            addCriterion("flight is not null");
            return (Criteria) this;
        }

        public Criteria andFlightEqualTo(String value) {
            addCriterion("flight =", value, "flight");
            return (Criteria) this;
        }

        public Criteria andFlightNotEqualTo(String value) {
            addCriterion("flight <>", value, "flight");
            return (Criteria) this;
        }

        public Criteria andFlightGreaterThan(String value) {
            addCriterion("flight >", value, "flight");
            return (Criteria) this;
        }

        public Criteria andFlightGreaterThanOrEqualTo(String value) {
            addCriterion("flight >=", value, "flight");
            return (Criteria) this;
        }

        public Criteria andFlightLessThan(String value) {
            addCriterion("flight <", value, "flight");
            return (Criteria) this;
        }

        public Criteria andFlightLessThanOrEqualTo(String value) {
            addCriterion("flight <=", value, "flight");
            return (Criteria) this;
        }

        public Criteria andFlightLike(String value) {
            addCriterion("flight like", value, "flight");
            return (Criteria) this;
        }

        public Criteria andFlightNotLike(String value) {
            addCriterion("flight not like", value, "flight");
            return (Criteria) this;
        }

        public Criteria andFlightIn(List<String> values) {
            addCriterion("flight in", values, "flight");
            return (Criteria) this;
        }

        public Criteria andFlightNotIn(List<String> values) {
            addCriterion("flight not in", values, "flight");
            return (Criteria) this;
        }

        public Criteria andFlightBetween(String value1, String value2) {
            addCriterion("flight between", value1, value2, "flight");
            return (Criteria) this;
        }

        public Criteria andFlightNotBetween(String value1, String value2) {
            addCriterion("flight not between", value1, value2, "flight");
            return (Criteria) this;
        }

        public Criteria andTrackInfoIsNull() {
            addCriterion("track_info is null");
            return (Criteria) this;
        }

        public Criteria andTrackInfoIsNotNull() {
            addCriterion("track_info is not null");
            return (Criteria) this;
        }

        public Criteria andTrackInfoEqualTo(String value) {
            addCriterion("track_info =", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoNotEqualTo(String value) {
            addCriterion("track_info <>", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoGreaterThan(String value) {
            addCriterion("track_info >", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoGreaterThanOrEqualTo(String value) {
            addCriterion("track_info >=", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoLessThan(String value) {
            addCriterion("track_info <", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoLessThanOrEqualTo(String value) {
            addCriterion("track_info <=", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoLike(String value) {
            addCriterion("track_info like", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoNotLike(String value) {
            addCriterion("track_info not like", value, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoIn(List<String> values) {
            addCriterion("track_info in", values, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoNotIn(List<String> values) {
            addCriterion("track_info not in", values, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoBetween(String value1, String value2) {
            addCriterion("track_info between", value1, value2, "trackInfo");
            return (Criteria) this;
        }

        public Criteria andTrackInfoNotBetween(String value1, String value2) {
            addCriterion("track_info not between", value1, value2, "trackInfo");
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