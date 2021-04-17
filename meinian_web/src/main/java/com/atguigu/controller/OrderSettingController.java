package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.constant.MessageConstant;
import com.atguigu.entity.Result;
import com.atguigu.pojo.OrderSetting;
import com.atguigu.service.OrderSettingService;
import com.atguigu.util.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author RZZhang
 * @create 2021-03-16 22:51
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {


    @Reference
    private OrderSettingService orderSettingService;

    // <el-upload action="/ordersetting/upload.do"

    /*@RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile file) {
        try {
            // 使用poi工具类解析excel文件，读取里面的内容
            List<String[]> lists = POIUtils.readExcel(file);
            List<OrderSetting> orderSettings = new ArrayList<>();
            for (String[] row : lists) {
                OrderSetting orderSetting = new OrderSetting(new Date(row[0]), Integer.parseInt(row[1]));
                orderSettings.add(orderSetting);
            }
            orderSettingService.add(orderSettings);
            return new Result(true, MessageConstant.UPLOAD_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.UPLOAD_FAIL);
        }
    }*/

    @RequestMapping("upload")
    public Result upload(@RequestParam("excelFile") MultipartFile file) {

        try {
            List<String[]> rows = POIUtils.readExcel(file);
            List<OrderSetting> orderSettings = new ArrayList<>();
            for (String[] row : rows) {
                OrderSetting orderSetting = new OrderSetting(new Date(row[0]), Integer.parseInt(row[1]));
                orderSettings.add(orderSetting);
            }
            orderSettingService.add(orderSettings);
            return new Result(true, MessageConstant.UPLOAD_SUCCESS);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.UPLOAD_FAIL);
        }

    }


    // axios.get("/ordersetting/getOrderSettingByMonth.do?date=" + this.currentYear + "-" + this.currentMonth).then((resp) => {
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date) { //参数格式为：2019-03
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    //axios.post("/ordersetting/editNumberByDate.do", {
    /**
     * 根据指定日期修改可预约人数
     * @param orderSetting
     * @return
     */
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberByDate(orderSetting);
            //预约设置成功
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            //预约设置失败
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
