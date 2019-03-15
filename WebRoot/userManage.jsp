<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>userManage.jsp</title>
    <link href="css/content.css" type="text/css" rel="stylesheet"/>
    <link href="css/table.css" type="text/css" rel="stylesheet"/>
  </head>
  <body>
  	<div id="content_top">
  		人员管理
  	</div><!-- user_top end -->
  	<div id="content_mid">
  		<table class="table_theme1">
  			<thead>
  				<tr>
  				<th>人员编码</th>
  				<th>姓名</th>
  				<th>角色</th>
  				<th>操作</th>
  				<th>操作</th>
  				</tr>
  			</thead>
  			<c:forEach items="${users}" var="item">
		 		<tr>
		 			<td>${item.userId}</td>
		 			<td>${item.userName}</td>
		 			<td>${item.userRole.roleName}</td>
		 			<td><a href="/HomeworkSystem/ActionServlet?method=edit&entityType=User&entityId=${item.userId}">编辑</a></td>
		 			<td><a href="/HomeworkSystem/ActionServlet?method=delete&entityType=User&entityId=${item.userId}">删除</a></td>
		 		</tr>
		    </c:forEach>	
		    <tr><td colspan="5">共${maxPage}页  当前是第${currentPage}页   <a href="/HomeworkSystem/ActionServlet?method=view&entityType=User&page=${currentPage-1}">上一页</a>  <a href="/HomeworkSystem/ActionServlet?method=view&entityType=User&page=${currentPage+1}">下一页</a></td></tr>
  		</table>
  	</div><!-- user_mid end -->
  	<div id="content_bottom">
  		<a href="/HomeworkSystem/ActionServlet?method=add&entityType=User">新增</a>
  	</div><!-- "user_bottom" end -->
  </body>
</html>
