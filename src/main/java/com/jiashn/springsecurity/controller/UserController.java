package com.jiashn.springsecurity.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jiashn.springsecurity.domain.UserInfo;
import com.jiashn.springsecurity.utils.JsonResult;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jiangjs
 * @Description:
 * @Date: 2022/3/19 16:03
 **/
@RestController
public class UserController {

    @PostAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping("/toMain")
    public ModelAndView toMain(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main.html");
        return mav;
    }

    @PostFilter("filterObject.username == 'xiaohui'")
    @PreFilter("filterObject.username = 'xiaohong'")
    @GetMapping("/checkedUser.do")
    public List<UserInfo> checkedUser(List<UserInfo> users){
        List<UserInfo> userInfos = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("xiaohui");
        UserInfo userInfo2 = new UserInfo();
        userInfo2.setUsername("xiaohui");
        userInfos.add(userInfo2);
        userInfos.add(userInfo);
        return userInfos;
    }
}