package com.atguigu.dao;

import com.atguigu.pojo.Address;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author RZZhang
 * @create 2021-04-12 1:38
 */
public interface AddressDao {
    void deleteById(Integer id);

    Page<Address> selectByCondition(@Param("queryString") String queryString);

    List<Address> findAll();

    void addAddress(Address address);
}
