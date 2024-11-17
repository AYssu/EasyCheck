package com.easyverify.springboot.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("open")
public class DemoTest {
    @GetMapping("/test")
    public String test(){
        return "hello world2";
    }
}
