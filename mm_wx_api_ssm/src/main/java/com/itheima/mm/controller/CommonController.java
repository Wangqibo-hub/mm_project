package com.itheima.mm.controller;

import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.service.CommonService;
import com.itheima.mm.service.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-22 09:25
 */
@RestController
@RequestMapping("common")
public class CommonController {

    //实例化业务层方法
    @Autowired
    private CommonService commonService;

    /**
    * @Description: 处理获取城市列表请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/22/0022
    */
    @RequestMapping("citys")
    public Result citys(@RequestBody Map<String, Object> requestMap) {
        try {
            //调用业务层方法获取城市信息
            Map<String, Object> cityMap = commonService.getCitys(requestMap);
            //获取成功将结果封装到result对象中，响应给前端
            return new Result(true,"获取城市信息成功",cityMap);
        } catch (Exception e) {
            //获取失败，返回失败信息
            e.printStackTrace();
            return new Result(false,"获取城市信息失败");
        }
    }

    /**
    * @Description: 处理获取学科列表请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/22/0022
    */
    @RequestMapping("courseList")
    public Result courses() {
        try {
            //调用业务层方法获取学科信息
            List<Course> courseList = commonService.getCourses();

            //获取成功将结果封装到result对象中，响应给前端
           return new Result(true, "获取学科列表成功",courseList);
        } catch (Exception e) {
            //获取失败，返回失败信息
            e.printStackTrace();
            return new Result(false, "获取学科列表失败");
        }
    }
}
