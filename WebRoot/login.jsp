<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>login.jsp</title>
<link href="css/login.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="top">培训班作业管理系统登录</div>
	<div id="mid">
		<div id="login_input">
			<form id="mainForm" method="post"
				action="/HomeworkSystem/LoginServlet">
				<div class="row">
					<span>用户：</span><input type="text" name="userName" />
				</div>
				<div class="row">
					<span>密码：</span><input type="password" name="userPassword" />
				</div>
				<div class="row">
					<input class="submit" type="submit" value="登录" />
				</div>
			</form>
		</div>
	</div>
	<div id="bottom">Copyright:PandaBrother 2017.2.22</div>
</body>
</html>
