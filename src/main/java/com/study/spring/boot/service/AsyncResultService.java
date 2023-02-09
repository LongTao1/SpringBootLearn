package com.study.spring.boot.service;

import com.study.spring.boot.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AsyncResultService {
    private static final Integer maxSize = 10;

    @Async
    public Future<List<User>> testAsync(String prefix, Long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String threadName = Thread.currentThread().getName();
        List<User> users = new ArrayList<>(maxSize);
        for (int i = 0; i < maxSize; i++) {
            User build = User.builder()
                    .name(prefix + "：" + threadName + "：" + i)
                    .age(i)
                    .build();
            users.add(build);

        }
        log.info(threadName + "：执行完成");
        return AsyncResult.forValue(users);
        //其他写法
        //return new AsyncResult<>(users);
    }
}
