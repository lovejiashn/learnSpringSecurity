package com.jiashn.springsecurity.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: jiangjs
 * @Description:
 * @Date: 2022/3/19 19:40
 **/
@Component
public class SelfDefineAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        JSONObject result = new JSONObject();
        result.put("code",HttpServletResponse.SC_FORBIDDEN);
        result.put("msg","当前用户没有权限,请联系管理员。");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("Content-type","application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(String.valueOf(result));
        writer.flush();
        writer.close();
    }
}