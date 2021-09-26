package com.qf.java2102.springsecurity.demo01.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ghy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class UserInfo {


    private Long id;
    private String username;
    private String password;

}
