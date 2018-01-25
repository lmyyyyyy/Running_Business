package com.running.business.monitor.actions.commands.impl.service_command;

import com.running.business.common.Config;
import com.running.business.monitor.actions.commands.AbstractCommand;
import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.actions.exception.EntityIsNullException;
import com.running.business.monitor.annotation.Command;
import com.running.business.monitor.aspect.ServiceAspect;
import com.running.business.monitor.pojo.RunServiceLog;
import com.running.business.monitor.pojo.RunServiceLogWithBLOBs;
import com.running.business.monitor.service.RunMethodLogService;
import com.running.business.monitor.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.running.business.monitor.actions.constants.BeanNameConstant.SERVICE_INVOKER;
import static com.running.business.monitor.annotation.Command.ORDER_LAST;

/**
 * service方法日志存入数据库
 */
@Component("ServiceLogToDBCommand")
@Command(order = ORDER_LAST, invoker = SERVICE_INVOKER)
public class ServiceLogToDBCommand extends AbstractCommand<RunServiceLogWithBLOBs> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);
    @Autowired
    private RunMethodLogService runMethodLogService;

    @Override
    public void execute() {
        LOGGER.info("【ServiceLogToDBCommand执行命令】Service日志入库");
        ActionContext<RunServiceLogWithBLOBs> actionContext = getActionContext();
        if (actionContext.getEntity() == null) {
            throw new EntityIsNullException("RunServiceLogWithBLOBs对象为空");
        }
        //更新数据库
        if (Config.Log_TO_DB && shouldOutput(actionContext.getEntity())) {
            //批量插入serviceLog记录
            runMethodLogService.batchSaveServiceLog(LogUtil.serviceLogList.get());
            if (Config.MAPPER_LOG_SINGLE_TO_DB) {
                runMethodLogService.batchSaveMapperLog(LogUtil.getRunMapperLogList());
            }
        }
        //清空threadlocal
        if (shouldClear(actionContext.getEntity())) {
            LogUtil.clear();
        }
    }

    /**
     * 判断当前runServiceLog是否需要输出
     *
     * @param runServiceLog
     * @return
     */
    private boolean shouldOutput(RunServiceLog runServiceLog) {
        return runServiceLog.isTop();
    }

    /**
     * 判断是否需要清除threadlocal中的runServiceLog相关记录
     *
     * @param runServiceLog
     * @return
     */
    private boolean shouldClear(RunServiceLogWithBLOBs runServiceLog) {
        return runServiceLog.isTop() || Config.IS_CLEAR_SERVICE_LOG_LIST;
    }

}
