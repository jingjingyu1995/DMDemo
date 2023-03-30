package com.gin.controller;

import com.gin.entity.People;
import com.gin.service.PeopleService;
import com.gin.utils.result.Result;
import com.gin.utils.result.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Date 2023/3/29 15:53
 * @Created by jingjing.yu
 */
@RestController
@RequestMapping("people")
public class PeopleController {

    @Resource
    private PeopleService peopleService;


    @PostMapping("add")
    @ApiOperation("添加人员")
    public Result<Void> addPeople(@RequestBody People people){
        peopleService.save(people);
        return ResultUtil.ok();
    }

    @GetMapping("list")
    @ApiOperation("获取人员列表")
    public Result<List<People>> list( ){

        return ResultUtil.ok( peopleService.list( ) );
    }
}
