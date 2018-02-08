package com.running.business.monitor.actions.commands.impl.mapper_command;

import com.running.business.monitor.actions.commands.AbstractCommand;
import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.actions.exception.EntityIsNullException;
import com.running.business.monitor.annotation.Command;
import com.running.business.monitor.aspect.MapperAspect;
import com.running.business.monitor.enums.InvokeStatusEnum;
import com.running.business.monitor.pojo.RunMapperLogWithBLOBs;
import com.running.business.util.RegexUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.running.business.monitor.actions.constants.BeanNameConstant.MAPPER_INVOKER;
import static com.running.business.monitor.annotation.Command.ORDER_LAST;
import static com.running.business.monitor.util.ObjectEncodeUtil.encodeErrorMessage;

/**
 * 填充字段
 */
@Component("MapperLogFillingCommand")
@Command(order = ORDER_LAST - 1, invoker = {MAPPER_INVOKER})
public class MapperLogFillingCommand extends AbstractCommand<RunMapperLogWithBLOBs> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapperAspect.class);

    @Override
    public void execute() {
        LOGGER.info("【MapperLogFillingCommand】Mapper日志数据填充");
        ActionContext<RunMapperLogWithBLOBs> actionContext = getActionContext();
        if (actionContext.getEntity() == null) {
            throw new EntityIsNullException("RunMapperLogWithBLOBs对象为空");
        }
        RunMapperLogWithBLOBs entity = actionContext.getEntity();
        entity.setTimeCost(actionContext.getMethodEndTime() - actionContext.getMethodStartTime());
        if (actionContext.getMethodException() == null) {
            entity.setInvokeStatus((byte) InvokeStatusEnum.SUCCESS.getCode());
            try {
                if (actionContext.getMethodResult() instanceof Integer || RegexUtils.checkDigit(actionContext.getMethodResult().toString())) {
                    entity.setInfluenceRow((Integer)actionContext.getMethodResult());
                } else {
                    entity.setInfluenceRow(0);
                }
            } catch (Throwable t) {
                LOGGER.error("method result encode error : ", t);
            }
        } else {
            entity.setInvokeStatus((byte) InvokeStatusEnum.FAIL.getCode());
            entity.setErrorMessage(encodeErrorMessage(actionContext.getMethodException()));
        }
        entity.setUpdateTime(new Date());
    }
}
