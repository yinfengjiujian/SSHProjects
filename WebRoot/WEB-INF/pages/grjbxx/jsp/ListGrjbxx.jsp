<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache"%>
<%@ page import="com.neusoft.duanmlssh.pojo.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'ListGrjbxx.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<table border=1 width=900>
		<tr align="center">
			<td>ACCOUNT</td>
			<td>PASSWORD</td>
			<td>USER_NAME</td>
		</tr>
		<c:forEach var="grjbxx" items="${ListGrjbxx}">
			<tr>
				<td><a href="${grjbxx.account}/fajfe">${grjbxx.account}</a></td>
				<td>fasfs:${grjbxx.password}</td>
				<td><c:out value="${grjbxx.userName}" ></c:out></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
