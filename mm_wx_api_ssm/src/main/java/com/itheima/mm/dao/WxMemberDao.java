package com.itheima.mm.dao;

import com.itheima.mm.pojo.WxMember;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-22 14:31
 */
public interface WxMemberDao {

    /**
    * @Description: 根据昵称获取会员
    * @Param: [nickName]
    * @Return: com.itheima.mm.pojo.WxMember
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    WxMember getWxMemberByNickname(String nickName);

    /**
    * @Description: 添加会员
    * @Param: [requestWxMember]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    void add(WxMember requestWxMember);

    /**
    * @Description: 根据id获取会员
    * @Param: [memberId]
    * @Return: com.itheima.mm.pojo.WxMember
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    WxMember getWxMemberById(@Param("id") Integer memberId);

    /**
    * @Description: 更新会员信息
    * @Param: [wxMember]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    void update(WxMember wxMember);

    /**
    * @Description: 获取会员答题总数
    * @Param: []
    * @Return: int
    * @Author: Wangqibo
    * @Date: 2020/6/25/0025
    */
    int getAnwserCount(Integer id);

    /**
    * @Description: 查询会员最后答题信息
    * @Param: [memberId]
    * @Return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: Wangqibo
    * @Date: 2020/6/25/0025
    */
    Map<String, Object> getLastAnswer(Integer memberId);

}
