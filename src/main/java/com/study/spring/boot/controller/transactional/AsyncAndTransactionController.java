package com.study.spring.boot.controller.transactional;

import com.study.spring.boot.service.transactional.AsyncAndTransactionalTestService;
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
@RequestMapping("/tran/async")
public class AsyncAndTransactionController {
    @Autowired
    private AsyncAndTransactionalTestService asyncAndTransactionalTestService;


    @GetMapping("/sameMethod")
    public void sameMethod() {
        String threadName = Thread.currentThread().getName();
        log.info("controller 执行，线程名称:{}", threadName);
        asyncAndTransactionalTestService.sameMethod();
    }


    @GetMapping("/syncException")
    public void syncException() {
        String threadName = Thread.currentThread().getName();
        log.info("controller 执行，线程名称:{}", threadName);
        asyncAndTransactionalTestService.syncException();
    }

    @GetMapping("/asyncException")
    public void asyncException() {
        String threadName = Thread.currentThread().getName();
        log.info("controller 执行，线程名称:{}", threadName);
        asyncAndTransactionalTestService.asyncException();
    }


    @GetMapping("/lastMethodException")
    public void lastMethodException() {
        String threadName = Thread.currentThread().getName();
        log.info("controller 执行，线程名称:{}", threadName);
        asyncAndTransactionalTestService.lastMethodException();
    }

    @GetMapping("/asyncExceptionInvokeLastMethod")
    public void asyncExceptionInvokeLastMethod() {
        String threadName = Thread.currentThread().getName();
        log.info("controller 执行，线程名称:{}", threadName);
        asyncAndTransactionalTestService.asyncExceptionInvokeLastMethod();
    }
}
