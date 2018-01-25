package com.running.business.monitor.actions.commands.impl.common;

import com.running.business.dto.UserDTO;
import com.running.business.monitor.actions.commands.AbstractCommand;
import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.annotation.Command;
import com.running.business.monitor.aspect.ServiceAspect;
import com.running.business.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static com.running.business.monitor.actions.constants.BeanNameConstant.MAPPER_INVOKER;
import static com.running.business.monitor.actions.constants.BeanNameConstant.SERVICE_INVOKER;
import static com.running.business.monitor.annotation.Command.ORDER_INIT;

/**
 * 记录用户信息
 *
 * @author liumingyu
 * @create 2018-01-21 下午5:15
 */
@Component("UserInfoCommand")
@Command(order = ORDER_INIT, invoker = {SERVICE_INVOKER, MAPPER_INVOKER})
public class UserInfoCommand extends AbstractCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Override
    public void execute() {
        LOGGER.info("【UserInfoCommand执行命令】获取当前线程用户信息");
        ActionContext actionContext = getActionContext();
        UserDTO userDTO = UserUtil.getUser();
        if (userDTO != null) {
            actionContext.setUserName(userDTO.getName() == null ? "" : userDTO.getName());
            actionContext.setOperatorId(userDTO.getId() == null ? 0 : userDTO.getId());
        } else {
            actionContext.setUserName("");
            actionContext.setOperatorId(0);
        }
    }
}
