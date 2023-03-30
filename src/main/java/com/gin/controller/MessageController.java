package com.gin.controller;

import com.gin.entity.Message;
import com.gin.service.MessageService;
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
@RequestMapping("message")
public class MessageController {

    @Resource
    private MessageService messageService;


    @PostMapping("add")
    @ApiOperation("添加消息")
    public Result<Void> add(@RequestBody Message message){
        messageService.save(message);
        return ResultUtil.ok();
    }

}
