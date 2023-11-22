<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 등록</title>
<link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>
	<div id="container">
		<h2>로그인</h2>
		<hr>
		<form action="loginProcess.jsp" method="post">
			<table id="tbl_login">
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" maxlength=30 size=30></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="로그인">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>