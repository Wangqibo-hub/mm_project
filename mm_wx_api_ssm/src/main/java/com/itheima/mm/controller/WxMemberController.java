package com.itheima.mm.controller;

import com.itheima.mm.entity.Result;
import com.itheima.mm.pojo.WxMember;
import com.itheima.mm.service.WxMemberService;
import com.itheima.mm.service.impl.WxMemberServiceImpl;
import com.itheima.mm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-22 14:21
 */
@RestController
@RequestMapping("member")
public class WxMemberController {

    //实例化业务层对象
    @Autowired
    private WxMemberService wxMemberService;

    /**
    * @Description: 处理登陆注册请求的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    @RequestMapping("login")
    public Result login(WxMember requestWxMember) {
        try {
            //调用业务层方法根据用户名查询数据库用户信息
            WxMember wxMember = wxMemberService.getWxMemberBynickName(requestWxMember.getNickName());
            //判断查询出的用户是否有效
            if (wxMember == null) {
                //无效，表示第一次登陆，进行注册操作
                //手动封装createTime
                requestWxMember.setCreateTime(DateUtils.parseDate2String(new Date()));
                //调用业务层方法添加用户信息
                wxMember = wxMemberService.add(requestWxMember);
            }
            //有效，说明已经注册过
            Map<String,Object> resultMap = new HashMap<>();
            //获取该用户在数据库中的id，作为token返回
            String memberId = wxMember.getId() + "";
            resultMap.put("token", memberId);
            //用户对象作为userInfo返回
            resultMap.put("userInfo", wxMember);
            //获取成功将结果封装到result对象中，响应给前端
            return new Result(true, "登陆成功", resultMap);
        } catch (Exception e) {
            //获取失败，返回失败信息
            e.printStackTrace();
           return new Result(false, "登陆失败");
        }
    }

    /**
    * @Description: 处理设置城市和学科信息的方法
    * @Param: [request, response]
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/23/0023
    */
    @RequestMapping("setCityCourse")
    public Result setCityCourse(HttpServletRequest request,@RequestBody Map requestMap) {
        try {
            //获取请求头中的Authorization
            String authorization = request.getHeader("Authorization");

            //截取Authorization中的memberId
            Integer memberId = Integer.valueOf(authorization.substring(7));

            //调用业务层方法根据memberId查询会员信息
            WxMember wxMember = wxMemberService.getWxMemberById(memberId);

            //分别设置WxMember的cityId和courseId
            wxMember.setCityId((Integer) requestMap.get("cityID"));
            wxMember.setCourseId((Integer) requestMap.get("subjectID"));

            //调用业务层方法修改会员信息
            wxMemberService.updateWxMember(wxMember);

            //修改成功，返回成功信息
            return new Result(true,"修改城市和学科成功");
        } catch (Exception e) {
            //修改失败，返回失败信息
            e.printStackTrace();
            return new Result(true,"修改城市和学科成功");
        }
    }

    /**
    * @Description: 处理获取个人中心的请求的方法
    * @Param: []
    * @Return: void
    * @Author: Wangqibo
    * @Date: 2020/6/25/0025
    */
    @RequestMapping("center")
    public Result center(HttpServletRequest request) {
        try {
            //获取请求头中的Authorization中的memberId
            Integer memberId = Integer.valueOf(request.getHeader("Authorization").substring(7));

            //调用业务层方法获取个人中心信息
            Map<String, Object> resultMap = wxMemberService.findCenter(memberId);
            //成功，返回成功信息
            return new Result(true,"获取个人中心信息成功", resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            //失败，返回失败信息
            return new Result(false,"获取个人中心信息成功");
        }
    }
}
