package com.zxh.demo.controller;

import com.zxh.demo.service.IDemoService;
import com.zxh.mvcframework.annotation.XHAutowired;
import com.zxh.mvcframework.annotation.XHController;
import com.zxh.mvcframework.annotation.XHRequestMapping;
import com.zxh.mvcframework.annotation.XHRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@XHController
@XHRequestMapping("/demo")
public class DemoController {

    @XHAutowired
    private IDemoService demoService;

    @XHRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response,
                      @XHRequestParam("name") String name){
        String result = demoService.getName(name);

        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @XHRequestMapping("/add")
    public void add(HttpServletRequest request,HttpServletResponse response,
                    @XHRequestParam("a") Integer a,@XHRequestParam("b") Integer b){
        String result = a+"+"+b+"="+(a+b);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
