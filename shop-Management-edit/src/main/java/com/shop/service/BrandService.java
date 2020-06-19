package com.shop.service;

import com.shop.pojo.Brand;

public interface BrandService {
    void addBrand(Brand brand);
    void delBrand(Long[] ids);
    void updateBrand(Brand brand);
}
