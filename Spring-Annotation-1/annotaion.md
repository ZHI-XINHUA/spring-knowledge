# spring annotaion

# 一、组件注册



## 1、ApplicationContext
> Spring有两个核心接口：BeanFactory和ApplicationContext，其中ApplicationContext是BeanFactory的子接口。他们是spring容器，用于管理容器中的bean。

ApplicationContext常见的三种实现;
 - `ClassPathXmlApplicationContext`：加载类路径下的bean配置文件
 - `FileSystemXmlApplicationContext`：加载系统路径下的bean配置文件
 - `AnnotationConfigApplicationContext`：获取注解的bean

 ```java
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
 ```



## 2、@Configuration

`@Comfiguration`是3.0的注解，用于定义配置类，替换xml文件配置的方式，管理bean容器。

### （1）源码：

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration {
	@AliasFor(annotation = Component.class)
	String value() default "";

}
```

- `@Component`的`派生`



### （2）`@Configuration`与xml配置:

```java
@Configuration
public class MainConfig {

}
```

等价于：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    
</beans>
```



### （3）获取上下文

- `@Comfiguration`：通过`AnnotationConfigApplicationContext`获取上下文。
- `xml`：通过`ClassPathXmlApplicationContext`或`FileSystemXmlApplicationContext`获取上下文。

```java
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
```



优秀文章：https://www.cnblogs.com/duanxz/p/7493276.html



## 3、@ComponentScan、@ComponentScans

`@ComponentScan`：包扫描，扫描注解了`@Controller`、`@Service`、`@Repository`、`@Component`等的类;

```java
@ComponentScan(value = "com.zxh")
```

等价

```xml
<context:component-scan base-package="com.zxh" />
```





### （1）@ComponentScans与@ComponentScan

- @ComponentScan是spring 3.1版本注解。@ComponentScans是4.3版本
- @ComponentScans用于注解多个@ComponentScan

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ComponentScans {

	ComponentScan[] value();

}
```



### （2）@ComponentScan源码

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Repeatable(ComponentScans.class)
public @interface ComponentScan {

  //指定扫描包名
	@AliasFor("basePackages")
	String[] value() default {};

	//指定扫描包名
	@AliasFor("value")
	String[] basePackages() default {};

	//指定扫描类
	Class<?>[] basePackageClasses() default {};

	
	Class<? extends ScopeMetadataResolver> scopeResolver() default AnnotationScopeMetadataResolver.class;

	
	ScopedProxyMode scopedProxy() default ScopedProxyMode.DEFAULT;

	
	String resourcePattern() default ClassPathScanningCandidateComponentProvider.DEFAULT_RESOURCE_PATTERN;

	
  //使用自动扫描注解类：@Component、@Repository、@Service、@Controller
	boolean useDefaultFilters() default true;

	//指定哪些类型符合组件扫描条件。
	Filter[] includeFilters() default {};

	//指定哪些类型不适合组件扫描
	Filter[] excludeFilters() default {};

	//指定扫描的bean是否应注册为延迟初始化
	boolean lazyInit() default false;


	//筛选器
	@Retention(RetentionPolicy.RUNTIME)
	@Target({})
	@interface Filter {

		//filter的类型
		FilterType type() default FilterType.ANNOTATION;

		//值
		@AliasFor("classes")
		Class<?>[] value() default {};

		//类型的值
		@AliasFor("value")
		Class<?>[] classes() default {};

		//用于过滤器的模式（或模式），作为替代方案
		String[] pattern() default {};

	}

}
```

常用熟悉：

- `String[] value() default {};`：指定扫描包。

  ```java
  @ComponentScan(value = "com.zxh")
  ```

  

- `Filter[] includeFilters() default {}`：指定符合组件扫描。

  指定扫描Controller、Service注解的类。`type = FilterType.ANNOTATION`

```java
@ComponentScan(value = "com.zxh",excludeFilters = {
       @ComponentScan.Filter(type = FilterType.ANNOTATION,classes ={Controller.class,Service.class})
})
```



- `Filter[] excludeFilters() default {}`：排除组件扫描。

排除Controller注解，**主要：使用includeFilters需要设置useDefaultFilters = false才能生效**

```java
@ComponentScan(value = "com.zxh",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
},useDefaultFilters = false)

```



### （3）FilterType

FilterType指定Filter的规则，可选值如下：

```java
public enum FilterType {

	//注解类型
	ANNOTATION,

	//指定类型
	ASSIGNABLE_TYPE,

	//AspectJ表达式
	ASPECTJ,

	//正则表达式
	REGEX,

	//自定义类型
	CUSTOM

}

```

