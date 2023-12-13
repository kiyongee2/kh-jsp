<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	out.println("현재 설정된 쿠키의 개수: " + cookies.length + "<br>");
	out.println("==========================================<br>");
	for(int i=0; i<cookies.length; i++){
		out.println(cookies[i].getName() + "<br>" );
		out.println(cookies[i].getValue() + "<br>");
		out.println("==========================================<br>");
	}
	

%>