package com.running.business.sdk.strategy;


import com.running.business.sdk.common.Request;
import com.running.business.sdk.common.Response;
import com.running.business.service.HelpBuyOrderService;
import com.running.business.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 调用Future策略
 *
 * @author liumingyu
 * @create 2018-01-14 下午4:36
 */
@Component
public class CallStrategy implements RemoteStrategy, ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallStrategy.class);
    private static final String LOG_PREFIX = "[反射调用接口] ";
    private static ApplicationContext applicationContext;
    private static final ExecutorService executorService = ThreadUtils.createPoolWithFixQueue(100, 150, 1000, "OrderFetcher-%d");
    /**
     * 接口超时时间, ms
     */
    public static final int PIGEON_INTERFACE_TIMEOUT = 5000;

    @Override
    public void call(Request request, Response response) {
        LOGGER.info("{} 开始执行 类名[{}], 方法名[{}], 参数[{}]", LOG_PREFIX, request.getClassName(), request.getMethodName(), request.getArgs());
        Long startTime = System.currentTimeMillis();
        String className = request.getClassName();
        String methodName = request.getMethodName();
        try {
            Future<Map<String, Object>> future = executorService.submit(new InvokeTask(className, methodName, request.getArgs()));
            response.addFutureMap(request.getOrder().getOrderid(), future);
        } catch (Exception e) {
            LOGGER.warn("{} 调用反射方法抛出未知异常. className[ {} ], methodCode[ {} ]", LOG_PREFIX, className, methodName, e);
        }
        Long endTime = System.currentTimeMillis();
        LOGGER.info("{} 执行完毕 用时[{}]", LOG_PREFIX, endTime - startTime);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CallStrategy.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() throws BeansException {
        return applicationContext;
    }

    private class InvokeTask implements Callable<Map<String, Object>> {

        private String clazzName;
        private String method;
        private Object[] args;


        public InvokeTask(String clazzName, String method, Object[] args) {
            this.clazzName = clazzName;
            this.method = method;
            this.args = args;
        }

        @Override
        public Map<String, Object> call() throws Exception {
            Map<String, Object> map = null;
            try {
                String beanName = clazzName.substring(0, 1).toLowerCase() + clazzName.substring(1);
                LOGGER.info("{} 获取Bean名称 {}", LOG_PREFIX, beanName);
                Class[] classes = getClazz(args);
                LOGGER.info("{} 获取参数数组[{}]", LOG_PREFIX, classes);
                Method method1 = ReflectionUtils.findMethod(applicationContext.getBean(beanName).getClass(), method, classes);
                LOGGER.info("{} 获取方法{}", LOG_PREFIX, method);
                Object object = method1.invoke(applicationContext.getBean(beanName), args);
                LOGGER.info("{} 执行方法{}", LOG_PREFIX, method);
                map = (Map<String, Object>) object;
                LOGGER.info("{} 转化结果{} --> {}", LOG_PREFIX, object, map);
            } catch (ClassNotFoundException e) {
                LOGGER.warn("{} 接口类加载失败, 请确认是否已引入相关依赖. clazzName[ {} ]", LOG_PREFIX, clazzName);
                throw e;
            }
            return map;
        }

        public Class[] getClazz(Object[] args) throws Exception {
            int count = args.length;
            Class[] classes = new Class[count];
            for (int i = 0; i < count; i++) {
                classes[i] = args[i].getClass();
            }
            return classes;
        }

    }

    public static void main(String[] args) throws Exception {
        String clazzName = "com.running.business.service.HelpBuyOrderService";
        clazzName = clazzName.substring(clazzName.lastIndexOf(".") + 1);
        String beanName = clazzName.substring(0, 1).toLowerCase() + clazzName.substring(1);
        System.out.println(beanName);
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-mvc.xml");
        HelpBuyOrderService service = (HelpBuyOrderService) context.getBean(beanName);
        System.out.println(service);
    }

}
