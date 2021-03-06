package com.jiashn.springsecurity.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: jiangjs
 * @Description:
 * @Date: 2022/3/19 21:04
 **/
public class SelfDefineAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final String forwardUrl;
    public SelfDefineAuthenticationFailureHandler(String forwardUrl){
        this.forwardUrl = forwardUrl;
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.sendRedirect(forwardUrl);
    }
}