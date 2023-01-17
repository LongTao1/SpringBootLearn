package com.study.spring.boot.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
@ConfigurationProperties(prefix = "loose.binding")
public class LooseBindingConfig {
    private String firstName;
    private String lastName;
    private String nickName;
    private String IPAddrEss;
}
