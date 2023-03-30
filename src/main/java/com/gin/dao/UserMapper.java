package com.gin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gin.entity.User;
import com.gin.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yjj
 * @since 2023-03-30
 */
public interface UserMapper extends BaseMapper<User> {
    IPage<UserVo> getUserList(Page<?> page, @Param("account") String account);
}
