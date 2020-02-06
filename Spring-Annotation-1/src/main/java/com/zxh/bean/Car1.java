package com.zxh.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Car1 implements InitializingBean,DisposableBean {
    public Car1(){
        System.out.println("car1 constructor....");
    }

    /**
     * 初始化bean
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        System.out.println("car1 init....");
    }

    /**
     * 销毁bean
     * @throws Exception
     */
    public void destroy() throws Exception {
        System.out.println("car1 destory...");
    }


}
