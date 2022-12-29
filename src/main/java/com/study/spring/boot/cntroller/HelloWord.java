package com.study.spring.boot.cntroller;

import com.study.spring.boot.config.ParamConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWord {

    @Autowired
    private ParamConfig paramConfig;

    @GetMapping("helloWord")
    public String helloWord() {
        System.out.println(paramConfig.toString());
        return "Hello Word";
    }
}
