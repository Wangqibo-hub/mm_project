package com.itheima.mm.controller;

import com.itheima.mm.constants.Constants;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.User;
import com.itheima.mm.service.CourseService;
import com.itheima.mm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-18 18:11
 */
@RestController
@RequestMapping("course")
public class CourseController {

    //实例化业务层对象
    @Autowired
    private CourseService courseService;

    /**
     * @Description: 处理添加学科的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/6/21/0021
     */
    @RequestMapping("add")
    public Result add(@RequestBody Course course, HttpSession session) {
        try {
            //补充学科的其他信息（createDate、userId、orderNo、creator）
            course.setCreateDate(DateUtils.parseDate2String(new Date()));
            User user = (User) session.getAttribute(Constants.MM_USER_LOGIN);
            course.setCreator(user.getUsername());
            course.setUserId(user.getId());
            course.setOrderNo(1);

            //调用业务层添加学科
            boolean flag = courseService.add(course);

            //添加成功返回true
            return new Result(true, "添加学科成功");
        } catch (Exception e) {
            e.printStackTrace();
            //添加失败返回异常信息
            return new Result(false, "添加学科失败");
        }
    }

    /**
     * @Description: 处理分页获取学科列表请求的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/6/18/0018
     */
    @RequestMapping("findByPage")
    public Result findByPage(@RequestBody QueryPageBean queryPageBean) {

        try {
            //调用业务层获取PageResult对象
            PageResult pageResult = courseService.findByPage(queryPageBean);

            //封装到result对象中
            //查询成功返回true
            return new Result(true, "查询学科成功", pageResult);
        } catch (Exception e) {
            //查询失败返回异常信息
            e.printStackTrace();
            return new Result(false, "查询学科失败");
        }
    }

    /**
     * @Description: 处理更新学科请求的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/6/18/0018
     */
    @RequestMapping("update")
    public Result update(@RequestBody Course course) {
        try {
            //调用业务层修改学科
            courseService.update(course);

            //修改成功返回true
            return new Result(true, "修改学科成功");
        } catch (Exception e) {
            //修改失败返回异常信息
            e.printStackTrace();
            return new Result(false, "修改学科失败");
        }
    }

    /**
     * @Description: 处理删除学科请求的方法
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/6/18/0018
     */
    @RequestMapping("delete")
    public Result delete(int id) {
        try {
            //调用业务层删除学科
            courseService.delete(id);

            //修改成功返回true
            return new Result(true, "删除学科成功");
        } catch (Exception e) {
            //修改失败返回异常信息
            e.printStackTrace();
            return new Result(false, e.getMessage());
        }
    }

    /**
     * @Description: 查询学科列表
     * @Param: [request, response]
     * @Return: void
     * @Author: Wangqibo
     * @Date: 2020/6/20/0020
     */
    @RequestMapping("findAll")
    public Result findAll(Integer status) {
        try {
            //调用业务层获取List<Course>
            List<Course> courseList = courseService.findAll(status);
            //封装到result对象中
            //查询成功返回true
            return new Result(true, "查询学科列表成功", courseList);
        } catch (Exception e) {
            e.printStackTrace();
            //查询失败返回异常信息
            return new Result(false, "查询学科列表失败");
        }
    }
}
