package com.atguigu.service;

import com.atguigu.entity.PageResult;
import com.atguigu.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @author RZZhang
 * @create 2021-03-06 0:38
 */
public interface SetmealService {
    void add(Integer[] travelgroupIds, Setmeal setmeal);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    List<Setmeal> findAll();

    Setmeal findById(int id);

    Setmeal getSetmealById(Integer id);

    List<Map<String, Object>> findSetmealCount();
}
