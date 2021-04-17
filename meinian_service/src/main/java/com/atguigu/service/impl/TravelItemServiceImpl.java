package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelItemDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelItem;
import com.atguigu.service.TravelItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author RZZhang
 * @create 2021-02-24 19:08
 */
@Service(interfaceClass = TravelItemService.class) //发布服务，注册到zk中心
@Transactional // 声明式事务
public class TravelItemServiceImpl implements TravelItemService {

    @Autowired
    TravelItemDao travelItemDao;



    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        // 不使用分页插件PageHelper
        // 至少写2条sql语句完成查询
        // 第1条，select count(*) from t_travelitem，查询的结果封装到PageResult中的total
        // 第2条，select * from t_travelitem where NAME = '001' OR CODE = '001' limit ?,?
        //（0,10）（10,10）(（currentPage-1)*pageSize,pageSize）
        // 使用分页插件PageHelper（简化上面的操作）
        // 1：初始化分页操作
        PageHelper.startPage(currentPage,pageSize);
        // 2：使用sql语句进行查询（不必在使用mysql的limit了）
        Page<TravelItem> page = travelItemDao.findPage(queryString);
        // 3：封装
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        //查自由行关联表中是否存在关联数据，如果存在，就抛异常， 不进行删除
        long count = travelItemDao.findCountByTravelitemId(id);
        if (count > 0) {
            //有关联数据，
            throw new RuntimeException("删除自由行失败，因为存在关联数据，先解除关系，再删除！");
        }

        // 使用自由行的id进行删除
        travelItemDao.deleteById(id);
    }

    @Override
    public TravelItem getById(Integer id) {

        return travelItemDao.getById(id);
    }

    @Override
    public void edit(TravelItem travelItem) {
        travelItemDao.edit(travelItem);
    }

    @Override
    public List<TravelItem> findAll() {
        return travelItemDao.findAll();
    }


}