常用方式：

- `FilterType.ANNOTATION`：上面已有例子
- `FilterType.ASSIGNABLE_TYPE`：

指定扫描BookService

```java
@ComponentScan(value = "com.zxh",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {BookService.class})
},useDefaultFilters = false)
```

- `FilterType.CUSTOM`：自定义过滤

  

#### 自定义过滤类型

  1、自定义过滤器实现`TypeFilter`接口。

  ```java
  /**
   * 自定义的TypeFilter
   */
  public class MyTypeFilter implements TypeFilter
  {
      public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
          //获取当前类注解信息
          AnnotatedTypeMetadata annotatedTypeMetadata = metadataReader.getAnnotationMetadata();
  
  
          //获取当前正在扫描的类信息
          ClassMetadata classMetadata = metadataReader.getClassMetadata();
  
          //获取当前类资源（类路径）
          Resource resource =  metadataReader.getResource();
  
          if(classMetadata.getClassName().contains("BookService")){
              return true;
          }
  
          return false;
      }
  }
  
  ```

  2、Filter的type设置为`FilterType.CUSTOM`，class为自定义的类

  ```java
  @ComponentScan(value = "com.zxh",includeFilters = {
          @ComponentScan.Filter(type=FilterType.CUSTOM,classes = {
                  MyTypeFilter.class
          })
  },useDefaultFilters = false)
  ```

  

## 4、@Scope

`@Scope`用于设置bean的作用域。**spring 2.5的注解**

@Scope可设置的bean作用域：

- `prototype`：多实例的：IOC容器启动并不会去调用方法创建对象放在容器中，每次获取的时候才会调用方法创建对象；
- `singleton`：单实例的（默认值），IOC容器启动时会调用方法创建对象并放到IOC容器中，以后每次获取直接在容器中获取。
- `request`:同一个请求创建一个实例
- `session`:同一个session创建一个实例



demo:

```java
@Scope("prototype")
@Bean
public Person person(){
  System.out.println("Person 初始化....");
  return new Person("zxh",25);
}
```

```java
 @Test
    public void scopeTest(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person1 = applicationContext.getBean("person",Person.class);
        Person person2 = applicationContext.getBean("person",Person.class);
        System.out.println(person1==person2);
    }
```

答应结果：

```text
Person 初始化....
Person 初始化....
false
```



## 5、@Lazy

`@Lazy`是spring3.0的注解，容器启动是不创建对象，在第一次获取Bean时才创建对象并初始化。



## 6、@Condition

`@Condition`是spring4.0的注解；bean注册条件，在注册bean之前通过程序控制转态。

demo：

满足自定义条件WindowCondition条件时创建bean:person1，满足自定义条件LinuxCondition条件时创建bean:person2

```java
@Configuration
@ComponentScan("com.zxh")
public class MainConfig2 {

    @Conditional({WindowCondition.class})
    @Bean("bill")
    public Person person1(){
        return new Person("bill",62);
    }

    @Conditional({LinuxCondition.class})
    @Bean("linus")
    public Person person2(){
        return new Person("linus",50);
    }
}
```



自定义条件类必须实现`Condition`

匹配当前运行环境是window

```java
**
 * 必须实现Comdition接口
 */
public class WindowCondition implements Condition {

    /**
     * 匹配方法，返回true则匹配成功
     * @param context 判断条件能使用的上下文（环境）
     * @param metadata 注释信息
     * @return
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取ioc使用的beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //获取当前环境信息
        Environment environment = context.getEnvironment();
        //获取到bean定义的注册类
        BeanDefinitionRegistry registry = context.getRegistry();

        //获取当前操作系统
        String osName = environment.getProperty("os.name");
        if(osName.equalsIgnoreCase("window")){
            return true;
        }
        return false;
    }
}
```

匹配当前运行环境是Linux

```java
public class LinuxCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String osName = environment.getProperty("os.name");
        if(osName.equalsIgnoreCase("linux")){
            return true;
        }
        return false;
    }
}
```



运行

```java
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
```





## 7、@Import

`@Import`是spring 3.0的注解，用于导入组件bean。



