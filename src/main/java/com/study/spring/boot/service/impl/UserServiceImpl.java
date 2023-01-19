package com.study.spring.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.spring.boot.pojo.User;
import com.study.spring.boot.service.UserService;
import com.study.spring.boot.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author javal
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-01-19 13:37:00
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




