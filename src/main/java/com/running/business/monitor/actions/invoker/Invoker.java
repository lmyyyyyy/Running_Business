package com.running.business.monitor.actions.invoker;

import com.running.business.monitor.actions.context.ActionContext;

/**
 * @author liumingyu
 * @create 2018-01-20 下午5:02
 */
public interface Invoker<T> {
    void invoke(ActionContext<T> actionContext);
}
