package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Company;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.service.CompanyService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-20 22:55
 */
@Controller
public class CompanyController {
    //实例化业务层方法
    private CompanyService companyService = new CompanyService();

    /**
    * @Description: 查询所有企业列表的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/20/0020
    */
    @RequestMapping("/company/findAll")
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取前端传递过来的status参数
            Integer state = Integer.valueOf(request.getParameter("state"));

            //调用业务层获取List<Course>
            List<Company> companyList = companyService.findAll(state);
            //封装到result对象中
            //查询成功返回true
            JsonUtils.printResult(response,new Result(true,"查询企业列表成功",companyList));
        } catch (Exception e) {
            e.printStackTrace();
            //查询失败返回异常信息
            JsonUtils.printResult(response,new Result(false,"查询企业列表失败"));
        }
    }
}
