package annotation.configure;


import annotation.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 自动装配;
 * 		Spring利用依赖注入（DI），完成对IOC容器中中各个组件的依赖关系赋值；
 *
 * 1）、@Autowired：自动注入：
 * 		1）、默认优先按照类型去容器中找对应的组件:applicationContext.getBean(BookDao.class);找到就赋值
 * 		2）、如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找
 * 							applicationContext.getBean("bookDao")
 * 		3）、@Qualifier("bookDao")：使用@Qualifier指定需要装配的组件的id，而不是使用属性名
 * 		4）、自动装配默认一定要将属性赋值好，没有就会报错；
 * 			可以使用@Autowired(required=false);
 * 		5）、@Primary：让Spring进行自动装配的时候，默认使用首选的bean；
 * 				也可以继续使用@Qualifier指定需要装配的bean的名字
 * 		BookService{
 * 			@Autowired
 * 			BookDao  bookDao;
 * 		}
 *
 * 2）Spring还支持使用@Resource(JSR250)和@Inject(JSR330) java规范的注解
 * @Resource:
 *    可以和@Autowired一样实现自动转配功能，默认是安装组件名称进行装配的
 *    不支持@Primary功能，不支持@Autowired(requireed=false)
 *
 * @Inject：
 *    导入javax.inject的包，和Autowired功能一样
 *
 **/
@Configuration
@ComponentScan({"annotation.dao","annotation.service","annotation.bean"})
public class MainConfigAutowired {


    @Primary
    @Bean
    public BookDao bookDao1(){
        return  new BookDao("2");
    }
}
