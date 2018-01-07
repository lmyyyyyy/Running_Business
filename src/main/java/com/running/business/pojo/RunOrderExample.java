package com.running.business.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RunOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RunOrderExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andGoodsIsNull() {
            addCriterion("goods is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsNotNull() {
            addCriterion("goods is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsEqualTo(String value) {
            addCriterion("goods =", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotEqualTo(String value) {
            addCriterion("goods <>", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsGreaterThan(String value) {
            addCriterion("goods >", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsGreaterThanOrEqualTo(String value) {
            addCriterion("goods >=", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLessThan(String value) {
            addCriterion("goods <", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLessThanOrEqualTo(String value) {
            addCriterion("goods <=", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsLike(String value) {
            addCriterion("goods like", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotLike(String value) {
            addCriterion("goods not like", value, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsIn(List<String> values) {
            addCriterion("goods in", values, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotIn(List<String> values) {
            addCriterion("goods not in", values, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsBetween(String value1, String value2) {
            addCriterion("goods between", value1, value2, "goods");
            return (Criteria) this;
        }

        public Criteria andGoodsNotBetween(String value1, String value2) {
            addCriterion("goods not between", value1, value2, "goods");
            return (Criteria) this;
        }

        public Criteria andSourceAddressIsNull() {
            addCriterion("source_address is null");
            return (Criteria) this;
        }

        public Criteria andSourceAddressIsNotNull() {
            addCriterion("source_address is not null");
            return (Criteria) this;
        }

        public Criteria andSourceAddressEqualTo(String value) {
            addCriterion("source_address =", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressNotEqualTo(String value) {
            addCriterion("source_address <>", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressGreaterThan(String value) {
            addCriterion("source_address >", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressGreaterThanOrEqualTo(String value) {
            addCriterion("source_address >=", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressLessThan(String value) {
            addCriterion("source_address <", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressLessThanOrEqualTo(String value) {
            addCriterion("source_address <=", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressLike(String value) {
            addCriterion("source_address like", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressNotLike(String value) {
            addCriterion("source_address not like", value, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressIn(List<String> values) {
            addCriterion("source_address in", values, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressNotIn(List<String> values) {
            addCriterion("source_address not in", values, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressBetween(String value1, String value2) {
            addCriterion("source_address between", value1, value2, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceAddressNotBetween(String value1, String value2) {
            addCriterion("source_address not between", value1, value2, "sourceAddress");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressIsNull() {
            addCriterion("source_remark_address is null");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressIsNotNull() {
            addCriterion("source_remark_address is not null");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressEqualTo(String value) {
            addCriterion("source_remark_address =", value, "sourceRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressNotEqualTo(String value) {
            addCriterion("source_remark_address <>", value, "sourceRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressGreaterThan(String value) {
            addCriterion("source_remark_address >", value, "sourceRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressGreaterThanOrEqualTo(String value) {
            addCriterion("source_remark_address >=", value, "sourceRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressLessThan(String value) {
            addCriterion("source_remark_address <", value, "sourceRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressLessThanOrEqualTo(String value) {
            addCriterion("source_remark_address <=", value, "sourceRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressLike(String value) {
            addCriterion("source_remark_address like", value, "sourceRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressNotLike(String value) {
            addCriterion("source_remark_address not like", value, "sourceRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressIn(List<String> values) {
            addCriterion("source_remark_address in", values, "sourceRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressNotIn(List<String> values) {
            addCriterion("source_remark_address not in", values, "sourceRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressBetween(String value1, String value2) {
            addCriterion("source_remark_address between", value1, value2, "sourceRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andSourceRemarkAddressNotBetween(String value1, String value2) {
            addCriterion("source_remark_address not between", value1, value2, "sourceRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneIsNull() {
            addCriterion("source_phone is null");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneIsNotNull() {
            addCriterion("source_phone is not null");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneEqualTo(String value) {
            addCriterion("source_phone =", value, "sourcePhone");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneNotEqualTo(String value) {
            addCriterion("source_phone <>", value, "sourcePhone");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneGreaterThan(String value) {
            addCriterion("source_phone >", value, "sourcePhone");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("source_phone >=", value, "sourcePhone");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneLessThan(String value) {
            addCriterion("source_phone <", value, "sourcePhone");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneLessThanOrEqualTo(String value) {
            addCriterion("source_phone <=", value, "sourcePhone");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneLike(String value) {
            addCriterion("source_phone like", value, "sourcePhone");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneNotLike(String value) {
            addCriterion("source_phone not like", value, "sourcePhone");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneIn(List<String> values) {
            addCriterion("source_phone in", values, "sourcePhone");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneNotIn(List<String> values) {
            addCriterion("source_phone not in", values, "sourcePhone");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneBetween(String value1, String value2) {
            addCriterion("source_phone between", value1, value2, "sourcePhone");
            return (Criteria) this;
        }

        public Criteria andSourcePhoneNotBetween(String value1, String value2) {
            addCriterion("source_phone not between", value1, value2, "sourcePhone");
            return (Criteria) this;
        }

        public Criteria andTargetAddressIsNull() {
            addCriterion("target_address is null");
            return (Criteria) this;
        }

        public Criteria andTargetAddressIsNotNull() {
            addCriterion("target_address is not null");
            return (Criteria) this;
        }

        public Criteria andTargetAddressEqualTo(String value) {
            addCriterion("target_address =", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressNotEqualTo(String value) {
            addCriterion("target_address <>", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressGreaterThan(String value) {
            addCriterion("target_address >", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressGreaterThanOrEqualTo(String value) {
            addCriterion("target_address >=", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressLessThan(String value) {
            addCriterion("target_address <", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressLessThanOrEqualTo(String value) {
            addCriterion("target_address <=", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressLike(String value) {
            addCriterion("target_address like", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressNotLike(String value) {
            addCriterion("target_address not like", value, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressIn(List<String> values) {
            addCriterion("target_address in", values, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressNotIn(List<String> values) {
            addCriterion("target_address not in", values, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressBetween(String value1, String value2) {
            addCriterion("target_address between", value1, value2, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetAddressNotBetween(String value1, String value2) {
            addCriterion("target_address not between", value1, value2, "targetAddress");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressIsNull() {
            addCriterion("target_remark_address is null");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressIsNotNull() {
            addCriterion("target_remark_address is not null");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressEqualTo(String value) {
            addCriterion("target_remark_address =", value, "targetRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressNotEqualTo(String value) {
            addCriterion("target_remark_address <>", value, "targetRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressGreaterThan(String value) {
            addCriterion("target_remark_address >", value, "targetRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressGreaterThanOrEqualTo(String value) {
            addCriterion("target_remark_address >=", value, "targetRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressLessThan(String value) {
            addCriterion("target_remark_address <", value, "targetRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressLessThanOrEqualTo(String value) {
            addCriterion("target_remark_address <=", value, "targetRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressLike(String value) {
            addCriterion("target_remark_address like", value, "targetRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressNotLike(String value) {
            addCriterion("target_remark_address not like", value, "targetRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressIn(List<String> values) {
            addCriterion("target_remark_address in", values, "targetRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressNotIn(List<String> values) {
            addCriterion("target_remark_address not in", values, "targetRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressBetween(String value1, String value2) {
            addCriterion("target_remark_address between", value1, value2, "targetRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andTargetRemarkAddressNotBetween(String value1, String value2) {
            addCriterion("target_remark_address not between", value1, value2, "targetRemarkAddress");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneIsNull() {
            addCriterion("target_phone is null");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneIsNotNull() {
            addCriterion("target_phone is not null");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneEqualTo(String value) {
            addCriterion("target_phone =", value, "targetPhone");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneNotEqualTo(String value) {
            addCriterion("target_phone <>", value, "targetPhone");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneGreaterThan(String value) {
            addCriterion("target_phone >", value, "targetPhone");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("target_phone >=", value, "targetPhone");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneLessThan(String value) {
            addCriterion("target_phone <", value, "targetPhone");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneLessThanOrEqualTo(String value) {
            addCriterion("target_phone <=", value, "targetPhone");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneLike(String value) {
            addCriterion("target_phone like", value, "targetPhone");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneNotLike(String value) {
            addCriterion("target_phone not like", value, "targetPhone");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneIn(List<String> values) {
            addCriterion("target_phone in", values, "targetPhone");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneNotIn(List<String> values) {
            addCriterion("target_phone not in", values, "targetPhone");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneBetween(String value1, String value2) {
            addCriterion("target_phone between", value1, value2, "targetPhone");
            return (Criteria) this;
        }

        public Criteria andTargetPhoneNotBetween(String value1, String value2) {
            addCriterion("target_phone not between", value1, value2, "targetPhone");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRequireTimeIsNull() {
            addCriterion("require_time is null");
            return (Criteria) this;
        }

        public Criteria andRequireTimeIsNotNull() {
            addCriterion("require_time is not null");
            return (Criteria) this;
        }

        public Criteria andRequireTimeEqualTo(Date value) {
            addCriterion("require_time =", value, "requireTime");
            return (Criteria) this;
        }

        public Criteria andRequireTimeNotEqualTo(Date value) {
            addCriterion("require_time <>", value, "requireTime");
            return (Criteria) this;
        }

        public Criteria andRequireTimeGreaterThan(Date value) {
            addCriterion("require_time >", value, "requireTime");
            return (Criteria) this;
        }

        public Criteria andRequireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("require_time >=", value, "requireTime");
            return (Criteria) this;
        }

        public Criteria andRequireTimeLessThan(Date value) {
            addCriterion("require_time <", value, "requireTime");
            return (Criteria) this;
        }

        public Criteria andRequireTimeLessThanOrEqualTo(Date value) {
            addCriterion("require_time <=", value, "requireTime");
            return (Criteria) this;
        }

        public Criteria andRequireTimeIn(List<Date> values) {
            addCriterion("require_time in", values, "requireTime");
            return (Criteria) this;
        }

        public Criteria andRequireTimeNotIn(List<Date> values) {
            addCriterion("require_time not in", values, "requireTime");
            return (Criteria) this;
        }

        public Criteria andRequireTimeBetween(Date value1, Date value2) {
            addCriterion("require_time between", value1, value2, "requireTime");
            return (Criteria) this;
        }

        public Criteria andRequireTimeNotBetween(Date value1, Date value2) {
            addCriterion("require_time not between", value1, value2, "requireTime");
            return (Criteria) this;
        }

        public Criteria andTimeLongIsNull() {
            addCriterion("time_long is null");
            return (Criteria) this;
        }

        public Criteria andTimeLongIsNotNull() {
            addCriterion("time_long is not null");
            return (Criteria) this;
        }

        public Criteria andTimeLongEqualTo(Long value) {
            addCriterion("time_long =", value, "timeLong");
            return (Criteria) this;
        }

        public Criteria andTimeLongNotEqualTo(Long value) {
            addCriterion("time_long <>", value, "timeLong");
            return (Criteria) this;
        }

        public Criteria andTimeLongGreaterThan(Long value) {
            addCriterion("time_long >", value, "timeLong");
            return (Criteria) this;
        }

        public Criteria andTimeLongGreaterThanOrEqualTo(Long value) {
            addCriterion("time_long >=", value, "timeLong");
            return (Criteria) this;
        }

        public Criteria andTimeLongLessThan(Long value) {
            addCriterion("time_long <", value, "timeLong");
            return (Criteria) this;
        }

        public Criteria andTimeLongLessThanOrEqualTo(Long value) {
            addCriterion("time_long <=", value, "timeLong");
            return (Criteria) this;
        }

        public Criteria andTimeLongIn(List<Long> values) {
            addCriterion("time_long in", values, "timeLong");
            return (Criteria) this;
        }

        public Criteria andTimeLongNotIn(List<Long> values) {
            addCriterion("time_long not in", values, "timeLong");
            return (Criteria) this;
        }

        public Criteria andTimeLongBetween(Long value1, Long value2) {
            addCriterion("time_long between", value1, value2, "timeLong");
            return (Criteria) this;
        }

        public Criteria andTimeLongNotBetween(Long value1, Long value2) {
            addCriterion("time_long not between", value1, value2, "timeLong");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Double value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Double value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Double value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Double value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Double value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Double> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Double> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Double value1, Double value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Double value1, Double value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(Double value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(Double value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(Double value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(Double value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(Double value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<Double> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<Double> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(Double value1, Double value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(Double value1, Double value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNull() {
            addCriterion("distance is null");
            return (Criteria) this;
        }

        public Criteria andDistanceIsNotNull() {
            addCriterion("distance is not null");
            return (Criteria) this;
        }

        public Criteria andDistanceEqualTo(Double value) {
            addCriterion("distance =", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotEqualTo(Double value) {
            addCriterion("distance <>", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThan(Double value) {
            addCriterion("distance >", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceGreaterThanOrEqualTo(Double value) {
            addCriterion("distance >=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThan(Double value) {
            addCriterion("distance <", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceLessThanOrEqualTo(Double value) {
            addCriterion("distance <=", value, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceIn(List<Double> values) {
            addCriterion("distance in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotIn(List<Double> values) {
            addCriterion("distance not in", values, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceBetween(Double value1, Double value2) {
            addCriterion("distance between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andDistanceNotBetween(Double value1, Double value2) {
            addCriterion("distance not between", value1, value2, "distance");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeIsNull() {
            addCriterion("source_longitude is null");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeIsNotNull() {
            addCriterion("source_longitude is not null");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeEqualTo(String value) {
            addCriterion("source_longitude =", value, "sourceLongitude");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeNotEqualTo(String value) {
            addCriterion("source_longitude <>", value, "sourceLongitude");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeGreaterThan(String value) {
            addCriterion("source_longitude >", value, "sourceLongitude");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("source_longitude >=", value, "sourceLongitude");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeLessThan(String value) {
            addCriterion("source_longitude <", value, "sourceLongitude");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeLessThanOrEqualTo(String value) {
            addCriterion("source_longitude <=", value, "sourceLongitude");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeLike(String value) {
            addCriterion("source_longitude like", value, "sourceLongitude");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeNotLike(String value) {
            addCriterion("source_longitude not like", value, "sourceLongitude");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeIn(List<String> values) {
            addCriterion("source_longitude in", values, "sourceLongitude");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeNotIn(List<String> values) {
            addCriterion("source_longitude not in", values, "sourceLongitude");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeBetween(String value1, String value2) {
            addCriterion("source_longitude between", value1, value2, "sourceLongitude");
            return (Criteria) this;
        }

        public Criteria andSourceLongitudeNotBetween(String value1, String value2) {
            addCriterion("source_longitude not between", value1, value2, "sourceLongitude");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeIsNull() {
            addCriterion("source_latitude is null");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeIsNotNull() {
            addCriterion("source_latitude is not null");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeEqualTo(String value) {
            addCriterion("source_latitude =", value, "sourceLatitude");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeNotEqualTo(String value) {
            addCriterion("source_latitude <>", value, "sourceLatitude");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeGreaterThan(String value) {
            addCriterion("source_latitude >", value, "sourceLatitude");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("source_latitude >=", value, "sourceLatitude");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeLessThan(String value) {
            addCriterion("source_latitude <", value, "sourceLatitude");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeLessThanOrEqualTo(String value) {
            addCriterion("source_latitude <=", value, "sourceLatitude");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeLike(String value) {
            addCriterion("source_latitude like", value, "sourceLatitude");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeNotLike(String value) {
            addCriterion("source_latitude not like", value, "sourceLatitude");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeIn(List<String> values) {
            addCriterion("source_latitude in", values, "sourceLatitude");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeNotIn(List<String> values) {
            addCriterion("source_latitude not in", values, "sourceLatitude");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeBetween(String value1, String value2) {
            addCriterion("source_latitude between", value1, value2, "sourceLatitude");
            return (Criteria) this;
        }

        public Criteria andSourceLatitudeNotBetween(String value1, String value2) {
            addCriterion("source_latitude not between", value1, value2, "sourceLatitude");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeIsNull() {
            addCriterion("recv_longitude is null");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeIsNotNull() {
            addCriterion("recv_longitude is not null");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeEqualTo(String value) {
            addCriterion("recv_longitude =", value, "recvLongitude");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeNotEqualTo(String value) {
            addCriterion("recv_longitude <>", value, "recvLongitude");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeGreaterThan(String value) {
            addCriterion("recv_longitude >", value, "recvLongitude");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("recv_longitude >=", value, "recvLongitude");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeLessThan(String value) {
            addCriterion("recv_longitude <", value, "recvLongitude");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeLessThanOrEqualTo(String value) {
            addCriterion("recv_longitude <=", value, "recvLongitude");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeLike(String value) {
            addCriterion("recv_longitude like", value, "recvLongitude");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeNotLike(String value) {
            addCriterion("recv_longitude not like", value, "recvLongitude");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeIn(List<String> values) {
            addCriterion("recv_longitude in", values, "recvLongitude");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeNotIn(List<String> values) {
            addCriterion("recv_longitude not in", values, "recvLongitude");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeBetween(String value1, String value2) {
            addCriterion("recv_longitude between", value1, value2, "recvLongitude");
            return (Criteria) this;
        }

        public Criteria andRecvLongitudeNotBetween(String value1, String value2) {
            addCriterion("recv_longitude not between", value1, value2, "recvLongitude");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeIsNull() {
            addCriterion("recv_latitude is null");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeIsNotNull() {
            addCriterion("recv_latitude is not null");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeEqualTo(String value) {
            addCriterion("recv_latitude =", value, "recvLatitude");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeNotEqualTo(String value) {
            addCriterion("recv_latitude <>", value, "recvLatitude");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeGreaterThan(String value) {
            addCriterion("recv_latitude >", value, "recvLatitude");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("recv_latitude >=", value, "recvLatitude");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeLessThan(String value) {
            addCriterion("recv_latitude <", value, "recvLatitude");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeLessThanOrEqualTo(String value) {
            addCriterion("recv_latitude <=", value, "recvLatitude");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeLike(String value) {
            addCriterion("recv_latitude like", value, "recvLatitude");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeNotLike(String value) {
            addCriterion("recv_latitude not like", value, "recvLatitude");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeIn(List<String> values) {
            addCriterion("recv_latitude in", values, "recvLatitude");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeNotIn(List<String> values) {
            addCriterion("recv_latitude not in", values, "recvLatitude");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeBetween(String value1, String value2) {
            addCriterion("recv_latitude between", value1, value2, "recvLatitude");
            return (Criteria) this;
        }

        public Criteria andRecvLatitudeNotBetween(String value1, String value2) {
            addCriterion("recv_latitude not between", value1, value2, "recvLatitude");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Integer value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Integer value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Integer value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Integer value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Integer> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Integer> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayAmoutIsNull() {
            addCriterion("pay_amout is null");
            return (Criteria) this;
        }

        public Criteria andPayAmoutIsNotNull() {
            addCriterion("pay_amout is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmoutEqualTo(Double value) {
            addCriterion("pay_amout =", value, "payAmout");
            return (Criteria) this;
        }

        public Criteria andPayAmoutNotEqualTo(Double value) {
            addCriterion("pay_amout <>", value, "payAmout");
            return (Criteria) this;
        }

        public Criteria andPayAmoutGreaterThan(Double value) {
            addCriterion("pay_amout >", value, "payAmout");
            return (Criteria) this;
        }

        public Criteria andPayAmoutGreaterThanOrEqualTo(Double value) {
            addCriterion("pay_amout >=", value, "payAmout");
            return (Criteria) this;
        }

        public Criteria andPayAmoutLessThan(Double value) {
            addCriterion("pay_amout <", value, "payAmout");
            return (Criteria) this;
        }

        public Criteria andPayAmoutLessThanOrEqualTo(Double value) {
            addCriterion("pay_amout <=", value, "payAmout");
            return (Criteria) this;
        }

        public Criteria andPayAmoutIn(List<Double> values) {
            addCriterion("pay_amout in", values, "payAmout");
            return (Criteria) this;
        }

        public Criteria andPayAmoutNotIn(List<Double> values) {
            addCriterion("pay_amout not in", values, "payAmout");
            return (Criteria) this;
        }

        public Criteria andPayAmoutBetween(Double value1, Double value2) {
            addCriterion("pay_amout between", value1, value2, "payAmout");
            return (Criteria) this;
        }

        public Criteria andPayAmoutNotBetween(Double value1, Double value2) {
            addCriterion("pay_amout not between", value1, value2, "payAmout");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeIsNull() {
            addCriterion("recv_time is null");
            return (Criteria) this;
        }

        public Criteria andRecvTimeIsNotNull() {
            addCriterion("recv_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecvTimeEqualTo(Date value) {
            addCriterion("recv_time =", value, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeNotEqualTo(Date value) {
            addCriterion("recv_time <>", value, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeGreaterThan(Date value) {
            addCriterion("recv_time >", value, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("recv_time >=", value, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeLessThan(Date value) {
            addCriterion("recv_time <", value, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeLessThanOrEqualTo(Date value) {
            addCriterion("recv_time <=", value, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeIn(List<Date> values) {
            addCriterion("recv_time in", values, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeNotIn(List<Date> values) {
            addCriterion("recv_time not in", values, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeBetween(Date value1, Date value2) {
            addCriterion("recv_time between", value1, value2, "recvTime");
            return (Criteria) this;
        }

        public Criteria andRecvTimeNotBetween(Date value1, Date value2) {
            addCriterion("recv_time not between", value1, value2, "recvTime");
            return (Criteria) this;
        }

        public Criteria andTargetTimeIsNull() {
            addCriterion("target_time is null");
            return (Criteria) this;
        }

        public Criteria andTargetTimeIsNotNull() {
            addCriterion("target_time is not null");
            return (Criteria) this;
        }

        public Criteria andTargetTimeEqualTo(String value) {
            addCriterion("target_time =", value, "targetTime");
            return (Criteria) this;
        }

        public Criteria andTargetTimeNotEqualTo(String value) {
            addCriterion("target_time <>", value, "targetTime");
            return (Criteria) this;
        }

        public Criteria andTargetTimeGreaterThan(String value) {
            addCriterion("target_time >", value, "targetTime");
            return (Criteria) this;
        }

        public Criteria andTargetTimeGreaterThanOrEqualTo(String value) {
            addCriterion("target_time >=", value, "targetTime");
            return (Criteria) this;
        }

        public Criteria andTargetTimeLessThan(String value) {
            addCriterion("target_time <", value, "targetTime");
            return (Criteria) this;
        }

        public Criteria andTargetTimeLessThanOrEqualTo(String value) {
            addCriterion("target_time <=", value, "targetTime");
            return (Criteria) this;
        }

        public Criteria andTargetTimeLike(String value) {
            addCriterion("target_time like", value, "targetTime");
            return (Criteria) this;
        }

        public Criteria andTargetTimeNotLike(String value) {
            addCriterion("target_time not like", value, "targetTime");
            return (Criteria) this;
        }

        public Criteria andTargetTimeIn(List<String> values) {
            addCriterion("target_time in", values, "targetTime");
            return (Criteria) this;
        }

        public Criteria andTargetTimeNotIn(List<String> values) {
            addCriterion("target_time not in", values, "targetTime");
            return (Criteria) this;
        }

        public Criteria andTargetTimeBetween(String value1, String value2) {
            addCriterion("target_time between", value1, value2, "targetTime");
            return (Criteria) this;
        }

        public Criteria andTargetTimeNotBetween(String value1, String value2) {
            addCriterion("target_time not between", value1, value2, "targetTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNull() {
            addCriterion("finish_time is null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIsNotNull() {
            addCriterion("finish_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTimeEqualTo(Date value) {
            addCriterion("finish_time =", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotEqualTo(Date value) {
            addCriterion("finish_time <>", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThan(Date value) {
            addCriterion("finish_time >", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finish_time >=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThan(Date value) {
            addCriterion("finish_time <", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeLessThanOrEqualTo(Date value) {
            addCriterion("finish_time <=", value, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeIn(List<Date> values) {
            addCriterion("finish_time in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotIn(List<Date> values) {
            addCriterion("finish_time not in", values, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeBetween(Date value1, Date value2) {
            addCriterion("finish_time between", value1, value2, "finishTime");
            return (Criteria) this;
        }

        public Criteria andFinishTimeNotBetween(Date value1, Date value2) {
            addCriterion("finish_time not between", value1, value2, "finishTime");
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