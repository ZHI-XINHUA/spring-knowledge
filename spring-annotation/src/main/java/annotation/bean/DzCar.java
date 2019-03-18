package annotation.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 使用JSR250；
 *  @PostConstruct：在bean创建完成并且属性赋值完成；来执行初始化方法
 *  @PreDestroy：在容器销毁bean之前通知我们进行清理工作
 */
@Component
public class DzCar {
    @PostConstruct
    public void init(){
        System.out.println("DzCar init...");
    }

    @PreDestroy
    public void destory(){
        System.out.println("DzCar destory.....");
    }
}
