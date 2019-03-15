<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>courseUpdate.jsp</title>
    <link href="css/content.css" type="text/css" rel="stylesheet"/>
   	<link href="css/table.css" type="text/css" rel="stylesheet"/>
  </head>
  <body>
  	<form action="/HomeworkSystem/ActionServlet?method=save&entityType=Course" method="post">
	  	<div id="content_top">
	  		课程信息更新
	  	</div><!-- user_top end -->
	  	<div id="content_mid">
	  		<table class="table_theme1">
	  			<tr>
	  				<td>课程名称:</td>
	  				<td>
	  					<input type="text" name="courseName" value="${course.courseName}"/>
	  					<input type="hidden" name="entityId" value="${course.courseId}"/>
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
