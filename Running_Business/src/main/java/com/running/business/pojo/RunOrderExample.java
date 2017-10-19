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

        public Criteria andOrderidEqualTo(Integer value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Integer value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Integer value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Integer value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Integer value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Integer> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Integer> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Integer value1, Integer value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Integer value1, Integer value2) {
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

        public Criteria andBusinessNameIsNull() {
            addCriterion("business_name is null");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIsNotNull() {
            addCriterion("business_name is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessNameEqualTo(String value) {
            addCriterion("business_name =", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotEqualTo(String value) {
            addCriterion("business_name <>", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameGreaterThan(String value) {
            addCriterion("business_name >", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameGreaterThanOrEqualTo(String value) {
            addCriterion("business_name >=", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLessThan(String value) {
            addCriterion("business_name <", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLessThanOrEqualTo(String value) {
            addCriterion("business_name <=", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLike(String value) {
            addCriterion("business_name like", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotLike(String value) {
            addCriterion("business_name not like", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIn(List<String> values) {
            addCriterion("business_name in", values, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotIn(List<String> values) {
            addCriterion("business_name not in", values, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameBetween(String value1, String value2) {
            addCriterion("business_name between", value1, value2, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotBetween(String value1, String value2) {
            addCriterion("business_name not between", value1, value2, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressIsNull() {
            addCriterion("business_address is null");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressIsNotNull() {
            addCriterion("business_address is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressEqualTo(String value) {
            addCriterion("business_address =", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressNotEqualTo(String value) {
            addCriterion("business_address <>", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressGreaterThan(String value) {
            addCriterion("business_address >", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressGreaterThanOrEqualTo(String value) {
            addCriterion("business_address >=", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressLessThan(String value) {
            addCriterion("business_address <", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressLessThanOrEqualTo(String value) {
            addCriterion("business_address <=", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressLike(String value) {
            addCriterion("business_address like", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressNotLike(String value) {
            addCriterion("business_address not like", value, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressIn(List<String> values) {
            addCriterion("business_address in", values, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressNotIn(List<String> values) {
            addCriterion("business_address not in", values, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressBetween(String value1, String value2) {
            addCriterion("business_address between", value1, value2, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andBusinessAddressNotBetween(String value1, String value2) {
            addCriterion("business_address not between", value1, value2, "businessAddress");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIsNull() {
            addCriterion("order_goods is null");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIsNotNull() {
            addCriterion("order_goods is not null");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsEqualTo(String value) {
            addCriterion("order_goods =", value, "orderGoods");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsNotEqualTo(String value) {
            addCriterion("order_goods <>", value, "orderGoods");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsGreaterThan(String value) {
            addCriterion("order_goods >", value, "orderGoods");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsGreaterThanOrEqualTo(String value) {
            addCriterion("order_goods >=", value, "orderGoods");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsLessThan(String value) {
            addCriterion("order_goods <", value, "orderGoods");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsLessThanOrEqualTo(String value) {
            addCriterion("order_goods <=", value, "orderGoods");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsLike(String value) {
            addCriterion("order_goods like", value, "orderGoods");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsNotLike(String value) {
            addCriterion("order_goods not like", value, "orderGoods");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsIn(List<String> values) {
            addCriterion("order_goods in", values, "orderGoods");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsNotIn(List<String> values) {
            addCriterion("order_goods not in", values, "orderGoods");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsBetween(String value1, String value2) {
            addCriterion("order_goods between", value1, value2, "orderGoods");
            return (Criteria) this;
        }

        public Criteria andOrderGoodsNotBetween(String value1, String value2) {
            addCriterion("order_goods not between", value1, value2, "orderGoods");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkIsNull() {
            addCriterion("order_remark is null");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkIsNotNull() {
            addCriterion("order_remark is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkEqualTo(String value) {
            addCriterion("order_remark =", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkNotEqualTo(String value) {
            addCriterion("order_remark <>", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkGreaterThan(String value) {
            addCriterion("order_remark >", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("order_remark >=", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkLessThan(String value) {
            addCriterion("order_remark <", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkLessThanOrEqualTo(String value) {
            addCriterion("order_remark <=", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkLike(String value) {
            addCriterion("order_remark like", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkNotLike(String value) {
            addCriterion("order_remark not like", value, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkIn(List<String> values) {
            addCriterion("order_remark in", values, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkNotIn(List<String> values) {
            addCriterion("order_remark not in", values, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkBetween(String value1, String value2) {
            addCriterion("order_remark between", value1, value2, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderRemarkNotBetween(String value1, String value2) {
            addCriterion("order_remark not between", value1, value2, "orderRemark");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIsNull() {
            addCriterion("order_price is null");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIsNotNull() {
            addCriterion("order_price is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPriceEqualTo(Double value) {
            addCriterion("order_price =", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotEqualTo(Double value) {
            addCriterion("order_price <>", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceGreaterThan(Double value) {
            addCriterion("order_price >", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("order_price >=", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceLessThan(Double value) {
            addCriterion("order_price <", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceLessThanOrEqualTo(Double value) {
            addCriterion("order_price <=", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIn(List<Double> values) {
            addCriterion("order_price in", values, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotIn(List<Double> values) {
            addCriterion("order_price not in", values, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceBetween(Double value1, Double value2) {
            addCriterion("order_price between", value1, value2, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotBetween(Double value1, Double value2) {
            addCriterion("order_price not between", value1, value2, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderFeeIsNull() {
            addCriterion("order_fee is null");
            return (Criteria) this;
        }

        public Criteria andOrderFeeIsNotNull() {
            addCriterion("order_fee is not null");
            return (Criteria) this;
        }

        public Criteria andOrderFeeEqualTo(Double value) {
            addCriterion("order_fee =", value, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeNotEqualTo(Double value) {
            addCriterion("order_fee <>", value, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeGreaterThan(Double value) {
            addCriterion("order_fee >", value, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("order_fee >=", value, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeLessThan(Double value) {
            addCriterion("order_fee <", value, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeLessThanOrEqualTo(Double value) {
            addCriterion("order_fee <=", value, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeIn(List<Double> values) {
            addCriterion("order_fee in", values, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeNotIn(List<Double> values) {
            addCriterion("order_fee not in", values, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeBetween(Double value1, Double value2) {
            addCriterion("order_fee between", value1, value2, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderFeeNotBetween(Double value1, Double value2) {
            addCriterion("order_fee not between", value1, value2, "orderFee");
            return (Criteria) this;
        }

        public Criteria andOrderTipIsNull() {
            addCriterion("order_tip is null");
            return (Criteria) this;
        }

        public Criteria andOrderTipIsNotNull() {
            addCriterion("order_tip is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTipEqualTo(Double value) {
            addCriterion("order_tip =", value, "orderTip");
            return (Criteria) this;
        }

        public Criteria andOrderTipNotEqualTo(Double value) {
            addCriterion("order_tip <>", value, "orderTip");
            return (Criteria) this;
        }

        public Criteria andOrderTipGreaterThan(Double value) {
            addCriterion("order_tip >", value, "orderTip");
            return (Criteria) this;
        }

        public Criteria andOrderTipGreaterThanOrEqualTo(Double value) {
            addCriterion("order_tip >=", value, "orderTip");
            return (Criteria) this;
        }

        public Criteria andOrderTipLessThan(Double value) {
            addCriterion("order_tip <", value, "orderTip");
            return (Criteria) this;
        }

        public Criteria andOrderTipLessThanOrEqualTo(Double value) {
            addCriterion("order_tip <=", value, "orderTip");
            return (Criteria) this;
        }

        public Criteria andOrderTipIn(List<Double> values) {
            addCriterion("order_tip in", values, "orderTip");
            return (Criteria) this;
        }

        public Criteria andOrderTipNotIn(List<Double> values) {
            addCriterion("order_tip not in", values, "orderTip");
            return (Criteria) this;
        }

        public Criteria andOrderTipBetween(Double value1, Double value2) {
            addCriterion("order_tip between", value1, value2, "orderTip");
            return (Criteria) this;
        }

        public Criteria andOrderTipNotBetween(Double value1, Double value2) {
            addCriterion("order_tip not between", value1, value2, "orderTip");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(Integer value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(Integer value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(Integer value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(Integer value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(Integer value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<Integer> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<Integer> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(Integer value1, Integer value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderRecvAddressIsNull() {
            addCriterion("order_recv_address is null");
            return (Criteria) this;
        }

        public Criteria andOrderRecvAddressIsNotNull() {
            addCriterion("order_recv_address is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRecvAddressEqualTo(Integer value) {
            addCriterion("order_recv_address =", value, "orderRecvAddress");
            return (Criteria) this;
        }

        public Criteria andOrderRecvAddressNotEqualTo(Integer value) {
            addCriterion("order_recv_address <>", value, "orderRecvAddress");
            return (Criteria) this;
        }

        public Criteria andOrderRecvAddressGreaterThan(Integer value) {
            addCriterion("order_recv_address >", value, "orderRecvAddress");
            return (Criteria) this;
        }

        public Criteria andOrderRecvAddressGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_recv_address >=", value, "orderRecvAddress");
            return (Criteria) this;
        }

        public Criteria andOrderRecvAddressLessThan(Integer value) {
            addCriterion("order_recv_address <", value, "orderRecvAddress");
            return (Criteria) this;
        }

        public Criteria andOrderRecvAddressLessThanOrEqualTo(Integer value) {
            addCriterion("order_recv_address <=", value, "orderRecvAddress");
            return (Criteria) this;
        }

        public Criteria andOrderRecvAddressIn(List<Integer> values) {
            addCriterion("order_recv_address in", values, "orderRecvAddress");
            return (Criteria) this;
        }

        public Criteria andOrderRecvAddressNotIn(List<Integer> values) {
            addCriterion("order_recv_address not in", values, "orderRecvAddress");
            return (Criteria) this;
        }

        public Criteria andOrderRecvAddressBetween(Integer value1, Integer value2) {
            addCriterion("order_recv_address between", value1, value2, "orderRecvAddress");
            return (Criteria) this;
        }

        public Criteria andOrderRecvAddressNotBetween(Integer value1, Integer value2) {
            addCriterion("order_recv_address not between", value1, value2, "orderRecvAddress");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneIsNull() {
            addCriterion("order_phone is null");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneIsNotNull() {
            addCriterion("order_phone is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneEqualTo(String value) {
            addCriterion("order_phone =", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneNotEqualTo(String value) {
            addCriterion("order_phone <>", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneGreaterThan(String value) {
            addCriterion("order_phone >", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("order_phone >=", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneLessThan(String value) {
            addCriterion("order_phone <", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneLessThanOrEqualTo(String value) {
            addCriterion("order_phone <=", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneLike(String value) {
            addCriterion("order_phone like", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneNotLike(String value) {
            addCriterion("order_phone not like", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneIn(List<String> values) {
            addCriterion("order_phone in", values, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneNotIn(List<String> values) {
            addCriterion("order_phone not in", values, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneBetween(String value1, String value2) {
            addCriterion("order_phone between", value1, value2, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneNotBetween(String value1, String value2) {
            addCriterion("order_phone not between", value1, value2, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedIsNull() {
            addCriterion("order_speed is null");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedIsNotNull() {
            addCriterion("order_speed is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedEqualTo(String value) {
            addCriterion("order_speed =", value, "orderSpeed");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedNotEqualTo(String value) {
            addCriterion("order_speed <>", value, "orderSpeed");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedGreaterThan(String value) {
            addCriterion("order_speed >", value, "orderSpeed");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedGreaterThanOrEqualTo(String value) {
            addCriterion("order_speed >=", value, "orderSpeed");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedLessThan(String value) {
            addCriterion("order_speed <", value, "orderSpeed");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedLessThanOrEqualTo(String value) {
            addCriterion("order_speed <=", value, "orderSpeed");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedLike(String value) {
            addCriterion("order_speed like", value, "orderSpeed");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedNotLike(String value) {
            addCriterion("order_speed not like", value, "orderSpeed");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedIn(List<String> values) {
            addCriterion("order_speed in", values, "orderSpeed");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedNotIn(List<String> values) {
            addCriterion("order_speed not in", values, "orderSpeed");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedBetween(String value1, String value2) {
            addCriterion("order_speed between", value1, value2, "orderSpeed");
            return (Criteria) this;
        }

        public Criteria andOrderSpeedNotBetween(String value1, String value2) {
            addCriterion("order_speed not between", value1, value2, "orderSpeed");
            return (Criteria) this;
        }

        public Criteria andOrderSendtimeIsNull() {
            addCriterion("order_sendtime is null");
            return (Criteria) this;
        }

        public Criteria andOrderSendtimeIsNotNull() {
            addCriterion("order_sendtime is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSendtimeEqualTo(Date value) {
            addCriterion("order_sendtime =", value, "orderSendtime");
            return (Criteria) this;
        }

        public Criteria andOrderSendtimeNotEqualTo(Date value) {
            addCriterion("order_sendtime <>", value, "orderSendtime");
            return (Criteria) this;
        }

        public Criteria andOrderSendtimeGreaterThan(Date value) {
            addCriterion("order_sendtime >", value, "orderSendtime");
            return (Criteria) this;
        }

        public Criteria andOrderSendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("order_sendtime >=", value, "orderSendtime");
            return (Criteria) this;
        }

        public Criteria andOrderSendtimeLessThan(Date value) {
            addCriterion("order_sendtime <", value, "orderSendtime");
            return (Criteria) this;
        }

        public Criteria andOrderSendtimeLessThanOrEqualTo(Date value) {
            addCriterion("order_sendtime <=", value, "orderSendtime");
            return (Criteria) this;
        }

        public Criteria andOrderSendtimeIn(List<Date> values) {
            addCriterion("order_sendtime in", values, "orderSendtime");
            return (Criteria) this;
        }

        public Criteria andOrderSendtimeNotIn(List<Date> values) {
            addCriterion("order_sendtime not in", values, "orderSendtime");
            return (Criteria) this;
        }

        public Criteria andOrderSendtimeBetween(Date value1, Date value2) {
            addCriterion("order_sendtime between", value1, value2, "orderSendtime");
            return (Criteria) this;
        }

        public Criteria andOrderSendtimeNotBetween(Date value1, Date value2) {
            addCriterion("order_sendtime not between", value1, value2, "orderSendtime");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeIsNull() {
            addCriterion("order_recv_longitude is null");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeIsNotNull() {
            addCriterion("order_recv_longitude is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeEqualTo(String value) {
            addCriterion("order_recv_longitude =", value, "orderRecvLongitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeNotEqualTo(String value) {
            addCriterion("order_recv_longitude <>", value, "orderRecvLongitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeGreaterThan(String value) {
            addCriterion("order_recv_longitude >", value, "orderRecvLongitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("order_recv_longitude >=", value, "orderRecvLongitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeLessThan(String value) {
            addCriterion("order_recv_longitude <", value, "orderRecvLongitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeLessThanOrEqualTo(String value) {
            addCriterion("order_recv_longitude <=", value, "orderRecvLongitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeLike(String value) {
            addCriterion("order_recv_longitude like", value, "orderRecvLongitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeNotLike(String value) {
            addCriterion("order_recv_longitude not like", value, "orderRecvLongitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeIn(List<String> values) {
            addCriterion("order_recv_longitude in", values, "orderRecvLongitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeNotIn(List<String> values) {
            addCriterion("order_recv_longitude not in", values, "orderRecvLongitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeBetween(String value1, String value2) {
            addCriterion("order_recv_longitude between", value1, value2, "orderRecvLongitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLongitudeNotBetween(String value1, String value2) {
            addCriterion("order_recv_longitude not between", value1, value2, "orderRecvLongitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeIsNull() {
            addCriterion("order_recv_latitude is null");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeIsNotNull() {
            addCriterion("order_recv_latitude is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeEqualTo(String value) {
            addCriterion("order_recv_latitude =", value, "orderRecvLatitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeNotEqualTo(String value) {
            addCriterion("order_recv_latitude <>", value, "orderRecvLatitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeGreaterThan(String value) {
            addCriterion("order_recv_latitude >", value, "orderRecvLatitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("order_recv_latitude >=", value, "orderRecvLatitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeLessThan(String value) {
            addCriterion("order_recv_latitude <", value, "orderRecvLatitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeLessThanOrEqualTo(String value) {
            addCriterion("order_recv_latitude <=", value, "orderRecvLatitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeLike(String value) {
            addCriterion("order_recv_latitude like", value, "orderRecvLatitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeNotLike(String value) {
            addCriterion("order_recv_latitude not like", value, "orderRecvLatitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeIn(List<String> values) {
            addCriterion("order_recv_latitude in", values, "orderRecvLatitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeNotIn(List<String> values) {
            addCriterion("order_recv_latitude not in", values, "orderRecvLatitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeBetween(String value1, String value2) {
            addCriterion("order_recv_latitude between", value1, value2, "orderRecvLatitude");
            return (Criteria) this;
        }

        public Criteria andOrderRecvLatitudeNotBetween(String value1, String value2) {
            addCriterion("order_recv_latitude not between", value1, value2, "orderRecvLatitude");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNull() {
            addCriterion("order_time is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNotNull() {
            addCriterion("order_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeEqualTo(Date value) {
            addCriterion("order_time =", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotEqualTo(Date value) {
            addCriterion("order_time <>", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThan(Date value) {
            addCriterion("order_time >", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("order_time >=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThan(Date value) {
            addCriterion("order_time <", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThanOrEqualTo(Date value) {
            addCriterion("order_time <=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIn(List<Date> values) {
            addCriterion("order_time in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotIn(List<Date> values) {
            addCriterion("order_time not in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeBetween(Date value1, Date value2) {
            addCriterion("order_time between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotBetween(Date value1, Date value2) {
            addCriterion("order_time not between", value1, value2, "orderTime");
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