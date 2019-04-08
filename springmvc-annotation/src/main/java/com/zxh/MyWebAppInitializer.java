package com.zxh;

import com.zxh.config.AppConfig;
import com.zxh.config.RootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * web容器启动时候创建对象；调用方法初始化容器前端控制器
 */
public class MyWebAppInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 获取根容器配置类 spring的配置文件 父容器
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 获取web容器的配置类  子容器
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /**
     * 获取DipatcherServlet的映射信息
     * /:拦截所有请求（包括静态资源xx.js、xx.png）,但是不包括*.jsp
     * /*:拦截所有请求（连*.jsp页面都拦截；jsp页面是tomcat的jsp引擎解析的）
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
