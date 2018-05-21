package com.wangqin.shopkeeper.biz1.app.dal.dataObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogisticCompanyDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogisticCompanyDOExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameInJdIsNull() {
            addCriterion("name_in_jd is null");
            return (Criteria) this;
        }

        public Criteria andNameInJdIsNotNull() {
            addCriterion("name_in_jd is not null");
            return (Criteria) this;
        }

        public Criteria andNameInJdEqualTo(String value) {
            addCriterion("name_in_jd =", value, "nameInJd");
            return (Criteria) this;
        }

        public Criteria andNameInJdNotEqualTo(String value) {
            addCriterion("name_in_jd <>", value, "nameInJd");
            return (Criteria) this;
        }

        public Criteria andNameInJdGreaterThan(String value) {
            addCriterion("name_in_jd >", value, "nameInJd");
            return (Criteria) this;
        }

        public Criteria andNameInJdGreaterThanOrEqualTo(String value) {
            addCriterion("name_in_jd >=", value, "nameInJd");
            return (Criteria) this;
        }

        public Criteria andNameInJdLessThan(String value) {
            addCriterion("name_in_jd <", value, "nameInJd");
            return (Criteria) this;
        }

        public Criteria andNameInJdLessThanOrEqualTo(String value) {
            addCriterion("name_in_jd <=", value, "nameInJd");
            return (Criteria) this;
        }

        public Criteria andNameInJdLike(String value) {
            addCriterion("name_in_jd like", value, "nameInJd");
            return (Criteria) this;
        }

        public Criteria andNameInJdNotLike(String value) {
            addCriterion("name_in_jd not like", value, "nameInJd");
            return (Criteria) this;
        }

        public Criteria andNameInJdIn(List<String> values) {
            addCriterion("name_in_jd in", values, "nameInJd");
            return (Criteria) this;
        }

        public Criteria andNameInJdNotIn(List<String> values) {
            addCriterion("name_in_jd not in", values, "nameInJd");
            return (Criteria) this;
        }

        public Criteria andNameInJdBetween(String value1, String value2) {
            addCriterion("name_in_jd between", value1, value2, "nameInJd");
            return (Criteria) this;
        }

        public Criteria andNameInJdNotBetween(String value1, String value2) {
            addCriterion("name_in_jd not between", value1, value2, "nameInJd");
            return (Criteria) this;
        }

        public Criteria andCodeInJdIsNull() {
            addCriterion("code_in_jd is null");
            return (Criteria) this;
        }

        public Criteria andCodeInJdIsNotNull() {
            addCriterion("code_in_jd is not null");
            return (Criteria) this;
        }

        public Criteria andCodeInJdEqualTo(String value) {
            addCriterion("code_in_jd =", value, "codeInJd");
            return (Criteria) this;
        }

        public Criteria andCodeInJdNotEqualTo(String value) {
            addCriterion("code_in_jd <>", value, "codeInJd");
            return (Criteria) this;
        }

        public Criteria andCodeInJdGreaterThan(String value) {
            addCriterion("code_in_jd >", value, "codeInJd");
            return (Criteria) this;
        }

        public Criteria andCodeInJdGreaterThanOrEqualTo(String value) {
            addCriterion("code_in_jd >=", value, "codeInJd");
            return (Criteria) this;
        }

        public Criteria andCodeInJdLessThan(String value) {
            addCriterion("code_in_jd <", value, "codeInJd");
            return (Criteria) this;
        }

        public Criteria andCodeInJdLessThanOrEqualTo(String value) {
            addCriterion("code_in_jd <=", value, "codeInJd");
            return (Criteria) this;
        }

        public Criteria andCodeInJdLike(String value) {
            addCriterion("code_in_jd like", value, "codeInJd");
            return (Criteria) this;
        }

        public Criteria andCodeInJdNotLike(String value) {
            addCriterion("code_in_jd not like", value, "codeInJd");
            return (Criteria) this;
        }

        public Criteria andCodeInJdIn(List<String> values) {
            addCriterion("code_in_jd in", values, "codeInJd");
            return (Criteria) this;
        }

        public Criteria andCodeInJdNotIn(List<String> values) {
            addCriterion("code_in_jd not in", values, "codeInJd");
            return (Criteria) this;
        }

        public Criteria andCodeInJdBetween(String value1, String value2) {
            addCriterion("code_in_jd between", value1, value2, "codeInJd");
            return (Criteria) this;
        }

        public Criteria andCodeInJdNotBetween(String value1, String value2) {
            addCriterion("code_in_jd not between", value1, value2, "codeInJd");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanIsNull() {
            addCriterion("name_in_youzan is null");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanIsNotNull() {
            addCriterion("name_in_youzan is not null");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanEqualTo(String value) {
            addCriterion("name_in_youzan =", value, "nameInYouzan");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanNotEqualTo(String value) {
            addCriterion("name_in_youzan <>", value, "nameInYouzan");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanGreaterThan(String value) {
            addCriterion("name_in_youzan >", value, "nameInYouzan");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanGreaterThanOrEqualTo(String value) {
            addCriterion("name_in_youzan >=", value, "nameInYouzan");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanLessThan(String value) {
            addCriterion("name_in_youzan <", value, "nameInYouzan");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanLessThanOrEqualTo(String value) {
            addCriterion("name_in_youzan <=", value, "nameInYouzan");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanLike(String value) {
            addCriterion("name_in_youzan like", value, "nameInYouzan");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanNotLike(String value) {
            addCriterion("name_in_youzan not like", value, "nameInYouzan");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanIn(List<String> values) {
            addCriterion("name_in_youzan in", values, "nameInYouzan");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanNotIn(List<String> values) {
            addCriterion("name_in_youzan not in", values, "nameInYouzan");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanBetween(String value1, String value2) {
            addCriterion("name_in_youzan between", value1, value2, "nameInYouzan");
            return (Criteria) this;
        }

        public Criteria andNameInYouzanNotBetween(String value1, String value2) {
            addCriterion("name_in_youzan not between", value1, value2, "nameInYouzan");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanIsNull() {
            addCriterion("code_in_youzan is null");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanIsNotNull() {
            addCriterion("code_in_youzan is not null");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanEqualTo(String value) {
            addCriterion("code_in_youzan =", value, "codeInYouzan");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanNotEqualTo(String value) {
            addCriterion("code_in_youzan <>", value, "codeInYouzan");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanGreaterThan(String value) {
            addCriterion("code_in_youzan >", value, "codeInYouzan");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanGreaterThanOrEqualTo(String value) {
            addCriterion("code_in_youzan >=", value, "codeInYouzan");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanLessThan(String value) {
            addCriterion("code_in_youzan <", value, "codeInYouzan");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanLessThanOrEqualTo(String value) {
            addCriterion("code_in_youzan <=", value, "codeInYouzan");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanLike(String value) {
            addCriterion("code_in_youzan like", value, "codeInYouzan");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanNotLike(String value) {
            addCriterion("code_in_youzan not like", value, "codeInYouzan");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanIn(List<String> values) {
            addCriterion("code_in_youzan in", values, "codeInYouzan");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanNotIn(List<String> values) {
            addCriterion("code_in_youzan not in", values, "codeInYouzan");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanBetween(String value1, String value2) {
            addCriterion("code_in_youzan between", value1, value2, "codeInYouzan");
            return (Criteria) this;
        }

        public Criteria andCodeInYouzanNotBetween(String value1, String value2) {
            addCriterion("code_in_youzan not between", value1, value2, "codeInYouzan");
            return (Criteria) this;
        }

        public Criteria andNameInPddIsNull() {
            addCriterion("name_in_pdd is null");
            return (Criteria) this;
        }

        public Criteria andNameInPddIsNotNull() {
            addCriterion("name_in_pdd is not null");
            return (Criteria) this;
        }

        public Criteria andNameInPddEqualTo(String value) {
            addCriterion("name_in_pdd =", value, "nameInPdd");
            return (Criteria) this;
        }

        public Criteria andNameInPddNotEqualTo(String value) {
            addCriterion("name_in_pdd <>", value, "nameInPdd");
            return (Criteria) this;
        }

        public Criteria andNameInPddGreaterThan(String value) {
            addCriterion("name_in_pdd >", value, "nameInPdd");
            return (Criteria) this;
        }

        public Criteria andNameInPddGreaterThanOrEqualTo(String value) {
            addCriterion("name_in_pdd >=", value, "nameInPdd");
            return (Criteria) this;
        }

        public Criteria andNameInPddLessThan(String value) {
            addCriterion("name_in_pdd <", value, "nameInPdd");
            return (Criteria) this;
        }

        public Criteria andNameInPddLessThanOrEqualTo(String value) {
            addCriterion("name_in_pdd <=", value, "nameInPdd");
            return (Criteria) this;
        }

        public Criteria andNameInPddLike(String value) {
            addCriterion("name_in_pdd like", value, "nameInPdd");
            return (Criteria) this;
        }

        public Criteria andNameInPddNotLike(String value) {
            addCriterion("name_in_pdd not like", value, "nameInPdd");
            return (Criteria) this;
        }

        public Criteria andNameInPddIn(List<String> values) {
            addCriterion("name_in_pdd in", values, "nameInPdd");
            return (Criteria) this;
        }

        public Criteria andNameInPddNotIn(List<String> values) {
            addCriterion("name_in_pdd not in", values, "nameInPdd");
            return (Criteria) this;
        }

        public Criteria andNameInPddBetween(String value1, String value2) {
            addCriterion("name_in_pdd between", value1, value2, "nameInPdd");
            return (Criteria) this;
        }

        public Criteria andNameInPddNotBetween(String value1, String value2) {
            addCriterion("name_in_pdd not between", value1, value2, "nameInPdd");
            return (Criteria) this;
        }

        public Criteria andCodeInPddIsNull() {
            addCriterion("code_in_pdd is null");
            return (Criteria) this;
        }

        public Criteria andCodeInPddIsNotNull() {
            addCriterion("code_in_pdd is not null");
            return (Criteria) this;
        }

        public Criteria andCodeInPddEqualTo(String value) {
            addCriterion("code_in_pdd =", value, "codeInPdd");
            return (Criteria) this;
        }

        public Criteria andCodeInPddNotEqualTo(String value) {
            addCriterion("code_in_pdd <>", value, "codeInPdd");
            return (Criteria) this;
        }

        public Criteria andCodeInPddGreaterThan(String value) {
            addCriterion("code_in_pdd >", value, "codeInPdd");
            return (Criteria) this;
        }

        public Criteria andCodeInPddGreaterThanOrEqualTo(String value) {
            addCriterion("code_in_pdd >=", value, "codeInPdd");
            return (Criteria) this;
        }

        public Criteria andCodeInPddLessThan(String value) {
            addCriterion("code_in_pdd <", value, "codeInPdd");
            return (Criteria) this;
        }

        public Criteria andCodeInPddLessThanOrEqualTo(String value) {
            addCriterion("code_in_pdd <=", value, "codeInPdd");
            return (Criteria) this;
        }

        public Criteria andCodeInPddLike(String value) {
            addCriterion("code_in_pdd like", value, "codeInPdd");
            return (Criteria) this;
        }

        public Criteria andCodeInPddNotLike(String value) {
            addCriterion("code_in_pdd not like", value, "codeInPdd");
            return (Criteria) this;
        }

        public Criteria andCodeInPddIn(List<String> values) {
            addCriterion("code_in_pdd in", values, "codeInPdd");
            return (Criteria) this;
        }

        public Criteria andCodeInPddNotIn(List<String> values) {
            addCriterion("code_in_pdd not in", values, "codeInPdd");
            return (Criteria) this;
        }

        public Criteria andCodeInPddBetween(String value1, String value2) {
            addCriterion("code_in_pdd between", value1, value2, "codeInPdd");
            return (Criteria) this;
        }

        public Criteria andCodeInPddNotBetween(String value1, String value2) {
            addCriterion("code_in_pdd not between", value1, value2, "codeInPdd");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoIsNull() {
            addCriterion("name_in_taobao is null");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoIsNotNull() {
            addCriterion("name_in_taobao is not null");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoEqualTo(String value) {
            addCriterion("name_in_taobao =", value, "nameInTaobao");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoNotEqualTo(String value) {
            addCriterion("name_in_taobao <>", value, "nameInTaobao");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoGreaterThan(String value) {
            addCriterion("name_in_taobao >", value, "nameInTaobao");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoGreaterThanOrEqualTo(String value) {
            addCriterion("name_in_taobao >=", value, "nameInTaobao");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoLessThan(String value) {
            addCriterion("name_in_taobao <", value, "nameInTaobao");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoLessThanOrEqualTo(String value) {
            addCriterion("name_in_taobao <=", value, "nameInTaobao");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoLike(String value) {
            addCriterion("name_in_taobao like", value, "nameInTaobao");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoNotLike(String value) {
            addCriterion("name_in_taobao not like", value, "nameInTaobao");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoIn(List<String> values) {
            addCriterion("name_in_taobao in", values, "nameInTaobao");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoNotIn(List<String> values) {
            addCriterion("name_in_taobao not in", values, "nameInTaobao");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoBetween(String value1, String value2) {
            addCriterion("name_in_taobao between", value1, value2, "nameInTaobao");
            return (Criteria) this;
        }

        public Criteria andNameInTaobaoNotBetween(String value1, String value2) {
            addCriterion("name_in_taobao not between", value1, value2, "nameInTaobao");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoIsNull() {
            addCriterion("code_in_taobao is null");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoIsNotNull() {
            addCriterion("code_in_taobao is not null");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoEqualTo(String value) {
            addCriterion("code_in_taobao =", value, "codeInTaobao");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoNotEqualTo(String value) {
            addCriterion("code_in_taobao <>", value, "codeInTaobao");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoGreaterThan(String value) {
            addCriterion("code_in_taobao >", value, "codeInTaobao");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoGreaterThanOrEqualTo(String value) {
            addCriterion("code_in_taobao >=", value, "codeInTaobao");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoLessThan(String value) {
            addCriterion("code_in_taobao <", value, "codeInTaobao");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoLessThanOrEqualTo(String value) {
            addCriterion("code_in_taobao <=", value, "codeInTaobao");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoLike(String value) {
            addCriterion("code_in_taobao like", value, "codeInTaobao");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoNotLike(String value) {
            addCriterion("code_in_taobao not like", value, "codeInTaobao");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoIn(List<String> values) {
            addCriterion("code_in_taobao in", values, "codeInTaobao");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoNotIn(List<String> values) {
            addCriterion("code_in_taobao not in", values, "codeInTaobao");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoBetween(String value1, String value2) {
            addCriterion("code_in_taobao between", value1, value2, "codeInTaobao");
            return (Criteria) this;
        }

        public Criteria andCodeInTaobaoNotBetween(String value1, String value2) {
            addCriterion("code_in_taobao not between", value1, value2, "codeInTaobao");
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