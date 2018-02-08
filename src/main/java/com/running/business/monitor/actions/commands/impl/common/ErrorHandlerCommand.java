package com.running.business.monitor.actions.commands.impl.common;


import com.running.business.monitor.actions.commands.AbstractCommand;
import com.running.business.monitor.actions.commands.CommandExceptions;
import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.annotation.Command;
import com.running.business.monitor.aspect.ServiceAspect;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.running.business.monitor.actions.constants.BeanNameConstant.MAPPER_INVOKER;
import static com.running.business.monitor.actions.constants.BeanNameConstant.SERVICE_INVOKER;
import static com.running.business.monitor.annotation.Command.ORDER_FINNALY;

/**
 * 对command执行过程中出现的错误进行统一处理
 *
 * @author liumingyu
 * @create 2018-01-21 下午5:00
 */
@Component("ErrorHandlerCommand")
@Command(order = ORDER_FINNALY, invoker = {SERVICE_INVOKER, MAPPER_INVOKER})
public class ErrorHandlerCommand extends AbstractCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Override
    public void execute() {
        LOGGER.info("【ErrorHandlerCommand执行命令】错误统一处理");
        ActionContext actionContext = getActionContext();
        if (actionContext == null) {
            return;
        }
        List<CommandExceptions> exceptionRecordList = actionContext.getExceptionList();
        if (CollectionUtils.isEmpty(exceptionRecordList)) {
            return;
        }
        if (LOGGER.isDebugEnabled()) {
            for (CommandExceptions record : exceptionRecordList) {
                LOGGER.debug("[MethodLog]- {} : 命令执行出错, command [{}], error : [{}]", new Object[]{
                        actionContext.getInvokerName(), record.getCommand().getClass(), record.getThrowable()
                });
            }
        }
    }
}
