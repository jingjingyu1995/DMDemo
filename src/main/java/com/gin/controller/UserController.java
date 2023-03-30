package com.gin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gin.entity.User;
import com.gin.service.UserService;
import com.gin.utils.result.Result;
import com.gin.utils.result.ResultUtil;
import com.gin.vo.UserVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yjj
 * @since 2023-03-30
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping("add")
    @ApiOperation("添加用户")
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> addUser(@RequestBody User user, HttpServletRequest request) {
        userService.save(user);
        return ResultUtil.ok();
    }


    @PostMapping("updateById")
    @ApiOperation("根据id 更新用户")
    public Result<Void> updateById(@RequestBody User user) {
        userService.updateByPrimaryKey(user);
        return ResultUtil.ok();
    }

    @PostMapping("update")
    @ApiOperation("更新指定内容")
    public Result<Void> update(@RequestBody User user) {
        userService.updateByPrimaryKey(user, "firstLogin");
        return ResultUtil.ok();
    }

    @PostMapping("updateExclude")
    @ApiOperation("更新-排除指定字段")
    public Result<Void> updateExclude(@RequestBody User user) {
        userService.updateByPrimaryKeyExclude(user, "firstLogin");
        return ResultUtil.ok();
    }


    @PostMapping("updateSelectiveByPrimaryKey")
    @ApiOperation("更新-排除null")
    public Result<Void> updateSelectiveByPrimaryKey(@RequestBody User user) {
        userService.updateSelectiveByPrimaryKey(user);
        return ResultUtil.ok();
    }



    @GetMapping("detail")
    @ApiOperation("详情")
    public Result<User> detail(@RequestParam Long userId,Boolean showAll) {

        if(showAll){
            return ResultUtil.ok(userService.selectByPrimaryKey(userId));
        }
        return ResultUtil.ok(userService.selectByPrimaryKey(userId, "account", "phone"));

    }

    @DeleteMapping("delete")
    @ApiOperation("删除")
    public Result<Void> delete(@RequestParam Long userId) {
        userService.removeById(userId);
        return ResultUtil.ok();
    }

    @GetMapping("page")
    @ApiOperation("分页列表 ")
    public Result<IPage<User>> getPage(@ApiParam("账号") String account) {
        Page<User> page = new Page<>();

        //查询条件
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(account), User::getAccount, account);
        return ResultUtil.ok(userService.page(page, lambdaQueryWrapper));
    }

    @GetMapping("page2")
    @ApiOperation("分页列表 ")
    public Result<IPage<UserVo>> getPag2( Page<?> page,@ApiParam("账号") String account) {


        return ResultUtil.ok(userService.getUserList(page, account));
    }


}
