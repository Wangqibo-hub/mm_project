package com.itheima.mm.service;

import com.itheima.mm.dao.IndustryDao;
import com.itheima.mm.pojo.Industry;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-21 17:40
 */
public class IndustryService {
    /**
    * @Description: 查询方向列表的方法
    * @Param: []
    * @Return: java.util.List<com.itheima.mm.pojo.Industry>
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    public List<Industry> findAll() throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        IndustryDao industryDao = sqlSession.getMapper(IndustryDao.class);

        List<Industry> industryList = industryDao.findAll();
        return industryList;
    }
}
