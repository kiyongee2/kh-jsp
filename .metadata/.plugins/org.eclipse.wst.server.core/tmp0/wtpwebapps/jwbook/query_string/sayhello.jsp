<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <%
	int cnt = Integer.parseInt(request.getParameter("cnt"));	
    //out.println(cnt);

	for(int i = 0; i < cnt; i++){
		out.println("안녕~ JSP<br>");
	}
%> --%>

<%
    //cnt - null 발생 오류 처리
	int cnt = 0;
    
    if(request.getParameter("cnt") != null){
    	cnt = Integer.parseInt(request.getParameter("cnt"));	
    }
	
	for(int i = 0; i < cnt; i++){
		out.println("안녕~ JSP<br>");
	}
%>