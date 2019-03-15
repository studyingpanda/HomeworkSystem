<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>workManage.jsp</title>
    <link href="css/content.css" type="text/css" rel="stylesheet"/>
    <link href="css/table.css" type="text/css" rel="stylesheet"/>
  </head>
  <body>
  	<div id="content_top">
  		课程作业列表
  	</div><!-- user_top end -->
  	<div id="content_mid">
  		<table class="table_theme1">
  			<thead>
  				<tr>
  				<th>作业编号</th>
  				<th>作业题目</th>
  				<th>所属课程</th>
  				<th>发布时间</th>
  				</tr>
  			</thead>
  			<c:forEach items="${works}" var="item">
		 		<tr>
		 			<td>${item.workId}</td>
		 			<td>${item.workTitle}</td>
		 			<td>${item.workCourse.courseName}</td>
		 			<td>${item.workTime}</td>
		 		</tr>
		    </c:forEach>	
		    <tr><td colspan="5">共${maxPage}页  当前是第${currentPage}页   <a href="/HomeworkSystem/ActionServlet?method=view&entityType=Work&byCourseId=${byCourseId}&page=${currentPage-1}">上一页</a>  <a href="/HomeworkSystem/ActionServlet?method=view&entityType=Work&byCourseId=${byCourseId}&page=${currentPage+1}">下一页</a></td></tr>
  		</table>
  	</div><!-- user_mid end -->
  	<div id="content_bottom">
  		<a href="/HomeworkSystem/ActionServlet?method=add&entityType=Work">发布新作业</a>
  	</div><!-- "user_bottom" end -->
  </body>
</html>
