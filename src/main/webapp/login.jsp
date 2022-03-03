<%--
登录页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login.do" method="post">
    <div class="info">${error}</div>
    账户:<input type="text" name="userCode"/><br>
    密码:<input type="text" name="userPassword"/><br>
    <button type="submit">提交</button>
    <button><a href="register.jsp">注册</a></button>
</form>
</body>
</html>