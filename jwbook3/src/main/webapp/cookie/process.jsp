<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	if(id.equals("today") && pwd.equals("1234")){
		//Cookie(쿠키이름, 쿠키값)
		Cookie cookieId = new Cookie("userId", id);
		Cookie cookiePw = new Cookie("userPw", pwd);
		
		//브라우저(로컬컴퓨터)로 보내줌
		response.addCookie(cookieId);
		response.addCookie(cookiePw);
		
		out.println("쿠키 생성이 성공했습니다.");
	}else{
		out.println("쿠키 생성이 실패했습니다.");
	}
%>