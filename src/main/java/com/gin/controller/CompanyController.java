package com.gin.controller;

import com.gin.entity.Company;
import com.gin.service.CompanyService;
import com.gin.utils.result.Result;
import com.gin.utils.result.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Date 2023/3/29 15:53
 * @Created by jingjing.yu
 */
@RestController
@RequestMapping("company")
public class CompanyController {

    @Resource
    private CompanyService companyService;


    @PostMapping("add")
    @ApiOperation("添加企业")
    public Result<Void> addPeople(@RequestBody Company company){
        companyService.save(company);
        return ResultUtil.ok();
    }

}
