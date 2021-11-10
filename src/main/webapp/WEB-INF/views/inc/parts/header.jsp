<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/js/user_menu.js"></script>
<div class="wrapper flex justify-between item-center">
	<div class="logo">
		<h1 class="text-black"><a href="/">Maze of Page</a></h1>
	</div>
	<form class="flex border-3 border-color-main round" action="/novel/list">
		<div class="flex item-center">
			<select class="search-select" name="search">
				<option value="1" ${pager.search == 1 ? 'selected' : ''}>제목</option>
				<option value="2" ${pager.search == 2 ? 'selected' : ''}>작성자</option>
				<option value="3" ${pager.search == 3 ? 'selected' : ''}>장르</option>
			</select>
		</div>
		<div class="search-box">
			<input type="text" class="input-text" name="keyword" value="${pager.keyword}">
			<button class="icon">
				<img alt="" src="/svg/search.svg">
			</button>
		</div>
	</form>
	<c:if test="${sessionScope.user != null}">
		<div class="user-box">
			<span class="nickname">${sessionScope.user.nickname}</span>님 환영합니다
			<ul class="user-menu">
				<li><a href="/logout">로그아웃</a></li>
				<li><a href="/profile/${sessionScope.user.id}">프로필</a></li>
			</ul>
		</div>
	</c:if>
	<c:if test="${sessionScope.user == null}">
		<div class="button">
			<a href="/login">로그인</a>
		</div>
	</c:if>
</div>