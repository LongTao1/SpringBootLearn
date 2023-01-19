package com.study.spring.boot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author LongTao
 * created on 2023/1/18
 */
@Slf4j
@Configuration
//EnableAsync可以放在启动类或者这里
@EnableAsync
public class MyAsyncConfigurer implements AsyncConfigurer {
    /**
     * 核心线程数
     */
    private static final int CORE_POOL_SIZE = 2;
    /**
     * 最大线程数
     */
    private static final int MAX_POOL_SIZE = 2;
    /**
     * 设置 BlockingQueue 的容量
     */
    private static final int QUEUE_CAPACITY = 100;

    /**
     * 自定义线程池
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        taskExecutor.setThreadNamePrefix("customization-thread-pool-");
        //队列满了以后，抛弃任务，并抛出RejectedExecutionException
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * 异常处理策略
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncUncaughtExceptionHandler();
    }

    /**
     * 实现AsyncUncaughtExceptionHandler接口
     */
    public static class MyAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            log.info("线程池异常，异常方法:{}", method.toString());
            log.info("线程池异常,异常信息:{}", throwable.getMessage());

            for (Object object : objects) {
                log.info("线程池异常,异常对象：{}",object.getClass());
            }
        }
    }
}
