package com.zxh.config;

import com.zxh.bean.Person;
import com.zxh.filter.MyTypeFilter;
import com.zxh.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

/**
 * 注解配置
 */
@Configuration
@ComponentScan("com.zxh")
//@ComponentScan(value = "com.zxh",excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class,Service.class})
//})
//@ComponentScan(value = "com.zxh",includeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
//},useDefaultFilters = false)

//@ComponentScan(value = "com.zxh",includeFilters = {
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {BookService.class})
//},useDefaultFilters = false)
//@ComponentScan(value = "com.zxh",includeFilters = {
//        @ComponentScan.Filter(type=FilterType.CUSTOM,classes = {
//                MyTypeFilter.class
//        })
//},useDefaultFilters = false)
public class MainConfig {

    /**
     * prototype：多实例的：IOC容器启动并不会去调用方法创建对象放在容器中，每次获取的时候才会调用方法创建对象；
     * singleton：单实例的（默认值），IOC容器启动时会调用方法创建对象并放到IOC容器中，以后每次获取直接在容器中获取。
     * request：同一个请求创建一个实例
     * session：同一个session创建一个实例
     */
    @Lazy
    @Scope()
    @Bean
    public Person person(){
        System.out.println("Person 初始化....");
        return new Person("zxh",25);
    }
}
