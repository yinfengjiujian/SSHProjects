<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>oscache 1</title>
<%@ include file="/comment/pages/duanmlssh.jsp" %>
</head>
<%
  System.out.println(path);
  System.out.println(basePath);
%>
<body>
	没有缓存的日期: ${date1}<p>
		<!--自动刷新-->
	<cache:cache time="5">  
   	每5秒刷新缓存一次的日期: ${date2}<p>   
   </cache:cache>
		<!--手动刷新-->
   <cache:cache key="testcache" scope="application">  
   	手动刷新缓存的日期: ${date3 } <p>
	</cache:cache>
	<a href="<%=basePath%>grjbxx/getGrjbxx.html">手动刷新</a>
</body>
</html>