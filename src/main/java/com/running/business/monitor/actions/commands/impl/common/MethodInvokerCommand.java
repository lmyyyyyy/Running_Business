package com.running.business.monitor.actions.commands.impl.common;

import com.running.business.monitor.actions.commands.AbstractCommand;
import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.annotation.Command;
import com.running.business.monitor.aspect.ServiceAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.running.business.monitor.actions.constants.BeanNameConstant.MAPPER_INVOKER;
import static com.running.business.monitor.actions.constants.BeanNameConstant.SERVICE_INVOKER;

/**
 * 方法执行
 *
 * @author liumingyu
 * @create 2018-01-21 下午5:30
 */
@Component("MethodInvokerCommand")
@Command(order = 5, invoker = {SERVICE_INVOKER, MAPPER_INVOKER})
public class MethodInvokerCommand extends AbstractCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);
    @Override
    public void execute() {
        LOGGER.info("【MethodInvokerCommand执行命令】方法执行");
        ActionContext actionContext = getActionContext();
        actionContext.setMethodStartTime(System.currentTimeMillis());
        if (actionContext.getJoinPoint() instanceof ProceedingJoinPoint) {
            try {
                if (actionContext.getInvokerName().equals(SERVICE_INVOKER)) {
                    Object result = ((ProceedingJoinPoint) actionContext.getJoinPoint()).proceed();
                    actionContext.setMethodResult(result);
                } else {
                    Integer result = (Integer)((ProceedingJoinPoint) actionContext.getJoinPoint()).proceed();
                    actionContext.setMethodResult(result);
                }
            } catch (Throwable throwable) {
                actionContext.setMethodException(throwable);
            }
        }
        actionContext.setMethodEndTime(System.currentTimeMillis());
    }
}
