package annotation.configure;

import annotation.bean.BenCar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean的生命周期
 *
 * 1）指定初始化和销毁方法：通过@Bean指定initMethod和destoryMethod方法
 *
 * 2）通过让Bean实现InitializingBean(定义初始化)和DisposableBean（定义销毁）
 *
 * 3) 可以使用JSR250:
 * @PostConstruct：在bean创建完成并且属性赋值完成；来执行初始化方法
 * @PreDestory：在容器销毁bean之前通知我们进行清理工作
 *
 * 4)BeanPostProcessor接口：bean的后置处理器：
 *  在bean初始化前后进行一些处理工作：
 *     postProcessBeforeInitialization：在初始化之前工作
 *     postProcessAfterInitialization:在初始之后工作
 */
@ComponentScan("annotation.bean")
@Configuration
public class MainConfigOfLifeCycle {

    //xml配置文件中factory-bean与factory-method(spring使用工厂方法注入bean)：https://www.cnblogs.com/vickylinj/p/9474597.html
    @Bean(initMethod = "init",destroyMethod = "destory")
    public BenCar benCar(){
        return new BenCar();
    }
}
