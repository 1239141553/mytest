package com.shop.controller;

import com.shop.pojo.TypeTemplate;
import com.shop.pojo.RespBean;
import com.shop.service.TypeTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(tags = "模板编辑相关接口",description = "提供编辑模板相关的Rest API")
public class TypeTemplateController {

    @Autowired
    private TypeTemplateService typeTemplateService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "删除模板")
    @PostMapping("/delTypeTemplate")
    public RespBean delTypeTemplate(@RequestBody @ApiParam("需删除模板的ID(可为多个)") Long [] ids){
        try {
            logger.info("正在删除模板");
            typeTemplateService.delTypeTemplate(ids);
            logger.info("删除模板完成");
            return RespBean.ok("删除模板成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.ok("删除模板失败");
        }
    }

    @ApiOperation(value = "添加模板")
    @PostMapping("/addTypeTemplate")
    public RespBean addTypeTemplate(@RequestBody @ApiParam("需增加模板信息") TypeTemplate typeTemplate){
        try {
            logger.info("正在增加模板");
            logger.info(typeTemplate.getName());
            typeTemplateService.addTypeTemplate(typeTemplate);
            logger.info("增加模板完成");
            return RespBean.ok("增加模板成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.ok("增加模板失败");
        }
    }

    @ApiOperation(value = "更新模板")
    @PostMapping("/updateTypeTemplate")
    public RespBean updateTypeTemplate(@RequestBody @ApiParam("需更新模板信息") TypeTemplate typeTemplate){
        try {
            logger.info("正在更新模板");
            typeTemplateService.updateTypeTemplate(typeTemplate);
            logger.info("更新模板完成");
            return RespBean.ok("更新模板成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.ok("更新模板失败");
        }
    }
}
