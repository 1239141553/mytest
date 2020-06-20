package com.shop.service.impl;

import com.shop.mapper.TypeTemplateMapper;
import com.shop.pojo.TypeTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class TypeTemplateService {

    @Autowired
    private TypeTemplateMapper typeTemplateMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public void addTypeTemplate(TypeTemplate TypeTemplate){
        typeTemplateMapper.insert(TypeTemplate);
        redisTemplate.boundHashOps("TypeTemplate").delete(TypeTemplate.getId());
    }

    public void delTypeTemplate(Long[] ids){
        for (Long id :ids){
            typeTemplateMapper.deleteByPrimaryKey(id);
            redisTemplate.boundHashOps("TypeTemplate").delete(id);
        }
    }

    public void updateTypeTemplate(TypeTemplate TypeTemplate){
        TypeTemplate oldTypeTemplate =typeTemplateMapper.selectByPrimaryKey(TypeTemplate.getId());
        redisTemplate.boundHashOps("TypeTemplate").delete(oldTypeTemplate.getId());
        typeTemplateMapper.updateByPrimaryKey(TypeTemplate);
        if(oldTypeTemplate.getId()!= TypeTemplate.getId()){
            redisTemplate.boundHashOps("TypeTemplate").delete(oldTypeTemplate.getId());
        }

    }

}
