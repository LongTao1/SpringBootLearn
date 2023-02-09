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
                .name("张三")
                .age(22)
                .build();
        userMapper.insert(user);
        throw new RuntimeException("新增数据库异常");
    }


    @Transactional(rollbackFor = Exception.class)
    public void syncException() {
        AsyncAndTransactionalTestService bean = applicationContext.getBean(this.getClass());
        bean.addOrder("123", false);
        bean.addOrderItem("123", false);
        throw new RuntimeException("自定义异常");
    }

    @Transactional(rollbackFor = Exception.class)
    public void asyncException() {
        AsyncAndTransactionalTestService bean = applicationContext.getBean(this.getClass());
        bean.addOrder("456", true);
        bean.addOrderItem("456", true);
    }

    @Async
    @Transactional(rollbackFor = Exception.class)
    public void addOrder(String orderNo, boolean exceptionFlag) {
        Order build = Order.builder()
                .orderNo(orderNo)
                .amount(new BigDecimal("98"))
                .build();
        orderMapper.insert(build);
        if (exceptionFlag) {
            throw new RuntimeException("自定义异常");
        }
    }


    @Async
    @Transactional(rollbackFor = Exception.class)
    public void addOrderItem(String orderNo, boolean exceptionFlag) {
        OrderItem build = OrderItem.builder()
                .orderNo(orderNo)
                .productId(98)
                .productName("测试商品")
                .productPrice(new BigDecimal("98"))
                .build();
        orderItemMapper.insert(build);
        if (exceptionFlag) {
            throw new RuntimeException("自定义异常");
        }
    }
}
