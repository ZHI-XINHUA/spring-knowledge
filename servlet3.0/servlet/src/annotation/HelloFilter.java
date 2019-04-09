package annotation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @WebFilter filter过滤器
 */
@WebFilter(filterName = "helloFilter",value = "/hello")
public class HelloFilter implements Filter {
    /**
     * 初始化时执行
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("HelloFilter>>>init");
    }

    /**
     * 过滤器链
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("HelloFilter>>>doFilter");
        //传递下一个filter
        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * 销毁时执行
     */
    @Override
    public void destroy() {
        System.out.println("HelloFilter>>>destroy");
    }
}
