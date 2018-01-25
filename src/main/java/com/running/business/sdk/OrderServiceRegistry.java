package com.running.business.sdk;

import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 服务注册中心
 *
 * @author liumingyu
 * @create 2018-01-20 下午2:18
 */
@Component
public class OrderServiceRegistry implements InitializingBean, BeanFactoryAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceRegistry.class);

    private static final String LOG_PREFIX = "[订单服务注册中心] ";

    private BeanFactory beanFactory;

    private final Map<Integer, OrderServiceStrategy> orderService = new HashMap<>();

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, OrderServiceStrategy> beans = ((ListableBeanFactory) beanFactory).getBeansOfType(OrderServiceStrategy.class);
        beans.forEach((key, value) -> orderService.put(value.returnBizId(), value));
        LOGGER.info("{} 已注册了{}条服务. actions[ {} ]", LOG_PREFIX, orderService.size(),
                orderService.entrySet().stream().map(entry -> entry.getValue().toString()).collect(
                        Collectors.joining(",")));
    }

    /**
     * 获取当前订单服务
     *
     * @param bizId
     * @return
     * @throws AppException
     */
    public OrderServiceStrategy getOrderServiceStrategy(Integer bizId) throws AppException {
        if (orderService.get(bizId) == null) {
            LOGGER.error(LOG_PREFIX + ResultEnum.ORDER_GET_ERROR.getCode() + "serviceCode = {}, ResultCodeEnum = {}", new Object[]{bizId, "订单服务获取异常"});
            throw new AppException(ResultEnum.ORDER_GET_ERROR.getCode(), "订单服务获取异常");
        }
        return orderService.get(bizId);
    }
}
