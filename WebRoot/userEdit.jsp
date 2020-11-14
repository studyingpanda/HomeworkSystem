<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>userEdit.jsp</title>
<link href="css/content.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<form action="/HomeworkSystem/UserServlet?method=userEdit"
		method="post">
		<div id="content_top">人员编辑</div>
		<div id="content_mid">
			<table class="table_theme1">
				<tr>
					<td>编号:</td>
					<td><input type="text" name="userId" value="${user.userId}"
						readonly /></td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input type="text" name="userName"
						value="${user.userName}" /></td>
				</tr>
				<tr>
					<td>密码:</td>
					<td><input type="text" name="userPassword"
						value="${user.userPassword}" /></td>
				</tr>
				<tr>
					<td>角色:</td>
					<td><select name="userRole" value="${user.userRole}">
							<option value="master">校长</option>
							<option value="teacher">老师</option>
							<option value="student">学生</option>
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
