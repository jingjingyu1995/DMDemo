package com.gin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gin.entity.People;

/**
 * <p>
 * 人员信息 服务类
 * </p>
 *
 * @author yjj
 * @since 2023-03-29
 */
public interface PeopleService extends IService<People> {

    void addPeople(People people);

}
