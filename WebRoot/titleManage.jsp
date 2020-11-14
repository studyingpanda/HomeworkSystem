<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>titleManage.jsp</title>
<link href="css/content.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="content_top">作业题目管理</div>
	<div id="content_mid">
		<table class="table_theme1">
			<thead>
				<tr
					<tr>
					<th>题目编号</th>
					<th>题目名称</th>
					<th>所属课程</th>
					<th>操作</th> 
				</tr>
			</thead>
			<c:forEach items="${titles}" var="item">
				<tr>
					<td>${item.titleId}</td>
					<td>${item.titleContent}</td>
					<td>${item.titleCourseName}</td>
					<td><a
						href="/HomeworkSystem/RouteServlet?childPage=titleEdit.jsp&titleId=${item.titleId}">编辑</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="content_bottom">
		<a href="/HomeworkSystem/RouteServlet?childPage=titleAdd.jsp">新增</a>
	</div>
</body>
</html>
