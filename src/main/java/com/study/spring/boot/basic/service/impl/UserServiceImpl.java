package com.study.spring.boot.basic.service.impl;

import com.study.spring.boot.basic.service.UserService;

import java.time.LocalDateTime;

/**
 * @author LongTao
 * created on 2023/1/19
 */
public class UserServiceImpl implements UserService {
    @Override
    public void test() {
        System.out.println("jdk proxy test");
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now().minusHours(24);
        System.out.println(localDateTime);
    }
}
