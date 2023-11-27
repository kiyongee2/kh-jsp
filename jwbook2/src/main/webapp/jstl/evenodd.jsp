<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>짝수/홀수 폼</title>
<script>
	//점수를 입력하지 않았거나, 문자인 경우 오류 처리
	function check(){
		//alert("test");
		let form = document.form1;
		let number = form.number;

		if(number.value == "" || isNaN(number.value) 
				|| number.value.trim() == ''){
			alert("숫자를 입력해 주세요.");
			number.focus();
			return false;
		}else{
			form.submit();
		}
	}
</script>
</head>
<body>
	<form action="calcProcess.jsp" method="get" name="form1">
		<p>숫자: <input type="text" name="number">
		    <input type="button" value="전송" onclick="check()">
		</p>
	</form>
</body>
</html>