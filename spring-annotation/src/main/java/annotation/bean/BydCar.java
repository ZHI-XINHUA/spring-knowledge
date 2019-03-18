package annotation.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 实现InitializingBean和DisposabelBean，重写bean的初始化和销毁方法
 */
@Component
public class BydCar implements InitializingBean,DisposableBean
{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * bean销毁时调用
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("Byd destory");
    }

    /**
     * bean初始化时调用
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Byd init");
    }
}
