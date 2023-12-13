<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보</title>
<script>
	function addToCart(){
		if(confirm("상품을 주문하시겠습니까?")){
			document.addform.submit();
		}else{
			document.addform.reset();
		}
	}
</script>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3">
	   <h2>상품 정보</h2>
	   <div class="row my-3">
	   	<div class="col-5">
	   		<img src="../upload/${product.pimage}" style="width: 100%">
	   	</div>
	   	<div class="col-6 mt-4">
	   		<h3>${product.pname}</h3>
	   		<p class="mt-3">${product.description}</p>
	   		<p><b>상품 코드:</b> <span class="badge bg-dark">${product.pid}</span> 
	   		<p><b>분류:</b> ${product.category}
	   		<p><b>재고수:</b> ${product.pstock}
	   		<p><b>가격:</b> ${product.price}원
	   		<form action="/addcart.do?pid=${product.pid}" method="post" name="addform">
	   			<a href="#" onclick="addToCart()" class="btn btn-success">상품주문</a>
	   			<a href="/cart.do" class="btn btn-warning">장바구니</a>
	   			<a href="/productlist.do" class="btn btn-secondary">상품 목록 &raquo;</a>
	   		</form>
	   	</div>
	   </div>
	 </div> 
	 <jsp:include page="../footer.jsp" />
</body>
</html>