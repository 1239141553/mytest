package com.shop.controller;

import com.shop.pojo.ResultPage;
import com.shop.pojo.Specification;
import com.shop.pojocom.SpecificationCom;
import com.shop.service.SpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@Api(tags = "规格查询相关接口",description = "提供查询规格相关的Rest API")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "分页查询所有规格")
    @GetMapping("/querySpecificationByPage")
    public ResultPage queryAllSpecification(int pageNum, int pageSize) {
        logger.info("正在查询所有规格");
        ResultPage resultPage = specificationService.queryAllSpecification(pageNum, pageSize);
        logger.info("查询规格完成");
        return resultPage;
    }

    @ApiOperation(value = "分页查询特定规格")
    @PostMapping("/querySpecificationLike")
    public ResultPage querySpecificationLike(int pageNum, int pageSize,
                                             @RequestBody @ApiParam("规格名称")String specName) {
        logger.info("正在查询特定规格");
        ResultPage resultPage = specificationService.querySpecificationLike(pageNum, pageSize,specName);
        logger.info("查询规格完成");
        return resultPage;
    }

    @ApiOperation(value = "依据ID查询单个规格")
    @GetMapping("/querySpecificationById")
    public @ResponseBody SpecificationCom querySpecificationById(@ApiParam("规格ID") Long id) {
        logger.info("正在查询单个规格  查询的ID为"+id);
        SpecificationCom specificationCom = specificationService.querySpecificationById(id);
        logger.info(specificationCom.getSpecification().getSpecName());
        logger.info("查询单个规格完成");
        return specificationCom;
    }

    @ApiOperation(value = "查询规格列表Json格式")
    @GetMapping("/selectSpecificationOptionList")
    public List<Map> selectSpecificationOptionList() {
        logger.info("正在查询规格列表Json");
        List<Map> maps = specificationService.selectOptionList();
        logger.info("查询规格列表Json完成");
        return maps;
    }
}
