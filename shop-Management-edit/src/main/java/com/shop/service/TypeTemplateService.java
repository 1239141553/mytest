package com.shop.service;


import com.shop.pojo.TypeTemplate;

public interface TypeTemplateService {
    void addTypeTemplate(TypeTemplate typeTemplate);
    void delTypeTemplate(Long[] ids);
    void updateTypeTemplate(TypeTemplate typeTemplate);
}
