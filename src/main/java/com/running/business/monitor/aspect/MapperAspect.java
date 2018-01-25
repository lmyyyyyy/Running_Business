package com.running.business.monitor.aspect;

import com.running.business.monitor.actions.context.ActionContext;
import com.running.business.monitor.actions.invoker.Invoker;
import com.running.business.monitor.pojo.RunMapperLogWithBLOBs;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.running.business.monitor.actions.constants.BeanNameConstant.MAPPER_INVOKER;

/**
 * @author liumingyu
 * @create 2018-01-20 下午5:01
 */
@Component
@Aspect
public class MapperAspect {
    @Resource(name = MAPPER_INVOKER)
    private Invoker<RunMapperLogWithBLOBs> invoker;

    /**
     * 在mapper中，返回类型为int的方法为增删改方法
     */
    @Pointcut("execution(public int com.running.business.mapper..*.*(..))")
    public void modifyOperationPointCut(){}

    /**
     * mapper日志本身的mapper方法
     */
    @Pointcut("execution(public * com.running.business.monitor.mapper.*.*(..))")
    public void mapperLogOperation(){}

    @Around("modifyOperationPointCut() && !mapperLogOperation()")
    public Object mapperMethodAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        ActionContext<RunMapperLogWithBLOBs> actionContext = ActionContext.<RunMapperLogWithBLOBs>builder()
                .invokerName(MAPPER_INVOKER)
                .joinPoint(joinPoint)
                .build();
        //使用dao方法对应的invoker执行一系列command
        invoker.invoke(actionContext);
        if (actionContext.getMethodException() != null){
            throw actionContext.getMethodException();
        }
        return actionContext.getMethodResult();
    }
}
