package com.zxh.test;

import com.zxh.config.MainConfig3;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanTest {

    @Test
    public void beanLifeCycle(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig3.class);
        System.out.println("容器初始化完成...");

        ((AnnotationConfigApplicationContext) context).close();
    }
}
