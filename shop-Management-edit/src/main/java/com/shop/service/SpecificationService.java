package com.shop.service;

import com.shop.pojo.Specification;
import com.shop.pojocom.SpecificationCom;

public interface SpecificationService {
    void addSpecification(SpecificationCom specificationCom);
    void delSpecification(Long[] ids);
    void updateSpecification(SpecificationCom specificationCom);
}
