package annotation.beantest;

import annotation.configure.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTestOfLifeCycle {

    @Test
    public void byxml(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("创建容器完成");
        applicationContext.getBean("benCar");
        //容器关闭后，bean销毁
        applicationContext.close();
    }

    @Test
    public void byAnnotation(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("创建容器完成");
        applicationContext.getBean("benCar");
        //容器关闭后，bean销毁
        applicationContext.close();
    }
}
