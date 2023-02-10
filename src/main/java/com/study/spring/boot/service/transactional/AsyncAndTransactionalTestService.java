package com.study.spring.boot.service.transactional;

import com.study.spring.boot.mapper.OrderItemMapper;
import com.study.spring.boot.mapper.OrderMapper;
import com.study.spring.boot.mapper.UserMapper;
import com.study.spring.boot.pojo.Order;
import com.study.spring.boot.pojo.OrderItem;
import com.study.spring.boot.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author LongTao
 * created on 2023/1/19
 */
@Slf4j
@Service
public class AsyncAndTransactionalTestService {
    private final OrderMapper orderMapper;

    private final OrderItemMapper orderItemMapper;

    private final UserMapper userMapper;

    @Autowired
    private ApplicationContext applicationContext;

    public AsyncAndTransactionalTestService(OrderMapper orderMapper, OrderItemMapper orderItemMapper, UserMapper userMapper) {
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.userMapper = userMapper;
    }


    @Async
    @Transactional(rollbackFor = Exception.class)
    public void sameMethod() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("当前线程名称:{}", Thread.currentThread().getName());

        User user = User.builder()
                .name("异步新增")
                .age(11)
                .build();
        userMapper.insert(user);
        throw new RuntimeException("新增数据库异常");
    }


    @Transactional(rollbackFor = Exception.class)
    public void syncException() {
        AsyncAndTransactionalTestService bean = applicationContext.getBean(this.getClass());
        bean.addOrder("异步新增", 11, false);
        bean.addOrderItem("异步新增", 11, false);
        User user = User.builder()
                .name("同步新增异常")
                .age(22)
                .build();
        userMapper.insert(user);
        throw new RuntimeException("新增数据库异常");
    }

    @Transactional(rollbackFor = Exception.class)
    public void asyncException() {
        AsyncAndTransactionalTestService bean = applicationContext.getBean(this.getClass());
        bean.addOrder("异步新增异常", 22, true);
        bean.addOrderItem("异步新增异常", 22, true);
        User user = User.builder()
                .name("同步新增")
                .age(33)
                .build();
        userMapper.insert(user);
    }

    @Async
    @Transactional(rollbackFor = Exception.class)
    public void addOrder(String orderNo, Integer amount, boolean exceptionFlag) {
        Order build = Order.builder()
                .orderNo(orderNo)
                .amount(new BigDecimal(amount.toString()))
                .build();
        orderMapper.insert(build);
        if (exceptionFlag) {
            throw new RuntimeException("自定义异常");
        }
    }


    @Async
    @Transactional(rollbackFor = Exception.class)
    public void addOrderItem(String orderNo, Integer amount, boolean exceptionFlag) {
        OrderItem build = OrderItem.builder()
                .orderNo(orderNo)
                .productId(1)
                .productName("测试商品")
                .productPrice(new BigDecimal(amount.toString()))
                .build();
        orderItemMapper.insert(build);
        if (exceptionFlag) {
            throw new RuntimeException("自定义异常");
        }
    }


    public void lastMethodException() {
        AsyncAndTransactionalTestService bean = applicationContext.getBean(this.getClass());
        bean.invokeLastMethod(false, true);
        User user = User.builder()
                .name("调用最后一个方法测试新增")
                .age(44)
                .build();
        userMapper.insert(user);
    }

    public void asyncExceptionInvokeLastMethod() {
        AsyncAndTransactionalTestService bean = applicationContext.getBean(this.getClass());
        bean.invokeLastMethod(true, false);
        User user = User.builder()
                .name("调用最后一个方法测试新增")
                .age(55)
                .build();
        userMapper.insert(user);
    }


    @Async
    @Transactional
    public void invokeLastMethod(boolean exceptionFlag, boolean lastMethodExceptionFlag) {
        addOrder("调用最后一个方法前异步新增", 33, false);
        lastMethod(lastMethodExceptionFlag);
        if (exceptionFlag) {
            throw new RuntimeException("异步方法异常");
        }
    }


    public void lastMethod(boolean exceptionFlag) {
        addOrderItem("最后调用的方法新增", 33, false);
        log.info("最后调用的方法");
        if (exceptionFlag) {
            throw new RuntimeException("最后调用方法的异常");
        }
    }


}
