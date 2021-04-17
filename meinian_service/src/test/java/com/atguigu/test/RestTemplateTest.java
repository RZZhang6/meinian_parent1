package com.atguigu.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RZZhang
 * @create 2021-02-12 22:30
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext-service.xml")
//@ContextConfiguration(classes = {RestConfig.class})
public class RestTemplateTest {

    @Autowired
    RestTemplate restTemplate;

    //测试使用restTemplate作为http的客户端向http服务端发起请求
    @Test
    public void gethtml() {
        String url = "http://www.baidu.com/";
        //向url发送http请求，得到响应结果
        String body = restTemplate.getForObject(url, String.class);
        System.out.println(body);
    }

    //测试使用restTemplate作为http的客户端向http服务端发起请求
    @Test
    public void getrest() {
        System.out.println(restTemplate);
    }

    //向验证码服务发送请求，获取验证码
    //http://localhost:56085/sailing/generate?effectiveTime=600&name=sms
    @Test
    public void getSmsCode() {

        String url = "http://localhost:56085/sailing/generate?effectiveTime=600&name=sms";
        Map<String, Object> body = new HashMap<>();
        body.put("mobile", "13131313");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> httpEntity = new HttpEntity<>(body, httpHeaders);


        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
//        log.info("请求验证码服务，得到响应:{}", JSON.toJSONString(exchange));
        Map bodyMap = exchange.getBody();
        System.out.println(bodyMap);
        Map result = (Map) bodyMap.get("result");
        String key = (String) result.get("key");
        System.out.println(key);

    }

}
