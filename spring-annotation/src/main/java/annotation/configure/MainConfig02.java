package annotation.configure;

import annotation.bean.Car;
import annotation.bean.Circular;
import annotation.bean.Person;
import annotation.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//满足当前条件 类才生效
//@Conditional(LinuxCondition.class)

//import 以配置3种类型的值Configuration，ImportSelector，ImportBeanDefinitionRegistrar   参考：https://blog.51cto.com/4247649/2118354
@Import({Circular.class, Car.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
@Configuration
public class MainConfig02 {

    @Conditional(LinuxCondition.class)
    @Bean
    public Person linuxPerson(){
        System.out.println("实例化linuxPerson");
        return new Person("linux",33);
    }

    @Conditional(WindowCondition.class)
    @Bean
    public Person windowsPerson(){
        System.out.println("实例化windowsPerson");
        return new Person("windows",33);
    }

    /**
     * 给容器中注册组件；
     * 1）、包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的类]
     * 2）、@Bean[导入的第三方包里面的组件]
     * 3）、@Import[快速给容器中导入一个组件]
     * 		1）、@Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id默认是全类名
     * 		2）、ImportSelector:返回需要导入的组件的全类名数组；
     * 		3）、ImportBeanDefinitionRegistrar:手动注册bean到容器中
     * 4）、使用Spring提供的 FactoryBean（工厂Bean）;
     * 		1）、默认获取到的是工厂bean调用getObject创建的对象
     * 		2）、要获取工厂Bean本身，我们需要给id前面加一个&
     * 			&phoneFactoryBean
     *
     * 	参考文章：
     * 	  https://blog.csdn.net/zknxx/article/details/79572387
     *    https://www.cnblogs.com/aspirant/p/9082858.html
     */
    @Bean
    public PhoneFactoryBean phoneFactoryBean(){
        return  new PhoneFactoryBean();
    }
}
