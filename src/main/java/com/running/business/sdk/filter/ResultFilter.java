package com.running.business.sdk.filter;

import com.running.business.sdk.FilterChain;
import com.running.business.sdk.common.Request;
import com.running.business.sdk.common.Response;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author liumingyu
 * @create 2018-01-14 下午4:43
 */
public class ResultFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultFilter.class);
    private static final String LOG_PREFIX = "【责任链结果转换模块】 ";

    @Override
    public void doFilter(Request req, Response resp, FilterChain chain) {
        LOGGER.info("{} begin", LOG_PREFIX);
        Map<String, Object> resultMap = resp.getResultMap();
        if (MapUtils.isEmpty(resultMap)) {
            LOGGER.info("{} 接口返回结果为空.", LOG_PREFIX);
        }
        resp.setData(resultMap);
        chain.doFilter(req, resp);
    }
}
