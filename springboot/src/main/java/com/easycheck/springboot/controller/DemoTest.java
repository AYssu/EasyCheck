package com.easycheck.springboot.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoTest {
    @GetMapping("/test")
    public String test(){
        return "hello world2";
    }
}
