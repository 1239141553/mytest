package com.shop.controller;

import com.shop.pojo.Brand;
import com.shop.pojo.ResultPage;
import com.shop.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "品牌查询相关接口",description = "提供查询品牌相关的Rest API")
public class BrandController {
    @Autowired
    private BrandService brandService;

    private  Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "分页查询所有品牌")
    @GetMapping("/queryBrandByPage")
    public ResultPage queryAllBrand(int pageNum,int pageSize) {
        logger.info("正在查询所有品牌");
        ResultPage resultPage = brandService.queryAllBrand(pageNum, pageSize);
        logger.info("查询品牌完成");
        return resultPage;
    }

    @ApiOperation(value = "依据ID查询单个品牌")
    @GetMapping("/queryBrandById")
    public Brand queryBrandById(@ApiParam("品牌ID") Long id) {
        logger.info("正在查询单个品牌  查询的ID为"+id);
        Brand brand = brandService.queryBrandById(id);
        logger.info("查询单个品牌完成");
        return brand;
    }

    @ApiOperation(value = "查询品牌列表Json格式")
    @GetMapping("/selectBrandOptionList")
    public List<Map> selectBrandOptionList() {
        logger.info("正在查询品牌列表Json");
        List<Map> maps = brandService.selectOptionList();
        logger.info("查询品牌列表Json完成");
        return maps;
    }
}
