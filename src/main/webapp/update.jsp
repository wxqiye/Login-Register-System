<%--
修改个人信息页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/update.do" method="post">
    <div class="info">${tips}</div>
    用户名:<input type="text" name="userName"/><br>
    生日:<input type="text" name="birthday"/><br>
    电话:<input type="text" name="phone"/><br>
    <button type="submit">修改</button>

</form>
</body>
</html>
