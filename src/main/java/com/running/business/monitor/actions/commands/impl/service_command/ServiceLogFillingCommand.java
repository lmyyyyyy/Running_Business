package com.running.business.monitor.actions.commands.impl.service_command;

import com.running.business.monitor.actions.commands.AbstractCommand;
import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.actions.exception.EntityIsNullException;
import com.running.business.monitor.annotation.Command;
import com.running.business.monitor.aspect.ServiceAspect;
import com.running.business.monitor.enums.InvokeStatusEnum;
import com.running.business.monitor.pojo.RunServiceLogWithBLOBs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.running.business.monitor.actions.constants.BeanNameConstant.SERVICE_INVOKER;
import static com.running.business.monitor.annotation.Command.ORDER_LAST;
import static com.running.business.monitor.util.ObjectEncodeUtil.encodeErrorMessage;
import static com.running.business.monitor.util.ObjectEncodeUtil.encodeObject;

/**
 * 填充字段
 */
@Component("ServiceLogFillingCommand")
@Command(order = ORDER_LAST - 1, invoker = {SERVICE_INVOKER})
public class ServiceLogFillingCommand extends AbstractCommand<RunServiceLogWithBLOBs> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Override
    public void execute() {
        LOGGER.info("【ServiceLogFillingCommand执行命令】Service日志数据填充");
        ActionContext<RunServiceLogWithBLOBs> actionContext = getActionContext();
        if (actionContext.getEntity() == null) {
            throw new EntityIsNullException("RunServiceLogWithBLOBs对象为空");
        }
        RunServiceLogWithBLOBs entity = actionContext.getEntity();
        entity.setTimeCost(actionContext.getMethodEndTime() - actionContext.getMethodStartTime());
        if (actionContext.getMethodException() == null) {
            entity.setInvokeStatus(InvokeStatusEnum.SUCCESS.getCode());
            try {
                entity.setReturnValue(encodeObject(actionContext.getMethodResult()));
            } catch (Throwable t) {
                LOGGER.error("method result encode error : ", t);
            }
        } else {
            entity.setInvokeStatus(InvokeStatusEnum.FAIL.getCode());
            entity.setErrorMessage(encodeErrorMessage(actionContext.getMethodException()));
        }
        entity.setUpdateTime(new Date());
    }
}
