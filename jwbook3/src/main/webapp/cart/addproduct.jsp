<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");    
	//상품 리스트 생성 및 상품 세션 발급
	ArrayList<String> productList = (ArrayList)session.getAttribute("sessionList");
	if(productList == null){
		productList = new ArrayList<>();
		session.setAttribute("sessionList", productList); //상품 세션 발급
	}
	
	//선택한 상품 받아서 상품리스트에 저장
	String product = request.getParameter("product");
	productList.add(product);
%>
<script>
	alert("<%=product %>가 추가되었습니다.");
	history.back();
</script>
