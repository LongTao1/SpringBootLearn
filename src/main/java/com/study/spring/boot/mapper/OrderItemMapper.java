package com.study.spring.boot.mapper;

import com.study.spring.boot.pojo.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author javal
* @description 针对表【order_item】的数据库操作Mapper
* @createDate 2023-01-19 13:36:56
* @Entity com.study.spring.boot.pojo.OrderItem
*/
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}




