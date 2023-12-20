<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>

</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3">
	   <h2>상품 목록</h2>
		 <div class="row" align="center">
		    <c:if test="${empty products}">
		    	검색 결과 없음
		    </c:if>
		    <c:if test="${not empty products}">
			    <c:forEach var="product" items="${products}">
			 	<div class="col-4">
			 	    <img src="../upload/${product.pimage}" style="width: 100%" class="my-3">
			 		<h3 class="mt-3">${product.pname}</h3>
			 		<p>${product.description}</p>
			 		<p>${product.category}</p>
			 		<p>${product.price}원</p>
			 		<a href="/productinfo.do?pid=${product.pid}" class="btn btn-secondary">상세 정보 &raquo;</a>
			 	</div>
			 	</c:forEach>
		 	</c:if>
		 </div>
	 </div>
	 <jsp:include page="../footer.jsp" />
</body>
</html>