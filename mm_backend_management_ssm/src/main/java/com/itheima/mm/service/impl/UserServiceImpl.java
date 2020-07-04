package com.itheima.mm.service.impl;

import com.itheima.mm.dao.UserDao;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-18 17:08
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
    * @Description: 处理用户登陆的方法
    * @Param: [requestUser]
    * @Return: com.itheima.mm.pojo.User
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    @Override
    public User login(User requestUser){
        //调用dao层查询方法根据用户名查询用户
        User loginUser = userDao.findUserByName(requestUser.getUsername());

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
