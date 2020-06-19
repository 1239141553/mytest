package com.shop;

import com.shop.mapper.TbBrandMapper;
import com.shop.pojo.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
public class test {
    @Autowired
    private TbBrandMapper tbBrandMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void test1(){
        stringRedisTemplate.opsForValue().set("kk", "wtt");
    }
    @Test
    public  void  test2(){
        String kk = stringRedisTemplate.opsForValue().get("kk");
        System.out.println(kk);
    }
}
