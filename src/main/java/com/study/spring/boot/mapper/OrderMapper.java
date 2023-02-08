package com.study.spring.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.spring.boot.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author javal
 * @description 针对表【order】的数据库操作Mapper
 * @createDate 2023-01-19 13:41:10
 * @Entity com.study.spring.boot.pojo.Order
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}




