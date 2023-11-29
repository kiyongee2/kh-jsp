<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
upload_files의 경로 : <br>
<%
	ServletContext context = getServletContext();
	String realPath = context.getRealPath("uploaded_files");
	out.println(realPath);
%>
