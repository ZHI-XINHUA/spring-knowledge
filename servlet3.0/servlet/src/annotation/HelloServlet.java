package annotation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @WebServlet
 */
//@WebServlet("/hello")
@WebServlet(name = "myservlet",urlPatterns = {"/hello","/hello1"},
        initParams = {
                @WebInitParam(name = "name",value = "james"),
                @WebInitParam(name = "age",value = "20")
        }

)
//@MultipartConfig
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        String name = config.getInitParameter("name");
        String age = config.getInitParameter("age");
        System.out.println("name="+name+" age="+age);

        req.setAttribute("zxh","zxh");
        req.setAttribute("zxh","zxh1");
        req.removeAttribute("zxh");

        HttpSession session = req.getSession();
        session.setAttribute("loginUser","zxh");

        session.removeAttribute("loginUser");


        //销毁session
        session.invalidate();

        resp.getWriter().write("hello servlet");
    }
}
