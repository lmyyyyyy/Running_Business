package com.running.business.sdk;

import com.running.business.sdk.common.Request;
import com.running.business.sdk.common.Response;
import com.running.business.sdk.filter.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liumingyu
 * @create 2018-01-14 下午4:27
 */
public class FilterChain {
    private List<Filter> filters = new ArrayList<Filter>();
    //调用链上的过滤器时，记录过滤器的位置用
    private int index = 0;

    public FilterChain addFilter(Filter f) {
        filters.add(f);
        return this;
    }

    public void doFilter(Request req, Response resp) {
        if (index == filters.size()) return;
        //得到当前过滤器
        Filter f = filters.get(index);
        index++;
        f.doFilter(req, resp, this);
    }
}