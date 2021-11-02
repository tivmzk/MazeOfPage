<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<div>
			<a href="/profile/${sessionScope.user.id}" class="nickname">${sessionScope.user.nickname}</a>님 환영합니다
		</div>
	</c:if>
	<c:if test="${sessionScope.user == null}">
		<div class="btn">
			<a href="/login">로그인</a>
		</div>
	</c:if>
</div>