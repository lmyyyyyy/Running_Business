package com.running.business.sdk.filter;

import com.running.business.common.ResultEnum;
import com.running.business.enums.OrderStatusEnum;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunOrder;
import com.running.business.sdk.FilterChain;
import com.running.business.sdk.common.Request;
import com.running.business.sdk.common.Response;
import com.running.business.util.Run_StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author liumingyu
 * @create 2018-01-14 下午4:42
 */
public class ConfigFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFilter.class);
    private static final String LOG_PREFIX = "【责任链获取配置模块】 ";

    @Override
    public void doFilter(Request req, Response resp, FilterChain chain) throws AppException{
        LOGGER.info("{} begin", LOG_PREFIX);
        RunOrder order = req.getOrder();
        if (order == null) {
            LOGGER.error("{} 订单参数为空", LOG_PREFIX);
            throw new AppException(ResultEnum.ORDER_GET_ERROR);
        }
        String orderNum = Run_StringUtil.getOrderId();
        if (orderNum == null || "".equals(orderNum)) {
            LOGGER.error("{} 订单号生成失败", LOG_PREFIX);
            throw new AppException(ResultEnum.ORDER_GENERATOR_FAIL);
        }
        order.setUid(req.getOperatorId());
        order.setType(req.getBizId());
        order.setOrderid(orderNum);
        order.setAddTime(new Date());
        order.setUpdateTime(new Date());
        order.setStatus(OrderStatusEnum.SUBMMIT.getCode());
        req.setOrder(order);

        req.setArgs(new Object[]{order, req.getOperatorId()});

        chain.doFilter(req, resp);
    }
}
