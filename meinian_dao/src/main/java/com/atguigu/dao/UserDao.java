package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * @author RZZhang
 * @create 2021-04-01 1:31
 */
public interface UserDao {
    User findUserByUsername(String username);
}
