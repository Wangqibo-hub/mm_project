package com.itheima.mm.service;

import com.itheima.mm.pojo.Course;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-07-04 09:24
 */
public interface CommonService {
    Map<String, Object> getCitys(Map requestMap);

    List<Course> getCourses() throws IOException;
}
