package com.running.business.monitor.actions.invoker.impl;

import com.running.business.monitor.actions.invoker.AbstractInvoker;
import com.running.business.monitor.pojo.RunServiceLogWithBLOBs;
import org.springframework.stereotype.Component;

import static com.running.business.monitor.actions.constants.BeanNameConstant.SERVICE_INVOKER;

/**
 * service方法对应的invoker，其所需执行的command顺序如下 :
 * 0 ： UserInfoCommand，MethodParameterCommand;
 * 1 ： ServiceLogGenerateCommand
 * 5 ： MethodInvokerCommand
 * ORDER_LAST-1 ：ServiceLogFillingCommand
 * ORDER_LAST ： ServiceLogToDBCommand，PrintLogCommand
 * ORDER_FINALLY ：ErrorHandlerCommand
 *
 * @author liumingyu
 * @create 2018-01-20 下午5:03
 */
@Component(SERVICE_INVOKER)
public class ServiceInvoker extends AbstractInvoker<RunServiceLogWithBLOBs> {
    @Override
    public String getInvokerName() {
        return SERVICE_INVOKER;
    }
}
