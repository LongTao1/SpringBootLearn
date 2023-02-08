package com.study.spring.boot.controller;

import com.study.spring.boot.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LongTao
 * created on 2023/1/17
 */
@Slf4j
@RestController
public class AsyncTestController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping("/sync")
    public void testSync() {
        asyncService.testSync();
        String threadName = Thread.currentThread().getName();
        log.info("controller 执行，线程名称:{}", threadName);
    }


    @GetMapping("/async")
    public void testAsync() {
        asyncService.testAsync();
        String threadName = Thread.currentThread().getName();
        log.info("controller 执行，线程名称:{}", threadName);
    }


}
