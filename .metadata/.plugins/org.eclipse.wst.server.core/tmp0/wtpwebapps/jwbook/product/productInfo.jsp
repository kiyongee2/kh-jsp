<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보</title>
</head>
<body>
	<h2>상품 정보</h2>
	<p>상품아이디: ${product.id} </p>
	<p>상품명: ${product.name} </p>
	<p>제조사: ${product.maker} </p>
	<p>가격: ${product.price} </p>
	<p>등록일: ${product.date} </p>
	<a href="/jwbook/pcontrol?action=list">상품 목록</a>
</body>
</html>