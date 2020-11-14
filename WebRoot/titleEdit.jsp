<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>titleEdit.jsp</title>
<link href="css/content.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<form action="/HomeworkSystem/TitleServlet?method=titleEdit"
		method="post">
		<div id="content_top">作业题目编辑</div>
		<div id="content_mid">
			<table class="table_theme1">
				<tr>
					<td>题目编号:</td>
					<td><input type="text" name="titleId" value="${title.titleId}"
						readonly /> <input type="hidden" name="titleCourse"
						value="${title.titleCourse}" /> <input type="hidden"
						name="titleTime" value="${title.titleTime}" /></td>
				</tr>
				<tr>
					<td>题目内容:</td>
					<td><input type="text" name="titleContent"
						value="${title.titleContent}" /></td>
				</tr>
			</table>
		</div>
		<div id="content_bottom">
			<input type="submit" value="保存"></input>
		</div>
	</form>
</body>
</html>
