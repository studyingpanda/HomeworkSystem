<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>courseManage.jsp</title>
<link href="css/content.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="content_top">课程管理</div>
	<div id="content_mid">
		<table class="table_theme1">
			<thead>
				<tr
					<tr>
					<th>课程编号</th>
					<th>课程名称</th>
					<th>授课教师</th>
					<th>操作</th> 
				</tr>
			</thead>
			<c:forEach items="${courses}" var="item">
				<tr>
					<td>${item.courseId}</td>
					<td>${item.courseName}</td>
					<td>${item.courseUserName}</td>
					<td><a
						href="/HomeworkSystem/RouteServlet?childPage=courseEdit.jsp&courseId=${item.courseId}">编辑</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="content_bottom">
		<a href="/HomeworkSystem/RouteServlet?childPage=courseAdd.jsp">新增</a>
	</div>
</body>
</html>
