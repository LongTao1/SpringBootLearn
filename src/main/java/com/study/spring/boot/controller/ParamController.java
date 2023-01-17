package com.study.spring.boot.controller;

import com.study.spring.boot.config.CustomSettingConfig;
import com.study.spring.boot.config.LooseBindingConfig;
import com.study.spring.boot.config.ParamConfig;
import com.study.spring.boot.config.ParamYmlConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping
public class ParamController {
    private final ParamConfig paramConfig;

    private final LooseBindingConfig looseBindingConfig;

    private final ParamYmlConfig paramYmlConfig;

    private final CustomSettingConfig customSettingConfig;

    @Value("${loopsmart.appkey:80cf31b1-7bef-40fe-8bb6-5e79688db994}")
    private String appKey;

    @Value("${loopsmart.appsecret:65655f5d020a117d11609007bd810adaf7987989}")
    private String appSecret;

    /**
     * 接口鉴权
     */
    public static String ACCESS_AUTH;
    @Value("${loopsmart.accessAuth:}")
    private void setAccessAuth(){
        ACCESS_AUTH = "Bearer "+ Base64.getEncoder().encodeToString(("app:"+appKey+":"+appSecret).getBytes());
    }


    public ParamController(ParamConfig paramConfig, CustomSettingConfig customSettingConfig, LooseBindingConfig looseBindingConfig, ParamYmlConfig paramYmlConfig) {
        this.paramConfig = paramConfig;
        this.customSettingConfig = customSettingConfig;
        this.looseBindingConfig = looseBindingConfig;
        this.paramYmlConfig = paramYmlConfig;
    }

    @GetMapping("/param")
    public String getParam() {
        return paramConfig.toString();
    }

    @GetMapping("/param/yml")
    public String getParamYml() {
        return paramYmlConfig.toString();
    }

    @GetMapping("/custom")
    public String getCustom() {
        return customSettingConfig.getName();
    }

    @GetMapping("/loose")
    public String getLooseParam() {
        System.out.println(ACCESS_AUTH);
        return looseBindingConfig.toString();
    }
}
