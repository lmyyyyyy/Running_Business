package com.running.business.sdk.filter;

import com.running.business.sdk.FilterChain;
import com.running.business.sdk.common.Request;
import com.running.business.sdk.common.Response;

/**
 * 过滤器
 *
 * @author liumingyu
 * @create 2018-01-14 下午4:25
 */
public interface Filter {
    /**
     * 过滤
     *
     * @param req
     * @param resp
     * @param chain
     */
    void doFilter(Request req, Response resp, FilterChain chain);
}