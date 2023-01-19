package com.study.spring.boot.mapper;

import com.study.spring.boot.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author javal
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-01-19 13:37:00
* @Entity com.study.spring.boot.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




