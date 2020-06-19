package com.shop.controller;

import com.shop.pojo.RespBean;
import com.shop.pojo.Brand;
import com.shop.service.BrandService;
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
@Api(tags = "品牌编辑相关接口",description = "提供编辑品牌相关的Rest API")
public class BrandController {

    @Autowired
    private BrandService brandService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "删除品牌")
    @PostMapping("/delBrand")
    public RespBean delBrand(@RequestBody @ApiParam("需删除品牌的ID(可为多个)") Long [] ids){
        try {
            logger.info("正在删除品牌");
            brandService.delBrand(ids);
            logger.info("删除品牌完成");
            return RespBean.ok("删除品牌成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.ok("删除品牌失败");
        }
    }

    @ApiOperation(value = "添加品牌")
    @PostMapping("/addBrand")
    public RespBean addBrand(@RequestBody @ApiParam("需增加品牌信息") Brand brand){
        try {
            logger.info("正在增加品牌");
            logger.info(brand.getName());
            brandService.addBrand(brand);
            logger.info("增加品牌完成");
            return RespBean.ok("增加品牌成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.ok("增加品牌失败");
        }
    }

    @ApiOperation(value = "更新品牌")
    @PostMapping("/updateBrand")
    public RespBean updateBrand(@RequestBody @ApiParam("需更新品牌信息") Brand brand){
        try {
            logger.info("正在更新品牌");
            brandService.updateBrand(brand);
            logger.info("更新品牌完成");
            return RespBean.ok("更新品牌成功");
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.ok("更新品牌失败");
        }
    }
}
