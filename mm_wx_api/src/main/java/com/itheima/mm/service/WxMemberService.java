package com.itheima.mm.service;

import com.itheima.mm.dao.CatalogDao;
import com.itheima.mm.dao.CompanyDao;
import com.itheima.mm.dao.WxMemberDao;
import com.itheima.mm.pojo.WxMember;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-22 14:23
 */
public class WxMemberService {

    /**
    * @Description: 根据昵获取会员
    * @Param: [nickName]
    * @Return: com.itheima.mm.pojo.WxMember
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    public WxMember getWxMemberBynickName(String nickName) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao memberDao = sqlSession.getMapper(WxMemberDao.class);

        //调用WxMemberDao方法根据昵称获取用户
        WxMember wxMember = memberDao.getWxMemberByNickname(nickName);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return wxMember;
    }

    /**
    * @Description: 添加会员
    * @Param: [requestWxMember]
    * @Return: com.itheima.mm.pojo.WxMember
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    public WxMember add(WxMember requestWxMember) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao memberDao = sqlSession.getMapper(WxMemberDao.class);
        //调用WxMemberDao方法添加用户
        memberDao.add(requestWxMember);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return requestWxMember;
    }

    /**
    * @Description: 根据id获取会员
    * @Param: [memberId]
    * @Return: com.itheima.mm.pojo.WxMember
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    public WxMember getWxMemberById(Integer memberId) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao memberDao = sqlSession.getMapper(WxMemberDao.class);
        //调用WxMemberDao方法根据id获取会员
        WxMember wxMember = memberDao.getWxMemberById(memberId);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return wxMember;
    }

    /**
    * @Description: 更新会员
    * @Param: [wxMember]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    public void updateWxMember(WxMember wxMember) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao memberDao = sqlSession.getMapper(WxMemberDao.class);
        //调用WxMemberDao方法修改会员
        memberDao.update(wxMember);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }

    /**
    * @Description: 获取个人中心信息的方法
    * @Param: [memberId]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Wangqibo
    * @Date: 2020/6/25/0025
    */
    public Map<String, Object> findCenter(Integer memberId) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao wxMemberDao = sqlSession.getMapper(WxMemberDao.class);

        //调用WxMemberDao方法查找累计答题数
        int answerCount = wxMemberDao.getAnwserCount(memberId);

        //调用WxMemberDao方法查找用户所答最后一道题的信息
        Map<String, Object> lastAnswer = wxMemberDao.getLastAnswer(memberId);
        String categoryTitle = null;
        if ((Integer) lastAnswer.get("categoryKind") == 1) {
            CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
            categoryTitle = catalogDao.findNameById((Integer)lastAnswer.get("categoryId"));
        } else if ((Integer) lastAnswer.get("categoryKind") == 2) {
            CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);
            categoryTitle = companyDao.findNameById((Integer)lastAnswer.get("categoryId"));
        }
        lastAnswer.put("categoryTitle",categoryTitle);

        //调用WxMemberDao方法查找用户的方向和学科信息
        WxMember wxMember = wxMemberDao.getWxMemberById(memberId);
        Map<String, Object> category = new HashMap<>();
        category.put("cityID", wxMember.getCityId());
        category.put("subjectID", wxMember.getCourseId());
        SqlSessionFactoryUtils.commitAndClose(sqlSession);

        //将查询结果封装到resultMap中返回
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("answerCount", answerCount);
        resultMap.put("lastAnswer", lastAnswer);
        resultMap.put("category", category);

        return resultMap;
    }

}
