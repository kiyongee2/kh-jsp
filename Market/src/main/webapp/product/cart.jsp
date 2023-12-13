<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3">
	   <h2>장바구니</h2>
	   <div class="row my-3 px-3" align="center">
	    <table style="width: 100%">
	    	<tr>
	    		<td align="left">
	    			<a href="/deletecart.do" class="btn btn-danger">삭제하기</a>
	    		</td>
	    		<td align="right">
	    			<a href="/shippingform.do?cartId=${cartId}" class="btn btn-success">주문하기</a>
	    		</td>
	    	</tr>
	    </table>
	    <div style="padding-top: 50px">
	      <table class="table table-hover">
	      	<thead>
	      	  <tr>
	      	  	<th>상품</th><th>가격</th><th>수량</th><th>소계</th><th>비고</th>
	      	  </tr>
	      	</thead>
	      	<tbody>
	      	  <c:forEach items="${cartList}" var="product">
	      	    <tr>
	      	    	<td>${product.pid}-${product.pname}</td>
	      	    	<td>${product.price}</td>
	      	    	<td>${product.quantity}</td>
	      	    	<td>${product.price * product.quantity}</td>
	      	    	<td>
	      	    		<a href="/removecart.do?pid=${product.pid}" class="badge bg-dark">삭제</a>
	      	    	</td>
	      	    </tr>
	      	  </c:forEach>
	      	</tbody>
	      	<tfoot>
	      		<tr>
	      			<td></td><td></td><td>총액</td><td>${sum}</td><td></td>
	      		</tr>
	      	</tfoot>
	      </table>
	    </div>
	   </div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>