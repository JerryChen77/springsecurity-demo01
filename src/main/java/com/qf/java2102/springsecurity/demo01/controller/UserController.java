package com.qf.java2102.springsecurity.demo01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ghy
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/add")
    public String add(){
        return "user add .......";
    }

    @RequestMapping("/update")
    public String update(){
        return "user update .......";
    }

}
