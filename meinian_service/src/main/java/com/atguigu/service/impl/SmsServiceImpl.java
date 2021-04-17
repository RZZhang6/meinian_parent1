package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.atguigu.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RZZhang
 * @create 2021-03-24 1:30
 */
@Service(interfaceClass = SmsService.class)
//@Transactional
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RestTemplate restTemplate;

    Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);

    //获取手机验证码，发送给用户，这里模拟打印打控制台
    @Override
    public void sendShortMessage2(String telephone) {
        //向验证码服务发送请求的地址
        String sms_url = "http://localhost:56085/sailing/generate?name=sms&effectiveTime=600";

        Map<String, Object> body = new HashMap<>();
        body.put("mobile", telephone);
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Object> httpEntity = new HttpEntity<>(body, httpHeaders);

        Map bodyMap = null;
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            ResponseEntity<Map> exchange = restTemplate.exchange(sms_url, HttpMethod.POST, httpEntity, Map.class);
            log.info("请求验证码服务，得到响应:{}", JSON.toJSONString(exchange));
            bodyMap = exchange.getBody();
        }catch (RestClientException e){
            e.printStackTrace();
            throw new RuntimeException("发送验证码失败");
        }
        if (bodyMap == null || bodyMap.get("result") == null) {
            throw new RuntimeException("发送验证码失败");
        }
        Map result = (Map) bodyMap.get("result");
        String key = (String) result.get("key");
        log.info("得到发送验证码对应的key:{}",key);
        System.out.println(key);
    }
}
