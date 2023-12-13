<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<div id="container">
		<h2>로그인</h2>
		<form action="process.jsp" method="post">
			<p>
			    <label>아이디</label>
				<input type="text" name="id">	
			<p>
			<p>
			    <label>비밀번호</label>
				<input type="password" name="pwd">	
			<p>
			<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>