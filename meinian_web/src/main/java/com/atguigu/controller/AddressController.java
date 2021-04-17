package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.PageResult;
import com.atguigu.entity.QueryPageBean;
import com.atguigu.entity.Result;
import com.atguigu.pojo.Address;
import com.atguigu.service.AddressService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    AddressService addressService;

    @RequestMapping("/deleteById")
    public Result deleteById(Integer id){
        addressService.deleteById(id);
        return new Result(true,"已删除地址");
    }

    @RequestMapping("/addAddress")
    public Result addAddress(@RequestBody Address address){
        //System.out.println(address.toString());
        addressService.addAddress(address);
        return new Result(true,"地址保存成功");
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult=null;
        try{
            pageResult= addressService.findPage(queryPageBean);
        }catch (Exception e){
            e.printStackTrace();
        }
        return pageResult;
    }

    @RequestMapping("/findAllMaps")
    public Map findAllMaps(){

        /*Map map = new HashMap();

        List<Address> list = addressService.findAllMaps();

        List<Map> gridnMaps = new ArrayList(); //标记地址的经纬度

        List<Map> nameMaps = new ArrayList<>(); //标记地址名称

        for (Address address : list) {
            String addressName = address.getAddressName();
            Map<String,String> mapName = new HashMap<>();
            mapName.put("addressName",addressName);
            nameMaps.add(mapName);

            Map<String,String> gridnMap = new HashMap<>();
            gridnMap.put("lng",address.getLng());
            gridnMap.put("lat",address.getLat());
            gridnMaps.add(gridnMap);
        }

        map.put("gridnMaps",gridnMaps);
        map.put("nameMaps",nameMaps);
        return map ;*/
        Map map = new HashMap();
        List<Address> addressList = addressService.findAll();

        //1、定义分店坐标集合
        List<Map> gridnMaps = new ArrayList<>();
        //2、定义分店名称集合
        List<Map> nameMaps = new ArrayList<>();

        for (Address address : addressList) {
            Map gridnMap = new HashMap();
            // 获取经度
            gridnMap.put("lng", address.getLng());
            // 获取纬度
            gridnMap.put("lat", address.getLat());
            gridnMaps.add(gridnMap);

            Map nameMap = new HashMap();
            nameMap.put("addressName", address.getAddressName());
            nameMaps.add(nameMap);
        }
        // 存放经纬度
        map.put("gridnMaps",gridnMaps);
        // 存放名字
        map.put("nameMaps",nameMaps);
        return map;
    }
}
