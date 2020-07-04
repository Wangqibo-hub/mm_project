package com.itheima.mm.service;

import com.itheima.mm.dao.CourseDao;
import com.itheima.mm.dao.DictDao;
import com.itheima.mm.pojo.Course;
import com.itheima.mm.pojo.Dict;
import com.itheima.mm.utils.LocationUtil;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-22 09:27
 */
public class CommonService {

    /**
    * @Description: 获取城市列表请求的方法
    * @Param: [requestMap]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Wangqibo
    * @Date: 2020/6/22/0022
    */
    public Map<String, Object> getCitys(Map requestMap) throws IOException {
        //调用高德地图API根据经纬度获取城市名
        String location = (String) requestMap.get("location");
        String city = LocationUtil.parseLocation(location);
        //根据城市名调用DictDao方法获取当前城市信息
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        DictDao dictDao = sqlSession.getMapper(DictDao.class);
        Dict locationCity = dictDao.getCityByName(city);
        //调用DictDao方法根据fs获取城市列表

        String fs = (String) requestMap.get("fs");
        List<Dict> cityList = dictDao.getCityByFs(fs);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);

        //将结果封装为Map对象返回
        Map<String, Object> cityMap = new HashMap<>();
        cityMap.put("location", locationCity);
        cityMap.put("citys", cityList);
        return cityMap;
    }


    /**
    * @Description: 获取学科列表请求的方法
    * @Param: []
    * @Return: java.util.List<com.itheima.mm.pojo.Course>
    * @Author: Wangqibo
    * @Date: 2020/6/22/0022
    */
    public List<Course> getCourses() throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CourseDao courseDao = sqlSession.getMapper(CourseDao.class);

        //调用CourseDao方法获取全部学科列表
        List<Course> courseList = courseDao.findAll();
        return courseList;
    }
}
