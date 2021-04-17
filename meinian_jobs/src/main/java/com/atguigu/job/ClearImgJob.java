package com.atguigu.job;

import com.atguigu.constant.RedisConstant;
import com.atguigu.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

/**
 * @author RZZhang
 * @create 2021-03-13 1:05
 */
public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg() {
        //计算redis中两个集合的差值，获取垃圾图片名称
        // 需要注意：在比较的时候，数据多的放到前面，如果pic多，那么pic放到前面，db多，db放到前面
        Set<String> pics = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        Iterator<String> iterator = pics.iterator();
        while (iterator.hasNext()) {
            String pic = iterator.next();
            System.out.println("删除的垃圾图片名称是: " + pic);
            //删除七牛云上的图片
            QiniuUtils.deleteFileFromQiniu(pic);
            //删除Redis中的垃圾图片数据
            jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES, pic);
        }

    }
}