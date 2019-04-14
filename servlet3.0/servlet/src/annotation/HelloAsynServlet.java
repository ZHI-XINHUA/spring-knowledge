package annotation;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet(value = "/async",asyncSupported = true)//1、支持异步处理asyncSupported=true
public class HelloAsynServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("主线程开始：[Thread-"+Thread.currentThread().getId()+"] 时间："+System.currentTimeMillis());

        //2、开启异步模式 主要是req.startAsync();
        //AsyncContext asyncContext = req.getAsyncContext();
        AsyncContext asyncContext =req.startAsync();

        //3、业务逻辑进行异步处理;开始异步处理
        asyncContext.start(() ->{
            System.out.println("副线程开始：[Thread-"+Thread.currentThread().getId()+"] 时间："+System.currentTimeMillis());
            try {
                sayHello();

                //4、完成请求处理
                asyncContext.complete();

                resp.getWriter().write(" hello HelloAsynServlet ");

            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println("副线程结束：[Thread-"+Thread.currentThread().getId()+"] 时间："+System.currentTimeMillis());
        });
        System.out.println("主线程结束：[Thread-"+Thread.currentThread().getId()+"] 时间："+System.currentTimeMillis());

    }


    private void sayHello() throws InterruptedException {
        System.out.println(Thread.currentThread()+" processing...");
        TimeUnit.SECONDS.sleep(3);
    }
}
