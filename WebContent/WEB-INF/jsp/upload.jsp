<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>My JSP 'upload.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<center>
		<form action="${pageContext.request.contextPath }/servlet/UploadFileServlet" method="post" enctype="multipart/form-data">
			<table frame="border">
				<tr>
					<td>用户姓名:</td>
					<td><input type="text" name="username" readonly="true" value="${user.username}">
					</td>
				</tr>
				<tr>
					<td>请上传小于500m的文件</td>
					<td><input type="file" name="filename">
					</td>
				</tr>
				<tr>
					<td>附加描述</td>
					<td><textarea rows="5" cols="80" name="description"></textarea>
					</td>
				</tr>
				<tr>
					<td><input type="reset" value="重置"></td>
					<td><input type="submit" value="上传"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>