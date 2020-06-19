package com.shop.service;


import com.shop.pojo.ResultPage;
import com.shop.pojo.TypeTemplate;

import java.util.List;

public interface TypeTemplateService {
    ResultPage queryTypeTemplateByPage(int pageNum, int pageSize);
    TypeTemplate queryTypeTemplateById(Long id);
    List<TypeTemplate> queryAllTypeTemplate();

}
