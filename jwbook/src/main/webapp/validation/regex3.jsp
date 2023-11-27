<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	ul li{list-style: none; margin: 10px;}
</style>
<script src="../resources/js/validation.js"></script>
</head>
<body>
	<h3>회원 가입</h3>
	<form action="joinProcess.jsp" method="post" name="member">
		<ul>
			<li>
				<label for="uid">아이디</label>
				<input type="text" id="uid" name="uid">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" id="passwd" name="passwd">
			</li>
			<li>
				<label for="passwd2">비밀번호 확인</label>
				<input type="password" id="passwd2" name="passwd2">
			</li>
			<li>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</li>
			<li>
				<button type="button" onclick="checkForm()">로그인</button>
			</li>
		</ul>
	</form>
</body>
</html>