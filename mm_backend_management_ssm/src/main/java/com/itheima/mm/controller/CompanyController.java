package com.itheima.mm.controller;


import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Company;
import com.itheima.mm.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-20 22:55
 */
@RestController
@RequestMapping("company")
public class CompanyController {

    //实例化业务层方法
    @Autowired
    private CompanyService companyService;

    /**
    * @Description: 查询所有企业列表的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/20/0020
    */
    @RequestMapping("findAll")
    public Result findAll(int state){
        try {
            //调用业务层获取List<Course>
            List<Company> companyList = companyService.findAll(state);
            //封装到result对象中
            //查询成功返回true
            return new Result(true,"查询企业列表成功",companyList);
        } catch (Exception e) {
            e.printStackTrace();
            //查询失败返回异常信息
            return new Result(false,"查询企业列表失败");
        }
    }
}
