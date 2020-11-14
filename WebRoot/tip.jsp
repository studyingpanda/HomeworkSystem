<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- 使用c:标签需要添加本行代码 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>tip.jsp</title>
</head>
<body>
	<!-- 如果tipInfo为空 -->
	<c:if test="${empty tipInfo}">
	  	欢迎使用猫哥培训班管理系统
	</c:if>
	<!-- 如果tipInfo不为空，显示 tipInfo -->
	<c:if test="${not empty tipInfo}">
		提示信息：${tipInfo}<br />
	</c:if>
</body>
</html>
