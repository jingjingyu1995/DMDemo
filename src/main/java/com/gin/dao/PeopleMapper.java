package com.gin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gin.entity.People;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 人员信息 Mapper 接口
 * </p>
 *
 * @author yjj
 * @since 2023-03-29
 */
public interface PeopleMapper extends BaseMapper<People> {

    void addPeople(@Param("people") People people);
}
