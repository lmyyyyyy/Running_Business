package com.running.business.monitor.actions.commands.impl.mapper_command;

import com.running.business.monitor.actions.commands.AbstractCommand;
import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.annotation.Command;
import com.running.business.monitor.annotation.MapperMethod;
import com.running.business.monitor.aspect.MapperAspect;
import com.running.business.monitor.enums.InvokeStatusEnum;
import com.running.business.monitor.pojo.RunMapperLogWithBLOBs;
import com.running.business.monitor.pojo.RunServiceLog;
import com.running.business.monitor.util.AnnotationUtil;
import com.running.business.monitor.util.LogUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.running.business.monitor.actions.constants.BeanNameConstant.MAPPER_INVOKER;
import static com.running.business.monitor.annotation.Command.ORDER_START;
import static com.running.business.monitor.constants.MethodLogConstant.DEFAULT_OPERATION_TYPE;
import static com.running.business.monitor.constants.MethodLogConstant.DELETE_METHOD_TYPE;
import static com.running.business.monitor.constants.MethodLogConstant.INSERT_METHOD_TYPE;
import static com.running.business.monitor.constants.MethodLogConstant.MAPPER_INTERFACE_POSTFIX;
import static com.running.business.monitor.constants.MethodLogConstant.UPDATE_METHOD_TYPE;
import static com.running.business.monitor.util.AnnotationUtil.getMapperAnnotation;

/**
 * mapper生成命令
 */
@Component("MapperLogGenerateCommand")
@Command(order = ORDER_START, invoker = {MAPPER_INVOKER})
public class MapperLogGenerateCommand extends AbstractCommand<RunMapperLogWithBLOBs> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperAspect.class);

    @Override
    public void execute() {
        LOGGER.info("【MapperLogGenerateCommand执行命令】mapper日志生成实体");
        RunServiceLog currentSerivceLog = LogUtil.peekCurrentServiceLog();
        ActionContext<RunMapperLogWithBLOBs> actionContext = getActionContext();
        JoinPoint joinPoint = actionContext.getJoinPoint();
        MapperMethod annotation = getMapperAnnotation(joinPoint);

        String methodName = joinPoint.getSignature().toString();
        RunMapperLogWithBLOBs runMapperLog = new RunMapperLogWithBLOBs();
        if (currentSerivceLog != null) {
            runMapperLog.setSerivceLogId(currentSerivceLog.getId());
        } else {
            runMapperLog.setSerivceLogId(0);
        }
        runMapperLog.setMethodName(methodName);
        runMapperLog.setMethodParam(actionContext.getMethodArgs());
        runMapperLog.setTimeCost(0L);
        runMapperLog.setTargetTableName(getTableName(annotation, AnnotationUtil.getMapperInterface(joinPoint)));
        runMapperLog.setOperateType((byte) getOperationType(annotation, methodName).ordinal());
        runMapperLog.setInfluenceRow(0);
        runMapperLog.setInvokeStatus((byte) InvokeStatusEnum.SUCCESS.getCode());
        Date now = new Date();
        runMapperLog.setAddTime(now);
        runMapperLog.setUpdateTime(now);
        LogUtil.pushRunMapperLog(runMapperLog);
        actionContext.setEntity(runMapperLog);
    }


    private String getTableName(MapperMethod annotation, Class targetClass) {
        return annotation != null ? annotation.table() : getTableNameFromClassName(targetClass);
    }

    private String getTableNameFromClassName(Class clazz) {
        String name = clazz.getName();
        String[] cols = name.split("\\.");
        name = cols[cols.length - 1];
        name = name.substring(0, name.lastIndexOf(MAPPER_INTERFACE_POSTFIX));
        StringBuilder sb = new StringBuilder();
        for (char c : name.toCharArray()) {
            if (c < 'Z' && c > 'A') {
                //下划线分割
                if (sb.length() != 0) {
                    sb.append('_');
                }
                //大写转小写
                sb.append((char) (c + 32));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 获取一个mapper方法的操作类型
     *
     * @param annotation
     * @param methodName
     * @return
     */
    private MapperMethod.OperationType getOperationType(MapperMethod annotation, String methodName) {
        //先从注解中获取
        if (annotation != null) {
            return annotation.operationType();
        }

        //如果没有，根据方法名判断
        if (StringUtils.isNotEmpty(methodName)) {
            if (StringUtils.containsIgnoreCase(methodName, INSERT_METHOD_TYPE)) {
                return MapperMethod.OperationType.INSERT;
            }

            if (StringUtils.containsIgnoreCase(methodName, UPDATE_METHOD_TYPE)) {
                return MapperMethod.OperationType.UPDATE;
            }

            if (StringUtils.containsIgnoreCase(methodName, DELETE_METHOD_TYPE)) {
                return MapperMethod.OperationType.DELETE;
            }
        }

        //全都没有则返回默认的操作类型
        return DEFAULT_OPERATION_TYPE;
    }
}
