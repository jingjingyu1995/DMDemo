package com.gin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gin.dao.PeopleMapper;
import com.gin.entity.People;
import com.gin.service.PeopleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 人员信息 服务实现类
 * </p>
 *
 * @author yjj
 * @since 2023-03-29
 */
@Service
public class PeopleServiceImpl extends ServiceImpl<PeopleMapper, People> implements PeopleService {
    @Resource
    private PeopleMapper peopleMapper;

    @Override
    public void addPeople(People people) {

        peopleMapper.addPeople(people);
    }
}
