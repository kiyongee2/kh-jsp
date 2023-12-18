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
			${board.voter}
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
							    <div>
								<c:if test="${not empty board.filename}">
									<img src="../upload/${board.filename}" width="60%" height="40%">
								</c:if>
								</div>
								${board.content}
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
								조회수: ${board.hit}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    <a href="/vote.do?bno=${board.bno}" title="이 게시글이 마음에 들어요">
									<i class="fa-regular fa-heart"></i>
								</a>
							</td>
						</tr>
						<tr>
							<td>
							<c:choose>
								<c:when test="${not empty board.filename }">
									<c:out value="첨부파일: ${board.filename}" />
							    	<a href="/filedown.do?filename=${board.filename}"> [다운로드]</a>
								</c:when>
								<c:otherwise>
									<c:out value="첨부파일: " />
								</c:otherwise>
							</c:choose>
							</td>
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
			<!-- 댓글 영역 -->
			<h3><i class="fa-solid fa-pen-to-square"></i> 댓글</h3>
			<!-- <h4>댓글 목록</h4> -->
			<c:forEach items="${replyList}" var="reply">
				<div class="reply">
					<p>${reply.rcontent}</p>
					<p>작성자: ${reply.replyer}(작성일: ${reply.rdate})</p>
					<p>
					    <c:if test="${sessionId == reply.replyer}">
						<a href="/updateReplyform.do?bno=${board.bno}&rno=${reply.rno}">수정</a> | 
						<a href="/deletereply.do?bno=${board.bno}&rno=${reply.rno}"
						   onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a>
					    </c:if>
					</p>
				</div>
			</c:forEach>
			
			<!-- 댓글 등록 -->
			<form action="/insertreply.do" method="post" id="replyform">
				<input type="hidden" name="bno" value="${board.bno}">
				<input type="hidden" name="replyer" value="${sessionId}">
				<p>${sessionId}</p>
				<p>
					<textarea rows="4" cols="70" name="rcontent"
						placeholder="댓글을 남겨보세요"></textarea>
				</p>
				<button type="submit">등록</button>
			</form>
			<c:if test="${empty sessionId}">
			<div class="replylogin">
				<a href="/loginForm.do">
					<i class="fa-solid fa-user"></i> 로그인한 사용자만 댓글 등록이 가능합니다.
				</a>
			</div>
			</c:if>
		</section>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>