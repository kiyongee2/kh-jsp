<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int a = Integer.parseInt(request.getParameter("num1"));
	int b = Integer.parseInt(request.getParameter("num2"));
	int c = a / b;
	out.println(c);

%>