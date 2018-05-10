<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>注册页面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/ShowCalendar.js"></script>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
	  <hr>
	  <center><h1>用户注册</h1></center>
	  <center>
   	<form action="${pageContext.request.contextPath}/servlet/RegisterServlet" method="post">
		 <table>
		   	<tr><td>用户名:</td><td><input type="text" name="username" value="${formBean.username }"></input>
		   	<span class="message">${formBean.errors['username']}${message}</span></td></tr>
		   	<%--${formBean.errors.username} el表达式还可以这样写--%>
		   	<tr><td>密码:</td><td><input type="password" name="password" value="${formBean.password }"></input>
		   	<span class="message">${formBean.errors['password']}</span></td></tr>
		   	
		   	<tr><td>确认密码:</td><td><input type="password" name="password2" value="${formBean.password2 }"></input>
		   	<span class="message">${formBean.errors['password2']}</span></td></tr>
		   	
		   	<tr><td>邮箱:</td><td><input type="text" name="email" value="${formBean.email }"></input>
		   	<span class="message">${formBean.errors['email']}</span></td></tr>
		   	
		   	<tr><td>生日:</td><td><input type="text" name="birthday" onClick="showCalendar(this.id)" id="birthday" value="${formBean.birthday }"></input>
		   	<span class="message">${formBean.errors['birthday']}</span></td></tr>
		   	
		   	<tr><td>昵称:</td><td><input type="text" name="nickname" value="${formBean.nickname }"></input>
		   	<span class="message">${formBean.errors['nickname']}</span></td></tr>
		   	
		   	<tr><td><input type="submit" value="注册" name="sumbit"></input></td>
		   		<td><input type="reset" value="重置"  name="reset"></input></td>
		   		<td>转到主页:<a href="${pageContext.request.contextPath }/servlet/IndexUIServlet">主页</a></td>
		   		<td>已有账号:<a href="${pageContext.request.contextPath }/servlet/LoginUIServlet">登录</a>
		   		<td>重置密码:<a href="${pageContext.request.contextPath }/servlet/ChangePasswordUIServlet">修改密码</a></td>
		   	</tr>
		 </table>
		</form>
		<hr>	
	  </center>
	   <marquee><h3>请把信息按照格式填写完整后单击注册</h3></marquee> <br>
  </body>
</html>