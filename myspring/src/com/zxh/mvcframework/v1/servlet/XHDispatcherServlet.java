package com.zxh.mvcframework.v1.servlet;

import com.zxh.mvcframework.annotation.XHAutowired;
import com.zxh.mvcframework.annotation.XHController;
import com.zxh.mvcframework.annotation.XHRequestMapping;
import com.zxh.mvcframework.annotation.XHService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class XHDispatcherServlet extends HttpServlet {

    private Map<String,Object> mapping = new HashMap<>();
    private Map<String,Object> scanClassMapping = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req,resp);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req,resp);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        url = url.replace(contextPath,"").replaceAll("/+","/");
        if(!mapping.containsKey(url)){
            try {
                response.getWriter().write("404 Not Found!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Method method = (Method)mapping.get(url);
        Map<String,String[]> params = request.getParameterMap();
        method.invoke(mapping.get(method.getDeclaringClass().getName()),new Object[]{request,response,params.get("name")[0]});
    }

    /**
     * 初始化
     * @param config
     * @throws ServletException
     */
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init......");
        InputStream is = null;

        Properties configContext = new Properties();

        System.out.println( config.getInitParameter("contextConfigLocation"));
        is = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            configContext.load(is);
            String scanPackage = configContext.getProperty("scanPackage");
            //扫描包下的所有class，并添加到map中
            doScanner(scanPackage);
            //遍历已扫描的类集合
            for (String className : scanClassMapping.keySet()){
                if(!className.contains(".")) continue;
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(XHController.class)){
                    mapping.put(className,clazz.newInstance());

                    //获取类中@XHRequestMapping的值
                    String baseUrl = "";
                    if(clazz.isAnnotationPresent(XHRequestMapping.class)){
                        XHRequestMapping requestMapping = clazz.getAnnotation(XHRequestMapping.class);
                        baseUrl = requestMapping.value();
                    }

                    //获取方法中@XHRequestMapping的值
                    Method[] methods = clazz.getMethods();
                    for (Method method : methods){
                        if(!method.isAnnotationPresent(XHRequestMapping.class)) continue;
                        XHRequestMapping requestMapping = method.getAnnotation(XHRequestMapping.class);
                        String url = (baseUrl+"/"+requestMapping.value()).replaceAll("/+","/");
                        mapping.put(url,method);
                        System.out.println("加载mapping："+url+"，"+method);
                    }
                }else if(clazz.isAnnotationPresent(XHService.class)){
                    XHService service = clazz.getAnnotation(XHService.class);
                    String baseName = service.value();
                    //如果没有值，则默认是类名称
                    if("".equals(baseName)){
                        baseName = clazz.getName();
                    }
                    Object instance = clazz.newInstance();
                    mapping.put(baseName,instance);
                    //如果有接口，则接口名称也作为key
                    for(Class<?> i : clazz.getInterfaces()){
                        mapping.put(i.getName(),instance);
                    }
                }
            }

            for (Object object:mapping.values()){
                if(object==null) continue;
                Class clazz = object.getClass();
                if(clazz.isAnnotationPresent(XHController.class)){
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field:fields){
                        if(!field.isAnnotationPresent(XHAutowired.class)) continue;
                        XHAutowired autowired = field.getAnnotation(XHAutowired.class);
                        String beanName = autowired.value();
                        if("".equals(beanName)){
                            beanName = field.getType().getName();
                        }
                        field.setAccessible(true);
                        field.set(mapping.get(clazz.getName()),mapping.get(beanName));

                    }
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 扫描scanPackage包下的所有类，并加载到map中
     * @param scanPackage
     */
    private void doScanner(String scanPackage) {
        System.out.println(("/"+scanPackage.replaceAll("\\.","/")));
        URL url = this.getClass().getClassLoader().getResource(("/"+scanPackage.replaceAll("\\.","/")));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()){
            String fileName = file.getName();
            //如果是文件，则递归查询
            if(file.isDirectory()){
                doScanner(scanPackage+"."+fileName);
            }else {
                if(!fileName.endsWith(".class")) continue;
                String className = scanPackage+"."+fileName.replaceAll(".class","");
                scanClassMapping.put(className,null);
            }

        }
    }
}
