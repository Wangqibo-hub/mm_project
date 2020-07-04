package com.itheima.mm.service.impl;

import com.itheima.mm.dao.CompanyDao;
import com.itheima.mm.pojo.Company;
import com.itheima.mm.service.CompanyService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-20 22:56
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    /**
    * @Description: 查询企业列表的方法
    * @Param: [status]
    * @Return: java.util.List<com.itheima.mm.pojo.Company>
    * @Author: Wangqibo
    * @Date: 2020/6/20/0020
    */
    @Override
    public List<Company> findAll(Integer state){
        List<Company> companyList = companyDao.findAll(state);
        return  companyList;
    }
}
