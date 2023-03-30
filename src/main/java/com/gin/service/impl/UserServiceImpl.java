package com.gin.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gin.dao.UserMapper;
import com.gin.entity.User;
import com.gin.service.UserService;
import com.gin.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yjj
 * @since 2023-03-30
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public IPage<UserVo> getUserList(Page<?> page,String account) {
        return userMapper.getUserList(page,account) ;
    }
}
