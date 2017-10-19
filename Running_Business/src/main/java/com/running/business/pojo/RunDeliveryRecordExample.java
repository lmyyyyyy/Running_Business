package com.running.business.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunDeliveryRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunDeliveryRecordExample() {
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

        public Criteria andDidIsNull() {
            addCriterion("did is null");
            return (Criteria) this;
        }

        public Criteria andDidIsNotNull() {
            addCriterion("did is not null");
            return (Criteria) this;
        }

        public Criteria andDidEqualTo(Integer value) {
            addCriterion("did =", value, "did");
            return (Criteria) this;
        }

        public Criteria andDidNotEqualTo(Integer value) {
            addCriterion("did <>", value, "did");
            return (Criteria) this;
        }

        public Criteria andDidGreaterThan(Integer value) {
            addCriterion("did >", value, "did");
            return (Criteria) this;
        }

        public Criteria andDidGreaterThanOrEqualTo(Integer value) {
            addCriterion("did >=", value, "did");
            return (Criteria) this;
        }

        public Criteria andDidLessThan(Integer value) {
            addCriterion("did <", value, "did");
            return (Criteria) this;
        }

        public Criteria andDidLessThanOrEqualTo(Integer value) {
            addCriterion("did <=", value, "did");
            return (Criteria) this;
        }

        public Criteria andDidIn(List<Integer> values) {
            addCriterion("did in", values, "did");
            return (Criteria) this;
        }

        public Criteria andDidNotIn(List<Integer> values) {
            addCriterion("did not in", values, "did");
            return (Criteria) this;
        }

        public Criteria andDidBetween(Integer value1, Integer value2) {
            addCriterion("did between", value1, value2, "did");
            return (Criteria) this;
        }

        public Criteria andDidNotBetween(Integer value1, Integer value2) {
            addCriterion("did not between", value1, value2, "did");
            return (Criteria) this;
        }

        public Criteria andDeliveryOldBalanceIsNull() {
            addCriterion("delivery_old_balance is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryOldBalanceIsNotNull() {
            addCriterion("delivery_old_balance is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryOldBalanceEqualTo(Double value) {
            addCriterion("delivery_old_balance =", value, "deliveryOldBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryOldBalanceNotEqualTo(Double value) {
            addCriterion("delivery_old_balance <>", value, "deliveryOldBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryOldBalanceGreaterThan(Double value) {
            addCriterion("delivery_old_balance >", value, "deliveryOldBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryOldBalanceGreaterThanOrEqualTo(Double value) {
            addCriterion("delivery_old_balance >=", value, "deliveryOldBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryOldBalanceLessThan(Double value) {
            addCriterion("delivery_old_balance <", value, "deliveryOldBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryOldBalanceLessThanOrEqualTo(Double value) {
            addCriterion("delivery_old_balance <=", value, "deliveryOldBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryOldBalanceIn(List<Double> values) {
            addCriterion("delivery_old_balance in", values, "deliveryOldBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryOldBalanceNotIn(List<Double> values) {
            addCriterion("delivery_old_balance not in", values, "deliveryOldBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryOldBalanceBetween(Double value1, Double value2) {
            addCriterion("delivery_old_balance between", value1, value2, "deliveryOldBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryOldBalanceNotBetween(Double value1, Double value2) {
            addCriterion("delivery_old_balance not between", value1, value2, "deliveryOldBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountIsNull() {
            addCriterion("delivery_amount is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountIsNotNull() {
            addCriterion("delivery_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountEqualTo(Double value) {
            addCriterion("delivery_amount =", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountNotEqualTo(Double value) {
            addCriterion("delivery_amount <>", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountGreaterThan(Double value) {
            addCriterion("delivery_amount >", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("delivery_amount >=", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountLessThan(Double value) {
            addCriterion("delivery_amount <", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountLessThanOrEqualTo(Double value) {
            addCriterion("delivery_amount <=", value, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountIn(List<Double> values) {
            addCriterion("delivery_amount in", values, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountNotIn(List<Double> values) {
            addCriterion("delivery_amount not in", values, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountBetween(Double value1, Double value2) {
            addCriterion("delivery_amount between", value1, value2, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmountNotBetween(Double value1, Double value2) {
            addCriterion("delivery_amount not between", value1, value2, "deliveryAmount");
            return (Criteria) this;
        }

        public Criteria andDeliveryNewBalanceIsNull() {
            addCriterion("delivery_new_balance is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryNewBalanceIsNotNull() {
            addCriterion("delivery_new_balance is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryNewBalanceEqualTo(Double value) {
            addCriterion("delivery_new_balance =", value, "deliveryNewBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryNewBalanceNotEqualTo(Double value) {
            addCriterion("delivery_new_balance <>", value, "deliveryNewBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryNewBalanceGreaterThan(Double value) {
            addCriterion("delivery_new_balance >", value, "deliveryNewBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryNewBalanceGreaterThanOrEqualTo(Double value) {
            addCriterion("delivery_new_balance >=", value, "deliveryNewBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryNewBalanceLessThan(Double value) {
            addCriterion("delivery_new_balance <", value, "deliveryNewBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryNewBalanceLessThanOrEqualTo(Double value) {
            addCriterion("delivery_new_balance <=", value, "deliveryNewBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryNewBalanceIn(List<Double> values) {
            addCriterion("delivery_new_balance in", values, "deliveryNewBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryNewBalanceNotIn(List<Double> values) {
            addCriterion("delivery_new_balance not in", values, "deliveryNewBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryNewBalanceBetween(Double value1, Double value2) {
            addCriterion("delivery_new_balance between", value1, value2, "deliveryNewBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryNewBalanceNotBetween(Double value1, Double value2) {
            addCriterion("delivery_new_balance not between", value1, value2, "deliveryNewBalance");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeIsNull() {
            addCriterion("delivery_type is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeIsNotNull() {
            addCriterion("delivery_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeEqualTo(String value) {
            addCriterion("delivery_type =", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotEqualTo(String value) {
            addCriterion("delivery_type <>", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeGreaterThan(String value) {
            addCriterion("delivery_type >", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_type >=", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeLessThan(String value) {
            addCriterion("delivery_type <", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeLessThanOrEqualTo(String value) {
            addCriterion("delivery_type <=", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeLike(String value) {
            addCriterion("delivery_type like", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotLike(String value) {
            addCriterion("delivery_type not like", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeIn(List<String> values) {
            addCriterion("delivery_type in", values, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotIn(List<String> values) {
            addCriterion("delivery_type not in", values, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeBetween(String value1, String value2) {
            addCriterion("delivery_type between", value1, value2, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotBetween(String value1, String value2) {
            addCriterion("delivery_type not between", value1, value2, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberIsNull() {
            addCriterion("delivery_number is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberIsNotNull() {
            addCriterion("delivery_number is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberEqualTo(String value) {
            addCriterion("delivery_number =", value, "deliveryNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberNotEqualTo(String value) {
            addCriterion("delivery_number <>", value, "deliveryNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberGreaterThan(String value) {
            addCriterion("delivery_number >", value, "deliveryNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_number >=", value, "deliveryNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberLessThan(String value) {
            addCriterion("delivery_number <", value, "deliveryNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberLessThanOrEqualTo(String value) {
            addCriterion("delivery_number <=", value, "deliveryNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberLike(String value) {
            addCriterion("delivery_number like", value, "deliveryNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberNotLike(String value) {
            addCriterion("delivery_number not like", value, "deliveryNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberIn(List<String> values) {
            addCriterion("delivery_number in", values, "deliveryNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberNotIn(List<String> values) {
            addCriterion("delivery_number not in", values, "deliveryNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberBetween(String value1, String value2) {
            addCriterion("delivery_number between", value1, value2, "deliveryNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumberNotBetween(String value1, String value2) {
            addCriterion("delivery_number not between", value1, value2, "deliveryNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNull() {
            addCriterion("delivery_time is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIsNotNull() {
            addCriterion("delivery_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeEqualTo(Date value) {
            addCriterion("delivery_time =", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotEqualTo(Date value) {
            addCriterion("delivery_time <>", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThan(Date value) {
            addCriterion("delivery_time >", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("delivery_time >=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThan(Date value) {
            addCriterion("delivery_time <", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeLessThanOrEqualTo(Date value) {
            addCriterion("delivery_time <=", value, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeIn(List<Date> values) {
            addCriterion("delivery_time in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotIn(List<Date> values) {
            addCriterion("delivery_time not in", values, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeBetween(Date value1, Date value2) {
            addCriterion("delivery_time between", value1, value2, "deliveryTime");
            return (Criteria) this;
        }

        public Criteria andDeliveryTimeNotBetween(Date value1, Date value2) {
            addCriterion("delivery_time not between", value1, value2, "deliveryTime");
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