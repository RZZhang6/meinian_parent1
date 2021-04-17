package com.atguigu.dao;

import com.atguigu.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author RZZhang
 * @create 2021-03-16 22:52
 */
public interface OrderSettingDao {
    public long findCountByOrderDate(Date orderDate) ;


    public void editNumberByOrderDate(OrderSetting orderSetting);


    public void add(OrderSetting orderSetting) ;

    List<OrderSetting> getOrderSettingByMonth(Map<String, Object> map);

    OrderSetting getOrderSettingByOrderDate(Date date);

    void editReservationsByOrderDate(OrderSetting orderSetting);
}
