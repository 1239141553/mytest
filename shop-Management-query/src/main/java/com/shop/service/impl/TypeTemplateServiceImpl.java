package com.shop.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shop.mapper.TypeTemplateMapper;
import com.shop.pojo.ResultPage;
import com.shop.pojo.TypeTemplate;
import com.shop.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    private TypeTemplateMapper typeTemplateMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultPage queryTypeTemplateByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TypeTemplate> page = (Page<TypeTemplate>) typeTemplateMapper.selectByExample(null);
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(page.getResult());
        resultPage.setTotal(page.getTotal());
        return resultPage;
    }

    @Override
    public TypeTemplate queryTypeTemplateById(Long id) {
        TypeTemplate typeTemplate = (TypeTemplate) redisTemplate.boundHashOps(
                "typeTemplate").get(id);
        if (typeTemplate==null){
             typeTemplate = typeTemplateMapper.selectByPrimaryKey(id);
             redisTemplate.boundHashOps("typeTemplate").put(id, typeTemplate);
        }
        return typeTemplate;
    }

    @Override
    public List<TypeTemplate> queryAllTypeTemplate() {
        return typeTemplateMapper.selectByExample(null);
    }
}
