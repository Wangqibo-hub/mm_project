package com.itheima.mm.controller;

import com.itheima.mm.constants.Constants;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.UserService;
import com.itheima.mm.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;


/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-18 17:05
 */
@RestController
@RequestMapping("user")
public class UserController {

    //实例化业务层对象
    @Autowired
    private UserService userService;

    /**
     * @Description: 处理用户登陆请求的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/6/18/0018
     */
    @RequestMapping("login")
    public Result login(@RequestBody User user, HttpSession session) {
        try {
            //调用业务层判断用户登陆的方法
            User loginUser = userService.login(user);

            //登陆成功返回true，并将用户信息写入session
            session.setAttribute(Constants.MM_USER_LOGIN, loginUser);
            return new Result(true, "登陆成功");
        } catch (Exception e) {
            e.printStackTrace();
            //登陆失败返回异常信息，用户名或密码错误异常
            return new Result(false, e.getMessage());
        }
    }

    /**
    * @Description: 处理用户退出的请求
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    @RequestMapping("logout")
    public Result logout(HttpSession session){
        //清除session中的用户数据
        session.invalidate();

        //响应前端，退出成功
        return new Result(true, "退出成功");
    }
}
