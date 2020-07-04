package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.service.QuestionService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-23 19:32
 */
@Controller
public class QuestionController {

    //实例化业务层对象
    private QuestionService questionService = new QuestionService();

    /**
    * @Description: 处理获取题目列表的请求
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    @RequestMapping("/question/list")
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取请求头中的Authorization中的memberId
            Integer memberId = Integer.valueOf(request.getHeader("Authorization").substring(7));

            //获取请求体中的categoryID、categoryKind和categoryType封装到requestMap中
            Map requestMap = JsonUtils.parseJSON2Object(request, Map.class);

            //将memberId封装到requestMap中
            requestMap.put("memberId", memberId);

            //调用业务层方法获取题目列表
            Map<String, Object> resultMap = questionService.getQuestionList(requestMap);
            //查询成功，返回成功信息
            JsonUtils.printResult(response, new Result(true, "获取题目列表成功",resultMap));
        } catch (Exception e) {
            //查询失败，返回失败信息
            e.printStackTrace();
            JsonUtils.printResult(response, new Result(false, "获取题目列表失败"));
        }
    }

    /**
    * @Description: 处理提交答题信息请求的方法
    * @Param: []
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/24/0024
    */
    @RequestMapping("/question/submit")
    public void questionCommit(HttpServletRequest request ,HttpServletResponse response) throws IOException {
        try {
            //获取请求头中的Authorization中的memberId
            Integer memberId = Integer.valueOf(request.getHeader("Authorization").substring(7));

            //获取请求体中的id、categoryId、categoryKindcategoryType、answerIsRight、answerResult、isFavorite封装到requestMap中
            Map requestMap = JsonUtils.parseJSON2Object(request, Map.class);

            //将memberId封装到requestMap中
            requestMap.put("memberId", memberId);

            //调用业务层方法提交做题信息
            questionService.questionCommit(requestMap);

            //成功，返回成功信息
            JsonUtils.printResult(response,new Result(true,"答题信息提交成功"));
        } catch (Exception e) {
            e.printStackTrace();
            //失败，返回失败信息
            JsonUtils.printResult(response,new Result(false,"答题信息提交失败"));
        }
    }
}
