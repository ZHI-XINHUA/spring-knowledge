package com.zxh.config;

import com.zxh.bean.ColorFactoryBean;
import com.zxh.bean.Green;
import com.zxh.bean.Person;
import com.zxh.bean.Red;
import com.zxh.condition.LinuxCondition;
import com.zxh.condition.MyImportBeanDefinitionRegistrar;
import com.zxh.condition.MyImportSelector;
import com.zxh.condition.WindowCondition;
import org.springframework.context.annotation.*;

@Configuration
//ComponentScan("com.zxh")
//@Import({Green.class,Red.class}) //导入组件，id默认是组件的全类名
//@Import(MyImportSelector.class)
@Import(MyImportBeanDefinitionRegistrar.class)
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


    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
