<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page    isELIgnored="false"  %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    
    <title>My JSP 'change.jsp' starting page</title>
    
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
		<form action="${pageContext.request.contextPath }/servlet/UpdateFileServlet" method="post" enctype="multipart/form-data">
			<table frame="border">
				<input type="hidden" value="${file.id}" name="id">
				<tr>
					<td>用户姓名:</td>
					<td><input type="text" name="username" readonly="true" value="${file.username }">
					</td>
				</tr>
				<tr>
					<td>请上传小于500m的文件</td>
					<td><input type="file" name="filename">
					</td>
				</tr>
				<tr>
					<td>附加描述</td>
					<td><textarea rows="5" cols="80" name="description">${file.description }</textarea>
					</td>
				</tr>
				<tr>
					<td><a href="${pageContext.request.contextPath }/servlet/ListFileServlet">取消</a></td>
					<td><input type="submit" value="修改"></td>
				</tr>
			</table>
		</form>
	</center>
  </body>
</html>