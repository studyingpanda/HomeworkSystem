<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>courseEdit.jsp</title>
<link href="css/content.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<form action="/HomeworkSystem/CourseServlet?method=courseEdit"
		method="post">
		<div id="content_top">课程编辑</div>
		<div id="content_mid">
			<table class="table_theme1">
				<tr>
					<td>编号:</td>
					<td><input type="text" name="courseId"
						value="${course.courseId}" readonly /></td>
				</tr>
				<tr>
					<td>名称:</td>
					<td><input type="text" name="courseName"
						value="${course.courseName}" /></td>
				</tr>
			</table>
		</div>
		<div id="content_bottom">
			<input type="submit" value="保存"></input>
		</div>
	</form>
</body>
</html>
