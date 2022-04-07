package com.jiashn.springsecurity.config;

import com.jiashn.springsecurity.handler.SelfDefineAccessDeniedHandler;
import com.jiashn.springsecurity.handler.SelfDefineAuthenticationFailureHandler;
import com.jiashn.springsecurity.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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
    @Autowired
    private UserDetailServiceImpl userDetailsService;
  /*  @Autowired
    private PersistentTokenRepository repository;
    @Autowired
    private DataSource dataSource;*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //提交表单
        http.formLogin()
                //当发现login时，则认为是登录，需与表单提交的地址一样，才能执行自定义的UserDetailsServiceImpl
                .loginProcessingUrl("/login")
                //指定自定义登录页面
                .loginPage("/showLogin")
               // .successHandler(new SelfDefineAuthenticationSuccessHandler(successUrl))
                .defaultSuccessUrl("/toMain",true)
                .failureHandler(new SelfDefineAuthenticationFailureHandler("/error.html"));

        http.authorizeRequests()
                //登录页面，失败页面不需要进行验证
                .antMatchers("/showLogin").permitAll()
                .antMatchers().access("hasRole(\"admin\")")
                //指定所有页面都必须进行验证后才能访问
                //.anyRequest().access("@userVisitPermissionsServiceImpl.hasPermission(httpServletRequest,authentication)");
                .anyRequest().authenticated();

        //关闭csrf防护
        //http.csrf().disable();

        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
       /* //记住我
        http.rememberMe()
                .tokenValiditySeconds(60)
                .rememberMeParameter("rememberMe")
                .alwaysRemember(true)
                .userDetailsService(userDetailsService)
                .tokenRepository(repository);*/

        http.logout()
                .logoutSuccessUrl("/login.html");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

   /* @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //自动在数据库创建表
       jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }*/
}