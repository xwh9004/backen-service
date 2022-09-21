package com.example.springsecurity.cloud.oauth2.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xwh90
 * @date: 2022/9/21 9:56
 * @description:
 */

@RestController
public class HelloController {
    @GetMapping("hello")
    public String hello() {
        return "Hello";
    }
}
