package com.study.spring.boot.proxy.cglib;

import com.study.spring.boot.basic.service.impl.CglibUserService;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author LongTao
 * created on 2023/1/19
 */
public class ProxyDemo {
    public static void main(String[] args) {
        CglibUserService cglibUserService = new CglibUserService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibUserService.class);

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before...");
                Object result = methodProxy.invoke(cglibUserService, objects);
                System.out.println("after...");
                return result;
            }
        });

        enhancer.setCallbacks(new Callback[]{new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("setCallbacks before...");
                Object result = methodProxy.invoke(cglibUserService, objects);
                System.out.println("setCallbacks after...");
                return result;
            }
        }});

        CglibUserService o = (CglibUserService)enhancer.create();
        o.test();
    }
}
