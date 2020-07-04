package com.itheima.mm.controller;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Industry;
import com.itheima.mm.service.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-21 17:36
 */
@RestController
@RequestMapping("industry")
public class IndustryController {

    //实例化业务层对象
    @Autowired
    private IndustryService industryService;

    /**
    * @Description: 处理获取所有方向信息的请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    @RequestMapping("findAll")
    public Result findAll() {
        try {
            //调用业务层获取List<Industry>
            List<Industry> industryList = industryService.findAll();
            //封装到result对象中
            //查询成功返回true
            return new Result(true,"查询方向列表成功",industryList);
        } catch (Exception e) {
            e.printStackTrace();
            //查询失败返回异常信息
            return new Result(false,"查询方向列表失败");
        }
    }
}
