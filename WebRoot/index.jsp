<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>index.jsp</title>
    <link href="css/index.css" type="text/css" rel="stylesheet"/>
  </head>
  <body>
  	<div id="main">
	  	<div id="top">
	  		<div id="top_title">
	  			猫哥培训班作业管理系统
	  		</div><!-- 标题部分 -->
	  		<div id="top_info">
	  			欢迎您，尊敬的：${sessionUser.userName}
	  		</div><!-- 登录用户信息部分 -->
	  	</div><!-- top部分是标题栏 -->
	  	<div id="left">
	  		<ul>
	  		  	<c:forEach items="${sessionRoleMenu}" var="menu">
		 		<li>
		 			<a href="/HomeworkSystem/ActionServlet?method=${menu[1]}&entityType=${menu[2]}">${menu[0]}</a>
		 		</li>
		    	</c:forEach>
	  		</ul>
	  	</div><!-- left部分是菜单栏 -->
	  	<div id="right">
	  		<c:if test="${empty actionUrl}">
	  			欢迎来到猫哥培训班管理系统
	  		</c:if>
	  		<c:if test="${not empty actionUrl}">
	  			<jsp:include page="${actionUrl}" flush="true"></jsp:include>
	  		</c:if>
	  	</div><!-- right部分是具体内容显示区 -->
  	</div>
  </body>
</html>
