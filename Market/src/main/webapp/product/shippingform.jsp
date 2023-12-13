<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송 정보</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3">
	   <h2 >배송 정보</h2>
		 <div class="row">
		    <form action="/shippinginfo.do" method="post">
		    	<input type="hidden" name="cartId" value="${cartId}">
		    	<div class="form-group row my-3">
		    		<label class="col-2">성명</label>
		    		<div class="col-3">
		    			<input type="text" name="name" class="form-control">
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<label  class="col-2">배송일</label>
		    		<div class="col-3">
		    			<input type="text" name="shippingdate" class="form-control"
		    					placeholder="yyyy/mm/dd">
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<label class="col-2">국가명</label>
		    		<div class="col-3">
		    			<input type="text" name="country" class="form-control">
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<label class="col-2">우편번호</label>
	    			<div class="col-3">
		    			<input type="text" name="zipcode" class="form-control">
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<label class="col-2">주소</label>
		    		<div class="col-3">
		    			<input type="text" name="address" class="form-control">
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<div class="col-3">
		    			<input type="submit" class="btn btn-primary" value="등록">
		    			<a href="/checkoutcancel.do" class="btn btn-secondary">취소</a>
		    		</div>
		    	</div>
		    </form>
		 </div>
	 </div>
	 <jsp:include page="../footer.jsp" />
</body>
</html>