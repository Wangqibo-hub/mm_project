package com.itheima.mm.dao;

import com.itheima.mm.pojo.Question;

import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-23 19:55
 */
public interface QuestionDao {
    
    /**
    * @Description: 获取题目列表
    * @Param: [requestMap]
    * @Return: java.util.List<com.itheima.mm.pojo.Question>
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    List<Question> findList(Map requestMap);

    /**
    * @Description: 获取做题记录的方法
    * @Param: [requestMap]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Wangqibo
    * @Date: 2020/6/24/0024
    */
    Map<String, Object> findByMemberIdAndQuestionId(Map requestMap);

    /**
    * @Description: 更新做题记录的方法
    * @Param: [requestMap]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/24/0024
    */
    void updateAnswer(Map requestMap);

    /**
    * @Description: 添加做题记录的方法
    * @Param: [requestMap]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/24/0024
    */
    void addAnswer(Map requestMap);
}
