package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Company;
import com.itheima.mm.pojo.Industry;
import com.itheima.mm.service.IndustryService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-21 17:36
 */
@Controller
public class IndustryController {
    //实例化业务层对象
    private IndustryService industryService = new IndustryService();

    /**
    * @Description: 处理获取所有方向信息的请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    @RequestMapping("/industry/findAll")
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //调用业务层获取List<Industry>
            List<Industry> industryList = industryService.findAll();
            //封装到result对象中
            //查询成功返回true
            JsonUtils.printResult(response,new Result(true,"查询方向列表成功",industryList));
        } catch (Exception e) {
            e.printStackTrace();
            //查询失败返回异常信息
            JsonUtils.printResult(response,new Result(false,"查询方向列表失败"));
        }
    }
}
