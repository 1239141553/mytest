package com.shop.controller;


import com.shop.pojo.RespBean;
import com.shop.pojo.Specification;
import com.shop.pojocom.SpecificationCom;
import com.shop.service.SpecificationService;
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
@Api(tags = "规格编辑相关接口",description = "提供编辑规格相关的Rest API")
public class specificationController {

    @Autowired
    private SpecificationService specificationService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "删除规格")
    @PostMapping("/delSpecification")
    public RespBean delSpecification(@RequestBody @ApiParam("需删除规格的ID(可为多个)") Long [] ids){
        try {
            logger.info("正在删除规格");
            specificationService.delSpecification(ids);
            logger.info("删除规格完成");
            return RespBean.ok("删除规格成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.ok("删除规格失败");
        }
    }

    @ApiOperation(value = "添加规格")
    @PostMapping("/addSpecification")
    public RespBean addSpecification(@RequestBody @ApiParam("需增加规格信息") SpecificationCom specificationCom){
        try {
            logger.info("正在增加规格");
            specificationService.addSpecification(specificationCom);
            logger.info("增加规格完成");
            return RespBean.ok("增加规格成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.ok("增加规格失败");
        }
    }

    @ApiOperation(value = "更新规格")
    @PostMapping("/updateSpecification")
    public RespBean updateSpecification(@RequestBody @ApiParam("需更新规格信息") SpecificationCom specificationCom){
        try {
            logger.info("正在更新规格");
            specificationService.updateSpecification(specificationCom);
            logger.info("更新规格完成");
            return RespBean.ok("更新规格成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.ok("更新规格失败");
        }
    }
}
