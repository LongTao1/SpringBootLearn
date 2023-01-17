package com.study.spring.boot.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
@ConfigurationProperties(prefix = "properties")
//自定义配置文件加载
@PropertySource(
    //配置文件的路径
    value = {"classpath:static/config/customSetting.properties"},
    //当找不到配置文件时，是否报错
    ignoreResourceNotFound = false,
    //配置文件的编码
    encoding = "UTF-8",
    //指定名称
    name = "customSetting.properties")
public class CustomSettingConfig {
    private String name;
}
