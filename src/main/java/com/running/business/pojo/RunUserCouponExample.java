package com.running.business.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunUserCouponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunUserCouponExample() {
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

        public Criteria andUserTitleIsNull() {
            addCriterion("user_title is null");
            return (Criteria) this;
        }

        public Criteria andUserTitleIsNotNull() {
            addCriterion("user_title is not null");
            return (Criteria) this;
        }

        public Criteria andUserTitleEqualTo(String value) {
            addCriterion("user_title =", value, "userTitle");
            return (Criteria) this;
        }

        public Criteria andUserTitleNotEqualTo(String value) {
            addCriterion("user_title <>", value, "userTitle");
            return (Criteria) this;
        }

        public Criteria andUserTitleGreaterThan(String value) {
            addCriterion("user_title >", value, "userTitle");
            return (Criteria) this;
        }

        public Criteria andUserTitleGreaterThanOrEqualTo(String value) {
            addCriterion("user_title >=", value, "userTitle");
            return (Criteria) this;
        }

        public Criteria andUserTitleLessThan(String value) {
            addCriterion("user_title <", value, "userTitle");
            return (Criteria) this;
        }

        public Criteria andUserTitleLessThanOrEqualTo(String value) {
            addCriterion("user_title <=", value, "userTitle");
            return (Criteria) this;
        }

        public Criteria andUserTitleLike(String value) {
            addCriterion("user_title like", value, "userTitle");
            return (Criteria) this;
        }

        public Criteria andUserTitleNotLike(String value) {
            addCriterion("user_title not like", value, "userTitle");
            return (Criteria) this;
        }

        public Criteria andUserTitleIn(List<String> values) {
            addCriterion("user_title in", values, "userTitle");
            return (Criteria) this;
        }

        public Criteria andUserTitleNotIn(List<String> values) {
            addCriterion("user_title not in", values, "userTitle");
            return (Criteria) this;
        }

        public Criteria andUserTitleBetween(String value1, String value2) {
            addCriterion("user_title between", value1, value2, "userTitle");
            return (Criteria) this;
        }

        public Criteria andUserTitleNotBetween(String value1, String value2) {
            addCriterion("user_title not between", value1, value2, "userTitle");
            return (Criteria) this;
        }

        public Criteria andUserContentIsNull() {
            addCriterion("user_content is null");
            return (Criteria) this;
        }

        public Criteria andUserContentIsNotNull() {
            addCriterion("user_content is not null");
            return (Criteria) this;
        }

        public Criteria andUserContentEqualTo(String value) {
            addCriterion("user_content =", value, "userContent");
            return (Criteria) this;
        }

        public Criteria andUserContentNotEqualTo(String value) {
            addCriterion("user_content <>", value, "userContent");
            return (Criteria) this;
        }

        public Criteria andUserContentGreaterThan(String value) {
            addCriterion("user_content >", value, "userContent");
            return (Criteria) this;
        }

        public Criteria andUserContentGreaterThanOrEqualTo(String value) {
            addCriterion("user_content >=", value, "userContent");
            return (Criteria) this;
        }

        public Criteria andUserContentLessThan(String value) {
            addCriterion("user_content <", value, "userContent");
            return (Criteria) this;
        }

        public Criteria andUserContentLessThanOrEqualTo(String value) {
            addCriterion("user_content <=", value, "userContent");
            return (Criteria) this;
        }

        public Criteria andUserContentLike(String value) {
            addCriterion("user_content like", value, "userContent");
            return (Criteria) this;
        }

        public Criteria andUserContentNotLike(String value) {
            addCriterion("user_content not like", value, "userContent");
            return (Criteria) this;
        }

        public Criteria andUserContentIn(List<String> values) {
            addCriterion("user_content in", values, "userContent");
            return (Criteria) this;
        }

        public Criteria andUserContentNotIn(List<String> values) {
            addCriterion("user_content not in", values, "userContent");
            return (Criteria) this;
        }

        public Criteria andUserContentBetween(String value1, String value2) {
            addCriterion("user_content between", value1, value2, "userContent");
            return (Criteria) this;
        }

        public Criteria andUserContentNotBetween(String value1, String value2) {
            addCriterion("user_content not between", value1, value2, "userContent");
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

        public Criteria andUserFullIsNull() {
            addCriterion("user_full is null");
            return (Criteria) this;
        }

        public Criteria andUserFullIsNotNull() {
            addCriterion("user_full is not null");
            return (Criteria) this;
        }

        public Criteria andUserFullEqualTo(Double value) {
            addCriterion("user_full =", value, "userFull");
            return (Criteria) this;
        }

        public Criteria andUserFullNotEqualTo(Double value) {
            addCriterion("user_full <>", value, "userFull");
            return (Criteria) this;
        }

        public Criteria andUserFullGreaterThan(Double value) {
            addCriterion("user_full >", value, "userFull");
            return (Criteria) this;
        }

        public Criteria andUserFullGreaterThanOrEqualTo(Double value) {
            addCriterion("user_full >=", value, "userFull");
            return (Criteria) this;
        }

        public Criteria andUserFullLessThan(Double value) {
            addCriterion("user_full <", value, "userFull");
            return (Criteria) this;
        }

        public Criteria andUserFullLessThanOrEqualTo(Double value) {
            addCriterion("user_full <=", value, "userFull");
            return (Criteria) this;
        }

        public Criteria andUserFullIn(List<Double> values) {
            addCriterion("user_full in", values, "userFull");
            return (Criteria) this;
        }

        public Criteria andUserFullNotIn(List<Double> values) {
            addCriterion("user_full not in", values, "userFull");
            return (Criteria) this;
        }

        public Criteria andUserFullBetween(Double value1, Double value2) {
            addCriterion("user_full between", value1, value2, "userFull");
            return (Criteria) this;
        }

        public Criteria andUserFullNotBetween(Double value1, Double value2) {
            addCriterion("user_full not between", value1, value2, "userFull");
            return (Criteria) this;
        }

        public Criteria andUserSubtractIsNull() {
            addCriterion("user_subtract is null");
            return (Criteria) this;
        }

        public Criteria andUserSubtractIsNotNull() {
            addCriterion("user_subtract is not null");
            return (Criteria) this;
        }

        public Criteria andUserSubtractEqualTo(Double value) {
            addCriterion("user_subtract =", value, "userSubtract");
            return (Criteria) this;
        }

        public Criteria andUserSubtractNotEqualTo(Double value) {
            addCriterion("user_subtract <>", value, "userSubtract");
            return (Criteria) this;
        }

        public Criteria andUserSubtractGreaterThan(Double value) {
            addCriterion("user_subtract >", value, "userSubtract");
            return (Criteria) this;
        }

        public Criteria andUserSubtractGreaterThanOrEqualTo(Double value) {
            addCriterion("user_subtract >=", value, "userSubtract");
            return (Criteria) this;
        }

        public Criteria andUserSubtractLessThan(Double value) {
            addCriterion("user_subtract <", value, "userSubtract");
            return (Criteria) this;
        }

        public Criteria andUserSubtractLessThanOrEqualTo(Double value) {
            addCriterion("user_subtract <=", value, "userSubtract");
            return (Criteria) this;
        }

        public Criteria andUserSubtractIn(List<Double> values) {
            addCriterion("user_subtract in", values, "userSubtract");
            return (Criteria) this;
        }

        public Criteria andUserSubtractNotIn(List<Double> values) {
            addCriterion("user_subtract not in", values, "userSubtract");
            return (Criteria) this;
        }

        public Criteria andUserSubtractBetween(Double value1, Double value2) {
            addCriterion("user_subtract between", value1, value2, "userSubtract");
            return (Criteria) this;
        }

        public Criteria andUserSubtractNotBetween(Double value1, Double value2) {
            addCriterion("user_subtract not between", value1, value2, "userSubtract");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNull() {
            addCriterion("user_status is null");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNotNull() {
            addCriterion("user_status is not null");
            return (Criteria) this;
        }

        public Criteria andUserStatusEqualTo(Integer value) {
            addCriterion("user_status =", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotEqualTo(Integer value) {
            addCriterion("user_status <>", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThan(Integer value) {
            addCriterion("user_status >", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_status >=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThan(Integer value) {
            addCriterion("user_status <", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThanOrEqualTo(Integer value) {
            addCriterion("user_status <=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusIn(List<Integer> values) {
            addCriterion("user_status in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotIn(List<Integer> values) {
            addCriterion("user_status not in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusBetween(Integer value1, Integer value2) {
            addCriterion("user_status between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("user_status not between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserExpiredtimeIsNull() {
            addCriterion("user_expiredtime is null");
            return (Criteria) this;
        }

        public Criteria andUserExpiredtimeIsNotNull() {
            addCriterion("user_expiredtime is not null");
            return (Criteria) this;
        }

        public Criteria andUserExpiredtimeEqualTo(Date value) {
            addCriterion("user_expiredtime =", value, "userExpiredtime");
            return (Criteria) this;
        }

        public Criteria andUserExpiredtimeNotEqualTo(Date value) {
            addCriterion("user_expiredtime <>", value, "userExpiredtime");
            return (Criteria) this;
        }

        public Criteria andUserExpiredtimeGreaterThan(Date value) {
            addCriterion("user_expiredtime >", value, "userExpiredtime");
            return (Criteria) this;
        }

        public Criteria andUserExpiredtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("user_expiredtime >=", value, "userExpiredtime");
            return (Criteria) this;
        }

        public Criteria andUserExpiredtimeLessThan(Date value) {
            addCriterion("user_expiredtime <", value, "userExpiredtime");
            return (Criteria) this;
        }

        public Criteria andUserExpiredtimeLessThanOrEqualTo(Date value) {
            addCriterion("user_expiredtime <=", value, "userExpiredtime");
            return (Criteria) this;
        }

        public Criteria andUserExpiredtimeIn(List<Date> values) {
            addCriterion("user_expiredtime in", values, "userExpiredtime");
            return (Criteria) this;
        }

        public Criteria andUserExpiredtimeNotIn(List<Date> values) {
            addCriterion("user_expiredtime not in", values, "userExpiredtime");
            return (Criteria) this;
        }

        public Criteria andUserExpiredtimeBetween(Date value1, Date value2) {
            addCriterion("user_expiredtime between", value1, value2, "userExpiredtime");
            return (Criteria) this;
        }

        public Criteria andUserExpiredtimeNotBetween(Date value1, Date value2) {
            addCriterion("user_expiredtime not between", value1, value2, "userExpiredtime");
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