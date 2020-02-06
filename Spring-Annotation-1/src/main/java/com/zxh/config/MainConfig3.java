package com.zxh.config;

import com.zxh.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.zxh.bean")
public class MainConfig3 {

    @Bean(initMethod = "init",destroyMethod = "destory")
    public Car car(){
        return new Car();

    }
}
