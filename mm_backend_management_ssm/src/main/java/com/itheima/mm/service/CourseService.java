package com.itheima.mm.service;

import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-07-02 20:38
 */
public interface CourseService {
    boolean add(Course course);

    PageResult findByPage(QueryPageBean queryPageBean);

    void update(Course course);

    void delete(Integer id);

    List<Course> findAll(Integer status);
}
