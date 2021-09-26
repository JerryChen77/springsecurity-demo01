package com.qf.java2102.springsecurity.demo01.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.java2102.springsecurity.demo01.mapper.UserInfoMapper;
import com.qf.java2102.springsecurity.demo01.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author ghy
 * @version 1.0
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserInfoMapper userInfoMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("认证开始了.....................");

        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if(null == userInfo) {
            return null;
        }
        /*

        Collection<? extends GrantedAuthority> authorities = null;
        authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("");  //不限定角色与权限
        return new User(username, userInfo.getPassword(), authorities);
*/

        Collection<? extends GrantedAuthority> authorities = null;
        //只有username=tom&password=123456
        if("tom".equals(username)){
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin, user_aaa");  //不限定角色与权限
            System.out.println("登录的是tom");
            return new User(username, passwordEncoder.encode("123456"), authorities);
        } else if("lucy".equals(username)) {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_user, user_bbb");  //不限定角色与权限
            System.out.println("登录的是lucy");
            return new User(username, passwordEncoder.encode("123456"), authorities);
        }
        System.out.println("登录的人不是tom，失败");
        return null;
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (int i = 0; i < 5; i++) {
            String encode = passwordEncoder.encode("123456");
            System.out.println(encode + " " + encode.length());
        }

        String pwd = "$2a$10$Mg6Ssf0RDaycmbgGv6NFjeq.5ibDKlwXXAOh19BXR9fJL6pViVSOu";
        //比对密码
        System.out.println(passwordEncoder.matches("123456", pwd));
        System.out.println(passwordEncoder.matches("123456", "$2a$10$UPSFAGKEdGJgtuqD6boW7eXAErwApq5mwgNDwV0rS/4zbznOVYxiE"));

    }

}
