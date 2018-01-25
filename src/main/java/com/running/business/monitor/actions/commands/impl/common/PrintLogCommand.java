package com.running.business.monitor.actions.commands.impl.common;


import com.running.business.common.Config;
import com.running.business.monitor.actions.commands.AbstractCommand;
import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.annotation.Command;
import com.running.business.monitor.aspect.ServiceAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.running.business.monitor.actions.constants.BeanNameConstant.MAPPER_INVOKER;
import static com.running.business.monitor.actions.constants.BeanNameConstant.SERVICE_INVOKER;
import static com.running.business.monitor.annotation.Command.ORDER_LAST;


/**
 * 输出日志
 *
 * @author liumingyu
 * @create 2018-01-21 下午5:10
 */
@Component("PrintLogCommand")
@Command(order = ORDER_LAST, invoker = {SERVICE_INVOKER, MAPPER_INVOKER})
public class PrintLogCommand extends AbstractCommand<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Override
    public void execute() {
        LOGGER.info("【PrintLogCommand执行命令】输出日志");
        if (Config.METHOD_LOG_PRINT_SWITCH) {
            ActionContext actionContext = getActionContext();
            LOGGER.info("[MethodLog]- : result[{}] , error[{}], methodCost [{}], logEntity [{}]",
                    new Object[]{
                            actionContext.getMethodResult(), actionContext.getMethodException(),
                            actionContext.getMethodEndTime() - actionContext.getMethodStartTime(), actionContext.getEntity()
                    }
            );
        }
    }
}
