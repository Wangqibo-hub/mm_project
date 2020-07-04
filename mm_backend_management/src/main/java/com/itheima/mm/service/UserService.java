package com.itheima.mm.service;

import com.itheima.mm.dao.UserDao;
import com.itheima.mm.pojo.User;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-18 17:08
 */
public class UserService {

    /**
    * @Description: 处理用户登陆的方法
    * @Param: [requestUser]
    * @Return: com.itheima.mm.pojo.User
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    public User login(User requestUser) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);

        //调用dao层查询方法根据用户名查询用户
        User loginUser = userDao.findUserByName(requestUser.getUsername());
        SqlSessionFactoryUtils.commitAndClose(sqlSession);

        if (loginUser == null) {
            //用户不存在，抛出用户名或密码错误异常
            throw new RuntimeException("用户不存在");
        }
        //用户存在，判断密码是否一致
        if (!loginUser.getPassword().equals(requestUser.getPassword())) {
            //不一致，抛出用户名或密码错误异常
            throw new RuntimeException("密码错误");
        }
        //一致返回数据库查询到的用户
        return loginUser;
    }
}
