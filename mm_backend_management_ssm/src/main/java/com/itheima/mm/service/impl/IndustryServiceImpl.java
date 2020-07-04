package com.itheima.mm.service.impl;

import com.itheima.mm.dao.IndustryDao;
import com.itheima.mm.pojo.Industry;
import com.itheima.mm.service.IndustryService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-21 17:40
 */
@Service
public class IndustryServiceImpl implements IndustryService {

    @Autowired
    private IndustryDao industryDao;

    /**
    * @Description: 查询方向列表的方法
    * @Param: []
    * @Return: java.util.List<com.itheima.mm.pojo.Industry>
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    @Override
    public List<Industry> findAll() throws IOException {
        List<Industry> industryList = industryDao.findAll();
        return industryList;
    }
}
