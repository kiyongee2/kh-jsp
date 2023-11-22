<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl 실습</title>
</head>
<body>
	<h2>JSTL 종합 예제</h2>
	<hr>
	<h3>set, out</h3>
	<c:set var="product1" value="<h2>애플 아이폰</h2>" />
	<p>
		product1(jstl):
		<c:out value="${product1}" escapeXml="true" />
	</p>
	<p>product1(el): ${product1}</p>
</body>
</html>