package com.itheima.mm.service.impl;

import com.itheima.mm.dao.CatalogDao;
import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.TagDao;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-18 18:13
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CatalogDao catalogDao;
    @Autowired
    private TagDao tagDao;
    @Autowired
    private QuestionDao questionDao;

    /**
    * @Description: 添加学科的方法
    * @Param: [course]
    * @Return: boolean
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    @Override
    public boolean add(Course course){

        //调用dao方法添加学科信息
        int count = courseDao.add(course);

        //返回添加是否成功
        return count > 0;
    }

    /**
    * @Description: 分页查询学科列表的方法
    * @Param: [queryPageBean]
    * @Return: com.itheima.mm.entity.PageResult
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) {

        //调用dao方法获取总条数
        long total = courseDao.total(queryPageBean);
        //调用dao方法获取当前页数据列表
        List<Course> courseList = courseDao.findCourseListByPage(queryPageBean);
        //封装PageResult返回
        return new PageResult(total, courseList);
    }

    /**
    * @Description: 修改学科的方法
    * @Param: [course]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    @Override
    public void update(Course course){
        //调用dao方法修改学科信息
        courseDao.update(course);
    }
    

    /**
    * @Description: 删除学科
    * @Param: [id]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/18/0018
    */
    @Override
    public void delete(Integer id) {

        //调用catalogDao方法判断对应的courseId对应的catalog有没有数据（有数据抛出异常，不允许删除）
        if (catalogDao.findTotalByCourseId(id)>0) {
            throw new RuntimeException("当前学科有关联的catalog，不能删除");
        }
        //调用tagDao方法判断对应的courseId对应的tag有没有数据（有数据抛出异常，不允许删除）
        if (tagDao.findTotalByCourseId(id) > 0) {
            throw new RuntimeException("当前学科有关联的tag，不能删除");
        }
        //调用questionDao方法判断对应的question对应的catalog有没有数据（有数据抛出异常，不允许删除）
        if (questionDao.findTotalByCourseId(id) > 0) {
            throw new RuntimeException("当前学科有关联的question，不能删除");
        }
        //调用dao方法删除学科信息
        courseDao.delete(id);
    }

    /**
    * @Description: 查询所有学科列表的方法
    * @Param: [status]
    * @Return: java.util.List<com.itheima.mm.pojo.Course>
    * @Author: Wangqibo
    * @Date: 2020/6/20/0020
    */
    @Override
    public List<Course> findAll(Integer status) {
        //调用dao方法获取总学科列表
        List<Course> courseList = courseDao.findAll(status);
        return courseList;
    }
}
