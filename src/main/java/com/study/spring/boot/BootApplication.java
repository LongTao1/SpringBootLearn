package com.study.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.study.spring.boot.mapper")
public class BootApplication {


    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}

