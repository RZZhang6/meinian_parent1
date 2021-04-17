package com.atguigu.dao;

import com.atguigu.pojo.Permission;

import java.util.Set;

/**
 * @author RZZhang
 * @create 2021-04-01 1:31
 */
public interface PermissionDao {


    Set<Permission> findPermissionsByRoleId(Integer roleId);
}
