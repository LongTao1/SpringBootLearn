package com.study.spring.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author LongTao
 * created on 2023/1/17
 */
@Slf4j
@Service
public class AsyncService {


    @Async
    public void testAsync() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String threadName = Thread.currentThread().getName();
        log.info("service 异步执行，线程名称:{}", threadName);
    }

    public void testSync() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String threadName = Thread.currentThread().getName();
        log.info("service 同步执行执行，线程名称:{}", threadName);
    }

}
