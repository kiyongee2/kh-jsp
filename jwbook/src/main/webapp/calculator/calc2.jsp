<%@page import="calculator.Calculator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>계산 결과</h2>
	<hr>
	<%
		//폼에 입력된 데이터 가져오기
		double n1 = Double.parseDouble(request.getParameter("n1"));
		double n2 = Double.parseDouble(request.getParameter("n2"));
		String op = request.getParameter("op");
		
		//Calculator 객체 생성
		Calculator calc = new Calculator();
		calc.setN1(n1); //첫번째수 입력(저장)
		calc.setOp(op);
		calc.setN2(n2); //두번째수 입력
		
		//out.println(n1);
		//out.println(op);
		//out.println(n2);
		
		//계산하는 함수 호출
		//out.println(calc.calc());
		
	%>
	<p>계산 결과: <%=calc.calc() %></p>
</body>
</html>