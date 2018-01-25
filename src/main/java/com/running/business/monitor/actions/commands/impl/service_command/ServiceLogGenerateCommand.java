package com.running.business.monitor.actions.commands.impl.service_command;

import com.running.business.common.Config;
import com.running.business.monitor.actions.commands.AbstractCommand;
import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.annotation.Command;
import com.running.business.monitor.annotation.ServiceMethod;
import com.running.business.monitor.aspect.ServiceAspect;
import com.running.business.monitor.enums.InvokeStatusEnum;
import com.running.business.monitor.pojo.RunServiceLogWithBLOBs;
import com.running.business.monitor.util.AnnotationUtil;
import com.running.business.monitor.util.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.running.business.monitor.actions.constants.BeanNameConstant.SERVICE_INVOKER;
import static com.running.business.monitor.annotation.Command.ORDER_START;
import static com.running.business.monitor.util.AnnotationUtil.getServiceClassAnnotation;
import static com.running.business.monitor.util.AnnotationUtil.getServiceMethodAnnotation;

/**
 * 生成service日志
 */
@Component("ServiceLogGenerateCommand")
@Command(order = ORDER_START, invoker = {SERVICE_INVOKER})
public class ServiceLogGenerateCommand extends AbstractCommand<RunServiceLogWithBLOBs> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Override
    public void execute() {
        LOGGER.info("【ServiceLogGenerateCommand执行命令】Service日志生成实体");
        ActionContext<RunServiceLogWithBLOBs> actionContext = getActionContext();
        RunServiceLogWithBLOBs runServiceLog = new RunServiceLogWithBLOBs();
        JoinPoint joinPoint = actionContext.getJoinPoint();
        String methodName = actionContext.getJoinPoint().getSignature().toString();
        runServiceLog.setClassName(getModuleName(joinPoint));
        runServiceLog.setMethodName(methodName);
        runServiceLog.setMethodParam(actionContext.getMethodArgs());
        runServiceLog.setOperatorId(actionContext.getOperatorId());
        runServiceLog.setOperatorName(actionContext.getUserName());
        runServiceLog.setReturnValue("");
        runServiceLog.setTimeCost(0L);
        runServiceLog.setInvokeStatus(InvokeStatusEnum.PROCEEDING.getCode());
        Date date = new Date();
        runServiceLog.setAddTime(date);
        runServiceLog.setUpdateTime(date);
        pushCurrentOpLog(runServiceLog);
        actionContext.setEntity(runServiceLog);
    }

    private String getModuleName(JoinPoint joinPoint) {
        //先获取方法上的注解
        ServiceMethod annotation = getServiceMethodAnnotation(joinPoint);
        //再获取接口或者类上的接口
        if (annotation == null) {
            annotation = getServiceClassAnnotation(joinPoint);
        }

        if (annotation != null) {
            return annotation.module();
        }

        return AnnotationUtil.getServiceClass(joinPoint).getName();
    }

    private void pushCurrentOpLog(RunServiceLogWithBLOBs runServiceLogWithBLOBs) {
        if (LogUtil.isServiceLogStackEmpty() || Config.SERVICE_LOG_RECURSION_SWITCH) {
            LogUtil.pushCurrentServiceLog(runServiceLogWithBLOBs);
        }
    }
}
