package com.itheima.mm.controller;

import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.constants.Constants;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.CourseService;
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
 * @date 2020-06-18 18:11
 */
@Controller
public class CourseController {

    //实例化业务层对象
    private CourseService courseService = new CourseService();

    /**
    * @Description: 处理添加学科的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    @RequestMapping("/course/add")
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            //获取前端传递过来的course对象
            Course course = JsonUtils.parseJSON2Object(request, Course.class);

            //补充学科的其他信息（createDate、userId、orderNo、creator）
            course.setCreateDate(DateUtils.parseDate2String(new Date()));
            User user = (User) request.getSession().getAttribute(Constants.MM_USER_LOGIN);
            course.setCreator(user.getUsername());
            course.setUserId(user.getId());
            course.setOrderNo(1);

            //调用业务层添加学科
            boolean flag = courseService.add(course);

            //添加成功返回true
            JsonUtils.printResult(response,new Result(true,"添加学科成功"));
        } catch (Exception e) {
            e.printStackTrace();
            //添加失败返回异常信息
            JsonUtils.printResult(response,new Result(false,"添加学科失败"));
        }
    }

    /**
    * @Description: 处理分页获取学科列表请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    @RequestMapping("/course/findByPage")
    public void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            //获取前端传递过来的QueryPageBean对象
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(request, QueryPageBean.class);

            System.out.println(queryPageBean);
            //调用业务层获取PageResult对象
            PageResult pageResult = courseService.findByPage(queryPageBean);

            //封装到result对象中
            //查询成功返回true
            JsonUtils.printResult(response, new Result(true, "查询学科成功", pageResult));
        } catch (Exception e) {
            //查询失败返回异常信息
            e.printStackTrace();
            JsonUtils.printResult(response, new Result(false, "查询学科失败"));
        }
    }


    /**
    * @Description: 处理更新学科请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    @RequestMapping("/course/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取前端传递过来的course对象
            Course course = JsonUtils.parseJSON2Object(request, Course.class);

            //调用业务层修改学科
            courseService.update(course);

            //修改成功返回true
            JsonUtils.printResult(response,new Result(true,"修改学科成功"));
        } catch (Exception e) {
            //修改失败返回异常信息
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"修改学科失败"));
        }
    }

    /**
    * @Description: 处理删除学科请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    @RequestMapping("/course/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取前端传递过来的id对象
            Integer id = Integer.valueOf(request.getParameter("id"));

            //调用业务层删除学科
            courseService.delete(id);

            //修改成功返回true
            JsonUtils.printResult(response,new Result(true,"删除学科成功"));
        } catch (Exception e) {
            //修改失败返回异常信息
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

    /**
    * @Description: 查询学科列表
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/20/0020
    */
    @RequestMapping("/course/findAll")
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //获取前端传递过来的status参数
            Integer status = Integer.valueOf(request.getParameter("status"));

            //调用业务层获取List<Course>
            List<Course> courseList = courseService.findAll(status);
            //封装到result对象中
            //查询成功返回true
            JsonUtils.printResult(response,new Result(true,"查询学科列表成功",courseList));
        } catch (Exception e) {
            e.printStackTrace();
            //查询失败返回异常信息
            JsonUtils.printResult(response,new Result(false,"查询学科列表失败"));
        }
    }
}
