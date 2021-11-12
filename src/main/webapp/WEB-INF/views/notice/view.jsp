<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="wrapper" id="post-view-wrapper">
	<div class="pos-relative article-title pt-30 pb-10 border-b-1 border-color-sub header">
		<h2 class="text-black">공지사항</h2>
		<a class="back" href="../list">목록으로 돌아가기</a>
	</div>
	<div class="form">
		<div>
			<h3 class="title">${item.title}</h3>
			<a class="nickname" href="/profile/${item.mgr}">${item.nickname}</a>
			<span class="date"><fmt:formatDate value="${item.date}" pattern="yyyy-MM-dd"/></span>
		</div>
		<div class="contents-wrapper">
			<p class="contents">
				${item.contents}
			</p>
		</div>
	</div>
</div>