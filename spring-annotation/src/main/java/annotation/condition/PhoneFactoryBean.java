package annotation.condition;

import annotation.bean.Phone;
import org.springframework.beans.factory.FactoryBean;

/**
 * 创建一个自定义的FactoryBean
 */
public class PhoneFactoryBean implements FactoryBean<Phone> {
    /**
     * 返回的对象会放到bean容器中
     * @return
     * @throws Exception
     */
    @Override
    public Phone getObject() throws Exception {
        return new Phone("苹果",5000,"4.8英寸");
    }

    @Override
    public Class<?> getObjectType() {
        return Phone.class;
    }

    /**
     * 是否单例
     * @return
     */
    @Override
    public boolean isSingleton() {
        return false;
    }
}
