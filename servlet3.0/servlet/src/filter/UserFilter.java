package filter;

import javax.servlet.*;
import java.io.IOException;

public class UserFilter implements Filter
{

    /**
     * 初始化过滤器
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("执行UserFilter>>>>init()");
    }

    /**
     * 过滤器执行链
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行UserFilter>>>>doFilter()");

        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * 过滤器销毁执行的方法
     */
    @Override
    public void destroy() {
        System.out.println("执行UserFilter>>>>destroy()");
    }
}
