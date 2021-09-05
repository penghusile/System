package com.web2019.team4.system.Controller;



import com.web2019.team4.system.Common.Utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @Author sqs
 * @Date 2020年10月17日
 * @Version 1.0
 **/
@RestController
public class TestController {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;// 通过构造方法注入该对象

    @GetMapping("/getdata")
    public Object getdata(){
        //测试redis

        redisUtil.set("name","小猫");
        System.out.println(redisUtil.getExpire("name"));
        return redisUtil.get("name");

    }
}