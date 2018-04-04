package com.running.business.service.impl;

import com.running.business.common.ResultEnum;
import com.running.business.enums.OrderStatusEnum;
import com.running.business.enums.OrderTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunOrderMapper;
import com.running.business.pojo.RunOrder;
import com.running.business.pojo.RunOrderExample;
import com.running.business.sdk.OrderServiceStrategy;
import com.running.business.service.HelpBuyOrderService;
import com.running.business.service.RunOrderService;
import com.running.business.util.Run_StringUtil;
import com.running.business.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
        LOGGER.info("{} 开始执行", LOG_PREFIX);
        if (uid == null || uid <= 0) {
            LOGGER.error("{} 用户ID错误, uid = {}", LOG_PREFIX, uid);
            throw new AppException(ResultEnum.USER_ID_IS_ERROR);
        }
        if (order == null) {
            LOGGER.error("{} 订单参数错误, uid = {}", LOG_PREFIX, uid);
            throw new AppException(ResultEnum.ORDER_PARAMETER_IS_EMPTY);
        }
        if (orderIdIsExist(order.getOrderid())) {
            order.setOrderid(this.generatorOrderId());
        }
        if (order.getOrderid() == null || "".equals(order.getOrderid())) {
            order.setOrderid(this.generatorOrderId());
        }
        order.setStatus(OrderStatusEnum.UNPAID.getCode());
        order.setUpdateTime(new Date());
        LOGGER.info("{} 订单入库", LOG_PREFIX);
        runOrderMapper.insert(order);
        runOrderService.saveOrUpdatePreference(order.getGoods(), order.getRemark(), uid);
        Map<String, Object> map = new HashMap<>();
        map.put("result", order);
        LOGGER.info("{}返回结果{}", LOG_PREFIX, map);
        return map;
    }

    /**
     * 生成订单ID（去重）
     *
     * @return
     * @throws AppException
     */
    private String generatorOrderId() throws AppException {
        String orderId = null;
        List<RunOrder> list = null;
        while (list != null || list.size() != 0) {
            orderId = Run_StringUtil.getOrderId();
            RunOrderExample example = new RunOrderExample();
            RunOrderExample.Criteria criteria = example.createCriteria();
            criteria.andOrderidEqualTo(orderId);
            list = runOrderMapper.selectByExample(example);
        }
        return orderId;
    }

    /**
     * 订单Id是否存在
     *
     * @param orderId
     * @return
     * @throws AppException
     */
    public boolean orderIdIsExist(String orderId) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            return false;
        }
        RunOrderExample example = new RunOrderExample();
        RunOrderExample.Criteria criteria = example.createCriteria();
        criteria.andOrderidEqualTo(orderId);
        List<RunOrder> list = runOrderMapper.selectByExample(example);
        return ValidateUtil.isValid(list);
    }

    @Override
    public Integer returnBizId() {
        return OrderTypeEnum.HELP_BUY.getCode();
    }
}
