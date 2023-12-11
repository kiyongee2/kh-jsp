<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String realFolder = "D:/yong-jakarta/Market/src/main/webapp/upload";

	MultipartRequest multi = new MultipartRequest(request, realFolder,
			10*1024*1024, new DefaultFileRenamePolicy());
	
	Enumeration<?> files = multi.getFileNames();
	String filename = "";
	while(files.hasMoreElements()){
		String name = (String)files.nextElement();
		filename = multi.getFilesystemName(name);
		out.println(filename);
	}
	
%>