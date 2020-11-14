<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>userManage.jsp</title>
<link href="css/content.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="content_top">人员管理</div>
	<div id="content_mid">
		<table class="table_theme1">
			<thead>
				<tr>
					<th>编号</th>
					<th>姓名</th>
					<th>角色</th>
					<th>操作</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${users}" var="item">
				<tr>
					<td>${item.userId}</td>
					<td>${item.userName}</td>
					<td>${item.userRole}</td>
					<td><a
						href="/HomeworkSystem/RouteServlet?childPage=userEdit.jsp&userId=${item.userId}">编辑</a></td>
					<td><a
						href="/HomeworkSystem/UserServlet?method=userDelete&userId=${item.userId}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="content_bottom">
		<a href="/HomeworkSystem/RouteServlet?childPage=userAdd.jsp">新增</a>
	</div>
</body>
</html>
