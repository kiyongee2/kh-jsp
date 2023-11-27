<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#container{width: 80%; margin: 30px auto; text-align: center;}
	select{padding: 5px;}
</style>
</head>
<body>
	<jsp:useBean id="product" class="el.Product" />
	<div id="container">
		<h2>상품 목록</h2>
		<hr>
		<form action="selProduct.jsp" method="post">
			<select name="select">
				<%
					for(String item : product.getProductList()){
						out.println("<option>" + item + "</option>");
					}
				%>
			</select>
			<button type="submit">선택</button>
		</form>
	</div>
</body>
</html>