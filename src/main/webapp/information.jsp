<%@ page import="com.dong.utl.Constants" %>
<%--
个人信息页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<img src=rpath width="30"/> <br/>
这是头像网址：<br/>
<%= request.getSession().getAttribute(Constants.USER_PATH)%>

<h1>个人信息</h1><br/>
用户名:<%=request.getSession().getAttribute(Constants.USER_NAME)%><br/>
生日:<%=request.getSession().getAttribute(Constants.USER_BIRTHDAY)%><br/>
电话:<%=request.getSession().getAttribute(Constants.USER_PHONE)%><br/>
<button><a href="update.jsp">修改</a></button>
        </body>
<button><a href="upload.jsp">上传头像</a></button>
</body>
</html>
