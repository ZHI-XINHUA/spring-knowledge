package annotation;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;

/**
 *
 */
@WebListener
public class MyHttpSessionIdListener implements HttpSessionIdListener {
    /**
     * 接收会话ID已在会话中更改的通知。
     * @param httpSessionEvent 包含会话的HttpSessionBindingEvent以及已替换的属性的名称和（旧）值
     * @param s 旧的会话ID
     */
    @Override
    public void sessionIdChanged(HttpSessionEvent httpSessionEvent, String s) {
        System.out.println("===================MyHttpSessionIdListener!sessionIdChanged======"+httpSessionEvent.getSession().getId()+"   "+s);
    }
}
