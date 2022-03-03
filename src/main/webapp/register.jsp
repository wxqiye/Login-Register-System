<%--
注册页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/register.do" method="post">
    <div class="info">${tips}</div>
    账户:<input type="text" name="userCode"/><br>
    密码:<input type="text" name="userPassword"/><br>
    邮箱：<input type="text" name="email"><br/>
    <button type="submit">注册</button>
</form>
</body>
</html>
