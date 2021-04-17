package com.atguigu.dao;

import com.atguigu.pojo.TravelGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author RZZhang
 * @create 2021-02-28 17:46
 */
public interface TravelGroupDao {


    void add(TravelGroup travelGroup);

    void addTravelGroupAndTravelItem(Map<String, Integer> paramData);

    Page<TravelGroup> findPage(@Param("queryString") String queryString);

    TravelGroup getById(Integer id);

    List<Integer> getTravelitemIdsByTravelGroupId(@Param("travelGroupId") Integer travelGroupId);

    void edit(TravelGroup travelGroup);

    void delete(Integer travelGroupId);

    List<TravelGroup> findAll();

    List<TravelGroup> findTravelGroupListById(Integer id);
}
