<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Param 액션 태그</h2>
	<jsp:forward page="param1_data.jsp">
		<jsp:param value="admin" name="id"/>
		<jsp:param value="0000" name="pwd"/>
	</jsp:forward>
	
	<p>Jakarta Server Page</p>
</body>
</html>