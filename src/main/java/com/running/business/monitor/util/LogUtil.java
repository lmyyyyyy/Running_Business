package com.running.business.monitor.util;

import com.running.business.monitor.pojo.RunMapperLogWithBLOBs;
import com.running.business.monitor.pojo.RunServiceLog;
import com.running.business.monitor.pojo.RunServiceLogWithBLOBs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author liumingyu
 * @create 2018-01-21 下午4:02
 */
public class LogUtil {

    /**
     * 当前service日志栈
     */
    public static ThreadLocal<Stack<RunServiceLog>> currentServiceLog = new ThreadLocal<Stack<RunServiceLog>>() {
        @Override
        protected Stack<RunServiceLog> initialValue() {
            return new Stack<>();
        }
    };

    /**
     * 总记录
     */
    public static ThreadLocal<List<RunServiceLogWithBLOBs>> serviceLogList = new ThreadLocal<List<RunServiceLogWithBLOBs>>() {
        @Override
        protected List<RunServiceLogWithBLOBs> initialValue() {
            return new ArrayList<>();
        }
    };

    /**
     * mapper日志集合
     */
    public static ThreadLocal<List<RunMapperLogWithBLOBs>> mapperThreadLocal = new ThreadLocal<List<RunMapperLogWithBLOBs>>() {
        @Override
        protected List<RunMapperLogWithBLOBs> initialValue() {
            return new LinkedList<>();
        }
    };

    /**
     * service日志入栈
     *
     * @param runServiceLog
     */
    public static void pushCurrentServiceLog(RunServiceLogWithBLOBs runServiceLog) {
        if (!currentServiceLog.get().isEmpty()) {
            RunServiceLog top = currentServiceLog.get().peek();
            runServiceLog.setTop(false);
            runServiceLog.setParentId(top.getId());
            runServiceLog.setParent(top);
        } else {
            runServiceLog.setTop(true);
            runServiceLog.setParentId(0);
        }
        currentServiceLog.get().push(runServiceLog);
        serviceLogList.get().add(runServiceLog);
        runServiceLog.setMapperStartIndex(mapperThreadLocal.get().size());
    }

    /**
     * 获取当前serivce日志的栈顶元素
     *
     * @return
     */
    public static RunServiceLog peekCurrentServiceLog() {
        if (currentServiceLog.get().isEmpty()) {
            return null;
        }

        return currentServiceLog.get().peek();
    }

    /**
     * 将mapper日志存入集合
     *
     * @param runMapperLog
     * @return
     */
    public static int pushRunMapperLog(RunMapperLogWithBLOBs runMapperLog) {
        if (!isServiceLogStackEmpty()) {
            runMapperLog.setParent(peekCurrentServiceLog());
        }
        mapperThreadLocal.get().add(runMapperLog);
        return mapperThreadLocal.get().size();
    }

    /**
     * 获取mapper日志集合
     *
     * @return
     */
    public static List<RunMapperLogWithBLOBs> getRunMapperLogList() {
        return mapperThreadLocal.get();
    }

    /**
     * 清除
     */
    public static void clear() {
        RunServiceLog runServiceLog = currentServiceLog.get().pop();
        if (currentServiceLog.get().isEmpty()) {
            serviceLogList.get().clear();
            mapperThreadLocal.get().clear();
        }
    }

    /**
     * 栈是否为空
     *
     * @return
     */
    public static boolean isServiceLogStackEmpty() {
        return currentServiceLog.get().isEmpty();
    }
}
