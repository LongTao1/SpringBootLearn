package com.study.spring.boot.proxy.jdk;

import com.study.spring.boot.basic.service.UserService;
import com.study.spring.boot.basic.service.impl.UserServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author LongTao
 * created on 2023/1/19
 */
public class ProxyDemo {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        Object proxy = Proxy.newProxyInstance(userService.getClass().getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before...");
                Object result = method.invoke(userService, args);
                System.out.println("after...");
                return result;
            }
        });
        UserService userServiceProxy = (UserService) proxy;
        userServiceProxy.test();
    }
}
