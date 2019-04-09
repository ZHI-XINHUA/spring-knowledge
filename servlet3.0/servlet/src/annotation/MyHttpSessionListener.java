package annotation;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听session的创建和销毁
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    /**
     * 项目启动时，创建session 调用
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("MyHttpSessionListener!sessionCreated>>>session创建:"+httpSessionEvent.getSession().getId());
    }

    /**
     * session销毁时调用
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("MyHttpSessionListener!sessionDestroyed>>>session销毁:"+httpSessionEvent.getSession().getId());
    }
}
