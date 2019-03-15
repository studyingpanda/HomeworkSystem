<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>courseManage.jsp</title>
    <link href="css/content.css" type="text/css" rel="stylesheet"/>
    <link href="css/table.css" type="text/css" rel="stylesheet"/>
  </head>
  <body>
  	<div id="content_top">
  		课程列表
  	</div><!-- user_top end -->
  	<div id="content_mid">
  		<table class="table_theme1">
  			<thead>
  				<tr>
  				<th>课程编号</th>
  				<th>课程名称</th>
  				<th>课程教师</th>
  				<th>操作</th>
  				</tr>
  			</thead>
  			<c:forEach items="${courses}" var="item">
		 		<tr>
		 			<td>${item.courseId}</td>
		 			<td>${item.courseName}</td>
		 			<td>${item.courseUser.userName}</td>
		 			<td><a href="/HomeworkSystem/ActionServlet?method=view&entityType=Work&byCourseId=${item.courseId}">查看课程作业</a></td>
		 		</tr>
		    </c:forEach>	
		    <tr><td colspan="4">共${maxPage}页  当前是第${currentPage}页   <a href="/HomeworkSystem/ActionServlet?method=view&entityType=Course&page=${currentPage-1}">上一页</a>  <a href="/HomeworkSystem/ActionServlet?method=view&entityType=Course&page=${currentPage+1}">下一页</a></td></tr>
  		</table>
  	</div><!-- user_mid end -->
  	<div id="content_bottom">
  		
  	</div><!-- "user_bottom" end -->
  </body>
</html>
