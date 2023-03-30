package com.gin.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description
 * @Date 2023/3/30 13:53
 * @Created by jingjing.yu
 */
public interface BaseService<T> extends IService<T> {

    T selectByPrimaryKey(Long id, String... properties);

    boolean updateByPrimaryKey(T entity, String... properties);

    void updateByPrimaryKeyExclude(T entity, String... properties);

    /**
     * 根据主键更新，忽略null值
     *
     * @param entity entity
     */
    void updateSelectiveByPrimaryKey(T entity);

    T lockById(Long id, String... properties);

}
