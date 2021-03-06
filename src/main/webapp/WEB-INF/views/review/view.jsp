<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	const review_code = `${item.code}`;
	const review_member = `${item.member}`;
</script>
<script src="/js/review_comments.js"></script>
<script src="/js/modal.js"></script>

<div class="wrapper" id="post-view-wrapper">
	<div class="pos-relative article-title pt-30 pb-10 border-b-1 border-color-sub header">
		<h2 class="text-black">감상평</h2>
		<a  class="back" href="../list">목록으로 돌아가기</a>
	</div>
	<div class="form pos-relative">
		<div>
			<h3 class="title">${item.title}</h3>
			<a class="novel" href="/novel/detail/${item.novel}">${item.novelTitle}</a>
			<a href="/profile/${item.member}" class="nickname">${item.nickname}</a>
			<span class="date"><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd"/></span>
			<c:if test="${item.member == sessionScope.user.id}">
				<div class="btn-box">
					<div>
						<a class="update" href="../update/${item.code}">수정</a>
					</div>
					<div>
						<a class="delete" href="../delete/${item.code}">삭제</a>
					</div>
				</div>
			</c:if>
		</div>
		<div class="contents-wrapper">
			<pre class="contents">${item.contents}</pre>
		</div>
	</div>
	<div class="comments-wrapper">
		<div class="header">
			<div>
				댓글 <span class="comments-total"></span>개
			</div>
			<div>
				<button class="comments-sort desc">정렬</button>
			</div>
		</div>
		<div class="inner-wrapper">
			<div>
				<input class="comments-input" placeholder="댓글을 입력하세요"/>
			</div>
			<div class="button">
				<button class="comments-button">확인</button>
			</div>
		</div>
		<div class="comments-list">
			
		</div>
	</div>
</div>

<div id="modal-wrapper">
	<div class="form">
		<h1 class="title pb-10">댓글 수정</h1>
		<div class="input-wrapper">
			<div>
				<input class="comments-input input" type="text"></input>
			</div>
			<div class="flex justify-end">
				<div class="button">
					<button class="update">확인</button>
				</div>
			</div>
		</div>
	</div>
</div>