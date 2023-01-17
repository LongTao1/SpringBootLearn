package com.study.spring.boot.config;

import com.study.spring.boot.pojo.User;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@ToString
@ConfigurationProperties(prefix = "yml")
public class ParamYmlConfig {
    private String name;
    private String description;
    private String javaHome;

    private List<String> recipients;

    private Map<String, String> headers;

    private User user;


}
