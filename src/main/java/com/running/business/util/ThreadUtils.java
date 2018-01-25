package com.running.business.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liumingyu
 * @create 2018-01-14 下午4:39
 */
public class ThreadUtils {

    /**
     * 自定义线程池(有界队列 + 自定义线程名),默认使用abort策略
     *
     * @param coreThreadSize
     * @param maxThreadSize
     * @param queueSize
     * @param threadNameFormat
     * @return
     */
    public static ExecutorService createPoolWithFixQueue(int coreThreadSize, int maxThreadSize, int queueSize, String threadNameFormat) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat(threadNameFormat)
                .setDaemon(true)
                .build();

        final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(queueSize);

        return new ThreadPoolExecutor(coreThreadSize, maxThreadSize,
                60L, TimeUnit.SECONDS,
                queue, threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 自定义线程池(有界队列 + 自定义线程名 + 自定义策略)
     *
     * @param coreThreadSize
     * @param maxThreadSize
     * @param queueSize
     * @param threadNameFormat
     * @return
     */
    public static ExecutorService createPoolWithFixQueue(int coreThreadSize, int maxThreadSize, int queueSize, String threadNameFormat, RejectedExecutionHandler rejectedExecutionHandler) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat(threadNameFormat)
                .setDaemon(true)
                .build();

        final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(queueSize);

        return new ThreadPoolExecutor(coreThreadSize, maxThreadSize,
                60L, TimeUnit.SECONDS,
                queue, threadFactory, rejectedExecutionHandler);
    }
}