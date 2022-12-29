package com.study.spring.boot.config;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "my")
public class ParamConfig {

    private String secret;
    private Integer number;
    private long bignumber;
    private String uuid;
    @Value("${my.number.less.than.ten}")
    private Integer ten;
    @Value("${my.number.in.range}")
    private int range;
}
