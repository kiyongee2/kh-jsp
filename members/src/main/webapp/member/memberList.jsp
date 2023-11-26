<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록</title>
<link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>
	<jsp:include page="../header.jsp" />
	  <div id="container">
	   <section id="memberlist">
		<h2>회원 목록</h2>
		<div class="logout">
			<p><a href="/logout.do">[관리자 로그아웃]</a></p>
		</div>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>비밀번호</th>
					<th>이름</th>
					<th>이메일</th>
					<th>성별</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${memberList}">
				<tr>
					<td>${member.mno}</td>
					<td>
						<a href="/memberView.do?id=${member.id}">${member.id}</a>
					</td>
					<td>${member.passwd}</td>
					<td>${member.name}</td>
					<td>${member.email}</td>
					<td>${member.gender}</td>
					<%-- <td>${member.joinDate}</td> --%>
					<td><fmt:formatDate value="${member.joinDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /> </td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- <h3>이벤트 추첨 링크</h3>
		<div class="banner">
			<a href="/memberEvent.do">
				<img src="../resources/images/bronx.png" alt="한식뷔페">
			</a>
		</div> -->
		</section>
	 </div>
 	<jsp:include page="../footer.jsp" />
</body>
</html>