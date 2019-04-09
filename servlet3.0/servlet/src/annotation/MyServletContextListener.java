package annotation;

import servlet.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

/**
 * 监听servlet容器启动和销毁
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    /**
     * 当Servlet 容器启动Web 应用时调用该方法。在调用完该方法之后，容器再对Filter 初始化，
     *  并且对那些在Web 应用启动时就需要被初始化的Servlet 进行初始化。
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获取ServletContext对象
        ServletContext servletContext = servletContextEvent.getServletContext();

        servletContext.setAttribute("zxg","zxg");

        //添加servlet
        ServletRegistration.Dynamic userServlet = servletContext.addServlet("userServlet1", UserServlet.class);
        //servlet映射
        userServlet.addMapping("/user");

        //可以添加filter 和listener...

        System.out.println("MyServletContextListener>>>contextInitialized()");

    }

    /**
     * 当Servlet 容器终止Web 应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet 和Filter 过滤器。
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("MyServletContextListener>>>contextDestroyed()");
    }
}
