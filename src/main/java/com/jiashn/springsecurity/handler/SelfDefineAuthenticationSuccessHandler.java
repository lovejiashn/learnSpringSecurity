package com.jiashn.springsecurity.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: jiangjs
 * @Description:
 * @Date: 2022/3/19 14:25
 **/
public class SelfDefineAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final String forWardUrl;
    public SelfDefineAuthenticationSuccessHandler(String forWardUrl){
        this.forWardUrl = forWardUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User)authentication.getPrincipal();
        response.setHeader("content-type","application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        JSONObject res = new JSONObject();
        res.put("user",user);
        writer.write(String.valueOf(res));
        writer.flush();
        writer.close();
        response.sendRedirect(forWardUrl);
    }
}