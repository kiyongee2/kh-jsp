<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax test</title>
<script src="../js/jquery-3.7.1.js"></script>
<script>
	//alert("test");
	$(document).ready(function(){
		$('h3').css('color', 'blue');
	})

	function fnProcess(){
		$.ajax({
			type: "get",
			dataType: "text",
			url: "http://localhost:8080/jwbook3/ajaxtest/ajax1",
			data: {message: "Hello~ Ajax!"},
			success: function(data){
				$('#message').append(data)
				             .css("margin-top", "10px");
			},
			error: function(data){
				alert("에러 발생!!");
			},
		});
	}
</script>
</head>
<body>
    <h3>ajax 테스트</h3>
	<button type="button" onclick="fnProcess()">전송</button>
	<div id="message"></div>
</body>
</html>