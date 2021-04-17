package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.dao.TravelGroupDao;
import com.atguigu.entity.PageResult;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.service.TravelGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author RZZhang
 * @create 2021-02-28 17:46
 */
@Service(interfaceClass = TravelGroupService.class)
@Transactional
public class TravelGroupServiceImpl implements TravelGroupService {

    @Autowired
    private TravelGroupDao travelGroupDao;

    @Override
    public void add(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.add(travelGroup);
        Integer travelGroupId = travelGroup.getId();
        setTravelGroupAndTravelItem(travelGroupId, travelItemIds);
    }

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<TravelGroup> page = travelGroupDao.findPage(queryString);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public TravelGroup getById(Integer id) {
        return travelGroupDao.getById(id);
    }

    @Override
    public List<Integer> getTravelitemIdsByTravelGroupId(Integer travelGroupId) {
        return travelGroupDao.getTravelitemIdsByTravelGroupId(travelGroupId);
    }

    @Override
    public void edit(Integer[] travelItemIds, TravelGroup travelGroup) {
        travelGroupDao.edit(travelGroup);
        Integer travelGroupId = travelGroup.getId();

        //先删除中间表的关联数据
        travelGroupDao.delete(travelGroupId);

        //重新再增加关联数据
        setTravelGroupAndTravelItem(travelGroupId, travelItemIds);

    }

    @Override
    public List<TravelGroup> findAll() {
        return travelGroupDao.findAll();
    }

    private void setTravelGroupAndTravelItem(Integer travelGroupId, Integer[] travelItemIds) {
        if (travelItemIds != null && travelItemIds.length > 0) {
            for (Integer travelItemId : travelItemIds) {
                Map<String, Integer> paramData = new HashMap<>();
                paramData.put("travelGroupId", travelGroupId);
                paramData.put("travelItemId", travelItemId);
                travelGroupDao.addTravelGroupAndTravelItem(paramData);
            }
        }
    }


}
