<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); //한글 인코딩 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <jsp:useBean id="product" class="el.Product" />
	<div id="container">
		<h2>상품 선택 결과</h2>
		<hr>
		<p>1. 선택한 상품은: ${param.select}</p>
		<p>2. num1 + num2 = ${product.num1 + product.num2}</p>
	</div>
</body>
</html>