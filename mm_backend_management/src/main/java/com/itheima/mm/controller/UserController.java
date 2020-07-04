package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.constants.Constants;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.UserService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-18 17:05
 */
@Controller
public class UserController {
    //实例化业务层对象
    private UserService userService = new UserService();

    /**
     * @Description: 处理用户登陆请求的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/6/18/0018
     */
    @RequestMapping("/user/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取前端传递过来的user对象
            User requestUser = JsonUtils.parseJSON2Object(request, User.class);

            //调用业务层判断用户登陆的方法
            User loginUser = userService.login(requestUser);

            //登陆成功返回true，并将用户信息写入session
            request.getSession().setAttribute(Constants.MM_USER_LOGIN, loginUser);
            JsonUtils.printResult(response, new Result(true, "登陆成功"));
        } catch (Exception e) {
            e.printStackTrace();
            //登陆失败返回异常信息，用户名或密码错误异常
            JsonUtils.printResult(response, new Result(false, e.getMessage()));
        }
    }

    /**
    * @Description: 处理用户退出的请求
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    @RequestMapping("/user/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //清除session中的用户数据
        request.getSession().invalidate();

        //响应前端，退出成功
        JsonUtils.printResult(response, new Result(true, "退出成功"));
    }
}
