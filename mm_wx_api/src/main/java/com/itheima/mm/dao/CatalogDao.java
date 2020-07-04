package com.itheima.mm.dao;

import com.itheima.mm.pojo.WxMember;

import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-23 14:21
 */
public interface CatalogDao {

    /**
    * @Description: 根据学科Id获取题库列表
    * @Param: [wxMember]
    * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    List<Map<String, Object>> findByCourseId(WxMember wxMember);

    /**
    * @Description: 根据id查询二级目录
    * @Param: []
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    Map<String, Object> findById(Map requestMap);

    /**
    * @Description: 根据id查询name
    * @Param: [categoryId]
    * @Return: java.lang.String
    * @Author: Wangqibo
    * @Date: 2020/6/25/0025
    */
    String findNameById(Integer categoryId);
}
