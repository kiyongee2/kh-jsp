<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>오늘의 날짜 및 시간</h2>
	<%= new Date().toLocaleString() %>
	<%-- <%= java.time.LocalDateTime.now() %> --%>
</body>
</html>