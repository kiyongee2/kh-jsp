<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="now" class="java.util.Date" />
	${now}
	<p><fmt:formatDate value="${now}" type="date" />
	<p><fmt:formatDate value="${now}" type="time" />
	<p><fmt:formatDate value="${now}" pattern="yyyy:MM:dd hh:mm:ss a" />
	
	<p>다국어 처리</p>
	<%= response.getLocale() %>
	
	<p>한국어</p>
	<fmt:setLocale value="ko" />
	<%= response.getLocale() %>
	
	<p>영어</p>
	<fmt:setLocale value="en" />
	<%= response.getLocale() %>
	
	<p>일국어</p>
	<fmt:setLocale value="ja" />
	<%= response.getLocale() %>
	
	
</body>
</html>