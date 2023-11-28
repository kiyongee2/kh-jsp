<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String context = request.getContextPath();
    String uri = request.getRequestURI();
    StringBuffer url = request.getRequestURL();

%>
<p>컨텍스트(프로젝트): <%=context %>
<p>uri(프로젝트 + 파일): <%=uri %>
<p>url(전체경로): <%=url %>
