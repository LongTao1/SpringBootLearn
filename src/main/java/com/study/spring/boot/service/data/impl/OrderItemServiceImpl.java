package com.study.spring.boot.service.data.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.spring.boot.mapper.OrderItemMapper;
import com.study.spring.boot.pojo.OrderItem;
import com.study.spring.boot.service.data.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * @author javal
 * @description 针对表【order_item】的数据库操作Service实现
 * @createDate 2023-01-19 13:36:56
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem>
        implements OrderItemService {

}




