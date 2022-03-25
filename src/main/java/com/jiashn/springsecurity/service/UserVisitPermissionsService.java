package com.jiashn.springsecurity.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jiangjs
 * @date 2022-03-22 7:24
 */
public interface UserVisitPermissionsService {
    /**
     * 验证当前登录用户是否具有某种权限
     * @param request 请求
     * @param authentication 权限
     * @return 返回验证结果
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication) ;
}
