package com.qf.java2102.springsecurity.demo01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ghy
 * @version 1.0
 */
@RestController
public class Demo01Controller {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Spring Security!!";
    }

    @RequestMapping("/aaa")
    public String aaa(){
        return "AAA";
    }

    @RequestMapping("/bbb")
    public String bbb(){
        return "BBB";
    }


}
