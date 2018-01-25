package com.running.business.sdk.filter;

import com.running.business.sdk.FilterChain;
import com.running.business.sdk.strategy.HttpStrategy;
import com.running.business.sdk.strategy.RemoteStrategy;
import com.running.business.sdk.strategy.RemoteStrategyFactory;
import com.running.business.sdk.common.Request;
import com.running.business.sdk.common.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author liumingyu
 * @create 2018-01-14 下午4:43
 */
public class CallFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallFilter.class);
    private static final String LOG_PREFIX = "【责任链调用模块】 ";

    @Override
    public void doFilter(Request req, Response resp, FilterChain chain) {
        LOGGER.info("{} Begin", LOG_PREFIX);
        //获取结果对象配置
        //同步调用
        List<RemoteStrategy> syncCall = new ArrayList<RemoteStrategy>();
        //遍历需要执行的远程方法
        RemoteStrategy remoteStrategy = RemoteStrategyFactory.getInstance().getRemoteStrategy(true);
        if (remoteStrategy instanceof HttpStrategy) {
            syncCall.add(remoteStrategy);
        } else {
            remoteStrategy.call(req, resp);
        }
        //同步调用的滞后执行
        for (RemoteStrategy strategy : syncCall) {
            strategy.call(req, resp);
        }

        //遍历future结果
        Map<String, Future> futureMap = resp.getFutureMap();
        for (Map.Entry<String, Future> entry : futureMap.entrySet()) {
            String objectCode = entry.getKey();
            Future future = entry.getValue();
            try {
                resp.addResultMap("resultMap", future.get());
            } catch (InterruptedException e) {
                LOGGER.error("{} InterruptedException异常 = {}", LOG_PREFIX, e);
            } catch (ExecutionException e) {
                LOGGER.error("{} ExecutionException异常 = {}", LOG_PREFIX, e);
            }
        }

        chain.doFilter(req, resp);
    }
}
