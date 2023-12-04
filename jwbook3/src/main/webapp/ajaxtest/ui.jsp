<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.7.1.js"></script>
<style>
	form ul {
		list-style: none;
	}
	form ul>li {
		text-align: right;
		width: 100px;
		display: inline-block;
		margin-right: 5px
	}
	#zumin{width:300px;text-align:left}
	form li:last-child {
		width: 300px;
		text-align: center;
	}
</style>
<script>
$(function() {
	$('#num1').keyup(function() {
		var num1 = $('#num1').val().trim();
		if (!($.isNumeric(num1))) {
			alert("숫자를 입력하세요");
			return false;
		}
	});

	$('#num2').keyup(function() {
		var num2 = $('#num2').val().trim();
		if (!($.isNumeric(num2))) {
			alert("숫자를 입력하세요");
			return false;
		}
		if (num2.length == 7) {
			if (num2.substring(0, 1) == 1 || num2.substring(0, 1) == 3) {
				$("input[value='M']").attr('checked', true);
			} else if (num2.substring(0, 1) ==2 || num2.substring(0, 1) == 4) {
				$("input[value='F']").attr('checked', true);
			}
		}
	});
	
	$("form").submit(function() {
		var value = $('#userId').val().trim();
		var reg = /^[A-za-z0-9_]{5,10}/;
		if (!reg.test(value)) {
			alert("5~10글자의 대문자, 소문자, 숫자 입력 가능");
			$('#userId').val('');
			return false;
		}
	});
});
</script>
</head>
<body>
	<fieldset>
	<legend>회원가입</legend>
	<form action="result.html" method="post">
	<ul>
		<li><label for="userId">아이디</label></li>
		<li><input type="text" name='userId' id="userId" size='10'
		required></li>
		<br>
		<li><label for='userName'>이름</label></li>
		<li><input type="text" name='userName' id="userName" size='10'
		required></li>
		<br>
		<li ><label for='zumin'>주민번호</label></li>
		<li id="zumin"><input type="text" name="num1" id="num1" size="10" maxlength="6" required>-
		<input type="password" name="num2" id="num2" size="10" maxlength="7" required>
		</li>
		<br>
		<li>성별</li>
		<li><label>남<input type="radio" name='gender' class="gender1" value="M"></label>   
		<label>여<input type="radio" name='gender' class="gender1" value="F"></label></li>
		<br>
		<li><input type="submit" value="회원가입">   
		<input type="reset" value="취소"></li>
		</ul>
	</form>
	</fieldset>
</body>
</html>