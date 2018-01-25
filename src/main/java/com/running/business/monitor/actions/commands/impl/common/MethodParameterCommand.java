package com.running.business.monitor.actions.commands.impl.common;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.running.business.monitor.actions.commands.AbstractCommand;
import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.annotation.Command;
import com.running.business.monitor.aspect.ServiceAspect;
import com.running.business.monitor.util.ObjectEncodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.running.business.monitor.actions.constants.BeanNameConstant.MAPPER_INVOKER;
import static com.running.business.monitor.actions.constants.BeanNameConstant.SERVICE_INVOKER;
import static com.running.business.monitor.annotation.Command.ORDER_INIT;

/**
 * 记录方法参数
 *
 * @author liumingyu
 * @create 2018-01-21 下午5:00
 */
@Component("RecordMethodParameterCommand")
@Command(order = ORDER_INIT, invoker = {SERVICE_INVOKER, MAPPER_INVOKER})
public class MethodParameterCommand extends AbstractCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Override
    public void execute() {
        LOGGER.info("【MethodParameterCommand执行命令】设置方法参数");
        ActionContext actionContext = getActionContext();
        try {
            actionContext.setMethodArgs(ObjectEncodeUtil.encodeMethodArgs(actionContext.getJoinPoint().getArgs()));
        } catch (JsonProcessingException e) {
            LOGGER.error("[MethodLog]- {} 方法参数编码出错 : {}", actionContext.getInvokerName(), e);
            actionContext.setMethodArgs("");
        }
    }
}
