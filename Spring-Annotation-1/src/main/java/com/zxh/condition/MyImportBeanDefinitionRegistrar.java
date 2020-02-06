package com.zxh.condition;

import com.zxh.bean.Green;
import com.zxh.bean.Red;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

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
