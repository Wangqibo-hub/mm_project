package com.itheima.mm.dao;

import com.itheima.mm.pojo.Catalog;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-18 21:45
 */
public interface CatalogDao {

    /**
    * @Description: 根据courseId查询总条数的方法
    * @Param: [id]
    * @Return: long
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    long findTotalByCourseId(Integer id);

    List<Catalog> findCataLogListByCourseId(int courseId);
}
