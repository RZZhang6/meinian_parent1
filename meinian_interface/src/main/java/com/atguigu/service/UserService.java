package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @author RZZhang
 * @create 2021-04-01 1:27
 */
public interface UserService {

    User findUserByUsername(String username);
}
