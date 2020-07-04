package com.itheima.mm.dao;

import com.itheima.mm.pojo.Industry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-20 23:04
 */
public interface IndustryDao {

    /**
    * @Description: 根据Id查询方向信息
    * @Param: [companyId]
    * @Return: java.util.List<com.itheima.mm.pojo.Industry>
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    List<Industry> findIndustryListByCompanyId(@Param("CompanyId") int companyId);

    /**
    * @Description: 查询所有方向的方法
    * @Param: []
    * @Return: java.util.List<com.itheima.mm.pojo.Industry>
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    List<Industry> findAll();
}
