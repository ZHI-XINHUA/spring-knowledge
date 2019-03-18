package annotation.beantest;

import annotation.bean.PersonValue;
import annotation.configure.MainConfigAutowired;
import annotation.configure.MainConfigOfPropertyValues;
import annotation.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTestProperty {
    @Test
    public void testProperty(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        PersonValue personValue = (PersonValue) applicationContext.getBean("personValue");
        System.out.println(personValue);
    }

    @Test
    public void testAutowired(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigAutowired.class);
        BookService bookService = applicationContext.getBean(BookService.class);

        System.out.println(bookService);
    }
}
