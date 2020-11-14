<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 使用c:标签需要添加本行代码 -->
<html>
<head>
<title>index.jsp</title>
<!-- index.css是外部样式文件 -->
<link href="css/index.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
		<div id="top">
			<!-- 标题部分 -->
			<div id="top_title">猫哥培训班作业管理系统</div>
			<!-- 登录用户信息部分 -->
			<div id="top_info">欢迎您，尊敬的：${loginUser.userName}</div>
		</div>
		<!-- left部分是菜单栏 -->
		<div id="left">
			<ul>
				<c:forEach items="${loginMenus}" var="menu">
					<li>
					<a href="/HomeworkSystem/RouteServlet?childPage=${menu[1]}">${menu[0]}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<!-- right部分是具体内容显示区 -->
		<div id="right">
			<c:if test="${empty childPage}">
	  			欢迎来到猫哥培训班管理系统
	  		</c:if>
			<c:if test="${not empty childPage}">
				<jsp:include page="${childPage}" flush="true"></jsp:include>
			</c:if>
		</div>
	</div>
</body>
</html>
