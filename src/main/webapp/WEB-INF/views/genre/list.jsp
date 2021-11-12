<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/js/modal.js"></script>
<script src="/js/genre.js"></script>
<div class="wrapper" id="genre-list-wrapper">
	<div class="article-title pt-30 pb-10 border-b-1 border-color-sub">
		<h2 class="text-black">장르</h2>
		<c:if test="${sessionScope.user.mgr == 1}">
			<div class="add">
				<span id="modal-open-btn">+</span>
			</div>
		</c:if>
	</div>
	<article id="genre-list">
		<c:forEach var="item" items="${list}">
			<div class="genre-item" data-genre="${item.code}">
				<a href="/novel/list?keyword=${item.contents}&search=3">${item.contents}</a> 
				<span class="recom">${item.recom}</span>
				<c:if test="${sessionScope.user.mgr == 1}">
					<span class="genre-delete-btn">X</span>
				</c:if>
			</div>
		</c:forEach>
	</article>
</div>
<div id="modal-wrapper">
	<div class="form">
		<h1 class="title">장르 추가</h1>
		<div class="input-wrapper">
			<div class="flex justify-between item-center">
				<div class="mr-30">
					<input type="text" class="genre-input">
				</div>
				<div class="button">
					<button class="genre-add-btn">확인</button>
				</div>
			</div>
		</div>
	</div>
</div>