package com.study.spring.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author LongTao
 * created on 2023/1/17
 */
@Slf4j
@Service
public class BatchAsyncService {
    @Autowired
    private AsyncService asyncService;

    @Autowired
    private ApplicationContext ac;

    private final AtomicLong atomicLong = new AtomicLong();

    public void batchAsync() {
        for (int i = 0; i < 5; i++) {
            BatchAsyncService bean = ac.getBean(this.getClass());
            bean.async();
        }
    }


    public void async() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String threadName = Thread.currentThread().getName();
        log.info("service 异步执行，线程名称:{}", threadName);
        long l = atomicLong.incrementAndGet();
        if (l == 5) {
            atomicLong.set(0);
            throw new RuntimeException("自定义异常");
        }
    }
}
