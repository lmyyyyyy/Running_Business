package com.running.business.monitor.actions.commands.impl.mapper_command;


import com.running.business.common.Config;
import com.running.business.monitor.actions.commands.AbstractCommand;
import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.actions.exception.EntityIsNullException;
import com.running.business.monitor.annotation.Command;
import com.running.business.monitor.aspect.MapperAspect;
import com.running.business.monitor.pojo.RunMapperLogWithBLOBs;
import com.running.business.util.JsonUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.running.business.monitor.actions.constants.BeanNameConstant.MAPPER_INVOKER;
import static com.running.business.monitor.annotation.Command.ORDER_START;
import static com.running.business.monitor.util.MyBatisUtil.getSQL4MapperMethod;

/**
 * 记录执行的sql语句
 *
 * @author liumingyu
 * @create 2018-01-21 下午5:25
 */
@Component("RecordSQLCommand")
@Command(order = ORDER_START + 1, invoker = {MAPPER_INVOKER})
public class RecordSQLCommand extends AbstractCommand<RunMapperLogWithBLOBs> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperAspect.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void execute() {
        LOGGER.info("【RecordSQLCommand执行命令】记录执行SQL语句");
        //记录执行的sql
        if (Config.RECORD_SQL_SWITCH) {
            ActionContext<RunMapperLogWithBLOBs> actionContext = getActionContext();
            RunMapperLogWithBLOBs mapperLog = actionContext.getEntity();
            JoinPoint joinPoint = actionContext.getJoinPoint();
            if (mapperLog == null) {
                throw new EntityIsNullException("RunMapperLogWithBLOBs实体类不存在");
            }
            try {
                mapperLog.setSqlStatement(JsonUtils.toJsonStr(getSQL4MapperMethod(joinPoint, sqlSessionFactory)));
            } catch (Exception e) {
                LOGGER.error("[MethodLog]- {} parse sql statement error : {}", actionContext.getInvokerName(), e);
            }
        }
    }
}
