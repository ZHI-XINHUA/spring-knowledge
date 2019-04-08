package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UserListener implements ServletContextListener {
    /**
     * 销毁监听器
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("执行UserListener>>>>>contextDestroyed()");
    }

    /**
     * 初始化监听器
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("执行UserListener>>>>>contextInitialized()");
    }
}
