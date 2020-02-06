package com.zxh;

import com.zxh.bean.Person;
import com.zxh.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Person person = (Person) context.getBean("person");
        System.out.println(person);

        ApplicationContext annotaionContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person1 = annotaionContext.getBean("person",Person.class);
        System.out.println(person1);

        //绝对路径需加“file:”前缀
        ApplicationContext fileContext = new FileSystemXmlApplicationContext("file:/Users/zxh/source/Spring-Annotation/src/main/resources/beans.xml");
        Person person2 = fileContext.getBean("person",Person.class);
        System.out.println(person2);
    }
}
