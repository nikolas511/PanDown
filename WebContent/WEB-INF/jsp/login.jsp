<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
	<center>
		<hr>
  		<h1>用户登录</h1>
	  <form action="${pageContext.request.contextPath}/servlet/LoginServlet" method="post">
		   		<table>
			   		<tr><td>用户名:</td>
			   		<td><input type="text" name="username" value="${username}"></input></td>
			   		<td><span class="user">${user}</span></td>
			   		</tr>
			   		<tr><td>密&nbsp码:</td><td><input type="password" name="password"></input></td>
			   		<td><span class="message">${message}</span></td></tr>
			   		<tr><td><input type="submit" value="登录" name="submit"></input></td>
			   			<td><input type="reset" value="重置" name="reset"></input></td>
			   			<td>转到主页:<a href="${pageContext.request.contextPath }/servlet/IndexUIServlet">主页</a></td>
			   			<td>未有账号:<a href="${pageContext.request.contextPath }/servlet/RegisterUIServlet">注册</a></td>
			   			<td>重置密码:<a href="${pageContext.request.contextPath }/servlet/ChangePasswordUIServlet">修改密码</a></td>
			   		</tr>
		   		</table>
	  </form>
	</center>
	  <hr>
	 <marquee><h3>请填写正确有效的信息进行登录</h3></marquee> <br>
  </body>
</html>