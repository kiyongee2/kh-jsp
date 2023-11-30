<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
<link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div id="container">
		<section id="reply_update">
			<h2>댓글 수정</h2>
			<form action="/updatereply.do" method="post">
				<input type="hidden" name="bno" value="${reply.bno}">
				<input type="hidden" name="rno" value="${reply.rno}">
				<p>
					<textarea rows="4" cols="50" 
							name="rcontent">${reply.rcontent}</textarea>
				</p>
				<p>
					<input type="text" name="replyer" 
						value="${reply.replyer}" readonly>
				</p>
				<button type="submit">수정하기</button>
			</form>
		</section>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>