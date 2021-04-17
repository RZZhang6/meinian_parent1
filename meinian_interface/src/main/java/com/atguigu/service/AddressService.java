package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.pojo.Address;

import java.util.List;

/**
 * @author RZZhang
 * @create 2021-04-12 1:33
 */
public interface AddressService {
    void deleteById(Integer id);


    PageResult findPage(QueryPageBean queryPageBean);

    List<Address> findAll();

    void addAddress(Address address);
}
