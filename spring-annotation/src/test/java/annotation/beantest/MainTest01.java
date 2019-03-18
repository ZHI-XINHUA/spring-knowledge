package annotation.beantest;

import annotation.bean.Person;
import annotation.bean.Phone;
import annotation.configure.MainConfig01;
import annotation.configure.MainConfig02;
import annotation.configure.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest01 {
    /**
     * xml方式创建bean 测试
     */
    @Test
    public void beanByXML(){
        //创建上下文,ClassPathXmlApplicationContext加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        Person person2 = (Person) applicationContext.getBean("person");
        System.out.println(person2);

        //获取容器中所有bean
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String bean:
                beans) {
            System.out.println(bean);
        }
    }

    /**
     * 注解方式创建bean 测试
     */
    @Test
    public void beanByAnnotation(){
        //创建上下文 AnnotationCongigApplicationContext
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig01.class);

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        Person person1 = (Person) applicationContext.getBean("person2");
        System.out.println(person1);


        Person person2 = (Person) applicationContext.getBean("person2");
        System.out.println(person2);

        //获取容器中所有bean
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String bean:
             beans) {
            System.out.println(bean);
        }
    }

    @Test
    public void beanByAnnotation1(){
        //创建上下文 AnnotationCongigApplicationContext
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig02.class);
        System.out.println("已获取上下文...");

        Person person = (Person) applicationContext.getBean(Person.class);
        System.out.println(person.getName()+":"+person.getAge());

    }

    @Test
    public void beanImport(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig02.class);

        //获取容器中所有bean
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String bean:
                beans) {
            System.out.println(bean);
        }
    }

    @Test
    public void factoryBean(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig02.class);
        //获取phoneFactoryBean创建的对象（getObject 返回的对象）
        Object phoneFactoryBean1 = applicationContext.getBean("phoneFactoryBean");
        Object phoneFactoryBean2 = applicationContext.getBean("phoneFactoryBean");
        System.out.println(phoneFactoryBean1==phoneFactoryBean2);//false 因为创建不是单例的

        String name = phoneFactoryBean1.getClass().getName();
        System.out.println(name);//annotation.bean.Phone

        Phone phone = (Phone) phoneFactoryBean1;
        System.out.println("name:"+phone.getName()+" price:"+phone.getPrice()+" size:"+phone.getSize());

        //获取bean本身
        Object bean = applicationContext.getBean("&phoneFactoryBean");
        System.out.println(bean.getClass());

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
