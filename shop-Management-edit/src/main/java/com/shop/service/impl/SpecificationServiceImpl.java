package com.shop.service.impl;


import com.shop.mapper.SpecificationMapper;
import com.shop.mapper.SpecificationOptionMapper;
import com.shop.pojo.Specification;
import com.shop.pojo.SpecificationOption;
import com.shop.pojo.SpecificationOptionExample;
import com.shop.pojocom.SpecificationCom;
import com.shop.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    public void addSpecification(SpecificationCom specificationCom){
      specificationMapper.insert(specificationCom.getSpecification());
        for (SpecificationOption specificationOption:
             specificationCom.getSpecificationOptions()) {
            specificationOptionMapper.insert(specificationOption);
        }
    }

    public void delSpecification(Long[] ids){
        SpecificationOptionExample example = new SpecificationOptionExample();
        SpecificationOptionExample.Criteria criteria = example.createCriteria();
        for (Long id :ids){
            specificationMapper.deleteByPrimaryKey(id);
            criteria.andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(example);
        }
    }

    public void updateSpecification(SpecificationCom specificationCom){
        specificationMapper.updateByPrimaryKey(specificationCom.getSpecification());
        SpecificationOptionExample example = new SpecificationOptionExample();
        SpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specificationCom.getSpecification().getId());
        specificationOptionMapper.deleteByExample(example);
        for (SpecificationOption specificationOption:specificationCom.getSpecificationOptions()){
            specificationOption.setSpecId(specificationCom.getSpecification().getId());
            specificationOptionMapper.insert(specificationOption);
        }
    }
}
