package com.study.spring.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author LongTao
 * created on 2023/1/17
 */
@Slf4j
@Service
public class BatchAsyncService {
    @Autowired
    private AsyncService asyncService;

    public void batchAsync() {
        for (int i = 0; i < 5; i++) {
            asyncService.testAsync();
        }
    }
}
