package com.qf.java2102.springsecurity.demo01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ghy
 * @version 1.0
 */
@RestController
public class TestController {

    @RequestMapping("/test1")
    public String test1(){
        return "TEST1";
    }

    @RequestMapping("/test2")
    public String test2(){
        return "TEST2";
    }

}
