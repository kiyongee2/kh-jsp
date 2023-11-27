<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 구구단 출력  -->
	<c:set var="dan" value="4" />
	<c:forEach var="i" begin="1" end="9">
		<c:out value="${dan} x ${i} = ${dan*i}" /><br>
	</c:forEach>
	
	<!-- 구구단 전체 -->
	<c:forEach var="i" begin="2" end="9">
		<c:forEach var="j" begin="1" end="9">
			<c:out value="${i} x ${j} = ${i*j}" /><br>
		</c:forEach>
		<br>
	</c:forEach>
</body>
</html>