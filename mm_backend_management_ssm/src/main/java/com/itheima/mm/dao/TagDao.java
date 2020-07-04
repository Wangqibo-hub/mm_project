package com.itheima.mm.dao;

import com.itheima.mm.pojo.Tag;

import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-18 21:44
 */
public interface TagDao {

    /**
    * @Description: 根据courseId查询总条数的方法
    * @Param: [id]
    * @Return: long
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    long findTotalByCourseId(Integer id);

    /**
    * @Description: 根据courseId获取标签集合
    * @Param: [courseId]
    * @Return: java.util.List<com.itheima.mm.pojo.Tag>
    * @Author: Wangqibo
    * @Date: 2020/6/20/0020
    */
    List<Tag> findTagListByCourseId(int courseId);

    /**
    * @Description: 添加题目的标签信息
    * @Param: []
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/25/0025
    */
    void addTagQuestion(Map map);
}
