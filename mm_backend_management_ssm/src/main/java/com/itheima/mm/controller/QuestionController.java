package com.itheima.mm.controller;
import com.itheima.mm.constants.Constants;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.QuestionService;
import com.itheima.mm.service.impl.QuestionServiceImpl;
import com.itheima.mm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-20 20:21
 */
@RestController
@RequestMapping("question")
public class QuestionController {

    //实例化业务层对象
    @Autowired
    private QuestionService questionService;

    /**
    * @Description: 分页获取基础题目信息的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/20/0020
    */
    @RequestMapping("findByPage")
    public Result findAll(@RequestBody QueryPageBean queryPageBean){
        try {
            //调用业务层获取PageResult对象
            PageResult pageResult = questionService.findByPage(queryPageBean);

            //封装到result对象中
            //查询成功返回true
            return new Result(true, "查询基础题目列表成功",pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            //查询失败返回异常信息
            return new Result(false, "查询基础题目列表失败");
        }
    }
    
    /**
    * @Description: 处理添加基础题目请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    @RequestMapping("addBasic")
    public Result addBasic(@RequestBody Question question, HttpSession session) {
        try {
            //手动封装createDate、userId
            question.setCreateDate(DateUtils.parseDate2String(new Date()));
            User loginUser = (User) session.getAttribute(Constants.MM_USER_LOGIN);
            question.setUserId(loginUser.getId());

            //调用业务层添加基础题目
            questionService.addBasic(question);
            //添加成功返回true
            return new Result(true,"基础题目添加成功");
        } catch (Exception e) {
            //添加失败返回失败信息
            e.printStackTrace();
            return new Result(false,"基础题目添加失败");
        }
    }
}
