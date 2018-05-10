<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>


<title>下载显示页面</title>
<style type="text/css">
tr:hover {
	background-color: #CCCCFF
}
</style>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>
	<center>
		<table border="1" width="80%">
			<tr>
				<td>文件id</td>
				<td>文件名称</td>
				<td>文件路径</td>
				<td>上传时间</td>
				<td>文件描述</td>
				<td>上传用户</td>
				<td>下载文件</td>
				<td>修改文件</td>
				<td>删除文件</td>
			</tr>
			<c:forEach items="${requestScope.pagebean.list}" var="file">
				<tr>
					<td>${file.id}</td>
					<td>${file.filename}</td>
					<td>${file.savepath}</td>
					<td>${file.uptime}</td>
					<td>${file.description}</td>
					<td>${file.username}</td>
					<td><a
						href="${pageContext.request.contextPath }/servlet/DownLoadServlet?id=${file.id}">下载</a>
					</td>
					<td><a
						href="${pageContext.request.contextPath }/servlet/UpdateFileServlet?id=${file.id}">修改</a>
					</td>
					<td><a
						href="${pageContext.request.contextPath }/servlet/DeleteFileServlet?id=${file.id}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>

		<form
			action="${pageContext.request.contextPath}/servlet/ListFileServlet"
			method="post">
			共[<b>${pagebean.totalRecord }</b>]条记录, 每页<input type="text"
				name="pageSize" value="${pagebean.pageSize }" style="width: 30px"
				maxlength="2">条, <input type="submit" value="确认"> 共[<b>${pagebean.totalPage }</b>]页,
			当前[<b>${pagebean.currentPage }</b>]页
		</form>
		<c:if test="${pagebean.totalRecord>0}">
			<form
				action="${pageContext.request.contextPath}/servlet/ListFileServlet"
				method="post">
				&nbsp;&nbsp;&nbsp; <a
					href="${pageContext.request.contextPath}/servlet/ListFileServlet?currentPage=${pagebean.previousPage}&pageSize=${pagebean.pageSize}">上一页</a>
				<c:forEach items="${pagebean.pageBar}" var="page">
					<c:if test="${page==pagebean.currentPage }">
						<font color="red">${page}</font>
					</c:if>
					<c:if test="${page!=pagebean.currentPage }">
						<a
							href="${pageContext.request.contextPath}/servlet/ListFileServlet?currentPage=${page}&pageSize=${pagebean.pageSize}">${page}</a>
					</c:if>
				</c:forEach>
				<a href="${pageContext.request.contextPath}/servlet/ListFileServlet?currentPage=${pagebean.nextPage}&pageSize=${pagebean.pageSize}">下一页</a>
				<input type="text" name="currentPage" style="width: 30px" /> 
				<input type="hidden" name="totalPage" value="${pagebean.totalPage}">
				<input type="hidden" name="pageSize" value="${pagebean.pageSize}">
				<input type="submit" value=" GO " />
			</form>
		</c:if>
	</center>
</body>
</html>