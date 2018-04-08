package com.running.business.service.impl;

import com.running.business.common.ResultEnum;
import com.running.business.enums.OrderStatusEnum;
import com.running.business.enums.OrderTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunOrderMapper;
import com.running.business.pojo.RunOrder;
import com.running.business.sdk.OrderServiceStrategy;
import com.running.business.service.HelpBuyOrderService;
import com.running.business.service.RunOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("helpBuyOrderService")
public class HelpBuyOrderServiceImpl implements HelpBuyOrderService, OrderServiceStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpBuyOrderServiceImpl.class);
    private static final String LOG_PREFIX = "【帮我买订单Service模块】 ";

    @Autowired
    private RunOrderMapper runOrderMapper;

    @Autowired
    private RunOrderService runOrderService;

    @Override
    public Map<String, Object> saveHelpBuyOrder(RunOrder order, Integer uid) throws AppException {
        LOGGER.info("{} 开始执行帮我买下单操作", LOG_PREFIX);
        if (uid == null || uid <= 0) {
            LOGGER.error("{} 用户ID错误, uid = {}", LOG_PREFIX, uid);
            throw new AppException(ResultEnum.USER_ID_IS_ERROR);
        }
        if (order == null) {
            LOGGER.error("{} 订单参数错误, uid = {}", LOG_PREFIX, uid);
            throw new AppException(ResultEnum.ORDER_PARAMETER_IS_EMPTY);
        }
        if (order.getOrderid() == null || "".equals(order.getOrderid())) {
            order.setOrderid(runOrderService.generatorOrderId());
        }
        if (runOrderService.orderIdIsExist(order.getOrderid())) {
            order.setOrderid(runOrderService.generatorOrderId());
        }
        order.setStatus(OrderStatusEnum.UNPAID.getCode());
        order.setUpdateTime(new Date());
        validateOrderFeilds(order);
        LOGGER.info("{} 订单入库", LOG_PREFIX);
        runOrderMapper.insert(order);
        //保存或更新用户偏好
        runOrderService.saveOrUpdatePreference(order.getGoods(), order.getRemark(), uid);
        Map<String, Object> map = new HashMap<>();
        map.put("result", order);
        LOGGER.info("{} 下单结束，返回结果{}", LOG_PREFIX, map);
        return map;
    }

    /**
     * 验证订单数据
     *
     * @param order
     * @throws AppException
     */
    private void validateOrderFeilds(RunOrder order) throws AppException {
        if (order == null) {
            throw new AppException(ResultEnum.ORDER_PARAMETER_IS_EMPTY);
        }
        if (order.getType() == null) {
            throw new AppException(ResultEnum.ORDER_BIZ_TYPE_ERROR);
        }
        if (order.getGoods() == null || "".equals(order.getGoods())) {
            throw new AppException(ResultEnum.ORDER_ID_IS_ERROR);
        }
        if (order.getSourceAddress() == null || "".equals(order.getSourceAddress())) {
            throw new AppException(ResultEnum.ORDER_SOURCE_ADDRESS_ERROR);
        }
        if (order.getSourcePhone() == null || "".equals(order.getSourcePhone())) {
            throw new AppException(ResultEnum.ORDER_SOURCE_PHONE_ERROR);
        }
        if (order.getTargetAddress() == null || "".equals(order.getTargetAddress())) {
            throw new AppException(ResultEnum.ORDER_TARGET_ADDRESS_ERROR);
        }
        if (order.getRemark() == null || "".equals(order.getRemark())) {
            throw new AppException(ResultEnum.ORDRE_REMARK_ERROR);
        }
        if (order.getRequireTime() == null) {
            throw new AppException(ResultEnum.ORDER_REQUIRE_TIME_ERROR);
        }
        if (order.getAmount() == null || order.getAmount() < 0) {
            throw new AppException(ResultEnum.ORDER_AMOUNT_ERROR);
        }
        if (order.getDistance() == null || order.getDistance() < 0) {
            throw new AppException(ResultEnum.ORDER_DISTANCE_ERROR);
        }
        if (order.getSourceLongitude() == null || "".equals(order.getSourceLongitude())) {
            throw new AppException(ResultEnum.ORDER_SOURCE_LON_ERROR);
        }
        if (order.getSourceLatitude() == null || "".equals(order.getSourceLatitude())) {
            throw new AppException(ResultEnum.ORDER_SOURCE_LAT_ERROR);
        }
        if (order.getRecvLongitude() == null || "".equals(order.getRecvLongitude())) {
            throw new AppException(ResultEnum.ORDER_TARGET_LON_ERROR);
        }
        if (order.getRecvLatitude() == null || "".equals(order.getRecvLatitude())) {
            throw new AppException(ResultEnum.ORDER_TARGET_LAT_ERROR);
        }
        if (order.getPayType() == null) {
            throw new AppException(ResultEnum.ORDER_PAY_TYPE_ERROR);
        }
    }

    @Override
    public Integer returnBizId() {
        return OrderTypeEnum.HELP_BUY.getCode();
    }
}
