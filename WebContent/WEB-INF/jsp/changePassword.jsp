<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page    isELIgnored="false"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>重置密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <body>
  	<center>
  		<hr>
  		<h1>修改密码</h1>
   		<form action="${pageContext.request.contextPath}/servlet/ChangePasswordServlet" method="post">
	   		<table>
		   		<tr><td>请输入用户名:<input type="text" name="username" value=""></input>${message }${bean.mistakes.username }</td></tr>
		   		<tr><td>您的${question}是:<input type="text" name="answer" ></input>${mess }</td></tr>
		   		<tr><td>请输入新密码:<input type="password" name="password1" value="">${bean.mistakes.password1 }</td></tr>
		   		<tr><td>再次输入密码:<input type="password" name="password2" value="">${bean.mistakes.password2 }</td></tr>
	   			<tr><td><input type="submit" value="提交">
	   					<input type="reset" value="重置" name="reset"></input></td>
	   				<td>刷新页面:<a href="${pageContext.request.contextPath }/servlet/ChangePasswordUIServlet">刷新</a></td>
	   				<td>转到主页:<a href="${pageContext.request.contextPath }/servlet/IndexUIServlet">主页</a></td>
	   			</tr>
 			</table>
   		</form>
   		<hr>
   		<marquee><h3>温馨提示:刷新页面可更换问题</h3></marquee> <br>
	</center>
  </body>
</html>