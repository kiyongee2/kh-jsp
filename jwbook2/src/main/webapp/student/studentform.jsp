<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 등록</title>
<style type="text/css">
    #content{width: 80%; margin: 0 auto; text-align: center;}
	h2{padding-left: 20px;}
	form{width: 360px; margin: 0 auto;}
	ul li{list-style:none; margin: 10px;}
	ul li label{width: 80px; float: left;}
	ul li input.birth{width: 166px;}
</style>
</head>
<body>
    <section id="content">
		<h2>학생 등록</h2>
		<hr>
		<form action="/insertstudent" method="post">
			<ul>
				<li>
					<label>이름</label>
					<input type="text" name="username">
				</li>
				<li>
					<label>대학</label>
					<input type="text" name="univ">
				</li>
				<li>
					<label>생일</label>
					<input type="date" name="birth" class="birth">
				</li>
				<li>
					<label>이메일</label>
					<input type="text" name="email">
				</li>
			</ul>
			<div class="button">
				<button type="submit">등록</button>
				<button type="reset">취소</button>
			</div>
		</form>
	</section>
</body>
</html>