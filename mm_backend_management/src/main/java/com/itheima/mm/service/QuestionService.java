package com.itheima.mm.service;

import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.QuestionItemDao;
import com.itheima.mm.dao.TagDao;
import com.itheima.mm.entity.PageResult;
import com.itheima.mm.entity.QueryPageBean;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.QuestionItem;
import com.itheima.mm.pojo.Tag;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-20 20:23
 */
public class QuestionService {
    /**
     * @Description: 分页查询基础题目列表信息
     * @Param: [queryPageBean]
     * @Return: com.itheima.mm.entity.PageResult
     * @Author: Wangqibo
     * @Date: 2020/6/20/0020
     */
    public PageResult findByPage(QueryPageBean queryPageBean) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);

        //调用dao方法获取总条数
        long totalByCondition = questionDao.getCountByCondition(queryPageBean);

        //调用dao方法获取当前页数据列表
        List<Question> questionList = questionDao.getQuestionListByPage(queryPageBean);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        //封装PageResult返回
        PageResult pageResult = new PageResult(totalByCondition, questionList);
        return pageResult;
    }

    /**
    * @Description: 添加基础题目的方法
    * @Param: [question]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/21/0021
    */
    public void addBasic(Question question) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
        QuestionItemDao questionItemDao = sqlSession.getMapper(QuestionItemDao.class);

        //调用questioDao方法添加基础题目，并查询出自增长id
        questionDao.addBasic(question);

        //循环遍历questionItemList，
        for (QuestionItem questionItem : question.getQuestionItemList()) {
            //设置questionId。
            questionItem.setQuestionId(question.getId());
            //调用questionItemDao方法添加基础题目选项信息
            questionItemDao.add(questionItem);
        }

        //添加题目的tag信息
        Map<String, Object> map = new HashMap<>();
        Integer questionId = question.getId();
        map.put("questionId", questionId);
        TagDao tagDao = sqlSession.getMapper(TagDao.class);
        for (Tag tag : question.getTagList()) {
            Integer tagId = tag.getId();
            map.put("tagId", tagId);
            tagDao.addTagQuestion(map);
        }

        //提交事务
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }
}
