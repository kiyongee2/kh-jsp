<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 패턴 실습</title>
</head>
<body>
	<h3>로그인 에러 : ${error}</h3>
	<h3>장바구니 : 
		<c:forEach items="${carts}" var="cart">
			${cart} 
		</c:forEach>
	</h3>
	<h3>성적<br>
	    <c:forEach items="${subjects}" var="subject">
			${subject.key}: ${subject.value}&nbsp; 
		</c:forEach>
	</h3>
	
</body>
</html>