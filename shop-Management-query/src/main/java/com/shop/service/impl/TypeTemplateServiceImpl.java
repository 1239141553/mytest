package com.shop.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shop.mapper.TypeTemplateMapper;
import com.shop.pojo.ResultPage;
import com.shop.pojo.TypeTemplate;
import com.shop.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

    @Autowired
    private TypeTemplateMapper typeTemplateMapper;

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
        TypeTemplate TypeTemplate = typeTemplateMapper.selectByPrimaryKey(id);
        return TypeTemplate;
    }

    @Override
    public List<TypeTemplate> queryAllTypeTemplate() {
        return typeTemplateMapper.selectByExample(null);
    }
}
