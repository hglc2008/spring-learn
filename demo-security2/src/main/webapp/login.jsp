<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>
<body>
<h3>用户登录</h3>
<c:if test="${not empty param.error}">
    <font color="red">Login error.<br />
    </font>
    Reason:${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
</c:if>
<!-- from的action地址，以及用户名密码的name 。都是spring-security固定的。 -->
<form action="<%=basePath %>j_spring_security_check" method="post">

    <p>
        <label for="j_username">Username</label>
        <input id="j_username" name="j_username" type="text" />
    </p>

    <p>
        <label for="j_password">Password</label>
        <input id="j_password" name="j_password" type="password" />
    </p>

    <input type="submit" value="Login" />

</form>

</body>
</html>
