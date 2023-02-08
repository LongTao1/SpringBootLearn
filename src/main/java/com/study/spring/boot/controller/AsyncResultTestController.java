package com.study.spring.boot.controller;

import com.alibaba.fastjson2.JSONObject;
import com.study.spring.boot.pojo.User;
import com.study.spring.boot.service.AsyncResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author LongTao
 * created on 2023/1/17
 */
@Slf4j
@RestController
public class AsyncResultTestController {
    @Autowired
    private AsyncResultService asyncResultService;


    @GetMapping("/async/result")
    public void testAsync() {
        String threadName = Thread.currentThread().getName();
        log.info("controller 执行，线程名称:{}", threadName);

        Future<List<User>> listFuture = asyncResultService.testAsync("第一次异步", 2L);
        Future<List<User>> listFuture1 = asyncResultService.testAsync("第二次异步", 4L);
        Future<List<User>> listFuture2 = asyncResultService.testAsync("第三次异步", 8L);
        while (!listFuture.isDone() || !listFuture1.isDone() || !listFuture2.isDone()) {
            log.info("第一个执行情况：{}，第二个执行情况：{}。第三个执行情况:{}", listFuture.isDone(), listFuture1.isDone(), listFuture2.isDone());
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Stream.of(listFuture, listFuture1, listFuture2)
                .map(future -> {
                    List<User> users = null;
                    try {
                        users = future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        log.error("获取异步执行结果失败,异常信息:{}", e.getMessage());
                    }
                    return users;
                })
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .forEach(item -> {
                    log.info("异步执行结果:{}", JSONObject.toJSONString(item));
                });

    }
}
