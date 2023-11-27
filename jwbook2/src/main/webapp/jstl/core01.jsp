<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	pageContext.setAttribute("cart", "계란");
%>
<body>
	<h3>${cart}</h3>

	<%-- <%
		int num = 10;
	
		if(num % 2 == 0){
			out.println("짝수");
		}else{
			out.println("홀수");
		}
	%> --%>
	
	<!-- <c:if>로 짝수/홀수 판정 -->
	<c:set var="num" value="11" />
	<c:if test="${num % 2 == 0}">
		<c:out value="${'짝수입니다.'}" />
	</c:if>
	<c:if test="${num % 2 == 1}">
		<c:out value="${'홀수입니다.'}" />
	</c:if>
	<br>
	
	<!-- <c:choose>로 짝수/홀수 판정 -->
	<c:choose>
		<c:when test="${num % 2 == 0}">
			<c:out value="${'짝수입니다.'}" />
		</c:when>
		<c:otherwise>
			<c:out value="${'홀수입니다.'}" />
		</c:otherwise>
	</c:choose>
</body>
</html>