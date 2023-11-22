<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 세션 발급</title>
<style>
	ul li{list-style: none; margin: 10px;}
</style>
</head>
<body>
	<form action="session1Process.jsp" method="post">
		<ul>
			<li>
				<label for="uid">아이디</label>
				<input type="text" name="uid" id="uid">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd">
			</li>
			<li>
				<input type="submit" value="로그인">
			</li>
		</ul>
	</form>
</body>
</html>