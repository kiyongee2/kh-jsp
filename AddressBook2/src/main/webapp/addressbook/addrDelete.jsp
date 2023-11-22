<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="abDAO" class="addressbook.AddrBookDAO" scope="application" />
<%
	int num = Integer.parseInt(request.getParameter("num"));
	abDAO.deleteAddrBook(num);
	
    //삭제후 페이지 이동
    response.sendRedirect("addrList.jsp");
%>
