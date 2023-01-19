package com.study.spring.boot.controller;

import com.study.spring.boot.service.AsyncService;
import com.study.spring.boot.service.BatchAsyncService;
import com.study.spring.boot.service.SameClassInvokeAsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LongTao
 * created on 2023/1/17
 */
@Slf4j
@RestController
@RequestMapping("/sameClass")
public class SameClassInvokeAsyncTestController {
    @Autowired
    private SameClassInvokeAsyncService sameClassInvokeAsyncService;


    @GetMapping
    public void testAsync() {
        String threadName = Thread.currentThread().getName();
        log.info("controller 执行，线程名称:{}", threadName);
        sameClassInvokeAsyncService.callAsync();
    }



}
