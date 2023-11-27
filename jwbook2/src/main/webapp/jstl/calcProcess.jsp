<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="num" value="${param.number}" />
<c:if test="${num % 2 == 0}">
	<c:out value="${'짝수입니다.'}" />
</c:if>
<c:if test="${num % 2 == 1}">
	<c:out value="${'홀수입니다.'}" />
</c:if>
