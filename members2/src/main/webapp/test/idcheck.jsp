<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax test</title>
<script src="../resources/js/jquery-3.7.1.js"></script>
<script>
	function checkId(){
		let t_id = $("#t_id").val();
		$.ajax({
			type: "get",
			dataType: "text",
			url: "http://localhost:8080/test/checkid",
			data: {id: t_id},
			success: function(data){
				if(data == 'usable'){
					$('#message').text('사용할 수 있는 ID입니다');
				}else{
					$('#message').text('사용할 수 없는 ID입니다');
				}
			},
			error: function(data){
				alert("에러 발생!!");
			},
		});
	}
</script>
</head>
<body>
    <p><input type="text" id="t_id">
    <p><input type="button" value="ID 중복" onclick="checkId()">
    <div id="message"></div>
</body>
</html>