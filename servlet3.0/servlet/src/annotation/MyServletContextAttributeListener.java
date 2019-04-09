package annotation;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听ServletContext（application）范围内属性的变化
 */
@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    /**
     * 当程序把一个属性存入application范围时触发该方法
     * @param scev
     */
    @Override
    public void attributeAdded(ServletContextAttributeEvent scev) {
        print(scev,"application范围内的属性：【添加】>>>>");
    }

    /**
     * 当程序把一个属性从application范围删除时触发该方法
     * @param scev
     */
    @Override
    public void attributeRemoved(ServletContextAttributeEvent scev) {
        print(scev,"application范围内的属性：【删除】>>>>");
    }

    /**
     * 当程序替换application范围内的属性时将触发该方法。
     * @param scev
     */
    @Override
    public void attributeReplaced(ServletContextAttributeEvent scev) {
        print(scev,"application范围内的属性：【修改】>>>>");

        Object value_ = scev.getServletContext().getAttribute(scev.getName());
        System.out.println("修改后的值为："+value_);
    }

    private void print(ServletContextAttributeEvent servletContextAttributeEvent,String msg){
        String name = servletContextAttributeEvent.getName();
        Object value = servletContextAttributeEvent.getValue();
        System.out.println(msg+":[name="+name+",value="+value+"]");
    }
}
