package com.itheima.mm.dao;

import com.itheima.mm.pojo.Tag;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-23 20:40
 */
public interface TagDao {

    /**
    * @Description: 根据questionId获取标签列表
    * @Param: [questionId]
    * @Return: java.util.List<com.itheima.mm.pojo.Tag>
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    List<Tag> findByQuestionId(Integer questionId);

}
