package com.itheima.mm.service;

import com.itheima.mm.dao.CompanyDao;
import com.itheima.mm.pojo.Company;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-20 22:56
 */
public class CompanyService {

    /**
    * @Description: 查询企业列表的方法
    * @Param: [status]
    * @Return: java.util.List<com.itheima.mm.pojo.Company>
    * @Author: Wangqibo
    * @Date: 2020/6/20/0020
    */
    public List<Company> findAll(Integer state) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
        List<Company> companyList = companyDao.findAll(state);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return  companyList;
    }
}
