package com.itheima.mm.service;

import com.itheima.mm.pojo.WxMember;

import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-07-04 09:25
 */
public interface WxMemberService {
    WxMember getWxMemberBynickName(String nickName);

    WxMember add(WxMember requestWxMember);

    WxMember getWxMemberById(Integer memberId);

    void updateWxMember(WxMember wxMember);

    Map<String, Object> findCenter(Integer memberId);
}
