package annotation;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 监听session属性变化
 */
@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("MyHttpSessionAttributeListener!attributeAdded>> 添加session属性值："+httpSessionBindingEvent.getName());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("MyHttpSessionAttributeListener!attributeRemoved>> 删除session属性值："+httpSessionBindingEvent.getName());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("MyHttpSessionAttributeListener!attributeReplaced>> 修改session属性值："+httpSessionBindingEvent.getName());
    }
}
