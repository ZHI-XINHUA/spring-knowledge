package com.zxh.bean;

import org.springframework.beans.factory.FactoryBean;

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
