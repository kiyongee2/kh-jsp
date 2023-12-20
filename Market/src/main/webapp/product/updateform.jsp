<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container my-3">
	   <h2 >상품 수정</h2>
		 <div class="row">
		    <form action="/updateproduct.do" method="post"
		    		enctype="multipart/form-data">
		    	<div class="form-group row my-3">
		    		<label for="pid" class="col-2">상품 코드</label>
		    		<div class="col-3">
		    			<input type="text" id="pid" name="pid" class="form-control"
		    					value="${product.pid}">
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<label for="pname" class="col-2">상품명</label>
		    		<div class="col-3">
		    			<input type="text" id="pname" name="pname" class="form-control"
		    					value="${product.pname}">
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<label for="price" class="col-2">가격</label>
		    		<div class="col-3">
		    			<input type="text" id="price" name="price" class="form-control"
		    					value="${product.price}">
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<label for="description" class="col-2">상품 설명</label>
		    		<div class="col-4">
		    			<textarea rows="2" cols="50" name="description"
		    						class="form-control">${product.description}</textarea>
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<label for="category" class="col-2">카테고리</label>
		    		<div class="col-3">
		    			<input type="text" id="category" name="category" class="form-control"
		    					value="${product.category}">
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<label for="pstock" class="col-2">재고수</label>
		    		<div class="col-3">
		    			<input type="text" id="pstock" name="pstock" class="form-control"
		    					value="${product.pstock}">
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<label for="condition" class="col-2">상태</label>
		    		<div class="col-3">
		    		   <c:choose>
			    		<c:when test="${product.condition eq 'New'}">
			    			<label><input type="radio" name="condition" value="New" checked>신상품</label>
			    			<label><input type="radio" name="condition" value="Old">중고품</label>
		    			</c:when>
			    		<c:otherwise>
			    			<label><input type="radio" name="condition" value="New" >신상품</label>
			    			<label><input type="radio" name="condition" value="Old" checked>중고품</label>
		    			</c:otherwise>
		    			</c:choose>
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<label for="pimage" class="col-2">상품이미지</label>
		    		<div class="col-3">
		    			<input type="file" id="pimage" name="pimage" class="form-control"
		    					value="${product.pimage}">
		    		</div>
		    	</div>
		    	<div class="form-group row my-3">
		    		<div class="col-3">
		    			<input type="submit" value="등록" class="btn btn-primary">
		    		</div>
		    	</div>
		    </form>
		 </div>
	 </div>
	 <jsp:include page="../footer.jsp" />
</body>
</html>