package com.zxh.test;

import com.zxh.bean.Person;
import com.zxh.config.MainConfig;
import com.zxh.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class IOCTest {

    @Test
    public void testAnnotaion(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames){
            System.out.println(beanName);
        }
    }

    @Test
    public void testXml(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName:beanNames){
            System.out.println(beanName);
        }
    }

    @Test
    public void scopeTest(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person1 = applicationContext.getBean("person",Person.class);
        Person person2 = applicationContext.getBean("person",Person.class);
        System.out.println(person1==person2);
    }

    @Test
    public void conditionTest(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanNames = applicationContext.getBeanNamesForType(Person.class);
        for (String beanName:beanNames){
            System.out.println(beanName);
        }

        Map<String,Person> map = applicationContext.getBeansOfType(Person.class);
        System.out.println(map);
    }

    @Test
    public void importTest(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName:beanNames){
            System.out.println(beanName);
        }
    }

    @Test
    public void factoryBeanTest(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        Object object = applicationContext.getBean("colorFactoryBean");
        //class com.zxh.bean.Color
        System.out.println("bean 类型："+ object.getClass());


        Object object1 = applicationContext.getBean("&colorFactoryBean");
        //com.zxh.bean.ColorFactoryBean
        System.out.println("工厂Bean本身："+ object1.getClass());

    }
}
