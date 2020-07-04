package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.service.CommonService;
import com.itheima.mm.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-22 09:25
 */
@Controller
public class CommonController {
    //实例化业务层方法
    private CommonService commonService = new CommonService();

    /**
    * @Description: 处理获取城市列表请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/22/0022
    */
    @RequestMapping("/common/citys")
    public void citys(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取请求参数fs、location,封装成map
            Map RequestMap = JsonUtils.parseJSON2Object(request, Map.class);

            //调用业务层方法获取城市信息
            Map<String, Object> cityMap = commonService.getCitys(RequestMap);
            //获取成功将结果封装到result对象中，响应给前端
            JsonUtils.printResult(response,new Result(true,"获取城市信息成功",cityMap));
        } catch (IOException e) {
            //获取失败，返回失败信息
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"获取城市信息失败"));

        }
    }

    /**
    * @Description: 处理获取学科列表请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/22/0022
    */
    @RequestMapping("/common/courseList")
    public void courses(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //调用业务层方法获取学科信息
            List<Course> courseList = commonService.getCourses();

            //获取成功将结果封装到result对象中，响应给前端
            JsonUtils.printResult(response, new Result(true, "获取学科列表成功",courseList));
        } catch (Exception e) {
            //获取失败，返回失败信息
            e.printStackTrace();
            JsonUtils.printResult(response, new Result(false, "获取学科列表失败"));
        }
    }
}
