package com.shop.service.impl;

import com.shop.mapper.TbBrandMapper;
import com.shop.pojo.Brand;
import com.shop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public void addBrand(Brand brand){
      tbBrandMapper.insert(brand);
      redisTemplate.boundHashOps("brand").delete(brand.getId());
    }

    public void delBrand(Long[] ids){
        for (Long id :ids){
            tbBrandMapper.deleteByPrimaryKey(id);
            redisTemplate.boundHashOps("brand").delete(id);
        }
    }

    public void updateBrand(Brand brand){
        Brand oldBrand = tbBrandMapper.selectByPrimaryKey(brand.getId());
        redisTemplate.boundHashOps("brand").delete(oldBrand.getId());
        tbBrandMapper.updateByPrimaryKey(brand);
        if(oldBrand.getId()!= brand.getId()){
            redisTemplate.boundHashOps("brand").delete(oldBrand.getId());
        }

    }
}
