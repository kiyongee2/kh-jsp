<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 목록</title>
<style>
	#content{width: 80%; margin: 40px auto; text-align: center;}
	table{margin: 0 auto; border-collapse: collapse;}
	table th, td{
		border: 1px solid #ccc;
		padding: 10px;
	}
</style>
</head>
<body>
	<section id="content">
		<h2>학생 목록</h2>
		<hr>
		<p><a href="/studentform">[학생 추가]</a></p>
		<table>
			<tr>
				<th>sid</th><th>이름</th><th>대학</th><th>생일</th><th>이메일</th>
			</tr>
			<c:forEach var="s" varStatus="i" items="${students}">
			<tr>
				<td>${i.count}</td>
				<td>${s.username}</td>
				<td>${s.univ}</td>
				<td>${s.birth}</td>
				<td>${s.email}</td>
			</tr>
			</c:forEach>
		</table>
	</section>
</body>
</html>