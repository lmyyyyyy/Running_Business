package com.running.business.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunOrderStatusExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunOrderStatusExample() {
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

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(String value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(String value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(String value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(String value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(String value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLike(String value) {
            addCriterion("orderid like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotLike(String value) {
            addCriterion("orderid not like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<String> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<String> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(String value1, String value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(String value1, String value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
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

        public Criteria andOrderIsSubmitIsNull() {
            addCriterion("order_is_submit is null");
            return (Criteria) this;
        }

        public Criteria andOrderIsSubmitIsNotNull() {
            addCriterion("order_is_submit is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIsSubmitEqualTo(Integer value) {
            addCriterion("order_is_submit =", value, "orderIsSubmit");
            return (Criteria) this;
        }

        public Criteria andOrderIsSubmitNotEqualTo(Integer value) {
            addCriterion("order_is_submit <>", value, "orderIsSubmit");
            return (Criteria) this;
        }

        public Criteria andOrderIsSubmitGreaterThan(Integer value) {
            addCriterion("order_is_submit >", value, "orderIsSubmit");
            return (Criteria) this;
        }

        public Criteria andOrderIsSubmitGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_is_submit >=", value, "orderIsSubmit");
            return (Criteria) this;
        }

        public Criteria andOrderIsSubmitLessThan(Integer value) {
            addCriterion("order_is_submit <", value, "orderIsSubmit");
            return (Criteria) this;
        }

        public Criteria andOrderIsSubmitLessThanOrEqualTo(Integer value) {
            addCriterion("order_is_submit <=", value, "orderIsSubmit");
            return (Criteria) this;
        }

        public Criteria andOrderIsSubmitIn(List<Integer> values) {
            addCriterion("order_is_submit in", values, "orderIsSubmit");
            return (Criteria) this;
        }

        public Criteria andOrderIsSubmitNotIn(List<Integer> values) {
            addCriterion("order_is_submit not in", values, "orderIsSubmit");
            return (Criteria) this;
        }

        public Criteria andOrderIsSubmitBetween(Integer value1, Integer value2) {
            addCriterion("order_is_submit between", value1, value2, "orderIsSubmit");
            return (Criteria) this;
        }

        public Criteria andOrderIsSubmitNotBetween(Integer value1, Integer value2) {
            addCriterion("order_is_submit not between", value1, value2, "orderIsSubmit");
            return (Criteria) this;
        }

        public Criteria andOrderIsPayIsNull() {
            addCriterion("order_is_pay is null");
            return (Criteria) this;
        }

        public Criteria andOrderIsPayIsNotNull() {
            addCriterion("order_is_pay is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIsPayEqualTo(Integer value) {
            addCriterion("order_is_pay =", value, "orderIsPay");
            return (Criteria) this;
        }

        public Criteria andOrderIsPayNotEqualTo(Integer value) {
            addCriterion("order_is_pay <>", value, "orderIsPay");
            return (Criteria) this;
        }

        public Criteria andOrderIsPayGreaterThan(Integer value) {
            addCriterion("order_is_pay >", value, "orderIsPay");
            return (Criteria) this;
        }

        public Criteria andOrderIsPayGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_is_pay >=", value, "orderIsPay");
            return (Criteria) this;
        }

        public Criteria andOrderIsPayLessThan(Integer value) {
            addCriterion("order_is_pay <", value, "orderIsPay");
            return (Criteria) this;
        }

        public Criteria andOrderIsPayLessThanOrEqualTo(Integer value) {
            addCriterion("order_is_pay <=", value, "orderIsPay");
            return (Criteria) this;
        }

        public Criteria andOrderIsPayIn(List<Integer> values) {
            addCriterion("order_is_pay in", values, "orderIsPay");
            return (Criteria) this;
        }

        public Criteria andOrderIsPayNotIn(List<Integer> values) {
            addCriterion("order_is_pay not in", values, "orderIsPay");
            return (Criteria) this;
        }

        public Criteria andOrderIsPayBetween(Integer value1, Integer value2) {
            addCriterion("order_is_pay between", value1, value2, "orderIsPay");
            return (Criteria) this;
        }

        public Criteria andOrderIsPayNotBetween(Integer value1, Integer value2) {
            addCriterion("order_is_pay not between", value1, value2, "orderIsPay");
            return (Criteria) this;
        }

        public Criteria andOrderIsOrdersIsNull() {
            addCriterion("order_is_orders is null");
            return (Criteria) this;
        }

        public Criteria andOrderIsOrdersIsNotNull() {
            addCriterion("order_is_orders is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIsOrdersEqualTo(Integer value) {
            addCriterion("order_is_orders =", value, "orderIsOrders");
            return (Criteria) this;
        }

        public Criteria andOrderIsOrdersNotEqualTo(Integer value) {
            addCriterion("order_is_orders <>", value, "orderIsOrders");
            return (Criteria) this;
        }

        public Criteria andOrderIsOrdersGreaterThan(Integer value) {
            addCriterion("order_is_orders >", value, "orderIsOrders");
            return (Criteria) this;
        }

        public Criteria andOrderIsOrdersGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_is_orders >=", value, "orderIsOrders");
            return (Criteria) this;
        }

        public Criteria andOrderIsOrdersLessThan(Integer value) {
            addCriterion("order_is_orders <", value, "orderIsOrders");
            return (Criteria) this;
        }

        public Criteria andOrderIsOrdersLessThanOrEqualTo(Integer value) {
            addCriterion("order_is_orders <=", value, "orderIsOrders");
            return (Criteria) this;
        }

        public Criteria andOrderIsOrdersIn(List<Integer> values) {
            addCriterion("order_is_orders in", values, "orderIsOrders");
            return (Criteria) this;
        }

        public Criteria andOrderIsOrdersNotIn(List<Integer> values) {
            addCriterion("order_is_orders not in", values, "orderIsOrders");
            return (Criteria) this;
        }

        public Criteria andOrderIsOrdersBetween(Integer value1, Integer value2) {
            addCriterion("order_is_orders between", value1, value2, "orderIsOrders");
            return (Criteria) this;
        }

        public Criteria andOrderIsOrdersNotBetween(Integer value1, Integer value2) {
            addCriterion("order_is_orders not between", value1, value2, "orderIsOrders");
            return (Criteria) this;
        }

        public Criteria andOrderIsSendIsNull() {
            addCriterion("order_is_send is null");
            return (Criteria) this;
        }

        public Criteria andOrderIsSendIsNotNull() {
            addCriterion("order_is_send is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIsSendEqualTo(Integer value) {
            addCriterion("order_is_send =", value, "orderIsSend");
            return (Criteria) this;
        }

        public Criteria andOrderIsSendNotEqualTo(Integer value) {
            addCriterion("order_is_send <>", value, "orderIsSend");
            return (Criteria) this;
        }

        public Criteria andOrderIsSendGreaterThan(Integer value) {
            addCriterion("order_is_send >", value, "orderIsSend");
            return (Criteria) this;
        }

        public Criteria andOrderIsSendGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_is_send >=", value, "orderIsSend");
            return (Criteria) this;
        }

        public Criteria andOrderIsSendLessThan(Integer value) {
            addCriterion("order_is_send <", value, "orderIsSend");
            return (Criteria) this;
        }

        public Criteria andOrderIsSendLessThanOrEqualTo(Integer value) {
            addCriterion("order_is_send <=", value, "orderIsSend");
            return (Criteria) this;
        }

        public Criteria andOrderIsSendIn(List<Integer> values) {
            addCriterion("order_is_send in", values, "orderIsSend");
            return (Criteria) this;
        }

        public Criteria andOrderIsSendNotIn(List<Integer> values) {
            addCriterion("order_is_send not in", values, "orderIsSend");
            return (Criteria) this;
        }

        public Criteria andOrderIsSendBetween(Integer value1, Integer value2) {
            addCriterion("order_is_send between", value1, value2, "orderIsSend");
            return (Criteria) this;
        }

        public Criteria andOrderIsSendNotBetween(Integer value1, Integer value2) {
            addCriterion("order_is_send not between", value1, value2, "orderIsSend");
            return (Criteria) this;
        }

        public Criteria andOrderIsFinishIsNull() {
            addCriterion("order_is_finish is null");
            return (Criteria) this;
        }

        public Criteria andOrderIsFinishIsNotNull() {
            addCriterion("order_is_finish is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIsFinishEqualTo(Integer value) {
            addCriterion("order_is_finish =", value, "orderIsFinish");
            return (Criteria) this;
        }

        public Criteria andOrderIsFinishNotEqualTo(Integer value) {
            addCriterion("order_is_finish <>", value, "orderIsFinish");
            return (Criteria) this;
        }

        public Criteria andOrderIsFinishGreaterThan(Integer value) {
            addCriterion("order_is_finish >", value, "orderIsFinish");
            return (Criteria) this;
        }

        public Criteria andOrderIsFinishGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_is_finish >=", value, "orderIsFinish");
            return (Criteria) this;
        }

        public Criteria andOrderIsFinishLessThan(Integer value) {
            addCriterion("order_is_finish <", value, "orderIsFinish");
            return (Criteria) this;
        }

        public Criteria andOrderIsFinishLessThanOrEqualTo(Integer value) {
            addCriterion("order_is_finish <=", value, "orderIsFinish");
            return (Criteria) this;
        }

        public Criteria andOrderIsFinishIn(List<Integer> values) {
            addCriterion("order_is_finish in", values, "orderIsFinish");
            return (Criteria) this;
        }

        public Criteria andOrderIsFinishNotIn(List<Integer> values) {
            addCriterion("order_is_finish not in", values, "orderIsFinish");
            return (Criteria) this;
        }

        public Criteria andOrderIsFinishBetween(Integer value1, Integer value2) {
            addCriterion("order_is_finish between", value1, value2, "orderIsFinish");
            return (Criteria) this;
        }

        public Criteria andOrderIsFinishNotBetween(Integer value1, Integer value2) {
            addCriterion("order_is_finish not between", value1, value2, "orderIsFinish");
            return (Criteria) this;
        }

        public Criteria andOrderOrdersTimeIsNull() {
            addCriterion("order_orders_time is null");
            return (Criteria) this;
        }

        public Criteria andOrderOrdersTimeIsNotNull() {
            addCriterion("order_orders_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrderOrdersTimeEqualTo(Date value) {
            addCriterion("order_orders_time =", value, "orderOrdersTime");
            return (Criteria) this;
        }

        public Criteria andOrderOrdersTimeNotEqualTo(Date value) {
            addCriterion("order_orders_time <>", value, "orderOrdersTime");
            return (Criteria) this;
        }

        public Criteria andOrderOrdersTimeGreaterThan(Date value) {
            addCriterion("order_orders_time >", value, "orderOrdersTime");
            return (Criteria) this;
        }

        public Criteria andOrderOrdersTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("order_orders_time >=", value, "orderOrdersTime");
            return (Criteria) this;
        }

        public Criteria andOrderOrdersTimeLessThan(Date value) {
            addCriterion("order_orders_time <", value, "orderOrdersTime");
            return (Criteria) this;
        }

        public Criteria andOrderOrdersTimeLessThanOrEqualTo(Date value) {
            addCriterion("order_orders_time <=", value, "orderOrdersTime");
            return (Criteria) this;
        }

        public Criteria andOrderOrdersTimeIn(List<Date> values) {
            addCriterion("order_orders_time in", values, "orderOrdersTime");
            return (Criteria) this;
        }

        public Criteria andOrderOrdersTimeNotIn(List<Date> values) {
            addCriterion("order_orders_time not in", values, "orderOrdersTime");
            return (Criteria) this;
        }

        public Criteria andOrderOrdersTimeBetween(Date value1, Date value2) {
            addCriterion("order_orders_time between", value1, value2, "orderOrdersTime");
            return (Criteria) this;
        }

        public Criteria andOrderOrdersTimeNotBetween(Date value1, Date value2) {
            addCriterion("order_orders_time not between", value1, value2, "orderOrdersTime");
            return (Criteria) this;
        }

        public Criteria andOrderFinishTimeIsNull() {
            addCriterion("order_finish_time is null");
            return (Criteria) this;
        }

        public Criteria andOrderFinishTimeIsNotNull() {
            addCriterion("order_finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrderFinishTimeEqualTo(Date value) {
            addCriterion("order_finish_time =", value, "orderFinishTime");
            return (Criteria) this;
        }

        public Criteria andOrderFinishTimeNotEqualTo(Date value) {
            addCriterion("order_finish_time <>", value, "orderFinishTime");
            return (Criteria) this;
        }

        public Criteria andOrderFinishTimeGreaterThan(Date value) {
            addCriterion("order_finish_time >", value, "orderFinishTime");
            return (Criteria) this;
        }

        public Criteria andOrderFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("order_finish_time >=", value, "orderFinishTime");
            return (Criteria) this;
        }

        public Criteria andOrderFinishTimeLessThan(Date value) {
            addCriterion("order_finish_time <", value, "orderFinishTime");
            return (Criteria) this;
        }

        public Criteria andOrderFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("order_finish_time <=", value, "orderFinishTime");
            return (Criteria) this;
        }

        public Criteria andOrderFinishTimeIn(List<Date> values) {
            addCriterion("order_finish_time in", values, "orderFinishTime");
            return (Criteria) this;
        }

        public Criteria andOrderFinishTimeNotIn(List<Date> values) {
            addCriterion("order_finish_time not in", values, "orderFinishTime");
            return (Criteria) this;
        }

        public Criteria andOrderFinishTimeBetween(Date value1, Date value2) {
            addCriterion("order_finish_time between", value1, value2, "orderFinishTime");
            return (Criteria) this;
        }

        public Criteria andOrderFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("order_finish_time not between", value1, value2, "orderFinishTime");
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