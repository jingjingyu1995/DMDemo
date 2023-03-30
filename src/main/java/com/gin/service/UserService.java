package com.gin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gin.entity.User;
import com.gin.vo.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yjj
 * @since 2023-03-30
 */
public interface UserService extends BaseService<User> {
    IPage<UserVo> getUserList(Page<?> page,String account);
}
