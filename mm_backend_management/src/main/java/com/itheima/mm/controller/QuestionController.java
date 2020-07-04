package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.constants.Constants;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.QuestionService;
import com.itheima.mm.utils.DateUtils;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-20 20:21
 */
@Controller
public class QuestionController {

    //实例化业务层对象
    private QuestionService questionService = new QuestionService();

    /**
    * @Description: 分页获取基础题目信息的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/20/0020
    */
    @RequestMapping("/question/findByPage")
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取前端传递过来的QueryPageBean对象
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(request, QueryPageBean.class);

            //调用业务层获取PageResult对象
            PageResult pageResult = questionService.findByPage(queryPageBean);

            //封装到result对象中
            //查询成功返回true
            JsonUtils.printResult(response, new Result(true, "查询基础题目列表成功",pageResult));
        } catch (Exception e) {
            e.printStackTrace();
            //查询失败返回异常信息
            JsonUtils.printResult(response, new Result(false, "查询基础题目列表失败"));
        }
    }
    
    /**
    * @Description: 处理添加基础题目请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    @RequestMapping("/question/addBasic")
    public void addBasic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取前端传递过来的formData封装成Question对象
            Question question = JsonUtils.parseJSON2Object(request, Question.class);

            //手动封装createDate、userId
            question.setCreateDate(DateUtils.parseDate2String(new Date()));
            User loginUser = (User) request.getSession().getAttribute(Constants.MM_USER_LOGIN);
            question.setUserId(loginUser.getId());

            //调用业务层添加基础题目
            questionService.addBasic(question);
            //添加成功返回true
            JsonUtils.printResult(response,new Result(true,"基础题目添加成功"));
        } catch (Exception e) {
            //添加失败返回失败信息
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"基础题目添加失败"));
        }
    }
}
