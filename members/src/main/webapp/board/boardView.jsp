<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div id="container">
		<section id="board_detail">
			<h2>게시글 상세 보기</h2>
				<table>
					<tbody>
						<tr>
							<td>
								<input type="text" name="title" 
										value="${board.title}" readonly="readonly">
							</td>
						</tr>
						<tr>
							<td>
								<textarea rows="8" cols="100" name="content"
									readonly="readonly">${board.content}</textarea> 
							</td>
						</tr>
						<tr>
							<td>
								<c:out value="글쓴이: ${board.id}"/>
	           					<c:choose>
	           						<c:when test="${not empty board.modifyDate}">
	           						  (수정일: <fmt:formatDate value="${board.modifyDate}" 
	           						  				pattern="yyyy-MM-dd hh:mm:ss"/>)
	           				    	</c:when>
	            				    <c:otherwise>
	            				   	  (작성일: <fmt:formatDate value="${board.createDate}" 
	            				   	  				pattern="yyyy-MM-dd hh:mm:ss"/>)
	            				   </c:otherwise>
	           				   </c:choose>
	           				</td>
						</tr>
						<tr>
							<td>
								${board.filename} <a href="/down.do?filename=${board.filename}">파일다운</a>
							</td>
						</tr>
						<tr>
							<td><c:out value="조회수: ${board.hit}" /></td>
						</tr>
						<tr>
							<td>
								<c:if test="${sessionId == board.id}">
									<a href="/updateBoardForm.do?bno=${board.bno}">
										<button type="button">수정</button>
									</a>
									<a href="/deleteBoard.do?bno=${board.bno}"
									   onclick="return confirm('정말로 삭제하시겠습니까?')">
										<button type="button">삭제</button>
									</a>
								</c:if>
								<a href="/boardList.do">
									<button type="button">목록</button>
								</a>
							</td>
						</tr>
					</tbody>
				</table>
		</section>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>