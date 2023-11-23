<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("uid");
    String pw = request.getParameter("passwd");
    String name = request.getParameter("name");
%>
<p>회원가입 성공!!</p>
<p>아이디: <%=id %>
<p>패스워드: <%=pw %>
<p>이름: <%=name %>


