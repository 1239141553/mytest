package com.shop.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shop.mapper.TbBrandMapper;
import com.shop.pojo.Brand;
import com.shop.pojo.ResultPage;
import com.shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultPage queryAllBrand(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Brand> page = (Page<Brand>) tbBrandMapper.selectByExample(null);
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(page.getResult());
        resultPage.setTotal(page.getTotal());
        return resultPage;
    }

    @Override
    public Brand queryBrandById(Long id) {
        Brand  brand = (Brand) redisTemplate.boundHashOps("brand").get(id);
        if (brand==null){
            brand = tbBrandMapper.selectByPrimaryKey(id);
            redisTemplate.boundHashOps("brand").put(id, brand);
        }
        return brand;
    }

    @Override
    public List<Map> selectOptionList() {
        return tbBrandMapper.selectOptionList();
    }
}
