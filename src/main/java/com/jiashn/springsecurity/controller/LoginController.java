package com.jiashn.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jiangjs
 * @date 2022-04-05 11:09
 */
@Controller
public class LoginController {

    @RequestMapping("/showLogin")
    public String showLogin(){
        return "login";
    }
}
