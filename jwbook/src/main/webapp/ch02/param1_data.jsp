<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
    String pw = request.getParameter("pwd");
%>

<p>아이디 <%=id %>
<p>패스워드 <%=pw %>