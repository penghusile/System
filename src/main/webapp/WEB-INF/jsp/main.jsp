<%--
  Created by IntelliJ IDEA.
  User: PengHusile
  Date: 2019/12/6
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.Date" %>
<%@ page import="com.web2019.team4.system.Dao.Entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<link rel="stylesheet" type="text/css" href="<%=path%>/static/layui/css/layui.css"  media="all">
<link rel="stylesheet" type="text/css" href="<%=path%>/static/layui/theme/default/layer.css">
<script src="<%=path%>/static/jquery/jquery-3.3.1.min.js"></script>
<script src="<%=path%>/static/layui/layui.js"></script>
<script src="<%=path%>/static/layui/layer.js"></script>
<html>
<head>
    <title>主界面</title>
</head>
<%--<% User user = (User) session.getAttribute("user"); %>--%>
<%--<% String site = (String) request.getAttribute("site"); %>--%>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">教务系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
<%--                <a><%=user.getId() %></a>--%>
            </li>
            <li class="layui-nav-item" >
                <a href="/quit" method="post">退出</a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">管理员业务</a>
                    <dl class="layui-nav-child">
                        <dd id="toAddCourse">
                            <a a href="javascript:;">添加课程</a>
                        </dd>
                        <dd id="toDelCourse">
                            <a href="javascript:;">删除课程</a>
                        </dd>
                        <dd id="toUpdateCourse">
                            <a href="javascript:;">更新课程</a>
                        </dd>
                        <dd id="toSearchCourse">
                            <a href="javascript:;">查询课程</a>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed" >
                    <a class="" href="javascript:;">学生业务</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/toSelectCourse">查询课程</a></dd>
                        <dd><a href="/toChooseCourse">选择课程</a></dd>
                        <dd><a href="/toQuitCourse">退选课程</a></dd>
                        <dd><a href="/toAlterInfo">修改个人信息</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <%--        <div style="padding: 15px;">内容主体区域</div>--%>
        <iframe
                src="/toAddCourse"
                frameborder="0"
                scrolling="no"
                width="100%"
                height="100%"
                noresize="noresize"
                id="contentIframe">

        </iframe>
    </div>
    <div class="layui-footer" align="center">
        <!-- 底部固定区域 -->
        © WEB第四组 - 教务系统
    </div>
</div>
<script src="../src/layui.js"></script>
<script type="text/javascript">
    $(function(){
        $('#toSearchCourse').on('click',function(){
            $(window.parent.document).find('#contentIframe').attr('src','/toSearchCourse')
            $(this).addClass('layui-this').siblings().removeClass('layui-this');
        })
        $('#toAddCourse').on('click',function(){
            $(window.parent.document).find('#contentIframe').attr('src','/toAddCourse')
            $(this).addClass('layui-this').siblings().removeClass('layui-this');
        })
        $('#toDelCourse').on('click',function(){
            $(window.parent.document).find('#contentIframe').attr('src','/toDelCourse')
            $(this).addClass('layui-this').siblings().removeClass('layui-this');
        })
        $('#toUpdateCourse').on('click',function(){
            $(window.parent.document).find('#contentIframe').attr('src','/toUpdateCourse')
            $(this).addClass('layui-this').siblings().removeClass('layui-this');
        })
    })
</script>

</body>
</html>
