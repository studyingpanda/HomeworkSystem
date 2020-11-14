<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>jobCheckEdit.jsp</title>
<link href="css/content.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<form action="/HomeworkSystem/JobServlet?method=jobCheckEdit"
		method="post">
		<div id="content_top">作业内容批阅</div>
		<div id="content_mid">
			<table class="table_theme1">
				<tr>
					<td>作业内容编号:</td>
					<td><input type="text" name="jobId" value="${job.jobId}"
						readonly /></td>
				</tr>
				<tr>
					<td>学生:</td>
					<td><input type="text" name="jobUserName"
						value="${job.jobUserName}" readonly /> <input type="hidden"
						name="jobTitle" value="${job.jobTitle}" /> <input type="hidden"
						name="jobUser" value="${job.jobUser}" /></td>
				</tr>
				<tr>
					<td>作业内容:</td>
					<td><textarea name="jobContent" value="${job.jobContent}"
							rows="3" cols="30" readonly></textarea></td>
				</tr>
				<tr>
					<td>评分:</td>
					<td><input type="text" name="jobScore" value="${job.jobScore}" /></td>
				</tr>
			</table>
		</div>
		<div id="content_bottom">
			<input type="submit" value="保存"></input>
		</div>
	</form>
</body>
</html>
