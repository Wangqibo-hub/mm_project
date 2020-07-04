package com.itheima.mm.dao;

import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Question;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-18 21:44
 */
public interface QuestionDao {
    /**
    * @Description: 根据courseId查询总条数的方法
    * @Param: [id]
    * @Return: long
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    long findTotalByCourseId(Integer id);

    /**
    * @Description: 根据条件查询基础题目总条数的方法
    * @Param: [queryPageBean]
    * @Return: long
    * @Author: Wangqibo
    * @Date: 2020/6/20/0020
    */
    long getCountByCondition(QueryPageBean queryPageBean);

    /**
    * @Description: 查询基础题目当前页数据列表的方法
    * @Param: [queryPageBean]
    * @Return: java.util.List<com.itheima.mm.pojo.Question>
    * @Author: Wangqibo
    * @Date: 2020/6/20/0020
    */
    List<Question> getQuestionListByPage(QueryPageBean queryPageBean);

    /**
    * @Description: 添加基础题目
    * @Param: [question]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    void addBasic(Question question);
}
