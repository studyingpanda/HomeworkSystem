<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>jobUpdateByTeacher.jsp</title>
    <link href="css/content.css" type="text/css" rel="stylesheet"/>
   	<link href="css/table.css" type="text/css" rel="stylesheet"/>
  </head>
  <body>
  	<form action="/HomeworkSystem/ActionServlet?method=save&entityType=Job" method="post">
	  	<div id="content_top">
	  		作业批阅
	  	</div><!-- user_top end -->
	  	<div id="content_mid">
	  		<table class="table_theme1">
	  			<tr>
	  				<td>作业编号:</td>
	  				<td>
	  					${job.jobId}
	  					<input type="hidden" name="entityId" value="${job.jobId}"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>题目:</td>
	  				<td>
	  					${job.jobWork.workTitle}
	  					<input type="hidden" name="workId" value="${job.jobWork.workId}"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>作业内容:</td>
	  				<td>
	  					<textarea name="jobContent" rows="8" cols="30" >${job.jobContent}</textarea>
	  				</td>
	  			</tr>
	  				<tr>
	  				<td>打分:</td>
	  				<td>
	  					<select name="jobScore">
	  						<option value="0">优秀</option>
	  						<option value="1">良好</option>
	  						<option value="2">合格</option>
	  						<option value="3">不合格</option>
	  					</select>
	  				</td>
	  			</tr>
	  		</table>
	  	</div><!-- user_mid end -->
	  	<div id="content_bottom">
			<input type="submit" value="确认"></input>
	  	</div><!-- "user_bottom" end -->
	  </form>
  </body>
</html>
