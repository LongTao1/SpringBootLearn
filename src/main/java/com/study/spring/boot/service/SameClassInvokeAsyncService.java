package com.study.spring.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author LongTao
 * created on 2023/1/17
 */
@Slf4j
@Service
public class SameClassInvokeAsyncService {
    @Resource
    private ApplicationContext applicationContext;

    public void callAsync() {
        async("同类直接调用");
        SameClassInvokeAsyncService bean = applicationContext.getBean(SameClassInvokeAsyncService.class);
        bean.async("同类但是通过applicationContext来getBean调用");
    }

    @Async
    public void async(String param) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String threadName = Thread.currentThread().getName();
        log.info("service 执行，线程名称:{},方法入参:{}", threadName, param);
    }
}
