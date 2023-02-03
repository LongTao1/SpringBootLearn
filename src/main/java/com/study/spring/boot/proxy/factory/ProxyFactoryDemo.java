package com.study.spring.boot.proxy.factory;

import com.study.spring.boot.basic.service.UserService;
import com.study.spring.boot.basic.service.impl.CglibUserService;
import com.study.spring.boot.basic.service.impl.UserServiceImpl;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author LongTao
 * created on 2023/1/19
 */
public class ProxyFactoryDemo {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(userService);
        proxyFactory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("before...");
                Object result = invocation.proceed();
                System.out.println("after...");
                return result;
            }
        });

        UserService userServiceProxy = (UserService) proxyFactory.getProxy();
        userServiceProxy.test();

        System.out.println("----------------------cglib代理如下-------------------------------");


        CglibUserService cglibUserService = new CglibUserService();

        ProxyFactory cglibProxyFactory = new ProxyFactory();
        cglibProxyFactory.setTarget(cglibUserService);
        cglibProxyFactory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("before...");
                Object result = invocation.proceed();
                System.out.println("after...");
                return result;
            }
        });

        CglibUserService cglibUserServiceProxy = (CglibUserService) cglibProxyFactory.getProxy();
        cglibUserServiceProxy.test();
    }
}
