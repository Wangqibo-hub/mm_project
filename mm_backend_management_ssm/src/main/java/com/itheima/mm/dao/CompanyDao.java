package com.itheima.mm.dao;

import com.itheima.mm.pojo.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-20 22:59
 */
public interface CompanyDao {

    /**
    * @Description: 获取所有企业列表
    * @Param: [state]
    * @Return: java.util.List<com.itheima.mm.pojo.Company>
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    List<Company> findAll(@Param("state") Integer state);
}
