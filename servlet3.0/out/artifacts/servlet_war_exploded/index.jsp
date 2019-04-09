<%--
  Created by IntelliJ IDEA.
  User: zxh
  Date: 19/3/17
  Time: 下午9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  System.out.println("添加userName>>>>>>>>");
  application.setAttribute("userName", "张三");

  System.out.println("修改userName>>>>>>>>");
  application.setAttribute("userName","李四");

  System.out.println("删除userName>>>>>>>>");
  application.removeAttribute("userName");

  application.setAttribute("userName1","李四1");

  ServletContext servletContext = pageContext.getServletContext();
  servletContext.setAttribute("address","guangzhou");


%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
hello
  </body>
</html>
