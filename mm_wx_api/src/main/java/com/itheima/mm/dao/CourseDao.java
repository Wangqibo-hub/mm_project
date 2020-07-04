package com.itheima.mm.dao;

import com.itheima.mm.pojo.Course;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-22 11:13
 */
public interface CourseDao {

    /**
    * @Description: 获取所有学科列表
    * @Param: []
    * @Return: java.util.List<com.itheima.mm.pojo.Course>
    * @Author: Wangqibo
    * @Date: 2020/6/22/0022
    */
    List<Course> findAll();
}
