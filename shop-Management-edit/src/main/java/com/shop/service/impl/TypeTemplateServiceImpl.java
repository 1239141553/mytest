package com.shop.service.impl;

import com.shop.mapper.TypeTemplateMapper;
import com.shop.pojo.TypeTemplate;
import com.shop.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    private TypeTemplateMapper typeTemplateMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void addTypeTemplate(TypeTemplate typeTemplate){
        typeTemplateMapper.insert(typeTemplate);
        redisTemplate.boundHashOps("TypeTemplate").delete(typeTemplate.getId());
    }
    @Override
    public void delTypeTemplate(Long[] ids){
        for (Long id :ids){
            typeTemplateMapper.deleteByPrimaryKey(id);
            redisTemplate.boundHashOps("typeTemplate").delete(id);
        }
    }
    @Override
    public void updateTypeTemplate(TypeTemplate typeTemplate){
        TypeTemplate oldTypeTemplate =typeTemplateMapper.selectByPrimaryKey(typeTemplate.getId());
        redisTemplate.boundHashOps("typeTemplate").delete(oldTypeTemplate.getId());
        typeTemplateMapper.updateByPrimaryKey(typeTemplate);
        if(oldTypeTemplate.getId()!= typeTemplate.getId()){
            redisTemplate.boundHashOps("typeTemplate").delete(oldTypeTemplate.getId());
        }

    }

}
