package spi;

import filter.UserFilter;
import listener.UserListener;
import service.HelloService;
import servlet.UserServlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

//容器启动时会将@HandlesTypes指定类型下的子类（实现类、子接口等）传递过来
@HandlesTypes(value = {HelloService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer
{
    /**
     * 应用启动时运行onStartup方法
     * @param set 指定类型的所有子类型集合
     * @param servletContext web容器
     * @throws ServletException
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("ServletContainerInitializer onstarup >>>>>>>> start ");
        for (Class c: set
             ) {
            System.out.println(c.toString());
        }

        System.out.println("ServletContainerInitializer onstarup >>>>>>>> end ");

        System.out.println("\n使用ServletContext注册Web组件(Servlet、Filter、Listener)");

        //添加servlet
        ServletRegistration.Dynamic userServlet = servletContext.addServlet("userServlet",UserServlet.class);
        //servlet映射
        userServlet.addMapping("/user");


        //注册listener
        servletContext.addListener(UserListener.class);

        //注册Filter
        FilterRegistration.Dynamic userFilter = servletContext.addFilter("userFilter",UserFilter.class);
        //配置filter映射(所有请求)
        userFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/");
    }
}
