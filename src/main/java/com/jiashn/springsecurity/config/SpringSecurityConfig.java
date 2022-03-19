package com.jiashn.springsecurity.config;

import com.jiashn.springsecurity.handler.SelfDefineAccessDeniedHandler;
import com.jiashn.springsecurity.handler.SelfDefineAuthenticationFailureHandler;
import com.jiashn.springsecurity.handler.SelfDefineAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: jiangjs
 * @Description:
 * @Date: 2022/3/19 11:08
 **/
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${sso.security.successUrl}")
    private String successUrl;

    @Autowired
    private SelfDefineAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //提交表单
        http.formLogin()
                //当发现login时，则认为是登录，需与表单提交的地址一样，才能执行自定义的UserDetailsServiceImpl
                .loginProcessingUrl("/login")
                //指定自定义登录页面
                .loginPage("/login.html")
                .successHandler(new SelfDefineAuthenticationSuccessHandler(successUrl))
                .failureHandler(new SelfDefineAuthenticationFailureHandler("/error.html"));

        http.authorizeRequests()
                //登录页面，失败页面不需要进行验证
                .antMatchers("/login.html").permitAll()
                //指定所有页面都必须进行验证后才能访问
                .anyRequest().authenticated();

        //关闭csrf防护
        http.csrf().disable();

        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}