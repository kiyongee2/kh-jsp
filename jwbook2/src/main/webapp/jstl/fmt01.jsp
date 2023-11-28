<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>숫자 : <fmt:formatNumber value="12300" />
	<p><fmt:formatNumber value="12300" type="number" />
	<p><fmt:formatNumber value="12300" type="currency" currencySymbol="$" />
	<p><fmt:formatNumber value="0.02" type="percent" />
	<p><fmt:formatNumber value="12300.567" pattern="#,##0.0" />
	
</body>
</html>