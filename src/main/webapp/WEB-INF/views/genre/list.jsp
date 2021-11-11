<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="/js/modal.js"></script>
<script src="/js/genre.js"></script>
<div class="wrapper">
	<div>
		<h2>장르</h2>
		<div>
			<span id="modal-open-btn">+</span>
		</div>
	</div>
	<article id="genre-list">
		<c:forEach var="item" items="${list}">
			<div class="genre-item" data-genre="${item.code}">
				<a href="/novel/list?keyword=${item.contents}&search=3">${item.contents}</a> (${item.recom})
				<span class="genre-delete-btn">X</span>
			</div>
		</c:forEach>
	</article>
</div>
<div id="modal-wrapper">
	<div class="form">
		<h1>장르 추가</h1>
		<div>
			<input type="text" class="genre-input">
			<button class="genre-add-btn">확인</button>
		</div>
	</div>
</div>