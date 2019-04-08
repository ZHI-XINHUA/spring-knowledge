package com.zxh.controller;

import com.zxh.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        String service = helloService.hello();
        return "controller >>"+service;
    }
}
