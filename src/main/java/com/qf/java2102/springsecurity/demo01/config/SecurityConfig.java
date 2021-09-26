package com.qf.java2102.springsecurity.demo01.config;

import com.qf.java2102.springsecurity.demo01.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ghy
 * @version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("安全框架开始工作了。。。。");
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //自定义权限不足页面
        http.exceptionHandling().accessDeniedPage("/403.html");


        //配置相关的信息
        http
                //登录
                .formLogin()   //表单登录
                .loginPage("/login.html")    //登录页
                .loginProcessingUrl("/user/login")//处理登录请求的url
                //如果访问其他资源没有权限，跳回到登录页，那么登录成功后，就会去之前访问的资源
                //没有访问过任何目标资源，直接从登录页进来，则就会进入默认的成功页
                .defaultSuccessUrl("/success.html")    //没有指定目标资源
                .permitAll()    //经过上述步骤后，需要放行
        .and()
                //释放指定资源
                .authorizeRequests()
                .antMatchers("/test1", "/test2")
                .permitAll()
        .and()
                //配置哪些资源需要哪些角色或权限访问
                //基于角色访问
                .authorizeRequests()
                // 访问 /user/下的资源，需要admin角色
                .antMatchers("/user/*")
                .hasRole("admin")
                //基于权限访问
                //访问/aaa资源，需要user_aaa或者user_bbb权限
                .antMatchers("/aaa").hasAnyAuthority("user_aaa", "user_bbb")
                //访问/aaa资源，需要user_bbb权限
                .antMatchers("/bbb").hasAuthority("user_bbb")
        .and()
                //拦截指定资源
                .authorizeRequests()
                .anyRequest()
                //只要认证通过，就可以访问
                .authenticated()
        .and()
                //退出
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/login.html")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
        .and()
                //关闭csrf认证    csrf:跨站伪造请求
                .csrf().disable();

        //退出

    }
}
