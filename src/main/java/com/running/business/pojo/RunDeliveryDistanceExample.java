package com.running.business.pojo;

import java.util.ArrayList;
import java.util.List;

public class RunDeliveryDistanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunDeliveryDistanceExample() {
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

        public Criteria andSendDistanceIsNull() {
            addCriterion("send_distance is null");
            return (Criteria) this;
        }

        public Criteria andSendDistanceIsNotNull() {
            addCriterion("send_distance is not null");
            return (Criteria) this;
        }

        public Criteria andSendDistanceEqualTo(Double value) {
            addCriterion("send_distance =", value, "sendDistance");
            return (Criteria) this;
        }

        public Criteria andSendDistanceNotEqualTo(Double value) {
            addCriterion("send_distance <>", value, "sendDistance");
            return (Criteria) this;
        }

        public Criteria andSendDistanceGreaterThan(Double value) {
            addCriterion("send_distance >", value, "sendDistance");
            return (Criteria) this;
        }

        public Criteria andSendDistanceGreaterThanOrEqualTo(Double value) {
            addCriterion("send_distance >=", value, "sendDistance");
            return (Criteria) this;
        }

        public Criteria andSendDistanceLessThan(Double value) {
            addCriterion("send_distance <", value, "sendDistance");
            return (Criteria) this;
        }

        public Criteria andSendDistanceLessThanOrEqualTo(Double value) {
            addCriterion("send_distance <=", value, "sendDistance");
            return (Criteria) this;
        }

        public Criteria andSendDistanceIn(List<Double> values) {
            addCriterion("send_distance in", values, "sendDistance");
            return (Criteria) this;
        }

        public Criteria andSendDistanceNotIn(List<Double> values) {
            addCriterion("send_distance not in", values, "sendDistance");
            return (Criteria) this;
        }

        public Criteria andSendDistanceBetween(Double value1, Double value2) {
            addCriterion("send_distance between", value1, value2, "sendDistance");
            return (Criteria) this;
        }

        public Criteria andSendDistanceNotBetween(Double value1, Double value2) {
            addCriterion("send_distance not between", value1, value2, "sendDistance");
            return (Criteria) this;
        }

        public Criteria andDeliveryDistanceIsNull() {
            addCriterion("delivery_distance is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryDistanceIsNotNull() {
            addCriterion("delivery_distance is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryDistanceEqualTo(Double value) {
            addCriterion("delivery_distance =", value, "deliveryDistance");
            return (Criteria) this;
        }

        public Criteria andDeliveryDistanceNotEqualTo(Double value) {
            addCriterion("delivery_distance <>", value, "deliveryDistance");
            return (Criteria) this;
        }

        public Criteria andDeliveryDistanceGreaterThan(Double value) {
            addCriterion("delivery_distance >", value, "deliveryDistance");
            return (Criteria) this;
        }

        public Criteria andDeliveryDistanceGreaterThanOrEqualTo(Double value) {
            addCriterion("delivery_distance >=", value, "deliveryDistance");
            return (Criteria) this;
        }

        public Criteria andDeliveryDistanceLessThan(Double value) {
            addCriterion("delivery_distance <", value, "deliveryDistance");
            return (Criteria) this;
        }

        public Criteria andDeliveryDistanceLessThanOrEqualTo(Double value) {
            addCriterion("delivery_distance <=", value, "deliveryDistance");
            return (Criteria) this;
        }

        public Criteria andDeliveryDistanceIn(List<Double> values) {
            addCriterion("delivery_distance in", values, "deliveryDistance");
            return (Criteria) this;
        }

        public Criteria andDeliveryDistanceNotIn(List<Double> values) {
            addCriterion("delivery_distance not in", values, "deliveryDistance");
            return (Criteria) this;
        }

        public Criteria andDeliveryDistanceBetween(Double value1, Double value2) {
            addCriterion("delivery_distance between", value1, value2, "deliveryDistance");
            return (Criteria) this;
        }

        public Criteria andDeliveryDistanceNotBetween(Double value1, Double value2) {
            addCriterion("delivery_distance not between", value1, value2, "deliveryDistance");
            return (Criteria) this;
        }

        public Criteria andViewOrderDistanceIsNull() {
            addCriterion("view_order_distance is null");
            return (Criteria) this;
        }

        public Criteria andViewOrderDistanceIsNotNull() {
            addCriterion("view_order_distance is not null");
            return (Criteria) this;
        }

        public Criteria andViewOrderDistanceEqualTo(Double value) {
            addCriterion("view_order_distance =", value, "viewOrderDistance");
            return (Criteria) this;
        }

        public Criteria andViewOrderDistanceNotEqualTo(Double value) {
            addCriterion("view_order_distance <>", value, "viewOrderDistance");
            return (Criteria) this;
        }

        public Criteria andViewOrderDistanceGreaterThan(Double value) {
            addCriterion("view_order_distance >", value, "viewOrderDistance");
            return (Criteria) this;
        }

        public Criteria andViewOrderDistanceGreaterThanOrEqualTo(Double value) {
            addCriterion("view_order_distance >=", value, "viewOrderDistance");
            return (Criteria) this;
        }

        public Criteria andViewOrderDistanceLessThan(Double value) {
            addCriterion("view_order_distance <", value, "viewOrderDistance");
            return (Criteria) this;
        }

        public Criteria andViewOrderDistanceLessThanOrEqualTo(Double value) {
            addCriterion("view_order_distance <=", value, "viewOrderDistance");
            return (Criteria) this;
        }

        public Criteria andViewOrderDistanceIn(List<Double> values) {
            addCriterion("view_order_distance in", values, "viewOrderDistance");
            return (Criteria) this;
        }

        public Criteria andViewOrderDistanceNotIn(List<Double> values) {
            addCriterion("view_order_distance not in", values, "viewOrderDistance");
            return (Criteria) this;
        }

        public Criteria andViewOrderDistanceBetween(Double value1, Double value2) {
            addCriterion("view_order_distance between", value1, value2, "viewOrderDistance");
            return (Criteria) this;
        }

        public Criteria andViewOrderDistanceNotBetween(Double value1, Double value2) {
            addCriterion("view_order_distance not between", value1, value2, "viewOrderDistance");
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