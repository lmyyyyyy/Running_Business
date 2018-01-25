package com.running.business.monitor.actions.commands;

import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.monitor.actions.context.ActionContext;
import lombok.Getter;

/**
 * @author liumingyu
 * @create 2018-01-21 下午3:34
 */
public abstract class AbstractCommand<T> implements Command<T> {

    @Getter
    private ActionContext<T> actionContext;

    @Override
    public Command<T> withContext(ActionContext<T> actionContext) {
        if (actionContext == null) {
            throw new AppException(ResultEnum.ACTION_CONTEXT);
        }
        this.actionContext = actionContext;
        return this;
    }
}
