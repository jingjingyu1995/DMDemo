package com.gin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gin.exceptions.ServiceException;
import com.gin.service.BaseService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Date 2023/3/30 13:55
 * @Created by jingjing.yu
 */
public class BaseServiceImpl<T> extends ServiceImpl<BaseMapper<T>, T> implements BaseService<T> {

    /**
     * 根据属性名获取属性元素，包括各种安全范围和所有父类
     *
     * @param fieldName
     * @param object
     * @return
     */
    private Field getFieldByClass(String fieldName, Object object) {
        Field field = null;
        Class<?> clazz = object.getClass();
        //从当前的类开始找，没找到再从父类中找。
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (Exception e) {
                // 这里甚么都不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会进入
            }
        }
        return field;

    }

    private Object getFieldValue(String fieldName, Object object) {
        try {
            Object result = null;
            Field field = getFieldByClass(fieldName, object);
            field.setAccessible(true);
            result = field.get(object);
            field.setAccessible(false);
            return result;
        } catch (IllegalAccessException exception) {
            throw new ServiceException("未获取主键信息");
        }
    }


    @Override
    public T selectByPrimaryKey(Long id, String... properties) {
        if (id == null) {
            throw new NullPointerException("id not null");
        }
        if (properties == null || properties.length == 0) {
            return getById(id);
        }
        String keyProperty = TableInfoHelper.getTableInfo(this.entityClass).getKeyProperty();

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(keyProperty, id);
        queryWrapper.select(properties);
        return getOne(queryWrapper);
    }


    @Override
    public boolean updateByPrimaryKey(T entity, String... properties) {

        if (properties == null || properties.length == 0) {
            return updateById(entity);
        }
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
        updateWrapper.eq(tableInfo.getKeyColumn(), tableInfo.getPropertyValue(entity, tableInfo.getKeyProperty()));


        for (TableFieldInfo tableFieldInfo : tableInfo.getFieldList()) {
            if (Arrays.asList(properties).contains(tableFieldInfo.getProperty())) {
                updateWrapper.set(tableFieldInfo.getColumn(), tableInfo.getPropertyValue(entity, tableFieldInfo.getProperty()));
            }
        }

        return update(null, updateWrapper);
    }

    @Override
    public void updateByPrimaryKeyExclude(T entity, String... properties) {
        if (properties == null || properties.length == 0) {
            updateById(entity);
            return;
        }
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
        List<String> propertyList = new ArrayList<>();
        for (TableFieldInfo fieldInfo : tableInfo.getFieldList()) {
            if (!Arrays.asList(properties).contains(fieldInfo.getProperty())) {
                propertyList.add(fieldInfo.getProperty());
            }
        }
        updateByPrimaryKey(entity, propertyList.toArray(new String[0]));
    }

    @Override
    public void updateSelectiveByPrimaryKey(T entity) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<>();
        for (TableFieldInfo fieldInfo : tableInfo.getFieldList()) {
            if (tableInfo.getPropertyValue(entity, fieldInfo.getProperty()) != null) {
                updateWrapper.set(fieldInfo.getColumn(), tableInfo.getPropertyValue(entity, fieldInfo.getProperty()));
            }
        }

        update(null, updateWrapper);
    }


    @Override
    public T lockById(Long id, String... properties) {

        if (id == null) {
            throw new NullPointerException("id not null");
        }
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.last("for update");
        String keyProperty = TableInfoHelper.getTableInfo(this.entityClass).getKeyProperty();
        queryWrapper.eq(keyProperty, id);
        if (properties != null && properties.length == 0) {
            queryWrapper.select(properties);

        }

        return getOne(queryWrapper);
    }
}
