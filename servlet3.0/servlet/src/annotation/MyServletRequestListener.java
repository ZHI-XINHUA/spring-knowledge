package annotation;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import java.util.Enumeration;

/**
 * 对request对象进行监听
 */
@WebListener
public class MyServletRequestListener implements ServletRequestListener {
    /**
     * request对象销毁时调用
     * @param srv
     */
    @Override
    public void requestDestroyed(ServletRequestEvent srv) {
        System.out.println("MyServletRequestListener!requestDestroyed()>>>request对象销毁:"+Thread.currentThread().getName());
    }

    /**
     * request对象创建时调用
     * @param srv
     */
    @Override
    public void requestInitialized(ServletRequestEvent srv) {
        System.out.println("MyServletRequestListener!requestInitialized()>>>request对象创建:"+Thread.currentThread().getName());
        //获取ServletContext
        ServletContext servletContext = srv.getServletContext();

        //获取ServletRequest
        ServletRequest servletRequest = srv.getServletRequest();


        System.out.println( "remoteAddr="+servletRequest.getServerName()+" remoteHost="+servletRequest.getServerPort());
        Enumeration<String>  paramters = servletRequest.getParameterNames();

        System.out.println("请求参数：");
        while(paramters.hasMoreElements()){
            String param = paramters.nextElement();
            String value = servletRequest.getParameter(param);
            System.out.print("["+param+"="+value+"] ");
        }



    }
}
