package annotation.configure;

import annotation.bean.Person;
import annotation.other.UserController;
import org.springframework.context.annotation.*;

@Configuration //声明这是配置类

// 包扫描、只要标注了@Controller、@Service、@Repository，@Component扫描起作用
//@ComponentScan("annotation")
@ComponentScans({
       /* @ComponentScan(value ="annotation",excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {UserController.class})
        })*/
       @ComponentScan(value = "annotation",includeFilters = {
               @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})  //自定义filter
       },useDefaultFilters = false)
})
//@ComponentScan  value:指定要扫描的包
//excludeFilters = Filter[] ：指定扫描的时候按照什么规则排除那些组件
//includeFilters = Filter[] ：指定扫描的时候只需要包含哪些组件
//FilterType.ANNOTATION：按照注解
//FilterType.ASSIGNABLE_TYPE：按照给定的类型；
//FilterType.ASPECTJ：使用ASPECTJ表达式
//FilterType.REGEX：使用正则指定
//FilterType.CUSTOM：使用自定义规则

//可以参考文章：https://blog.csdn.net/luojinbai/article/details/85877956
public class MainConfig01 {

    @Bean(name = "person")  //在容器中注册bean，默认id是方法名
    public Person person1(){
        return new Person("张三",30);
    }

    @Scope("prototype")
    @Bean(name = "person2")
    public Person person2(){
        System.out.println("初始化person2");
        return new Person("李四",29);
    }


    @Bean
    @Lazy //懒加载，用到的时候才去实例化
    public Person person3(){
        System.out.println("初始化person3");
        return new Person("王五",29);
    }
}
