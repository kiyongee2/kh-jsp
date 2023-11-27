<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 폼</title>
<style type="text/css">
	h2{background: green; text-align: center; 
	    border-radius: 5px; padding: 16px 0; color: #eee;}
	#regform{width: 50%; margin: 0 auto; background: lightgreen; 
	          padding: 15px 20px; border-radius: 10px;}
	ul li{list-style: none; margin: 16px;}
	label{width: 60px; float: left;}
</style>
<script type="text/javascript">
	function signUp(){
		let form = document.form1;
		let name = form.name.value;
		let email = form.email.value;
		
		alert("정말로 가입하시겠습니까?");
		document.getElementById('regform').style.display = "none";
		document.getElementById('rname').innerHTML = name;
		document.getElementById('rmail').innerHTML = email;
		document.getElementById('result').setAttribute('style', 
				'display:block; background: lightgreen; padding: 5px 20px;');
	}
</script>
</head>
<body>
	<h2>회원 가입</h2>
	<hr>
	<div id="regform">
		<form action="" method="post" name="form1">
			<ul>
				<li>
					<label for="name">이름</label>
					<input type="text" id="name" name="name" size="40">
				</li>
				<li>
					<label for="email">이메일</label>
					<input type="text" id="email" name="email" size="40">
				</li>
				<li>
					<button type="button" onclick="signUp()">가입</button>
				</li>
			</ul>
		</form>
	</div>
	
	<div id="result">
		<h3>가입 정보</h3>
		<hr>
		<p>이름: <span id="rname"></span></p>
		<p>이메일: <span id="rmail"></span></p>
	</div>
	
	<script>
		document.getElementById('result').style.display = "none";
	</script>
</body>
</html>