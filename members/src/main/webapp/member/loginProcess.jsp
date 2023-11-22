<%@page import="members.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	LoginBean login = new LoginBean();
	String id = request.getParameter("userid");
	String pw = request.getParameter("passwd");
	
	login.setUserid(id);
	login.setPasswd(pw);
	
	boolean result = login.checkUser();
	if(result){
		out.println("로그인 성공!!");
	}else{
		out.print("로그인 실패!!");
	}

%>
<p>아이디 <%= login.getUserid() %></p>
<p>비밀번호 <%= login.getPasswd() %></p>