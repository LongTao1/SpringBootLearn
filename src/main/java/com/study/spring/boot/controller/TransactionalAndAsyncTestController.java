package com.study.spring.boot.controller;

import com.study.spring.boot.service.SameClassInvokeAsyncService;
import com.study.spring.boot.service.TransactionalAndAsyncTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LongTao
 * created on 2023/1/19
 */
@Slf4j
@RestController
@RequestMapping("/tran/async/test")
public class TransactionalAndAsyncTestController {
    @Autowired
    private TransactionalAndAsyncTestService sameClassInvokeAsyncService;


    @GetMapping
    public void testAsync() {
        String threadName = Thread.currentThread().getName();
        log.info("controller 执行，线程名称:{}", threadName);
        sameClassInvokeAsyncService.tranAndAsync();
    }
}
