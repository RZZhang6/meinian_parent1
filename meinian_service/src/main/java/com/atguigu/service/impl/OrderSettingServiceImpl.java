package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.OrderSettingDao;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author RZZhang
 * @create 2021-03-16 22:52
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService{

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> orderSettings) {
        // 1：遍历List<OrderSetting>
        for (OrderSetting orderSetting : orderSettings) {
            // 判断当前的日期之前是否已经被设置过预约日期，使用当前时间作为条件查询数量
            long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
            // 如果设置过预约日期，更新number数量
            if (count > 0) {
                orderSettingDao.editNumberByOrderDate(orderSetting);
            }else{
                // 如果没有设置过预约日期，执行保存
                orderSettingDao.add(orderSetting);
            }
        }
    }

    /**  传递的参数
     *   date（格式：2019-8）
     *  构建的数据List<Map>
     *    map.put("date",1);
     map.put("number",120);
     map.put("reservations",10);

     *  查询方案：SELECT * FROM t_ordersetting WHERE orderDate LIKE '2019-08-%'
     *  查询方案：SELECT * FROM t_ordersetting WHERE orderDate BETWEEN '2019-9-1' AND '2019-9-31'
     */
    //根据日期查询预约设置数据
    @Override
    public List<Map> getOrderSettingByMonth(String date) {

        String dateBegin = date + "-1";
        String dateEnd = date + "-31";
        Map<String, Object> map = new HashMap<>();

        map.put("dateBegin", dateBegin);
        map.put("dateEnd", dateEnd);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            orderSettingMap.put("date", orderSetting.getOrderDate().getDate());
            orderSettingMap.put("number", orderSetting.getNumber());
            orderSettingMap.put("reservations", orderSetting.getReservations());
            data.add(orderSettingMap);
        }
        return data;
    }

    //根据日期修改可预约人数

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count > 0) {
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        } else {
            //当前日期没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }
}
