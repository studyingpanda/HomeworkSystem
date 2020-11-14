<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>login.jsp</title>
  </head>
  <body>
  	<form id="mainForm" method="post" action="/HomeworkSystem/LoginServlet">
  		请输入用户名：<input type="text" name="userName" />
  		<br/>
  		请输入密码：<input type="password" name="userPassword"/>	
  		<br/>
  		<input type="submit" value="登录"/>
  	</form>
  </body>
</html>