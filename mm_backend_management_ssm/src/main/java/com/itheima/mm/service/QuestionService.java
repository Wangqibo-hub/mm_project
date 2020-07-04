package com.itheima.mm.service;

import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Question;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-07-02 20:39
 */
public interface QuestionService {
    PageResult findByPage(QueryPageBean queryPageBean);

    void addBasic(Question question) throws Exception;
}
