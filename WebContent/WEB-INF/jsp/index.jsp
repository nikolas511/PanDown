<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Yun网盘</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  		<hr>
		<marquee><h1>欢迎光临</h1></marquee> <br>
		<center>
			<h1>Yun网盘首页</h1><br>
			<c:if test="${user!=null}">
				<h2>欢迎你:${user.nickname}</h2>
				<h2><a href="${pageContext.request.contextPath }/index.jsp" target="view_window">去网盘</a></h2>
				<h4><a href="${pageContext.request.contextPath }/servlet/QuitLoginServlet">注销</a></h4>
			</c:if>
			<c:if test="${user==null}">
				<a href="${pageContext.request.contextPath }/servlet/RegisterUIServlet">注册</a>
				<a href="${pageContext.request.contextPath }/servlet/LoginUIServlet">登录</a>
			</c:if>
		</center>
		<br>
		<hr>   
  </body>
</html>