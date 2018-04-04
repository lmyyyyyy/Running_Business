package com.running.business.sdk;

import com.running.business.enums.OrderTypeEnum;
import com.running.business.pojo.RunOrder;
import com.running.business.sdk.common.Request;
import com.running.business.sdk.common.Response;
import com.running.business.sdk.filter.CallFilter;
import com.running.business.sdk.filter.ConfigFilter;
import com.running.business.sdk.filter.MonitorFilter;
import com.running.business.sdk.filter.ResultFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 服务执行引擎
 *
 * @author liumingyu
 * @create 2018-01-14 下午4:36
 */
public class BizFetcher {

    private Logger LOGGER = LoggerFactory.getLogger(BizFetcher.class);

    private static FilterChain getDefaultFilterChain() {
        FilterChain filterChain = new FilterChain();
        //监控过滤模块
        filterChain.addFilter(new MonitorFilter());
        //获取配置数据
        filterChain.addFilter(new ConfigFilter());
        //远程调用
        filterChain.addFilter(new CallFilter());
        //结果转换
        filterChain.addFilter(new ResultFilter());
        return filterChain;
    }

    /**
     * 执行对象查询返回结果
     *
     * @param bizId
     * @param orderTypeEnum
     * @param order
     * @return
     */
    public static Map<String, Object> fetchMap(Integer bizId, OrderTypeEnum orderTypeEnum, RunOrder order, Integer operatorId) {
        Request request = new Request();
        request.setBizId(bizId);
        request.setClassName(orderTypeEnum.getClassName());
        request.setOrder(order);
        request.setMethodName(orderTypeEnum.getMethodName());
        request.setOperatorId(operatorId);
        Response<Map<String, Object>> response = new Response<>();
        FilterChain filterChain = getDefaultFilterChain();
        filterChain.doFilter(request, response);
        return response.getData();

    }

}
