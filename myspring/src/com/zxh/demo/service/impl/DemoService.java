package com.zxh.demo.service.impl;

import com.zxh.demo.service.IDemoService;
import com.zxh.mvcframework.annotation.XHService;

@XHService
public class DemoService implements IDemoService {
    @Override
    public String getName(String name) {
        return "My Name is "+name;
    }
}
