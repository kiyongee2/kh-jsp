<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://kit.fontawesome.com/187dabceeb.js" crossorigin="anonymous"></script>
<header>
  <div id="logo">
	<h1><a href="/">khit</a></h1>
  </div>

  <nav>
  <c:choose>
	  <c:when test="${empty sessionId}">
	    <ul id="topMenu">
			<li><a href="/loginForm.do">로그인</a></li>
			<li><a href="/joinForm.do">회원가입</a></li>
			<li><a href="/boardList.do">게시판</a></li>
			<li><a href="/memberList.do">회원목록</a></li>
		</ul>
		</c:when>
		<c:otherwise>
		<ul id="topMenu">
			<li><a href="/logout.do">(${sessionName}님)로그아웃</a></li>
			<li><a href="/memberView.do?id=${sessionId}">나의정보</a></li>
			<li><a href="/boardList.do">게시판</a></li>
			<li><a href="/memberList.do">회원목록</a></li>
		</ul>
		</c:otherwise>
	</c:choose>
  </nav>
</header>