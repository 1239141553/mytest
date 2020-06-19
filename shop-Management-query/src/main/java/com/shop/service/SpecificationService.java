package com.shop.service;

import com.shop.pojo.Brand;
import com.shop.pojo.ResultPage;
import com.shop.pojo.Specification;
import com.shop.pojocom.SpecificationCom;

import java.util.List;
import java.util.Map;


public interface SpecificationService {
    ResultPage queryAllSpecification(int pageNum, int pageSize);
    SpecificationCom querySpecificationById(Long id);
    ResultPage querySpecificationLike(int pageNum, int pageSize, String specName);
    List<Map> selectOptionList();
}
