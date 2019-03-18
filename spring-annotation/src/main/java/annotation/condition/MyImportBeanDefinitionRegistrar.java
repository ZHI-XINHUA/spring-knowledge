package annotation.condition;

import annotation.bean.BmwCar;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 指定组件
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param annotationMetadata 当前类的注解信息
     * @param registry BeanDefinition注册类；把所有需要添加到容器中的bean；调用BeanDefinitionRegistry.registerBeanDefinition手工注册进来
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        //判断容器中是否包含annotation.bean.Color bean
        boolean colorBean = registry.containsBeanDefinition("annotation.bean.Color");
        //如果包含则创建BmwCar bean
        if(colorBean){
            //指定Bean定义信息
            RootBeanDefinition beanDefinition = new RootBeanDefinition(BmwCar.class);
            //注册一个Bean，指定bean名
            registry.registerBeanDefinition("bmwCar",beanDefinition);
        }
    }
}
