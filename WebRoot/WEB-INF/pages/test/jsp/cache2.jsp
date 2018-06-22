<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache"%>
<%@ include file="/comment/pages/duanmlssh.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	缓存已刷新...<p>
	<cache:flush key="testcache" scope="application" />
	<a href="<%=basePath %>test/879kjkjk/cache1.html">返回</a>
</body>
</html>
