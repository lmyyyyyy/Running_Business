package com.running.business.sdk.filter;

import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.sdk.FilterChain;
import com.running.business.sdk.common.Request;
import com.running.business.sdk.common.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liumingyu
 * @create 2018-01-14 下午4:42
 */
public class MonitorFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorFilter.class);
    private static final String LOG_PREFIX = "【责任链监控模块】 ";
    @Override
    public void doFilter(Request req, Response resp, FilterChain chain) {
        if (req.getBizId()  == null || req.getOrder() == null) {
            LOGGER.error("{} 参数不能为空", LOG_PREFIX);
            throw new AppException(ResultEnum.ORDER_PARAMETER_IS_EMPTY);
        }
        LOGGER.info("{} bizId = {}, operatorId = {}, className = {}, methodName = {}, paramter = {}",LOG_PREFIX ,
                req.getBizId(), req.getOperatorId(), req.getClassName(), req.getMethodName(), req.getOperatorId());

        chain.doFilter(req, resp);
    }
}
