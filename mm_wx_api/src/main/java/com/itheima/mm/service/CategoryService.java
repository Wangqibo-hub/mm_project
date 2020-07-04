package com.itheima.mm.service;

import com.itheima.mm.dao.CatalogDao;
import com.itheima.mm.dao.CompanyDao;
import com.itheima.mm.dao.WxMemberDao;
import com.itheima.mm.pojo.WxMember;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-23 12:29
 */
public class CategoryService {

    /**
    * @Description: 获取题库分类列表的方法
    * @Param: [requestMap]
    * @Return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    public List<Map<String, Object>> getCategoryList(Map requestMap) throws IOException {
        Integer memberId = (Integer) requestMap.get("memberId");
        Integer categoryType = (Integer) requestMap.get("categoryType");
        Integer categoryKind = Integer.valueOf((String) requestMap.get("categoryKind"));

        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        WxMemberDao memberDao = sqlSession.getMapper(WxMemberDao.class);
        CatalogDao catalogDao = sqlSession.getMapper(CatalogDao.class);
        CompanyDao companyDao = sqlSession.getMapper(CompanyDao.class);

        //调用WxMemberDao方法根据memberId获取会员信息
        WxMember wxMember = memberDao.getWxMemberById(memberId);

        List<Map<String, Object>> resultList = null;
        //先判断categoryType是哪个分类101、201、202、203
        if (categoryType == 101) {
            //刷题
            //再判断categoryKind是哪个分类（技术、企业、方向）
            if (categoryKind == 1) {
                //按学科目录查询
                //调用CatalogDao方法获取题库分类列表
                resultList = catalogDao.findByCourseId(wxMember);
                SqlSessionFactoryUtils.commitAndClose(sqlSession);
            } else if (categoryKind == 2) {
                //按企业查询
                //调用CompanyDao方法获取题库分类列表
                resultList = companyDao.findByCityId(wxMember);
                SqlSessionFactoryUtils.commitAndClose(sqlSession);
            } else if (categoryKind == 3) {
                //按方向查询
            }
        } else if (categoryType == 201) {
            //错题本
        } else if (categoryType == 202) {
            //我的练习
        } else if (categoryType == 203) {
            //收藏题目
        }
        return resultList;
    }
}
