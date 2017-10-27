package com.running.business.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunUserBalanceRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunUserBalanceRecordExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUserOldBalanceIsNull() {
            addCriterion("user_old_balance is null");
            return (Criteria) this;
        }

        public Criteria andUserOldBalanceIsNotNull() {
            addCriterion("user_old_balance is not null");
            return (Criteria) this;
        }

        public Criteria andUserOldBalanceEqualTo(Double value) {
            addCriterion("user_old_balance =", value, "userOldBalance");
            return (Criteria) this;
        }

        public Criteria andUserOldBalanceNotEqualTo(Double value) {
            addCriterion("user_old_balance <>", value, "userOldBalance");
            return (Criteria) this;
        }

        public Criteria andUserOldBalanceGreaterThan(Double value) {
            addCriterion("user_old_balance >", value, "userOldBalance");
            return (Criteria) this;
        }

        public Criteria andUserOldBalanceGreaterThanOrEqualTo(Double value) {
            addCriterion("user_old_balance >=", value, "userOldBalance");
            return (Criteria) this;
        }

        public Criteria andUserOldBalanceLessThan(Double value) {
            addCriterion("user_old_balance <", value, "userOldBalance");
            return (Criteria) this;
        }

        public Criteria andUserOldBalanceLessThanOrEqualTo(Double value) {
            addCriterion("user_old_balance <=", value, "userOldBalance");
            return (Criteria) this;
        }

        public Criteria andUserOldBalanceIn(List<Double> values) {
            addCriterion("user_old_balance in", values, "userOldBalance");
            return (Criteria) this;
        }

        public Criteria andUserOldBalanceNotIn(List<Double> values) {
            addCriterion("user_old_balance not in", values, "userOldBalance");
            return (Criteria) this;
        }

        public Criteria andUserOldBalanceBetween(Double value1, Double value2) {
            addCriterion("user_old_balance between", value1, value2, "userOldBalance");
            return (Criteria) this;
        }

        public Criteria andUserOldBalanceNotBetween(Double value1, Double value2) {
            addCriterion("user_old_balance not between", value1, value2, "userOldBalance");
            return (Criteria) this;
        }

        public Criteria andUserAmountIsNull() {
            addCriterion("user_amount is null");
            return (Criteria) this;
        }

        public Criteria andUserAmountIsNotNull() {
            addCriterion("user_amount is not null");
            return (Criteria) this;
        }

        public Criteria andUserAmountEqualTo(Double value) {
            addCriterion("user_amount =", value, "userAmount");
            return (Criteria) this;
        }

        public Criteria andUserAmountNotEqualTo(Double value) {
            addCriterion("user_amount <>", value, "userAmount");
            return (Criteria) this;
        }

        public Criteria andUserAmountGreaterThan(Double value) {
            addCriterion("user_amount >", value, "userAmount");
            return (Criteria) this;
        }

        public Criteria andUserAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("user_amount >=", value, "userAmount");
            return (Criteria) this;
        }

        public Criteria andUserAmountLessThan(Double value) {
            addCriterion("user_amount <", value, "userAmount");
            return (Criteria) this;
        }

        public Criteria andUserAmountLessThanOrEqualTo(Double value) {
            addCriterion("user_amount <=", value, "userAmount");
            return (Criteria) this;
        }

        public Criteria andUserAmountIn(List<Double> values) {
            addCriterion("user_amount in", values, "userAmount");
            return (Criteria) this;
        }

        public Criteria andUserAmountNotIn(List<Double> values) {
            addCriterion("user_amount not in", values, "userAmount");
            return (Criteria) this;
        }

        public Criteria andUserAmountBetween(Double value1, Double value2) {
            addCriterion("user_amount between", value1, value2, "userAmount");
            return (Criteria) this;
        }

        public Criteria andUserAmountNotBetween(Double value1, Double value2) {
            addCriterion("user_amount not between", value1, value2, "userAmount");
            return (Criteria) this;
        }

        public Criteria andUserNewBalanceIsNull() {
            addCriterion("user_new_balance is null");
            return (Criteria) this;
        }

        public Criteria andUserNewBalanceIsNotNull() {
            addCriterion("user_new_balance is not null");
            return (Criteria) this;
        }

        public Criteria andUserNewBalanceEqualTo(Double value) {
            addCriterion("user_new_balance =", value, "userNewBalance");
            return (Criteria) this;
        }

        public Criteria andUserNewBalanceNotEqualTo(Double value) {
            addCriterion("user_new_balance <>", value, "userNewBalance");
            return (Criteria) this;
        }

        public Criteria andUserNewBalanceGreaterThan(Double value) {
            addCriterion("user_new_balance >", value, "userNewBalance");
            return (Criteria) this;
        }

        public Criteria andUserNewBalanceGreaterThanOrEqualTo(Double value) {
            addCriterion("user_new_balance >=", value, "userNewBalance");
            return (Criteria) this;
        }

        public Criteria andUserNewBalanceLessThan(Double value) {
            addCriterion("user_new_balance <", value, "userNewBalance");
            return (Criteria) this;
        }

        public Criteria andUserNewBalanceLessThanOrEqualTo(Double value) {
            addCriterion("user_new_balance <=", value, "userNewBalance");
            return (Criteria) this;
        }

        public Criteria andUserNewBalanceIn(List<Double> values) {
            addCriterion("user_new_balance in", values, "userNewBalance");
            return (Criteria) this;
        }

        public Criteria andUserNewBalanceNotIn(List<Double> values) {
            addCriterion("user_new_balance not in", values, "userNewBalance");
            return (Criteria) this;
        }

        public Criteria andUserNewBalanceBetween(Double value1, Double value2) {
            addCriterion("user_new_balance between", value1, value2, "userNewBalance");
            return (Criteria) this;
        }

        public Criteria andUserNewBalanceNotBetween(Double value1, Double value2) {
            addCriterion("user_new_balance not between", value1, value2, "userNewBalance");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(String value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(String value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(String value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(String value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(String value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLike(String value) {
            addCriterion("user_type like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotLike(String value) {
            addCriterion("user_type not like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<String> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<String> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(String value1, String value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(String value1, String value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserNumberIsNull() {
            addCriterion("user_number is null");
            return (Criteria) this;
        }

        public Criteria andUserNumberIsNotNull() {
            addCriterion("user_number is not null");
            return (Criteria) this;
        }

        public Criteria andUserNumberEqualTo(String value) {
            addCriterion("user_number =", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberNotEqualTo(String value) {
            addCriterion("user_number <>", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberGreaterThan(String value) {
            addCriterion("user_number >", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberGreaterThanOrEqualTo(String value) {
            addCriterion("user_number >=", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberLessThan(String value) {
            addCriterion("user_number <", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberLessThanOrEqualTo(String value) {
            addCriterion("user_number <=", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberLike(String value) {
            addCriterion("user_number like", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberNotLike(String value) {
            addCriterion("user_number not like", value, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberIn(List<String> values) {
            addCriterion("user_number in", values, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberNotIn(List<String> values) {
            addCriterion("user_number not in", values, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberBetween(String value1, String value2) {
            addCriterion("user_number between", value1, value2, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserNumberNotBetween(String value1, String value2) {
            addCriterion("user_number not between", value1, value2, "userNumber");
            return (Criteria) this;
        }

        public Criteria andUserTimeIsNull() {
            addCriterion("user_time is null");
            return (Criteria) this;
        }

        public Criteria andUserTimeIsNotNull() {
            addCriterion("user_time is not null");
            return (Criteria) this;
        }

        public Criteria andUserTimeEqualTo(Date value) {
            addCriterion("user_time =", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeNotEqualTo(Date value) {
            addCriterion("user_time <>", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeGreaterThan(Date value) {
            addCriterion("user_time >", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user_time >=", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeLessThan(Date value) {
            addCriterion("user_time <", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeLessThanOrEqualTo(Date value) {
            addCriterion("user_time <=", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeIn(List<Date> values) {
            addCriterion("user_time in", values, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeNotIn(List<Date> values) {
            addCriterion("user_time not in", values, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeBetween(Date value1, Date value2) {
            addCriterion("user_time between", value1, value2, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeNotBetween(Date value1, Date value2) {
            addCriterion("user_time not between", value1, value2, "userTime");
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