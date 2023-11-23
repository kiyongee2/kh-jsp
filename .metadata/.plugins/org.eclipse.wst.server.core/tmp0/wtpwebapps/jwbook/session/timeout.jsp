<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>세션 유효시간 변경 전</h3>
<%
	//세션 유효시간 -30분 - 30*60=1800초
	int time = session.getMaxInactiveInterval();
    out.println(time + "초" + "<br>");
    
	time = session.getMaxInactiveInterval() / 60;
	out.println(time + "분" + "<br>");
%>

<h3>세션 유효시간 변경 후</h3>
<%
	//세션 유효시간을 5분으로 설정함
	session.setMaxInactiveInterval(5*60);

    time = session.getMaxInactiveInterval();
	out.println(time + "초" + "<br>");

	time = session.getMaxInactiveInterval() / 60;
	out.println(time + "분" + "<br>");
%>