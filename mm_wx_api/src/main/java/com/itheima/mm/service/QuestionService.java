package com.itheima.mm.service;

import com.alibaba.fastjson.JSONArray;
import com.itheima.mm.dao.CatalogDao;
import com.itheima.mm.dao.CompanyDao;
import com.itheima.mm.dao.QuestionDao;
import com.itheima.mm.dao.WxMemberDao;
import com.itheima.mm.pojo.Question;
import com.itheima.mm.pojo.WxMember;
import com.itheima.mm.utils.ParseIntegerUtil;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-23 19:35
 */
public class QuestionService {

    /**
    * @Description: 获取题目列表的方法
    * @Param: [requestMap]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    public Map<String, Object> getQuestionList(Map requestMap) throws IOException {
        Integer categoryType = Integer.valueOf((String) requestMap.get("categoryType"));
        Integer categoryKind = Integer.valueOf((String) requestMap.get("categoryKind"));

        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
        CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
        QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
        Map<String, Object> resultMap = null;

        //先判断categoryType是哪个分类101、201...
        if (categoryType == 100) {
            //刷题
            //再判断categoryKind是哪个分类（技术1、企业2、方向3）
            if (categoryKind == 1) {
                //按二级目录查询题目列表
                //调用CatalogDao方法根据categoryId查询二级目录信息，封装到ResultMap中
                resultMap = catalogDao.findById(requestMap);
                //调用QuestionDao方法获取题目列表
                List<Question> questionList = questionDao.findList(requestMap);
                //将题目列表封装到ResultMap中,名称为items，返回
                resultMap.put("items", questionList);
                SqlSessionFactoryUtils.commitAndClose(sqlSession);
            } else if (categoryKind == 2) {
                //按企业查询题目列表
                //调用CompanyDao方法查询根据categoryId企业信息，封装到ResultMap中
                resultMap = companyDao.findById(requestMap);
                //调用QuestionDao方法获取题目列表
                List<Question> questionList = questionDao.findList(requestMap);
                //将题目列表封装到ResultMap中,名称为items，返回
                resultMap.put("items", questionList);
                SqlSessionFactoryUtils.commitAndClose(sqlSession);
            } else if (categoryKind == 3) {
                //按方向查询题目列表
            }
        } else if (categoryType == 201) {
            //错题本
        } else if (categoryType == 202) {
            //我的练习
        } else if (categoryType == 203) {
            //收藏题目
        }
        return resultMap;
    }

    /**
    * @Description: 处理提交答题信息的方法
    * @Param: [requestMap]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/24/0024
    */
    public void questionCommit(Map requestMap) throws IOException {

        //判断是否收藏，更新收藏信息为1和0
        Boolean isFavorite = (Boolean) requestMap.get("isFavorite");
        if (isFavorite) {
            requestMap.put("isFavorite", 1);
        } else {
            requestMap.put("isFavorite", 0);
        }

        //判断answerResult是否有效
        Object answerResult = requestMap.get("answerResult");
        if (answerResult == null) {
            //有效表示题型是单选或多选
            //根据answerIsRight设置tag为0或1
            Boolean answerIsRight = (Boolean) requestMap.get("answerIsRight");
            if (answerIsRight) {
                requestMap.put("tag", 0);
            } else {
                requestMap.put("tag", 1);
            }
        } else {
            //无效表示题型是简答题
            //根据answerIsRight设置tag为2或3
            Boolean answerIsRight = (Boolean) requestMap.get("answerIsRight");
            if (answerIsRight) {
                requestMap.put("tag", 2);
            } else {
                requestMap.put("tag", 3);
            }

            //设置answerResult
            JSONArray jsonArray = (JSONArray) answerResult;
            String answerResultStr = jsonArray.toString();
            requestMap.put("answerResult",answerResultStr);
        }

        //调用QuestionDao方法根据memberId和questionId查询会员做题信息
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        QuestionDao questionDao = sqlSession.getMapper(QuestionDao.class);
        Map<String, Object> answerMap =  questionDao.findByMemberIdAndQuestionId(requestMap);
        if (answerMap != null) {
            //有效表示该题已经做过
            //调用questionDao方法修改会员做题信息
            questionDao.updateAnswer(requestMap);
        } else {
            //无效表示第一次做该题
            //调用questionDao方法添加会员做题信息
            questionDao.addAnswer(requestMap);
        }

        //调用wxMemberDao方法根据memberId查询用户信息，然后更新各种lastXXX信息
        WxMemberDao wxMemberDao = sqlSession.getMapper(WxMemberDao.class);
        WxMember wxMember = wxMemberDao.getWxMemberById((Integer) requestMap.get("memberId"));
        wxMember.setLastCategoryId(ParseIntegerUtil.parseObjectToInteger(requestMap.get("categoryID")));
        wxMember.setLastCategoryKind(ParseIntegerUtil.parseObjectToInteger(requestMap.get("categoryKind")));
        wxMember.setLastCategoryType(ParseIntegerUtil.parseObjectToInteger(requestMap.get("categoryType")));
        wxMember.setLastQuestionId(ParseIntegerUtil.parseObjectToInteger(requestMap.get("id")));

        wxMemberDao.update(wxMember);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }
}
