package com.atguigu.dao;

import com.atguigu.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @author RZZhang
 * @create 2021-03-06 0:40
 */
public interface SetmealDao {
    void add(Setmeal setmeal);

    void addSetmealAndTravelGroup(Map<String, Integer> map);

    Page<Setmeal> findPage(String queryString);

    List<Setmeal> findAll();

    Setmeal findById(int id);

    Setmeal getSetmealById(Integer id);

    List<Map<String, Object>> findSetmealCount();
}
