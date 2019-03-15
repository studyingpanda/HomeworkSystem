<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>userUpdate.jsp</title>
    <link href="css/content.css" type="text/css" rel="stylesheet"/>
   	<link href="css/table.css" type="text/css" rel="stylesheet"/>
  </head>
  <body>
  	<form action="/HomeworkSystem/ActionServlet?method=save&entityType=User" method="post">
	  	<div id="content_top">
	  		人员信息更新
	  	</div><!-- user_top end -->
	  	<div id="content_mid">
	  		<table class="table_theme1">
	  			<tr>
	  				<td>姓名:</td>
	  				<td>
	  					<input type="text" name="userName" value="${user.userName}"/>
	  					<input type="hidden" name="entityId" value="${user.userId}"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>密码:</td>
	  				<td>
	  					<input type="text" name="userPassword" value="${user.userPassword}"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>角色:</td>
	  				<td>
		  				<select name="userRole"><!-- 编辑情况下，默认显示编辑用户的角色的功能，待后续优化项目时再讲如何实现 -->
		  					<option value="1">校长</option>
		  					<option value="2">老师</option>
		  					<option value="3">学生</option>
		  				</select>
	  				</td>
	  			</tr>
	  		</table>
	  	</div><!-- user_mid end -->
	  	<div id="content_bottom">
			<input type="submit" value="保存"></input>
	  	</div><!-- "user_bottom" end -->
	  </form>
  </body>
</html>
