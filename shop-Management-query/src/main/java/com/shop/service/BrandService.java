package com.shop.service;

import com.shop.pojo.Brand;
import com.shop.pojo.ResultPage;

import java.util.List;
import java.util.Map;


public interface BrandService {
    ResultPage queryAllBrand(int pageNum,int pageSize);
    Brand queryBrandById(Long id);
    List<Map> selectOptionList();
}
