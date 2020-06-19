package com.shop.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shop.mapper.SpecificationMapper;
import com.shop.mapper.SpecificationOptionMapper;
import com.shop.mapper.TbBrandMapper;
import com.shop.pojo.*;
import com.shop.pojocom.SpecificationCom;
import com.shop.service.BrandService;
import com.shop.service.SpecificationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;

    @Override
    public ResultPage queryAllSpecification(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Specification> page = (Page<Specification>) specificationMapper.selectByExample(null);
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(page.getResult());
        resultPage.setTotal(page.getTotal());
        return resultPage;
    }

    @Override
    public SpecificationCom querySpecificationById(Long id) {
        SpecificationCom specificationCom = new SpecificationCom();
        SpecificationOptionExample example = new SpecificationOptionExample();
        SpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<SpecificationOption> specificationOptions = specificationOptionMapper.selectByExample(example);
        Specification specification = specificationMapper.selectByPrimaryKey(id);
        specificationCom.setSpecificationOptions(specificationOptions);
        specificationCom.setSpecification(specification);
        return specificationCom;
    }

    @Override
    public ResultPage querySpecificationLike(int pageNum, int pageSize, String specName) {
        SpecificationExample example = new SpecificationExample();
        SpecificationExample.Criteria criteria = example.createCriteria();
        criteria.andSpecNameLike(specName);
        PageHelper.startPage(pageNum, pageSize);
        Page<Specification> page = (Page<Specification>) specificationMapper.selectByExample(example);
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(page.getResult());
        resultPage.setTotal(page.getTotal());
        return resultPage;
    }

    @Override
    public List<Map> selectOptionList() {
        return specificationMapper.selectOptionList();
    }


}
