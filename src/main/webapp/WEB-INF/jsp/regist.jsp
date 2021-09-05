<%--
  Created by IntelliJ IDEA.
  User: PengHusile
  Date: 2019/12/6
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>注册</title>
</head>
<body background="../../image/background.png">
<div id="container">
    <div id="output">
        <div class="containerT">
            <h1>教务系统</h1>
            <form class="form" id="regist_form" method="post" action="">
                <input type="text" placeholder="用户名" name="user_name" id="user_name">
                <input type="password" placeholder="密码" name="user_password" id="user_password">
                <button onclick="regist()">注册</button>
                <!-- 登录失败提示的信息 -->
                <c:if test="${registError!=null }">
                    <script type="text/javascript"> alert("注册失败");
                    window.location = "/toRegist" </script>
                </c:if>
            </form>
        </div>
    </div>
</div>

</body>
</html>

<script type="text/javascript">
    function regist() {
        document.getElementById("regist_form").action = "regist";
        document.getElementById("regist_form").submit();
    }
</script>