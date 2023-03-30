package com.gin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gin.dao.CompanyMapper;
import com.gin.entity.Company;

import com.gin.service.CompanyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yjj
 * @since 2023-03-30
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

}
