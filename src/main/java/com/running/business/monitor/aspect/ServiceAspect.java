package com.running.business.monitor.aspect;

import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.actions.invoker.Invoker;
import com.running.business.monitor.pojo.RunServiceLogWithBLOBs;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import static com.running.business.monitor.actions.constants.BeanNameConstant.SERVICE_INVOKER;

/**
 * @author liumingyu
 * @create 2018-01-20 下午5:01
 */
@Component
@Aspect
public class ServiceAspect {

    @Resource(name = SERVICE_INVOKER)
    private Invoker<RunServiceLogWithBLOBs> invoker;

    @Pointcut("@annotation(com.running.business.monitor.annotation.ServiceMethod)")
    public void annotationPointCut(){}


    @Pointcut("execution(public * com.running.business.service..*.save*(..))")
    public void saveMethodPointcut(){}

    @Pointcut("execution(public * com.running.business.service..*.update*(..))")
    public void updateMethodPointcut(){}

    @Pointcut("execution(public * com.running.business.service..*.delete*(..))")
    public void deleteMethodPointcut(){}

    @Pointcut("execution(public * com.running.business.service..*.batch*(..))")
    public void batchMethodPointcut(){}

    /** 需排除自己的log */
    @Pointcut("execution(public * com.running.business.monitor.service..*.*(..))")
    public void servicelogServicePointcut(){}

    @Around(value = "(annotationPointCut() || saveMethodPointcut() || updateMethodPointcut() || deleteMethodPointcut() " +
            "|| batchMethodPointcut()) && !servicelogServicePointcut()")
    public Object serviceMethodAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        ActionContext<RunServiceLogWithBLOBs> actionContext = ActionContext.<RunServiceLogWithBLOBs>builder()
                .invokerName(SERVICE_INVOKER)
                .joinPoint(joinPoint)
                .build();
        //使用service方法对应的invoker执行对应的command
        invoker.invoke(actionContext);
        if (actionContext.getMethodException() != null){
            throw actionContext.getMethodException();
        }
        return actionContext.getMethodResult();
    }
}
