package com.shop.pojocom;

import com.shop.pojo.Specification;
import com.shop.pojo.SpecificationOption;

import java.util.List;

public class SpecificationCom {
    private Specification specification;
    private List<SpecificationOption> specificationOptions;

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public List<SpecificationOption> getSpecificationOptions() {
        return specificationOptions;
    }

    public void setSpecificationOptions(List<SpecificationOption> specificationOptions) {
        this.specificationOptions = specificationOptions;
    }
}
