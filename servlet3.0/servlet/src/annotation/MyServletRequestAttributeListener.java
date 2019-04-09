package annotation;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * request对象属性监听
 */
@WebListener
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {
    /**
     *  属性添加时调用
     * @param srae
     */
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("MyServletRequestAttributeListener!attributeAdded>>>添加属性："+srae.getName());
    }

    /**
     * 属性删除时调用
     * @param srae
     */
    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("MyServletRequestAttributeListener!attributeRemoved>>>删除属性："+srae.getName());
    }

    /**
     * 属性修改时调用
     * @param srae
     */
    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("MyServletRequestAttributeListener!attributeReplaced>>>修改属性："+srae.getName());
    }
}
