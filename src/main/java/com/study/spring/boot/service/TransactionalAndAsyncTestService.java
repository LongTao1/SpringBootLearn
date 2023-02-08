package com.study.spring.boot.service;

import com.study.spring.boot.mapper.OrderItemMapper;
import com.study.spring.boot.mapper.OrderMapper;
import com.study.spring.boot.mapper.UserMapper;
import com.study.spring.boot.pojo.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author LongTao
 * created on 2023/1/19
 */
@Service
public class TransactionalAndAsyncTestService {
    private final OrderMapper orderMapper;

    private final OrderItemMapper orderItemMapper;

    private final UserMapper userMapper;

    public TransactionalAndAsyncTestService(OrderMapper orderMapper, OrderItemMapper orderItemMapper, UserMapper userMapper) {
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.userMapper = userMapper;
    }

    @Async
    @Transactional(rollbackFor = Exception.class)
    public void tranAndAsync() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName());
    /*    Order build = Order.builder()
          .amount(new BigDecimal("32.23"))
          .orderNum("123123")
          .build();
        orderMapper.insert(build);
        throw new RuntimeException("自定义异常");*/

        User user = User.builder()
                .name("哈哈1")
                .age(1213)
                .build();
        userMapper.insert(user);
        throw new RuntimeException("哈哈哈哈哈异常");
    }
}
