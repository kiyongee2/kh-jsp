<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 커뮤니티...</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
  <jsp:include page="header.jsp" />
  <div id="container">
   <section id="main">
	   <h2>안녕하세요~ KH-IT 커뮤니티입니다.</h2>
	   <div class="main_img">
	   	  <img src="resources/images/bird.jpg" alt="배경 사진">
	   </div>
	    <div class="newBoard">
		   	<h3><i class="fa-solid fa-pen"></i> 최신 게시글</h3>
		   	<c:forEach items="${boardList}" var="board">
		   		<p>
		   			<a href="/boardView.do?bno=${board.bno}">
				   		${board.title}(<fmt:formatDate value="${board.createDate}"
				   			pattern="yyyy-MM-dd HH:mm:ss"/>
				   		, 글쓴이: ${board.id})
			   		</a>
		   		</p>
	   		</c:forEach>
		   </div>
   </section>
 </div>
 <jsp:include page="footer.jsp" />
</body>
</html>