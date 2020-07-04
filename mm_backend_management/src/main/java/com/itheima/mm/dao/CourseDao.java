package com.itheima.mm.dao;

import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-18 18:25
 */
public interface CourseDao {

    /**
    * @Description: 添加学科信息的方法
    * @Param: [course]
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    int add(Course course);

    /**
    * @Description: 根据条件获取学科总条数
    * @Param: []
    * @Return: long
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    long total(QueryPageBean queryPageBean);

    /**
    * @Description: 根据条件获取当前页数据列表
    * @Param: [queryPageBean]
    * @Return: java.util.List<com.itheima.mm.pojo.Course>
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    List<Course> findCourseListByPage(QueryPageBean queryPageBean);

    /**
    * @Description: 修改学科的方法
    * @Param: [course]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    int update(Course course);

    /**
    * @Description: 删除学科
    * @Param: [id]
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    int delete(Integer id);

    /**
    * @Description: 获取所有学科信息的方法
    * @Param: [status]
    * @Return: java.util.List<com.itheima.mm.pojo.Course>
    * @Author: Wangqibo
    * @Date: 2020/6/20/0020
    */
    List<Course> findAll(@Param("status") Integer status);
}
