package com.study.spring.boot.controller;

import com.study.spring.boot.service.AsyncService;
import com.study.spring.boot.service.BatchAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LongTao
 * created on 2023/1/17
 */
@Slf4j
@RestController
public class AsyncTestController {
    private final AsyncService asyncService;
    private final BatchAsyncService batchAsync;

    public AsyncTestController(AsyncService asyncService, BatchAsyncService batchAsync) {
        this.asyncService = asyncService;
        this.batchAsync = batchAsync;
    }

    @GetMapping("/async")
    public void testAsync() {
        asyncService.testAsync();
        String threadName = Thread.currentThread().getName();
        log.info("controller 执行，线程名称:{}", threadName);
    }

    @GetMapping("/sync")
    public void testSync() {
        asyncService.testSync();
        String threadName = Thread.currentThread().getName();
        log.info("controller 执行，线程名称:{}", threadName);
    }


    @GetMapping("/batch/async")
    public void batchAsync() {
        batchAsync.batchAsync();
    }
}
