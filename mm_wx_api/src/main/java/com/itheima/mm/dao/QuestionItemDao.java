package com.itheima.mm.dao;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-23 20:35
 */
public interface QuestionItemDao {

    /**
    * @Description: 根据questionId获取选项；列表
    * @Param: [questionId]
    * @Return: java.util.List<com.itheima.mm.dao.QuestionItemDao>
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    List<QuestionItemDao> findByQuestionId(Integer questionId);
}
