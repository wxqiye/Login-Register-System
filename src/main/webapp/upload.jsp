<%--
头像上传页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/upload.do" method="post" enctype="multipart/form-data">
    <p>上传头像：<input type="file" name="file1"></p>
    <p><input type="submit" value="点击上传"> | <input type="reset" value="重置"></p>


</form>
</body>
</html>

