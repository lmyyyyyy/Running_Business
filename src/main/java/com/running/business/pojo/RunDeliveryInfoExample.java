package com.running.business.pojo;

import java.util.ArrayList;
import java.util.List;

public class RunDeliveryInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunDeliveryInfoExample() {
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

        public Criteria andDeliveryPhotoIsNull() {
            addCriterion("delivery_photo is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoIsNotNull() {
            addCriterion("delivery_photo is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoEqualTo(String value) {
            addCriterion("delivery_photo =", value, "deliveryPhoto");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoNotEqualTo(String value) {
            addCriterion("delivery_photo <>", value, "deliveryPhoto");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoGreaterThan(String value) {
            addCriterion("delivery_photo >", value, "deliveryPhoto");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_photo >=", value, "deliveryPhoto");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoLessThan(String value) {
            addCriterion("delivery_photo <", value, "deliveryPhoto");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoLessThanOrEqualTo(String value) {
            addCriterion("delivery_photo <=", value, "deliveryPhoto");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoLike(String value) {
            addCriterion("delivery_photo like", value, "deliveryPhoto");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoNotLike(String value) {
            addCriterion("delivery_photo not like", value, "deliveryPhoto");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoIn(List<String> values) {
            addCriterion("delivery_photo in", values, "deliveryPhoto");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoNotIn(List<String> values) {
            addCriterion("delivery_photo not in", values, "deliveryPhoto");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoBetween(String value1, String value2) {
            addCriterion("delivery_photo between", value1, value2, "deliveryPhoto");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhotoNotBetween(String value1, String value2) {
            addCriterion("delivery_photo not between", value1, value2, "deliveryPhoto");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameIsNull() {
            addCriterion("delivery_name is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameIsNotNull() {
            addCriterion("delivery_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameEqualTo(String value) {
            addCriterion("delivery_name =", value, "deliveryName");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameNotEqualTo(String value) {
            addCriterion("delivery_name <>", value, "deliveryName");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameGreaterThan(String value) {
            addCriterion("delivery_name >", value, "deliveryName");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_name >=", value, "deliveryName");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameLessThan(String value) {
            addCriterion("delivery_name <", value, "deliveryName");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameLessThanOrEqualTo(String value) {
            addCriterion("delivery_name <=", value, "deliveryName");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameLike(String value) {
            addCriterion("delivery_name like", value, "deliveryName");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameNotLike(String value) {
            addCriterion("delivery_name not like", value, "deliveryName");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameIn(List<String> values) {
            addCriterion("delivery_name in", values, "deliveryName");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameNotIn(List<String> values) {
            addCriterion("delivery_name not in", values, "deliveryName");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameBetween(String value1, String value2) {
            addCriterion("delivery_name between", value1, value2, "deliveryName");
            return (Criteria) this;
        }

        public Criteria andDeliveryNameNotBetween(String value1, String value2) {
            addCriterion("delivery_name not between", value1, value2, "deliveryName");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardIsNull() {
            addCriterion("delivery_card is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardIsNotNull() {
            addCriterion("delivery_card is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardEqualTo(String value) {
            addCriterion("delivery_card =", value, "deliveryCard");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardNotEqualTo(String value) {
            addCriterion("delivery_card <>", value, "deliveryCard");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardGreaterThan(String value) {
            addCriterion("delivery_card >", value, "deliveryCard");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_card >=", value, "deliveryCard");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardLessThan(String value) {
            addCriterion("delivery_card <", value, "deliveryCard");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardLessThanOrEqualTo(String value) {
            addCriterion("delivery_card <=", value, "deliveryCard");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardLike(String value) {
            addCriterion("delivery_card like", value, "deliveryCard");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardNotLike(String value) {
            addCriterion("delivery_card not like", value, "deliveryCard");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardIn(List<String> values) {
            addCriterion("delivery_card in", values, "deliveryCard");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardNotIn(List<String> values) {
            addCriterion("delivery_card not in", values, "deliveryCard");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardBetween(String value1, String value2) {
            addCriterion("delivery_card between", value1, value2, "deliveryCard");
            return (Criteria) this;
        }

        public Criteria andDeliveryCardNotBetween(String value1, String value2) {
            addCriterion("delivery_card not between", value1, value2, "deliveryCard");
            return (Criteria) this;
        }

        public Criteria andDeliveryGenderIsNull() {
            addCriterion("delivery_gender is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryGenderIsNotNull() {
            addCriterion("delivery_gender is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryGenderEqualTo(Integer value) {
            addCriterion("delivery_gender =", value, "deliveryGender");
            return (Criteria) this;
        }

        public Criteria andDeliveryGenderNotEqualTo(Integer value) {
            addCriterion("delivery_gender <>", value, "deliveryGender");
            return (Criteria) this;
        }

        public Criteria andDeliveryGenderGreaterThan(Integer value) {
            addCriterion("delivery_gender >", value, "deliveryGender");
            return (Criteria) this;
        }

        public Criteria andDeliveryGenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("delivery_gender >=", value, "deliveryGender");
            return (Criteria) this;
        }

        public Criteria andDeliveryGenderLessThan(Integer value) {
            addCriterion("delivery_gender <", value, "deliveryGender");
            return (Criteria) this;
        }

        public Criteria andDeliveryGenderLessThanOrEqualTo(Integer value) {
            addCriterion("delivery_gender <=", value, "deliveryGender");
            return (Criteria) this;
        }

        public Criteria andDeliveryGenderIn(List<Integer> values) {
            addCriterion("delivery_gender in", values, "deliveryGender");
            return (Criteria) this;
        }

        public Criteria andDeliveryGenderNotIn(List<Integer> values) {
            addCriterion("delivery_gender not in", values, "deliveryGender");
            return (Criteria) this;
        }

        public Criteria andDeliveryGenderBetween(Integer value1, Integer value2) {
            addCriterion("delivery_gender between", value1, value2, "deliveryGender");
            return (Criteria) this;
        }

        public Criteria andDeliveryGenderNotBetween(Integer value1, Integer value2) {
            addCriterion("delivery_gender not between", value1, value2, "deliveryGender");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneIsNull() {
            addCriterion("delivery_phone is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneIsNotNull() {
            addCriterion("delivery_phone is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneEqualTo(String value) {
            addCriterion("delivery_phone =", value, "deliveryPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneNotEqualTo(String value) {
            addCriterion("delivery_phone <>", value, "deliveryPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneGreaterThan(String value) {
            addCriterion("delivery_phone >", value, "deliveryPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_phone >=", value, "deliveryPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneLessThan(String value) {
            addCriterion("delivery_phone <", value, "deliveryPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneLessThanOrEqualTo(String value) {
            addCriterion("delivery_phone <=", value, "deliveryPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneLike(String value) {
            addCriterion("delivery_phone like", value, "deliveryPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneNotLike(String value) {
            addCriterion("delivery_phone not like", value, "deliveryPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneIn(List<String> values) {
            addCriterion("delivery_phone in", values, "deliveryPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneNotIn(List<String> values) {
            addCriterion("delivery_phone not in", values, "deliveryPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneBetween(String value1, String value2) {
            addCriterion("delivery_phone between", value1, value2, "deliveryPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryPhoneNotBetween(String value1, String value2) {
            addCriterion("delivery_phone not between", value1, value2, "deliveryPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressIsNull() {
            addCriterion("delivery_address is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressIsNotNull() {
            addCriterion("delivery_address is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressEqualTo(String value) {
            addCriterion("delivery_address =", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotEqualTo(String value) {
            addCriterion("delivery_address <>", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressGreaterThan(String value) {
            addCriterion("delivery_address >", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_address >=", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressLessThan(String value) {
            addCriterion("delivery_address <", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressLessThanOrEqualTo(String value) {
            addCriterion("delivery_address <=", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressLike(String value) {
            addCriterion("delivery_address like", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotLike(String value) {
            addCriterion("delivery_address not like", value, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressIn(List<String> values) {
            addCriterion("delivery_address in", values, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotIn(List<String> values) {
            addCriterion("delivery_address not in", values, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressBetween(String value1, String value2) {
            addCriterion("delivery_address between", value1, value2, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryAddressNotBetween(String value1, String value2) {
            addCriterion("delivery_address not between", value1, value2, "deliveryAddress");
            return (Criteria) this;
        }

        public Criteria andDeliveryPointIsNull() {
            addCriterion("delivery_point is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryPointIsNotNull() {
            addCriterion("delivery_point is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryPointEqualTo(Integer value) {
            addCriterion("delivery_point =", value, "deliveryPoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryPointNotEqualTo(Integer value) {
            addCriterion("delivery_point <>", value, "deliveryPoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryPointGreaterThan(Integer value) {
            addCriterion("delivery_point >", value, "deliveryPoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("delivery_point >=", value, "deliveryPoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryPointLessThan(Integer value) {
            addCriterion("delivery_point <", value, "deliveryPoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryPointLessThanOrEqualTo(Integer value) {
            addCriterion("delivery_point <=", value, "deliveryPoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryPointIn(List<Integer> values) {
            addCriterion("delivery_point in", values, "deliveryPoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryPointNotIn(List<Integer> values) {
            addCriterion("delivery_point not in", values, "deliveryPoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryPointBetween(Integer value1, Integer value2) {
            addCriterion("delivery_point between", value1, value2, "deliveryPoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryPointNotBetween(Integer value1, Integer value2) {
            addCriterion("delivery_point not between", value1, value2, "deliveryPoint");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelIsNull() {
            addCriterion("delivery_level is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelIsNotNull() {
            addCriterion("delivery_level is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelEqualTo(String value) {
            addCriterion("delivery_level =", value, "deliveryLevel");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelNotEqualTo(String value) {
            addCriterion("delivery_level <>", value, "deliveryLevel");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelGreaterThan(String value) {
            addCriterion("delivery_level >", value, "deliveryLevel");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_level >=", value, "deliveryLevel");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelLessThan(String value) {
            addCriterion("delivery_level <", value, "deliveryLevel");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelLessThanOrEqualTo(String value) {
            addCriterion("delivery_level <=", value, "deliveryLevel");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelLike(String value) {
            addCriterion("delivery_level like", value, "deliveryLevel");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelNotLike(String value) {
            addCriterion("delivery_level not like", value, "deliveryLevel");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelIn(List<String> values) {
            addCriterion("delivery_level in", values, "deliveryLevel");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelNotIn(List<String> values) {
            addCriterion("delivery_level not in", values, "deliveryLevel");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelBetween(String value1, String value2) {
            addCriterion("delivery_level between", value1, value2, "deliveryLevel");
            return (Criteria) this;
        }

        public Criteria andDeliveryLevelNotBetween(String value1, String value2) {
            addCriterion("delivery_level not between", value1, value2, "deliveryLevel");
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