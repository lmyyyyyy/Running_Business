package com.running.business.monitor.actions.commands.impl.mapper_command;

import com.running.business.monitor.actions.commands.AbstractCommand;
import com.running.business.monitor.annotation.Command;
import com.running.business.monitor.aspect.MapperAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.running.business.monitor.actions.constants.BeanNameConstant.MAPPER_INVOKER;
import static com.running.business.monitor.annotation.Command.ORDER_LAST;

/**
 * mapper日志存入数据库
 */
@Command(order = ORDER_LAST, invoker = MAPPER_INVOKER)
public class MapperLogToDBCommand extends AbstractCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapperAspect.class);

    @Override
    public void execute() {
        LOGGER.info("【MapperLogToDBCommand执行命令】Mapper日志入库");
    }
}
