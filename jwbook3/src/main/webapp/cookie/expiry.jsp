<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();

	for(int i=0; i<cookies.length; i++){
		//쿠키 유효 시간 설정(2분)
		cookies[i].setMaxAge(2*60);
		
		//쿠키 삭제
		//cookies[i].setMaxAge(0);
		response.addCookie(cookies[i]);
	}
	
	response.sendRedirect("cookie02.jsp");
%>
