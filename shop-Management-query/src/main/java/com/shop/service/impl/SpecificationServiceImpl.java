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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;
    @Autowired
    private RedisTemplate redisTemplate;

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
        SpecificationCom OldpecificationCom = (SpecificationCom) redisTemplate.boundHashOps(
                "specification").get(id);
        if (OldpecificationCom==null){
            SpecificationOptionExample example = new SpecificationOptionExample();
            SpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);
            List<SpecificationOption> specificationOptions = specificationOptionMapper.selectByExample(example);
            Specification specification = specificationMapper.selectByPrimaryKey(id);
            SpecificationCom specificationCom = new SpecificationCom();
            specificationCom.setSpecificationOptions(specificationOptions);
            specificationCom.setSpecification(specification);
            redisTemplate.boundHashOps("specification").put(id, specificationCom);
            return specificationCom;
        }
        return OldpecificationCom;
    }

    @Override
    public ResultPage querySpecificationLike(int pageNum, int pageSize, String specName) {
        ResultPage oldResultPage = (ResultPage) redisTemplate.boundHashOps(
                "specification").get(specName);
        if(oldResultPage==null){
            SpecificationExample example = new SpecificationExample();
            SpecificationExample.Criteria criteria = example.createCriteria();
            criteria.andSpecNameLike(specName);
            PageHelper.startPage(pageNum, pageSize);
            Page<Specification> page = (Page<Specification>) specificationMapper.selectByExample(example);
            ResultPage resultPage = new ResultPage();
            resultPage.setRows(page.getResult());
            resultPage.setTotal(page.getTotal());
            redisTemplate.boundHashOps("specification").put(specName, resultPage);
        }
        return oldResultPage;
    }

    @Override
    public List<Map> selectOptionList() {
        List<Map> list = new ArrayList<>();
        list = (List<Map>) redisTemplate.boundHashOps("specification").get("optionList");
        if(CollectionUtils.isEmpty(list)){
            list = specificationMapper.selectOptionList();
            redisTemplate.boundHashOps("specification").put("optionList", list);
        }
        return list;
    }
}
