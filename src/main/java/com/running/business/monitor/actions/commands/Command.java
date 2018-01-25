package com.running.business.monitor.actions.commands;

import com.running.business.monitor.actions.context.ActionContext;

/**
 * @author liumingyu
 * @create 2018-01-20 下午5:08
 */
public interface Command<T> {
    Command<T> withContext(ActionContext<T> actionContext);

    void execute();
}
