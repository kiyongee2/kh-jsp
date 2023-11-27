<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("userID");

    //모든 세션 삭제
    session.invalidate();
%>
<p>세션을 삭제했습니다.</p>
