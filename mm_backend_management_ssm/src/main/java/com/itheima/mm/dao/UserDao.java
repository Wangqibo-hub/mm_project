package com.itheima.mm.dao;

import com.itheima.mm.pojo.User;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-18 17:13
 */
public interface UserDao {

    /**
    * @Description: 根据用户名查询用户的方法
    * @Param: [requestUser]
    * @Return: com.itheima.mm.pojo.User
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    User findUserByName(String username);
}
