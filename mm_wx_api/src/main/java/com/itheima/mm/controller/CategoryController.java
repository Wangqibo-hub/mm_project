package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.service.CategoryService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-23 12:20
 */
@Controller
public class CategoryController {
    //实例化业务层对象
    private CategoryService categoryService = new CategoryService();

    /**
    * @Description: 处理获取题库分类别表的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    @RequestMapping("/category/list")
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取请求头中的Authorization中的memberId
            Integer memberId = Integer.valueOf(request.getHeader("Authorization").substring(7));

            //获取请求体中的categoryKind和categoryType封装到requestMap中
            Map requestMap = JsonUtils.parseJSON2Object(request, Map.class);
            //将memberId封装到requestMap中
            requestMap.put("memberId", memberId);

            //调用业务层方法获取题库分类列表
            List<Map<String, Object>> resultList = categoryService.getCategoryList(requestMap);
            //查询成功，返回成功信息
            JsonUtils.printResult(response,new Result(true,"获取题库分类列表成功",resultList));
        } catch (Exception e) {
            //查询失败，返回失败信息
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"获取题库分类列表失败"));
        }
    }
}
