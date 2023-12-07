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
	   <section id="boardlist">
		<h2>게시판 목록</h2>
		<!-- 검색 폼 -->
		<form action="" method="get" class="searchform">
			<select name="field" class="field">
				<option value="title" ${(field eq "title") ? "selected" : ""}>제목</option>
			    <option value="id" ${(field eq "id") ? "selected" : ""}>작성자</option>
			</select>
			<input type="text" name="kw" value="${kw}" class="kw">
			<button type="submit">검색</button>
		</form>
		<!-- 목록 -->
		<table id="tbl_list">
			<thead>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>작성일</th>
					<th>조회수</th>
					<th>글쓴이</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${boardList}">
				<tr>
					<td><c:out value="${board.bno}" /></td>
					<td>
						<a href="/boardView.do?bno=${board.bno}">
							<c:out value="${board.title}" />
						</a>
					</td>
					<%-- <td><fmt:formatDate value="${board.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /> 
					</td> --%>
					<td>
						<c:choose>
       						<c:when test="${not empty board.modifyDate}">
       						  <fmt:formatDate value="${board.modifyDate}" 
       						  				pattern="yyyy-MM-dd hh:mm:ss"/>
       				    	</c:when>
         				    <c:otherwise>
         				   	  <fmt:formatDate value="${board.createDate}" 
         				   	  				pattern="yyyy-MM-dd hh:mm:ss"/>
         				   </c:otherwise>
       				   </c:choose>
					</td>
					<td><c:out value="${board.hit}" /></td>
					<td><c:out value="${board.id}" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- 페이지 처리 영역 -->
		<div class="pagination">
			<!-- 이전 페이지 -->
			<c:choose>
				<c:when test="${startPage > 1}">
					<a href="/boardList.do?pageNum=${startPage-1}">&laquo;</a>
				</c:when>
				<c:otherwise>
					<a href="">&laquo;</a>
				</c:otherwise>
			</c:choose>
			<!-- 페이지 리스트 -->
			<c:forEach var="i" begin="1" end="${endPage}">
				<%-- <a href="#">${i}</a>  --%>
				<c:choose>
					<c:when test="${currentPage == i}">
						<a href="/boardList.do?pageNum=${i}"><b>${i}</b></a> 
					</c:when>
					<c:otherwise>
						<a href="/boardList.do?pageNum=${i}">${i}</a> 
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- 다음 페이지 -->
			<c:choose>
				<c:when test="${endPage > startPage}">
					<a href="/boardList.do?pageNum=${startPage+1}">&raquo;</a>
				</c:when>
				<c:otherwise>
					<a href="">&raquo;</a>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- 글쓰기 버튼 -->
		<div class="btnWrite">
			<a href="/writeForm.do">
				<button type="button">글쓰기</button>
			</a>
		</div>
		</section>
	 </div>
 	<jsp:include page="../footer.jsp" />
</body>
</html>