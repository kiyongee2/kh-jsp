<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 내역</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3">
	   <h2>주문 내역</h2>
	   <div class="row my-3 px-3" align="center">
	     <div class="col-4" align="left">
	       배송 주소<br>
	       성명: ${shipping_name}<br>
	       우편번호: ${shipping_zipcode}
	       주소: ${shipping_address}
	     </div>
	     <div class="col-8" align="left">
	       배송일: ${shipping_shippingdate}
	     </div>
	     
	     <div style="padding-top: 50px">
	      <table class="table table-hover">
	      	<thead>
	      	  <tr>
	      	  	<th>상품</th><th>가격</th><th>수량</th><th>소계</th>
	      	  </tr>
	      	</thead>
	      	<tbody>
	      	  <c:forEach items="${cartList}" var="product">
	      	    <tr>
	      	    	<td>${product.pname}</td>
	      	    	<td>${product.price}</td>
	      	    	<td>${product.quantity}</td>
	      	    	<td>${product.price * product.quantity}</td>
	      	    </tr>
	      	  </c:forEach>
	      	</tbody>
	      	<tfoot>
	      		<tr>
	      			<td></td><td></td><td>총액</td><td>${sum}</td>
	      		</tr>
	      	</tfoot>
	      </table>
	      <a href="/thankscustomer.do" class="btn btn-success">주문 완료</a>
	    </div>
	   </div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>