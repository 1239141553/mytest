package com.shop.controller;


import com.shop.pojo.ResultPage;
import com.shop.pojo.TypeTemplate;
import com.shop.service.TypeTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Api(tags = "模板查询相关接口",description = "提供查询模板相关的Rest API")
public class TypeTemplateController {

    @Autowired
    private TypeTemplateService typeTemplateService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "分页查询所有模板")
    @GetMapping("/queryTypeTemplateByPage")
    public @ResponseBody ResultPage queryTypeTemplateByPage(int pageNum, int pageSize) {
        logger.info("正在分页查询所有模板");
        ResultPage resultPage = typeTemplateService.queryTypeTemplateByPage(pageNum, pageSize);
        logger.info("查询模板完成");
        return resultPage;
    }

    @ApiOperation(value = "查询所有模板")
    @GetMapping("/queryAllTypeTemplate")
    public  @ResponseBody List<TypeTemplate> queryAllTypeTemplate() {
        logger.info("正在查询所有模板");
        final List<TypeTemplate> typeTemplates = typeTemplateService.queryAllTypeTemplate();
        logger.info("查询模板完成");
        return typeTemplates;
    }

    @ApiOperation(value = "依据ID查询单个模板")
    @GetMapping("/queryTypeTemplateById")
    public @ResponseBody TypeTemplate queryTypeTemplateById(@ApiParam("模板ID") Long id) {
        logger.info("正在查询单个模板  查询的ID为"+id);
        TypeTemplate TypeTemplateCom = typeTemplateService.queryTypeTemplateById(id);
        logger.info("查询单个模板完成");
        return TypeTemplateCom;
    }

}
