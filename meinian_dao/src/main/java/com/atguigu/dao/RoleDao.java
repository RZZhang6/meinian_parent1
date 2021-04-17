package com.atguigu.dao;

import com.atguigu.pojo.Role;

import java.util.Set;

/**
 * @author RZZhang
 * @create 2021-04-01 1:31
 */
public interface RoleDao {

    Set<Role> findRolesByUserId(Integer userId);
}
