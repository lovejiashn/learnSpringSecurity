package com.jiashn.springsecurity.controller;

import com.jiashn.springsecurity.utils.JsonResult;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: jiangjs
 * @Description:
 * @Date: 2022/3/19 16:03
 **/
@RestController
public class UserController {

    @Secured("ROLE_admin")
    @GetMapping("/toMain")
    public ModelAndView toMain(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main.html");
        return mav;
    }
}