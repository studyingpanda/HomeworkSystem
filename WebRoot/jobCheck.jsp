<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>jobCheck.jsp</title>
<link href="css/content.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="content_top">作业批阅</div>
	<div id="content_mid">
		<form method="get" action="/HomeworkSystem/RouteServlet">
			<input type="hidden" name="childPage" value="jobCheck.jsp"> <select
				name="selectedTitle">
				<c:forEach items="${titles}" var="item">
					<option value="${item.titleId}">${item.titleContent}</option>
				</c:forEach>
			</select> <input type="submit" value="查询" />
		</form>
		<table class="table_theme1">
			<thead>
				<tr>
					<th>编号</th>
					<th>姓名</th>
					<th>时间</th>
					<th>内容</th>
					<th>得分</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach items="${jobs}" var="item">
				<tr>
					<td>${item.jobId}</td>
					<td>${item.jobUserName}</td>
					<td>${item.jobTime}</td>
					<td>${item.jobContent}</td>
					<td>${item.jobScore}</td>
					<td><a
						href="/HomeworkSystem/RouteServlet?childPage=jobCheckEdit.jsp&jobId=${item.jobId}">批阅</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
