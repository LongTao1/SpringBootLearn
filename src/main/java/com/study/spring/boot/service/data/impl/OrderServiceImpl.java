package com.study.spring.boot.service.data.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.spring.boot.mapper.OrderMapper;
import com.study.spring.boot.pojo.Order;
import com.study.spring.boot.service.data.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author javal
 * @description 针对表【order】的数据库操作Service实现
 * @createDate 2023-01-19 13:41:10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {

}