源码：

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Import {

	/**
	 * {@link Configuration}, {@link ImportSelector}, {@link ImportBeanDefinitionRegistrar}
	 * or regular component classes to import.
	 */
	Class<?>[] value();

}
```





给容器中注册组件的有以下方式

- 包扫描+组件标注注解（@Controller、@Service、@Repository、@Component）
- @Bean[导入的第三方包里面的组件]
- @Import[导入组件的类]
  - @Import(要导入容器中的组件)，容器中就会自动注册这个组件，id默认是全类名。
  - ImportSelector：返回需要导入的组件的全类名数组。
  - ImportBeanDefinitionRegistrar：手动注册bean到容器中
- 使用Spring提供的FactoryBean（工厂Bean）创建bean
  - 默认获取到的是工厂bean调用getObject创建的对象
  - 要获取工厂Bean本身，需要给id前面加&符合



### （1）使用@Import快速导入组件

定义bean实体类

```java
public class Green {
}

public class Red {
}
```

使用@Import导入Green、Red组件

```java
@Configuration
@Import({Green.class,Red.class}) //导入组件，id默认是组件的全类名
public class MainConfig2 {
}

```

测试：

```java
@Test
    public void importTest(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName:beanNames){
            System.out.println(beanName);
        }
    }
```

输出结果：

```text
.....
com.zxh.bean.Green
com.zxh.bean.Red
```



### （2）使用ImportSelector选择器导入组件

自定义ImportSelector选择器，必须实现ImportSelector#selectImports

```java
/**
 * 自定义逻辑返回需要导入的组件
 */
public class MyImportSelector implements ImportSelector
{
    /**
     * 返回需要导入组件的数组
     * @param importingClassMetadata 当前标注@Import注解类的所有注解信息
     * @return
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.zxh.bean.Green","com.zxh.bean.Red"};
    }
}
```

配置类中使用@Import导入自定义选择器

```java
@Configuration
@Import(MyImportSelector.class)
public class MainConfig2 {

}
```

测试：

```java
@Test
    public void importTest(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName:beanNames){
            System.out.println(beanName);
        }
    }
```

输出结果：

```text
.....
com.zxh.bean.Green
com.zxh.bean.Red
```



### （3）使用ImportBeanDefinitionRegistrar手动注册bean到容器中

自定义ImportBeanDefinitionRegistrar

```java
/**
 * 自定义手动注册的bean类
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * 手动注册bean到容器中
     * @param importingClassMetadata 当前类的注解信息
     * @param registry BeanDefinition注册类，把需要添加到容器中的bean，
     *                 调用BeanDefinitionRegistry#registerBeanDefinition手动注册
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //注册red和green
        registry.registerBeanDefinition("red",new RootBeanDefinition(Red.class));
        registry.registerBeanDefinition("green",new RootBeanDefinition(Green.class));

    }
}
```

配置类中使用@Import导入自定义bean注册类

```java
@Configuration
@Import(MyImportBeanDefinitionRegistrar.class)
public class MainConfig2 {

}

```

测试类和上面一样。



### （4）使用Spring提供的FactoryBean（工厂Bean）创建bean

自定义FactoryBean，注册bean

```java
/**
 * 创建自定义FactoryBean
 */
public class ColorFactoryBean implements FactoryBean<Color> {
    /**
     * 返回一个Color对象，这个对象会添加到容器中
     * @return
     * @throws Exception
     */
    public Color getObject() throws Exception {
        return new Color();
    }

    /**
     * 返回bean的类型
     * @return
     */
    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * 是否单例模式
     * @return
     */
    public boolean isSingleton() {
        return false;
    }
}

```

配置自定义factorybean

```java
@Configuration
public class MainConfig2 {
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}

```

测试：

```java
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
```



# 二、Bean生命周期

## 指定初始化和销毁方法：

- 通过@Bean指定`initMethod`初始化和`destroyMethod`销毁。和xml中`init-method`、`destroy-method`对应的。

```java
@Bean(initMethod = "init",destroyMethod = "destory")
public Car car(){
  return new Car();

}
```

相当于

```xml
 <bean id="car" class="com.zxh.bean.Car" init-method="init" destroy-method="destory"/>
```



## 通过bean实现InitializingBean（初始化）和DisposableBean（销毁）

```java
@Component
public class Car1 implements InitializingBean,DisposableBean {
    public Car1(){
        System.out.println("car1 constructor....");
    }

    /**
     * 初始化bean
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        System.out.println("car1 init....");
    }

    /**
     * 销毁bean
     * @throws Exception
     */
    public void destroy() throws Exception {
        System.out.println("car1 destory...");
    }


}
```

配置类扫描bean
```java
@Configuration
@ComponentScan("com.zxh.bean")
public class MainConfig3 {

}
```

