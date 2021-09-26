package com.qf.java2102.springsecurity.demo01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ghy
 * @version 1.0
 */
//@Configuration
public class SecurityConfig1 extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        String password = passwordEncoder.encode("123123");

        //1.基于内存配置用户名与密码
        //auth.inMemoryAuthentication().withUser("java2102").password("{noop}123123").roles();

        auth.inMemoryAuthentication().withUser("java2102").password(password).roles();

        //1.1 springsecurity配置类登录方式，密码必须使用加密策略。
        //{noop} : 是一种加密策略，不加密
        //譬如：md5、sha-1,sha-256
        //springsecurity默认使用的是 bcrypt
    }


    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("123123");

        //通过手动指定密码加密器也可以。  了解
        auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("java2102").password(password).roles();
    }
*/
}
