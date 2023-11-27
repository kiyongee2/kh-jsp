<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:useBean id="calc" class="calculator.Calculator" />
<%-- <jsp:setProperty property="*" name="calc"/> --%>
<jsp:setProperty property="n1" name="calc"/>
<jsp:setProperty property="n2" name="calc"/>
<jsp:setProperty property="op" name="calc"/>
<body>
	<h2>계산 결과</h2>
	<hr>
	결과: <%=calc.calc() %>
</body>
</html>