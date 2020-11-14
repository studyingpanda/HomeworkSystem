<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>titleAdd.jsp</title>
<link href="css/content.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<form action="/HomeworkSystem/TitleServlet?method=titleAdd"
		method="post">
		<div id="content_top">作业题目新增</div>
		<div id="content_mid">
			<table class="table_theme1">
				<tr>
					<td>作业题目:</td>
					<td><input type="text" name="titleContent" /></td>
				</tr>
				<tr>
					<td>所属课程:</td>
					<td><select name="titleCourse">
							<c:forEach items="${courses}" var="item">
								<option value="${item.courseId}">${item.courseName}</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
		</div>
		<div id="content_bottom">
			<input type="submit" value="保存"></input>
		</div>
	</form>
</body>
</html>
